package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.pd.service.Pd2180Service;

/**
* @Class Name : Pd2180ServiceImpl.java
* @Description : Pd2180Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.09.28   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.09.28
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2180Service")
public class Pd2180ServiceImpl extends CmnAbstractServiceImpl implements Pd2180Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<?> selectPd2180List_01(LinkedHashMap paramMap) throws Exception {
        // 제품/자재상세(전표별)
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2180list_01",paramMap);
    }

}
