package com.sfmes.by.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.by.service.By3015Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : By3015ServiceImpl.java
 * @Description : 매입내역
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.09.10  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("By3015Service")
public class By3015ServiceImpl extends CmnAbstractServiceImpl implements By3015Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    //전표별매입기본내역
    @Override
    public List<?> selectBy3015_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표별매입기본내역[BY3015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy3015_01", paramMap);
    }

    //전표별매입상세내역
    @Override
    public List<?> selectBy3015_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표별매입상세내역[BY3015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy3010_02", paramMap);
    }
    
    //전표-물품별매입내역
    @Override
    public List<?> selectBy3015_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표-물품별매입내역[BY3015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy3015_02", paramMap);
    }
}
