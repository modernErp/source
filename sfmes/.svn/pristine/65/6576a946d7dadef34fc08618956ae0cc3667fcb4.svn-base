package com.sfmes.co.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Co2020Service.java
 * @Description : Co2020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.16  김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Co2020Service {
    
    //공휴일정보를 조회한다.   
    List<?> selectCo2020List(LinkedHashMap paramMap);
    
    //기준연도를 조회한다.   
    List<?> selectCo2020_Basyy(LinkedHashMap paramMap);
    
    //영업주차기준연도를 조회한다.   
    List<?> selectCo2020_weekBasyy(LinkedHashMap paramMap);
 
    //공휴일정보를 수정한다.
    void updateCo2020(List<Map<String, Object>> paramList) throws Exception;
}
