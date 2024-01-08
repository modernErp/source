package com.sfmes.by.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : By3020Service.java
 * @Description : By3020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.08.11   곽환용     최초생성
 * @ 2020.09.23   김지혜     매입반품등록화면으로 수정
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface By3020Service {
    
    /**
     * 매입반품기본내역 조회(BY_RGD_REF_DSC 구분)
     */    
    List<?> selectBy3020_01(LinkedHashMap paramMap) throws Exception;

    /**
     * 매입반품상세내역 조회
     */    
    List<?> selectBy3020_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 매입정산내역 조회
     */    
    List<?> selectBy3020_03(LinkedHashMap paramMap) throws Exception;
    
    
    /**
     * 입고반품내역 조회
     * - PDA로 스캔한 입고반품 내역을 조회한다 (매입반품 등록을 위해 PDA로 스캔한 입고반품 내역)
     */    
    List<?> selectBy3020_04(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 매입반품내역 등록
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    String insertBy3020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception; 
    
    /**
     * 매입반품내역 삭제
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void deleteBy3020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;    
}
