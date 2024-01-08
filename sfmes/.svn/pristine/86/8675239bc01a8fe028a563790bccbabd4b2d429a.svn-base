package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.pd.service.Pd2515Service;

/**
* @Class Name : Pd2515ServiceImpl.java
* @Description : Pd2515Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.16   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.16
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2515Service")
public class Pd2515ServiceImpl extends CmnAbstractServiceImpl implements Pd2515Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<?> selectPd2515List_01(LinkedHashMap paramMap) throws Exception {
        //공정기록서 기반 작업지시 내역조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select2515_List_01",paramMap);
    }

}
