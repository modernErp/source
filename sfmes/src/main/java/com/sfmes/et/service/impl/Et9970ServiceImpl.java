package com.sfmes.et.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.et.service.Et9970Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Et9970ServiceImpl.java
 * @Description : 매입내역
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2022.01.11  김상철   최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.01.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Et9970Service")
public class Et9970ServiceImpl extends CmnAbstractServiceImpl implements Et9970Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    //전표별매입기본내역
    @Override
    public List<?> selectEt9970_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표별매입기본내역[Et9970] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.et.selectEt9970_01", paramMap);
    }
    
  
}
