package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy6010Service;

/**
 * @Class Name  : Sy6010ServiceImpl.java
 * @Description : Sy6010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.12.01   이철홍      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sy6010Service")
public class Sy6010ServiceImpl extends CmnAbstractServiceImpl implements Sy6010Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 테이블 목록을 조회한다.
    @Override
    public List<?> selectSy6010List(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("selectSy6010List :::"+ paramMap);
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy6010List",paramMap);
    }
    
    // 테이블 정의서를 조회한다.
    @Override
    public List<?> selectSy6010List02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("selectSy6010List02 :::"+ paramMap);
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy6010List02",paramMap);
    }    
}
