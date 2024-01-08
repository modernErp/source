package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sm2120Service.java
 * @Description : Sm2120Service Class
 * @Modification Information
 * @
 * @  수정일        수정자     수정내용
 * @ ----------   ---------   -------------------------------
 * @ 2020.09.17     정성환     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sm2120Service {
    //품원장일별집계내역 기본조회
    List<?> select_Sm2120_01(LinkedHashMap paramMap) throws Exception;
}
