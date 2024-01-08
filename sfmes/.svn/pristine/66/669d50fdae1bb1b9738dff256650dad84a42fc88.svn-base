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
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.Co1030Service;

/**
 * @Class Name : Co1030ServiceImpl.java
 * @Description : 창고등록
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
@Service("Co1030Service")
public class Co1030ServiceImpl extends CmnAbstractServiceImpl implements Co1030Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

	/**
	 * 창고 신규 등록한다.
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void insertCo1030(LinkedHashMap paramMap) throws Exception {
		//유효성체크
		List<Map<String, Object>> result = sqlSession.selectList("sfmes.sqlmap.co.selectCO1030_PRCHK",paramMap);
		if(result.size() >0) {
			throw infoException("해당 사업장에 중복된 창고코드가 존재합니다.");
		}			   
		//창고등록
		sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_WHSE",paramMap);
        //창고등록변경이력
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_L_WHSE",paramMap);
	}
	
	/**
	 * 창고 수정
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void updateCo1030(LinkedHashMap paramMap) throws Exception {
		
		//창고수정
		sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_WHSE",paramMap);
		//창고수정변경이력
		sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_L_WHSE",paramMap);  
	}
	
	/**
     * @return 
	 * 창고내역 데이터를 상세조회한다.
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	@Override
	public List<?> selectCo1030List01(LinkedHashMap paramMap) {
		return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_CO_M_WHSE",paramMap);
	}
	
	/**
     * @return 
     * 창고 등록시 사업장주소 조회
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */
    @Override
    public List<?> selectCo1030List02(LinkedHashMap paramMap) {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo1030_BZPL_C_ADR",paramMap);
    }
}
