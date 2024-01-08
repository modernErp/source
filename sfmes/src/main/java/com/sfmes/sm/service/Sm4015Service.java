package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sm4015Service.java
 * @Description : Sm4015Service Class
 * @Modification Information
 * @
 * @  수정일               수정자     수정내용
 * @ ----------   ---------   -------------------------------
 * @ 2020.10.21  정성환     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sm4015Service {
    //품간대체처리내역 기본조회
    List<?> select_Sm4015_01(LinkedHashMap paramMap) throws Exception;
}
