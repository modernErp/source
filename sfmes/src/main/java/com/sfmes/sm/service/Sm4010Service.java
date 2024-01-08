package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sm4010Service.java
 * @Description : Sm4010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.19  정성환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sm4010Service {
    
    //품간대체처리내역 저장 
    String saveSm4010(LinkedHashMap paramMap, List<Map<String, Object>> paramList1, List<Map<String, Object>> paramList2) throws Exception;
   
    //품간대체처리내역 전표 삭제 
    String deleteSm4010(LinkedHashMap paramMap, List<Map<String, Object>> paramList1, List<Map<String, Object>> paramList2) throws Exception;
    
    //품간대체처리내역
    String updateSm4010(LinkedHashMap paramMap, List<Map<String, Object>> paramList1, List<Map<String, Object>> paramList2) throws Exception;
    
    //품간대체처리내역- 창고찾기팝업
    List<?> searchSm4010P03 (LinkedHashMap paramMap) throws Exception;
    
    //품간대체처리내역 기본 팝업
    List<?> searchSm4010P01_M (LinkedHashMap paramMap) throws Exception;
    
    //품간대체처리내역 상세 팝업
    List<?> searchSm4010P01_D (LinkedHashMap paramMap) throws Exception;
}
