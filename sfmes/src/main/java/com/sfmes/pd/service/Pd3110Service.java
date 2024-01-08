package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
* @Class Name : Pd3110Service.java
* @Description : Pd3110Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.12.15   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.12.15
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd3110Service {
    
	/*
	 * 기간별배부차액집계내역 총집계조회 
	 */
    List<?> selectPd3110_01(LinkedHashMap paramMap) throws Exception;
    
    /*
     * 기간별배부차액집계내역조회 
     */
    List<?> selectPd3110_02(LinkedHashMap paramMap) throws Exception;
}
