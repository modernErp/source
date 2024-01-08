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
package com.sfmes.dl.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : Dl1010Service.java
 * @Description : Dl1010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.20  손용찬      최초생성
 * @ 2020.12.14  이수빈      INSERT,UPDATE수정
 *
 * @author (주)모든솔루션
 * @since 2020.07.20
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Dl1010Service {

	/**
	 * 회계계정코드 등록
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @param paramList 
	 * @return void형
	 * @exception Exception
	 */

//    void insertDl1010(List<Map<String, Object>> paramList) throws Exception;
    
    /**
     * 회계계정코드 수정
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    void updateDl1010(List<Map<String, Object>> paramList) throws Exception;
    
    /**
    * 회계계정코드 조회
    * @param paramMap - 등록할 정보가 담긴 Map
    * @return void형
    * @exception Exception
    */
   List<?> selectDl1010List01(LinkedHashMap paramMap) throws Exception;
   
   /**
    * 회계계정코드 변경이력 조회
    * @param paramMap - 등록할 정보가 담긴 Map
    * @return void형
    * @exception Exception
    */
   List<?> selectDl1010List02(LinkedHashMap paramMap) throws Exception;

   /**
   * 원가요소 회계계정코드 조회
   * @param paramMap - 등록할 정보가 담긴 Map
   * @return void형
   * @exception Exception
   */
  List<?> selectDl1010List03(LinkedHashMap paramMap) throws Exception;
}
