package com.sfmes.ge.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.ge.service.Ge2030Service;

/**
 * @Class Name : Ge2030ServiceImpl.java
 * @Description : 민원사후관리 등록/수정 및 조회
 * @Modification Information
 * @
 * @  수정일     수정자      수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.11.17  유승현      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.11.17
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Ge2030Service")
public class Ge2030ServiceImpl extends CmnAbstractServiceImpl implements Ge2030Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 공통 서비스 선언
	@Resource(name = "CommonService")
	private CommonService commonService;
	
    //민원접수 조회
    @Override
    public List<?> selectGe2030_01(LinkedHashMap paramMap) throws Exception {     
        egovLogger.debug("************* 민원접수내역 조회 [selectGe2030_01] ****************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe2030_01", paramMap);       
    }
    
    //민원사후관리 조회
    @Override
    public List<?> selectGe2030_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************* 민원사후관리 조회 [selectGe2030_02] ****************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe2030_02", paramMap);
    }
    
    //민원접수 상세 조회
    @Override
    public List<?> selectGe2030_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************* 민원접수 상세 조회 [selectGe2030_03] ****************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe2030_03", paramMap);
    }
    
    //민원사후 상세 조회
    @Override
    public List<?> selectGe2030_04(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************* 민원사후 상세 조회 [selectGe2030_04] ****************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe2030_04", paramMap);
    }
    
    //민원사후 팝업 조회
    @Override
    public List<?> selectGe2030_05(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************* 민원사후 팝업 조회 [selectGe2030_05] ****************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe2030_05", paramMap);
    }    
    
    //민원사후관리 신규 등록
	@Override
	public String insertGe2030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {    
	    egovLogger.debug("************ 민원사후관리 등록 [insertGe2030] ************");
	    egovLogger.debug("paramMap: "  + paramMap.toString());
	    egovLogger.debug("paramList: " + paramList.toString());
	    
	    String result = "";
	    
        int chk_cnt = 0;
        
        //민원사후관리 등록된 내역 존재 확인
        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.ge.selectGe2030CntR", paramMap);        
        
        if(chk_cnt != 0) 
        {
            throw infoException("이미 등록된 내역이 존재합니다. 민원처리번호를 확인하세요.");
        }	    
	    
	    String s_CORP_C = paramMap.get("CORP_C").toString();    	    
	    String s_BZPL_C = paramMap.get("BZPL_C").toString();
	    String s_PRC_DT = paramMap.get("PRC_DT").toString();
	    
	    //채번 서비스 호출(발주일련번호)
	    String seqNo = commonService.getGvno(s_CORP_C, "TB_GE_M_CVAP_AFF", s_BZPL_C, s_PRC_DT, 1);
	    egovLogger.debug("민원사후일련번호 채번: " + seqNo);
	    paramMap.put("PRC_SQNO", seqNo);
	    
	    egovLogger.debug("************ 민원사후관리 등록 TB_GE_M_CVAP_AFF ************");    
	    sqlSession.insert("sfmes.sqlmap.tb.insert_TB_GE_M_CVAP_AFF", paramMap);
	    sqlSession.insert("sfmes.sqlmap.tb.update_TB_GE_M_CVAP_RC_AFF", paramMap);

	    egovLogger.debug("************ 민원상세내역 등록 TB_GE_D_CVAP_AFF ************");
	    for(Map<String, Object> map : paramList) {
	        map.put("BZPL_C", paramMap.get("BZPL_C"));
	        map.put("PRC_DT", paramMap.get("PRC_DT"));
	        map.put("PRC_SQNO", paramMap.get("PRC_SQNO"));        
	        if(map.get("_status_").equals("+")) {            
	            egovLogger.debug("map: " + map.toString());
	            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_GE_D_CVAP_AFF", map);
	        }
	    }
	    
	    result = paramMap.toString();
	    return result;
	}
	
	//민원사후관리 기본/상세 수정
	@Override
	public String updateGe2030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
	    egovLogger.debug("************ 민원사후관리 수정 [updateGe2030] ************");
	    egovLogger.debug("paramMap: " + paramMap.toString());
	    egovLogger.debug("paramLIst: " + paramList.toString());
	    
	    String result = "";
        int chk_cnt = 0;
        
        //민원사후관리 등록된 내역 존재 확인
        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.ge.selectGe2030CntA", paramMap);        
        
        if(chk_cnt == 0) 
        {
            throw infoException("민원사후관리 내역이 존재하지 않습니다.");
        }

        //민원사후관리 수정        
	    egovLogger.debug("민원사후관리 수정 TB_GE_M_CVAP_AFF");
	    sqlSession.update("sfmes.sqlmap.tb.update_TB_GE_M_CVAP_AFF", paramMap);
	    sqlSession.insert("sfmes.sqlmap.tb.update_TB_GE_M_CVAP_RC_AFF", paramMap);
        	    
        if("N".equals(paramMap.get("DEL_YN")))
        {
            //민원사후관리 상세내역 수정
            egovLogger.debug("민원사후관리 상세내역수정 TB_GE_D_CVAP_AFF");
            for(Map<String, Object> map : paramList) 
            {
                map.put("BZPL_C",  paramMap.get("BZPL_C"));
                map.put("PRC_DT",   paramMap.get("PRC_DT"));
                map.put("PRC_SQNO", paramMap.get("PRC_SQNO"));   
                
                if(map.get("_status_").equals("+")) 
                {
                    egovLogger.debug("입력 map: " + map.toString());
                    sqlSession.insert("sfmes.sqlmap.tb.insert_TB_GE_D_CVAP_AFF", map);
                }
                else 
                {
                    egovLogger.debug("수정 map: " + map.toString());
                    sqlSession.update("sfmes.sqlmap.tb.update_TB_GE_D_CVAP_AFF", map);               
                }
            }            
        } else {
            //민원상세내역 삭제
            egovLogger.debug("민원상세내역수정 TB_GE_D_CVAP_AFF");
            for(Map<String, Object> map : paramList) 
            {
                map.put("BZPL_C",  paramMap.get("BZPL_C"));
                map.put("PRC_DT",   paramMap.get("PRC_DT"));
                map.put("PRC_SQNO", paramMap.get("PRC_SQNO"));   
                
                egovLogger.debug("삭제 map: " + map.toString());
                sqlSession.update("sfmes.sqlmap.tb.update_TB_GE_D_CVAP_AFF", map);               
            }            
        }
        
        result = paramMap.toString();
        return result; 	        
	}
	
}
