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
import com.sfmes.sm.service.Sm2230Service;

/**
 * @Class Name : Sm2230ServiceImpl.java
 * @Description : 외상매입금잔액장조회
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2022.01.07   김상철      최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.01.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Sm2230Service")
public class Sm2230ServiceImpl extends CmnAbstractServiceImpl implements Sm2230Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    // 출고처원장조회
    @Override
    public List<?> selectSm2230List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.selectSm2230List01",paramMap);
    }
    
 // 출고처원장조회
    @Override
    public List<?> selectSm2230List2(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.selectSm2230List02",paramMap);
    }
}
    