package com.sfmes.ca.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name : Ca6120Service.java
 * @Description : Ca6120Service Class
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
public interface Ca6120Service {

    // 선수금일원장조회
    List<?> selectCa6120List(LinkedHashMap paramMap) throws Exception;
}