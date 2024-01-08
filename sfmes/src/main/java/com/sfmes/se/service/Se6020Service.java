package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Se6020Service.java
 * @Description : Se6020Service Class
 * @Modification Information
 * @
 * @  수정일        수정자     수정내용
 * @ ----------   ---------   -------------------------------
 * @ 2020.09.14     곽환용     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Se6020Service {
    //제품출고기본내역조회
    List<?> selectSe6020_01(LinkedHashMap paramMap) throws Exception;
    
    //제품출고상세내역조회
    List<?> selectSe6020_02(LinkedHashMap paramMap) throws Exception;
    
    //제품출고내역찾기팝업 조회
    List<?> selectSe6020_03(LinkedHashMap paramMap) throws Exception;
    
    //참조출고지시상세내역조회
    List<?> selectSe6020_M_DLR(LinkedHashMap paramMap) throws Exception;    
    
    //참조출고지시상세내역조회
    List<?> selectSe6020_D_DLR(LinkedHashMap paramMap) throws Exception;
    
    // PDA 물품정보조회
    List<?> select_PDA_DLR_GDS(LinkedHashMap paramMap) throws Exception;

    //PDA 물품기반 발주참조입고예정내역조회
    List<?> select_Pda_Sm1050_DLRGDS_M(LinkedHashMap paramMap) throws Exception;

    // PDA 출고예정일조회
    List<?> select_Pda_Sm1050_DLRDT(LinkedHashMap paramMap) throws Exception;

    // PDA 출고예정일의 거래처조회
    List<?> select_Pda_TRPL(LinkedHashMap paramMap) throws Exception;
    
    // 제품출고의뢰내역조회
    List<?> select_Pda_Sm1050_DLR_M(LinkedHashMap paramMap) throws Exception;

    //거래명세서출력
    List<?> selectSe6020_R(LinkedHashMap paramMap) throws Exception;    
    
    //제품출고내역 저장 
    String saveSe6020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    //제품출고내역 수정
    String updateSe6020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;    
    
    //제품출고내역 삭제
    void deleteSe6020(LinkedHashMap paramMap) throws Exception;    
    
    //출고상태구분 수정하는 함수
    void Call_updateSe6020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception; 
}
