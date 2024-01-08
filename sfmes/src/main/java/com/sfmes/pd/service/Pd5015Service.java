package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
* @Class Name : Pd5015Service.java
* @Description : Pd5015Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.11.27   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.11.27
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd5015Service {
    
	/*
	 * 위탁가공의뢰 내역 조회 
	 */
    List<?> selectPd5015List_01(LinkedHashMap paramMap) throws Exception;
    
}
