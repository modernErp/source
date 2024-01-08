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
package com.sfmes.dl.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.dl.service.Dl1020Service;

/**
 * @Class Name : Dl1020ServiceImpl.java
 * @Description : 회계계정코드 등록
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.22  손용찬      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Dl1020Service")
public class Dl1020ServiceImpl extends CmnAbstractServiceImpl implements Dl1020Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
 // 공통 서비스 선언
 	@Resource(name = "CommonService")
 	private CommonService commonService;

	/**
	 * 회계분개기준 등록
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void insertDl1020(List<Map<String, Object>> paramList01, List<Map<String, Object>> paramList02) throws Exception {
    	egovLogger.debug(paramList01.toString());
	    	    
        //분개기본 수정,삭제
        for(Map<String, Object> map : paramList01) {
            if(map.get("_status_").equals("+")) {
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_DL_M_ACG_JNLZ", map);
            } else if(map.get("_status_").equals("*")) {
                sqlSession.update("sfmes.sqlmap.tb.update_TB_DL_M_ACG_JNLZ", map);
            } else if(map.get("_status_").equals("-")) {
                sqlSession.delete("sfmes.sqlmap.tb.delete_TB_DL_M_ACG_JNLZ", map);
            }
        }
                
        //분개상세 수정,삭제
        for(Map<String, Object> map : paramList02) {
            if(map.get("_status_").equals("+")) {
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_DL_D_ACG_JNLZ", map);
            } else if(map.get("_status_").equals("*")) {
                sqlSession.update("sfmes.sqlmap.tb.update_TB_DL_D_ACG_JNLZ", map);
            } else if(map.get("_status_").equals("-")) {
                sqlSession.delete("sfmes.sqlmap.tb.delete_TB_DL_D_ACG_JNLZ", map);
            }
        }
	}
	
    /**
     * @return 
     * 회계분개기준 조회
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */
    @Override
    public List<?> selectDl1020List01(LinkedHashMap paramMap) {
    	egovLogger.debug(paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.dl.selectDl1020List01", paramMap);
    }
    
    /**
     * @return 
     * 회계분개기준 상세조회
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */
    @Override
    public List<?> selectDl1020List02(LinkedHashMap paramMap) {
    	egovLogger.debug(paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.dl.selectDl1020List02", paramMap);
    }
}
