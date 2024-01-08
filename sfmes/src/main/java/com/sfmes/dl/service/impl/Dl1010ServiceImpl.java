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
import com.sfmes.dl.service.Dl1010Service;

/**
 * @Class Name : Dl1010ServiceImpl.java
 * @Description : 회계계정코드 등록
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
@Service("Dl1010Service")
public class Dl1010ServiceImpl extends CmnAbstractServiceImpl implements Dl1010Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
 // 공통 서비스 선언
 	@Resource(name = "CommonService")
 	private CommonService commonService;

	/**
	 * 회계계정코드 등록
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
//	@Override
//	public void insertDl1010(List<Map<String, Object>> paramList) throws Exception {
//	    //List유효성 체크
//        if(paramList == null) {
//            throw infoException("회계계정 내역에 입력된 정보가 없습니다.");
//        }
//        
//        //데이터저장
//        for(Map<String, Object> map : paramList) {
//            
//            if(map.get("_status_").equals("+")) {
//                //Map 유효성 체크 
//                String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.dl.selectDl1010Check", map);
//                if(!"OK".equals(resultMsg)) {
//                    throw infoException(resultMsg);
//                }
//                sqlSession.delete("sfmes.sqlmap.tb.insert_TB_DL_M_ACG", map);
//            } else if(map.get("_status_").equals("*")) {
//                sqlSession.update("sfmes.sqlmap.tb.update_TB_DL_M_ACG", map);
//            } else {
//                throw infoException("[" + map.get("") + "]" + "\n\n" 
//                                        + "계정과목에 입력된 내용이 없습니다. 재확인하세요.");
//            }
//            
//            //회계계정코드 변경이력 등록
//            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_DL_L_ACG", map);
//        }
//	}
	
	/**
     * 회계계정코드 수정
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    @Override
    public void updateDl1010(List<Map<String, Object>> paramList) throws Exception {
        
        //List유효성 체크
        if(paramList == null) {
            throw infoException("회계계정 내역 중 입력된 정보가 없습니다.");
        }
        
        //데이터저장
        for(Map<String, Object> map : paramList) {
            
            if(map.get("_status_").equals("+")) {
               
                //Map 유효성 체크 
                String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.dl.selectDl1010Check", map);
                if(!"OK".equals(resultMsg)) {
                    throw infoException(resultMsg);
                }
                
                sqlSession.delete("sfmes.sqlmap.tb.insert_TB_DL_M_ACG", map);
            } else if(map.get("_status_").equals("*")) {
                sqlSession.update("sfmes.sqlmap.tb.update_TB_DL_M_ACG", map);
            } else if(map.get("_status_").equals("-")) {
                sqlSession.delete("sfmes.sqlmap.tb.delete_TB_DL_M_ACG", map);
            } else {
                throw infoException("[" + map.get("") + "]" + "\n\n" 
                                        + "계정과목에 수정된 내용이 없습니다. 재확인하세요.");
            }
    
            //회계계정코드 변경이력 등록
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_DL_L_ACG", map);
        }
    }
        
	
	/**
    * @return 
    * 회계계정코드 조회
    * @param paramMap - 조회할 조건이 담긴 Map
    * @return 상세 내역
    * @exception
    */
   @Override
   public List<?> selectDl1010List01(LinkedHashMap paramMap) {
       return sqlSession.selectList("sfmes.sqlmap.dl.selectDl1010List01", paramMap);
   }
   
   /**
    * @return 
    * 회계계정코드 변경이력 조회
    * @param paramMap - 조회할 조건이 담긴 Map
    * @return 상세 내역
    * @exception
    */
   @Override
   public List<?> selectDl1010List02(LinkedHashMap paramMap) {
       return sqlSession.selectList("sfmes.sqlmap.dl.selectDl1010List02", paramMap);
   }
   /**
   * @return 
   * 회계계정코드 조회
   * @param paramMap - 조회할 조건이 담긴 Map
   * @return 상세 내역
   * @exception
   */
  @Override
  public List<?> selectDl1010List03(LinkedHashMap paramMap) {
      return sqlSession.selectList("sfmes.sqlmap.dl.selectDl1010List03", paramMap);
  }
  
}
