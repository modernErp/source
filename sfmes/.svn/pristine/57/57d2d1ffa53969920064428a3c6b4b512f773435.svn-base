package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sm1050Service.java
 * @Description : Sm1050Service Class
 * @Modification Information
 * @
 * @  수정일        수정자     수정내용
 * @ ----------   ---------   -------------------------------
 * @ 2020.08.26     곽환용     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sm1050Service {
    //출고기본내역조회
    List<?> select_Sm1050_01(LinkedHashMap paramMap) throws Exception;
    
    //출고상세내역조회
    List<?> select_Sm1050_02(LinkedHashMap paramMap) throws Exception;
    
    //출고내역찾기팝업 조회
    List<?> select_Sm1050_03(LinkedHashMap paramMap) throws Exception;
    
    //출고내역조회[SM1055]
    List<?> select_Sm1050_04(LinkedHashMap paramMap) throws Exception;
    
    //참조출고지시상세내역조회
    List<?> select_Sm1050_M_DLR(LinkedHashMap paramMap) throws Exception;    
    
    //참조출고지시상세내역조회
    List<?> select_Sm1050_D_DLR(LinkedHashMap paramMap) throws Exception;
    
    //출고내역 저장 
    String saveSm1050(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    //출고내역 수정
    String updateSm1050(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;    
    
    //입고내역 삭제
    void deleteSm1050(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;    
    
    //출고상태구분 수정하는 함수
    void Call_updateSm1050(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception; 
}
