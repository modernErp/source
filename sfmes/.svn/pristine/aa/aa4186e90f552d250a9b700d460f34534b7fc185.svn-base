/**
 * 
 */
package com.sfmes.ge.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.ge.service.Ge1020Service;

/**
 * @Class Name  : Ge1020ServiceImpl.java
 * @Description : 업무연락
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.10.23  여다혜  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Ge1020Service")
public class Ge1020ServiceImpl extends CmnAbstractServiceImpl implements Ge1020Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    //GE1020 : 업무연락 등록 조회 (*사용자가 등록한 업무연락)
    @Override
    public List<?> selectGe1020(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe1020", paramMap);
    }

    //GE1025 :업무연락 내역 조회 (*사용자가 수신한 업무연락)
    @Override
    public List<?> selectGe1025(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe1025", paramMap);
    }

    //GE1020P01 업무연락 등록
    @Override
    public String insertGe1020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        //업무연락 일련번호 채번
        String BSN_CTC_SQNO = (String)sqlSession.selectOne("sfmes.sqlmap.ge.selectGe1020P01_BSN_CTC_SQNO", paramMap);

        //폼 저장
        paramMap.put("BSN_CTC_SQNO", BSN_CTC_SQNO);
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_BSN_CTC", paramMap);
        
        //그리드 저장
        for(Map<String, Object> map : paramList) {
            map.put("BSN_CTC_SQNO", BSN_CTC_SQNO);
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_D_BSN_CTC", map);
        }
        
        return BSN_CTC_SQNO;
    }

    @Override
    public String updateGe1020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        String BSN_CTC_SQNO = (String)paramMap.get("BSN_CTC_SQNO");
        
        //폼 수정
        sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_BSN_CTC", paramMap);
        
        //그리드 수정
        for(Map<String, Object> map : paramList) {
            map.put("BSN_CTC_SQNO", paramMap.get("BSN_CTC_SQNO"));
            
            if(map.get("_status_").equals("+")) {
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_D_BSN_CTC", map);
            }else if(map.get("_status_").equals("*")) {
                sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_D_BSN_CTC", map);
            }else if(map.get("_status_").equals("-")) {
                sqlSession.delete("sfmes.sqlmap.tb.delete_TB_CO_D_BSN_CTC", map);
            }
        }
        return BSN_CTC_SQNO;
    }

    //GE1020P01 : 업무연락 등록/내역 상세 조회 (1건)
    @Override
    public List<?> selectGe1020P01_Detail(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_CO_M_BSN_CTC", paramMap);
    }
      
    //GE1020P01 : 업무연락 수신자목록 조회
    @Override
    public List<?> selectGe1020P01_Rcst_id_List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe1020P01_02", paramMap);
    }

    //업무연락 수신 확인 업데이트
    @Override
    public void update_RC_STS_C(LinkedHashMap paramMap) throws Exception {
        sqlSession.update("sfmes.sqlmap.ge.updateGe1020_RCST", paramMap);
    }


}
