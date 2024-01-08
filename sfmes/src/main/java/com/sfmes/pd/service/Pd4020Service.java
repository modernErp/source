package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @Class Name : Pd4020Service.java
* @Description : Pd4020Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.22   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.16
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd4020Service {
    
	/**
     * 수탁가공재료 인수내역 등록
     */
    void insertPd4020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;

    /**
     * 수탁가공재료 인수내역 수정
     */
    void updatePd4020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    /**
     * 수탁가공접수 조회
     */
    List<?> selectPd4020_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 수탁가공재료 인수내역 조회
     */
    List<?> selectPd4020_02(LinkedHashMap paramMap) throws Exception;
    
}
