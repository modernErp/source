package com.sfmes.mi.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.mi.service.Mi1120Service;

/**
 * @Class Name  : Mi1120ServiceImpl.java
 * @Description : 기간대비매출현황 조회
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.11.12  곽환용  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Mi1120Service")
public class Mi1120ServiceImpl extends CmnAbstractServiceImpl implements Mi1120Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    //기간대비매출현황
    @Override
    public List<?> selectMi1120_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 기간대비매출현황[MI1120] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());          
        return sqlSession.selectList("sfmes.sqlmap.mi.selectMi1120_01", paramMap);
    }
}
