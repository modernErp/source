package com.sfmes.sy.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name : Sy2010Service.java
 * @Description : Sy2010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.06.11   김수민    최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sy2010Service {
    
    /**
     * 메뉴등록
     */
    void insertSy2010(LinkedHashMap paramMap) throws Exception;

    /**
     * 메뉴변경
     */
    void updateSy2010(LinkedHashMap paramMap) throws Exception;

    /**
     * 메뉴목록조회 
     */
    List<?> selectSy2010List(LinkedHashMap paramMap) throws Exception;
    
    
}
