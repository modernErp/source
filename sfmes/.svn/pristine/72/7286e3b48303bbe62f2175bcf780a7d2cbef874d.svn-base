
package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sm1010Service.java
 * @Description : Sm1010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.06  김지혜      최초생성
 * @ 2020.09.21  정성환      변경
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sm1010Service {
    
  //입고내역 저장 
    String saveSm1010_2(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    //입고내역 삭제
    String updateSm1010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;    
    
    //입고내역 삭제
    void deleteSm1010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    // TOTE MSTTBL 내역 등록
    void Call_insertPdTote_MSTTBL(LinkedHashMap paramMap) throws Exception;
    
    // TOTE MSTTBL 내역 삭제
    void Call_deletePdTote_MSTTBL(LinkedHashMap paramMap) throws Exception;
    
    // TOTE 입출 내역 등록
    void Call_insertPdTote(LinkedHashMap paramMap, List<Map<String, Object>> paramList, LinkedHashMap paramMap2, List<Map<String, Object>> paramList2) throws Exception;

    // TOTE 입출 내역 삭제
    void Call_deletePdTote(LinkedHashMap paramMap, List<Map<String, Object>> paramList, LinkedHashMap paramMap2, List<Map<String, Object>> paramList2) throws Exception;
    
    // PDA 입고 내역 등록
    String saveSm1010_PDA(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    //입고기본내역조회
    List<?> searchSm1010_01(LinkedHashMap paramMap) throws Exception;
    
    //입고상세내역조회
    List<?> searchSm1010_02(LinkedHashMap paramMap) throws Exception;
    
    //입고내역찾기팝업 조회
    List<?> select_Sm1010P01(LinkedHashMap paramMap) throws Exception;
    
    //입고내역조회[SM1015]
    List<?> select_Sm1015(LinkedHashMap paramMap) throws Exception;
    
    //발주참조입고내역조회
    List<?> select_Sm1010_BY(LinkedHashMap paramMap) throws Exception;

    // 전표별내역 메인 조회
    List<?> select_Sm1015_M(LinkedHashMap paramMap) throws Exception;

    // TOTE_CODE 정보조회
    List<?> select_PdTOTE_M(LinkedHashMap paramMap) throws Exception;

    // PDA 물품정보조회
    List<?> select_PDA_GDS_C(LinkedHashMap paramMap) throws Exception;

    //PDA 물품기반 발주참조입고예정내역조회
    List<?> select_Sm1010_ODRGDS_M(LinkedHashMap paramMap) throws Exception;

    // PDA 입고예정일조회
    List<?> select_Pda_ODRDT(LinkedHashMap paramMap) throws Exception;

    // PDA 입고예정일의 거래처조회
    List<?> select_Pda_TRPL(LinkedHashMap paramMap) throws Exception;
    
    //발주참조입고내역조회
    List<?> select_Sm1010_ODR_M(LinkedHashMap paramMap) throws Exception;
}
