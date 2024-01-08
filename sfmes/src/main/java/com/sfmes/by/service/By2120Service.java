package com.sfmes.by.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name  : By2120Service.java
 * @Description : By2120Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.09.16   김지혜     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface By2120Service {
    
    //발주대비(미)입고현황 조회  
    List<?> selectBy2120(LinkedHashMap paramMap) throws Exception;
}
