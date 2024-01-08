package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Se4010Service.java
 * @Description : Se4010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.07.23   곽환용     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Se4010Service {
    
    /**
     * 거래처별배송지등록 조회
     */    
    List<?> selectSe4010(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 거래처별등록배송지내역
     */    
    List<?> selectSe4015(LinkedHashMap paramMap) throws Exception;    
    
    /**
     * 거래처별배송지 등록
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void saveSe4010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;    
}
