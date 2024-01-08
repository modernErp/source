package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Pd3030Service.java
 * @Description : Pd3030Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.12.07   김수민    최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.12.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Pd3030Service {
    
    /**
     * 노무비/제조경비 조회
     */ 
    List<?> selectPd3030_01(LinkedHashMap paramMap) throws Exception;
    
    
    /**
     * 노무비/제조경비 등록
     */    
    void insertPd3030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    

    /**
     * 노무비/제조경비 수정
     */    
    void updatePd3030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;    

}
