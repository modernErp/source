package com.sfmes.se.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se6025Service;

/**
 * @Class Name : Se6025ServiceImpl.java
 * @Description : 제품출고내역
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.09.11  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se6025Service")
public class Se6025ServiceImpl extends CmnAbstractServiceImpl implements Se6025Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    //전표별출고기본내역
    @Override
    public List<?> selectSe6025_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표별출고기본내역[SE6025] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe6025_01", paramMap);
    }

    //전표별출고상세내역
    @Override
    public List<?> selectSe6025_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표별출고상세내역[SE6025] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3010_02", paramMap);
    }
    
    //전표-물품별출고내역
    @Override
    public List<?> selectSe6025_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표-물품별출고내역[SE6025] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe6025_02", paramMap);
    }
}
