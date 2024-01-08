package com.sfmes.ge.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Ge3010Service.java
 * @Description : Ge3010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2022.03.16   나명우    최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Ge3010Service {
    
    //AS접수내역 조회
    List<?> selectGe3010_01(LinkedHashMap paramMap) throws Exception;
    
    //AS접수내역 조회
    List<?> selectGe3010_02(LinkedHashMap paramMap) throws Exception;
    
    //AS접수내역 등록
    String insertGe3010(LinkedHashMap paramMap) throws Exception;
    
    //AS접수내역 수정   
    String updateGe3010(LinkedHashMap paramMap) throws Exception;
    
}
