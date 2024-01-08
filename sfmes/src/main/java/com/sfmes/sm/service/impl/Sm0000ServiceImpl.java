package com.sfmes.sm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;

import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Sm0000Service;

/**
 * @Class Name  : Sm0000ServiceImpl.java
 * @Description : Sm0000Service Class
 * @Modification Information
 * @
 * @ 수정일              수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2021.04.22   장경석            PDA BARCODE SCAN 내용으로 공통 INFO 구성
 *
 * @author (주)모든솔루션
 * @since 2021.04.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm0000Service")
public class Sm0000ServiceImpl extends CmnAbstractServiceImpl implements Sm0000Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    // BARCODE 내역조회
    @Override
    public List<?> searchSm0000_01(LinkedHashMap paramMap) throws Exception {
        
        List<Map<String, Object>> resultList = null;
        
        egovLogger.debug("paramMap: "+paramMap.toString());
        
        String SCAN_CODE_GBN = (String) paramMap.get("SCAN_CD_GBN");
        
        switch(SCAN_CODE_GBN) {
        case "GD" :   // 물품코드
            egovLogger.debug("************ SCANCODE - 물품코드 내역조회[SM0000] *********");
            resultList = sqlSession.selectList("sfmes.sqlmap.sm.select_SM0000_PDA_GDS", paramMap);
            break;
        case "WK" :   // 작업지시번호
        case "OD" :   // 발주지시번호
            egovLogger.debug("************ SCANCODE - 발주상세내역조회(참조)[SM0000] *********");

            resultList = sqlSession.selectList("sfmes.sqlmap.sm.select_Sm0000_ODR",paramMap);
            break;
        case "RQ" :   // 출고으뢰번호 
        }
        
        return resultList;
    }
}
