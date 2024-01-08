package com.sfmes.co.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.Co1075Service;

/**
 * @Class Name  : Co1075ServiceImpl.java
 * @Description : Co1075Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.02   김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.02 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Co1075Service")
public class Co1075ServiceImpl extends CmnAbstractServiceImpl implements Co1075Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<?> selectCo1075P01(LinkedHashMap paramMap) {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo1075P01",paramMap);
    }

}
