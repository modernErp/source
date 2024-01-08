package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Se3020Service.java
 * @Description : Se3020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.10.12   곽환용     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) Se 모든솔루션 All right reserved.
 */
public interface Se3020Service {
    
    /**
     * 매출반입기본내역 조회
     */    
    List<?> selectSe3020_01(LinkedHashMap paramMap) throws Exception;

    /**
     * 매출반입상세내역 조회
     */    
    List<?> selectSe3020_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 매출정산내역 조회
     */    
    List<?> selectSe3020_03(LinkedHashMap paramMap) throws Exception;     
    
    /**
     * 매출반입내역 등록
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    String insertSe3020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception; 
    
    /**
     * 매출반입내역 삭제
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void deleteSe3020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;

}