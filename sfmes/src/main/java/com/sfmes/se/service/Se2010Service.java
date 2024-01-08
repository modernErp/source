package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Se2010Service.java
 * @Description : Se2010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.07.14   곽환용     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Se2010Service {
    
    /**
     * 수주기본내역 조회
     */    
    List<?> selectSe2010_01(LinkedHashMap paramMap) throws Exception;

    /**
     * 수주상세내역 조회
     */    
    List<?> selectSe2010_02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 수주내역찾기팝업 조회
     */    
    List<?> selectSe2010_03(LinkedHashMap paramMap) throws Exception;   
    
    /**
     * 매출단가부가세포함여부 조회
     */    
    List<?> selectSe2010_04(LinkedHashMap paramMap) throws Exception;    
    
    /**
     * 수주내역 등록
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    String insertSe2010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    /**
     * 수주내역 수정
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    String updateSe2010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    /**
     * 수주내역 삭제
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void deleteSe2010(LinkedHashMap paramMap) throws Exception;     

    /**
     * 참조수주마감내역 수정(출고지시등록 시)
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void Call_updateSe2010_DLR_DNTT(List<Map<String, Object>> paramList) throws Exception;  
    
    /**
     * 참조수주마감내역 수정 작업지시기본 (작업지시등록 시)
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void Call_updateSe2010_WK_DNTT(Map<String, Object> map) throws Exception;
    
    /**
     * 참조수주마감내역 수정 작업지시상세 (작업지시등록 시)   추가  20211219  rchkorea
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void Call_updateSe2010_WK_DNTT_D(Map<String, Object> map) throws Exception;      
    
    
    /**
     * 참조수주마감내역 수정 작업지시기본 (작업지시등록 수주건별분할 시)  추가  20220407  ksckorea
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void Call_updateSe2010_WK_DNTT_NON(Map<String, Object> map) throws Exception;
    
    /**
     * 참조수주마감내역 수정 작업지시상세 (작업지시등록 시)   추가  20220504  ksckorea
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void Call_updateSe2010_WK_DNTT_D_NON(Map<String, Object> map) throws Exception;      
    
}
