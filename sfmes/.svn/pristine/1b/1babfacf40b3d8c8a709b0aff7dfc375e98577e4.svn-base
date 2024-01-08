package com.sfmes.by.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : By2010Service.java
 * @Description : By2010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.07.06   곽환용     최초생성
 * @ 2021.12.13   여다혜     발주취소 추가
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface By2010Service {
    
    /**
     * 발주기본내역 조회
     */    
    List<?> selectBy2010_01(LinkedHashMap paramMap) throws Exception;

    /**
     * 발주상세내역 조회
     */    
    List<?> selectBy2010_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 발주내역찾기팝업 조회
     */    
    List<?> selectBy2010_03(LinkedHashMap paramMap) throws Exception; 
    
    /**
     * 발주서내역 조회
     */    
    List<?> selectBy2010_04(LinkedHashMap paramMap) throws Exception;     
    
    /**
     * 발주내역 등록
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    String insertBy2010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    /**
     * 발주내역 수정
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    String updateBy2010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;    
    
    /**
     * 발주취소
     * @param paramMap
     * @return void형
     * @exception Exception
     */
    void updateBy2010_cancel(LinkedHashMap paramMap) throws Exception;    
    
    /**
     * 발주상태구분 수정하는 함수
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void Call_updateBy2010(LinkedHashMap paramMap) throws Exception;     

}
