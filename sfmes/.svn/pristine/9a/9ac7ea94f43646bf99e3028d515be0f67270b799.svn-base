package com.sfmes.et.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.et.service.Et9989Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Et9989ServiceImpl.java
 * @Description : 매입내역
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2022.10.17  성명건   최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.10.17
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Et9989Service")
public class Et9989ServiceImpl extends CmnAbstractServiceImpl implements Et9989Service {
                            
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    //전표별매입기본내역
    @Override
    public List<?> selectEt9989_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표별매입기본내역[Et9989] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.et.selectEt9989_01", paramMap);
    }

    //전표별매입상세내역
    @Override
    public List<?> selectEt9989_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표별매입상세내역[Et9989] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.et.selectEt9989_02", paramMap);
    }
    
    //전표-물품별매입내역
    @Override
    public List<?> selectEt9989_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표-물품별매입내역[Et9989] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.et.selectEt9989_03", paramMap);
    }
}
