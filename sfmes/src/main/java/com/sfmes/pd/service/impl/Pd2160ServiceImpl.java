package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.pd.service.Pd2160Service;

/**
* @Class Name : Pd2160ServiceImpl.java
* @Description : Pd2160Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.09.21   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.09.24
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2160Service")
public class Pd2160ServiceImpl extends CmnAbstractServiceImpl implements Pd2160Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public List<?> selectPd2160List01(LinkedHashMap paramMap) throws Exception {
		return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2160_Rpt_Mfs_01",paramMap);
    }
    
    @Override
    public List<?> selectPd2160List02(LinkedHashMap paramMap) throws Exception {
		return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2160_Rpt_Mfs_02",paramMap);
    }

}
