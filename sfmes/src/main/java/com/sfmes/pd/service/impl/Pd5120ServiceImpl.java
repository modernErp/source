package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd5120Service;

/**
* @Class Name : Pd5120ServiceImpl.java
* @Description : Pd5120Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.11.24   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.11.24
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd5120Service")
public class Pd5120ServiceImpl extends CmnAbstractServiceImpl implements Pd5120Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    // 위탁가공 출고 내역 조회
    @Override
    public List<?> selectPd5120List_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd5120_01",paramMap);
    }

}
