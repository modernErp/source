package com.sfmes.mi.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.mi.service.Mi1150Service;

/**
 * @Class Name  : Mi1150ServiceImpl.java
 * @Description : 거래처별물품별매출현황 조회
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.11.13  곽환용  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Mi1150Service")
public class Mi1150ServiceImpl extends CmnAbstractServiceImpl implements Mi1150Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    //거래처별물품별매출현황
    @Override
    public List<?> selectMi1150_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 거래처별물품별매출현황[MI1150] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());          
        return sqlSession.selectList("sfmes.sqlmap.mi.selectMi1150_01", paramMap);
    }
}
