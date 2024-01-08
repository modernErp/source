package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @Class Name : Pd1010Service.java
* @Description : Pd1010Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.07.20   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.07.20
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd1010Service {
    
    /**
     * BOM등록
     */
    void insertPd1010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;

    /**
     * BOM변경
     */
    void updatePd1010_01(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    /**
     * BOM사용여부변경
     */
    void updatePd1010_02(LinkedHashMap paramMap) throws Exception;
    
    
    /**
     * BOM조회
     */
    List<?> selectPd1010List_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * BOM상세내역조회
     */
    List<?> selectPd1010List_02(LinkedHashMap paramMap) throws Exception;



}
