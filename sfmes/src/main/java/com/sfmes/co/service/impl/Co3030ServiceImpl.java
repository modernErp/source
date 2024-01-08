package com.sfmes.co.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.co.service.Co3030Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Co3030ServiceImpl.java
 * @Description : SMS수신대상자내역
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.11.09  이수빈  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.11.09
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Co3030Service")
public class Co3030ServiceImpl extends CmnAbstractServiceImpl implements Co3030Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    
    // SMS수신대상자내역
    @Override
    public List<?> selectCo3030List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo3030List", paramMap);
    }
    
    // SMS수신대상자팝업
    @Override
    public List<?> selectCo3030_01List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo3030_01List", paramMap);
    }
        
    //SMS수신대상자팝업등록   
    @Override
    public void insertCo3030List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        
        for(Map < String, Object > map : paramList) {
            
            map.put("CORP_C", paramMap.get("CORP_C"));
            
            if("+".equals(map.get("_status_").toString() ) ) {
               sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_MSG_RMS_USR", map);
            
            } else if("-".equals(map.get("_status_").toString() ) ) {
               sqlSession.delete("sfmes.sqlmap.tb.delete_TB_CO_M_MSG_RMS_USR", map);
            
            } else if("*".equals(map.get("_status_").toString() ) ) {
               sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_MSG_RMS_USR", map);  
            }
        }
    }
          

    @Override
    public void deleteCo3030List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {

    }

    @Override
    public void updateCo3030List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        
    }
}