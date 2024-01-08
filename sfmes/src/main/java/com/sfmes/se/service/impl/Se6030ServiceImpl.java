package com.sfmes.se.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.sfmes.se.service.Se6030Service;

/**
 * @Class Name : Se6030ServiceImpl.java
 * @Description : 출고지시등록/수정 및 조회
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

@Service("Se6030Service")
public class Se6030ServiceImpl extends CmnAbstractServiceImpl implements Se6030Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;     
    
    //가격군별거래처 서비스 선언
    @Resource(name = "Se1030Service")
    private Se1030Service se1030service;    

    //출고지시기본내역 조회
    @Override
    public List<?> selectSe6030_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시기본내역조회[SE6030] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe6030_01", paramMap);
    }

    //출고지시상세내역 조회
    @Override
    public List<?> selectSe6030_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시상세내역조회[SE6030] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe6030_02", paramMap);
    }
    
    //매출단가부가세포함여부 조회
    @Override
    public List<?> selectSe6030_04(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 콜 매출단가부가세포함여부조회[SE6030] *********");
        egovLogger.debug("paramMap: "+paramMap);
        return se1030service.Call_selecteSe1030_sl_upr_vat(paramMap);
    }    

    //출고지시등록
    @Override
    public String insertSe6030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 출고지시등록[SE6030] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());

        //리턴할 값 담아놓을 변수설정
        String result = "";       
        String resultMsg = null;  //결과메세지
        
        //출고지시기본내역에 대한 정합성 체크를 한다.
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.se.selectSe6030Valid", paramMap);
        if(! resultMsg.equals("OK")) {
            throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
        }
        
        //채번을 위한 변수설정
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_DLR_DNTT_DT = paramMap.get("DLR_DNTT_DT").toString();
        
        //채번 서비스 호출(출고지시일련번호)
        String seqNo = commonService.getGvno(s_CORP_C, "TB_SE_M_DLR_DNTT", s_BZPL_C, s_DLR_DNTT_DT, 1);
        egovLogger.debug("생성된 출고지시일련번호 채번: " + seqNo);
        paramMap.put("DLR_DNTT_SQNO", seqNo);
        
        //배송고객정보 등록
        if("2".equals(paramMap.get("DVY_OBJ_DSC").toString())) {
            //오늘날짜구하기
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());      
            
            //채번 서비스 호출(출고지시일련번호)
            String seqNo2 = commonService.getGvno(s_CORP_C, "TB_SE_M_DVY_CUS", s_BZPL_C, strToday, 1);
            
            egovLogger.debug("생성된 배송고객등록일련번호 채번: " + seqNo2);
            paramMap.put("DVY_CUS_REG_DT", strToday);
            paramMap.put("DVY_CUS_REG_SQNO", seqNo2);
            
            egovLogger.debug("배송고객등록 TB_SE_M_DVY_CUS");
            egovLogger.debug("paramMap: " + paramMap.toString());
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_DVY_CUS", paramMap);
        } else {
            //배송고객등록일련번호, 등록날짜 초기화
            paramMap.put("DVY_CUS_REG_DT"  , null);
            paramMap.put("DVY_CUS_REG_SQNO", null);
        }
        
        //출고지시기본내역 저장
        egovLogger.debug("출고지시기본내역등록 TB_SE_M_RVO");
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_DLR_DNTT", paramMap); 
        
        //출고지시상세내역 저장
        egovLogger.debug("출고지시상세내역등록 TB_SE_D_RVO");
        for(Map<String, Object> map : paramList) {
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("DLR_DNTT_DT", paramMap.get("DLR_DNTT_DT"));
            map.put("DLR_DNTT_SQNO", paramMap.get("DLR_DNTT_SQNO"));
            
            //출고지시상세내역에 대한 정합성 체크를 한다.
            resultMsg = sqlSession.selectOne("sfmes.sqlmap.se.selectSe6030ValidDet", map);
            if(! resultMsg.equals("OK")) {
                throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
            } 
            
            egovLogger.debug("map: " + map.toString());
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_D_DLR_DNTT", map);
        }
        
        result = paramMap.toString();
        return result;
    }

    //출고지시수정
    @Override
    public String updateSe6030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 출고지시수정[SE6030] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        //리턴할 값 담아놓을 변수설정
        String result = "";
        
        LinkedHashMap resultMap = sqlSession.selectOne("sfmes.sqlmap.tb.select_TB_SE_M_DLR_DNTT", paramMap);
        if("2".equals(resultMap.get("DLR_DNTT_STS_DSC").toString())) {
            throw infoException("이미 출고등록된 내역은 수정할 수 없습니다.");
        }      
        
        //채번을 위한 변수설정
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String dvyCusRegSqno = (String)paramMap.get("DVY_CUS_REG_SQNO");
        
        //배송고객기본 수정
        if("2".equals(paramMap.get("DVY_OBJ_DSC"))) {
            if("".equals(dvyCusRegSqno) || dvyCusRegSqno == null) {
                //오늘날짜구하기
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Calendar c1 = Calendar.getInstance();
                String strToday = sdf.format(c1.getTime());      
                
                //채번 서비스 호출(출고지시일련번호)
                String seqNo2 = commonService.getGvno(s_CORP_C, "TB_SE_M_DVY_CUS", s_BZPL_C, strToday, 1);
                
                egovLogger.debug("생성된 배송고객등록일련번호 채번: " + seqNo2);
                paramMap.put("DVY_CUS_REG_DT", strToday);
                paramMap.put("DVY_CUS_REG_SQNO", seqNo2);
                
                egovLogger.debug("배송고객등록 TB_SE_M_DVY_CUS");
                egovLogger.debug("paramMap: " + paramMap.toString());
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_DVY_CUS", paramMap);
            } else {
	            egovLogger.debug("배송고객수정 TB_SE_M_DVY_CUS");
	            egovLogger.debug("paramMap: " + paramMap.toString());
	            sqlSession.insert("sfmes.sqlmap.tb.update_TB_SE_M_DVY_CUS", paramMap);
            }
        }
        
        //출고지시기본내역 수정
        egovLogger.debug("출고지시기본내역수정 TB_SE_M_DLR_DNTT");
        sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_DLR_DNTT", paramMap); 
        
        //출고지시상세내역 수정
        egovLogger.debug("출고지시상세내역수정 TB_SE_D_DLR_DNTT");
        for(Map<String, Object> map : paramList) {
            map.put("BZPL_C", s_BZPL_C);
            map.put("DLR_DNTT_DT", paramMap.get("DLR_DNTT_DT"));
            map.put("DLR_DNTT_SQNO", paramMap.get("DLR_DNTT_SQNO"));            
            
            if(map.get("_status_").equals("+"))  {
                egovLogger.debug("입력 map: " + map.toString());
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_D_DLR_DNTT", map);
            } else {
                egovLogger.debug("수정 map: " + map.toString());
                sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_D_DLR_DNTT", map);               
            }
        }
        
        result = paramMap.toString();
        return result;
    }
    
    //출고지시내역 삭제
    @Override
    public void deleteSe6030(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시삭제[SE6030] *********");
        egovLogger.debug("paramMap: " + paramMap.toString());
        
        LinkedHashMap resultMap = sqlSession.selectOne("sfmes.sqlmap.tb.select_TB_SE_M_DLR_DNTT", paramMap);
        if("2".equals(resultMap.get("DLR_DNTT_STS_DSC").toString())) {
            throw infoException("이미 출고등록된 내역은 삭제할 수 없습니다.");
        }
        
        //출고지시기본내역 수정(삭제여부: "Y")
        egovLogger.debug("출고지시기본내역수정 TB_SE_M_DLR_DNTT");        
        sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_DLR_DNTT", paramMap); 
    }    

    //출고지시마감내역 수정
    //조건parameter - 회사코드, 사업장코드, 출고지시마감일자, 출고지시마감일련번호
    //입력parameter - 출고지시일자, 출고지시일련번호
    @Override
    public void Call_updateSe6030_DLR_DNTT(List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 출고지시마감내역수정(출고지시)[SE6030] *********");
        egovLogger.debug("paramList: " + paramList.toString());
        
        sqlSession.update("sfmes.sqlmap.se.updateSe6030_DLR_DNTT", paramList);
    }

    //출고지시마감내역 수정
    //조건parameter - 회사코드, 사업장코드, 출고지시마감일자, 출고지시마감일련번호
    //입력parameter - 작업지시일자, 작업지시일련번호    
    @Override
    public void Call_updateSe6030_WK_DNTT(Map<String, Object> map) throws Exception {
        egovLogger.debug("************ 출고지시마감내역수정(작업지시)[SE6030] *********");
        egovLogger.debug("paramList: " + map.toString());
        
        sqlSession.update("sfmes.sqlmap.se.updateSe6030_WK_DNTT", map);
    }
}
