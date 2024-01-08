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

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.Co1020Service;

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
@Service("Co1020Service")
public class Co1020ServiceImpl extends CmnAbstractServiceImpl implements Co1020Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

	/**
	 * 사업장환결설정 등록한다.
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void insertCo1020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {			
		//사업장환경설정 등록
		sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_BZPL_ENV",paramMap);
		
		//인정감모율 유효성체크
		for(Map<String, Object> map : paramList) {		
			if(map.get("_status_").equals("+")) {
				List<Map<String, Object>> result = sqlSession.selectList("sfmes.sqlmap.co.selectCo1020_RCGDWD_PKCHK", map);
				if(result.size() >0) {
					throw infoException("동일한 물품유형이 존재합니다. 확인 후 다시 처리하세요.");
				}
				//인정감모율 등록
				sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_RCGDWD",map);
			} else if(map.get("_status_").equals("*")) {
				//인정감모율 수정
				sqlSession.insert("sfmes.sqlmap.tb.update_TB_CO_M_RCGDWD",map);
			} else if(map.get("_status_").equals("-")) {
				//인정감모율 삭제
				sqlSession.insert("sfmes.sqlmap.tb.delete_TB_CO_M_RCGDWD",map);
			}
		}
	}
	
	/**
	 * 사업장환경설정 수정
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void updateCo1020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {				
		
		//사업장환경설정 수정
		sqlSession.insert("sfmes.sqlmap.tb.update_TB_CO_M_BZPL_ENV",paramMap);
		
		//사업장환경설정 인정감모율 수정
		for(Map<String, Object> map : paramList) {		
			if(map.get("_status_").equals("+")) {
				//인정감모율 유효성체크
				List<Map<String, Object>> result = sqlSession.selectList("sfmes.sqlmap.co.selectCo1020_RCGDWD_PKCHK", map);
				if(result.size() >0) {
					throw infoException("동일한 물품유형이 존재합니다. 확인 후 다시 처리하세요.");
				}
				//인정감모율 등록
				sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_RCGDWD",map);				
			} else if(map.get("_status_").equals("*")) {
				sqlSession.insert("sfmes.sqlmap.tb.update_TB_CO_M_RCGDWD",map);
			} else if(map.get("_status_").equals("-")) {
				sqlSession.insert("sfmes.sqlmap.tb.delete_TB_CO_M_RCGDWD",map);
			}
		}
		
		//사업장변경이력 등록
		sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_L_BZPL_ENV",paramMap); 
	}
	
	/**
     * @return 
	 * 사업장환경설정 조회
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	@Override
	public List<?> selectCo1020List01(LinkedHashMap paramMap) {
		return sqlSession.selectList("sfmes.sqlmap.co.selectCo1020List01",paramMap);
	}
	
	/**
     * @return 
	 * 사업장환경설정 변경이력 조회
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	@Override
	public List<?> selectCo1020List02(LinkedHashMap paramMap) {
		return sqlSession.selectList("sfmes.sqlmap.co.selectCo1020ListP01",paramMap);
	}
	
	/**
     * @return 
	 * 사업장환경설정 인정감모율 조회
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	@Override
	public List<?> selectCo1020List03(LinkedHashMap paramMap) {
		return sqlSession.selectList("sfmes.sqlmap.co.selectCo1020List02",paramMap);
	}
}
