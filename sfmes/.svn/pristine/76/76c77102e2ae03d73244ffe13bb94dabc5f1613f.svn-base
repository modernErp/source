package com.sfmes.mi.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.mi.service.Mi2140Service;

/**
 * @Class Name  : Mi2140ServiceImpl.java
 * @Description : Mi2140Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.11.30   김수민    최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.11.30
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Mi2140Service")
public class Mi2140ServiceImpl extends CmnAbstractServiceImpl implements Mi2140Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<?> selectMi2140_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.mi.selectMi2140_01", paramMap);
    }

}
