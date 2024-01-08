package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Se3010Service.java
 * @Description : Se3010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.07.14   곽환용     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) Se 모든솔루션 All right reserved.
 */
public interface Se3010Service {
    
    /**
     * 매출기본내역 조회
     */    
    List<?> selectSe3010_01(LinkedHashMap paramMap) throws Exception;

    /**
     * 매출상세내역 조회
     */    
    List<?> selectSe3010_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 매출내역찾기팝업 조회
     */    
    List<?> selectSe3010_03(LinkedHashMap paramMap) throws Exception; 
    
    /**
     * 참조출고상세내역 조회
     */    
    List<?> selectSe3010_04(LinkedHashMap paramMap) throws Exception; 
    
    /**
     * 매출정산내역 조회
     */    
    List<?> selectSe3010_05(LinkedHashMap paramMap) throws Exception;     
    
    /**
     * 매출단가부가세포함여부 조회
     */    
    List<?> selectSe3010_06(LinkedHashMap paramMap) throws Exception;      
    
    /**
     * 매출내역 등록
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    String insertSe3010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception; 
    
    /**
     * 매출내역 삭제
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void deleteSe3010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;

}