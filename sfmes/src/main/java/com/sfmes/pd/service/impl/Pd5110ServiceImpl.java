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
import com.sfmes.pd.service.Pd5110Service;

/**
* @Class Name : Pd5110ServiceImpl.java
* @Description : Pd5110Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.11.24   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.11.24
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd5110Service")
public class Pd5110ServiceImpl extends CmnAbstractServiceImpl implements Pd5110Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    // 위탁가공 수불 내역 조회
    @Override
    public List<?> selectPd5110List_01(LinkedHashMap paramMap) throws Exception {
    	String selectCheck = paramMap.get("DSC_CHK").toString();                    // 수불내역 조회 조건확인
    	List<Map<String, Object>> itemList1 = new ArrayList<Map<String, Object>>(); // 원부재료인수내역 조회결과
    	List<Map<String, Object>> itemList2 = new ArrayList<Map<String, Object>>(); // 제품출고내역 조회결과
    	List<Map<String, Object>> allItem = new ArrayList<Map<String, Object>>();   // 원부재료인수내역 + 제품출고내역 조회결과
    	
    	// 원부재료인수내역 조회
    	itemList1 = sqlSession.selectList("sfmes.sqlmap.pd.selectPd5110_TB_PD_D_TRU_MFC_OMTR_STDV",paramMap);
    	// 제품출고내역 조회
    	itemList2 = sqlSession.selectList("sfmes.sqlmap.pd.selectPd5110_TB_PD_D_TRU_MFC_MFS_STDV",paramMap);
    	
    	if(selectCheck.equals("1")) {
    		return itemList1;
    	} else if(selectCheck.equals("2")) {
    		return itemList2;
    	} else {
    		allItem.addAll(itemList1);
    		allItem.addAll(itemList2);
    		return allItem;
    	}
    }

}
