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
package com.sfmes.ge.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : Ge2030Service.java
 * @Description : Ge2030Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.11.17  유승현      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.11.17
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Ge2030Service {

	//민원접수 조회
	List<?> selectGe2030_01(LinkedHashMap paramMap) throws Exception;

	//민원사후 조회
	List<?> selectGe2030_02(LinkedHashMap paramMap) throws Exception;
	
    //민원접수 상세 조회
    List<?> selectGe2030_03(LinkedHashMap paramMap) throws Exception;
    
    //민원사후 상세 조회
    List<?> selectGe2030_04(LinkedHashMap paramMap) throws Exception;
    
    //민원사후 팝업 조회
    List<?> selectGe2030_05(LinkedHashMap paramMap) throws Exception;    

    //민원사후관리 등록	
    String insertGe2030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    //민원사후관리 수정    
    String updateGe2030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;    
}
