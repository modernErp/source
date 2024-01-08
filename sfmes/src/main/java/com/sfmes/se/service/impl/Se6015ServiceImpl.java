package com.sfmes.se.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se6015Service;

/**
 * @Class Name : Se6015ServiceImpl.java
 * @Description : 출고지시내역
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.10  박지환   최초생성   
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se6015Service")
public class Se6015ServiceImpl extends CmnAbstractServiceImpl implements Se6015Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;        

    //출고지시기본내역
    @Override
    public List<?> selectSe6015_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시기본내역[SE6015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe6015_01", paramMap);
    }
    
    //출고지시상세내역
    @Override
    public List<?> selectSe6015_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시상세내역[SE6015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe6015_02", paramMap);
    }
}
