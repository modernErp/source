package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.pd.service.Pd3010Service;

/**
 * @Class Name  : Pd3010ServiceImpl.java
 * @Description : 예정원가 조회/등록
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.13  여다혜  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.08.13
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Pd3010Service")
public class Pd3010ServiceImpl extends CmnAbstractServiceImpl implements Pd3010Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    //최종원가조회
    @Override
    public List<?> selectPd3010_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd3010_01", paramMap);
    }

    //적용일자조회
    @Override
    public List<?> selectPd3010_02(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd3010_02", paramMap);
    }

    //(예정원가 미등록)신규품목조회
    @Override
    public List<?> selectPd3010_03(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd3010_03", paramMap);
    }

    //예정원가변경이력 조회
    @Override
    public List<?> selectPd3010_04(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd3010_04", paramMap);
    }
    
    /*
     * [예정원가등록 업무 순서] 
     * ============================================================================================
     * CASE1 : 신규품목 예정원가 등록 시 (INSERT만)
     * ============================================================================================
     * 01. TB_PD_M_PLA_PCS 에 INSERT  (xml : sfmes.sqlmap.tb.insert_TB_PD_M_PLA_PCS)
     *     - 적용시작일자 : 오늘
     *     - 적용종료일자 : 9999-12-31
     * ============================================================================================
     * 
     * ============================================================================================
     * CASE2 : 기존품목 예정원가 수정 시 (UPDATE -> INSERT)
     * ============================================================================================
     * 01. 기존예정원가 Row의 적용일자 UPDATE (xml : sfmes.sqlmap.pd.updatePd3010_PriorValue)
     *     - 적용시작일자 : 기존일자
     *     - 적용종료일자 : 오늘 - 1일 (어제날짜)
     * 02. TB_PD_M_PLA_PCS 에 새로운 일자로 INSERT (xml : sfmes.sqlmap.tb.insert_TB_PD_M_PLA_PCS)
     *     - 적용시작일자 : 화면에서 입력한적용시작일자
     *     - 적용종료일자 : 9999-12-31
     * ============================================================================================
     * 
     * ============================================================================================
     * CASE3 : 기존품목 예정원가/적요만 수정 시 (UPDATE만)
     *         EX1) 기존적용시작일자 == 수정할(입력한)적용시작일자  
     *         EX2) 기존예정원가 == 수정할(입력한)예정원가 
     * ============================================================================================
     * EX1) (입력한)적용시작일자 = 기존적용시작일자 같은 날짜일 경우 
     * --> 해당 case는 같은 날 예정원가를 2번 이상 수정하는 경우에 해당 합니다.
     *     이에 따라, 기존 예정원가의 종료일자를 입력한날짜-1 로 update가 불가하며,
     *     새로 변경 될 예정원가의 값을 insert할 수 없습니다.
     * 
     * EX2) 기존예정원가 = 수정할예정원가(화면에서 입력한 예정원가) 
     * --> 해당 case는 적요만 수정 된 경우 입니다. 
     *     이에 따라, 적용시작일자 / 적용종료일자의 변경이 일어나지 않습니다.
     *     
     *     
     * 상기 두 조건의 경우에는 UPDATE -> INSERT 동작이 불가하거나 불필요하므로
     * UPDATE Query만 수행 합니다. (xml : sfmes.sqlmap.tb.update_TB_PD_M_PLA_PCS)
     * ============================================================================================
     */
    
    
    //예정원가 insert (CASE 1)
    @Override
    public void insertPd3010(List<Map<String, Object>> paramList) throws Exception {
        String resultMsg = null;
        
        for(Map<String, Object> map : paramList) {
            //Query Validation Check
            resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.pd.selectPd3010_Insert_ValidCheck", map);
            
            if(!"OK".equals(resultMsg)) {
                throw infoException(resultMsg);
            }else {
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_M_PLA_PCS", map);
            }
        }
    }

    //예정원가 수정 (CASE 2, CASE 3)
    @Override
    public void updatePd3010(List<Map<String, Object>> paramList) throws Exception {
        String resultMsg = null;
        
        for(Map<String, Object> map : paramList) {
            //Query Validation Check
            resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.pd.selectPd3010_Insert_ValidCheck", map);            

            if(!"OK".equals(resultMsg)) {
                throw infoException(resultMsg);
            }else {
                if(map.get("newAPL_ST_DT").equals(map.get("APL_ST_DT")) ||   //적용시작일자 = 기존적용시작일자  
                   map.get("PLA_PCS").equals(map.get("PRIOR_PLA_PCS")))      //입력한예정원가 = 기존예정원가
                {
                    //CASE3 (UPDATE만)
                    sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_M_PLA_PCS", map);
                }else {
                    //CASE2 (UPDATE -> INSERT)
                    sqlSession.update("sfmes.sqlmap.pd.updatePd3010_PriorValue", map);
                    sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_M_PLA_PCS", map);
                }
            }
        }
    }

}
