package com.sfmes.sy.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sy4020ervice.java
 * @Description : Sy4020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.10  김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sy4020Service {
    
    //시스템오류이력 조회
    List<?> selectSy4020ErrList(LinkedHashMap paramMap) throws Exception;
    
    //시스템오류이력상세 조회
    List<?> selectSy4020ErrDetail(LinkedHashMap paramMap) throws Exception;
    
    //시스템오류이력등록(throws Exception 미처리함(무한반복 방지용)
    void insertSy4020(LinkedHashMap paramMap);
}
