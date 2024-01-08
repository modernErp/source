package com.sfmes.ca.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name : Ca1025Service.java
 * @Description : Ca1025Service Class
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
public interface Ca1025Service {

    // 외상매출금원장계수정정내역 조회
    List<?> selectCa1025List(LinkedHashMap paramMap) throws Exception; 

    // 외상매출금원장계수정정내역 조회
    List<?> selectCa1025List_2(LinkedHashMap paramMap) throws Exception; 
}
