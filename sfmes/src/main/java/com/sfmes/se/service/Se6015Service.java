package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name  : Se6015Service.java
 * @Description : Se6015Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.08.10    박지환   최초생성 
 *
 * @author (주)모든솔루션
 * @since 2020.09.14
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Se6015Service {

    /**
     * 수주마감기본내역 조회
     */    
    List<?> selectSe6015_01(LinkedHashMap paramMap) throws Exception;

    /**
     * 수주마감상세내역 조회
     */
    List<?> selectSe6015_02(LinkedHashMap paramMap) throws Exception;
    
}
