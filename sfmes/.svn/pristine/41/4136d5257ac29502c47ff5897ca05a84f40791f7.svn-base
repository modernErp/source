package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy9000Service;

/**
 * @Class Name  : Sy9000ServiceImpl.java
 * @Description : Sy9000Service Class
 * @Modification Information
 * @
 * @  수정일             수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2021.01.28   장경석            최초생성
 *
 * @author (주)모든솔루션
 * @since 2021.01.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Sy9000Service")
public class Sy9000ServiceImpl extends CmnAbstractServiceImpl implements Sy9000Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * CUD용 SQL 수행 서비스
     * 모든viewer 조회 (SY9999.mvf)
     */  
    @Override
    public void saveSy9000(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug(":::::[모든viewer Dynamic Query]:::::" + paramMap.get("SQL_STR"));
        
        sqlSession.update("sfmes.sqlmap.sy.updateSy9000_MDNV", paramMap);    	
    	return;
    }
    
    /**
     * 조회용 SQL 수행 서비스
     */
    @Override
    public List<?> selectSe9000(LinkedHashMap paramMap) {
        egovLogger.debug(":::::[모든viewer Dynamic Query]:::::" + paramMap.get("SQL_STR"));
        
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy9000_MDNV",paramMap);
    }
}
