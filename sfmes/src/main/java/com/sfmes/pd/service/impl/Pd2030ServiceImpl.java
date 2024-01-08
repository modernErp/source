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
import com.sfmes.pd.service.Pd2030Service;
import com.sfmes.sm.service.Sm1000Service;

/**
* @Class Name : Pd2030ServiceImpl.java
* @Description : Pd2030Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.08.31   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.08.31
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2030Service")
public class Pd2030ServiceImpl extends CmnAbstractServiceImpl implements Pd2030Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    //재고 입출고 등록 서비스 선언
    @Autowired
    private Sm1000Service sm1000Service;     

    @Override
    public void insertPd2030(LinkedHashMap paramMap, List<Map<String, Object>> paramList, List<Map<String, Object>> paramList01) throws Exception {
        //작업보고등록        
        egovLogger.debug("=====param" +paramMap);
        
        // 마감여부 체크 추가 (2022.02.08) 구민희
        LinkedHashMap<String, Object> chkDdlYnParam = new LinkedHashMap<String, Object>();
        chkDdlYnParam.put("CORP_C" , paramMap.get("CORP_C")); //회사코드
        chkDdlYnParam.put("BZPL_C" , paramMap.get("BZPL_C")); //사업장코드
        chkDdlYnParam.put("BSN_DSC", "PD");                   //업무구분 PD:생산
        chkDdlYnParam.put("BAS_DT" , paramMap.get("ACG_DT"));  //기준일자 BAS_DT:작업보고(회계)일자
        
        String ddlYn = commonService.checkDdlYn(chkDdlYnParam);
        if("Y".equals(ddlYn)) {
            throw infoException("해당 작업보고일자로 업무가 마감되었습니다. 날짜를 확인해주세요.");
        }
        
        //보고일련번호 채번
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_RPT_DT = paramMap.get("RPT_DT").toString();
        String seqNo = commonService.getGvno(s_CORP_C,"TB_PD_M_WK_RPT",s_BZPL_C, s_RPT_DT, 1);
        egovLogger.debug("=====작업보고일련번호 채번" +seqNo);
        paramMap.put("RPT_SQNO", seqNo);
        
        //거래업무구분코드(PD20작업보고)
        paramMap.put("TR_BSN_DSC", "PD20");
        paramMap.put("MFC_WK_STS_C", "06");
        
        //채번 서비스 호출(거래일련번호)
        String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
        egovLogger.debug("생성된 거래일련번호 채번: " + tr_seqNo);
        paramMap.put("TR_SQNO", tr_seqNo);            

        egovLogger.debug("=====거래업무구분코드" +paramMap.get("TR_BSN_DSC"));
        egovLogger.debug("=====거래일련번호" +paramMap.get("TR_SQNO"));
        egovLogger.debug("=====가공작업상태" +paramMap.get("MFC_WK_STS_C"));
        
        //기본정보저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_M_WK_RPT",paramMap);
        //제품상세저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_RPT_MFS",paramMap);
        //작업지시 상태값 변경
        // sqlSession.insert("sfmes.sqlmap.pd.TB_PD_D_WK_DNTT_MFS_change",paramMap);    20220103  rchkorea  콜하는 서비스 없음 
        sqlSession.insert("sfmes.sqlmap.pd.TB_PD_D_WK_DNTT_MFS_change_2021",paramMap);

        for(Map<String, Object> map : paramList){
            //투입자재상세저장
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("RPT_DT", paramMap.get("RPT_DT"));
            map.put("RPT_SQNO", paramMap.get("RPT_SQNO"));
            
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_RPT_MTRL",map);
            
        }
        
        //재고이동 호출
        this.callPd2030(paramMap, paramList);
        
        for(Map<String, Object> map : paramList01){
            //투입자재상세저장
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("RPT_DT", paramMap.get("RPT_DT"));
            map.put("RPT_SQNO", paramMap.get("RPT_SQNO"));
            
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_RPT_MAP",map);
            
        }

    }

    @Override
    public void updatePd2030(LinkedHashMap paramMap, List<Map<String, Object>> paramList, List<Map<String, Object>> paramList01) throws Exception {
        // 작업보고 수정
        
        /*
        //마감여부 체크
        commonService.checkDdl(paramMap);
        */
        
        // 마감여부 체크 추가 (2022.02.08) 구민희
        LinkedHashMap<String, Object> chkDdlYnParam = new LinkedHashMap<String, Object>();
        chkDdlYnParam.put("CORP_C" , paramMap.get("CORP_C")); //회사코드
        chkDdlYnParam.put("BZPL_C" , paramMap.get("BZPL_C")); //사업장코드
        chkDdlYnParam.put("BSN_DSC", "PD");                   //업무구분 PD:생산
        chkDdlYnParam.put("BAS_DT" , paramMap.get("ACG_DT"));  //기준일자 BAS_DT:작업보고(회계)일자
        
        String ddlYn = commonService.checkDdlYn(chkDdlYnParam);
        if("Y".equals(ddlYn)) {
            throw infoException("해당 작업보고일자로 업무가 마감되었습니다. 날짜를 확인해주세요.");
        }
        
        if("Y".equals(paramMap.get("DEL_YN"))) {
            //작업보고 삭제여부 Y 변경
            paramMap.put("MFC_WK_STS_C", "99");
            egovLogger.debug("===========삭제시가공작업상태"+paramMap.get("MFC_WK_STS_C"));
            sqlSession.update("sfmes.sqlmap.pd.update_delyn_TB_PD_M_WK_RPT",paramMap);
            sqlSession.update("sfmes.sqlmap.pd.update_delyn_TB_PD_D_WK_RPT_MFS",paramMap);
            for(Map<String, Object> map : paramList){
                //투입자재상세저장
                map.put("CORP_C", paramMap.get("CORP_C"));
                map.put("BZPL_C", paramMap.get("BZPL_C"));
                map.put("RPT_DT", paramMap.get("RPT_DT"));
                map.put("RPT_SQNO", paramMap.get("RPT_SQNO"));
                egovLogger.debug("삭제==================================="+map);
                sqlSession.update("sfmes.sqlmap.pd.update_delyn_TB_PD_D_WK_RPT_MTRL",map);
            }
            //작업상태 '05생산완료' 변경 
            paramMap.put("MFC_WK_STS_C", "05");
            paramMap.put("TR_BSN_DSC", "PD10");
            egovLogger.debug("===========삭제시작업지시가공작업상태"+paramMap.get("MFC_WK_STS_C"));
            sqlSession.update("sfmes.sqlmap.pd.TB_PD_D_WK_DNTT_MFS_change", paramMap);
            
            //재고이동 호출
            this.callPd2030(paramMap, paramList);
            
        } else {
            //기본정보수정
            sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_M_WK_RPT",paramMap);
            //제품상세수정
            sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_WK_RPT_MFS",paramMap);
            
            for(Map<String, Object> map : paramList){
                //투입자재상세저장
                map.put("CORP_C", paramMap.get("CORP_C"));
                map.put("BZPL_C", paramMap.get("BZPL_C"));
                map.put("RPT_DT", paramMap.get("RPT_DT"));
                map.put("RPT_SQNO", paramMap.get("RPT_SQNO"));
                
                if(map.get("_status_").equals("*")){
                    
                    sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_WK_RPT_MTRL",map);
                    
                }
            }
            
            for(Map<String, Object> map : paramList01){
                //투입자재상세저장
                map.put("CORP_C", paramMap.get("CORP_C"));
                map.put("BZPL_C", paramMap.get("BZPL_C"));
                map.put("RPT_DT", paramMap.get("RPT_DT"));
                map.put("RPT_SQNO", paramMap.get("RPT_SQNO"));
                
                if(map.get("_status_").equals("*")){
                    
                    sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_WK_RPT_MAP",map);
                    
                }else if(map.get("_status_").equals("+")){
                    
                    sqlSession.update("sfmes.sqlmap.tb.insert_TB_PD_D_WK_RPT_MAP",map);
                    
                }else if(map.get("_status_").equals("-")){
                    
                    sqlSession.update("sfmes.sqlmap.pd.update_delyn_TB_PD_D_WK_RPT_MAP",map);
                    
                }
            }
            
        }
        
    }

    @Override
    public List<?> selectPd2030List_01(LinkedHashMap paramMap) throws Exception {
        //작업보고기본 조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2030List01",paramMap);
    }

    @Override
    public List<?> selectPd2030List_02(LinkedHashMap paramMap) throws Exception {
        // 작업보고투입상세 조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2030List02",paramMap);
    }
    
    @Override
    public List<?> selectPd2030List_03(LinkedHashMap paramMap) throws Exception {
        //작업지시기본 조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2030List03",paramMap);
    }
    
    @Override
    public List<?> selectPd2030List_04(LinkedHashMap paramMap) throws Exception {
        //작업지시상세내역 조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2030List04",paramMap);
    }

    @Override
    public List<?> selectPd2030List_05(LinkedHashMap paramMap) throws Exception {
        //투입인력내역 조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2030List05",paramMap);
    }
    
