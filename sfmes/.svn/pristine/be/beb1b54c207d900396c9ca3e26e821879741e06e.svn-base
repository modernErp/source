package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Pd2040Service.java
 * @Description : Pd2040Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2040.08.10   김종관    최초생성
 *
 * @author (주)모든솔루션
 * @since 2040.08.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Pd2040Service {

    /**
     *  제품상세 조회(작업지시기준)
     */    
    List<?> selectPd2040List_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     *  제품상세 조회(생산내역기준)
     */    
    List<?> selectPd2040List_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     *  투입자재상세내역 조회
     */
    List<?> selectPd2040List_03(LinkedHashMap paramMap) throws Exception;

    /**
     * 작업보고 등록
     */
    void insertPd2040(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    /**
     * 작업보고 등록 투입내역 바코드라벨 등록   2021.11.11 rchkorea
     */
    void insertPd2040_11(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    /**
     *  투입자재상세내역 조회 바코드라벨조회 
     */
    List<?> selectPd2040List_13(LinkedHashMap paramMap) throws Exception;
    
}
