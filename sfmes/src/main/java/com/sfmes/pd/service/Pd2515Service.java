package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
* @Class Name : Pd2515Service.java
* @Description : Pd2515Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.16   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.16
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd2515Service {
    
    /**
     * 공정기록서 기반 작업지시 내역조회
     */
    List<?> selectPd2515List_01(LinkedHashMap paramMap) throws Exception;
    

}
