package com.sfmes.by.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.by.service.By2110Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;

/**
 * @Class Name  : By2110Service.java
 * @Description : By2110Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.09.10   김지혜     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */


@Service("By2110Service")
public class By2110ServiceImpl extends CmnAbstractServiceImpl implements By2110Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    //발주현황(거래처)거래처별내역
    @Override
    public List<?> selectBy2110_trpl_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 발주현황거래처별내역[BY2110_01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2110_trpl_01", paramMap);
    }

    //발주현황(거래처)전표별내역
    @Override
    public List<?> selectBy2110_trpl_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 발주현황전표별내역[BY2110_02] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2110_trpl_02", paramMap);
    }

  //발주현황(거래처)물품별내역
    @Override
    public List<?> selectBy2110_trpl_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 발주현황전표별내역[BY2110_03] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2110_trpl_03", paramMap);
    }

    //발주현황(물품) 물품별내역
    @Override
    public List<?> selectBy2110_gds_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 발주현황전표별내역[BY2110_04] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2110_gds_01", paramMap);
    }

  //발주현황(물품) 거래처별내역
    @Override
    public List<?> selectBy2110_gds_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 발주현황전표별내역[BY2110_05] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2110_gds_02", paramMap);
    }

    @Override
    public List<?> selectBy2110_gds_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 발주현황전표별내역[BY2110_06] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2110_gds_03", paramMap);
    }

}
