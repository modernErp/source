package com.sfmes.ca.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : Ca3010Service.java
 * @Description : Ca3010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.07  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Ca3010Service {

    // 선급금 지급/사용 등록 기준정보 조회
    List<?> selectCa3010List(LinkedHashMap paramMap) throws Exception;
    
    // 선급금지급내역등록
    void insertCa3010One(LinkedHashMap paramMap) throws Exception;
}
