package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Pd2020Service.java
 * @Description : Pd2020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.08.10   김종관    최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.08.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Pd2020Service {
    
    /**
     *  작업지시제품상세 조회
     */    
    List<?> selectWkDnttList(LinkedHashMap paramMap) throws Exception;

    /**
     *  작업지시자재상세 조회
     */    
    List<?> selectWkDnttMtrlList(LinkedHashMap paramMap) throws Exception;

    /**
     * 작업지시 등록
     */
    void insertPd2020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;

}
