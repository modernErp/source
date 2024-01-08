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
import com.sfmes.pd.service.Pd5020Service;
import com.sfmes.pd.service.IF_PD_SM_HST_MNGService;

/**
* @Class Name : Pd5020ServiceImpl.java
* @Description : Pd5020Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.22   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.22
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd5020Service")
public class Pd5020ServiceImpl extends CmnAbstractServiceImpl implements Pd5020Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    @Autowired
    private IF_PD_SM_HST_MNGService IF_PD_SM_HST_MNGService;
    
    // 위탁가공 재료 출고 내역 등록
    @Override
    public void insertPd5020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        // 축산물이럭번호 I/F 를 위한 List 선언
        List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();

        egovLogger.debug(":::::[Pd5020INSERT]::::::" + paramMap);
		String sCorpC = paramMap.get("CORP_C").toString();					//회사코드
		String sBzplC = paramMap.get("BZPL_C").toString();					//사업장 코드
		String sTruMfcRcDt = paramMap.get("TRU_MFC_RC_DT").toString();		//위탁가공 접수 일자
		String sTruMfcRcSqno = paramMap.get("TRU_MFC_RC_SQNO").toString();	//위탁가공 접수 일련번호
		String sOmtrStdvDt = null;                                          //위탁가공 입출고 일자
		String sOmtrStdvSqno = null;										//위탁가공 입출고 일련번호
		String omtrStdvDtCompare = "";										//위탁가공 입출고 일자 비교변수
		
	    // 위탁가공 재료 출고 내역 등록을 위한 반복
		for(Map<String, Object> rowInfo : paramList) {
		    
			sOmtrStdvDt = rowInfo.get("OMTR_STDV_DT").toString();//위탁가공 입출고 일자
			
			// 위탁가공재료출고 내역 등록시 같은 입출고 일자 일 경우 같은 일련번호로 저장
			// 다르면 if문 진입후 일련번호 채번
			if(!sOmtrStdvDt.equals(omtrStdvDtCompare)) {
			    
	            // 축산물이력 I/F
			    if(!("").equals(sOmtrStdvDt)) {		            
		            paramMap.put("OLD_STDV_DT"  , sOmtrStdvDt);
		            paramMap.put("OLD_STDV_SQNO", sOmtrStdvSqno);
		            paramMap.put("MFC_DSC"      , "3");
		            
		            IF_PD_SM_HST_MNGService.if_HST_MNG_insert(paramMap, itemList);
		            
		            itemList.clear();
			    }
			    
				// 위탁가공재료출고 내역 일련번호 채번
				sOmtrStdvSqno = commonService.getGvno(sCorpC,"TB_PD_D_TRU_MFC_OMTR_STDV",sBzplC, sOmtrStdvDt, 1);
				
				// 다른 입출고 일자가 등록될 경우 비교를 위한 현재 입출고 일자 대입
				omtrStdvDtCompare = sOmtrStdvDt;
			}
			
			egovLogger.debug("=====일련번호 채번" + sOmtrStdvSqno);
			rowInfo.put("BZPL_C", sBzplC);
			rowInfo.put("TRU_MFC_RC_DT", sTruMfcRcDt);
			rowInfo.put("TRU_MFC_RC_SQNO", sTruMfcRcSqno);
			rowInfo.put("OMTR_STDV_SQNO", sOmtrStdvSqno);
			rowInfo.put("STDV_PCS", rowInfo.get("PTIN_UPR"));
			sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_TRU_MFC_OMTR_STDV", rowInfo);

			rowInfo.put("STDV_DT"  , sOmtrStdvDt);
            rowInfo.put("STDV_SQNO", sOmtrStdvSqno);
            rowInfo.put("MFC_DSC"  , "3"); 
			itemList.add(rowInfo);
		}
		// 위탁가공재료 출고내역 등록시 해당 위탁가공접수상태 재료출고(02)로 update
		sqlSession.update("sfmes.sqlmap.pd.updateTRU_MFC_STS_DSC", paramMap);

		// 축산물이력 I/F
        paramMap.put("OLD_STDV_DT"  , sOmtrStdvDt);
        paramMap.put("OLD_STDV_SQNO", sOmtrStdvSqno);
        paramMap.put("MFC_DSC"      , "3");
        
        IF_PD_SM_HST_MNGService.if_HST_MNG_insert(paramMap, itemList);                  
    }

    // 위탁가공 재료 출고 내역 수정
    @Override
    public void updatePd5020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
    	egovLogger.debug(":::::[Pd5020UPDATE]::::::" + paramMap);
    	
        // 축산물이럭번호 I/F 를 위한 List 선언
        List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();

        // 수정시 insert가 필요한 행 list 선언
    	List<Map<String, Object>> insertList = new ArrayList<Map<String, Object>>();
    	
    	for(Map<String, Object> rowInfo : paramList) {

    	    // 축산물이력번호 I/F 관련 추가
            paramMap.put("OLD_STDV_DT"  , rowInfo.get("OMTR_STDV_DT"));
            paramMap.put("OLD_STDV_SQNO", rowInfo.get("OMTR_STDV_SQNO"));
            paramMap.put("MFC_DSC"      , "3");

            // '+' 일 경우 행추가로 추가 된 내역으로 등록 리스트에 add()
    		String rowStatus = rowInfo.get("_status_").toString();
    		if(rowStatus.equals("+")) {
    			insertList.add(rowInfo);
    		} else if(rowStatus.equals("-")) {
    			sqlSession.update("sfmes.sqlmap.pd.update_PD5020_DEL_YN", rowInfo);
    			
    	        //물품이력테이블 축산물이력번호 삭제    	        
    	        IF_PD_SM_HST_MNGService.if_HST_MNG_delete(paramMap);
    			
    		} else {
    			rowInfo.put("STDV_PCS", rowInfo.get("PTIN_UPR"));
    			sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_TRU_MFC_OMTR_STDV", rowInfo);
    		
    	        //(물품이력테이블 축산물이력번호 변경)( 삭제 -> 등록)
    	        IF_PD_SM_HST_MNGService.if_HST_MNG_delete(paramMap);

    	        rowInfo.put("OLD_STDV_DT"  , rowInfo.get("OMTR_STDV_DT"));
    	        rowInfo.put("OLD_STDV_SQNO", rowInfo.get("OMTR_STDV_SQNO"));
    	        rowInfo.put("STDV_DT"      , rowInfo.get("OMTR_STDV_DT"));
                rowInfo.put("STDV_SQNO"    , rowInfo.get("OMTR_STDV_SQNO"));
                rowInfo.put("MFC_DSC"      , "3"); 
                itemList.add(rowInfo);
                
    	        IF_PD_SM_HST_MNGService.if_HST_MNG_insert(paramMap, paramList);
    	        itemList.clear();
    		}
    	}
    	
    	//status의 값이 '+' 로 insertList에 추가된 list의 size가 0보다 클 경우 insert메서드 호출
    	if(insertList.size() > 0) {
    		insertPd5020(paramMap, insertList);
    	}
    }
    
    // 위탁가공접수 조회
    @Override
    public List<?> selectPd5010_01(LinkedHashMap paramMap) throws Exception {
    	// 물품 및 필요 정보가 추가 된 쿼리가 기존에 존재하여 pd4010 쿼리 조회
    	return sqlSession.selectList("sfmes.sqlmap.pd.selectPd5010_01", paramMap);
    }

    // 위탁가공재료출고 단가 조회
    @Override
    public List<?> selectPd5020_01(LinkedHashMap paramMap) throws Exception {
    	return sqlSession.selectList("sfmes.sqlmap.pd.selectPd5020_01", paramMap);
    }
    
    // 위탁가공재료출고 내역 조회
    @Override
    public List<?> selectPd5020_02(LinkedHashMap paramMap) throws Exception {
    	return sqlSession.selectList("sfmes.sqlmap.pd.selectPd5020_02", paramMap);
    }
}
