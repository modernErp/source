/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sfmes.co.service.impl;

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
import com.sfmes.co.service.Co1035Service;

/**
 * @Class Name : Co1035ServiceImpl.java
 * @Description : 창고내역
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.15  손용찬      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Co1035Service")
public class Co1035ServiceImpl extends CmnAbstractServiceImpl implements Co1035Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * @return 
	 * 창고내역 조회
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	@Override
	public List<?> selectCo1035List(LinkedHashMap paramMap) {
		return sqlSession.selectList("sfmes.sqlmap.co.selectCo1035List",paramMap);
	}
	
	/**
     * @return 
	 * 창고찾기팝업내역 조회
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	@Override
	public List<?> selectCo1030ListP01(LinkedHashMap paramMap) {
		return sqlSession.selectList("sfmes.sqlmap.co.selectCo1030ListP01",paramMap);
	}
	
	/**
     * @return 
	 * 창고변경이력내역 조회
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	@Override
	public List<?> selectCo1035ListP01(LinkedHashMap paramMap) {
		return sqlSession.selectList("sfmes.sqlmap.co.selectCo1035ListP01",paramMap);
	}
}
