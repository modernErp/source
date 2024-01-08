package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @Class Name : Pd2010Service.java
* @Description : Pd2010Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.08.04   김수민     최초생성
* @ 2021.01.13   이수빈     작업지시내역출력조회, 원재료입고(예정)내역 추가
*
* @author (주)모든솔루션
* @since 2020.08.04
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd2010Service {
    
    /**
     * 작업지시등록
     */
    void insertPd2010(LinkedHashMap paramMap, List<Map<String, Object>> paramList01, List<Map<String, Object>> paramList02) throws Exception;

    /**
     * 작업지시변경
     */
    void updatePd2010(LinkedHashMap paramMap, List<Map<String, Object>> paramList01, List<Map<String, Object>> paramList02) throws Exception;
    
    /**
     * 작업지시 기본정보조회
     */
    List<?> selectPd2010List_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 작업지시제품상세조회
     */
    List<?> selectPd2010List_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 작업지시투입상세조회
     */
    List<?> select_Pd2010List03(LinkedHashMap paramMap) throws Exception;
    
    /**
     *  제품상세(저장전)
     */
    List<?> selectPd2010List_04(LinkedHashMap paramMap) throws Exception;
    
    /**
     *  투입상세(저장전BOM상세조회(반제품투입내역조회에 사용))
     */
    List<?> selectPd2010List_05(LinkedHashMap paramMap) throws Exception;

    /**
     *  반제품조회(기등록된 조회용) 20211214 rchkorea
     */
    List<?> selectPd2010List_06(LinkedHashMap paramMap) throws Exception;

}
