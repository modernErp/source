package com.sfmes.et.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.et.service.Et9962Service;

/**
* @Class Name : Et9962ServiceImpl.java
* @Description : Et9962Service Class
* @Modification Information
* @
* @  수정일                수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2022.10.05   김주경               최초생성
*
* @author (주)모든솔루션
* @since 2022.10.05
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Et9962Service")
public class Et9962ServiceImpl extends CmnAbstractServiceImpl implements Et9962Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public void insertEt9962(LinkedHashMap paramMap) throws Exception {
        // 데이터 등록
        egovLogger.debug("********** 데이터등록[Et9962] **********");
        egovLogger.debug("paramMap : " + paramMap.toString());
       sqlSession.insert("sfmes.sqlmap.et.insertEt9962", paramMap);     
    }

    @Override
    public void updateEt9962(LinkedHashMap paramMap) throws Exception {
         // 데이터 수정
        egovLogger.debug("********** 데이터수정[Et9962] **********");
        egovLogger.debug("paramMap : " + paramMap.toString());
        sqlSession.update("sfmes.sqlmap.et.updateEt9962", paramMap);
    }

    @Override
    public List<?> selectEt9962(LinkedHashMap paramMap) throws Exception {
          // 데이터 조회
        egovLogger.debug("********** 데이터조회[Et9962] **********");
        egovLogger.debug("paramMap : " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.et.selectEt9962", paramMap);
    }
}
