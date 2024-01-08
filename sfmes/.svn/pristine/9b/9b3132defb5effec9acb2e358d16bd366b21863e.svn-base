package com.sfmes.ca.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.ca.service.Ca2025Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Ca2025ServiceImpl.java
 * @Description : 기타미수금원장계수정정내역
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
@Service("Ca2025Service")
public class Ca2025ServiceImpl extends CmnAbstractServiceImpl implements Ca2025Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    // 기타미수금원장계수정정내역 조회
    @Override
    public List<?> selectCa2025List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.ca.selectCa2025List", paramMap);
    }

    // 기타미수금원장계수정정내역 조회
    @Override
    public List<?> selectCa2025List_2(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.ca.selectCa2025List_2", paramMap);
    }
}
