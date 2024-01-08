package com.sfmes.by.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.by.service.By2310_2Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;


/**
 * @Class Name : By2310_2ServiceImpl.java
 * @Description : 매입내역
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2022.10.25   성명건  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("By2310_2Service")
public class By2310_2ServiceImpl extends CmnAbstractServiceImpl implements By2310_2Service {
  
    @Autowired
    private SqlSessionTemplate sqlSession;
    //수주별내역조회
    @Override
    public List<?> selectBy2310_2_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주별내역조회[BY2310_2] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2310_2_01", paramMap);
    }

    //소요재료내역조회
    @Override
    public List<?> selectBy2310_2_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 소요재료내역조회[BY2310_2] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2310_2_02", paramMap);
    }
    
    //Popup창 조회
    @Override
    public List<?> selectBy2310_2_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ Popup창 조회[BY2310_2] *********");
        egovLogger.debug("paramMap: " +paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2310_2_03", paramMap);
    }

    @Override
    public List<?> selectBy2310_2_04(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 발주화면[BY2310_2] *********");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2310_2_04", paramMap);
    }

}
