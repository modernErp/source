package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @Class Name : Pd2610Service.java
* @Description : Pd2610Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2021.02.16   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2021.02.16
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd2610Service {
    
    /**
     * 공정기록서 기반 작업지시등록
     */
    void insertPd2610(LinkedHashMap<String, Object> paramMap,List<Map<String, Object>> paramList01) throws Exception;
}
