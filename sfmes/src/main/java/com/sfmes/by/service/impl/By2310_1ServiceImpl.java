package com.sfmes.by.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.by.service.By2310_1Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;

/**
* @Class Name : By2310_1ServiceImpl.java
* @Description : 수주분 생산 재료 소요내역 조회
* @Modification Information
* @
* @  수정일                수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2022.10.21   김주경               최초생성
*
* @author (주)모든솔루션
* @since 2022.10.21
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("By2310_1Service")
public class By2310_1ServiceImpl extends CmnAbstractServiceImpl implements By2310_1Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
       
    // 수주내역 조회
    @Override
    public List<?> selectBy2310_1_01(LinkedHashMap paramMap) throws Exception {
       egovLogger.debug("********** 수주내역조회[BY2310_1] **********");
       egovLogger.debug("paramMap : " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2310_1_01", paramMap);
    }

    // 소요 재료 내역 조회
    @Override
    public List<?> selectBy2310_1_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("********** 소요재료내역조회[BY2310_1] **********");
        egovLogger.debug("paramMap : " +paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2310_1_02", paramMap);
    }

    // 발주내역 조회
    @Override
    public List<?> selectBy2310_1_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("********** 발주내역조회[BY2310_1] **********");
        egovLogger.debug("paramMap : " +paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2310_1_03", paramMap);
    }

    // 팝업창 조회
    @Override
    public List<?> selectBy2310_1p01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("********** 팝업창조회[BY2310_1] **********");
        egovLogger.debug("paramMap : " +paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2310_1p01", paramMap);
    }

}
