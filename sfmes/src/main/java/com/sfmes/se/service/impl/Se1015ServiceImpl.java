package com.sfmes.se.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se1015Service;

/**
 * @Class Name : Se1015ServiceImpl.java
 * @Description : 물품별매출단가내역
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.06  김선규   최초생성
 * @ 2020.09.01  곽환용   수정
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se1015Service")
public class Se1015ServiceImpl extends CmnAbstractServiceImpl implements Se1015Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;        

    //물품변매출단가내역
    @Override
    public List<?> selectSe1015_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 물품별매출단가내역[SE1015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe1015_01", paramMap);
    }
}
