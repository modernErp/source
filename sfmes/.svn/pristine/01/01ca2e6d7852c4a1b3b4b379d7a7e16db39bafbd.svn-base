package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.cm.web.LoginVO;
import com.sfmes.sy.service.LoginService;
import com.sfmes.sy.service.LogoutService;

/**
 * @Class Name : LogoutServiceImpl.java
 * @Description : 로그아웃 ServiceImple
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.21  여다혜  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.08.21
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("LogoutService")
public class LogoutServiceImpl extends CmnAbstractServiceImpl implements LogoutService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    //로그아웃 시, CONN_YN 정보 업데이트
    @Override
    public void updateUsrDisconnInfo(LoginVO loginVO) throws Exception {
        sqlSession.update("sfmes.sqlmap.sy.updateUsrDisconnInfo", loginVO);
    }
}
