package com.sfmes.ge.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Ge1020Service.java
 * @Description : Ge1020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.10.23   여다혜    최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Ge1020Service {
    
    /* GE1020, GE1025*/
    //업무연락 등록 조회 (사용자가 등록한 업무연락 목록)
    List<?> selectGe1020(LinkedHashMap paramMap) throws Exception;
    
    //업무연락 내역 조회 (사용자가 수신한 업무연락 목록)
    List<?> selectGe1025(LinkedHashMap paramMap) throws Exception;
    
    
    //업무연락 등록 및 수정
    String insertGe1020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    //업무연락 수정   
    String updateGe1020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;

    
    //업무연락 수신확인 업데이트
    void update_RC_STS_C(LinkedHashMap paramMap) throws Exception;
    
    
    /* GE1020P01 */
    //업무연락 상세 조회(팝업)
    List<?> selectGe1020P01_Detail(LinkedHashMap paramMap) throws Exception;
    
    //업무연락 수신인 목록 조회(팝업)
    List<?> selectGe1020P01_Rcst_id_List(LinkedHashMap paramMap) throws Exception;

    
    
}
