package com.sfmes.by.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.by.service.By2015Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;

/**
 * @Class Name : By2015ServiceImpl.java
 * @Description : 발주내역조회
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.28  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("By2015Service")
public class By2015ServiceImpl extends CmnAbstractServiceImpl implements By2015Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    //전표별 발주기본내역 조회
    @Override
    public List<?> selectBy2015_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표별 발주기본내역조회[BY2015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2015_01", paramMap);
    }

    //전표별 발주상세내역 조회
    @Override
    public List<?> selectBy2015_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표별 발주상세내역조회[BY2015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2015_02", paramMap);
    }

    //물품별 발주내역 조회
    @Override
    public List<?> selectBy2015_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 물품별 발주내역조회[BY2015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2015_03", paramMap);
    }
}
