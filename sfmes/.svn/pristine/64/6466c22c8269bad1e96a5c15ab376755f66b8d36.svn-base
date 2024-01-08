package com.sfmes.co.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @Class Name : Co2030Service.java
* @Description : Co2030Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.08.13   장경석     최초생성
*   2020.09.01   여다혜     서비스 수정
*
* @author (주)모든솔루션
* @since 2020.08.13
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Co2030Service {
    
    /**
     * 묶음번호등록
     */
    void insertCo2030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;

    /**
     * 묶음번호 대상내역 조회 (20.09.01 여다혜 수정)
     */
    List<?> selectCo2030List_01(LinkedHashMap paramMap01) throws Exception;
    
    /**
     * 묶음번호조회(기본)
     */
    List<?> selectCo2030_BUDL_NO_List(LinkedHashMap paramMap01) throws Exception;
    
    /**
     * 묶음번호조회(상세)
     */
    List<?> selectCo2030_BUDL_NO_Detail_List(LinkedHashMap paramMap01) throws Exception;

    /**
     * 일반코드조회(콤보자료)
     */
    List<?> selectCo2030_STD_PAT_C() throws Exception;

    
    /**
     * 일반코드조회(콤보자료)
     */
    List<?> selectCo2030List_04(LinkedHashMap paramMap01) throws Exception;

    /**
     * 축산물이력번호관리기본 validation Check
     */
    String co2030validCheck_01(LinkedHashMap paramMap01) throws Exception;

    /**
     * 묶음번호 출고전 validation Check
     */
    String co2030validCheck_02(LinkedHashMap paramMap01) throws Exception;

    /**
     * 축산물이력번호관리기본 출고항목 수정
     */
    String updateCo2030_01(LinkedHashMap paramMap01) throws Exception;

    /**
     * 묶음번호 출고중량 수정
     */
    String updateCo2030_02(LinkedHashMap paramMap01) throws Exception;
    
    /**
     * 묶음번호등록(인터페이스 사용)
     */
    void if_Co2030_insert(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
}
