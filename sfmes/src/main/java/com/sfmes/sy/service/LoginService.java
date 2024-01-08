package com.sfmes.sy.service;

import java.util.LinkedHashMap;
import java.util.List;


/**
 * 로그인을 처리하는 비즈니스 인터페이스 클래스
 * 
 * @author 
 * @since 2020.05.28
 * @version 1.0
 * @see
 * 
 * 
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  ----------   ------    ---------------------------
 *  2020.05.28   여다혜    최초작성 
 */
public interface LoginService {
	
	/**
	 * 로그인하는 회사의 사용여부 확인
	 * @param paramMap
	 * @return List
	 * @exception Exception
	 */
	String selectCorpChk(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 사용자 정보 조회
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */  
    List<?> selectUsrInfo(LinkedHashMap paramMap) throws Exception;
        
    /**
     * 로그인 시도 횟수 조회
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */   
    int selectLginProvNt(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 로그인 시도 횟수 증가 (업데이트)
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 
     * @exception
     */  
    void updateUsrLginProvNt(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 비밀번호 5회 실패로 계정잠김 처리 (업데이트)
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 
     * @exception
     */  
    void updateUsrLockYn(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 로그인 성공 시 최종접속 정보 변경 (업데이트)
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 
     * @exception
     */  
    void updateUsrConnInfo(LinkedHashMap paramMap) throws Exception;

    /**
     * 로그인 시도 횟수 조회
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */   
    int selectBasBzplCnt(LinkedHashMap paramMap) throws Exception;
}
