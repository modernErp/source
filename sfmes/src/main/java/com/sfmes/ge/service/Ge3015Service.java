package com.sfmes.ge.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Ge3015Service.java
 * @Description : Ge3015Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.03.16   나명우    최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Ge3015Service {
    
    //AS 접수내역 조회
    List<?> selectGe3015_01(LinkedHashMap paramMap) throws Exception;
    
}
