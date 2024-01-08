package com.sfmes.se.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se1030Service;
import com.sfmes.se.service.Se2010Service;
import com.sfmes.se.service.Se2030Service;

/**
 * @Class Name : Se2010ServiceImpl.java
 * @Description : 수주등록/수정 및 조회
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.06  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se2010Service")
public class Se2010ServiceImpl extends CmnAbstractServiceImpl implements Se2010Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;     
    
    //가격군별거래처 서비스 선언
    @Resource(name = "Se1030Service")
    private Se1030Service se1030service;    
    
    //긴급수주시 수주마감
    @Resource(name = "Se2030Service")
    private Se2030Service se2030service;    

    //수주기본내역 조회
    @Override
    public List<?> selectSe2010_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주기본내역조회[SE2010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe2010_01", paramMap);
    }

    //수주상세내역 조회
    @Override
    public List<?> selectSe2010_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주상세내역조회[SE2010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe2010_02", paramMap);
    }
    
    //수주내역찾기팝업 조회
    @Override
    public List<?> selectSe2010_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주내역찾기팝업조회[SE2010P01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe2010_03", paramMap);
    }
    
    //매출단가부가세포함여부 조회
    @Override
    public List<?> selectSe2010_04(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 콜 매출단가부가세포함여부조회[SE2010] *********");
        egovLogger.debug("paramMap: "+paramMap);
        return se1030service.Call_selecteSe1030_sl_upr_vat(paramMap);
    }    

    //수주등록
    @Override
    public String insertSe2010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 수주등록[SE2010] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());

        //리턴할 값 담아놓을 변수설정
        String result = "";       
        String resultMsg = null;  //결과메세지
        int chk_cnt = 0;

        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe2010Cnt", paramMap);
        if(chk_cnt > 0)
        {
            throw infoException("이미 등록된 수주내역입니다.");
        }
        
        //수주기본내역에 대한 정합성 체크를 한다.
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.se.selectSe2010Valid", paramMap);
        if(! resultMsg.equals("OK")) 
        {
            throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
        }
        
        //채번을 위한 변수설정
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_RVO_DT = paramMap.get("RVO_DT").toString();
        
        //채번 서비스 호출(수주일련번호)
        String seqNo = commonService.getGvno(s_CORP_C, "TB_SE_M_RVO", s_BZPL_C, s_RVO_DT, 1);
        egovLogger.debug("생성된 수주일련번호 채번: " + seqNo);
        paramMap.put("RVO_SQNO", seqNo);
        
        //배송고객정보 등록
        if("2".equals(paramMap.get("DVY_OBJ_DSC").toString()))
        {
            //오늘날짜구하기
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());      
            
            //채번 서비스 호출(수주일련번호)
            String seqNo2 = commonService.getGvno(s_CORP_C, "TB_SE_M_DVY_CUS", s_BZPL_C, strToday, 1);
            
            egovLogger.debug("생성된 배송고객등록일련번호 채번: " + seqNo2);
            paramMap.put("DVY_CUS_REG_DT", strToday);
            paramMap.put("DVY_CUS_REG_SQNO", seqNo2);
            
            egovLogger.debug("배송고객등록 TB_SE_M_DVY_CUS");
            egovLogger.debug("paramMap: " + paramMap.toString());
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_DVY_CUS", paramMap);
        }
        else 
        {
            //배송고객등록일련번호, 등록날짜 초기화
            paramMap.put("DVY_CUS_REG_DT"  , null);
            paramMap.put("DVY_CUS_REG_SQNO", null);
        }
        
        if (paramMap.get("EMRG_RVO_YN").equals("Y")) {
            paramMap.put("RVO_CLO_DT"  , paramMap.get("RVO_DT"));
            paramMap.put("RVO_CLO_SQNO", "0");
            paramMap.put("RVO_STS_DSC" , "2");
        }
        
        //수주기본내역 저장
        egovLogger.debug("수주기본내역등록 TB_SE_M_RVO");
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_RVO", paramMap); 
        
        //수주상세내역 저장
        egovLogger.debug("수주상세내역등록 TB_SE_D_RVO");
        for(Map<String, Object> map : paramList) 
        {
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("RVO_DT", paramMap.get("RVO_DT"));
            map.put("RVO_SQNO", paramMap.get("RVO_SQNO"));
            
            //수주상세내역에 대한 정합성 체크를 한다.
            resultMsg = sqlSession.selectOne("sfmes.sqlmap.se.selectSe2010ValidDet", map);
            if(! resultMsg.equals("OK")) 
            {
                throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
            } 
            
            egovLogger.debug("map: " + map.toString());
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_D_RVO", map);
        }
        
        result = paramMap.toString();
        return result;
    }

    //수주수정
    @Override
    public String updateSe2010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 수주수정[SE2010] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        //리턴할 값 담아놓을 변수설정
        String result = "";
        
        LinkedHashMap resultMap = sqlSession.selectOne("sfmes.sqlmap.tb.select_TB_SE_M_RVO", paramMap);
//        2022.04.14 나명우 대표님 지시사항으로 수주마감상태도 수정가능하게끔 변경
//        if("2".equals(resultMap.get("RVO_STS_DSC").toString()))
//        {
//            throw infoException("이미 수주마감된 내역은 수정할 수 없습니다.");
//        }
         if("3".equals(resultMap.get("RVO_STS_DSC").toString()))
        {
            throw infoException("이미 출고등록된 내역은 수정할 수 없습니다.");
        }        
        
        //배송고객기본 수정
        if("2".equals(paramMap.get("DVY_OBJ_DSC")))
        {
            if("0".equals(paramMap.get("DVY_CUS_REG_SQNO")))
            {
                //채번을 위한 변수설정
                String s_CORP_C = paramMap.get("CORP_C").toString();
                String s_BZPL_C = paramMap.get("BZPL_C").toString();
                
                //오늘날짜구하기
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Calendar c1 = Calendar.getInstance();
                String strToday = sdf.format(c1.getTime());      
                
                //채번 서비스 호출(수주일련번호)
                String seqNo2 = commonService.getGvno(s_CORP_C, "TB_SE_M_DVY_CUS", s_BZPL_C, strToday, 1);
                
                egovLogger.debug("생성된 배송고객등록일련번호 채번: " + seqNo2);
                paramMap.put("DVY_CUS_REG_DT", strToday);
                paramMap.put("DVY_CUS_REG_SQNO", seqNo2);
                
                egovLogger.debug("배송고객등록 TB_SE_M_DVY_CUS");
                egovLogger.debug("paramMap: " + paramMap.toString());
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_DVY_CUS", paramMap);
            }
            
            egovLogger.debug("배송고객수정 TB_SE_M_DVY_CUS");
            egovLogger.debug("paramMap: " + paramMap.toString());
            sqlSession.insert("sfmes.sqlmap.tb.update_TB_SE_M_DVY_CUS", paramMap);
        }
        
        //수주기본내역 수정
        egovLogger.debug("수주기본내역수정 TB_SE_M_RVO");
        sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_RVO", paramMap); 
        
        //수주상세내역 수정
        egovLogger.debug("수주상세내역수정 TB_SE_D_RVO");
        for(Map<String, Object> map : paramList) 
        {
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("RVO_DT", paramMap.get("RVO_DT"));
            map.put("RVO_SQNO", paramMap.get("RVO_SQNO"));            
            
            if(map.get("_status_").equals("+")) 
            {
                egovLogger.debug("입력 map: " + map.toString());
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_D_RVO", map);
            }
            else 
            {
                egovLogger.debug("수정 map: " + map.toString());
                sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_D_RVO", map);               
            }
        }
        
        result = paramMap.toString();
        return result;
    }
    
    //수주내역 삭제
    @Override
    public void deleteSe2010(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주삭제[SE2010] *********");
        egovLogger.debug("paramMap: " + paramMap.toString());
        
        int chk_cnt = 0;
        String errMsg = "";
        
        /*---------------------------------------------------------------------------------
         * 수주 기본 유효성(NULL) 체크 
         *--------------------------------------------------------------------------------*/
        errMsg = sqlSession.selectOne("sfmes.sqlmap.se.selectSe2010Valid_02", paramMap);
        if(!errMsg.equals("OK")) {
            throw infoException(errMsg + "\n전산담당자에게 문의하세요");
        }
        
        /*---------------------------------------------------------------------------------
         * 수주상태구분이 [1]수주상태 인지 확인
         *--------------------------------------------------------------------------------*/
        LinkedHashMap resultMap = sqlSession.selectOne("sfmes.sqlmap.tb.select_TB_SE_M_RVO", paramMap);
        /*
         *  수주마감된 내역도 삭제(수주취소) 가능 하도록 수정함 
         *  
        if("2".equals(resultMap.get("RVO_STS_DSC").toString()))
        {
            throw infoException("이미 수주마감된 내역은 삭제할 수 없습니다.");
        }
        else 
        */  
        if("3".equals(resultMap.get("RVO_STS_DSC").toString()))
        {
            throw infoException("이미 출고등록된 내역은 삭제할 수 없습니다.");
        } 
        else if("9".equals(resultMap.get("RVO_STS_DSC").toString()))
        {
            throw infoException("이미 수주취소된 건 입니다. 재확인 바랍니다.");
        } 
        
        //수주기본내역 삭제 시 중복체크를 한다.
        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe2010Cnt", paramMap);
        if(chk_cnt > 0)
        {
            throw infoException("이미 삭제된 수주내역입니다.");
        }        

        /*---------------------------------------------------------------------------------
         * 수주취소사유, 수주상태구분, 삭제여부 UPDATE 
         *--------------------------------------------------------------------------------*/
        //수주기본내역 수정(삭제여부: "Y")
        egovLogger.debug("수주기본내역수정 TB_SE_M_RVO");        
        sqlSession.update("sfmes.sqlmap.se.updateSe2010_cancel", paramMap); 
