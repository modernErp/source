package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sm3010Service.java
 * @Description : Sm3010Service Class
 * @Modification Information
 * @
 * @  수정일           수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.08  정성환             최초생성
 * @ 2021.03.19  장경석             PDA용 추가
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sm3010Service {
    
    // 창고간이동내역 저장 
    String saveSm3010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    // 창고간이동기본내역조회
    List<?> searchSm3010_01(LinkedHashMap paramMap) throws Exception;
    
    // 창고간이동상세내역조회
    List<?> searchSm3010_02(LinkedHashMap paramMap) throws Exception;
    
    //창고간이동등록 전표 삭제 
    String deleteSm3010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    //창고간이동내역수정
    String updateSm3010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;    

    // 창고간이동내역 저장 
    String savePDA_Sm3010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;

    // 창고별 물품내역조회
    List<?> searchPDA_Sm3010_01(LinkedHashMap paramMap) throws Exception;
    
    // 물품별 창고물품내역조회
    List<?> searchPDA_Sm3010_02(LinkedHashMap paramMap) throws Exception;
}
