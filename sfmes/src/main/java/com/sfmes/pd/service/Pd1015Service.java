package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
* @Class Name : Pd1015Service.java
* @Description : Pd1015Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.07.23   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.07.23
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd1015Service {
    
    /**
     * BOM조회
     */
    List<?> selectPd1015List_01(LinkedHashMap paramMap) throws Exception;
    
    
    /**
     * 2022-01-19 ksckorea
     * BOM기본상세조회
     */
    List<?> selectPd1015List_02(LinkedHashMap paramMap) throws Exception;
    

}
