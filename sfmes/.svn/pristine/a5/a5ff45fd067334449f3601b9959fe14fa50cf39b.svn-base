package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.pd.service.Pd2140Service;

/**
* @Class Name : Pd2140ServiceImpl.java
* @Description : Pd2140Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.09.25   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.09.25
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2140Service")
public class Pd2140ServiceImpl extends CmnAbstractServiceImpl implements Pd2140Service{

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<?> selectPd2140List_01(LinkedHashMap paramMap) throws Exception {
        // 제품상세투입현황
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2140list_01",paramMap);
    }

}
