package com.sfmes.ge.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name  : Ge1010Service.java
 * @Description : Ge1010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.10.07   여다혜    최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Ge1010Service {
    
    //공지사항 목록 조회 (전체)
    List<?> selectGe1010_OFANC_List(LinkedHashMap paramMap) throws Exception;
    
    //공지사항 상세 조회 (1건)
    List<?> selectGe1010_OFANC_Detail(LinkedHashMap paramMap) throws Exception;

    //공지사항 등록 
    String insertGe1010_OFANC(LinkedHashMap paramMap) throws Exception;

    //공지사항 수정   
    String updateGe1010_OFANC(LinkedHashMap paramMap) throws Exception;
    
    //공지사항 삭제여부 저장
    void updateGe1010_delYn(LinkedHashMap paramMap) throws Exception;
    
}
