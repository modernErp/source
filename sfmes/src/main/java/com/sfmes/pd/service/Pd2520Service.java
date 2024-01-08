package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @Class Name : Pd2520Service.java
* @Description : Pd2520Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.27   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.27
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd2520Service {
    
    /**
     *  작업지시일괄등록
     */
    void insertPd2520(LinkedHashMap paramMap, List<Map<String, Object>> paramList01, 
            List<Map<String, Object>> paramList02, List<Map<String, Object>> paramList03) throws Exception;
    
    /**
     *  공정기록서 투입품상세(저장전)
     */
    List<?> selectPd2520List_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     *  공정기록서 생산품상세(저장전)
     */
    List<?> selectPd2520List_02(LinkedHashMap paramMap) throws Exception;

}
