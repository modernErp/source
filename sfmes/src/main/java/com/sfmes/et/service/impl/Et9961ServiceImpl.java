package com.sfmes.et.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.et.service.Et9961Service;
import com.sfmes.et.service.Et9970Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Et9961ServiceImpl.java
 * @Description : Et9961ServiceImpl Class
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2022.09.16   김주경     최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Et9961Service")
public class Et9961ServiceImpl extends CmnAbstractServiceImpl implements Et9961Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<?> selectEt9961List(LinkedHashMap paramMap) throws Exception {
        //사용자내역(사용자찾기팝업)조회
        return sqlSession.selectList("sfmes.sqlmap.et.selectEt9961_01",paramMap);
        
    }
    
  
}
