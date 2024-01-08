package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : Co4020Service.java
 * @Description : Co4020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.23   김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Se4020Service {
    
    //배송차량등록[SE4020]
    void insert_Se4020(LinkedHashMap paramMap) throws Exception;
    
    //배송차량수정[SE4020]
    void update_Se4020(LinkedHashMap paramMap) throws Exception;

    //배송차량조회[SE4020]
    List<?> selct_Se4020(LinkedHashMap paramMap) throws Exception;
    
    //배송차량내역[SE4025]
    List<?> select_Se4025(LinkedHashMap paramMap) throws Exception;
}
