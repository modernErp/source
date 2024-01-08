package com.sfmes.sy.service;

import java.util.LinkedHashMap;

import com.sfmes.cm.web.LoginVO;

/**
 * 로그아웃을 처리하는 비즈니스 인터페이스 클래스
 * 
 * @author 
 * @since 2020.08.21
 * @version 1.0
 * @see
 * 
 * 
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  ----------   ------    ---------------------------
 *  2020.08.21   여다혜    최초작성 
 */
public interface LogoutService {
    /**
     * 로그아웃 시 최종접속 정보 변경 (업데이트)
     * @param paramMap
     * @return 
     * @exception
     */  
    void updateUsrDisconnInfo(LoginVO loginVO) throws Exception;
}
