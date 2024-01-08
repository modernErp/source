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

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.Co1010Service;

/**
 * @Class Name : Co1010ServiceImpl.java
 * @Description : 창고등록
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
@Service("Co1010Service")
public class Co1010ServiceImpl extends CmnAbstractServiceImpl implements Co1010Service {

    @Autowired
    private SqlSessionTemplate sqlSession;	
	/**
	 * 사업장 수정
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void updateCo1010_01(LinkedHashMap paramMap01) throws Exception {
		//사업장수정
		sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_BZPL", paramMap01);
		//사업장변경이력 등록
		sqlSession.update("sfmes.sqlmap.tb.insert_TB_CO_L_BZPL", paramMap01);
		
		//사업장 수정 시 가상창고도 같이 update를 위한 CO1030(창고수정) 호출
		sqlSession.update("sfmes.sqlmap.co.updateCo1030_V_Whse", paramMap01);
		
		//사업장 수정 시 거래처 테이블에 같은 사업장이 있을경우 사업장이름, 대표자명 같이 update
		sqlSession.update("sfmes.sqlmap.co.update_trpl", paramMap01);
		
		//사업장환경설정 수정
		sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_BZPL_ENV", paramMap01);
		//사업장환경설정변경이력 등록
        sqlSession.update("sfmes.sqlmap.tb.insert_TB_CO_L_BZPL_ENV", paramMap01);
        
        
//        if("1".equals(paramMap01.get("PRW_DSC"))) {
//            // BOM 관련 메뉴 사용여부 수정
//            paramMap01.put("USE_YN_BOM", "Y");
//            paramMap01.put("USE_YN_PRW", "N");            
//        } else {
//            // BOM 관련 메뉴 사용여부 수정
//            paramMap01.put("USE_YN_BOM", "N");
//            paramMap01.put("USE_YN_PRW", "Y");            
//        }
//        
//        // BOM 관련 메뉴 사용여부 수정
//        sqlSession.update("sfmes.sqlmap.sy.update_TB_SY_M_MENU_BOM", paramMap01);
//        // 공정기록서 관련 메뉴 사용여부 수정
//        sqlSession.update("sfmes.sqlmap.sy.update_TB_SY_M_MENU_PRW", paramMap01);

	}
	
	/**
     * @return 
	 * 사업장내역 데이터를 상세조회
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	@Override
	public List<?> selectCo1010List(LinkedHashMap paramMap01) throws Exception {
		return sqlSession.selectList("sfmes.sqlmap.co.selectCo1010List", paramMap01);
		
	}
	
	/**
	 * 인정감모율 등록
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void insertCo1010_01(List<Map<String,Object>> paramList01) throws Exception {	
		
		for(Map<String, Object> map : paramList01) {
			//유효성체크
			List<Map<String, Object>> result = sqlSession.selectList("sfmes.sqlmap.co.selectCo1010RCGDWD_PRCHK",map);
			if(result.size() >0) {
				throw infoException("동일한 품목유형이 존재합니다. 확인 후 다시 처리하세요.");
			}
			if(map.get("_status_").equals("+")) {
				sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_RCGDWD", map);
			} 			
		}
	}
	
	/**
	 * 인정감모율 등록
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void updateCo1010_02(List<Map<String,Object>> paramList01) throws Exception {	
		
		for(Map<String, Object> map : paramList01) {
			
			if(map.get("_status_").equals("+")) {
				//유효성체크
				List<Map<String, Object>> result = sqlSession.selectList("sfmes.sqlmap.co.selectCo1010RCGDWD_PRCHK",map);
				if(result.size() >0) {
					throw infoException("동일한 품목유형이 존재합니다. 확인 후 다시 처리하세요.");
				}
				sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_RCGDWD", map);
			} else if(map.get("_status_").equals("*")) {
				sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_RCGDWD", map);
			} else if(map.get("_status_").equals("-")) {
				sqlSession.delete("sfmes.sqlmap.tb.delete_TB_CO_M_RCGDWD", map);
			}			
		}
		
	}
	
	/**
     * @return 
	 * 인정감모율 조회
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 상세 내역
	 * @exception
	 */
	@Override
	public List<?> selectCo1010List03(LinkedHashMap paramMap01) throws Exception {	    
		return sqlSession.selectList("sfmes.sqlmap.co.selectCo1010List03", paramMap01);
	}
	
	/**
     * @return 
     * 사업장등록시 회사 주소 조회
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */
    @Override
    public List<?> selectCo1010List04(LinkedHashMap paramMap01) throws Exception {                
         return sqlSession.selectList("sfmes.sqlmap.co.selectCo1010_CORP_C_ADR", paramMap01);
    }

    /**
     * @return 
     * 사업장내역 데이터를 상세조회
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */
    @Override
    public List<?> selectCo1010List05(LinkedHashMap paramMap01) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo1010_CORP_C_PRW_DSC", paramMap01);
        
    }
}
