package com.sfmes.by.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hsqldb.lib.StringUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.by.service.By2010Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : By2010ServiceImpl.java
 * @Description : 발주등록/수정 및 조회
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.06  곽환용   최초생성
 * @ 2021.12.23  여다혜   발주취소 추가
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("By2010Service")
public class By2010ServiceImpl extends CmnAbstractServiceImpl implements By2010Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;    

    //발주기본내역 조회
    @Override
    public List<?> selectBy2010_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 발주기본내역조회[BY2010] *********");
        egovLogger.debug("paramMap: " + paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2010_01", paramMap);
    }

    //발주상세내역 조회
    @Override
    public List<?> selectBy2010_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 발주상세내역조회[BY2010] *********");
        egovLogger.debug("paramMap: " + paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2010_02", paramMap);
    }
    
    //발주내역찾기팝업 조회
    @Override
    public List<?> selectBy2010_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 발주내역찾기팝업조회[BY2010P01] *********");
        egovLogger.debug("paramMap: " + paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2010_03", paramMap);
    }

    //발주서내역 조회
    @Override
    public List<?> selectBy2010_04(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 발주서 조회[BY2010R01] *********");
        egovLogger.debug("paramMap: " + paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2010_04", paramMap);
    }    
    
    //발주기본,상세내역 등록
    @Override
    public String insertBy2010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 발주등록[BY2010] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        String result = "";
        String resultMsg = null;
        int chk_cnt = 0;
        
        //발주기본내역 등록 시 중복체크를 한다.
        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.by.selectBy2010Cnt", paramMap);
        if(chk_cnt > 0) throw infoException("이미 등록된 발주내역입니다.");
        
        //발주기본내역에 대한 정합성 체크를 한다.
        egovLogger.debug("발주기본내역 Validation Check"); 
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.by.selectBy2010Valid", paramMap);

        if(! resultMsg.equals("OK")) {
            throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
        }        

        //채번을 위한 변수설정
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_ODR_DT = paramMap.get("ODR_DT").toString();
        
        //채번 서비스 호출(발주일련번호)
        String seqNo = commonService.getGvno(s_CORP_C, "TB_BY_M_ODR", s_BZPL_C, s_ODR_DT, 1);
        egovLogger.debug("생성된 발주일련번호 채번: " + seqNo);
        paramMap.put("ODR_SQNO", seqNo);

        //발주기본내역 저장
        egovLogger.debug("발주기본내역등록 TB_BY_M_ODR");        
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_BY_M_ODR", paramMap); 
        
        //발주상세내역 저장
        egovLogger.debug("발주상세내역등록 TB_BY_D_ODR");        
        for(Map<String, Object> map : paramList) 
        {
            map.put("BZPL_C",   paramMap.get("BZPL_C"));
            map.put("ODR_DT",   paramMap.get("ODR_DT"));
            map.put("ODR_SQNO", paramMap.get("ODR_SQNO"));
            
            //발주상세내역에 대한 정합성 체크를 한다.
            egovLogger.debug("발주상세내역 Validation Check"); 
            resultMsg = sqlSession.selectOne("sfmes.sqlmap.by.selectBy2010ValidDet", map);

            if(! resultMsg.equals("OK")) 
            {
                throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
            } 

            egovLogger.debug("map: " + map.toString());
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_BY_D_ODR", map);
        }
        
        result = paramMap.toString();
        return result;     
    }

    //발주기본,상세내역 수정
    @Override
    public String updateBy2010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 발주수정[BY2010] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        //변수선언
        String result = "";
        int chk_cnt = 0;
        
        //발주내역 삭제하기 전 체크
        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.by.select_validationBy2010", paramMap);
        if(chk_cnt > 0) throw infoException("이미 검수입고된 발주내역입니다."); 

        //발주기본내역 수정
        egovLogger.debug("발주기본내역수정 TB_BY_M_ODR");
        sqlSession.update("sfmes.sqlmap.tb.update_TB_BY_M_ODR", paramMap);
        
        if("N".equals(paramMap.get("DEL_YN")))
        {
            //발주상세내역 수정
            egovLogger.debug("발주상세내역수정 TB_BY_D_ODR");
            for(Map<String, Object> map : paramList) 
            {
                map.put("BZPL_C",   paramMap.get("BZPL_C"));
                map.put("ODR_DT",   paramMap.get("ODR_DT"));
                map.put("ODR_SQNO", paramMap.get("ODR_SQNO"));   
                
                if(map.get("_status_").equals("+")) 
                {
                    egovLogger.debug("입력 map: " + map.toString());
                    sqlSession.insert("sfmes.sqlmap.tb.insert_TB_BY_D_ODR", map);
                }
                else 
                {
                    egovLogger.debug("수정 map: " + map.toString());
                    sqlSession.update("sfmes.sqlmap.tb.update_TB_BY_D_ODR", map);               
                }
            }            
        }
        
        result = paramMap.toString();
        return result;        
    }

    //참조발주내역수정
    @Override
    public void Call_updateBy2010(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 발주상태구분수정[BY2010] *********");
        
        paramMap.put("ODR_DT"  , paramMap.get("STDV_REF_DT"));
        paramMap.put("ODR_SQNO", paramMap.get("STDV_REF_SQNO"));
        paramMap.put("STR_DT"  , paramMap.get("STDV_DT"));
        paramMap.put("STR_SQNO", paramMap.get("STDV_SQNO"));
        
        egovLogger.debug("발주기본내역수정 TB_BY_M_ODR");
        egovLogger.debug("paramMap: " + paramMap.toString());
        sqlSession.update("sfmes.sqlmap.by.updateBy2010_STS", paramMap);
    }

    //발주취소 (20211223 추가 여다혜)
    @Override
    public void updateBy2010_cancel(LinkedHashMap paramMap) throws Exception {
        String errMsg = "";
        
        /*---------------------------------------------------------------------------------
         * 발주 기본 유효성(NULL) 체크 
         *--------------------------------------------------------------------------------*/
        errMsg = sqlSession.selectOne("sfmes.sqlmap.by.selectBy2010Valid_02", paramMap);
        if(!errMsg.equals("OK")) {
            throw infoException(errMsg + "\n전산담당자에게 문의하세요");
        }

        /*---------------------------------------------------------------------------------
         * 발주상태구분이 [1]발주상태 인지 확인
         *--------------------------------------------------------------------------------*/
        int cnt = sqlSession.selectOne("sfmes.sqlmap.by.selectBy2010_cnt_01", paramMap);
        if(cnt != 1) throw infoException("발주상태구분이 [1]발주상태가 아닙니다.\n\n" +
                                         "발주취소가 불가합니다. 재확인 바랍니다.");  
        
        
        /*---------------------------------------------------------------------------------
         * 발주취소사유, 발주상태구분 UPDATE 
         *--------------------------------------------------------------------------------*/
        sqlSession.update("sfmes.sqlmap.by.updateBy2010_cancel", paramMap);
        
        
    }
}
