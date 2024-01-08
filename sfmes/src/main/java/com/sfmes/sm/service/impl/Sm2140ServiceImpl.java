package com.sfmes.sm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Sm2140Service;

/**
 * @Class Name  : Sm2140ServiceImpl.java
 * @Description : Sm2140Service Class
 * @Modification Information
 * @
 * @  수정일              수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.09.14   정성환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm2140Service")
public class Sm2140ServiceImpl extends CmnAbstractServiceImpl implements Sm2140Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    //품원장건별내역
    @Override
    public List<?> select_Sm2140_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm2140_01", paramMap);
    }    
}
