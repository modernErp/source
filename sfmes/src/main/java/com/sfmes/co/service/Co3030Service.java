package com.sfmes.co.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Co3030Service.java
 * @Description : Co3030Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.11.09   이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.11.09
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Co3030Service {
    
    // SMS수신대상자내역조회
    List<?> selectCo3030List(LinkedHashMap paramMap) throws Exception;
    
    List<?> selectCo3030_01List(LinkedHashMap paramMap) throws Exception;
    
    void insertCo3030List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    void deleteCo3030List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;

    void updateCo3030List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
}