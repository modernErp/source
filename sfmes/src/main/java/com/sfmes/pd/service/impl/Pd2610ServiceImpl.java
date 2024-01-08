package com.sfmes.pd.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd2510Service;
import com.sfmes.pd.service.Pd2610Service;

/**
* @Class Name : Pd2610ServiceImpl.java
* @Description : Pd2610Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2021.02.16   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2021.02.16
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2610Service")
public class Pd2610ServiceImpl extends CmnAbstractServiceImpl implements Pd2610Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Resource(name = "Pd2510Service")
    private Pd2510Service pd2510Service;

    
    @Override
    public void insertPd2610(LinkedHashMap<String, Object> paramMap, List<Map<String, Object>> paramList01) throws Exception {
    	// 생산물품 조회
    	List<Map<String, Object>> prwPd = sqlSession.selectList("sfmes.sqlmap.pd.selectPd2510_prw_pd",paramMap);
    	// 투입물품 조회
    	List<Map<String, Object>> prwPtin = sqlSession.selectList("sfmes.sqlmap.pd.selectPd2510_prw_ptin",paramMap);
    	
    	
    	for(Map<String, Object> prwInfo : paramList01) {
    		List<Map<String, Object>> prwPdData = new ArrayList<Map<String,Object>>();
    		List<Map<String, Object>> prwPtinData = new ArrayList<Map<String,Object>>();
    		// 작업지시 마스터 테이블 저장정보 세팅
    		// default데이터
    		prwInfo.put("MFC_DSC", "1");     
    		prwInfo.put("DNTT_METH_C", "4"); 
    		prwInfo.put("MFC_WK_STS_C", "01");
    		prwInfo.put("DANI_PD_C", "1");
    		prwInfo.put("BZPL_C", (String)paramMap.get("BZPL_C"));
    		prwInfo.put("PRW_C", (String)paramMap.get("PRW_C"));
    		
    		// (작업일지-생산일자) -> (작업지시-생산시작일자) 로 변경 
    		String pdPlaDt = (String)prwInfo.get("ACG_DT");
    		prwInfo.put("PD_PLA_DT", pdPlaDt);
    		
    		// (작업일지-지육번호) -> (작업지시-기타구분1(임시로 지정)) 로 변경 
    		String etcDsc1 = (String)prwInfo.get("CONDU_NO");
    		prwInfo.put("CONDU_NO", etcDsc1);
    		
    		// 생산물품
    		for(Map<String, Object> gdsInfo : prwPd) {
    			// 현재는 투입 지시량에 따른 생산량을 계산안하고 화면에서 넘겨준 값을 저장
    			String mfsDnttQt = (String)prwInfo.get("PTIN_DNTT_QT");
    			gdsInfo.put("MFS_DNTT_QT", mfsDnttQt);
    			prwPdData.add(gdsInfo);
    		}
    		
    		// 투입물품
    		for(Map<String, Object> gdsInfo : prwPtin) {
    			// 투입 지시량
    			String ptinDnttQt = (String)prwInfo.get("PTIN_DNTT_QT");
    			gdsInfo.put("PTIN_DNTT_QT", ptinDnttQt);
    			
    			// 이력번호
    			String gdsHstNo = (String)prwInfo.get("GDS_HST_NO");
    			gdsInfo.put("GDS_HST_NO", gdsHstNo);
    			prwPtinData.add(gdsInfo);
    		}
    		pd2510Service.insertPd2510((LinkedHashMap)prwInfo, prwPdData, prwPtinData);
    	}
        
    }

}
