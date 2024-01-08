package com.sfmes.et.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.et.service.Et9964Service;

/**
 * @Class Name : Et9964ServiceImpl.java
 * @Description : Et9964ServiceImpl Class
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2022.10.11   강동현     최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.10.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Et9964Service")
public class Et9964ServiceImpl extends CmnAbstractServiceImpl implements Et9964Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<?> selectEt9964List(LinkedHashMap paramMap) throws Exception {
        //사용자내역(사용자찾기팝업)조회
        return sqlSession.selectList("sfmes.sqlmap.et.selectEt9964_01",paramMap);
        
    }
    
  
}
