package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
* @Class Name : Pd2035Service.java
* @Description : Pd2035Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.08.24   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.08.24
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd2035Service {
    
    /**
     * 작업지시내역조회
     */
    List<?> selectPd2035List_01(LinkedHashMap paramMap) throws Exception;
    

}
