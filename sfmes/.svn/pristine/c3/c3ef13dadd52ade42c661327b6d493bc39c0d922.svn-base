package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sm1040Service.java
 * @Description : Sm1040Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.08.06   곽환용     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Sm1040Service {
    
    /**
     * 수주마감기본내역 조회
     */    
    List<?> selectSm1040_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 수주마감상세내역 조회
     */    
    List<?> selectSm1040_02(LinkedHashMap paramMap) throws Exception;  
    
    /**
     * 출고지시기본내역(팝업)
     */    
    List<?> selectSm1040_03(LinkedHashMap paramMap) throws Exception;    
    
    /**
     * 출고지시상세내역(팝업)
     */    
    List<?> selectSm1040_04(LinkedHashMap paramMap) throws Exception;     
    
    /**
     * 출고지시등록
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void insertSm1040(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;    
    
    /**
     * 출고상태구분 수정하는 함수
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void Call_updateSm1040(LinkedHashMap paramMap) throws Exception;      

}
