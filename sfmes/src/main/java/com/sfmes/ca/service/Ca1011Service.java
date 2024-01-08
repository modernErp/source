package com.sfmes.ca.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name : Ca1011Service.java
 * @Description : Ca1011Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.08.27  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.08.27
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Ca1011Service {
    
    //외상매출금회수예정내역조회
    List<?> selectCa1011List(LinkedHashMap paramMap) throws Exception;
}