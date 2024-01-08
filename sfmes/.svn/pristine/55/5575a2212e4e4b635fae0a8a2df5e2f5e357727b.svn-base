package com.sfmes.co.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.Co2010Service;

/**
* @Class Name : Co2010ServiceImpl.java
* @Description : Co2010Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.06.24   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.06.24
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/
@Service("Co2010Service")
public class Co2010ServiceImpl extends CmnAbstractServiceImpl implements Co2010Service{

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public String insertCo2010(LinkedHashMap paramMap01, LinkedHashMap paramMap02, LinkedHashMap paramMap03) throws Exception {
        //거래처등록
 
        String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.co.co2010validCheck_01",paramMap01);
        
        String TRPL_C_no = null;      // 거래처코드 
        
        if(!"OK".equals(resultMsg)) {
            throw infoException(resultMsg);
        } else if("".equals(paramMap01.get("TRPL_C"))) {
            // 거래처코드가 공백일 경우 자동채번   20220311 구민희 추가
            TRPL_C_no = sqlSession.selectOne("sfmes.sqlmap.co.select_AutoNum_TRPL_C", paramMap01);
            paramMap01.put("TRPL_C", TRPL_C_no);    // 거래처코드  
        } else {
            // 거래처 코드가 공백이 아닐 경우
        }   
            //거래처정보
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_TRPL",paramMap01);
            //거래처정보기본
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_TRPL_INFO",paramMap01);
            //거래처정보등록 후 이력저장
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_L_TRPL",paramMap01);
               
        if("Y".equals(paramMap01.get("BY_CTR_YN"))) {
            paramMap02.put("BY_SL_DSC", "1");
            //거래처계약정보(매입)
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_TRPL_CTR",paramMap02);
            //2022-01-18 ksckorea
            //거래처계약정보등록 후 이력저장
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_L_TRPL_CTR",paramMap02);
            
        }
        
        if("Y".equals(paramMap01.get("SL_CTR_YN"))) {
            paramMap03.put("BY_SL_DSC", "2");
            //거래처계약정보(매출)
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_TRPL_CTR",paramMap03);
            //2022-01-18 ksckorea
            //거래처계약정보등록 후 이력저장
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_L_TRPL_CTR",paramMap03);
            
        }
        //저장 후 채번된 거래처코드 Return (재조회)
        return TRPL_C_no;
    }

    @Override
    public void updateCo2010(LinkedHashMap paramMap01, LinkedHashMap paramMap02, LinkedHashMap paramMap03) throws Exception {
        // 거래처수정
        
        //거래처정보
        sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_TRPL",paramMap01);
        //거래처정보기본
        sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_TRPL_INFO",paramMap01);
        //2022-01-18 ksckorea
        //거래처정보등록 후 이력저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_L_TRPL",paramMap01);
        
        
        if("Y".equals(paramMap01.get("BY_CTR_YN"))) {
            //거래처계약정보(매입)
            paramMap02.put("BY_SL_DSC", "1");
            
            if(!"".equals(paramMap02.get("FSRG_ID"))) {
                sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_TRPL_CTR",paramMap02);
            } else {
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_TRPL_CTR",paramMap02);
            }
            //2022-01-18 ksckorea
            //거래처계약정보등록 후 이력저장
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_L_TRPL_CTR",paramMap02);
        }
            
        if("Y".equals(paramMap01.get("SL_CTR_YN"))) {
            //거래처계약정보(메출)
            paramMap03.put("BY_SL_DSC", "2");

            if(!"".equals(paramMap03.get("FSRG_ID"))) {
                sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_TRPL_CTR",paramMap03);
            } else {
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_TRPL_CTR",paramMap03);
            }  
            //거래처계약정보등록 후 이력저장
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_L_TRPL_CTR",paramMap03);
        }
        
    }

    @Override
    public List<?> selectCo2010List_01(LinkedHashMap paramMap01) throws Exception {
        //거래처정보조회
        return sqlSession.selectList("sfmes.sqlmap.co.co2010selectList_01",paramMap01);
        
    }  
    
    @Override
    public List<?> selectCo2010List_02(LinkedHashMap paramMap01) throws Exception {
        //거래처계약정보조회(매입)
        return sqlSession.selectList("sfmes.sqlmap.co.co2010selectList_02",paramMap01);
        
    }

    @Override
    public List<?> selectCo2010List_03(LinkedHashMap paramMap01) throws Exception {
        //거래처계약정보조회(매출)
        return sqlSession.selectList("sfmes.sqlmap.co.co2010selectList_03",paramMap01);
    } 

}
