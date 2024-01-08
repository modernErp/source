package com.sfmes.pd.service.impl;

import java.util.ArrayList;
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
import com.sfmes.pd.service.Pd2530Service;
import com.sfmes.sm.service.Sm1000Service;

/**
* @Class Name : Pd2530ServiceImpl.java
* @Description : Pd2530Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.11.04   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.11.04
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2530Service")
public class Pd2530ServiceImpl extends CmnAbstractServiceImpl implements Pd2530Service{

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    //재고 입출고 등록 서비스 선언
    @Autowired
    private Sm1000Service sm1000Service;     

    
    @Override
    public void insertPd2530(LinkedHashMap paramMap, List<Map<String, Object>> paramList01,
            List<Map<String, Object>> paramList02) throws Exception {
        // 공정기록서 기반 작업보고등록
        // 마감여부 체크
        commonService.checkDdl(paramMap);
                
        // 보고일련번호 채번
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_RPT_DT = paramMap.get("RPT_DT").toString();
        String seqNo = commonService.getGvno(s_CORP_C,"TB_PD_M_WK_RPT",s_BZPL_C, s_RPT_DT, 1);
        egovLogger.debug("=====작업보고일련번호 채번" +seqNo);
        paramMap.put("RPT_SQNO", seqNo);
        
        //거래업무구분코드(PD20작업보고)
        paramMap.put("TR_BSN_DSC", "PD20");
        paramMap.put("MFC_WK_STS_C", "06");
        egovLogger.debug("=====거래업무구분코드" +paramMap.get("TR_BSN_DSC"));
        egovLogger.debug("=====거래일련번호" +paramMap.get("TR_SQNO"));
        egovLogger.debug("=====가공작업상태" +paramMap.get("MFC_WK_STS_C"));
        
        //기본정보저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_M_WK_RPT",paramMap);
        //작업지시 상태값 변경
        sqlSession.insert("sfmes.sqlmap.pd.TB_PD_M_WK_DNTT_change",paramMap);
        
        for(Map<String, Object> map : paramList01){
            //투입자재상세저장
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("RPT_DT", paramMap.get("RPT_DT"));
            map.put("RPT_SQNO", paramMap.get("RPT_SQNO"));
            
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_RPT_MTRL",map);
            
        }
        
        for(Map<String, Object> map : paramList02){
            //제품상세저장
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("RPT_DT", paramMap.get("RPT_DT"));
            map.put("RPT_SQNO", paramMap.get("RPT_SQNO"));
            
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_RPT_MFS",map);
            
        }
        
        //재고이동 호출
        this.callPd2530(paramMap, paramList01, paramList02);

    }

    @Override
    public void updatePd2530(LinkedHashMap paramMap, List<Map<String, Object>> paramList01,
            List<Map<String, Object>> paramList02) throws Exception {
        // 공정기록서 기반 작업보고변경
        // 마감여부 체크
        commonService.checkDdl(paramMap);
                
        if("Y".equals(paramMap.get("DEL_YN"))) {
            //작업보고 삭제여부 Y 변경
            paramMap.put("MFC_WK_STS_C", "99");
            egovLogger.debug("===========삭제시가공작업상태"+paramMap.get("MFC_WK_STS_C"));
            sqlSession.update("sfmes.sqlmap.pd.update_delyn_TB_PD_M_WK_RPT",paramMap);
            
            for(Map<String, Object> map : paramList01){
                //투입자재상세저장
                map.put("CORP_C", paramMap.get("CORP_C"));
                map.put("BZPL_C", paramMap.get("BZPL_C"));
                map.put("RPT_DT", paramMap.get("RPT_DT"));
                map.put("RPT_SQNO", paramMap.get("RPT_SQNO"));
                egovLogger.debug("삭제==================================="+map);
                sqlSession.update("sfmes.sqlmap.pd.update_delyn_TB_PD_D_WK_RPT_MTRL",map);
            }
            
            for(Map<String, Object> map : paramList02){
                //투입자재상세저장
                map.put("CORP_C", paramMap.get("CORP_C"));
                map.put("BZPL_C", paramMap.get("BZPL_C"));
                map.put("RPT_DT", paramMap.get("RPT_DT"));
                map.put("RPT_SQNO", paramMap.get("RPT_SQNO"));
                egovLogger.debug("삭제==================================="+map);
                sqlSession.update("sfmes.sqlmap.pd.update_delyn_TB_PD_D_WK_RPT_MFS",map);
            }
            //작업상태 '05생산완료' 변경 
            paramMap.put("MFC_WK_STS_C", "05");
            paramMap.put("TR_BSN_DSC", "PD10");
            egovLogger.debug("===========삭제시작업지시가공작업상태"+paramMap.get("MFC_WK_STS_C"));
            sqlSession.update("sfmes.sqlmap.pd.TB_PD_M_WK_DNTT_change",paramMap);
            
            //재고이동 호출
            this.callPd2530(paramMap, paramList01, paramList02);
            
        } else {
            //기본정보수정
            sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_M_WK_RPT",paramMap);
            
            for(Map<String, Object> map : paramList01){
                //투입자재상세저장
                map.put("CORP_C", paramMap.get("CORP_C"));
                map.put("BZPL_C", paramMap.get("BZPL_C"));
                map.put("RPT_DT", paramMap.get("RPT_DT"));
                map.put("RPT_SQNO", paramMap.get("RPT_SQNO"));
                
                if(map.get("_status_").equals("*")){
                    
                    sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_WK_RPT_MTRL",map);
                    
                }
            }
            
            for(Map<String, Object> map : paramList02){
                //제품상세수정
                map.put("CORP_C", paramMap.get("CORP_C"));
                map.put("BZPL_C", paramMap.get("BZPL_C"));
                map.put("RPT_DT", paramMap.get("RPT_DT"));
                map.put("RPT_SQNO", paramMap.get("RPT_SQNO"));
                
                if(map.get("_status_").equals("*")){
                    
                    sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_WK_RPT_MFS",map);
                    
                }
            }
            
        }
        
    }
    
    @Override
    public List<?> selectPd2530List_01(LinkedHashMap paramMap) throws Exception {
        // 공정기록서작업보고 기본조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2530List01",paramMap);
    }

    @Override
    public List<?> selectPd2530List_02(LinkedHashMap paramMap) throws Exception {
        //공정기록서 기반 작업보고 투입물품조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2530List02",paramMap);
    }

    @Override
    public List<?> selectPd2530List_03(LinkedHashMap paramMap) throws Exception {
        //공정기록서 기반 작업보고 생산물품조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2530List03",paramMap);
    }

    @Override
    public List<?> selectPd2530List_04(LinkedHashMap paramMap) throws Exception {
        //공정기록서 기반 작업지시 기본정보조회(저장전)
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2530List04",paramMap);
    }

    @Override
    public List<?> selectPd2530List_05(LinkedHashMap paramMap) throws Exception {
        //공정기록서 기반 작업지시 투입물품조회(저장전)
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2530List05",paramMap);
    }

    @Override
    public List<?> selectPd2530List_06(LinkedHashMap paramMap) throws Exception {
        //공정기록서 기반 작업지시 생산물품조회(저장전)
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2530List06",paramMap);
    }

    @Override
    public void callPd2530(LinkedHashMap paramMap, List<Map<String, Object>> paramList01,
            List<Map<String, Object>> paramList02) throws Exception {
        //작업보고재고이동impl
        /**
         * 물품 재고 입출고 등록을 한다.
         * @param paramMap1, paramMap2(물품입출고 등록 기본항목)
         *         CORP_C : 회사코드 
         *         BZPL_C : 사업장코드
         *         STDV_DSC : 입출고구분코드(I.입고,O.출고)
         *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
         *         RLTR_DT : 입출고일자(검수일자)
         *         TRPL_C : 거래처코드
         *         STDV_STS_DSC : 입출고상태구분코드(1:검수입고/출고완료)
         *         STDV_REF_DSC : 입출고참조구분코드(1:무참조)
         *         RMK_CNTN : 비고내용
         *         TR_BSN_DSC : 거래업무구분코드
         *         TR_SQNO : 거래일련번호
         *         GUSRID : 사용자ID
         *         
         *        paramList1, paramList2(물품입출고 상세 기본항목)
         *         GDS_C : 물품코드 
         *         STDV_QT : 입출고수량
         *         STDV_BOX_QT : 입출고박스수량
         *         TR_UNT_C : 거래단위코드
         *         STDV_UPR : 입출고단가
         *         SPY_AM : 공급금액
         *         VAT : 부가세
         *         STDV_AM : 입출고금액
         *         WHSE_C : 창고코드
         *         DSTR_TERDT : 유통기한일자
         *         HST_AMN_DSC : 이력관리구분코드
         *         GDS_HST_NO : 물품이력번호
         *         BUDL_NO : 묶음번호
         *         QT_WT_DSC : 수(중)량형구분코드
         *         WHT_QT : 단량
         *         WHT_UNT_C : 단량단위코드
         *         STDV_WT : 입출고중량
         *         TXT_DSC : 과세구분코드
         *         RMK_CNTN : 비고내용
         *         DEL_YN : 삭제여부
         *         FLAG_STDV_DSC_IO_YN : 출고,입고 값이 둘다 있는경우 
         * @return void형
         * @exception Exception
         */
        egovLogger.debug("=====param" +paramMap);
        egovLogger.debug("=====paramList01" +paramList01);
        egovLogger.debug("=====paramList02" +paramList02);
        
        //제품 전달 매체변수 (Map, List) 생성
        List<Map<String, Object>> if_param_List_01 = new ArrayList<Map<String, Object>>();
        
        LinkedHashMap if_param_map_01 = new LinkedHashMap();
        Map<String, Object> if_list_map_01 = new HashMap<>();  
        
        // 생산물품 Map 값을 Mapping 
        if_param_map_01.put("CORP_C"      , paramMap.get("CORP_C"));
        if_param_map_01.put("BZPL_C"      , paramMap.get("BZPL_C"));
        if_param_map_01.put("STDV_DSC"    , "I");
        if("N".equals(paramMap.get("DEL_YN"))) {
            if_param_map_01.put("SLP_NML_YN"  , "Y");
        } else {
            if_param_map_01.put("SLP_NML_YN"  , "N");
        }
        if_param_map_01.put("RLTR_DT"     , paramMap.get("PD_DT"));
        if_param_map_01.put("ACG_DT"      , paramMap.get("ACG_DT"));
        if_param_map_01.put("TRPL_C"      , "");
        if_param_map_01.put("STDV_STS_DSC", "1");
        if_param_map_01.put("STDV_REF_DSC", "1");
        if_param_map_01.put("RMK_CNTN"    , paramMap.get("RMK_CNTN"));
        if_param_map_01.put("TR_BSN_DSC"  , paramMap.get("TR_BSN_DSC"));
        if_param_map_01.put("TR_SQNO"     , paramMap.get("TR_SQNO"));
        if_param_map_01.put("GUSRID"      , paramMap.get("GUSRID"));
        
        egovLogger.debug("=======생산물품" +if_param_map_01);
        
        for(Map<String, Object> map : paramList02){
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("RPT_DT", paramMap.get("RPT_DT"));
            map.put("RPT_SQNO", paramMap.get("RPT_SQNO"));
            
            //address참조
            LinkedHashMap if_list_map = new LinkedHashMap();
            if_list_map.putAll(map);
            if_list_map.put("STDV_QT"            , map.get("PD_QT"));
            if_list_map.put("STDV_BOX_QT"        , "1");
            if_list_map.put("TR_UNT_C"           , map.get("UNT_C"));
            if_list_map.put("STDV_UPR"           , map.get("PD_UPR"));
            if_list_map.put("SPY_AM"             , map.get("PD_AM"));
            if_list_map.put("VAT"                , "0");
            if_list_map.put("DSTR_TERDT"         , map.get("DSTR_TER_DT"));
            if_list_map.put("STDV_AM"            , map.get("PD_AM"));
            if_list_map.put("STDV_WT"            , "0");
            if_list_map.put("HST_AMN_DSC"        , "0"); 
            if_list_map.put("GDS_HST_NO"         , "");
            if_list_map.put("BUDL_NO"            , "");
            if_list_map.put("DEL_YN"             , "N");
            if_list_map.put("FLAG_STDV_DSC_IO_YN", "N");
            
            egovLogger.debug("=======생산물품" +if_list_map);

            //Map을 List에 Add 
            if_param_List_01.add(if_list_map);
        
        }
        egovLogger.debug("=======생산물품" +if_param_map_01);
        egovLogger.debug("=======생산물품list" +if_param_List_01);
        
        //투입품 전달 매체변수 (Map, List) 생성
        List<Map<String, Object>> if_param_List_02 = new ArrayList<Map<String, Object>>();
              
        LinkedHashMap if_param_map_02 = new LinkedHashMap();
        Map<String, Object> if_list_map_02 = new HashMap<>();  
        
        if_param_map_02.put("CORP_C"      , paramMap.get("CORP_C"));
        if_param_map_02.put("BZPL_C"      , paramMap.get("BZPL_C"));
        if_param_map_02.put("STDV_DSC"    , "O");
        if("N".equals(paramMap.get("DEL_YN"))) {
            if_param_map_02.put("SLP_NML_YN"  , "Y");
        } else {
            if_param_map_02.put("SLP_NML_YN"  , "N");
        }
        if_param_map_02.put("RLTR_DT"     , paramMap.get("PD_DT"));
        if_param_map_02.put("ACG_DT"      , paramMap.get("ACG_DT"));
        if_param_map_02.put("TRPL_C"      , "");
        if_param_map_02.put("STDV_STS_DSC", "1");
        if_param_map_02.put("STDV_REF_DSC", "1");
        if_param_map_02.put("RMK_CNTN"    , paramMap.get("RMK_CNTN"));
        if_param_map_02.put("TR_BSN_DSC"  , paramMap.get("TR_BSN_DSC"));
        if_param_map_02.put("TR_SQNO"     , paramMap.get("TR_SQNO"));
        if_param_map_02.put("GUSRID"      , paramMap.get("GUSRID"));
        
        egovLogger.debug("=======투입품" +if_param_map_02);
        
        for(Map<String, Object> map : paramList01){
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("RPT_DT", paramMap.get("RPT_DT"));
            map.put("RPT_SQNO", paramMap.get("RPT_SQNO"));
            
            //조건에 맞는지 처리(재고연동여부 체크되있을경우)
            if ("1".equals(paramMap.get("SM_IF_YN"))) {
                //address참
                LinkedHashMap if_list_map = new LinkedHashMap();
                
                if_list_map.putAll(map);
                
                if_list_map.put("STDV_QT"            , map.get("MTRL_PTIN_QT"));
                if_list_map.put("STDV_BOX_QT"        , "1");
                if_list_map.put("TR_UNT_C"           , map.get("UNT_C"));
                if_list_map.put("STDV_UPR"           , map.get("PTIN_UPR"));
                if_list_map.put("SPY_AM"             , map.get("PTIN_AM"));
                if_list_map.put("VAT"                , "0");
                if_list_map.put("DSTR_TERDT"         , "");
                if_list_map.put("STDV_AM"            , map.get("PTIN_AM"));
                if_list_map.put("STDV_WT"            , "0");
                if_list_map.put("GDS_HST_NO"         , "");
                if_list_map.put("BUDL_NO"            , map.get("GDS_HST_NO"));
                if_list_map.put("DEL_YN"             , "N");
                if_list_map.put("FLAG_STDV_DSC_IO_YN", "Y");
                if_list_map.put("WHSE_C"             , "ZZZ");

                
                egovLogger.debug("=======투입품" +if_list_map);

                //Map을 List에 Add 
                if_param_List_02.add(if_list_map);
            }
            
        }
        egovLogger.debug("=======투입물품" +if_param_map_02);
        egovLogger.debug("=======투입물품list" +if_param_List_02);
        
        if ("1".equals(paramMap.get("SM_IF_YN"))) {
            // 재고연동 체크일때만 처리
            egovLogger.debug("===============재고연동여부체크");

            sm1000Service.Call_saveSm1000(if_param_map_01, if_param_List_01, if_param_map_02, if_param_List_02);
        } else {
            egovLogger.debug("===============재고연동여부미체크");
            
            sm1000Service.Call_saveSm1000(if_param_map_01, if_param_List_01, null, null);
        }
        
    }

}
