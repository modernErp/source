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
package com.sfmes.et.service.impl;

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
import com.sfmes.et.service.Et0010Service;

/**
 * @Class Name : Et0010ServiceImpl.java
 * @Description : 테스트 예제 비지니스 로직
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.05.22  이철홍      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.05.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Et0010Service")
public class Et0010ServiceImpl extends CmnAbstractServiceImpl implements Et0010Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 공통 서비스 선언
	@Resource(name = "CommonService")
	private CommonService commonService;
    
	/**
	 * 테스트 데이터를 신규 등록한다.
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void insertEt0010(LinkedHashMap paramMap) throws Exception {
		System.out.println("== insertEt0010 종료 paramMap=="+ paramMap);
		
		// 채번 서비스를 호출한다.
		String seqNo = commonService.getGvno("9999","TB_CO_M_GVNO","1234567890", "20200624", 1);
		
		System.out.println("== insertEt0010 디버그seqNo == " + seqNo);
		
		// if(1==1) throw infoException("사용자 안내 메시지 테스트");
//		paramMap.put("USRID","test1");
		// sqlSession.insert("sfmes.sqlmap.tb.insert_TB_TE_M_TEST_USR",paramMap);
		
		// 사전 정합성 체크를 한다.
		String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.et.selectEt0010Check",paramMap);
		
		if(!"OK".equals(resultMsg)) {
			throw infoException(resultMsg);
		}
		
		commonService.testError(paramMap);
		
		if(1==1) throw infoException("서비스 연계 메시지 테스트");
		
//		System.out.println("== insertEt0010 디버그2 ==");
//		paramMap.put("USRID","test2");
//		sqlSession.insert("sfmes.sqlmap.tb.insert_TB_TE_M_TEST_USR",paramMap);
//		System.out.println("== insertEt0010 디버그3 ==");
//		paramMap.put("USRID","test3");
//		sqlSession.insert("sfmes.sqlmap.tb.insert_TB_TE_M_TEST_USR",paramMap);
//		paramMap.put("USRID","test4");
//		System.out.println("== insertEt0010 디버그4 ==");
	}

	/**
	 * 테스트 데이터를 수정한다.
	 * @param paramMap - 수정할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void updateEt0010(LinkedHashMap paramMap) throws Exception {
		sqlSession.update("sfmes.sqlmap.tb.update_TB_TE_M_TEST_USR",paramMap);
		
	}

	/**
	 * 테스트 데이터를 삭제한다.
	 * @param paramMap - 삭제할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void deleteEt0010(LinkedHashMap paramMap) throws Exception {
		sqlSession.delete("sfmes.sqlmap.tb.delete_TB_TE_M_TEST_USR",paramMap);
	}

	/**
	 * 테스트 데이터를 목록조회한다.
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 결과 목록
	 * @exception Exception
	 */
	@Override
	public List<?> selectEt0010List(LinkedHashMap paramMap) throws Exception {
		System.out.println("== selectEt0010List paramMap=="+ paramMap);
		List resultList = null;
		resultList = sqlSession.selectList("sfmes.sqlmap.et.selectEt0010List",paramMap);
		System.out.println("== selectEt0010List selectList=="+ resultList.toString());
		// return sqlSession.selectList("sfmes.sqlmap.et.selectEt0010List",paramMap);
		return resultList;
	}
	
	/**
	 * 테스트 데이터를 목록조회한다.(팝업조회용)
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 결과 목록
	 * @exception Exception
	 */
	@Override
	public List<?> selectEt0010List02(LinkedHashMap paramMap) throws Exception {
		System.out.print("selectEt0010List02 : " + paramMap.toString());
		
		egovLogger.debug("사용자로그 메시지 테스트");

		List resultList = sqlSession.selectList("sfmes.sqlmap.et.selectEt0010List02",paramMap);
		
		// if(1==1) throw processException("usr.error",  new String[] {"사용자오류메시지21"});
		if(1==1) throw infoException("사용자 안내 메시지 테스트");
		
		return resultList;
	}

	/**
	 * 테스트 데이터를 상세조회한다.
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	@Override
	public List<?> selectEt0010One(LinkedHashMap paramMap) {
		return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_TE_M_TEST_USR",paramMap);
	}

}
