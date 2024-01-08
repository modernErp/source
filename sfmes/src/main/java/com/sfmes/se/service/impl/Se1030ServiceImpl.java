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
import com.sfmes.se.service.Se1030Service;

/**
 * @Class Name : Se1030ServiceImpl.java
 * @Description : 가격군별거래처등록
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.09.03  곽환용   최초등록
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se1030Service")
public class Se1030ServiceImpl extends CmnAbstractServiceImpl implements Se1030Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;        
    
    //가격군내역
    @Override
    public List<?> selectSe1030_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 가격군내역[SE1030] *********");
        egovLogger.debug("paramMap: "+paramMap);
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SE_M_PRGR", paramMap);        
    }

    //가격군별거래처내역
    @Override
    public List<?> selectSe1030_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 가격군별거래처내역[SE1030] *********");
        egovLogger.debug("paramMap: "+paramMap);
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SE_M_PRGR_TRPL", paramMap);
    }     
    
    //매출단가부가세포함여부조회
    @Override
    public List<?> Call_selecteSe1030_sl_upr_vat(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매출단가부가세포함여부조회[SE1030] *********");
        egovLogger.debug("paramMap: "+paramMap);
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe1030_sl_upr_vat", paramMap);
    }    
    
    //가격군별거래처등록
    @Override
    public void insertSe1030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {        
        egovLogger.debug("************ 가격군별거래처등록[SE1030] *********");
        egovLogger.debug("paramMap: " + paramMap);
        egovLogger.debug("paramList: " + paramList);
        
        for(Map<String, Object> map : paramList) 
        {
            int chk_cnt = 0;
            
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("PRGR_C", paramMap.get("PRGR_C"));
            
            //Validation Check 실패 시, 예외 메세지 발생
            String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.se.selectSe1030_ValidCheck", map);
            if(! resultMsg.equals("OK")) 
            {
                throw infoException(resultMsg);
            } 
            else 
            {                
                if(map.get("_status_").equals("+")) 
                {
                    egovLogger.debug("************ 등록된 거래처여부체크 ************");
                    chk_cnt = 0;
                    chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe1030_chk1", map);
                    egovLogger.debug("chk_cnt: " + chk_cnt);
                    if(chk_cnt < 1) 
                    {
                        throw infoException("\n거래처코드: " + map.get("TRPL_C").toString() +
                                            "\n거래처명: " + map.get("TRPL_NM").toString() + 
                                            "\n등록되지 않은 거래처입니다. 거래처등록 먼저 진행하십시요.");
                    }

                    egovLogger.debug("************ 가격군별거래처등록여부체크 ************");
                    chk_cnt = 0;
                    chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe1030_chk2", map);
                    egovLogger.debug("chk_cnt: " + chk_cnt);
                    if(chk_cnt > 0) 
                    {
                        throw infoException("\n거래처코드: " + map.get("TRPL_C").toString() +
                                            "\n거래처명: " + map.get("TRPL_NM").toString() + 
                                            "\n이미 가격군에 등록된 거래처입니다.");
                    }
                    
                    egovLogger.debug("가격군별거래처등록 TB_SE_M_PRGR_TRPL");
                    sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_PRGR_TRPL", map);
                } 
                else if(map.get("_status_").equals("*"))
                {
                    egovLogger.debug("가격군별거래처수정 TB_SE_M_PRGR_TRPL");
                    sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_PRGR_TRPL", map);
                }      
                else 
                {
                    egovLogger.debug("가격군별거래처삭제 TB_SE_M_PRGR_TRPL");
                    sqlSession.delete("sfmes.sqlmap.tb.delete_TB_SE_M_PRGR_TRPL", map);
                }
                
                egovLogger.debug("가격군별거래처이력등록 TB_SE_L_PRGR_TRPL");
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_L_PRGR_TRPL", map);
            }
        }
    }
}
