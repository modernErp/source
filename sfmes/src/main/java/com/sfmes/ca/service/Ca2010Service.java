package com.sfmes.ca.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : Ca2010Service.java
 * @Description : Ca2010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.09.28  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Ca2010Service {

    // 기타미수금회수 회수대상 조회
    List<?> selectCa2010List(LinkedHashMap paramMap) throws Exception;
    
    // 기타미수금회수등록
    void insertCa2010List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
}
