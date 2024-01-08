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
package com.sfmes.co.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : Co1010Service.java
 * @Description : Co1010Service Class
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
public interface Co1010Service {
	/**
	 * 사업장 수정
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	void updateCo1010_01(LinkedHashMap paramMap01) throws Exception;
	
	/**
	 * 사업장내역 상세조회, 사업장 환경설정 상세조회
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	
	List<?> selectCo1010List(LinkedHashMap paramMap01) throws Exception;
	
	/**
	 * 인정감모율 등록
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	void insertCo1010_01(List<Map<String,Object>> paramList01) throws Exception;
	
	/**
	 * 인정감모율 조회
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	List<?> selectCo1010List03(LinkedHashMap paramMap01) throws Exception;
	
	/**
	 * 인정감모율 수정
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	void updateCo1010_02(List<Map<String,Object>> paramList01) throws Exception;
	
	/**
     * 사업장등록시 회사 주소 조회
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    List<?> selectCo1010List04(LinkedHashMap paramMap01) throws Exception;

    /**
     * 사업장등록시 회사 주소 조회
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    List<?> selectCo1010List05(LinkedHashMap paramMap01) throws Exception;

}
