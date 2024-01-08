package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd2035Service;

/**
* @Class Name : Pd2035ServiceImpl.java
* @Description : Pd2035Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.08.24   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.08.24
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2035Service")
public class Pd2035ServiceImpl extends CmnAbstractServiceImpl implements Pd2035Service{

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Override
    public List<?> selectPd2035List_01(LinkedHashMap paramMap) throws Exception {
        //작업보고내역조회
        egovLogger.debug("==============="+paramMap.get("PRW_DSC"));
        if("1".equals(paramMap.get("PRW_DSC"))) {
            egovLogger.debug("===============1");
            //BOM기준
            return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2035List01",paramMap);
        } else {
            egovLogger.debug("===============2");
            //공정기록서기준
            return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2035List02",paramMap);
        }
    }

}
