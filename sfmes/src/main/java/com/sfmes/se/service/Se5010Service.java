package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : Se5010Service.java
 * @Description : Se5010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.06  손용찬      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Se5010Service {

    /**
     * 견적서 등록
     * @param paramMap - 등록할 정보가 담긴 Map
     * @param paramList 
     * @return void형
     * @exception Exception
     */
    void insertSe5010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
        
    /**
     * 견적서 수정
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void updateSe5010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    /**
     * 견적서 삭제
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void deleteSe5010(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 견적서 기본조회
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    List<?> selectSe5010List01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 견적서 상세조회
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    List<?> selectSe5010List02(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 견적서 출력(리포트) 조회
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    List<?> selectSe5010List03(LinkedHashMap paramMap) throws Exception;
}
