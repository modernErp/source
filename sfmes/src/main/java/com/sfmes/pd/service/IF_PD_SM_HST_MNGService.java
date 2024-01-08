package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @Class Name : IF_PD_SM_HST_MNGService.java
* @Description : IF_PD_SM_HST_MNGService Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.09.08   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.08.24
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface IF_PD_SM_HST_MNGService {
    
    /**
     * 입고물품이력등록
     */
    void if_HST_MNG_insert(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;

    /**
     * 입고물품이력삭제여부변경
     */
    void if_HST_MNG_delete(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 물품이력출고정보등록
     */
    LinkedHashMap<String, Object> if_HST_MNG_Dlr_insert(LinkedHashMap paramMap) throws Exception;

    /**
     * 물품이력출고정보삭제
     */
    LinkedHashMap<String, Object> if_HST_MNG_Dlr_delete(LinkedHashMap paramMap) throws Exception;
}
