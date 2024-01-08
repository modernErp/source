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
import com.sfmes.ge.service.Ge2010Service;

/**
 * @Class Name : Ge2010ServiceImpl.java
 * @Description : 민원 등록/수정 및 조회
 * @Modification Information
 * @
 * @  수정일     수정자      수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.11.03  유승현      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.11.03
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Ge2010Service")
public class Ge2010ServiceImpl extends CmnAbstractServiceImpl implements Ge2010Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 공통 서비스 선언
	@Resource(name = "CommonService")
	private CommonService commonService;
	
    //민원기본내역 조회
    @Override
    public List<?> selectGe2010_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************* 민원기본내역 조회 [GE2010] ****************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe2010_01", paramMap);
    }
    
    //민원상세내역 조회
    @Override
    public List<?> selectGe2010_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************* 민원상세내역 조회 [GE2010] ****************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe2010_02", paramMap);
    }
    
    //민원접수내역 팝업 조회
    @Override
    public List<?> selectGe2010_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************* 민원내역 팝업 조회 [GE2010] ****************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe2010_03", paramMap);
    }    
    
    //민원접수 신규 등록
	@Override
	public String insertGe2010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
	    egovLogger.debug("************ 민원접수 등록 Insert [GE2010] ************");
	    egovLogger.debug("paramMap: "  + paramMap.toString());
	    egovLogger.debug("paramList: " + paramList.toString());
	    
	    String result = "";
	    
	    String s_CORP_C = paramMap.get("CORP_C").toString();
	    String s_BZPL_C = paramMap.get("BZPL_C").toString();
	    String s_RC_DT  = paramMap.get("RC_DT").toString();
	    
	    //채번 서비스 호출(발주일련번호)
	    String seqNo = commonService.getGvno(s_CORP_C, "TB_GE_M_CVAP_RC", s_BZPL_C, s_RC_DT, 1);
	    egovLogger.debug("민원일련번호 채번: " + seqNo);
	    paramMap.put("RC_SQNO", seqNo);
	    
	    egovLogger.debug("민원기본내역 등록 TB_GE_M_CVAP_RC");
	    sqlSession.insert("sfmes.sqlmap.tb.insert_TB_GE_M_CVAP_RC", paramMap);
	    
	    egovLogger.debug("민원상세내역 등록 TB_GE_D_CVAP_RC");
	    for(Map<String, Object> map : paramList) {
	        map.put("BZPL_C", paramMap.get("BZPL_C"));
	        map.put("RC_DT", paramMap.get("RC_DT"));
	        map.put("RC_SQNO", paramMap.get("RC_SQNO"));
	        if(map.get("_status_").equals("+")) {
	            egovLogger.debug("map: " + map.toString());
	            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_GE_D_CVAP_RC", map);
	        }
	    }
	    
	    result = paramMap.toString();
	    return result;
	}
	
	//민원접수 기본/상세 수정
	@Override
	public String updateGe2010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
	    egovLogger.debug("************ 민원접수 수정 Update [GE2010] ************");
	    egovLogger.debug("paramMap: " + paramMap.toString());
	    egovLogger.debug("paramLIst: " + paramList.toString());
	    
	    String result = "";
        int chk_cnt = 0;
        
        //민원접수 등록된 내역 존재 확인
        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.ge.selectGe2010Cnt", paramMap);        
        
        if(chk_cnt == 0) 
        {
            throw infoException("민원접수내역이 존재하지 않습니다.");
        }

        //민원기본내역 수정        
	    egovLogger.debug("민원기본내역 수정 TB_GE_M_CVAP_RC");
	    sqlSession.update("sfmes.sqlmap.tb.update_TB_GE_M_CVAP_RC", paramMap);
        	    
        if("N".equals(paramMap.get("DEL_YN")))
        {
            //민원상세내역 수정
            egovLogger.debug("민원상세내역수정 TB_GE_D_CVAP_RC");
            for(Map<String, Object> map : paramList) 
            {
                map.put("BZPL_C",  paramMap.get("BZPL_C"));
                map.put("RC_DT",   paramMap.get("RC_DT"));
                map.put("RC_SQNO", paramMap.get("RC_SQNO"));   
                
                if(map.get("_status_").equals("+")) 
                {
                    egovLogger.debug("입력 map: " + map.toString());
                    sqlSession.insert("sfmes.sqlmap.tb.insert_TB_GE_D_CVAP_RC", map);
                }
                else 
                {
                    egovLogger.debug("수정 map: " + map.toString());
                    sqlSession.update("sfmes.sqlmap.tb.update_TB_GE_D_CVAP_RC", map);               
                }
            }            
        } else {
            //민원상세내역 삭제
            egovLogger.debug("민원상세내역수정 TB_GE_D_CVAP_RC");
            for(Map<String, Object> map : paramList) 
            {
                map.put("BZPL_C",  paramMap.get("BZPL_C"));
                map.put("RC_DT",   paramMap.get("RC_DT"));
                map.put("RC_SQNO", paramMap.get("RC_SQNO"));   
                
                egovLogger.debug("삭제 map: " + map.toString());
                sqlSession.update("sfmes.sqlmap.tb.update_TB_GE_D_CVAP_RC", map);               
            }            
        }
        
        result = paramMap.toString();
        return result; 	        
	}
	
}
