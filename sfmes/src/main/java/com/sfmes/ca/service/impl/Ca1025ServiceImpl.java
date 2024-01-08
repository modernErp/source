package com.sfmes.ca.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.ca.service.Ca1025Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Ca1025ServiceImpl.java
 * @Description : 외상매출금원장계수정정내역
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.19  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Ca1025Service")
public class Ca1025ServiceImpl extends CmnAbstractServiceImpl implements Ca1025Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    // 외상매출금원장계수정정내역 조회
    @Override
    public List<?> selectCa1025List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.ca.selectCa1025List", paramMap);
    }

    // 외상매출금원장계수기초등록내역 조회
    @Override
    public List<?> selectCa1025List_2(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.ca.selectCa1025List_2", paramMap);
    }
}
