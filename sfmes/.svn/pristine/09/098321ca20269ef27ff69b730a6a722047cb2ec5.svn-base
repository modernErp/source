package com.sfmes.co.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @Class Name : CoSraNoHistService.java
* @Description : CoSraNoHistService Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.09.09   장경석     최초생성
*
* @author (주)모든솔루션
* @since 2020.09.09
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface CoSraNoHistService {
    
    /**
     * 축산물이력(묶음)번호 내역 조회
     */
    LinkedHashMap<String, Object> CoSraNoHistselect(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 축산물이력(묶음)번호 내역 조회 Process
     */
    void CoSraNoHistupdate(LinkedHashMap paramMap01) throws Exception;
    
    /**
     * 국내산-축산물이력(묶음)번호 내역 조회 (수입축산물이력관리시스템접속)
     */
    LinkedHashMap<String, Object> ConnectDataHistNo(LinkedHashMap paramMap)  throws Exception;
    
    /**
     * 국내산 - 축산물묶음번호 내역 조회 On-line
     */
    LinkedHashMap<String, Object> CoDataBudlNoHistInsert(LinkedHashMap paramMap, LinkedHashMap paramMap01) throws Exception;    

    /**
     * 국내산 - 축산물이력번호 내역 조회 On-line
     */
    LinkedHashMap<String, Object> CoDataIdntNoHistInsert(LinkedHashMap paramMap, LinkedHashMap paramMap01) throws Exception;    

    /**
     * 수입축산물이력(묶음)번호 내역 조회 Process
     */
    LinkedHashMap<String, Object> CoMeatwatchHistselect(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 수입산 - 축산물이력(묶음)번호 내역 조회 (수입축산물이력관리시스템접속)
     */
    LinkedHashMap<String, Object> ConnectCoMeatWatch(LinkedHashMap paramMap)  throws Exception;

    /**
     * 수입축산물묶음번호 내역 조회 On-line
     */
    LinkedHashMap<String, Object> CoMeatwatchBudlNoHistselect(LinkedHashMap paramMap, LinkedHashMap paramMap01) throws Exception;    

    /**
     * 수입축산물이력번호 내역 조회 On-line
     */
    LinkedHashMap<String, Object> CoMeatwatchIdntNoHistselect(LinkedHashMap paramMap, LinkedHashMap paramMap01) throws Exception;    
}
