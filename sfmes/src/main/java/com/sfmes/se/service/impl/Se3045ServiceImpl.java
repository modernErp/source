package com.sfmes.se.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se3045Service;

/**
 * @Class Name : Se3045ServiceImpl.java
 * @Description : 매출(덤)내역
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.10.30  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se3045Service")
public class Se3045ServiceImpl extends CmnAbstractServiceImpl implements Se3045Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    //전표별매출(덤)기본내역
    @Override
    public List<?> selectSe3045_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표별매출(덤)기본내역[SE3045] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3045_01", paramMap);
    }

    //전표별매출(덤)상세내역
    @Override
    public List<?> selectSe3045_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표별매출(덤)상세내역[SE3045] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3040_02", paramMap);
    }
    
    //전표-물품별매출(덤)내역
    @Override
    public List<?> selectSe3045_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 전표-물품별매출(덤)내역[SE3045] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3045_02", paramMap);
    }
}
