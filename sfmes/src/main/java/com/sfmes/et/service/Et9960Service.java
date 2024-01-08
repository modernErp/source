package com.sfmes.et.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name  : Et9960Service.java
 * @Description : Et9960Service class
 * @Modification Information
 * @
 * @  수정일              수정자                수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2022.09.22    김주경               최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.09.22
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Et9960Service {
    
    /**
     * 전표별매입기본내역
     * */
    List<?> selectEt9960_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 전표별매입상세내역
     * */
    List<?> selectEt9960_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 전표-물품별매입내역
     * */
    List<?> selectEt9960_03(LinkedHashMap paramMap) throws Exception;

}
