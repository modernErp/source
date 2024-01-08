package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Pd3010Service.java
 * @Description : Pd3010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.08.13   여다혜    최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.08.13
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Pd3010Service {
    
    /**
     * 최종예정원가 조회
     */    
    List<?> selectPd3010_01(LinkedHashMap paramMap) throws Exception;
    
    
    /**
     * 적용일자원가 조회
     */    
    List<?> selectPd3010_02(LinkedHashMap paramMap) throws Exception;
   
    
    /**
     * 신규품목조회 (아직예정원가가 입력되지 않은 신규품목)
     */ 
    List<?> selectPd3010_03(LinkedHashMap paramMap) throws Exception;
    
    
    /**
     * 예정원가 변경이력 조회
     */ 
    List<?> selectPd3010_04(LinkedHashMap paramMap) throws Exception;
    
    
    /**
     * 예정원가 등록
     */    
    void insertPd3010(List<Map<String, Object>> paramList) throws Exception;
    

    /**
     * 예정원가 수정
     */    
    void updatePd3010(List<Map<String, Object>> paramList) throws Exception;    
}
