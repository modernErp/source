package com.sfmes.sy.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sy2040Service.java
 * @Description : Sy2040Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.06.12   여다혜    최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Sy2040Service {
    
    /**
     *  사용자정보내역 조회
     */    
    List<?> selectUsrInfoList(LinkedHashMap paramMap) throws Exception;
    
    /**
     *  사용자별 역할그룹내역 조회
     */    
    List<?> selectUsrRole(LinkedHashMap paramMap) throws Exception;
    
    
    /**
     *  사용자별 역할그룹내역 저장
     */    
    void saveUsrRole(List<Map<String, Object>> paramList) throws Exception;
}
