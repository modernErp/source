package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd5015Service;

/**
* @Class Name : Pd5015ServiceImpl.java
* @Description : Pd5015Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.11.27   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.11.27
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd5015Service")
public class Pd5015ServiceImpl extends CmnAbstractServiceImpl implements Pd5015Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    // 위탁가공 접수 내역 조회
    @Override
    public List<?> selectPd5015List_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd5015_01",paramMap);
    }

}
