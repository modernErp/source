package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sm1000Service.java
 * @Description : 재고 입출고 등록 및 월집계 인터페이스
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.09.01  이철홍      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sm1000Service {
    
    // 재고 입출고 내역 저장 인터페이스 
    void Call_saveSm1000(LinkedHashMap paramMap1, List<Map<String, Object>> paramList1, LinkedHashMap paramMap2, List<Map<String, Object>> paramList2) throws Exception;
    
    // 재고 월별집계 변경 처리 집계 인터페이스 
    void Call_Sm1000MonthChk(LinkedHashMap paramMap) throws Exception;
    
    // 재고 월별집계 재집계 인터페이스 전표별  
    void Call_Sm1000MonthTot(LinkedHashMap paramMap) throws Exception;
    
    // 재고 월별집계 재집계 인터페이스 
    void Call_Sm1000MonthTot_LE(LinkedHashMap paramMap) throws Exception;

    // 재고 입고내역의 상태값을 변경하는 인터페이스
    void Call_Sm1000StsUpd(LinkedHashMap paramMap) throws Exception;
    
    // 재고 입고내역의 상태값을 변경하는 인터페이스(거래일련번호 기준)
    void Call_Sm1000StsUpdTrno(LinkedHashMap paramMap) throws Exception;
    
}
