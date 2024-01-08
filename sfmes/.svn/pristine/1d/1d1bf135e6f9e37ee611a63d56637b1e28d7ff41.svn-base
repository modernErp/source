package com.sfmes.se.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se3110Service;

/**
 * @Class Name : Se3110ServiceImpl.java
 * @Description : 매출내역
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.10.28  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se3110Service")
public class Se3110ServiceImpl extends CmnAbstractServiceImpl implements Se3110Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    //거래처별매출현황
    @Override
    public List<?> selectSe3110_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 거래처별매출현황[SE3110] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3110_01", paramMap);
    }

    //물품별매출현황
    @Override
    public List<?> selectSe3110_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 물품별매출현황[SE3110] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3110_02", paramMap);
    }
}
