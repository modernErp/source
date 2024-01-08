package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.pd.service.Pd1035Service;

/**
* @Class Name : Pd1035ServiceImpl.java
* @Description : Pd1035Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.22   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.22
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd1035Service")
public class Pd1035ServiceImpl extends CmnAbstractServiceImpl implements Pd1035Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<?> selectPd1035List_01(LinkedHashMap paramMap) throws Exception {
        //공정기록서조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_pd1035_list01",paramMap);
    }

}
