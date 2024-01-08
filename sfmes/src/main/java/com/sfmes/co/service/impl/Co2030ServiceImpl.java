package com.sfmes.co.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.Co2030Service;

/**
* @Class Name : Co2030ServiceImpl.java
* @Description : Co2030Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.08.13   장경석     최초생성
*   2020.09.01   여다혜     서비스 수정
*   2020.09.22   여다혜     묶음번호 저장/Validation Check추가
*
* @author (주)모든솔루션
* @since 2020.08.13
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/
@Service("Co2030Service")
public class Co2030ServiceImpl extends CmnAbstractServiceImpl implements Co2030Service{

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public void insertCo2030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        String resultMsg = null;
        String BUDL_NO = (String)sqlSession.selectOne("sfmes.sqlmap.co.select_AutoBudlNo", paramMap); //묶음번호 채번 생성
        String RE_BUDL_YN = (String)paramMap.get("RE_BUDL_YN"); //재묶음여부

        egovLogger.debug("### BUDL_NO  =>"+BUDL_NO);
        
        //묶음번호 상세 저장
        for(Map<String, Object> map : paramList){
            map.put("BUDL_NO", BUDL_NO); //채번된 묶음번호를 상세 List에 row별로 추가한다

            //Query Validation Check (묶음번호 상세)
            resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.co.selectCo2030_Budl_Detail_Insert_ValidCheck", map);
            
            if(!"OK".equals(resultMsg)){
                throw infoException(resultMsg);
            }else{
                //묶음번호 상세 INSERT (TB_CO_D_SRA_HST_BUDNO)
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_D_SRA_HST_BNDNO", map);
            }
        }
        
        //Query Validation Check (묶음번호 기본)
        resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.co.selectCo2030_Budl_Master_Insert_ValidCheck", paramMap);
        //egovLogger.debug("### ValidCheck resultMsg  =>"+resultMsg);
        
        paramMap.put("BUDL_NO", BUDL_NO);
        if(!"OK".equals(resultMsg)){
            throw infoException(resultMsg);
        }else{
            //묵음번호 마스터 INSERT (TB_CO_M_SRA_HST_BUDNO)
            //egovLogger.debug("### 묵음번호 마스터 INSERT (TB_CO_M_SRA_HST_BUDNO)  =>");
            sqlSession.insert("sfmes.sqlmap.co.insertCo2030_BUDL_NO_Master", paramMap);
            //egovLogger.debug("### 묵음번호 마스터 INSERT OK  =>");
        }
    }

    /**
     * 묶음번호등록(인터페이스 사용)
     */
    public void if_Co2030_insert(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        String resultMsg = null;
        String BUDL_NO = "";
        
        egovLogger.debug("### BUDL_NO  =>"+paramMap.get("BUDL_NO"));
        BUDL_NO = paramMap.get("BUDL_NO").toString(); //채번한 묶음번호를 paramMap에 추가 (묶음번호 마스터에 저장)
            
        
        egovLogger.debug("### d_xml2JsonList  =>"+paramList); 
        egovLogger.debug("######################################");

        //묶음번호 상세 저장
        for(Map<String, Object> map : paramList){
            map.put("BUDL_NO", BUDL_NO); //채번된 묶음번호를 상세 List에 row별로 추가한다

            //Query Validation Check (묶음번호 상세)
            resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.co.selectCo2030_Budl_Detail_Insert_ValidCheck", map);
            
            if(!"OK".equals(resultMsg)){
                throw infoException(resultMsg);
            }else{
                //묶음번호 상세 INSERT (TB_CO_D_SRA_HST_BUDNO)
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_D_SRA_HST_BNDNO", map);
            }
        }
        
        //묵음번호 마스터 INSERT (TB_CO_M_SRA_HST_BUDNO)
        egovLogger.debug("#############  paramMap =====>"+paramMap);
        egovLogger.debug("#############  paramMap =====>"+paramMap.get("RPT_YN"));
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_SRA_HST_BNDNO", paramMap);
        egovLogger.debug("#############  insert_TB_CO_M_SRA_HST_BNDNO  OK !!!!!");
    }
    
    
    //묶음번호 대상내역 조회 (20.09.01 여다혜 수정)
    @Override
    public List<?> selectCo2030List_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo2030_List01", paramMap);
        
    }  
    
    //묶음번호기본 조회
    @Override
    public List<?> selectCo2030_BUDL_NO_List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo2030_BUDL_NO_List", paramMap);
    }

    //묶음번호 상세 조회
    @Override
    public List<?> selectCo2030_BUDL_NO_Detail_List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo2030_BUDL_NO_Detail_List", paramMap);
    }

    //표준부위코드(한우, 돈육) 조회 - 조회조건 세팅용
    @Override
    public List<?> selectCo2030_STD_PAT_C() throws Exception {
        return sqlSession.selectList("select_LATC_CODE");
    } 
    
    @Override
    public List<?> selectCo2030List_04(LinkedHashMap paramMap01) throws Exception {
        //거래처계약정보조회(매출)
        return sqlSession.selectList("sfmes.sqlmap.co.co2030selectList_04",paramMap01);
    } 
    /**
     * 축산물이력번호관리기본 validation Check
     */
    @Override
    public String co2030validCheck_01(LinkedHashMap paramMap01) throws Exception {
        //거래처계약정보조회(매출)
        return sqlSession.selectOne("sfmes.sqlmap.co.co2030validCheck_01",paramMap01);
    } 

    /**
     * 묶음번호 출고전 validation Check
     */
    @Override
    public String co2030validCheck_02(LinkedHashMap paramMap01) throws Exception {
        //거래처계약정보조회(매출)
        return sqlSession.selectOne("sfmes.sqlmap.co.co2030validCheck_02",paramMap01);
    } 

    @Override
    public String updateCo2030_01(LinkedHashMap paramMap01) throws Exception {
        // 축산물이력번호관리기본 출고항목 수정
        egovLogger.debug("************ 축산물이력번호관리기본 출고항목 수정[CO2030U01] *********");
        egovLogger.debug("paramMap01: "+paramMap01.toString());
        
        String result = "";
        
        // 축산물이력번호관리기본 이력번호 Validation Check
        result = sqlSession.selectOne("sfmes.sqlmap.co.co2030validCheck_01", paramMap01);
        egovLogger.debug("Validation Check Message : " + result);
        
        if("OK".equals(result))
        {
            sqlSession.update("sfmes.sqlmap.co.update_co2030_01",paramMap01);
            
            return "OK";            
        }
        else
        {
            return result;
        }
    } 

    @Override
    public String updateCo2030_02(LinkedHashMap paramMap01) throws Exception {
        // 묶음번호 출고중량 수정
        egovLogger.debug("************ 묶음번호 출고중량 수정[CO2030U01] *********");
        egovLogger.debug("paramMap01: "+paramMap01.toString());
        
        String result = "";
        
        // 묶음번호 Validation Check
        result = sqlSession.selectOne("sfmes.sqlmap.co.co2030validCheck_02", paramMap01);
        egovLogger.debug("Validation Check Message : " + result);
        
        if("OK".equals(result))
        {
            sqlSession.update("sfmes.sqlmap.co.update_co2030_01",paramMap01);
            
            return "OK";            
        }
        else
        {
            return result;
        }
    }



}
