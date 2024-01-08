package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy5010Service;

/**
 * @Class Name  : Sy5010ServiceImpl.java
 * @Description : Sy5010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.01   김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sy5010Service")
public class Sy5010ServiceImpl extends CmnAbstractServiceImpl implements Sy5010Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    //그룹코드신규추가 및 공통코드 신규추가 및 수정(Update)
    @Override
    public void save01Sy5010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        
        int M_CNT = sqlSession.selectOne("sfmes.sqlmap.sy.validationSy5010",paramMap);
        if(M_CNT > 0) {
            //중복그룹코드확인
            throw infoException("중복된 그룹코드가 존재합니다. 확인 후 다시 처리하시오.");
        }else {
            //그룹코드신규등록
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_M_COMNGRP",paramMap);
        }
            for(Map<String, Object> map : paramList) {
               if(map.get("_status_").equals("+")) {
                   int D_CNT = sqlSession.selectOne("sfmes.sqlmap.sy.validation2Sy5010", map);
                   if(D_CNT > 0){
                       //중복공통코드확인
                       throw infoException("중복된 공통코드가 존재합니다. 확인 후 다시 처리하세요.");
                   } 
                 // 공통코드 신규추가
                 sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_D_COMNC", map); 
               }
            }
        //공통코드 변경이력 추가
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_L_COMNC", (LinkedHashMap) paramMap); 
    }
    
    //그룹코드 수정 및 공통코드 신규추가 및 수정(Update)
    @Override
    public void save02Sy5010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception { 
        
        if(!paramMap.isEmpty()){
            // 그룹코드 수정.
            sqlSession.update("sfmes.sqlmap.tb.update_TB_SY_M_COMNGRP", paramMap); 
        }
        //공통코드 변경이력 추가
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_L_COMNC", (LinkedHashMap) paramMap); 

        for(Map<String, Object> map : paramList) {
            if (map.get("_status_").equals("+")) {
                int D_CNT = sqlSession.selectOne("sfmes.sqlmap.sy.validation2Sy5010",map);
                if(D_CNT > 0){
                    //중복공통코드확인
                    throw infoException("중복된 공통코드가 존재합니다. 확인 후 다시 처리하세요.");   
                }
                // 공통코드 신규추가  
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_D_COMNC", map);               
            } else if (map.get("_status_").equals("*")) {
                  // 공통코드 수정  
                  sqlSession.update("sfmes.sqlmap.tb.update_TB_SY_D_COMNC",map);               
            } 
        }
    }
 
  //그룹코드 공통코드 복제[SY5010P01.mvf]
    @Override
    public void save03Sy5010P01(List<Map<String, Object>> paramList) throws Exception {
        
        for(Map<String, Object> map : paramList) {
            if(map.get("_status_").equals("+") && map.get ("CHK").equals("")) {
                //그룹코드복제하는리스트 중복체크
                int M_CNT = sqlSession.selectOne("sfmes.sqlmap.sy.validationSy5010P01",map);
                egovLogger.debug("::::[SY5010P01 그룹코드중복체크]:::::" + map);
                if(M_CNT > 0) {
                    //중복시 메세지처리
                    egovLogger.debug(":::::[SY5010P01 중복코드발생]:::::" + M_CNT);
                    throw infoException("중복된 그룹코드가 존재합니다. 확인 후 다시 처리하시오.");
                }else {
                    egovLogger.debug(":::::[SY5010P01 그룹코드복제000000]:::::" + map);    
                    sqlSession.insert("sfmes.sqlmap.sy.insert_TB_SY_M_COMNGRP_sy5010p01", map); 
                      
                    egovLogger.debug(":::::[SY5010P01 공통코드복제111111]:::::" + map);
                    sqlSession.insert("sfmes.sqlmap.sy.insert_TB_SY_D_COMNC_sy5010p01", map); 

                }
            }
        }
    }
//        for(Map<String, Object> map : paramList) {
//            if(map.get("_status_").equals("+")) {
//               
//            }
//        }
//    }
//    
     //그룹코드 삭제 (그룹코드 삭제 시 공통코드 같이 삭제됨)
     @Override
     public void deleteSy5010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception { 
         
         if(!paramMap.isEmpty()){
             sqlSession.delete("sfmes.sqlmap.tb.delete_TB_SY_M_COMNGRP",(LinkedHashMap) paramMap);
         }
         sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_L_COMNC", (LinkedHashMap) paramMap); // 공통코드 변경이력 추가
     
         for (int i = 0; i < paramList.size(); i++) { 
             if (paramList.get(i) != null){
                     sqlSession.delete("sfmes.sqlmap.tb.delete_TB_SY_D_COMNC", (LinkedHashMap) paramList.get(i));
             }  
         }
     }
    
     
    //그룹코드조회
    @Override
    public List<?> selectSy5010List(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("그룹코드 ::");
        egovLogger.debug("그룹코드 :::"+ paramMap);
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy5010List",paramMap);
    }
    
    //그룹코드조회(사용자용)
    @Override
    public List<?> selectSy5015List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy5015List",paramMap);
    }
    
    //공통코드조회
    @Override
    public List<?> selectSy5010List02(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy5010List02",paramMap);
    }
    
    //공통코드내역조회(메모리적재용)
    @Override
    public List<?> selectSy5010List03(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy5010List03",paramMap);
    }
    
    //SY5010P01 공통코드복제리스트조회
    @Override
    public List<?> select_TB_SY_M_COMNGRP(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug(":::::[SY5010P01EXIST SELECT]:::::" + paramMap );
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SY_M_COMNGRP",paramMap);
    }

    @Override
    public List<?> select_TB_SY_M_COMNGRPP_COPY(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug(":::::[SY5010P01SECEND LIST SEARCH ]:::::" + paramMap );
        return sqlSession.selectList("sfmes.sqlmap.sy.select_TB_SY_M_COMNGRPP_COPY",paramMap);
    }



    
    
}