/**
 *  작업보고재고이동impl
 */  
    @Override
    public void callPd2030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        //작업보고재고이동impl
        egovLogger.debug("=====param" +paramMap);
        
        //재고I/F호출, 20201006 김수민
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
        
        //제품 전달 매체변수 (Map, List) 생성
        List<Map<String, Object>> if_param_List_01 = new ArrayList<Map<String, Object>>();
              
        LinkedHashMap if_param_map_01 = new LinkedHashMap();
        Map<String, Object> if_list_map_01 = new HashMap<>();  

        // 제품 Map 값을 Mapping 
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

        if_param_map_01.put("GDS_C"              , paramMap.get("GDS_C"));
        if_param_map_01.put("STDV_QT"            , paramMap.get("PD_QT")); 
        if_param_map_01.put("STDV_BOX_QT"        , "1");
        if_param_map_01.put("TR_UNT_C"           , paramMap.get("UNT_C"));
        if_param_map_01.put("STDV_UPR"           , paramMap.get("PD_UPR"));
        if_param_map_01.put("SPY_AM"             , paramMap.get("PD_AM"));
        if_param_map_01.put("VAT"                , "0");
        if_param_map_01.put("STDV_AM"            , paramMap.get("PD_AM"));
        if_param_map_01.put("WHSE_C"             , paramMap.get("WHSE_C"));
        if_param_map_01.put("DSTR_TERDT"         , paramMap.get("DSTR_TERDT"));
        if_param_map_01.put("HST_AMN_DSC"        , paramMap.get("HST_AMN_DSC")); 
        if_param_map_01.put("GDS_HST_NO"         , "");
        if_param_map_01.put("BUDL_NO"            , paramMap.get("GDS_HST_NO"));
        if_param_map_01.put("QT_WT_DSC"          , paramMap.get("QT_WT_DSC"));
        if_param_map_01.put("WHT_QT"             , paramMap.get("WHT_QT"));
        if_param_map_01.put("WHT_UNT_C"          , paramMap.get("WHT_UNT_C"));
        if_param_map_01.put("TXT_DSC"            , paramMap.get("TXT_DSC"));
        if_param_map_01.put("STDV_WT"            , "0"); 
        if_param_map_01.put("RMK_CNTN"           , paramMap.get("RMK_CNTN"));
        if_param_map_01.put("DEL_YN"             , "N");
        if_param_map_01.put("FLAG_STDV_DSC_IO_YN", "N");
        
        //Map을 List에 Add
        if_param_List_01.add(if_param_map_01);

        egovLogger.debug("=======제품" +if_param_map_01);
        egovLogger.debug("=======제픔list" +if_param_List_01);
        
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

        for(Map<String, Object> map : paramList){
            //투입자재상세저장
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
                if_list_map.put("DSTR_TERDT"         , paramMap.get("DSTR_TER_DT"));
                if_list_map.put("STDV_AM"            , map.get("PTIN_AM"));
                if_list_map.put("STDV_WT"            , "0");
                if_list_map.put("HST_AMN_DSC"        , "0"); 
                if_list_map.put("GDS_HST_NO"         , "");
                if_list_map.put("BUDL_NO"            , "");
                if_list_map.put("DEL_YN"             , "N");
                if_list_map.put("FLAG_STDV_DSC_IO_YN", "Y");
                if_list_map.put("WHSE_C"             , "ZZZ");
//                if_list_map.put("GDS_C"              , map.get("GDS_C"));                
//                if_list_map.put("WHSE_C"             , map.get("WHSE_C"));
//                if_list_map.put("QT_WT_DSC"          , map.get("QT_WT_DSC"));
//                if_list_map.put("WHT_QT"             , map.get("WHT_QT"));
//                if_list_map.put("WHT_UNT_C"          , map.get("WHT_UNT_C"));
//                if_list_map.put("TXT_DSC"            , map.get("TXT_DSC"));
//                if_list_map.put("RMK_CNTN"           , map.get("RMK_CNTN"));

                
                egovLogger.debug("=======투입품" +if_list_map);

                //Map을 List에 Add 
                if_param_List_02.add(if_list_map);
            }
        }

        egovLogger.debug("=======투입품list" +if_param_List_02);
        
        if ("1".equals(paramMap.get("SM_IF_YN"))) {
            // 재고연동 체크일때만 처리
            egovLogger.debug("===============재고연동여부체크");

            sm1000Service.Call_saveSm1000(if_param_map_01, if_param_List_01, if_param_map_02, if_param_List_02);
            
            egovLogger.debug("===============재고연동후 Map (if_param_map_01)확인 ::: "+ if_param_map_01);
            egovLogger.debug("===============재고연동후 Map (if_param_map_02)확인 ::: "+ if_param_map_02);
        } else {
            egovLogger.debug("===============재고연동여부미체크");
            
            sm1000Service.Call_saveSm1000(if_param_map_01, if_param_List_01, null, null);
        }
        
    }

}
