package com.sfmes.se.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se4010Service;

/**
 * @Class Name : Se4010ServiceImpl.java
 * @Description : 거래처별배송지등록 및 조회
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.23  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se4010Service")
public class Se4010ServiceImpl extends CmnAbstractServiceImpl implements Se4010Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;        

    //거래처별배송지등록 조회
    @Override
    public List<?> selectSe4010(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 거래처별배송지등록내역[SE4010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe4010", paramMap);
    }
    
    //거래처별등록배송지내역
    @Override
    public List<?> selectSe4015(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 거래처별등록배송지내역[SE4015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe4015", paramMap);
    }    

    //거래처별배송지등록
    @Override
    public void saveSe4010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 거래처별배송지등록[SE4010] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        egovLogger.debug("거래처별배송지등록 TB_SE_M_TRPL_DVYAA");
        for(Map<String, Object> map : paramList) 
        {            
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("TRPL_C", paramMap.get("TRPL_C"));
            
            if(map.get("_status_").equals("+")) 
            {
                egovLogger.debug("입력map: " + map.toString());
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_TRPL_DVYAA", map);
            }
            else 
            {
                egovLogger.debug("수정map: " + map.toString());
                sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_TRPL_DVYAA", map);
            }
        }
    }
}
