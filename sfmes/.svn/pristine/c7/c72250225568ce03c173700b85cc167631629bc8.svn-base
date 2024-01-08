package com.sfmes.sm.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Sm2210Service;

/**
 * @Class Name : Sm2210ServiceImpl.java
 * @Description : 외상매입금잔액장조회
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2021.12.28   김상철      최초생성
 *
 * @author (주)모든솔루션
 * @since 2021.12.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Sm2210Service")
public class Sm2210ServiceImpl extends CmnAbstractServiceImpl implements Sm2210Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    // 입금처원장조회
    @Override
    public List<?> selectSm2210List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.selectSm2210List01",paramMap);
    }
    
    // 입금처원장조회
    @Override
    public List<?> selectSm2210List2(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.selectSm2210List02",paramMap);
    }
}
    