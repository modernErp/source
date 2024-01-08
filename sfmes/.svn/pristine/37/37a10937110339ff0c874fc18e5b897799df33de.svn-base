package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.pd.service.Pd2130Service;

/**
* @Class Name : Pd2130ServiceImpl.java
* @Description : Pd2130Service Class
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

@Service("Pd2130Service")
public class Pd2130ServiceImpl extends CmnAbstractServiceImpl implements Pd2130Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<?> selectPd2130List_01(LinkedHashMap paramMap) throws Exception {
        //자재투입현황조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2130list_01",paramMap);
    }

}
