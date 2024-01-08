package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.pd.service.Pd2110Service;

/**
* @Class Name : Pd2110ServiceImpl.java
* @Description : Pd2110Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.09.21   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.09.21
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2110Service")
public class Pd2110ServiceImpl extends CmnAbstractServiceImpl implements Pd2110Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public List<?> selectPd2110List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2110_Rpt_Mfs",paramMap);
    }

}
