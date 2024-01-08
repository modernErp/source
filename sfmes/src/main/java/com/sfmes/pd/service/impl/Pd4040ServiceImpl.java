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
import com.sfmes.pd.service.Pd4040Service;

/**
* @Class Name : Pd4040ServiceImpl.java
* @Description : Pd4040Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.11.02   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.11.02
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd4040Service")
public class Pd4040ServiceImpl extends CmnAbstractServiceImpl implements Pd4040Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    // 수탁가공 물품 출고 내역 등록
    @Override
    public void insertPd4040(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
    	egovLogger.debug(":::::[Pd4040INSERT]::::::" + paramMap);
    	
    	//출고총수량 UPDATE에 필요 변수 선언
    	String strStdvQt;
    	int dlrTotQt = Integer.parseInt(paramMap.get("DLR_TOT_QT").toString());
    	
    	// 폼에만 있는 정보를 리스트에 세팅 후 insert  
    	for(Map<String, Object> rowInfo : paramList) {
    		rowInfo.put("CORP_C", paramMap.get("CORP_C"));
    		rowInfo.put("BZPL_C", paramMap.get("BZPL_C"));
    		rowInfo.put("ETR_MFC_RC_DT", paramMap.get("ETR_MFC_RC_DT"));
    		rowInfo.put("ETR_MFC_RC_SQNO", paramMap.get("ETR_MFC_RC_SQNO"));
    		// 출고 등록 이기 때문에 입출고구분 코드(I : 입고, O : 출고)의 'O' 등록
    		rowInfo.put("STDV_DSC", "O");
    		sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_ETR_MFC_MFS_STDV", rowInfo);
    		
    		// 출고총수량 증가
    		strStdvQt = rowInfo.get("STDV_QT").toString();
    		int stdvQt = Integer.parseInt(strStdvQt);
    		dlrTotQt += stdvQt;
		}
    	
        // 출고 상세정보 등록 후 수탁가공접수정보 출고총수량 update
    	paramMap.put("DLR_TOT_QT", dlrTotQt);
    	sqlSession.update("sfmes.sqlmap.pd.update_PD4040_DLR_TOT_QT", paramMap);
    }
    
    // 수탁가공접수 조회
    @Override
    public List<?> selectPd4040_01(LinkedHashMap paramMap) throws Exception {
    	// pd4010화면에서 사용된 물품 및 필요 정보가 있는 기존 쿼리 조회
    	return sqlSession.selectList("sfmes.sqlmap.pd.selectPd4010_Mfc_Rc", paramMap);
    }
    
    // 수탁가공제품 출고내역 조회
    @Override
    public List<?> selectPd4040_02(LinkedHashMap paramMap) throws Exception {
    	return sqlSession.selectList("sfmes.sqlmap.pd.selectPd4040_02", paramMap);
    }
    
    // 수탁가공제품 출고내역 수정
    @Override
    public void updatePd4040(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
    	egovLogger.debug(":::::[Pd4040UPDATE]::::::" + paramMap);
    	
    	//수정시 insert가 필요한 행 list 선언
    	List<Map<String, Object>> insertList = new ArrayList<Map<String, Object>>();
    	
    	//출고총수량 UPDATE에 필요 변수 선언
    	String strStdvQt;
    	int dlrTotQt = Integer.parseInt(paramMap.get("DLR_TOT_QT").toString());
    	
    	// '+' 일 경우 행추가로 추가 된 내역으로 등록 리스트에 add()
    	// '-' 일 경우 행삭제 상태로 DEL_YN 값 'Y' 로 update
    	// 나머지는 행 자체 update
    	for(Map<String, Object> rowInfo : paramList) {
    		
    		// 출고총수량 증감
    		strStdvQt = rowInfo.get("STDV_QT").toString();
    		int stdvQt = Integer.parseInt(strStdvQt);
    		
    		String rowStatus = rowInfo.get("_status_").toString();
    		if(rowStatus.equals("+")) {
    			insertList.add(rowInfo);
    			dlrTotQt += stdvQt;
    		} else if(rowStatus.equals("-")) {
    			sqlSession.update("sfmes.sqlmap.pd.update_PD4040_DEL_YN", rowInfo);
    			dlrTotQt -= stdvQt;
    		} else {
    			// 수정필요
        		rowInfo.put("ETR_MFC_MTRCS", "0"); // 재료원가 컬럼 확인
    			sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_ETR_MFC_MFS_STDV", rowInfo);
    			dlrTotQt += stdvQt;
    		}
    	}
    	
    	// 의뢰마감 했을경우 수탁가공상태 UPDATE
    	if(paramMap.get("ETR_MFC_STS_DSC").toString().equals("05")) {
    		sqlSession.update("sfmes.sqlmap.pd.update_PD4040_ETR_MFC_STS_DSC", paramMap);
    	}
    	
    	//status의 값이 '+' 로 insertList에 추가된 list의 size가 0보다 클 경우 insert메서드 호출
    	if(insertList.size() > 0) {
    		insertPd4040(paramMap, insertList);
    	}
    	
        // 출고 상세정보 등록 후 수탁가공접수정보 출고총수량 update
    	paramMap.put("DLR_TOT_QT", dlrTotQt);
    	sqlSession.update("sfmes.sqlmap.pd.update_PD4040_DLR_TOT_QT", paramMap);
    }
}
