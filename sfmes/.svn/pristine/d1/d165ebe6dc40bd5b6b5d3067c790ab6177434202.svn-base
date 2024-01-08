package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy2010Service;

/**
* @Class Name : Sy2010ServiceImpl.java
* @Description : Sy2010Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.06.11   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.06.11
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/
@Service("Sy2010Service")
public class Sy2010ServiceImpl extends CmnAbstractServiceImpl implements Sy2010Service{

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public void insertSy2010(LinkedHashMap paramMap) throws Exception {
    	egovLogger.debug("paramMap : " + paramMap.toString());
        int CNT = sqlSession.selectOne("sfmes.sqlmap.sy.sy2010validCheck_01",paramMap);
        if(CNT > 0) {
            throw infoException("중복된 메뉴아이디가 존재합니다.");
            
        }
        //메뉴등록
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_M_MENU",paramMap);
        
    }

    @Override
    public void updateSy2010(LinkedHashMap paramMap) throws Exception {
        //메뉴변경
    	egovLogger.debug("updateSy2010 paramMap : " + paramMap.toString());
        sqlSession.update("sfmes.sqlmap.tb.update_TB_SY_M_MENU",paramMap);
    	egovLogger.debug("updateSy2010 paramMap end: " + paramMap.toString());
    }
    
    @Override
    public List<?> selectSy2010List(LinkedHashMap paramMap) throws Exception {
        // 메뉴목록조회
    	egovLogger.debug("paramMap : " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.sy.sy2010selectMenu",paramMap);
        
    }

   
}
