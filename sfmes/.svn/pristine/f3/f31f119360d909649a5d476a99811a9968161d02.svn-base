package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sm1030Service.java
 * @Description : Sm1030Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.06  정성환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sm1030Service {
    
    //출고내역 저장 
    String saveSm1030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    //출고기본내역조회
    List<?> searchSm1030_01(LinkedHashMap paramMap) throws Exception;
    
    //출고상세내역조회
    List<?> searchSm1030_02(LinkedHashMap paramMap) throws Exception;
    
    //출고내역조회[SM1015]
    List<?> select_Sm1015(LinkedHashMap paramMap) throws Exception;
    
    //출고의뢰조회
    List<?> select_Sm1030_05(LinkedHashMap paramMap) throws Exception;
    
    //출고등록 전표 삭제 
    String deleteSm1030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    //출고등록 메인 조회
    List<?> select_Sm1035_M(LinkedHashMap paramMap) throws Exception;
    
    //출고등록 디테일 조회
    List<?> select_Sm1035_D(LinkedHashMap paramMap) throws Exception;
    
    //출고내역찾기팝업 조회
    List<?> search_Sm1030P01(LinkedHashMap paramMap) throws Exception;
    
    // TOTE_CODE 정보조회
    List<?> select_Sm1035_TOTE_M(LinkedHashMap paramMap) throws Exception;
}
