/**
 * 
 */
package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy1020Service;



/**
 * @Class Name : Sy1020ServiceImpl.java
 * @Description : Sy1020ServiceImpl Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------   ------   -------------------------------
 * @ 2020.06.08   여다혜   최초작성
 * @ 2021.12.16   여다혜   마이메뉴(즐겨찾기 메뉴) 기능 추가
 *
 * @author (주)모든솔루션
 * @since 2020.06.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by (주)모든솔루션 All right reserved.
 */

@Service("Sy1020Service")
public class Sy1020ServiceImpl extends CmnAbstractServiceImpl implements Sy1020Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    //전체메뉴조회
    @Override
    public List<?> selectAllMenu(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.selectAllMenu", paramMap);
    }

    //사용중메뉴조회
    @Override
    public List<?> selectUsingMenu(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.selectUsingMenu", paramMap);
    }

    //사업장목록조회(전체)
    @Override
    public List<?> selectBzplList(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.selectBzpl_c", paramMap);
    }

    //사업장목록조회(권한사업장)
    @Override
    public List<?> selectAuthBzplList(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.selectAuthBzpl_c", paramMap);
    }
    
    //사용자목록조회
    @Override
    public List<?> selectUsrList(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.selectUsrList", paramMap);
    }

    //권한조회
    @Override
    public List<?> selectPgmAuth(LinkedHashMap paramMap) throws Exception {
        List<?> resultList = null;
        
        String minMaxGbn = (String)paramMap.get("flag_minMaxOpt");
        System.out.println("selectPgmAuth Impl ====> minMaxGbn :" + minMaxGbn);
        
        if(minMaxGbn.equals("1")) {
            //flag = 1 : 중복된 역할구분이 있을 경우, 최소 권한으로 조회함
            resultList = sqlSession.selectList("sfmes.sqlmap.sy.selectUsrPgmAuthMin", paramMap);
        }else {
            //flag = 2 : 중복된 역할구분이 있을 경우, 최대 권한으로 조회함
            resultList = sqlSession.selectList("sfmes.sqlmap.sy.selectUsrPgmAuthMax", paramMap);
        }
        
        return resultList;
    }

    // 마이메뉴 조회 
    @Override
    public List<?> selectSy1020_MyMenu(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy1020_MyMenu", paramMap);
    }
    
    // 마이메뉴 등록
    @Override
    public void insertSy1020_MyMenu(LinkedHashMap paramMap) throws Exception {
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_D_USR_MYMNU", paramMap);
    }

    // 마이메뉴 삭제
    @Override
    public void deleteSy1020_MyMenu(LinkedHashMap paramMap) throws Exception {
        sqlSession.delete("sfmes.sqlmap.tb.delete_TB_SY_D_USR_MYMNU", paramMap);
    }

}
