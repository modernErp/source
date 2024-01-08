package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
* @Class Name : Pd4110Service.java
* @Description : Pd4110Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.19   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.11.17
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd4110Service {
    
	/**
     * 수탁가공 출고 내역 조회
     */  
    List<?> selectPd4110List_01(LinkedHashMap paramMap) throws Exception;
    
}
