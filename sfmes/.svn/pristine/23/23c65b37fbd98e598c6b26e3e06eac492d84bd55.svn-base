package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.pd.service.Pd2150Service;

/**
* @Class Name : Pd2150ServiceImpl.java
* @Description : Pd2150Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.09.21   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.09.22
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2150Service")
public class Pd2150ServiceImpl extends CmnAbstractServiceImpl implements Pd2150Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public List<?> selectPd2150List(LinkedHashMap paramMap) throws Exception {
    	
    	String wkRe = (String)paramMap.get("WK_RE");
    	
    	if(wkRe.equals("01")) {
    		return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2150_Rpt_Mfs_01",paramMap);
    	} else {
    		return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2150_Rpt_Mfs_02",paramMap);
    	}
    }

}
