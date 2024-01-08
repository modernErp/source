package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
* @Class Name : Pd2015Service.java
* @Description : Pd2015Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.08.11   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.08.11
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd2015Service {
    
    /**
     * 작업지시내역조회
     */
    List<?> selectPd2015List_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 작업보고 생산기준 내역조회
     */
    List<?> selectPd2015List_02(LinkedHashMap paramMap) throws Exception;

    
}
