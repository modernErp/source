package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd3110Service;

/**
* @Class Name : Pd3110ServiceImpl.java
* @Description : Pd3110Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.12.15   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.12.15
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd3110Service")
public class Pd3110ServiceImpl extends CmnAbstractServiceImpl implements Pd3110Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    // 기간별배부차액집계내역 총집계조회
    @Override
    public List<?> selectPd3110_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd3110_01",paramMap);
    }
    
    // 기간별배부차액집계내역조회
    @Override
    public List<?> selectPd3110_02(LinkedHashMap paramMap) throws Exception {
    	return sqlSession.selectList("sfmes.sqlmap.pd.selectPd3110_02",paramMap);
    }
}
