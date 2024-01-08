package com.sfmes.ca.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name : Ca3130Service.java
 * @Description : Ca3130Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.09.16  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Ca3130Service {

    // 선급금잔액장조회
    List<?> selectCa3130List(LinkedHashMap paramMap) throws Exception;
}