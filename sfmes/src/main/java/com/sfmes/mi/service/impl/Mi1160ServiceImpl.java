package com.sfmes.mi.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.mi.service.Mi1160Service;

/**
 * @Class Name  : Mi1160ServiceImpl.java
 * @Description : 사업장별물품순위현황 조회
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.11.19  곽환용  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Mi1160Service")
public class Mi1160ServiceImpl extends CmnAbstractServiceImpl implements Mi1160Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    //사업장별물품순위현황
    @Override
    public List<?> selectMi1160_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 사업장별물품순위현황[MI1160] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());          
        return sqlSession.selectList("sfmes.sqlmap.mi.selectMi1160_01", paramMap);
    }
}
