/**
 * 
 */
package com.sfmes.ge.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.ge.service.Ge1040Service;

/**
 * @Class Name  : Ge1040ServiceImpl.java
 * @Description : 사용자목소리
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.10.28  여다혜  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Ge1040Service")
public class Ge1040ServiceImpl extends CmnAbstractServiceImpl implements Ge1040Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    //사용자 목소리 조회 (1건)
    @Override
    public List<?> selectGe1040(LinkedHashMap paramMap) throws Exception {
        //조회수 +1 업데이트
        sqlSession.update("sfmes.sqlmap.ge.updateGe1040_InqCn", paramMap);
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_CO_M_USR_VOICE", paramMap);
    }

    //사용자 목소리 내역 (전체조회)
    @Override
    public List<?> selectGe1045(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe1045", paramMap);
    }

    //사용자 목소리 저장
    @Override
    public String insertGe1040(LinkedHashMap paramMap) throws Exception {
        //사용자목소리 번호 채번(VOICE_SQNO)
        String VOICE_SQNO = sqlSession.selectOne("sfmes.sqlmap.ge.selectGe1040P01_VOICE_SQNO", paramMap);
        paramMap.put("VOICE_SQNO", VOICE_SQNO);
        
        //사용자 목소리 INSERT
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_USR_VOICE", paramMap);
        return VOICE_SQNO;
    }

    //사용자 목소리 수정
    @Override
    public String updateGe1040(LinkedHashMap paramMap) throws Exception {
        String VOICE_SQNO = (String)paramMap.get("VOICE_SQNO");
        
        //사용자 목소리 UPDATE
        sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_USR_VOICE", paramMap);
        return VOICE_SQNO;
    }


}
