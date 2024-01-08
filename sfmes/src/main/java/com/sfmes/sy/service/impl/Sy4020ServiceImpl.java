package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy4020Service;

/**
 * @Class Name  : Sy4020ServiceImpl.java
 * @Description : Sy4020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.10   김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sy4020Service")
public class Sy4020ServiceImpl extends CmnAbstractServiceImpl implements Sy4020Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    //시스템오류이력 조회
    @Override
    public List<?> selectSy4020ErrList(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy4020ErrList",paramMap);
    }
    
    //시스템오류이력상세 조회
    @Override
    public List<?> selectSy4020ErrDetail(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy4020ErrDetail",paramMap);
    }
    
    // 시스템오류이력등록
    @Override
    public void insertSy4020(LinkedHashMap paramMap) {
        
        // 오류 무한반복 방지를 위해 자체 오류 처리함
        // 해당 오류 발생 시 로그 기록을 하지 않고 무시함
        try {
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_L_ERR_LOG",paramMap);
        } catch (Exception e) {
            // 오류 발생 시 무시한다.
            e.printStackTrace();
            return;
        }
    }
}
