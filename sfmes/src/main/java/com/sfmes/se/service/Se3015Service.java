package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name  : Se3015Service.java
 * @Description : Se3015Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.09.11   곽환용     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) Se 모든솔루션 All right reserved.
 */
public interface Se3015Service {
    
    /**
     * 전표별매출기본내역
     */    
    List<?> selectSe3015_01(LinkedHashMap paramMap) throws Exception;

    /**
     * 전표별매출상세내역
     */    
    List<?> selectSe3015_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 전표-물품별매출내역
     */    
    List<?> selectSe3015_03(LinkedHashMap paramMap) throws Exception; 
}
