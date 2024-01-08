package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
* @Class Name : Pd4120Service.java
* @Description : Pd4120Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.11.20   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.11.20
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd4120Service {
    
	/**
     * 수탁가공 출고 내역 조회
     */  
    List<?> selectPd4120List_01(LinkedHashMap paramMap) throws Exception;
    
}
