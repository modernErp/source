package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @Class Name : Pd4040Service.java
* @Description : Pd4040Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.11.02   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.11.02
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd4040Service {
    
    void insertPd4040(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;

    void updatePd4040(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    List<?> selectPd4040_01(LinkedHashMap paramMap) throws Exception;
    
    List<?> selectPd4040_02(LinkedHashMap paramMap) throws Exception;
    
}
