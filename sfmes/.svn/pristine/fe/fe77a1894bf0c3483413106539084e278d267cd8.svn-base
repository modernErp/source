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
import com.sfmes.pd.service.Pd5030Service;

/**
* @Class Name : Pd5030ServiceImpl.java
* @Description : Pd5030Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.11.27   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.11.27
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd5030Service")
public class Pd5030ServiceImpl extends CmnAbstractServiceImpl implements Pd5030Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    // 위탁의뢰 물품 입고 내역 등록
    @Override
    public void insertPd5030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
    	egovLogger.debug(":::::[Pd5030INSERT]::::::" + paramMap);
    	// 폼에만 있는 정보를 리스트에 세팅 후 insert  
    	for(Map<String, Object> rowInfo : paramList) {
    		rowInfo.put("CORP_C", paramMap.get("CORP_C"));
    		rowInfo.put("BZPL_C", paramMap.get("BZPL_C"));
    		rowInfo.put("TRU_MFC_RC_DT", paramMap.get("TRU_MFC_RC_DT"));
    		rowInfo.put("TRU_MFC_RC_SQNO", paramMap.get("TRU_MFC_RC_SQNO"));
    		// 입고 등록 이기 때문에 입출고구분 코드(I : 입고, O : 입고)의 'I' 등록
    		rowInfo.put("STDV_DSC", "I");
    		sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_TRU_MFC_MFS_STDV", rowInfo);
		}
    	
    	// 위탁제품 입고 등록시 생산완료(입고) 상태로 코드값 변경
    	if(!paramMap.get("TRU_MFC_STS_DSC").toString().equals("05")) {
    		paramMap.put("TRU_MFC_STS_DSC", "04");
    	}
    	sqlSession.update("sfmes.sqlmap.pd.update_PD5030_TRU_MFC_STS_DSC", paramMap);
    }
    
    // 위탁의뢰접수 조회
    @Override
    public List<?> selectPd5030_01(LinkedHashMap paramMap) throws Exception {
    	// pd4010화면에서 사용된 물품 및 필요 정보가 있는 기존 쿼리 조회
    	return sqlSession.selectList("sfmes.sqlmap.pd.selectPd5010_01", paramMap);
    }
    
    // 위탁의뢰제품 입고내역 조회
    @Override
    public List<?> selectPd5030_02(LinkedHashMap paramMap) throws Exception {
    	return sqlSession.selectList("sfmes.sqlmap.pd.selectPd5030_02", paramMap);
    }
    
    // 위탁의뢰제품 입고내역 수정
    @Override
    public void updatePd5030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
    	egovLogger.debug(":::::[Pd5030UPDATE]::::::" + paramMap);
    	
    	//수정시 insert가 필요한 행 list 선언
    	List<Map<String, Object>> insertList = new ArrayList<Map<String, Object>>();
    	
    	// '+' 일 경우 행추가로 추가 된 내역으로 등록 리스트에 add()
    	// '-' 일 경우 행삭제 상태로 DEL_YN 값 'Y' 로 update
    	// 나머지는 행 자체 update
    	for(Map<String, Object> rowInfo : paramList) {
    		String rowStatus = rowInfo.get("_status_").toString();
    		if(rowStatus.equals("+")) {
    			insertList.add(rowInfo);
    		} else if(rowStatus.equals("-")) {
    			sqlSession.update("sfmes.sqlmap.pd.update_PD5030_DEL_YN", rowInfo);
    		} else {
    			sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_TRU_MFC_MFS_STDV", rowInfo);
    		}
    	}
    	
    	if(paramMap.get("TRU_MFC_STS_DSC").toString().equals("05")) {
    		sqlSession.update("sfmes.sqlmap.pd.update_PD5030_TRU_MFC_STS_DSC", paramMap);
    	}
    	
    	//status의 값이 '+' 로 insertList에 추가된 list의 size가 0보다 클 경우 insert메서드 호출
    	if(insertList.size() > 0) {
    		insertPd5030(paramMap, insertList);
    	}
    }

}
