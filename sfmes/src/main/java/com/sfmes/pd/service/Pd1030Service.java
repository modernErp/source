package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Pd1030Service.java
 * @Description : Pd1030Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.08.10   김종관    최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.08.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Pd1030Service {
    //공정기로서등록 등록
    void insertPd1030(LinkedHashMap paramMap, List<Map<String, Object>> paramList, List<Map<String, Object>> paramList1) throws Exception;

    //공정기록서 수정
    void updatePd1030(LinkedHashMap paramMap, List<Map<String, Object>> paramList, List<Map<String, Object>> paramList1) throws Exception;

    //공정기록서_POPUP
    List<?> selectPrwPopupList(LinkedHashMap paramMap) throws Exception;

    //공정기록서 조회
    List<?> selectPrwRecList(LinkedHashMap paramMap) throws Exception;
    
    //공정기록서_생산내역 조회
    List<?> selectPrwPdGdsList(LinkedHashMap paramMap) throws Exception;
    
    //공정기록서_투입내역 조회
    List<?> selectPrwPtinList(LinkedHashMap paramMap) throws Exception;
    
    //공정기록서_POPUP_생산물품
    List<?> selectPrwPopupList_PdGds(LinkedHashMap paramMap) throws Exception;

    //공정기록서_POPUP_투입물품
    List<?> selectPrwPopupList_PtinGds(LinkedHashMap paramMap) throws Exception;
}
