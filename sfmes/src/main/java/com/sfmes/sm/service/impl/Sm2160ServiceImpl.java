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
import com.sfmes.sm.service.Sm2160Service;

/**
 * @Class Name  : Sm2160ServiceImpl.java
 * @Description : Sm2160Service Class
 * @Modification Information
 * @
 * @  수정일              수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.07   정성환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm2160Service")
public class Sm2160ServiceImpl extends CmnAbstractServiceImpl implements Sm2160Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Override
    public List<?> select_Sm2160_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm2160_01", paramMap);
    }    
}
