package com.sfmes.co.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.Co2020Service;

/**
 * @Class Name  : Co2020ServiceImpl.java
 * @Description : Co2020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.16   김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Co2020Service")
public class Co2020ServiceImpl extends CmnAbstractServiceImpl implements Co2020Service {
   
    @Autowired
    private SqlSessionTemplate sqlSession;

    //공휴일정보를 조회한다.
    @Override
    public List<?> selectCo2020List(LinkedHashMap paramMap) {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo2020List", paramMap);
    }

    //기준연도를 조회한다.
    @Override
    public List<?> selectCo2020_Basyy(LinkedHashMap paramMap) {
        egovLogger.debug("기준년도조회::" + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo2020_Basyy", paramMap);
    }

    //영업주차기준연도를 조회한다.
    @Override
    public List<?> selectCo2020_weekBasyy(LinkedHashMap paramMap) {
     
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo2020_weekBasyy", paramMap);
    }

    //공휴일정보를 수정한다.
    @Override
    public void updateCo2020(List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug(":::::[CO2020LISTUPDQTE]:::::" + paramList);
        
        for(Map<String, Object> map : paramList) {
            if (map.get("_status_").equals("*")) {
                egovLogger.debug(":::::[CO2020UPDATE]:::::" + map);
                sqlSession.update("sfmes.sqlmap.co.updateCo2020",map);
            }
        }
    }
}   
   