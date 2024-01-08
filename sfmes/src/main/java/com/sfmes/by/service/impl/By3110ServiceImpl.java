package com.sfmes.by.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.by.service.By3110Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : By3110ServiceImpl.java
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

@Service("By3110Service")
public class By3110ServiceImpl extends CmnAbstractServiceImpl implements By3110Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    //거래처별매입현황
    @Override
    public List<?> selectBy3110_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 거래처별매입현황[BY3110] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy3110_01", paramMap);
    }

    //물품별매입현황
    @Override
    public List<?> selectBy3110_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 물품별매입현황[BY3110] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy3110_02", paramMap);
    }
}
