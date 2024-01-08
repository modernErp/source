package com.sfmes.co.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name  : Co1040Service.java
 * @Description : Co1040Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.07.03   여다혜    최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.03
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Co1040Service {
    
    /**
     * 물품목록조회(팝업) - 기준정보용(공통)
     */    
    List<?> select_GDS_List(LinkedHashMap paramMap) throws Exception;

    /**
     * 물품이력조회(팝업) - 기준정보용(공통)
     */    
    List<?> select_GDS_List1(LinkedHashMap paramMap) throws Exception;
 
    /**
     * 물품목록조회(팝업) - 업무용(매입, 매출, 생산, 등)
     */    
    List<?> select_GDS_List_ForWork(LinkedHashMap paramMap) throws Exception;

    
    /**
     * 물품조회(1건)
     */    
    List<?> select_GDS_ONE(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 물품등록
     */    
    String insert_Gds(LinkedHashMap paramMap) throws Exception;

    
    /**
     * 물품수정
     */    
    void update_Gds(LinkedHashMap paramMap) throws Exception;

    
    /**
     * 표준부위코드(대분류)조회
     */    
    List<?> select_LATC_LCLC(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 표준부위코드(중분류)조회
     */    
    List<?> select_LATC_MCLC(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 표준부위코드(소분류)조회
     */    
    List<?> select_LATC_SCLC(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 물품분류코드(전체)조회
     */    
    List<?> select_GDS_CLF(LinkedHashMap paramMap) throws Exception;
    
    

    /**
     * 물품코드채번
     */   
    List<?> select_Auto_GDS_C(LinkedHashMap paramMap) throws Exception;
}
