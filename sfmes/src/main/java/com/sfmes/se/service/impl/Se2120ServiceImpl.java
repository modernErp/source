package com.sfmes.se.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.se.service.Se2120Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;

/**
 * @Class Name  : Se2120Service.java
 * @Description : Se2120Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.09.16   김지혜     최초생성
 * @ 2022.04.15   나명우     출고대상조회 추가
 *
 * @author (주)모든솔루션
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se2120Service")
public class Se2120ServiceImpl extends CmnAbstractServiceImpl implements Se2120Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    //수주대비(미)출고현황 조회
    @Override
    public List<?> selectSe2120(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주대비(미)출고현황조회[SE2120] ************");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe2120", paramMap);
    }

    @Override//(미)출고 대상 조회 2022.04.18 나명우 추가
    public List<?> selectSe2120_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주대비(미)출고대상조회[SE2120] ************");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe2120_02", paramMap);
    }

    @Override//(미)출고 내용 수정 2022.04.18 나명우 추가
    public String updateSe2120(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주대비(미)출고대상 수정 Update [SE2120] ************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        String result = "";
        
        int chk_cnt = 0;
      //  String conn_id = paramMap.get("CONN_ID").toString();
        String rol_dsc = paramMap.get("ROLE_DSC").toString();
        
        //존재 내역 체크
        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe2120Cnt", paramMap);        
        if(chk_cnt == 0) 
        {
            throw infoException("(미)출고 내역이 존재하지 않습니다.");
        }
        
       // String fsrg_id = sqlSession.selectOne("sfmes.sqlmap.se.selectSe2120USRCHK", paramMap);
        if( rol_dsc.equals("99") ) {
            egovLogger.debug("수주대비(미)출고 대상 수정 TB_SE_M_RVO");
            egovLogger.debug("수정 paramMap: " + paramMap.toString());
            sqlSession.update("sfmes.sqlmap.se.update_TB_SE_M_RVO", paramMap);
            result = "OK";
        } else {
            throw infoException("관리자만 변경가능합니다.");
        }
        
        return result;
    }
    
    
}
