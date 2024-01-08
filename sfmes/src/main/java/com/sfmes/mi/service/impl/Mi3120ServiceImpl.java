package com.sfmes.mi.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.mi.service.Mi3120Service;

/**
 * @Class Name  : Mi3120ServiceImpl.java
 * @Description : 거래처별매입현황 조회
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.12.11  곽환용  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Mi3120Service")
public class Mi3120ServiceImpl extends CmnAbstractServiceImpl implements Mi3120Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    //사업장별매출현황
    @Override
    public List<?> selectMi3120_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 거래처별매입현황[MI3120] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());          
        return sqlSession.selectList("sfmes.sqlmap.mi.selectMi3120_01", paramMap);
    }
}
