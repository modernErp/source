package com.sfmes.ca.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : Ca4010Service.java
 * @Description : Ca4010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.09.18  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.18
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Ca4010Service {
    
    // 외상매입금지급내역 조회
    List<?> selectCa4010List(LinkedHashMap paramMap) throws Exception;
    
    // 외상매입금지급등록
    void insertCa4010List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
}
