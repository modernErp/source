package com.sfmes.et.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name  : Et9940Service.java
 * @Description : Et9940Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2022.10.17   박보현     최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.10.17
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Et9940Service {
    
    
    // 전표별 매입기본내역
    List<?> select9940_01(LinkedHashMap paramMap) throws Exception;
    
    // 전표별매입상세내역
    List<?> select9940_02(LinkedHashMap paramMap) throws Exception;
    
    // 전표-물품별매입내역
    List<?> select9940_03(LinkedHashMap paramMap) throws Exception;
    
}
