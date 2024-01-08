package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd2015Service;

/**
* @Class Name : Pd2015ServiceImpl.java
* @Description : Pd2015Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.08.11   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.08.11
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2015Service")
public class Pd2015ServiceImpl extends CmnAbstractServiceImpl implements Pd2015Service{

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Override
    public List<?> selectPd2015List_01(LinkedHashMap paramMap) throws Exception {
        //작업지시내역조회
        egovLogger.debug("==============="+paramMap.get("PRW_DSC"));
        if("1".equals(paramMap.get("PRW_DSC"))) {
            egovLogger.debug("===============1");
            //BOM기준
            return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2015List01",paramMap);
        } else {
            egovLogger.debug("===============2");
            //공정기록서기준
            return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2015List02",paramMap);
        }
        
    }
    
    @Override
    public List<?> selectPd2015List_02(LinkedHashMap paramMap) throws Exception {
    	return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2015List03",paramMap);
    }

}
