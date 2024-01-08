package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy2030Service;


/**
 * @Class Name : Sy2030ServiceImpl.java
 * @Description : 역할그룹별 프로그램 권한 조회 / 삽입 / 수정
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
@Service("Sy2030Service")
public class Sy2030ServiceImpl extends CmnAbstractServiceImpl implements Sy2030Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    /**
     * 역할그룹내역 조회 (EMP_ROL_DSC 로 공통코드 조회)
     * @param paramMap 
     * @return List
     * @exception Exception
     */
    @Override
    public List<?> selectCmncForEmpRole(LinkedHashMap paramMap) throws Exception {
        // TODO Auto-generated method stub
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy2030_Comnc", paramMap);
    }
    
    /**
     * 역할그룹별 프로그램 권한내역 조회  
     * @param paramMap - 역할그룹코드
     * @return List
     * @exception Exception
     */
    @Override
    public List<?> selectPgmAuth(LinkedHashMap paramMap) throws Exception {
        // TODO Auto-generated method stub
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy2030_Auth", paramMap);
    }

    /**
     * 역할그룹별 프로그램 권한내역 삽입/수정  
     * @param paramMap 
     * @return 
     * @exception Exception
     */
    @Override
    public void saveMenuAuth(List<Map<String, Object>> paramList) throws Exception {
        // TODO Auto-generated method stub
        for(Map<String, Object> map : paramList) {
            sqlSession.insert("sfmes.sqlmap.sy.insertSy2030_Auth", map); //MERGE
            sqlSession.insert("sfmes.sqlmap.sy.insertSy2030_Log", map);  //이력INSERT
        }
    }
}
