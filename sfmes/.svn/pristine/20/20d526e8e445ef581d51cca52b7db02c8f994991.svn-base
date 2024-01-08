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
import com.sfmes.ge.service.Ge2035Service;

/**
 * @Class Name : Ge2035ServiceImpl.java
 * @Description : 민원사후 처리내역 조회
 * @Modification Information
 * @
 * @  수정일     수정자      수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.11.19  유승현      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.11.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Ge2035Service")
public class Ge2035ServiceImpl extends CmnAbstractServiceImpl implements Ge2035Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    //민원사후 처리내역 조회
    @Override
    public List<?> selectGe2035_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************* 민원사후 처리내역 조회 [selectGe2035_01] ****************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe2035_01", paramMap);       
    }
    
    //민원사후 처리내역 상세조회
    @Override
    public List<?> selectGe2035_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************* 민원사후 처리내역 상세조회 [selectGe2035_02] ****************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe2035_02", paramMap);
    }
}
