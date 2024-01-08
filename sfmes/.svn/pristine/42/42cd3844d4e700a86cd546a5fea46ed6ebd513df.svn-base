package com.sfmes.pd.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd2540Service;
import com.sfmes.sm.service.Sm1000Service;

/**
* @Class Name : Pd2540ServiceImpl.java
* @Description : Pd2540Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.11.12   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.11.12
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2540Service")
public class Pd2540ServiceImpl extends CmnAbstractServiceImpl implements Pd2540Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Resource(name = "CommonService")
    private CommonService commonService;
    
    //재고 입출고 등록 서비스 선언
    @Autowired
    private Sm1000Service sm1000Service;     
    
    @Override
    public void insertPd2540(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        //마감여부 체크
        commonService.checkDdl(paramMap);
        
        //공정기록서 기반 작업보고 일괄저장
        for(Map<String, Object> map : paramList){
            
            String s_CORP_C = paramMap.get("CORP_C").toString();
            String s_BZPL_C = paramMap.get("BZPL_C").toString();
            String s_RPT_DT = paramMap.get("RPT_DT").toString();
            
            String seqNo = commonService.getGvno(s_CORP_C,"TB_PD_M_WK_RPT",s_BZPL_C, s_RPT_DT, 1);
            egovLogger.debug("=====작업보고일련번호 채번" +seqNo);
            map.put("RPT_SQNO", seqNo);
            map.put("RPT_DT", paramMap.get("RPT_DT"));
            map.put("RMK_CNTN", paramMap.get("RMK_CNTN"));
            map.put("TR_BSN_DSC", "PD20");
            map.put("MFC_WK_STS_C", "06");
            
            //기본저장
            sqlSession.insert("sfmes.sqlmap.pd.insert_Pd2540List_TB_PD_M_WK_RPT",map);
            //투입물품저장
            sqlSession.insert("sfmes.sqlmap.pd.insert_Pd2540List_TB_PD_D_WK_RPT_MTRL",map);
            //생산물품저장
            sqlSession.insert("sfmes.sqlmap.pd.insert_Pd2540List_TB_PD_D_WK_RPT_MFS",map);
            
            
            //가공상태구분값변경
            sqlSession.update("sfmes.sqlmap.pd.TB_PD_M_WK_DNTT_change",map);
            
            //재고입출고생산(입고)
            List<?> selList1 = null;
            LinkedHashMap if_param_map_01 = new LinkedHashMap();
            List<Map<String, Object>> if_param_List_01 = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> param_list01 = new ArrayList<Map<String, Object>>();
            
            //생산기본list
            //if_param_map_01 = sqlSession.selectOne("sfmes.sqlmap.pd.select_Pd2530List01",map);
            selList1 = sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2530List01",map);
            if_param_map_01.putAll((Map<String, Object>)selList1.get(0));
            if_param_map_01.put("STDV_DSC"    , "I");
            if_param_map_01.put("SLP_NML_YN"  , "Y");
            if_param_map_01.put("RLTR_DT"     , if_param_map_01.get("PD_DT"));
            if_param_map_01.put("TRPL_C"      , "");
            if_param_map_01.put("STDV_STS_DSC", "1");
            if_param_map_01.put("STDV_REF_DSC", "1");
            if_param_map_01.put("GUSRID"      , if_param_map_01.get("FSRG_ID"));
            
            //생산물품list(입고)
            param_list01 = sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2530List03",map);
            for(Map<String, Object> map01 : param_list01){
                LinkedHashMap if_list_map01 = new LinkedHashMap();
                if_list_map01.putAll(map01);
                if_list_map01.put("STDV_QT"            , map01.get("PD_QT"));
                if_list_map01.put("STDV_BOX_QT"        , "1");
                if_list_map01.put("TR_UNT_C"           , map01.get("UNT_C"));
                if_list_map01.put("STDV_UPR"           , map01.get("PD_UPR"));
                if_list_map01.put("SPY_AM"             , map01.get("PD_AM"));
                if_list_map01.put("VAT"                , "0");
                if_list_map01.put("DSTR_TERDT"         , map01.get("DSTR_TER_DT"));
                if_list_map01.put("STDV_AM"            , map01.get("PD_AM"));
                if_list_map01.put("STDV_WT"            , "0");
                if_list_map01.put("HST_AMN_DSC"        , "0"); 
                if_list_map01.put("GDS_HST_NO"         , "");
                if_list_map01.put("BUDL_NO"            , "");
                if_list_map01.put("DEL_YN"             , "N");
                if_list_map01.put("FLAG_STDV_DSC_IO_YN", "N");
                if_list_map01.put("WHSE_C"             , "001");
                
                if_param_List_01.add(if_list_map01);
                
            }
            
            //재고입출고투입(출고)
            List<?> selList2 = null;
            LinkedHashMap if_param_map_02 = new LinkedHashMap();
            List<Map<String, Object>> if_param_List_02 = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> param_list02 = new ArrayList<Map<String, Object>>();
            
            //투입기본list
            //if_param_map_02 = sqlSession.selectOne("sfmes.sqlmap.pd.select_Pd2540List01_master",map);
            selList2 = sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2530List01",map);
            if_param_map_02.putAll((Map<String, Object>)selList2.get(0));
            if_param_map_02.put("STDV_DSC"    , "O");
            if_param_map_02.put("SLP_NML_YN"  , "Y");
            if_param_map_02.put("RLTR_DT"     , if_param_map_02.get("PD_DT"));
            if_param_map_02.put("TRPL_C"      , "");
            if_param_map_02.put("STDV_STS_DSC", "1");
            if_param_map_02.put("STDV_REF_DSC", "1");
            if_param_map_02.put("GUSRID"      , if_param_map_02.get("FSRG_ID"));
            
            //투입물품list(출고)
            param_list02 = sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2530List02",map);
            for(Map<String, Object> map02 : param_list02){
                LinkedHashMap if_list_map02 = new LinkedHashMap();
                if_list_map02.putAll(map02);
                if_list_map02.put("STDV_QT"            , map02.get("MTRL_PTIN_QT"));
                if_list_map02.put("STDV_BOX_QT"        , "1");
                if_list_map02.put("TR_UNT_C"           , map02.get("UNT_C"));
                if_list_map02.put("STDV_UPR"           , map02.get("PTIN_UPR"));
                if_list_map02.put("SPY_AM"             , map02.get("PTIN_AM"));
                if_list_map02.put("VAT"                , "0");
                if_list_map02.put("DSTR_TERDT"         , "");
                if_list_map02.put("STDV_AM"            , map02.get("PTIN_AM"));
                if_list_map02.put("STDV_WT"            , "0");
                if_list_map02.put("GDS_HST_NO"         , "");
                if_list_map02.put("BUDL_NO"            , map02.get("GDS_HST_NO"));
                if_list_map02.put("DEL_YN"             , "N");
                if_list_map02.put("FLAG_STDV_DSC_IO_YN", "Y");
                if_list_map02.put("WHSE_C"             , "ZZZ");
                
                if_param_List_02.add(if_list_map02);
                
            }
            
            //재고함수호출
            sm1000Service.Call_saveSm1000(if_param_map_01, if_param_List_01, if_param_map_02, if_param_List_02);
        }
    }

    @Override
    public List<?> selectPd2540List_01(LinkedHashMap paramMap) throws Exception {
        //공정기록서 작업지시내역조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2540List02", paramMap);
    }

    
}
