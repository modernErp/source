package com.sfmes.sy.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sy2030Service.java
 * @Description : Sy2030Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.06.09   여다혜    최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.09
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Sy2030Service {
    
    /**
     * 역할그룹내역 (공통코드 - 그룹코드 : EMP_ROL_DSC)을 조회한다
     */    
    List<?> selectCmncForEmpRole(LinkedHashMap paramMap) throws Exception;
    

    /**
     * 프로그램내역 (공통코드 - 그룹코드 : EMP_ROL_DSC)을 조회한다
     */
    List<?> selectPgmAuth(LinkedHashMap paramMap) throws Exception;
    
    
    /**
     * 역할그룹별 프로그램 권한내역을 추가/수정/삭제한다.
     */    
    void saveMenuAuth(List<Map<String, Object>> paramList) throws Exception;
}
