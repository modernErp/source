package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Se6010Service.java
 * @Description : Se6010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용   
 * @ ----------  --------   -------------------------------
 * @ 2020.09.11   김지혜     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Se6010Service {
    
    /**
     * 수주마감기본내역 조회
     */    
    List<?> selectSe6010_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 수주마감상세내역 조회
     */    
    List<?> selectSe6010_02(LinkedHashMap paramMap) throws Exception;  
    
    /**
     * 출고지시기본내역(팝업)
     */    
    List<?> selectSe6010_03(LinkedHashMap paramMap) throws Exception;    
    
    /**
     * 출고지시상세내역(팝업)
     */    
    List<?> selectSe6010_04(LinkedHashMap paramMap) throws Exception;     
    
    /**
     * 출고지시등록
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void insertSe6010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;    
    
    /**
     * 출고상태구분 수정하는 함수
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void Call_updateSe6010(LinkedHashMap paramMap) throws Exception;   
    
    /**
     * 출고지시기본내역(PDA) - 박지환 추가 (2021.03.31)
     */    
    List<?> selectPdaSe6010_01(LinkedHashMap paramMap) throws Exception;
}
