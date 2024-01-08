package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @Class Name : Pd2510Service.java
* @Description : Pd2510Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.13   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.13
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd2510Service {
    
    /**
     * 공정기록서 기반 작업지시등록
     */
    void insertPd2510(LinkedHashMap paramMap, List<Map<String, Object>> paramList01, List<Map<String, Object>> paramList02) throws Exception;

    /**
     * 공정기록서 기반 작업지시변경
     */
    void updatePd2510(LinkedHashMap paramMap, List<Map<String, Object>> paramList01, List<Map<String, Object>> paramList02) throws Exception;
    
    /**
     * 공정기록서 기반 작업지시 기본정보조회
     */
    List<?> selectPd2510List_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 공정기록서 기반 작업지시 제품상세조회
     */
    List<?> selectPd2510List_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 공정기록서 기반 작업지시 투입상세조회
     */
    List<?> selectPd2510List_03(LinkedHashMap paramMap) throws Exception;
    
    /**
     *  공정기록서 제품상세(저장전)
     */
    List<?> selectPd2510List_04(LinkedHashMap paramMap) throws Exception;
    
    /**
     *  공정기록서 투입상세(저장전)
     */
    List<?> selectPd2510List_05(LinkedHashMap paramMap) throws Exception;

}
