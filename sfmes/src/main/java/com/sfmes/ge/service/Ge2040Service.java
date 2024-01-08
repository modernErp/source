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

import com.popbill.api.PopbillException;
import com.popbill.api.message.Message;

/**
 * @Class Name : Ge2040Service.java
 * @Description : Ge2040Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.11.19  유승현      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.11.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Ge2040Service {

    //수신자 목록 리스트
    List<?> selectGe2040_01(LinkedHashMap<String, Object> paramMap) throws Exception;
    
    //문자 전송
    String msgSend(LinkedHashMap<String, Object> paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    //팩스 전송
    String faxSend(List<Map<String, Object>> paramList01, List<Map<String, Object>> paramList02) throws Exception;
    
    //카카오 전송
    String kakaoSend(LinkedHashMap<String, Object> paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    //카카오 알림톡 템플릿 조회
    List<?> kakaoTemplate() throws Exception;
}
