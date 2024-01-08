package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy2040Service;

/**
 * @Class Name : Sy2040ServiceImpl.java
 * @Description : 사용자별역할 조회/등록
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.06.10  여다혜  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sy2040Service")
public class Sy2040ServiceImpl extends CmnAbstractServiceImpl implements Sy2040Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * 사용자정보내역 조회
     * @param paramMap 
     * @return 
     * @exception Exception
     */
    @Override
    public List<?> selectUsrInfoList(LinkedHashMap paramMap) throws Exception {
        // TODO Auto-generated method stub
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy2040_Usr", paramMap);
    }


    /**
     * 사용자역할그룹내역 조회
     * @param paramMap 
     * @return 
     * @exception Exception
     */
    @Override
    public List<?> selectUsrRole(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy2040_UsrRole", paramMap);
    }


    /**
     * 사용자별역할그룹내역 저장
     * @param paramMap 
     * @return 
     * @exception Exception
     */
    @Override
    public void saveUsrRole(List<Map<String, Object>> paramList) throws Exception {
        for(Map<String, Object> map : paramList) {
            sqlSession.insert("sfmes.sqlmap.sy.insertSy2040_UsrRole", map);
            sqlSession.insert("sfmes.sqlmap.sy.insertSy2040_Log", map);  //이력INSERT
        }
    }
}
