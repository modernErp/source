package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy1030Service;

/**
* @Class Name : Sy1030ServiceImpl.java
* @Description : Sy1030Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.05.28   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.05.28
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/
@Service("Sy1030Service")
public class Sy1030ServiceImpl extends CmnAbstractServiceImpl implements Sy1030Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public void insertSy1030(LinkedHashMap paramMap) throws Exception {
        //사용자신규등록
        String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.sy.sy1030validCheck_01",paramMap);
        
        if(!"OK".equals(resultMsg)) {
            throw infoException(resultMsg);
        }
        
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_M_USR",paramMap);
        
        //사용자신규등록 후 이력저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_L_USR",paramMap);
        
        //최초 등록시 입력한 사업장에 기본사업장, 권한줌
        paramMap.put("BAS_BZPL_YN", "Y");
        paramMap.put("AUTH_YN", "Y");
        sqlSession.insert("sfmes.sqlmap.sy.insertSy2060_BzplAuth", paramMap);
    }

    @Override
    public void updateSy1030(LinkedHashMap paramMap) throws Exception {
        //사용자수정
        sqlSession.update("sfmes.sqlmap.tb.update_TB_SY_M_USR",paramMap);
        
        //사용자수정 후 이력저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_L_USR",paramMap);

    }
    

    @Override
    public void updateSy1030_01(LinkedHashMap paramMap) throws Exception {
        //비밀번호초기화
        sqlSession.update("sfmes.sqlmap.sy.updateSy1030_01",paramMap);
        
    }
    
    @Override
    public void updateSy1030_02(LinkedHashMap paramMap) throws Exception {
        //비밀번호변경
        LinkedHashMap select_PW = sqlSession.selectOne("sfmes.sqlmap.sy.sy1030validCheck_03",paramMap);
        if(!select_PW.get("PW").equals(paramMap.get("CUR_PW"))){
            throw infoException("기존비밀번호가 틀립니다.");
            
        }
        sqlSession.update("sfmes.sqlmap.sy.updateSy1030_02",paramMap);
        
        //비밀번호변경 후 이력저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_L_USR",paramMap);
        
    }


    @Override
    public List<?> selectSy1030List(LinkedHashMap paramMap) throws Exception {
        //사용자조회
        return sqlSession.selectList("sfmes.sqlmap.sy.select1030List",paramMap);
        
    }

}
