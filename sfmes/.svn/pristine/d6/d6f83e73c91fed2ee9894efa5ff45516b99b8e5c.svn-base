package com.sfmes.mi.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.mi.service.Mi3130Service;

/**
 * @Class Name  : Mi3130ServiceImpl.java
 * @Description : 물품별매입현황 조회
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

@Service("Mi3130Service")
public class Mi3130ServiceImpl extends CmnAbstractServiceImpl implements Mi3130Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    //사업장별매출현황
    @Override
    public List<?> selectMi3130_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 물품별매입현황[MI3130] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());          
        return sqlSession.selectList("sfmes.sqlmap.mi.selectMi3130_01", paramMap);
    }
}
