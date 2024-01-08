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
package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy3030Service;

/**
 * @Class Name : Sy3030ServiceImpl.java
 * @Description : 연계전문송수신내역
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.09  손용찬     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.09
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Sy3030Service")
public class Sy3030ServiceImpl extends CmnAbstractServiceImpl implements Sy3030Service {
	
    @Autowired
    private SqlSessionTemplate sqlSession;

	/**
	 * 연계전문송수신내역 데이터를 상세조회한다.
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	@Override
	public List<?> selectSy3030One(LinkedHashMap paramMap) {
		return sqlSession.selectList("sfmes.sqlmap.sy.selectSy3030List",paramMap);
	}

}
