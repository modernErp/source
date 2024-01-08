package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Se2030Service.java
 * @Description : Se2030Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.07.20   곽환용     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Se2030Service {
    
    /**
     * 수주마감대상 조회
     */    
    List<?> selectSe2030_01(LinkedHashMap paramMap) throws Exception;

    /**
     * 수주마감내역
     */    
    List<?> selectSe2030_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 수주마감기본내역 조회
     */    
    List<?> selectSe2030_03(LinkedHashMap paramMap) throws Exception; 
    
    /**
     * 수주마감상세내역 조회
     */    
    List<?> selectSe2030_04(LinkedHashMap paramMap) throws Exception;     
    
    /**
     * 수주마감등록
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void updateSe2030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;    

    /**
     * 수주마감취소
     */
    void updateSe2030_02(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
}
