package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
* @Class Name : Pd2120Service.java
* @Description : Pd2120Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.09.23   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.09.23
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd2120Service {
    
    /**
     * 작업지시대비생산내역(건별)조회
     */
    List<?> selectPd2120List_01(LinkedHashMap paramMap) throws Exception;

}
