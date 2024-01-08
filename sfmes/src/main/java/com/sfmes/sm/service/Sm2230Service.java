package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name : Sm2230Service.java
 * @Description : Sm2230Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2022.01.07   김상철      최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.01.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Sm2230Service {

    // 출고처원장조회
    List<?> selectSm2230List(LinkedHashMap paramMap) throws Exception;
    
    List<?> selectSm2230List2(LinkedHashMap paramMap) throws Exception;
}