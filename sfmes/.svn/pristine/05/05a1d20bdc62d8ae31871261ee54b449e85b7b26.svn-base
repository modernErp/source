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
import com.sfmes.se.service.Se4020Service;

/**
 * @Class Name  : Se4020ServiceImpl.java
 * @Description : Se4020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.23   김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.23 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Se4020Service")
public class Se4020ServiceImpl extends CmnAbstractServiceImpl implements Se4020Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    //배송차량등록[SE4020]
    @Override
    public void insert_Se4020(LinkedHashMap paramMap) throws Exception {
        
        int V_CNT = sqlSession.selectOne("sfmes.sqlmap.se.select_validationSe4020",paramMap);
        egovLogger.debug("validtation v_Cnt check" + V_CNT);
        
        if(V_CNT > 0){
            throw infoException("중복된 차량번호가 존재합니다. 확인 후 다시 처리하십시오.");
        }else {
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_DVY_VHC", paramMap);
        }
        
        
    }
    //배송차량수정[SE4020]
    @Override
    public void update_Se4020(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug(":::::[SE4020_UPDATE]:::::" + paramMap);
        sqlSession.insert("sfmes.sqlmap.tb.update_TB_SE_M_DVY_VHC", paramMap);
    }
    
    //배송차량조회[SE4020]
    @Override
    public List<?> selct_Se4020(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug(":::::[SE4020_SEARCH]:::::" + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SE_M_DVY_VHC", paramMap);
    }
    
    //배송차량내역[SE4025]
    @Override
    public List<?> select_Se4025(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug(":::::[SE4025_SEARCH]:::::" + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.se.select_Se4025", paramMap);
    }
  
}
