package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @Class Name : Pd5030Service.java
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

public interface Pd5030Service {
    
	/*
	 * 위탁의뢰 제품 입고등록
	 */
    void insertPd5030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    /*
	 * 위탁의뢰 제품 입고내용 수정
	 */
    void updatePd5030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    /*
	 * 위탁의뢰 접수조회
	 */
    List<?> selectPd5030_01(LinkedHashMap paramMap) throws Exception;
    
    /*
	 * 위탁의뢰 제품 입고내용 조회
	 */
    List<?> selectPd5030_02(LinkedHashMap paramMap) throws Exception;
    
}
