package com.sfmes.ge.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.ge.service.Ge1011Service;

/**
 * @Class Name  : Ge1011ServiceImpl.java
 * @Description : 공지사항 조회 (사용자용)
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.10.19  여다혜  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Ge1011Service")
public class Ge1011ServiceImpl extends CmnAbstractServiceImpl implements Ge1011Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    //공지사항조회(GE1011)
    @Override
    public List<?> selectGe1011_OFANC_List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe1011_OFANC_List", paramMap);
    }
}
