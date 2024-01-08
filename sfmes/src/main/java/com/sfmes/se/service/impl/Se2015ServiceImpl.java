package com.sfmes.se.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.se.service.Se2015Service;

/**
 * @Class Name : Se2015ServiceImpl.java
 * @Description : 수주내역조회
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.30  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se2015Service")
public class Se2015ServiceImpl extends CmnAbstractServiceImpl implements Se2015Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    //전표별 수주기본내역 조회
    @Override
    public List<?> selectSe2015_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표별 수주기본내역조회[SE2015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe2015_01", paramMap);
    }

    //전표별 수주상세내역 조회
    @Override
    public List<?> selectSe2015_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표별 수주상세내역조회[SE2015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe2015_02", paramMap);
    }

    //물품별 수주내역 조회
    @Override
    public List<?> selectSe2015_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 물품별 수주내역조회[SE2015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe2015_03", paramMap);
    }
}
