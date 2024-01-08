package com.sfmes.ge.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.ge.service.Ge2015Service;

/**
 * @Class Name : Ge2015ServiceImpl.java
 * @Description : 민원접수내역 조회
 * @Modification Information
 * @
 * @  수정일     수정자      수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.11.05  유승현      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.11.05
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Ge2015Service")
public class Ge2015ServiceImpl extends CmnAbstractServiceImpl implements Ge2015Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 공통 서비스 선언
	@Resource(name = "CommonService")
	private CommonService commonService;
	
    //민원기본내역 조회
    @Override
    public List<?> selectGe2015_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************* 민원기본내역 조회 [GE2015] ****************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe2015_01", paramMap);
    }
    
    //민원상세내역 조회
    @Override
    public List<?> selectGe2015_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************* 민원상세내역 조회 [GE2015] ****************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe2015_02", paramMap);
    }	

}
