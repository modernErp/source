package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy2050Service;

/**
 * @Class Name : Sy2050ServiceImpl.java
 * @Description : 사용자별역할 조회/등록
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.06.15  여다혜  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sy2050Service")
public class Sy2050ServiceImpl extends CmnAbstractServiceImpl implements Sy2050Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;


    @Override
    public List<?> selectCmncForEmpRole(LinkedHashMap paramMap) throws Exception {
        //SY2030 역할그룹내역조회와 동일한 쿼리 사용
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy2030_Comnc", paramMap);
    }
    
    @Override
    public List<?> selectUsrInfoList(LinkedHashMap paramMap) throws Exception {
        
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy2050_UsrRole", paramMap);
    }

    @Override
    public void saveUsrRole(List<Map<String, Object>> paramList) throws Exception {
        for(Map<String, Object> map : paramList) {
            sqlSession.insert("sfmes.sqlmap.sy.insertSy2050_UsrRole", map);
            sqlSession.insert("sfmes.sqlmap.sy.insertSy2050_Log", map);
        }
    }
}
