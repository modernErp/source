package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name   : Sm1020Service.java
 * @Description : Sm1020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.08.05  김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.08.05
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sm1020Service {
    
    //생산투입출고의뢰 신규등록
    String saveSm1020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    //생산투입출고의뢰 신규등록
    String updateSm1020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;

    //생산투입출고의뢰 폼 조회
    List<?> searchSm1020_01(LinkedHashMap paramMap) throws Exception;
    
    //생산투입출고의뢰 리스트 조회
    List<?> searchSm1020_02(LinkedHashMap paramMap) throws Exception;
    
    //생산투입출고의뢰내역팝업조회
    List<?> searchSm1020P01(LinkedHashMap paramMap) throws Exception;
    
    //생산투입출고의뢰내역팝업조회
    List<?> searchSm1020PDA(LinkedHashMap paramMap) throws Exception;
}
