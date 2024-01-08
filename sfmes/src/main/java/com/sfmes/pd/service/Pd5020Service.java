package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @Class Name : Pd5020Service.java
* @Description : Pd5020Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.12.03   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.12.03
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd5020Service {
    
	/**
     * 수탁가공재료 인수내역 등록
     */
    void insertPd5020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;

    /**
     * 수탁가공재료 인수내역 수정
     */
    void updatePd5020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    /**
     * 수탁가공접수 조회
     */
    List<?> selectPd5010_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 수탁가공재료 단가 조회
     */
    List<?> selectPd5020_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 수탁가공재료 인수내역 조회
     */
    List<?> selectPd5020_02(LinkedHashMap paramMap) throws Exception;
    
}
