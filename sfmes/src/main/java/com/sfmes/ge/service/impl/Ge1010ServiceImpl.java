package com.sfmes.ge.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.ge.service.Ge1010Service;

/**
 * @Class Name  : Ge1010ServiceImpl.java
 * @Description : 공지사항 조회/등록/수정
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.10.07  여다혜  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Ge1010Service")
public class Ge1010ServiceImpl extends CmnAbstractServiceImpl implements Ge1010Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    //공지사항 목록 조회
    @Override
    public List<?> selectGe1010_OFANC_List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe1010_OFANC_List", paramMap);
    }

    //공지사항 상세 조회
    @Override
    public List<?> selectGe1010_OFANC_Detail(LinkedHashMap paramMap) throws Exception {
        //조회수 1증가 update
        sqlSession.update("sfmes.sqlmap.ge.updateGe1010_InqCnt", paramMap);
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_CO_M_OFANC", paramMap);
    }

    //공지사항 등록
    @Override
    public String insertGe1010_OFANC(LinkedHashMap paramMap) throws Exception {
        //공지사항일련번호 select (등록 후 재조회를 위한 retrun값, Max+1)
        String OFANC_SQNO = sqlSession.selectOne("sfmes.sqlmap.ge.selectGe1010_OFANC_SQNO");
        
        //공지사항 등록
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_OFANC", paramMap);
        return OFANC_SQNO;
    }

    //공지사항 수정
    @Override
    public String updateGe1010_OFANC(LinkedHashMap paramMap) throws Exception {
        //공지사항 수정 후 재조회를 위해 OFANC_SQNO 값
        String OFANC_SQNO = (String)paramMap.get("OFANC_SQNO");
        
        //공지사항 수정 
        sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_OFANC", paramMap);
        return OFANC_SQNO;
    }

    //공지사항 삭제여부 업데이트
    @Override
    public void updateGe1010_delYn(LinkedHashMap paramMap) throws Exception {
        // TODO Auto-generated method stub
        
    }



}
