package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
* @Class Name : Pd5035Service.java
* @Description : Pd5035Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.12.02   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.12.02
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd5035Service {
    
	/**
     * 위탁가공제품 입고 내역 조회
     */  
    List<?> selectPd5035List_01(LinkedHashMap paramMap) throws Exception;
    
}
