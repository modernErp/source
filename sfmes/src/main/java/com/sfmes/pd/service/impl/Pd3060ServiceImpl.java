package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd3060Service;
import com.sfmes.sm.service.Sm1000Service;

/**
 * @Class Name  : Pd3060ServiceImpl.java
 * @Description : Pd3060Service Class
 * @Modification Information
 * @
 * @  수정일           수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2022.02.15  유춘호            배부차액정리
 *
 * @author (주)모든솔루션
 * @since 2022.02.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Pd3060Service")
public class Pd3060ServiceImpl extends CmnAbstractServiceImpl implements Pd3060Service{

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Resource(name = "Sm1000Service")
    private Sm1000Service sm1000Service;

    
    
    @Override
    public void insertPd3060_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 배부차액계산 시작 *****************");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        
        egovLogger.debug("************ 배부차액 계산 사전 정합성 체크  *****************");
        String resultMsg = null;  //결과메세지
        resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.pd.pd3060_ValidChk",paramMap);
        
        if(! resultMsg.equals("OK")) 
        {
            throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
        }
        
        egovLogger.debug("************ 배부차액 대상 매출 보정 내역 생성   *****************");
        sqlSession.insert("sfmes.sqlmap.pd.insert_Pd3060_01",paramMap);

        egovLogger.debug("************ 원가상세 매출 보정 내역 품목합계 수정   *****************");
        sqlSession.update("sfmes.sqlmap.pd.update_pd3060_02",paramMap);
        
        egovLogger.debug("************ 원가상세 재고보정내역 수정   *****************");
        sqlSession.update("sfmes.sqlmap.pd.update_pd3060_03",paramMap);

        egovLogger.debug("************ 배부차액 대상 매출 보정 정리비율, 정리금액 수정   *****************");
        sqlSession.update("sfmes.sqlmap.pd.update_pd3060_05",paramMap);

        egovLogger.debug("************ 원가기본 원가계산상태 3.배부차액계산으로 수정   *****************");
        paramMap.put("NEW_PCS_CLC_STSC", "3");    // 원가계산상태 1.원가계산, 2.원가계산확정, 3.배부차액계산, 4.배부차액정리           
        sqlSession.update("sfmes.sqlmap.pd.update_pd3060_01",paramMap);

    }

    @Override
    public void insertPd3060_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 배부차액  정리 시작 *****************");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        
        egovLogger.debug("************ 매출보정 등록 시작 *****************");

        egovLogger.debug("매출보정 등록할 내역 조회");
        List<Map<String, Object>> paramList1 = new ArrayList<Map<String, Object>>();
        paramList1 = sqlSession.selectList("sfmes.sqlmap.pd.select_Pd3060_01",paramMap);

        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();

        //오늘날짜구하기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        
        
        egovLogger.debug("매출보정 내역 매출기본, 매출상세내역 등록");
        for(Map<String, Object> map : paramList1) {
            //채번 서비스 호출(매입일련번호)
            String s_SL_DT  = map.get("PCS_CLC_ED_DT").toString();
            String seqNo = commonService.getGvno(s_CORP_C,"TB_SE_M_SL",s_BZPL_C, s_SL_DT, 1);
            egovLogger.debug("생성된 매출일련번호 채번: " + seqNo);
            map.put("SL_SQNO", seqNo  );    // 매출일련번호
            map.put("SL_DT"  , s_SL_DT);    // 매출일자 = 원가계산기간종료일자 
            map.put("ACG_DT" , s_SL_DT);    // 회계일자 = 원가계산기간종료일자 
            map.put("GUSRID", paramMap.get("GUSRID"));
            
            //채번 서비스 호출(거래일련번호)
            String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
            egovLogger.debug("생성된 거래일련번호 채번: " + tr_seqNo);
            map.put("TR_SQNO", tr_seqNo);

            egovLogger.debug("매출보정 등록할 map: " + map.toString());
            egovLogger.debug("매출기본내역등록 TB_SE_M_SL");   
            map.put("TR_BSN_DSC"  ,"PD30");   // 거래업무구분코드 PD30 배부차액정리등록
            
            sqlSession.insert("sfmes.sqlmap.pd.insert_Pd3060_02", map);         
            
            map.put("SL_PCS_AM"  , map.get("ARG_AM"));   // 배부차액 매출 보정금액 

            egovLogger.debug("매출상세내역등록 TB_SE_D_SL"); 
            sqlSession.insert("sfmes.sqlmap.pd.insert_Pd3060_03", map);            
        }
        
        egovLogger.debug("************ 재고보정 등록 시작 *****************");
        
        egovLogger.debug("재고보정 등록할 내역 조회");
        List<Map<String, Object>> paramList2 = new ArrayList<Map<String, Object>>();
        paramList2 = sqlSession.selectList("sfmes.sqlmap.pd.select_Pd3060_02",paramMap);

        egovLogger.debug("재고_물품실재고입출고기본, 재고_물품실재고입출고상세 등록");
        for(Map<String, Object> map :paramList2) {

        	//채번 서비스 호출
	        String seqNo = commonService.getGvno(s_CORP_C,"TB_SM_M_GDS_RL_STDV",s_BZPL_C, strToday, 1);
	        egovLogger.debug("생성된 입고일련번호 채번: " + seqNo);
	        
	        map.put("STDV_DT"       , strToday);
	        map.put("STDV_SQNO"     , seqNo);
            map.put("STDV_DSC"      , "I");         // I.입고, O.출고 
            map.put("GUSRID"        , paramMap.get("GUSRID"));
            map.put("RMK_CNTN"      , "배부차액 정리");          

            egovLogger.debug("입출고기본내역저장");
            map.put("SLP_NML_YN"    , "Y");         // 거래정상여부
            map.put("STDV_REF_DT"   , map.get("PCS_CLC_DT"  ));    // 입출고참조일자      = 원가계산일자  
            map.put("STDV_REF_SQNO" , map.get("PCS_CLC_SQNO"));    // 입출고참조일련번호 = 원가계산일련번호 
            map.put("TR_BSN_DSC","PD30");       // 거래업무구분코드 PD30 배부차액정리등록
            map.put("MON_TOT_YN","Y");          

            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_M_GDS_RL_STDV", map);
	        
            egovLogger.debug("입출고상세내역저장");
            map.put("STDV_DSQNO"       ,1);   
            map.put("STDV_QT"          ,0);
            map.put("STDV_BOX_QT"      ,0);
            map.put("STDV_UPR"         ,0);
            map.put("SPY_AM"           ,0); 
            map.put("VAT"              ,0);
            map.put("WHT_QT"           ,0);
            map.put("STDV_AM"          ,map.get("DSBN_ARG_SM_AM"));  // 공급금액,입출고금액   = 재고보정금액 
            map.put("STDV_WT"          ,0);
            map.put("DEL_YN"           ,"N");
            map.put("TR_STDV_QT"       ,0);

            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_D_GDS_RL_STDV", map);   

            egovLogger.debug("************ 재고월집계   *****************");
        	paramMap.put("ACG_MN"  , map.get("ACG_MN"));   // 회계년월 = 원가계산기간종료일자 6자리  
        	paramMap.put("GDS_C"   , map.get("GDS_C"));    // 물품코드   
            sm1000Service.Call_Sm1000MonthTot_LE(paramMap);
        }


        egovLogger.debug("************ 원가기본 원가계산상태 4.배부차액정리로 수정   *****************");
        paramMap.put("NEW_PCS_CLC_STSC", "4");    // 원가계산상태 1.원가계산, 2.원가계산확정, 3.배부차액계산, 4.배부차액정리           
        sqlSession.update("sfmes.sqlmap.pd.update_pd3060_01",paramMap);
        
    }

    @Override
    public void updatePd3060_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 배부차액  취소 시작 *****************");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        
        if(paramMap.get("PCS_CLC_STSC").equals("3")) { 
            
        	egovLogger.debug("************ 원가계산상세 배부계산내역 초기화 *****************");
            sqlSession.update("sfmes.sqlmap.pd.update_pd3060_04",paramMap);
            
        	egovLogger.debug("************ 배부차액 대상 매출 보정 내역 삭제 *****************");
            sqlSession.update("sfmes.sqlmap.pd.delete_pd3060_01",paramMap);

        	egovLogger.debug("************ 원가계산상태 변경 2.원가계상확정으로 변경 *****************");
        	paramMap.put("NEW_PCS_CLC_STSC", "2");    // 원가계산상태 1.원가계산, 2.원가계산확정, 3.배부차액계산, 4.배부차액정리
            sqlSession.update("sfmes.sqlmap.pd.update_pd3060_01",paramMap);
        } else if(paramMap.get("PCS_CLC_STSC").equals("4")) {
        	
        	paramMap.put("ACG_DT"  , paramMap.get("PCS_CLC_ED_DT").toString());   // 회계일자 = 원가계산기간종료일자 
        	paramMap.put("TR_BSN_DSC", "PD30");    // 거래업무구분코드 PD30 배부차액정리등록

        	egovLogger.debug("************ 매출보정 삭제 시작  *****************");
            sqlSession.insert("sfmes.sqlmap.pd.delete_pd3060_03", paramMap);    // 매출상세내역 삭제 
            sqlSession.insert("sfmes.sqlmap.pd.delete_pd3060_02", paramMap);    // 매출기본내역 삭제 
            
        	egovLogger.debug("************ 재고보정 삭제 시작  *****************");
            sqlSession.insert("sfmes.sqlmap.pd.delete_pd3060_05", paramMap);    // 재고실입출고상세내역 삭제 
            sqlSession.insert("sfmes.sqlmap.pd.delete_pd3060_04", paramMap);    // 재고실입출고기본내역 삭제 

            egovLogger.debug("재고보정 삭제대상 월집계 내역 조회");
            List<Map<String, Object>> paramList2 = new ArrayList<Map<String, Object>>();
            paramList2 = sqlSession.selectList("sfmes.sqlmap.pd.select_Pd3060_02",paramMap);
            for(Map<String, Object> map :paramList2) {

	            egovLogger.debug("************ 재고월 재집계   *****************");
	        	paramMap.put("ACG_MN"  , map.get("ACG_MN"));   // 회계년월 = 원가계산기간종료일자 6자리  
	        	paramMap.put("GDS_C"   , map.get("GDS_C"));    // 물품코드   
	            sm1000Service.Call_Sm1000MonthTot_LE(paramMap);
            }

        	egovLogger.debug("************ 원가계산상태 변경 3.배부차액계산으로  변경 *****************");
        	paramMap.put("NEW_PCS_CLC_STSC", "3");    // 원가계산상태 1.원가계산, 2.원가계산확정, 3.배부차액계산, 4.배부차액정리
            sqlSession.update("sfmes.sqlmap.pd.update_pd3060_01",paramMap);
        }
    }
    
}
