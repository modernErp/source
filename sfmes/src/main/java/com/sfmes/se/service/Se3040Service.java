package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Se3040Service.java
 * @Description : Se3040Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.10.29   곽환용     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) Se 모든솔루션 All right reserved.
 */
public interface Se3040Service {
    
    /**
     * 매출(덤)기본내역 조회
     */    
    List<?> selectSe3040_01(LinkedHashMap paramMap) throws Exception;

    /**
     * 매출(덤)상세내역 조회
     */    
    List<?> selectSe3040_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 매출(덤)내역찾기팝업 조회
     */    
    List<?> selectSe3040_03(LinkedHashMap paramMap) throws Exception; 
    
    /**
     * 매출단가부가세포함여부 조회
     */    
    List<?> selectSe3040_04(LinkedHashMap paramMap) throws Exception;     
    
    /**
     * 매출(덤)내역 등록
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    String insertSe3040(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception; 
    
    /**
     * 매출(덤)내역 수정
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    String updateSe3040(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;

}