package com.sfmes.sm.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Sm1045Service;

/**
 * @Class Name : Sm1045ServiceImpl.java
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

@Service("Sm1045Service")
public class Sm1045ServiceImpl extends CmnAbstractServiceImpl implements Sm1045Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;        

    //출고지시기본내역
    @Override
    public List<?> selectSm1045_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시기본내역[SM1045] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.sm.selectSm1045_01", paramMap);
    }
    
    //출고지시상세내역
    @Override
    public List<?> selectSm1045_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시상세내역[SM1045] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.sm.selectSm1045_02", paramMap);
    }
}
