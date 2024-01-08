package com.sfmes.co.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.Co1060Service;

/**
 * @Class Name : Co1060ServiceImpl.java
 * @Description : 물품분류 조회/등록
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.06.29  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Co1060Service")
public class Co1060ServiceImpl extends CmnAbstractServiceImpl implements Co1060Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    //물품대분류 조회
    @Override
    public List<?> selectGdsLCLC(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo1060_LCLC", paramMap);
    }

    //물품중분류 조회
    @Override
    public List<?> selectGdsMCLC(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo1060_MCLC", paramMap);
    }

    //물품소분류 조회
    @Override
    public List<?> selectGdsSCLC(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo1060_SCLC", paramMap);
    }

    //물품분류 등록(대+중+소)
    @Override
    public void insertCo1060Gds(List<Map<String, Object>> paramList1, List<Map<String, Object>> paramList2, List<Map<String, Object>> paramList3) throws Exception {
        String Gdsclfc = "";

        //물품대분류 등록/수정
        for(Map<String, Object> map : paramList1) {
            //신규및수정에 따른 분개 후 물품분류기본테이블에 저장
            if(map.get("_status_").equals("+")) {
                //물품분류코드(대+중+소) 만들기
                Gdsclfc = map.get("GDS_LCLC").toString()+map.get("GDS_MCLC").toString()+map.get("GDS_SCLC").toString();
                map.put("GDS_CLF_C", Gdsclfc);
                
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_GDS_CLF", map);
            }
            else {
                //물품분류기본이력테이블에 먼저 저장
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_L_GDS_CLF", map);                
                sqlSession.insert("sfmes.sqlmap.tb.update_TB_CO_M_GDS_CLF", map);
            }
        }
        
        //물품중분류 등록/수정
        for(Map<String, Object> map : paramList2) {
            //신규및수정에 따른 분개 후 물품분류기본테이블에 저장
            if(map.get("_status_").equals("+")) {
                //물품분류코드(대+중+소) 만들기
                Gdsclfc = map.get("GDS_LCLC").toString()+map.get("GDS_MCLC").toString()+map.get("GDS_SCLC").toString();
                map.put("GDS_CLF_C", Gdsclfc);
                
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_GDS_CLF", map);
            }
            else {
                //물품분류기본이력테이블에 먼저 저장
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_L_GDS_CLF", map);                
                sqlSession.insert("sfmes.sqlmap.tb.update_TB_CO_M_GDS_CLF", map);
            }            
        }
        
        //물품소분류 등록/수정
        for(Map<String, Object> map : paramList3) {
            //신규및수정에 따른 분개 후 물품분류기본테이블에 저장
            if(map.get("_status_").equals("+")) {
                //물품분류코드(대+중+소) 만들기
                Gdsclfc = map.get("GDS_LCLC").toString()+map.get("GDS_MCLC").toString()+map.get("GDS_SCLC").toString();
                map.put("GDS_CLF_C", Gdsclfc);
                
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_GDS_CLF", map);
            }
            else {
                //물품분류기본이력테이블에 먼저 저장
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_L_GDS_CLF", map);                
                sqlSession.insert("sfmes.sqlmap.tb.update_TB_CO_M_GDS_CLF", map);
            }            
        }         
        
    }

}
