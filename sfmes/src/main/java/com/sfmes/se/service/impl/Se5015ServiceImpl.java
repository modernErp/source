package com.sfmes.se.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.se.service.Se5015Service;

/**
 * @Class Name : Se5015ServiceImpl.java
 * @Description : 견적서 내역
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.06  손용찬      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Se5015Service")
public class Se5015ServiceImpl extends CmnAbstractServiceImpl implements Se5015Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    /**
     * @return 
     * 견적서내역 조회
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */
    @Override
    public List<?> selectSe5015List01(LinkedHashMap paramMap) {
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe5015List01", paramMap);
    }
    
    /**
     * @return 
     * 견적서내역 상세조회
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */
    @Override
    public List<?> selectSe5015List02(LinkedHashMap paramMap) {
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe5015List02", paramMap);
    }
}