//        sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_RVO", paramMap); 
    }    

    //수주마감내역 수정
    //조건parameter - 회사코드, 사업장코드, 수주마감일자, 수주마감일련번호
    //입력parameter - 출고지시일자, 출고지시일련번호
    @Override
    public void Call_updateSe2010_DLR_DNTT(List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 수주마감내역수정(출고지시)[SE2010] *********");
        egovLogger.debug("paramList: " + paramList.toString());
        
        sqlSession.update("sfmes.sqlmap.se.updateSe2010_DLR_DNTT", paramList);
    }

    //수주마감내역 수정 작업지시기본
    //조건parameter - 회사코드, 사업장코드, 수주마감일자, 수주마감일련번호
    //입력parameter - 작업지시일자, 작업지시일련번호    
    @Override
    public void Call_updateSe2010_WK_DNTT(Map<String, Object> map) throws Exception {
        egovLogger.debug("************ 수주마감내역수정(작업지시)[SE2010] *********");
        egovLogger.debug("paramList: " + map.toString());
        
        sqlSession.update("sfmes.sqlmap.se.updateSe2010_WK_DNTT", map);
    }
    
    //수주마감내역 수정 작업지시상세  추가  20211219  rchkorea
    //조건parameter - 회사코드, 사업장코드, 수주마감일자, 수주마감일련번호
    //입력parameter - 작업지시일자, 작업지시일련번호    
    @Override
    public void Call_updateSe2010_WK_DNTT_D(Map<String, Object> map) throws Exception {
        egovLogger.debug("************ 수주마감내역수정(작업지시)[SE2010] *********");
        egovLogger.debug("paramList: " + map.toString());
        
        sqlSession.update("sfmes.sqlmap.se.updateSe2010_WK_DNTT_D", map);
    }
    
    //수주마감내역 수주건별분할 수정 작업지시기본 추가  20220407  ksckorea
    //조건parameter - 회사코드, 사업장코드, 수주마감일자, 수주마감일련번호
    //입력parameter - 작업지시일자, 작업지시일련번호    
    @Override
    public void Call_updateSe2010_WK_DNTT_NON(Map<String, Object> map) throws Exception {
        egovLogger.debug("************ 수주마감내역수주건별분할수정(작업지시)[SE2010] *********");
        egovLogger.debug("paramList: " + map.toString());
        
        sqlSession.update("sfmes.sqlmap.se.updateSe2010_WK_DNTT_NON", map);
    }
    
    //수주마감내역 수정 작업지시상세  추가  20220504  ksckorea
    //조건parameter - 회사코드, 사업장코드, 수주마감일자, 수주마감일련번호
    //입력parameter - 작업지시일자, 작업지시일련번호    
    @Override
    public void Call_updateSe2010_WK_DNTT_D_NON(Map<String, Object> map) throws Exception {
        egovLogger.debug("************ 수주마감내역수정(작업지시)[SE2010] *********");
        egovLogger.debug("paramList: " + map.toString());
        
        sqlSession.update("sfmes.sqlmap.se.updateSe2010_WK_DNTT_D_NON", map);
    }

}
