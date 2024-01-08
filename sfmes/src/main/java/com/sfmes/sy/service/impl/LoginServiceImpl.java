package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.LoginService;

/**
 * @Class Name : LoginServiceImpl.java
 * @Description : 일반 로그인, 인증서 로그인을 처리하는 비즈니스 구현 클래스
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.05.28  여다혜  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.05.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("LoginService")
public class LoginServiceImpl extends CmnAbstractServiceImpl implements LoginService {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    /**
     * 로그인하는 회사의 사용여부 확인
     * @param paramMap
     * @return List
     * @exception Exception
     */
    @Override
    public String selectCorpChk(LinkedHashMap paramMap) throws Exception {
    	return sqlSession.selectOne("sfmes.sqlmap.sy.selectCorpChk",paramMap);
    }
    
    /**
     * 로그인하는 사용자 정보 조회
     * @param paramMap - 사용자ID
     * @return List
     * @exception Exception
     */
    @Override
    public List<?> selectUsrInfo(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.selectUsrInfo", paramMap);
    }

    @Override
    public int selectLginProvNt(LinkedHashMap paramMap) throws Exception {
        // TODO Auto-generated method stub
        return (int)sqlSession.selectOne("sfmes.sqlmap.sy.selectLginProvNt", paramMap);
    }

    @Override
    public void updateUsrLginProvNt(LinkedHashMap paramMap) throws Exception {
        sqlSession.update("sfmes.sqlmap.sy.updateUsrLginProvNt", paramMap);
    }

    @Override
    public void updateUsrLockYn(LinkedHashMap paramMap) throws Exception {
        sqlSession.update("sfmes.sqlmap.sy.updateUsrLockYn", paramMap);
    }

    @Override
    public void updateUsrConnInfo(LinkedHashMap paramMap) throws Exception {
        sqlSession.update("sfmes.sqlmap.sy.updateUsrConnInfo", paramMap);
    }


    @Override
    public int selectBasBzplCnt(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectOne("sfmes.sqlmap.sy.selectBasBzplCnt", paramMap);
    }


    
}
