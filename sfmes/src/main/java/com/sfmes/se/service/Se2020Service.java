package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Se2020Service.java
 * @Description : Se2020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.08.03   곽환용     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Se2020Service {
    
    /**
     * 온라인몰수주일괄등록내역
     */    
    List<?> selectSe2020_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 수주일괄등록내역찾기팝업
     */    
    List<?> selectSe2020_02(LinkedHashMap paramMap) throws Exception;   
    
    /**
     * 거래처물품연결내역 조회
     */    
    List<?> selectSe2020_03(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;    
    
    /**
     * 온라인몰수주일괄등록
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void insertSe2020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
}
