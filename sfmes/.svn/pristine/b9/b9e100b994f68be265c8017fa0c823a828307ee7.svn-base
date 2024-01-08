package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Pd2050Service.java
 * @Description : Pd2050Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.09.10   김종관    최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Pd2050Service {

    /**
     *  작업보고서 조회
     */    
    List<?> selectPd2050DnttList_01(LinkedHashMap paramMap) throws Exception;

    List<?> selectPd2050MtrlList_01(LinkedHashMap paramMap) throws Exception;

    /**
     * 출고의뢰 등록
     */
    void insertPd2050(LinkedHashMap paramMap, List<Map<String, Object>> paramList, List<Map<String, Object>> paramList1) throws Exception;

    /**
     * 작업지시 상태변경
     */
    void updatePd2050(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
}
