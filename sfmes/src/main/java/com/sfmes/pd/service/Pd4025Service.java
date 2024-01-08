package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
* @Class Name : Pd4025Service.java
* @Description : Pd4025Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.22   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.16
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd4025Service {
    
	/**
	 * 수탁가공재료 인수내역 조회
	 */
    List<?> selectPd4025List(LinkedHashMap paramMap) throws Exception;
    
}
