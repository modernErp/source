package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd3070Service;

/**
* @Class Name : Pd3070ServiceImpl.java
* @Description : Pd3070Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.12.14   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.12.14
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd3070Service")
public class Pd3070ServiceImpl extends CmnAbstractServiceImpl implements Pd3070Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    // 배부차액정리 기본정보 팝업리스트 조회
    @Override
    public List<?> selectPd3070_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd3070_01",paramMap);
    }
    
    // 배부차액정리 기본정보 조회
    @Override
    public List<?> selectPd3070_02(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_PD_M_PCS_CLC",paramMap);
    }
    
    // 배부차액정리 상세정보 조회
    @Override
    public List<?> selectPd3070_03(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd3070_02",paramMap);
    }
}
