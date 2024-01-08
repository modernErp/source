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
import com.sfmes.pd.service.Pd2040Service;
import com.sfmes.sm.service.Sm1000Service;

/**
 * @Class Name : Pd2040ServiceImpl.java
 * @Description : 작업지시일괄등록 등록
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2040.08.10  김종관  최초생성
 *
 * @author (주)모든솔루션
 * @since 2040.08.
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Pd2040Service")
public class Pd2040ServiceImpl extends CmnAbstractServiceImpl implements Pd2040Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Resource(name = "CommonService")
    private CommonService commonService;
    
    //재고 입출고 등록 서비스 선언
    @Autowired
    private Sm1000Service sm1000Service; 
    
    @Override
    public void insertPd2040(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        //마감여부 체크
        commonService.checkDdl(paramMap);
        
        for(Map<String, Object> map : paramList){

            String s_CORP_C = paramMap.get("CORP_C").toString();
            String s_BZPL_C = paramMap.get("BZPL_C").toString();
            String s_RPT_DT = paramMap.get("RPT_DT").toString();                  

            //작업보고일련번호 채번
            String seqNo = commonService.getGvno(s_CORP_C,"TB_PD_M_WK_RPT",s_BZPL_C, s_RPT_DT, 1);
            egovLogger.debug("======작업보고일련번호 채번" +seqNo);
            paramMap.put("RPT_SQNO", seqNo);
                
            map.put("RPT_DT", paramMap.get("RPT_DT"));
            map.put("RMK_CNTN", paramMap.get("RMK_CNTN"));
            map.put("RPT_SQNO", paramMap.get("RPT_SQNO"));
            map.put("PD_METH_C", paramMap.get("PD_METH_C"));
            map.put("DT_DSC", paramMap.get("DT_DSC"));
            map.put("ST_DT", paramMap.get("ST_DT"));
            map.put("ED_DT", paramMap.get("ED_DT"));
            map.put("SM_IF_YN", paramMap.get("SM_IF_YN"));    // 20220103 rchkorea 추가 
            
            //거래업무구분코드(PD20작업보고)
            map.put("TR_BSN_DSC", "PD20");
            //작업지시 상태값 변경
            map.put("MFC_WK_STS_C", "06");

            //채번 서비스 호출(거래일련번호)
            String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
            egovLogger.debug("생성된 거래일련번호 채번: " + tr_seqNo);
            map.put("TR_SQNO", tr_seqNo);            
            
            //작업보고기본 등록
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_M_WK_RPT",map);

            //작업보고제품상세 등록
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_RPT_MFS",map);

            egovLogger.debug("=================map"+map);
            
            //2021.10.28 서광석
            //자재투입량 계산을 위한 생산율(%) 추가
            //작업지시량 String -> int
            String strMfsDnttQt = paramList.get(0).get("MFS_DNTT_QT").toString();
            float mfsDnttQt = Float.parseFloat(strMfsDnttQt);            
            
            //생산량 String -> int
            String strPdQt = paramList.get(0).get("PD_QT").toString();
            float pdQt = Float.parseFloat(strPdQt);
            
            //작업지시중 생산완료된 비율
            float pdRatio = pdQt / mfsDnttQt;            
            map.put("pdRatio", pdRatio);            
            
            //작업보고자재상세 등록
            sqlSession.insert("sfmes.sqlmap.pd.insert_TB_PD_D_WK_RPT_MTRL01_TEMP",map);

            //작업지시 상태값 변경
            sqlSession.update("sfmes.sqlmap.pd.TB_PD_D_WK_DNTT_MFS_change_2021",map);
            
            //재고입출고생산(입고)
            List<?> selList1 = null;
            LinkedHashMap if_param_map_01 = new LinkedHashMap();
            List<Map<String, Object>> if_param_List_01 = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> param_list01 = new ArrayList<Map<String, Object>>();
            
            //생산기본list
            selList1 = sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2530List01",map);
            
            if_param_map_01.putAll((Map<String, Object>)selList1.get(0));
            if_param_map_01.put("STDV_DSC"    , "I");
            if_param_map_01.put("SLP_NML_YN"  , "Y");
            if_param_map_01.put("RLTR_DT"     , if_param_map_01.get("PD_DT"));
            if_param_map_01.put("TRPL_C"      , "");
            if_param_map_01.put("STDV_STS_DSC", "1");
            if_param_map_01.put("STDV_REF_DSC", "1");
            if_param_map_01.put("GUSRID"      , if_param_map_01.get("FSRG_ID"));
            if_param_map_01.put("TR_SQNO"     , tr_seqNo);
            
            //생산물품list(입고)
            param_list01 = sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2030List01",map);
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
            
            egovLogger.debug("===================생산기본"+if_param_map_01);
            egovLogger.debug("===================생산리스트"+if_param_List_01);
            
            //재고입출고투입(출고)
            List<?> selList2 = null;
            LinkedHashMap if_param_map_02 = new LinkedHashMap();
            List<Map<String, Object>> if_param_List_02 = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> param_list02 = new ArrayList<Map<String, Object>>();
            
            //투입기본list
            selList2 = sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2530List01",map);
            if_param_map_02.putAll((Map<String, Object>)selList2.get(0));
            if_param_map_02.put("STDV_DSC"    , "O");
            if_param_map_02.put("SLP_NML_YN"  , "Y");
            if_param_map_02.put("RLTR_DT"     , if_param_map_02.get("PD_DT"));
            if_param_map_02.put("TRPL_C"      , "");
            if_param_map_02.put("STDV_STS_DSC", "1");
            if_param_map_02.put("STDV_REF_DSC", "1");
            if_param_map_02.put("GUSRID"      , if_param_map_02.get("FSRG_ID"));
            if_param_map_02.put("TR_SQNO"     , tr_seqNo);
            
            //투입물품list(출고)
            //2021.10.28 서광석
            //map 오류 수정 map02로 들어가야 되는데 as-is map 으로 들어가있어서 자재출고내역 이 상이하게 나옴
            param_list02 = sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2030List02",map);
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
            
            egovLogger.debug("===================투입기본"+if_param_map_02);
            egovLogger.debug("===================투입리스트"+if_param_List_02);
            
            //재고함수호출
            //재고함수호출
            if ("1".equals(paramMap.get("SM_IF_YN"))) {
                // 재고연동 체크일때만 처리
                egovLogger.debug("===============재고연동여부 연동");

                sm1000Service.Call_saveSm1000(if_param_map_01, if_param_List_01, if_param_map_02, if_param_List_02);
            } else {
                egovLogger.debug("===============재고연동여부 연동 안함");
            }
            
        }
    }

    
    @Override   // 작업보고등록 바코드라벨 적용 등록    rchkorea
    public void insertPd2040_11(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        //마감여부 체크
        commonService.checkDdl(paramMap);
        
        for(Map<String, Object> map : paramList){

            String s_CORP_C = paramMap.get("CORP_C").toString();
            String s_BZPL_C = paramMap.get("BZPL_C").toString();
            String s_RPT_DT = paramMap.get("RPT_DT").toString();                  

            //작업보고일련번호 채번
            String seqNo = commonService.getGvno(s_CORP_C,"TB_PD_M_WK_RPT",s_BZPL_C, s_RPT_DT, 1);
            egovLogger.debug("======작업보고일련번호 채번" +seqNo);
            paramMap.put("RPT_SQNO", seqNo);
                
            map.put("RPT_DT", paramMap.get("RPT_DT"));
            map.put("RMK_CNTN", paramMap.get("RMK_CNTN"));
            map.put("RPT_SQNO", paramMap.get("RPT_SQNO"));
            map.put("PD_METH_C", paramMap.get("PD_METH_C"));
            map.put("DT_DSC", paramMap.get("DT_DSC"));
            map.put("ST_DT", paramMap.get("ST_DT"));
            map.put("ED_DT", paramMap.get("ED_DT"));
            map.put("SM_IF_YN", paramMap.get("SM_IF_YN"));    // 20220103 rchkorea 추가 
            
            //거래업무구분코드(PD20작업보고)
            map.put("TR_BSN_DSC", "PD20");
            //작업지시 상태값 변경
            map.put("MFC_WK_STS_C", "06");

            //채번 서비스 호출(거래일련번호)
            String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
            egovLogger.debug("생성된 거래일련번호 채번: " + tr_seqNo);
            map.put("TR_SQNO", tr_seqNo);            
            
            //작업보고기본 등록
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_M_WK_RPT",map);

            //작업보고제품상세 등록
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_RPT_MFS",map);

            egovLogger.debug("=================map"+map);
            
            // 바코드라벨투입에 따른 투입저장 
            //작업보고자재상세 등록
            sqlSession.insert("sfmes.sqlmap.pd.insert_TB_PD_D_WK_RPT_MTRL11",map);

            //작업지시 상태값 변경
            sqlSession.update("sfmes.sqlmap.pd.TB_PD_D_WK_DNTT_MFS_change_2021",map);
            
            //재고입출고생산(입고)
            List<?> selList1 = null;
            LinkedHashMap if_param_map_01 = new LinkedHashMap();
            List<Map<String, Object>> if_param_List_01 = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> param_list01 = new ArrayList<Map<String, Object>>();
            
            //생산기본list
            selList1 = sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2530List01",map);
            
            if_param_map_01.putAll((Map<String, Object>)selList1.get(0));
            if_param_map_01.put("STDV_DSC"    , "I");
            if_param_map_01.put("SLP_NML_YN"  , "Y");
            if_param_map_01.put("RLTR_DT"     , if_param_map_01.get("PD_DT"));
            if_param_map_01.put("TRPL_C"      , "");
            if_param_map_01.put("STDV_STS_DSC", "1");
            if_param_map_01.put("STDV_REF_DSC", "1");
            if_param_map_01.put("GUSRID"      , if_param_map_01.get("FSRG_ID"));
            if_param_map_01.put("TR_SQNO"     , tr_seqNo);
            
            //생산물품list(입고)
            param_list01 = sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2030List01",map);
            for(Map<String, Object> map01 : param_list01){
                LinkedHashMap if_list_map01 = new LinkedHashMap();
                if_list_map01.putAll(map01);
                // if_list_map01.put("STDV_QT"            , map01.get("PD_QT"));
                // if_list_map01.put("STDV_BOX_QT"        , "1"); 
                if_list_map01.put("STDV_QT"            , "0");    // 생산수량은 전자저울 에서 자동 등록됨 따라서 수량없이 금액만 저장 20211122 rchkorea
                if_list_map01.put("STDV_BOX_QT"        , "0");    // 20211122 rchkorea
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
            
            egovLogger.debug("===================생산기본"+if_param_map_01);
            egovLogger.debug("===================생산리스트"+if_param_List_01);
            
            //재고입출고투입(출고)
            List<?> selList2 = null;
            LinkedHashMap if_param_map_02 = new LinkedHashMap();
            List<Map<String, Object>> if_param_List_02 = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> param_list02 = new ArrayList<Map<String, Object>>();
            
            // 바코드라벨 작업보고의 경우 이미 생산물품의 수량, 투입물품 수량이 바코드출고로 생산라인에 투입 처리되어있어 중복됨 
            // 따라서 사용하지 않음 재고 함수호출 주석 처리 20211117 rchkorea
            //투입기본list
//            selList2 = sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2530List01",map);
//            if_param_map_02.putAll((Map<String, Object>)selList2.get(0));
//            if_param_map_02.put("STDV_DSC"    , "O");
//            if_param_map_02.put("SLP_NML_YN"  , "Y");
//            if_param_map_02.put("RLTR_DT"     , if_param_map_02.get("PD_DT"));
//            if_param_map_02.put("TRPL_C"      , "");
//            if_param_map_02.put("STDV_STS_DSC", "1");
//            if_param_map_02.put("STDV_REF_DSC", "1");
//            if_param_map_02.put("GUSRID"      , if_param_map_02.get("FSRG_ID"));
//            if_param_map_02.put("TR_SQNO"     , tr_seqNo);
//            
//            //투입물품list(출고)
//            //2021.10.28 서광석
//            //map 오류 수정 map02로 들어가야 되는데 as-is map 으로 들어가있어서 자재출고내역 이 상이하게 나옴
//            param_list02 = sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2030List02",map);
//            for(Map<String, Object> map02 : param_list02){
//                LinkedHashMap if_list_map02 = new LinkedHashMap();
//                if_list_map02.putAll(map02);
//                if_list_map02.put("STDV_QT"            , map02.get("MTRL_PTIN_QT"));
//                if_list_map02.put("STDV_BOX_QT"        , "1");
//                if_list_map02.put("TR_UNT_C"           , map02.get("UNT_C"));
//                if_list_map02.put("STDV_UPR"           , map02.get("PTIN_UPR"));
//                if_list_map02.put("SPY_AM"             , map02.get("PTIN_AM"));
//                if_list_map02.put("VAT"                , "0");
//                if_list_map02.put("DSTR_TERDT"         , "");
//                if_list_map02.put("STDV_AM"            , map02.get("PTIN_AM"));
//                if_list_map02.put("STDV_WT"            , "0");
//                if_list_map02.put("GDS_HST_NO"         , "");
//                if_list_map02.put("BUDL_NO"            , map02.get("GDS_HST_NO"));
//                if_list_map02.put("DEL_YN"             , "N");
//                if_list_map02.put("FLAG_STDV_DSC_IO_YN", "Y");
//                if_list_map02.put("WHSE_C"             , "ZZZ");
//                
//                if_param_List_02.add(if_list_map02);
//                
//            }
            
            egovLogger.debug("===================투입기본"+if_param_map_02);
            egovLogger.debug("===================투입리스트"+if_param_List_02);
            
            //재고함수호출
            if ("1".equals(paramMap.get("SM_IF_YN"))) {
                // 재고연동 체크일때만 처리
                egovLogger.debug("===============재고연동여부 연동");

                sm1000Service.Call_saveSm1000(if_param_map_01, if_param_List_01, if_param_map_02, if_param_List_02);
            } else {
                egovLogger.debug("===============재고연동여부 연동 안함");
            }
            
            
        }
    }

    
    
    
    // 제품상세 조회(작업지시기준)
    @Override
    public List<?> selectPd2040List_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("===== paramMap : " + paramMap);
        //return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2040_01", paramMap);
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2041_01", paramMap);
    }
    
    // 제품상세 조회(생산내역기준)
    @Override
    public List<?> selectPd2040List_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("===== paramMap : " + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2041_02", paramMap);
    }
    
    // 투입자재상세내역 조회
    @Override
    public List<?> selectPd2040List_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("===== paramMap : " + paramMap);
        
        //작업지시량 String -> int
        String strMfsDnttQt = (String)paramMap.get("MFS_DNTT_QT");
        float mfsDnttQt = Float.parseFloat(strMfsDnttQt);
        
        //생산량 String -> int
        String strPdQt = (String)paramMap.get("PD_QT");
        float pdQt = Float.parseFloat(strPdQt);
        
        //작업지시중 생산완료된 비율
        float pdRatio = pdQt / mfsDnttQt;
        
        paramMap.put("pdRatio", pdRatio);
        
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2041_03", paramMap);
    }
    
    // 투입물품  상세 조회(투입물품 바코드라벨 기준)  20211110 rchkorea
    @Override
    public List<?> selectPd2040List_13(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("===== paramMap : " + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2041_13", paramMap);
    }
    
}