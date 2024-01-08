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
package com.sfmes.ge.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.ge.service.Ge1030Service;

/**
 * @Class Name : Co1020ServiceImpl.java
 * @Description : 사업장환경설정 등록
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.22  손용찬      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Ge1030Service")
public class Ge1030ServiceImpl extends CmnAbstractServiceImpl implements Ge1030Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

	/**
	 * 자료실 등록
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public String insertGe1030(LinkedHashMap paramMap) throws Exception {			
	    String DOC_SQNO = sqlSession.selectOne("sfmes.sqlmap.ge.selectGe1030_DOC_SQNO", paramMap);
		
	    paramMap.put("DOC_SQNO", DOC_SQNO);
	    sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_DOC", paramMap);
		
		return DOC_SQNO;
	}
	
	/**
	 * 자료실 수정
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public String updateGe1030(LinkedHashMap paramMap) throws Exception {				
		String DOC_SQNO = (String)paramMap.get("DOC_SQNO");
		
		//사업장환경설정 수정
		sqlSession.insert("sfmes.sqlmap.tb.update_TB_CO_M_DOC", paramMap);
		
		return DOC_SQNO;
	}
	
	/**
     * @return 
	 * 자료실찾기 팝업 내역조회
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	@Override
	public List<?> selectGe1030ListP01(LinkedHashMap paramMap) {		
		//자료실 내역 조회
		return sqlSession.selectList("sfmes.sqlmap.ge.selectGe1030ListP01",paramMap);
	}
	
	/**
     * @return 
	 * 자료실 상세내역 조회
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	@Override
	public List<?> selectGe1030List01(LinkedHashMap paramMap) {		
		//조회시 조회건수 증가처리
		sqlSession.update("sfmes.sqlmap.ge.updateGe1030_InqCn", paramMap);	
		
		//자료실 내역 조회
		return sqlSession.selectList("sfmes.sqlmap.ge.selectGe1030List01",paramMap);
	}
}
