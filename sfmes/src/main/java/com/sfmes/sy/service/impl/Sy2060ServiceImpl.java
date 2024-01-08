package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy2060Service;

/**
 * @Class Name : Sy2060ServiceImpl.java
 * @Description : 사용자별역할 조회/등록
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.26  여다혜  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.08.26
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sy2060Service")
public class Sy2060ServiceImpl extends CmnAbstractServiceImpl implements Sy2060Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<?> selectUsrInfoList(LinkedHashMap paramMap) throws Exception {
        //SY2050과 동일한 쿼리 사용(사용자정보조회)
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy2040_Usr", paramMap);
    }

    @Override
    public List<?> selectAuthBzplList(LinkedHashMap paramMap) throws Exception {
        // TODO Auto-generated method stub
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy2060_AuthBzplList", paramMap);
    }

    @Override
    public void saveAuthBzpl(List<Map<String, Object>> paramList) throws Exception {
        for(Map<String, Object> map : paramList) {
            sqlSession.insert("sfmes.sqlmap.sy.insertSy2060_BzplAuth", map);
        } 
    }
}
