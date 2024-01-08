package com.sfmes.pd.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd2520Service;

/**
* @Class Name : Pd2520Service.java
* @Description : Pd2520Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.27   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.27
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2520Service")
public class Pd2520ServiceImpl extends CmnAbstractServiceImpl implements Pd2520Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;

    

    @Override
    public void insertPd2520(LinkedHashMap paramMap, List<Map<String, Object>> paramList01,
            List<Map<String, Object>> paramList02, List<Map<String, Object>> paramList03) throws Exception {
        //작업지시일괄등록
        
        //작업지시기본저장
        for(Map<String, Object> map01 : paramList01) {
            map01.putAll(paramMap);
            
            String s_CORP_C = map01.get("CORP_C").toString();
            String s_BZPL_C = map01.get("BZPL_C").toString();
            String s_DNTT_DT = map01.get("DNTT_DT").toString();
            
            //지시일련번호 채번
            String seqNo = commonService.getGvno(s_CORP_C,"TB_PD_M_WK_DNTT",s_BZPL_C, s_DNTT_DT, 1);
            egovLogger.debug("=====작업지시일련번호 채번" +seqNo);
            map01.put("DNTT_SQNO", seqNo);
            
            //거래일련번호
            String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
            egovLogger.debug("=====작업지시거래일련번호 채번" +tr_seqNo);
            map01.put("TR_SQNO", tr_seqNo);
            map01.put("MFC_WK_STS_C", "01");
            map01.put("TR_BSN_DSC", "PD10");
            
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_M_WK_DNTT",map01);
            
            //작업지시투입물품저장
            for(Map<String, Object> map02 : paramList02){
                map02.put("CORP_C", paramMap.get("CORP_C"));
                map02.put("BZPL_C", paramMap.get("BZPL_C"));
                map02.put("DNTT_DT", paramMap.get("DNTT_DT"));
                map02.put("DNTT_DNO", "00");
                
                if(!"".equals(map02.get("GDS_HST_NO"))){
                    String gds_no = ((String) map02.get("GDS_HST_NO")).substring(0,1);
                    if("L".equals(gds_no)) {
                        map02.put("GDS_NO", "");
                    } else {
                        map02.put("GDS_NO", gds_no);
                    }
                    egovLogger.debug("==============이력번호비교"+map02.get("GDS_NO"));
                    
                    //물품이력번호 ValidCheck
                    String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.pd.selectPd2010_HSTNO_ValidCheck", map02);
                    if(!"OK".equals(resultMsg)) {
                        throw infoException(resultMsg);
                    }
                }
                
                if(map02.get("PRW_C").equals(map01.get("PRW_C"))){
                    //공정코드가 같은 투입품은 마스터같은 작업지시번호
                    map02.put("DNTT_SQNO", map01.get("DNTT_SQNO"));
                    sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_DNTT_MTRL",map02);
                } 
                
            }
            
            //작업지시생산물품저장
            for(Map<String, Object> map03 : paramList03){
                map03.put("CORP_C", paramMap.get("CORP_C"));
                map03.put("BZPL_C", paramMap.get("BZPL_C"));
                map03.put("DNTT_DT", paramMap.get("DNTT_DT"));
                map03.put("DNTT_DNO", "00");
                
                if(map03.get("PRW_C").equals(map01.get("PRW_C"))){
                    //공정코드가 같은 투입품은 마스터같은 작업지시번호
                    map03.put("DNTT_SQNO", map01.get("DNTT_SQNO"));
                    sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_DNTT_MFS",map03);
                } 
                
            }
            
        }
        
    }
    
    @Override
    public List<?> selectPd2520List_01(LinkedHashMap paramMap) throws Exception {
        // 공정기록서 투입품상세(저장전)
        return sqlSession.selectList("sfmes.sqlmap.pd.select2520_List_01",paramMap);
    }

    @Override
    public List<?> selectPd2520List_02(LinkedHashMap paramMap) throws Exception {
        // 공정기록서 생산품상세(저장전)
        return sqlSession.selectList("sfmes.sqlmap.pd.select2520_List_02",paramMap);
    }

    

}
