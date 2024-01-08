package com.sfmes.sy.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name  : Sy1020Service.java
 * @Description : Sy1020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.06.08   여다혜    최초생성
 * @ 2021.12.16   여다혜    마이메뉴(메뉴즐겨찾기) 추가 
 *
 * @author (주)모든솔루션
 * @since 2020.06.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Sy1020Service {
    
    /**
     * 전체 메뉴 조회
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */  
    List<?> selectAllMenu(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 사용자 메뉴 조회
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */  
    List<?> selectUsingMenu(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 사업장목록조회(전체)
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */  
    List<?> selectBzplList(LinkedHashMap paramMap) throws Exception;

    
    /**
     * 사업장목록조회(권한사업장)
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */  
    List<?> selectAuthBzplList(LinkedHashMap paramMap) throws Exception;
    
    
    /**
     * 사용자목록조회
     * @param paramMap - 조회할 조건이 담긴 Map(회사코드)
     * @return 사용자id, 사용자명
     * @exception
     */  
    List<?> selectUsrList(LinkedHashMap paramMap) throws Exception;
    
    

    /**
     * 실행 프로그램 권한 조회 (20.10.16 추가)
     * @param paramMap / 권한조건 옵션 (1:최소권한, 2:최대권한)
     * @return 프로그램id, 권한
     * @exception
     */
    List<?> selectPgmAuth(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 마이메뉴(메뉴즐겨찾기) 조회 (21.12.16 추가)
     * @param paramMap 
     * @return paramList 
     * @exception
     */
    List<?> selectSy1020_MyMenu(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 마이메뉴(메뉴즐겨찾기) 등록 (21.12.16 추가)
     * @param paramMap 
     * @return void 
     * @exception
     */
    void insertSy1020_MyMenu(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 마이메뉴(메뉴즐겨찾기) 삭제 (21.12.16 추가)
     * @param paramMap 
     * @return void 
     * @exception
     */
    void deleteSy1020_MyMenu(LinkedHashMap paramMap) throws Exception;
}
