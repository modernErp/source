package com.sfmes.by.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : By2110Service.java
 * @Description : By2110Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.09.10   김지혜     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface By2110Service {
    
    /**
     * 발주현황(거래처)거래처별내역
     */    
    List<?> selectBy2110_trpl_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 발주현황(거래처)전표별내역
     */    
    List<?> selectBy2110_trpl_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 발주현황(거래처)물품별내역
     */    
    List<?> selectBy2110_trpl_03(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 발주현황(물품) 물품별내역
     */    
    List<?> selectBy2110_gds_01(LinkedHashMap paramMap) throws Exception;

    /**
     * 발주현황(물품) 거래처별내역
     */    
    List<?> selectBy2110_gds_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 발주현황(물품) 전표별내역
     */    
    List<?> selectBy2110_gds_03(LinkedHashMap paramMap) throws Exception;
    
}
