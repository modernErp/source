package com.sfmes.dl.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Dl1030Service.java
 * @Description : Dl1030Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.12.07   이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.12.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Dl1030Service {
    
    // 회계전표수기등록조회
    List<?> selectDl1030List(LinkedHashMap paramMap) throws Exception;
    
    // 회계전표발생내역상세조회_01
    List<?> selectDl1030List_01(LinkedHashMap paramMap) throws Exception;
    
    // 회계전표수기등록
    void insertDl1030List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    // 회계전표수기등록수정
    void updateDl1030List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;

}