package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Pd3040Service.java
 * @Description : Pd3040Service Class
 * @Modification Information
 * @
 * @  수정일           수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2022.02.10  유춘호            원가계산
 *
 * @author (주)모든솔루션
 * @since2022.02.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Pd3040Service {
    
    /**
     * 원가계산기본조회
     */ 
    List<?> selectPd3040_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 원가계산상세조회
     */ 
    List<?> selectPd3040_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 원가계산팝업조회
     */ 
    List<?> selectPd3040_04(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 원가계산
     */    
    void insertPd3040(LinkedHashMap paramMap) throws Exception;

    /**
     * 원가계산확정
     */    
    void updatePd3040_01(LinkedHashMap paramMap) throws Exception;    

    /**
     * 원가계산취소
     */    
    void updatePd3040_02(LinkedHashMap paramMap) throws Exception;    

}
