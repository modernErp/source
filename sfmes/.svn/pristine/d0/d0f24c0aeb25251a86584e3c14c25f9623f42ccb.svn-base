package com.sfmes.by.service.impl;

import java.util.LinkedHashMap;
import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.by.service.By2130Service_4;
import com.sfmes.cm.web.CmnAbstractServiceImpl;

@Service("By2130Service_4")
public class By2130ServiceImpl_4 extends CmnAbstractServiceImpl implements By2130Service_4 {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    
    @Override
    public List<?> select01By2130List(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주분 생산 수주 내역[BY2130] *********");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.by.select01By2130_4", paramMap);
    }


    @Override
    public List<?> select02By2130List(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주분 생산 수요 재료 내역[BY2130] *********");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.by.select02By2130_4", paramMap);
    }


    @Override
    public List<?> select03By2130List(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주분 발주수량[BY2130] *********");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.by.select03By2130_4", paramMap);
    }


    @Override
    public List<?> select04By2130List(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 팝업[BY2130_04P01] *********");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.by.select04By2130_4", paramMap);
    }

    
    
}
