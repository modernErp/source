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
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy3010Service;

/**
 * @Class Name : Sy3010ServiceImpl.java
 * @Description : 연계전문등록
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.01  손용찬      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Sy3010Service")
public class Sy3010ServiceImpl extends CmnAbstractServiceImpl implements Sy3010Service {
	
    @Autowired
    private SqlSessionTemplate sqlSession;

	/**
	 * 연계전문 데이터를 신규 등록한다.
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void insertSy3010(LinkedHashMap<String, Object> paramMap, List<Map<String, Object>> paramList01, List<Map<String, Object>> paramList02) throws Exception {
        //유효성체크
	    String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.sy.selectSy3010Check", paramMap);
	    
	    if(!"OK".equals(resultMsg)) {
	        throw infoException(resultMsg);
	    }
	    //연계기본정보 등록
		sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_M_INTF",paramMap);
		
		//요청연계컬럼 등록
		for(Map<String, Object> paramMap1 : paramList01) {
			if(paramMap1.get("_status_").equals("+")) {
				sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_D_INTF", paramMap1);				
			}
		}
		
		//응답연계컬럼 등록
		for(Map<String, Object> paramMap2 : paramList02) {
			if(paramMap2.get("_status_").equals("+")) {
				sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_D_INTF", paramMap2);				
			}		
		}
	}
    
	/**
	 * 연계전문 리스트 데이터를 상세조회한다.
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	@Override
	public List<?> selectSy3010One(LinkedHashMap paramMap) {
		return sqlSession.selectList("sfmes.sqlmap.sy.selectSy3010List",paramMap);
	}
	
	/**
	 * 연계전문 폼 데이터를 상세조회한다.
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	@Override
	public List<?> selectSy3010List(LinkedHashMap paramMap) {
		return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SY_M_INTF",paramMap);
	}
	
	/**
	 * 연계전문 데이터를 수정한다.
	 * @param paramMap - 수정할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void updateSy3010(LinkedHashMap<String, Object> paramMap, List<Map<String, Object>> paramList01, List<Map<String, Object>> paramList02) throws Exception {
		//연계기본정보 수정
		sqlSession.update("sfmes.sqlmap.tb.update_TB_SY_M_INTF",paramMap);
		
		//요청연계컬럼 수정
		for(Map<String, Object> paramMap1 : paramList01) {
			if(paramMap1.get("_status_").equals("+")) {
				sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_D_INTF", paramMap1);
			} else if(paramMap1.get("_status_").equals("*")) {
				sqlSession.update("sfmes.sqlmap.tb.update_TB_SY_D_INTF", paramMap1);
			} else if(paramMap1.get("_status_").equals("-")) {
				sqlSession.delete("sfmes.sqlmap.tb.delete_TB_SY_D_INTF", paramMap1); 
			}
		}
		
		//응답연계컬럼 수정
		for(Map<String, Object> paramMap2 : paramList02) {
			if(paramMap2.get("_status_").equals("+")) {
				sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_D_INTF", paramMap2);
			} else if(paramMap2.get("_status_").equals("*")) {
				sqlSession.update("sfmes.sqlmap.tb.update_TB_SY_D_INTF", paramMap2);
			} else if(paramMap2.get("_status_").equals("-")) {
				sqlSession.delete("sfmes.sqlmap.tb.delete_TB_SY_D_INTF", paramMap2); 
			}
		}
	}

}
