package com.sfmes.sm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Sm1040Service;

/**
 * @Class Name : Sm1040ServiceImpl.java
 * @Description : 출고지시등록
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.06  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm1040Service")
public class Sm1040ServiceImpl extends CmnAbstractServiceImpl implements Sm1040Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;        

    //수주마감기본내역 조회
    @Override
    public List<?> selectSm1040_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주마감기본내역[SM1040] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.sm.selectSm1040_01", paramMap);
    }
    
    //수주마감상세내역 조회
    @Override
    public List<?> selectSm1040_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주마감상세내역[SM1040] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.sm.selectSm1040_02", paramMap);
    } 
    
    //출고지시기본내역찾기(팝업)
    @Override
    public List<?> selectSm1040_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시기본내역찾기팝업[SM1040P01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.sm.selectSm1040_03", paramMap);
    }
    
    //출고지시상세내역찾기(팝업)
    @Override
    public List<?> selectSm1040_04(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시상세내역찾기팝업[SM1040P01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.sm.selectSm1040_04", paramMap);
    }      
    
    //출고지시등록
    @Override
    public void insertSm1040(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 출고지시등록[SM1040] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        egovLogger.debug("paramList1: " + paramList.toString());

        List<Map<String, Object>> paramList2 = new LinkedList();
        
        //채번을 위한 변수설정
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        
        //오늘날짜구하기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
            
        //출고지시등록
        egovLogger.debug("출고지시기본내역 TB_SM_M_DLR");
        for(Map<String, Object> map : paramList) {
            //채번 서비스를 호출한다.
            String seqNo = commonService.getGvno(s_CORP_C, "TB_SM_M_DLR", s_BZPL_C, strToday, 1);
            egovLogger.debug("생성된 출고지시일련번호: " + seqNo);  
            
            map.put("BZPL_C"          , paramMap.get("BZPL_C").toString());
            map.put("DLR_DNTT_DT"     , strToday);
            map.put("DLR_DNTT_SQNO"   , seqNo);
            map.put("DLR_DNTT_STS_DSC", "1");
            
            egovLogger.debug("map : " + map.toString());            
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_M_DLR", map);    
            
            //수주상태(3:출고등록) 및 출구지시일자,출고지시번호 수정
            sqlSession.update("sfmes.sqlmap.sm.updateSm1040_rvo", map); 
            
            egovLogger.debug("출고지시상세내역 TB_SM_D_DLR");
            //수주마감상세내역조회 후 출고지시상세내역에 저장
            paramList2 = sqlSession.selectList("sfmes.sqlmap.se.selectSe2030_04", map);
            paramList2.get(0).put("DLR_DNTT_DT", strToday);
            paramList2.get(0).put("DLR_DNTT_SQNO", seqNo);
            //사용자아이디 담기
            paramList2.get(0).put("GUSRID", paramMap.get("GUSRID"));
            paramList2.get(0).put("GUSRID", paramMap.get("GUSRID"));
          
            egovLogger.debug("paramList2 : " + paramList2.toString());            
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_D_DLR", paramList2.get(0));
        }          
    }

    //제품출고등록에서 출고지시참조 시 출고지시상태구분 수정
    @Override
    public void Call_updateSm1040(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시상태구분수정[SM1040] *********");
        paramMap.put("DLR_DNTT_DT"     , paramMap.get("STDV_REF_DT"));
        paramMap.put("DLR_DNTT_SQNO"   , paramMap.get("STDV_REF_SQNO"));
        
        egovLogger.debug("출고지시기본내역수정 TB_SM_M_DLR");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        sqlSession.insert("sfmes.sqlmap.sm.updateSm1040_STS", paramMap);
        
    }
}
