package com.sfmes.co.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.Co2015Service;

/**
* @Class Name : Co2015ServiceImpl.java
* @Description : Co2015Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.06.30   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.06.30
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/
@Service("Co2015Service")
public class Co2015ServiceImpl extends CmnAbstractServiceImpl implements Co2015Service{

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public List<?> selectCo2015List_01(LinkedHashMap paramMap) throws Exception {
        //거래처내역조회
        return sqlSession.selectList("sfmes.sqlmap.co.select2015List_01",paramMap);
    }

    @Override
    public List<?> selectCo2015List_02(LinkedHashMap paramMap) throws Exception {
        //거래처정보변경이력조회
        return sqlSession.selectList("sfmes.sqlmap.co.select2015List_02",paramMap);
    }

    @Override
    public List<?> selectCo2015List_03(LinkedHashMap paramMap) throws Exception {
        //거래처팝업조회
        return sqlSession.selectList("sfmes.sqlmap.co.select2015List_03",paramMap);
    }

    @Override
    public List<?> selectCo2015List_04(LinkedHashMap paramMap) throws Exception {
        //거래처계약정보변경이력조회
        return sqlSession.selectList("sfmes.sqlmap.co.select2015List_04",paramMap);
    }

}
