package com.sfmes.ca.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name : Ca6130Service.java
 * @Description : Ca6130Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.09.17  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.17
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Ca6130Service {

    // 선수금잔액장조회
    List<?> selectCa6130List(LinkedHashMap paramMap) throws Exception;
}