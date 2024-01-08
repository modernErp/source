package com.sfmes.by.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.by.service.By2120Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;

/**
 * @Class Name  : By2120Service.java
 * @Description : By2120Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.09.16   김지혜     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("By2120Service")
public class By2120ServiceImpl extends CmnAbstractServiceImpl implements By2120Service {

    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
  //발주대비(미)입고현황 조회
    @Override
    public List<?> selectBy2120(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 발주대비(미)입고현황 조회[BY2120] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy2120", paramMap);
    }

}
