package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd4025Service;

/**
* @Class Name : Pd4025ServiceImpl.java
* @Description : Pd4025Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.28   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.22
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd4025Service")
public class Pd4025ServiceImpl extends CmnAbstractServiceImpl implements Pd4025Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    
    // 수탁가공재료 인수내역 조회
    @Override
    public List<?> selectPd4025List(LinkedHashMap paramMap) throws Exception {
    	return sqlSession.selectList("sfmes.sqlmap.pd.selectPd4025List01", paramMap);
    }

}
