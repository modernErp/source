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
 * @Class Name  : Sy7010Service.java
 * @Description : Sy7010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2021.12.16  김상철      최초생성
 *
 * @author (주)모든솔루션
 * @since 2021.12.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Sy7010Service {
    
    
//    /**
//     * 그룹코드와 공통코드를 신규추가
//     */
//    void save01Sy5010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
//    
//    /**
//     * 그룹코드와 공통코드를 수정
//     */
//
//    void save02Sy5010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
//    
//    /**
//     * 그룹코드 삭제 (그룹코드 삭제 시 공통코드 같이 삭제됨)
//     */
//    void deleteSy5010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
//    
//    /**
//     * 그룹코드와 공통코드를 복제[SY5010P01]
//     */
//    void save03Sy5010P01(List<Map<String, Object>> paramList) throws Exception;
//    
    /**
     * 그룹코드 조회
     */
    
    List<?> selectSy7010List(LinkedHashMap paramMap) throws Exception;
    
//  
//  저장    
//    
    
    void insertSy7010(List<Map<String, Object>> paramList) throws Exception;
    
//    /**
//     * 그룹코드내역 조회(사용자용)
//     */
//    List<?> selectSy5015List(LinkedHashMap paramMap) throws Exception;
//    
//    /**
//     * 공통코드 조회
//     */
//    List<?> selectSy5010List02(LinkedHashMap paramMap)throws Exception;
//    
//    /**
//     * 공통코드내역조회(메모리적재용)
//     */
//    List<?> selectSy5010List03(LinkedHashMap paramMap)throws Exception;
//    
//    /**
//     * SY5010P01 공통코드복제리스트조회
//     */
//    List<?> select_TB_SY_M_COMNGRP(LinkedHashMap paramMap)throws Exception;
//    
//    /**
//     * SY5010P01 공통코드복제리스트조회
//     */
//    List<?> select_TB_SY_M_COMNGRPP_COPY(LinkedHashMap paramMap)throws Exception;
    
}
