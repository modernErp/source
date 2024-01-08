package com.sfmes.sm.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Sm1025Service;

/**
 * @Class Name  : Sm1025ServiceImpl.java
 * @Description : Sm1025Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.08.19   김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.08.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm1025Service")
public class Sm1025ServiceImpl extends CmnAbstractServiceImpl implements Sm1025Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    //생산투입출고의뢰내역기본조회
    @Override
    public List<?> search_sm1025_01(LinkedHashMap paramMap) throws Exception {
       egovLogger.debug(":::::[SM1025생산투입출고의뢰기본내역조회]:::::" + paramMap);
       return sqlSession.selectList("sfmes.sqlmap.sm.search_sm1025_01", paramMap);
    }

    //생산투입출고의뢰내역상세조회
    @Override
    public List<?> search_sm1025_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug(":::::[SM1025생산투입출고의뢰상세내역조회]:::::" + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.sm.search_sm1025_02", paramMap);
     }

    //생산투입출고의뢰내역조회(물품별)
    @Override
    public List<?> search_sm1025_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug(":::::[SM1025생산투입출고의뢰내역조회(물품별)]:::::" + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.sm.search_sm1025_03", paramMap);
     }

}
