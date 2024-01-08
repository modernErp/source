package com.sfmes.ca.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name : Ca4020Service.java
 * @Description : Ca4020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.19  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Ca4020Service {

    // 외상매입금 원장계수정정등록 정보 조회
    List<?> selectCa4020List(LinkedHashMap paramMap) throws Exception;
    
    // 외상매입금 원장기초등록 정보 조회
    List<?> selectCa4020List_2(LinkedHashMap paramMap) throws Exception;
    
    // 외상매입금 원장계수정정등록
    void insertCa4020One(LinkedHashMap paramMap) throws Exception;
}
