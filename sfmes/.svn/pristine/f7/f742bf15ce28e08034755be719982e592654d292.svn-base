package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd5025Service;

/**
* @Class Name : Pd5025ServiceImpl.java
* @Description : Pd5025Service Class
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

@Service("Pd5025Service")
public class Pd5025ServiceImpl extends CmnAbstractServiceImpl implements Pd5025Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    
    // 위탁가공재료 출고내역 조회
    @Override
    public List<?> selectPd5025List(LinkedHashMap paramMap) throws Exception {
    	return sqlSession.selectList("sfmes.sqlmap.pd.selectPd5025List01", paramMap);
    }

}
