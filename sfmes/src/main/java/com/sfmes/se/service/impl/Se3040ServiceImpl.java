package com.sfmes.se.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.ca.service.Ca0100Service;
import com.sfmes.ca.service.Ca0200Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se1030Service;
import com.sfmes.se.service.Se3040Service;
import com.sfmes.se.service.Se6020Service;

/**
 * @Class Name : Se3040ServiceImpl.java
 * @Description : 매출(덤)등록/수정 및 조회
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.10.29  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) Se 모든솔루션 All right reserved.
 */

@Service("Se3040Service")
public class Se3040ServiceImpl extends CmnAbstractServiceImpl implements Se3040Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    //가격군별거래처 서비스 선언
    @Resource(name = "Se1030Service")
    private Se1030Service se1030service;     
    
    //제품출고 서비스 선언
    @Autowired
    private Se6020Service se6020Service;   
    
    //정산 서비스 선언
    @Autowired
    private Ca0100Service ca0100Service;
    
    //정산 서비스 선언
    @Autowired
    private Ca0200Service ca0200Service;
    
    //매출(덤)기본내역 조회
    @Override
    public List<?> selectSe3040_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매출(덤)기본내역조회[SE3040] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3040_01", paramMap);
    }

    //매출(덤)상세내역 조회
    @Override
    public List<?> selectSe3040_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매출(덤)상세내역조회[SE3040] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3040_02", paramMap);
    }
    
    //매출(덤)내역찾기팝업 조회
    @Override
    public List<?> selectSe3040_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매출(덤)내역찾기팝업조회[SE3040P01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3040_03", paramMap);
    }
    
    @Override
    public List<?> selectSe3040_04(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 콜 매출단가부가세포함여부조회[SE3040] *********");
        egovLogger.debug("paramMap: "+paramMap);
        return se1030service.Call_selecteSe1030_sl_upr_vat(paramMap);
    }    
    
    //매출(덤)기본,상세내역 등록
    @Override
    public String insertSe3040(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception { 
        egovLogger.debug("************ 매출(덤)등록[SE3040] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        String result = "";
        String resultMsg = null;  //결과메세지
        int chk_cnt = 0;
        
        //매출(덤)기본내역 저장 시 중복체크를 한다.
        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe3040Cnt", paramMap);
        if(chk_cnt > 0)
        {
            throw infoException("이미 등록된 매출(덤)내역입니다.");
        }
        
        //매출(덤)기본내역에 대한 정합성 체크를 한다.
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.se.selectSe3040Valid", paramMap);

        if(! resultMsg.equals("OK")) 
        {
            throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
        }
        
        //채번을 위한 변수설정
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_PRC_DT = paramMap.get("PRC_DT").toString();
        
        //채번 서비스 호출(처리일련번호)
        String seqNo = commonService.getGvno(s_CORP_C,"TB_SE_M_SL_DUM",s_BZPL_C, s_PRC_DT, 1);
        egovLogger.debug("생성된 처리일련번호 채번: " + seqNo);
        paramMap.put("PRC_SQNO", seqNo);

        //매출(덤)기본내역 저장
        egovLogger.debug("매출(덤)기본내역등록 TB_SE_M_SL_DUM");   
        egovLogger.debug("paramMap: " + paramMap.toString());
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_SL_DUM", paramMap);         
        
        //매출(덤)상세내역 저장        
        for(Map<String, Object> map : paramList) 
        {
            map.put("BZPL_C"  , paramMap.get("BZPL_C"));
            map.put("PRC_DT"  , paramMap.get("PRC_DT"));
            map.put("PRC_SQNO", paramMap.get("PRC_SQNO"));
            
            //매출상세내역에 대한 정합성 체크를 한다.
            resultMsg = sqlSession.selectOne("sfmes.sqlmap.se.selectSe3040ValidDet", map);

            if(! resultMsg.equals("OK")) 
            {
                throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
            } 
            
            egovLogger.debug("매출(덤)상세내역등록 TB_SE_D_SL_DUM"); 
            egovLogger.debug("map: " + map.toString());
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_D_SL_DUM", map);            
        }

        result = paramMap.toString();
        return result; 
    }

    //매출(덤)내역수정
    @Override
    public String updateSe3040(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 매출(덤)삭제[SE3040] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        String result = "";
        int chk_cnt   = 0;
        
        //매출(덤)기본내역 삭제 시 중복체크를 한다.
        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe3040Cnt", paramMap);
        if(chk_cnt > 0)
        {
            throw infoException("이미 삭제된 매출(덤)내역입니다.");
        }        
        
        //매출(덤)기본내역 수정(전표정상여부: "N")
        egovLogger.debug("매출(덤)기본내역수정 TB_SE_M_SL_DUM");        
        sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_SL_DUM", paramMap);
        
        if("Y".equals(paramMap.get("SLP_NML_YN").toString()))
        {
            //매출(덤)상세내역 수정
            egovLogger.debug("매출(덤)상세내역수정 TB_SE_D_SL_DUM");
            for(Map<String, Object> map : paramList) 
            {
                map.put("BZPL_C"  , paramMap.get("BZPL_C"));
                map.put("PRC_DT"  , paramMap.get("PRC_DT"));
                map.put("PRC_SQNO", paramMap.get("PRC_SQNO"));   
                
                if(map.get("_status_").equals("+")) 
                {
                    egovLogger.debug("입력 map: " + map.toString());
                    sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_D_SL_DUM", map);
                }
                else 
                {
                    egovLogger.debug("수정 map: " + map.toString());
                    sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_SL_DUM", map);               
                }
            }            
        }        
        
        result = paramMap.toString();
        return result; 
    }
}
