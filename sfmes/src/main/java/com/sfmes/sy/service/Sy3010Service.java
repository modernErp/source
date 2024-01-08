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
package com.sfmes.sy.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : Sy3010Service.java
 * @Description : Sy3010Service Class
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
public interface Sy3010Service {

	/**
	 * 연계전문 신규 등록한다.
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @param paramList02 
	 * @param paramList01 
	 * @return void형
	 * @exception Exception
	 */
	public void insertSy3010(LinkedHashMap<String, Object> paramMap, List<Map<String, Object>> paramList01, List<Map<String, Object>> paramList02) throws Exception;
    
	/**
	 * 연계전문 데이터를 수정한다.
	 * @param paramMap - 수정할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	public void updateSy3010(LinkedHashMap<String, Object> paramMap, List<Map<String, Object>> paramList01, List<Map<String, Object>> paramList02) throws Exception;

	/**
	 * 연계전문 리스트 데이터를 상세조회한다.
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	List<?> selectSy3010One(LinkedHashMap paramMap);
	
	/**
	 * 연계전문 폼 데이터를 상세조회한다.
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	List<?> selectSy3010List(LinkedHashMap paramMap);

}
