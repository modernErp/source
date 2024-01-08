package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
* @Class Name : Pd3070Service.java
* @Description : Pd3070Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.12.14   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.12.14
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd3070Service {
    
    /*
     * 배부차액정리 기본정보 팝업리스트 조회 
     */
    List<?> selectPd3070_01(LinkedHashMap paramMap) throws Exception;
    
    /*
     * 배부차액정리 기본정보 조회 
     */
    List<?> selectPd3070_02(LinkedHashMap paramMap) throws Exception;
    
    /*
     * 배부차액정리 상세정보 조회 
     */
    List<?> selectPd3070_03(LinkedHashMap paramMap) throws Exception;
}
