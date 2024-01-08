package com.sfmes.ca.service.impl;


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
import com.sfmes.ca.service.Ca5110Service;

/**
 * @Class Name : Ca5110ServiceImpl.java
 * @Description : 기타미지급금거래내역조회
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.14  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.14
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Ca5110Service")
public class Ca5110ServiceImpl extends CmnAbstractServiceImpl implements Ca5110Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 공통 서비스 선언
	@Resource(name = "CommonService")
	private CommonService commonService;

	// 기타미지급금거래내역조회
	@Override
	public List<?> selectCa5110List(LinkedHashMap paramMap) throws Exception {
		 return sqlSession.selectList("sfmes.sqlmap.ca.selectCa5110List",paramMap);
	}
}
    