package com.sfmes.co.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Co1060Service.java
 * @Description : Co1060Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.06.29   곽환용     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Co1060Service {
    
    /**
     * 물품 대분류 조회
     */    
    List<?> selectGdsLCLC(LinkedHashMap paramMap) throws Exception;

    /**
     * 물품 중분류 조회
     */    
    List<?> selectGdsMCLC(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 물품 소분류 조회
     */    
    List<?> selectGdsSCLC(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 물품분류 등록
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void insertCo1060Gds(List<Map<String, Object>> paramList1, List<Map<String, Object>> paramList2, List<Map<String, Object>> paramList3) throws Exception;

}
