package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
* @Class Name : Pd5025Service.java
* @Description : Pd5025Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.12.03   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.12.03
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd5025Service {
    
	/**
	 * 위탁가공재료 출고내역 조회
	 */
    List<?> selectPd5025List(LinkedHashMap paramMap) throws Exception;
    
}
