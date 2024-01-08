package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name : Sm2210Service.java
 * @Description : Sm2210Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2021.12.28   김상철      최초생성
 *
 * @author (주)모든솔루션
 * @since 2021.12.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Sm2210Service {

    // 입고처원장조회
    List<?> selectSm2210List(LinkedHashMap paramMap) throws Exception;
    
    // 입고처원장 정산 정보 조회
    List<?> selectSm2210List2(LinkedHashMap paramMap) throws Exception;
}