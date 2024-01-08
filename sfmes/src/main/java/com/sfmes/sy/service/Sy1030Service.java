package com.sfmes.sy.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
* @Class Name : Sy1030Service.java
* @Description : Sy1030Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.05.28   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.05.28
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Sy1030Service {
    
    /**
     * 신규사용자등록
     */
    void insertSy1030(LinkedHashMap paramMap) throws Exception;

    /**
     * 사용자정보변경
     */
    void updateSy1030(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 비밀번호 초기화
     */
    void updateSy1030_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 비밀번호변경
     */
    void updateSy1030_02(LinkedHashMap paramMap) throws Exception;

    /**
     * 사용자정보조회
     */
    List<?> selectSy1030List(LinkedHashMap paramMap) throws Exception;

}
