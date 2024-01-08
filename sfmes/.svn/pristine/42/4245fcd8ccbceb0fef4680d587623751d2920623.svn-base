package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Se1030Service.java
 * @Description : Se1030Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.09.03   곽환용     최초등록
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Se1030Service {
        
    /**
     * 가격군내역
     */    
    List<?> selectSe1030_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 가격군별거래처내역
     */    
    List<?> selectSe1030_02(LinkedHashMap paramMap) throws Exception;         
    
    /**
     * 가격군별거래처등록
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void insertSe1030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;    
    
    /**
     * 매출단가부가세포함여부조회
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    List<?> Call_selecteSe1030_sl_upr_vat(LinkedHashMap paramMap) throws Exception;    
}
