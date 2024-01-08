package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name  : Se3110Service.java
 * @Description : Se3110Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.10.28   곽환용     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) Se 모든솔루션 All right reserved.
 */
public interface Se3110Service {
    
    /**
     * 거래처별매출현황
     */    
    List<?> selectSe3110_01(LinkedHashMap paramMap) throws Exception;

    /**
     * 물품별매출현황
     */    
    List<?> selectSe3110_02(LinkedHashMap paramMap) throws Exception;
}
