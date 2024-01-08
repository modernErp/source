package com.sfmes.co.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Pack200;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.co.service.Co3010Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Co3010ServiceImpl.java
 * @Description : SMS문구등록
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.11.02  이수빈  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.11.02
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Co3010Service")
public class Co3010ServiceImpl extends CmnAbstractServiceImpl implements Co3010Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    
    // SMS문구조회
    @Override
    public List<?> selectCo3010List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo3010List", paramMap);
    }

    
    // SMS문구조회_01
    @Override
    public List<?> selectCo3010_01List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo3010_01List", paramMap);
    }
    
    @Override
    public void insertCo3010List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug(":::::[CO3010INSERT]::::::" + paramMap);
    
        for(Map<String, Object> map : paramList) {
            
            map.put("CORP_C", paramMap.get("CORP_C"));
            
            if( "+".equals( map.get("_status_").toString() ) ) {
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_SMS_MSG", map);
                
            } else if( "-".equals( map.get("_status_").toString() ) ) {
                sqlSession.delete("sfmes.sqlmap.tb.delete_TB_CO_M_SMS_MSG", map);
                
            } else if( "*".equals( map.get("_status_").toString() ) ) {
                sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_SMS_MSG", map);
            }
        }
    }

    @Override
    public void updateCo3010List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
       
    }

    @Override
    public void deleteCo3010List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        
    }
}