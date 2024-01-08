package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd2120Service;

/**
* @Class Name : Pd2120ServiceImpl.java
* @Description : Pd2120Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.09.23   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.09.23
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2120Service")
public class Pd2120ServiceImpl extends CmnAbstractServiceImpl implements Pd2120Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;

    @Override
    public List<?> selectPd2120List_01(LinkedHashMap paramMap) throws Exception {
        // 작업지시대비생산내역(건별)조회
//        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2120list_01",paramMap);
        
        String sql = "";
        
        if("1".equals(paramMap.get("SEARCH_DSC"))){ //1.건별조회 
            sql = "sfmes.sqlmap.pd.select_Pd2120list_01";    
        }else if("2".equals(paramMap.get("SEARCH_DSC"))){ //2.폼목별집계내역
            sql = "sfmes.sqlmap.pd.select_Pd2120list_02";
        }
        return sqlSession.selectList(sql, paramMap);
    }

}
