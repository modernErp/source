package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name  : Se6025Service.java
 * @Description : Se6025Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.09.18   곽환용     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) Se 모든솔루션 All right reserved.
 */
public interface Se6025Service {
    
    /**
     * 전표별출고기본내역
     */    
    List<?> selectSe6025_01(LinkedHashMap paramMap) throws Exception;

    /**
     * 전표별출고상세내역
     */    
    List<?> selectSe6025_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 전표-물품별출고내역
     */    
    List<?> selectSe6025_03(LinkedHashMap paramMap) throws Exception; 
}
