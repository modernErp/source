package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Se1020Service.java
 * @Description : Se1020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.07.14   김선규     최초생성
 * @ 2020.09.02   곽환용     수정
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Se1020Service {
        
    /**
     * 가격군내역
     */    
    List<?> selectSe1020_01(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 가격군별물품내역
     */    
    List<?> selectSe1020_02(LinkedHashMap paramMap) throws Exception;    
    
    /**
     * 도매매출단가조회
     */    
    List<?> selectSe1020_03(LinkedHashMap paramMap) throws Exception;     
    
    /**
     * 최근적용일자의 매출단가만 조회
     */    
    List<?> selectSe1020_04(LinkedHashMap paramMap) throws Exception;  
    
    /**
     * 가격군등록
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void insertSe1020_01(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    /**
     * 가격군별물품등록
     * @param paramList - 등록할 정보가 담긴 List
     * @return void형
     * @exception Exception
     */
    void insertSe1020_02(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
}
