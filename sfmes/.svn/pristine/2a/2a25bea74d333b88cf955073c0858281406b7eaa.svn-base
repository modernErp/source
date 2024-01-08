package com.sfmes.se.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.ca.service.Ca0100Service;
import com.sfmes.ca.service.Ca0200Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se1030Service;
import com.sfmes.se.service.Se3010Service;
import com.sfmes.se.service.Se6020Service;

/**
 * @Class Name : Se3010ServiceImpl.java
 * @Description : 매출등록/수정 및 조회
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.06  곽환용   최초생성
 * @ 2022.04.21  나명우   수정
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) Se 모든솔루션 All right reserved.
 */

@Service("Se3010Service")
public class Se3010ServiceImpl extends CmnAbstractServiceImpl implements Se3010Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    //가격군별거래처 서비스 선언
    @Resource(name = "Se1030Service")
    private Se1030Service se1030service;         
    
    //제품출고 서비스 선언
    @Autowired
    private Se6020Service se6020Service;   
    
    //정산 서비스 선언
    @Autowired
    private Ca0100Service ca0100Service;
    
    //정산 서비스 선언
    @Autowired
    private Ca0200Service ca0200Service;
    
    //매출기본내역 조회
    @Override
    public List<?> selectSe3010_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매출기본내역조회[SE3010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3010_01", paramMap);
    }

    //매출상세내역 조회
    @Override
    public List<?> selectSe3010_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매출상세내역조회[SE3010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3010_02", paramMap);
    }
    
    //매출내역찾기팝업 조회
    @Override
    public List<?> selectSe3010_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매출내역찾기팝업조회[SE3010P01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3010_03", paramMap);
    }
    
    //참조출고상세내역 조회, 2022.04.21 나명우 물품, 이력번호통합 추가 
    @Override
    public List<?> selectSe3010_04(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 참조출고상세내역조회[SE6020P01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());  
        
        
        String DLR_DSC_GUBUN = paramMap.get("DLR_DSC_GUBUN").toString();
        List<?> resultList = null;
        if(DLR_DSC_GUBUN.equals("0")) {
            resultList = sqlSession.selectList("sfmes.sqlmap.se.selectSe3010_04", paramMap);
        } else if(DLR_DSC_GUBUN.equals("1")) {
            resultList = sqlSession.selectList("sfmes.sqlmap.se.selectSe3010_06", paramMap);
        } else if(DLR_DSC_GUBUN.equals("2")) {
            resultList = sqlSession.selectList("sfmes.sqlmap.se.selectSe3010_07", paramMap);
        }
        
        return resultList;
    }    
    
    //매출정산내역 조회
    @Override
    public List<?> selectSe3010_05(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매출정산내역조회[SE3010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3010_05", paramMap);
    }    
    
    //매출단가부가세포함여부 조회
    @Override
    public List<?> selectSe3010_06(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 콜 매출단가부가세포함여부조회[SE3010] *********");
        egovLogger.debug("paramMap: "+paramMap);
        return se1030service.Call_selecteSe1030_sl_upr_vat(paramMap);
    }    
    
    //매출기본,상세내역 등록
    @Override
    public String insertSe3010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception { 
        egovLogger.debug("************ 매출등록[SE3010] *********");
        
        /*
        paramMap.put("SL_DT", paramMap.get("writeDate"));
        
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        //마감여부 체크
        commonService.checkDdl(paramMap);
        */
        
        /*--------------------------------------------------------------------------------------------------
         마감여부 체크 추가_20220203 여다혜
         마감여부 체크 추가 수정_20220223 구민희
         --------------------------------------------------------------------------------------------------*/
        LinkedHashMap<String, Object> chkDdlYnParam = new LinkedHashMap<String, Object>();
        chkDdlYnParam.put("CORP_C" , paramMap.get("CORP_C")); //회사코드
        chkDdlYnParam.put("BZPL_C" , paramMap.get("BZPL_C")); //사업장코드
        chkDdlYnParam.put("BSN_DSC", "SE");                   //업무구분 SE:매출
        chkDdlYnParam.put("BAS_DT" , paramMap.get("ACG_DT"));  //기준일자 BAS_DT:매출(회계)일자
        
        
        String ddlYn = commonService.checkDdlYn(chkDdlYnParam);
        
        
        
        if("Y".equals(ddlYn)) {
            throw infoException("해당 매출일자로 업무가 마감되었습니다. 날짜를 확인해주세요.");
        }
        
        //공휴일체크(예시) - 적용대상 아님
        /*
        chkDdlYnParam.put("BAS_DT" , paramMap.get("FILP_DT"));  //기준일자 FILP_DT:기표일자(회계일자)
        chkDdlYnParam.put("BSN_DSC", "HLDY");                   //업무구분 HLDY:공휴일
        
        ddlYn = commonService.checkDdlYn(chkDdlYnParam);
        if("Y".equals(ddlYn)) {
            throw infoException("해당 매출일자는 공휴일입니다. 날짜를 확인해주세요.");
        }
        */
        
        
        
        
        String result = "";
        String resultMsg = null;  //결과메세지
 
        //동시 등록시 같은 출고내역을 참조하는지 validate
        if("2".contentEquals((String)paramMap.get("SL_REF_DSC"))) {
        	int dlrStsDscCheck = sqlSession.selectOne("sfmes.sqlmap.se.selectSe3010Valid_02", paramMap);
        	if(dlrStsDscCheck == 2) {
        	    throw infoException("매출 등록된 출고내역입니다\n전산담당자에게 문의하세요");
        	}
        }
        
        //매출기본내역에 대한 정합성 체크를 한다.
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.se.selectSe3010Valid", paramMap);
        if(! resultMsg.equals("OK")) 
        {
            throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
        }
        
        //채번을 위한 변수설정
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_SL_DT  = paramMap.get("SL_DT").toString();
        
        //채번 서비스 호출(매입일련번호)
        String seqNo = commonService.getGvno(s_CORP_C,"TB_SE_M_SL",s_BZPL_C, s_SL_DT, 1);
        egovLogger.debug("생성된 매출일련번호 채번: " + seqNo);
        paramMap.put("SL_SQNO", seqNo);
        paramMap.put("DLR_STS_DSC", "2");   //2:매출
        
        //무참조 매출등록 시 거래일련번호 채번함.
        if("1".equals(paramMap.get("SL_REF_DSC").toString())) {
        	egovLogger.debug("************ 제품출고내역등록 TB_SE_M_DLR *********");
        	egovLogger.debug("paramMap: "  + paramMap.toString());
        	egovLogger.debug("paramList: " + paramList.toString());
        	
            //채번 서비스 호출(거래일련번호)
            String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
            egovLogger.debug("생성된 거래일련번호 채번: " + tr_seqNo);
            paramMap.put("TR_SQNO", tr_seqNo);
            
            paramMap.put("RCPL_C"     , paramMap.get("TRPL_C"));  //무참조 매출등록 시 수령처는 거래처로 세팅
            paramMap.put("DLR_DT"     , paramMap.get("SL_DT"));   //제품출고전표일자는 매출전표일자로 세팅  
            paramMap.put("DVY_DT"     , paramMap.get("RLTR_DT")); //배송일자는 출고일자로 세팅
            paramMap.put("DLR_REF_DSC", "2");   //2:무참조
            
            // 제품출고 등록
            se6020Service.saveSe6020(paramMap, paramList);
            
        } else {         //출고참조매출등록 시에만 제품출고내역을 수정
        	//참조한출고내역 수정
            egovLogger.debug("************ 제품출고내역수정 TB_SE_M_DLR *********");
            egovLogger.debug("paramMap: "  + paramMap.toString());
            egovLogger.debug("paramList: " + paramList.toString());
            
            paramMap.put("STDV_STS_DSC", "2");//매출완료
            se6020Service.Call_updateSe6020(paramMap, paramList);
        }
                
        //출고참조나 무참조나 모두 매출기본,상세는 등록함.
        //매출기본내역 저장
        egovLogger.debug("매출기본내역등록 TB_SE_M_SL");   
        egovLogger.debug("paramMap: " + paramMap.toString());
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_SL", paramMap);         
        
        //매입상세내역 저장        
        for(Map<String, Object> map : paramList) 
        {
            map.put("BZPL_C" , paramMap.get("BZPL_C"));
            map.put("SL_DT"  , paramMap.get("SL_DT"));
            map.put("SL_SQNO", paramMap.get("SL_SQNO"));
            
            // 매출상세내역에 대한 정합성 체크를 한다.
            resultMsg = sqlSession.selectOne("sfmes.sqlmap.se.selectSe3010ValidDet", map);
            if(!resultMsg.equals("OK")) 
            {
                throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
            } 
            
            egovLogger.debug("매출상세내역등록 TB_SE_D_SL"); 
            egovLogger.debug("map: " + map.toString());
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_D_SL", map);            
        }

        /**
         * 기타미수금 (회수)등록[/정정/삭제] 한다.
         * @param paramList(채권_외상매출금기본 등록)
         *        === 회수등록 발생의 경우 입력정보 === [REG_DSC:N]
         *         CORP_C     : 회사코드 
         *         BZPL_C     : 사업장코드
         *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
         *         RLTR_DT    : 실거래일자
         *         ACG_DT     : 회계일자
         *         WDR_PLA_DT : 회수예정일자
         *         ADJPL_C    : 정산처코드
         *         CRE_SL_AM  : 외상매출금  
         *         RMK_CNTN   : 비고내용 
         *         TR_BSN_DSC : 거래업무구분코드 (SE10:매출등록 [미등록시:SE10으로 세팅함.] / SE11:매출반입등록)
         *         TR_SQNO    : 거래일련번호 
         *         GUSRID     : 사용자ID
         * @return void형
         * @exception Exception
         */ 
/*
 *  (20220114 기준) 기타미수금 I/F : 운송비만 계산하여 기타미수금 처리 함
 *  
        if(! "0".equals(paramMap.get("TRPCS").toString()))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            paramMap.put("CRE_SL_AM", paramMap.get("TRPCS").toString());
            resultList.add(paramMap);
            
            egovLogger.debug("************ 기타미수금등록 *********");
            egovLogger.debug("resultList: "  + resultList);
            
            ca0100Service.Call_saveEtcAcrv_Ocr(resultList);
        }
*/        
        
        /*----------------------------------------------------------------------------------------------------
         * 기타미수금 I/F 
         * ---------------------------------------------------------------------------------------------------
         * TRPCS    : 운송비
         * LT_COST  : 물류비용
         * ETC_ACRV : 기타미수금 (TRPCS + LT_COST) 
         ----------------------------------------------------------------------------------------------------*/         
        if( ! "0".equals(paramMap.get("ETC_ACRV").toString()) || 
            ! "0".equals(paramMap.get("TRPCS").toString())    || 
            ! "0".equals(paramMap.get("LT_COST").toString()) ) 
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            /*----------------------------------------------------------------------------------------------------
             * 금액정합성체크 : 기타미수금 = (운송비 + 물류비용)
             *----------------------------------------------------------------------------------------------------*/
            Long l_trPcs   = Long.parseLong((String) paramMap.get("TRPCS"));    //운송비
            Long l_ltCost  = Long.parseLong((String) paramMap.get("LT_COST"));  //물류비용
            Long l_etcAcrv = Long.parseLong((String) paramMap.get("ETC_ACRV")); //기타미수금

            if (l_etcAcrv != (l_trPcs + l_ltCost)) {
                throw infoException("기타미수금합계와 운송비+물류비용 합계가 동일하지 않습니다.");
            }
            
            /*----------------------------------------------------------------------------------------------------
             * 기타미수금 등록 I/F
             *----------------------------------------------------------------------------------------------------*/
            paramMap.put("CRE_SL_AM", paramMap.get("ETC_ACRV").toString());
            resultList.add(paramMap);

            egovLogger.debug("************ 기타미수금등록 *********");
            egovLogger.debug("resultList: "  + resultList);
            
            ca0100Service.Call_saveEtcAcrv_Ocr(resultList);
        }
        
        
        /**
         * 외상매출금 (발생)등록[/정정/삭제] 한다.
         * @param paramList(채무_외상매출금기본 [발생]등록)
         *        === 회수등록 발생 입력정보 ===
         *         CORP_C     : 회사코드 
         *         BZPL_C     : 사업장코드
         *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
         *         RLTR_DT    : 실거래일자
         *         ACG_DT     : 회계일자
         *         WDR_PLA_DT : 회수예정일자
         *         ADJPL_C    : 정산처코드
         *         CRE_SL_AM  : 외상매출금  
         *         RMK_CNTN   : 비고내용 
         *         TR_BSN_DSC : 거래업무구분코드 (SE10:매출등록 [미등록시:SE10으로 세팅함.] / SE11:매출반입등록)
         *         TR_SQNO    : 거래일련번호 
         *         GUSRID     : 사용자ID
         * @return void형
         * @exception Exception
         */ 
        if(! "0".equals(paramMap.get("CRE_AM").toString()))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
//            egovLogger.debug("@@TRPCS   : " + paramMap.get("TRPCS"));
//            egovLogger.debug("@@LT_COST : " + paramMap.get("LT_COST"));
            
//            Long l_creSlAm = Long.parseLong((String) paramMap.get("TRPCS"))   //운송비
//                           + Long.parseLong((String) paramMap.get("LT_COST")) //물류비용
//                           + Long.parseLong((String) paramMap.get("CRE_AM"))  //외상 
//                           ;
            
            paramMap.put("CRE_SL_AM", paramMap.get("CRE_AM"));
            resultList.add(paramMap);
            
            egovLogger.debug("************ 외상매출금등록 *********");
            egovLogger.debug("resultList: "  + resultList);
            
            ca0100Service.Call_saveClam_Ocr(resultList);
        }
        
        /**
         * 선수금 (입금)등록[/정정/삭제] 한다.
         * @param paramList(채무_선수금기본 등록)
         *        === 선수금등록 사용의 경우 입력정보 ===
         *         CORP_C     : 회사코드 
         *         BZPL_C     : 사업장코드
         *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
         *         RLTR_DT    : 실거래일자
         *         ACG_DT     : 회계일자
         *         ADJPL_C    : 정산처코드
         *         PRV_AM     : 선수금액
         *         RMK_CNTN   : 비고내용 
         *         TR_BSN_DSC : 거래업무구분코드 (DT21:선수금사용등록 [미등록시:DT21으로 세팅함.])
         *         TR_SQNO    : 거래일련번호
         *         GUSRID     : 사용자ID
         * @return void형
         * @exception Exception
         */
        if(! "0".equals(paramMap.get("PRV_AM").toString()))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            paramMap.put("TR_BSN_DSC", "DT21");
            resultList.add(paramMap);
            
            egovLogger.debug("************ 선수금사용등록 *********");
            egovLogger.debug("resultList: "  + resultList);
            
            ca0200Service.Call_savePrv_Use(resultList);
        }
        
        result = paramMap.toString();
        return result; 
    }

    //매출내역삭제
    @Override
    public void deleteSe3010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 매출삭제[SE3010] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        /*
        //마감여부 체크
        commonService.checkDdl(paramMap);
        */
        
        // 마감여부 체크 추가 (2022.02.08) 구민희
        LinkedHashMap<String, Object> chkDdlYnParam = new LinkedHashMap<String, Object>();
        chkDdlYnParam.put("CORP_C" , paramMap.get("CORP_C")); //회사코드
        chkDdlYnParam.put("BZPL_C" , paramMap.get("BZPL_C")); //사업장코드
        chkDdlYnParam.put("BSN_DSC", "SE");                   //업무구분 SE:매출
        chkDdlYnParam.put("BAS_DT" , paramMap.get("ACG_DT"));  //기준일자 BAS_DT:매출(회계)일자
        
        String ddlYn = commonService.checkDdlYn(chkDdlYnParam);
        if("Y".equals(ddlYn)) {
            throw infoException("해당 매출일자로 업무가 마감되었습니다. 날짜를 확인해주세요.");
        }
        
        int chk_cnt = 0;
        
        LinkedHashMap resultMap = sqlSession.selectOne("sfmes.sqlmap.tb.select_TB_SE_M_SL", paramMap);
        if(! "0".equals(resultMap.get("OGN_SL_SQNO").toString())) 
        {
            throw infoException("이미 매출반품등록된 전표는 삭제할 수 없습니다.");
        }
        
        //매출기본내역 삭제 시 중복체크를 한다.
        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe3010Cnt", paramMap);
        if(chk_cnt > 0)
        {
            throw infoException("이미 삭제된 매출내역입니다.");
        }        
        
        //매출기본내역 수정(전표정상여부: "N")
        egovLogger.debug("매출기본내역수정 TB_SE_M_SL");   
        //sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_SL", paramMap);
        sqlSession.update("sfmes.sqlmap.se.update_SE3010_01", paramMap); //수정해야됨
        
        //제품출고내역 참조한 매출내역 삭제
        if(!"1".equals(paramMap.get("SL_REF_DSC").toString()))
        {
            egovLogger.debug("************ 매출삭제(제품출고참조) [SE3010] *********");
            paramMap.put("STDV_STS_DSC", "1");
            paramMap.put("DLR_STS_DSC", "1");
            paramMap.put("SL_DT", null);
            paramMap.put("SL_SQNO", 0);
            se6020Service.Call_updateSe6020(paramMap, paramList);
        }
        else //무참조매출내역 삭제
        {
            egovLogger.debug("************ 매출삭제(무참조) [SE3010] *********");
            paramMap.put("STDV_DT", paramMap.get("DLR_DT"));
            paramMap.put("STDV_SQNO", paramMap.get("DLR_SQNO"));
            se6020Service.deleteSe6020(paramMap);
        }        
        
        /**
         * 기타미수금 (회수)등록[/정정/삭제] 한다.
         * @param paramList(채권_외상매출금기본 등록)
         *        === 회수등록 발생의 경우 입력정보 === [REG_DSC:N]
         *         CORP_C     : 회사코드 
         *         BZPL_C     : 사업장코드
         *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
         *         RLTR_DT    : 실거래일자
         *         ACG_DT     : 회계일자
         *         WDR_PLA_DT : 회수예정일자
         *         ADJPL_C    : 정산처코드
         *         CRE_SL_AM  : 외상매출금  
         *         RMK_CNTN   : 비고내용 
         *         TR_BSN_DSC : 거래업무구분코드 (SE10:매출등록 [미등록시:SE10으로 세팅함.] / SE11:매출반입등록)
         *         TR_SQNO    : 거래일련번호 
         *         GUSRID     : 사용자ID
         * @return void형
         * @exception Exception
         */
        if(! "0".equals(paramMap.get("TRPCS").toString()))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            paramMap.put("CRE_SL_AM", paramMap.get("TRPCS").toString());
            resultList.add(paramMap);
            
            egovLogger.debug("************ 기타미수금등록 *********");
            egovLogger.debug("resultList: "  + resultList);
            
            ca0100Service.Call_saveEtcAcrv_Ocr(resultList);
        }
        
        /**
         * 외상매출금 (발생)등록[/정정/삭제] 한다.
         * @param paramList(채무_외상매출금기본 [발생]등록)
         *        === 회수등록 발생 입력정보 ===
         *         CORP_C     : 회사코드 
         *         BZPL_C     : 사업장코드
         *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
         *         RLTR_DT    : 실거래일자
         *         ACG_DT     : 회계일자
         *         WDR_PLA_DT : 회수예정일자
         *         ADJPL_C    : 정산처코드
         *         CRE_SL_AM  : 외상매출금 
         *         RMK_CNTN   : 비고내용 
         *         TR_BSN_DSC : 거래업무구분코드 (SE10:매출등록 [미등록시:SE10으로 세팅함.])
         *         TR_SQNO    : 거래일련번호
         *         GUSRID     : 사용자ID
         * @return void형
         * @exception Exception
         */
        if(! "0".equals(paramMap.get("CRE_AM").toString()))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            paramMap.put("CRE_SL_AM", paramMap.get("CRE_AM").toString());
            resultList.add(paramMap);
            
            egovLogger.debug("************ 외상매출금등록 *********");
            egovLogger.debug("resultList: "  + resultList);
            
            ca0100Service.Call_saveClam_Ocr(resultList);
        }
        
        /**
         * 선수금 (입금)등록[/정정/삭제] 한다.
         * @param paramList(채무_선수금기본 등록)
         *        === 선수금등록 사용의 경우 입력정보 ===
         *         CORP_C     : 회사코드 
         *         BZPL_C     : 사업장코드
         *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
         *         RLTR_DT    : 실거래일자
         *         ACG_DT     : 회계일자
         *         ADJPL_C    : 정산처코드
         *         PRV_AM     : 선수금액
         *         RMK_CNTN   : 비고내용 
         *         TR_BSN_DSC : 거래업무구분코드 (DT21:선수금사용등록 [미등록시:DT21으로 세팅함.])
         *         TR_SQNO    : 거래일련번호
         *         GUSRID     : 사용자ID
         * @return void형
         * @exception Exception
         */
        if(! "0".equals(paramMap.get("PRV_AM").toString()))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            paramMap.put("TR_BSN_DSC", "DT21");
            resultList.add(paramMap);
            
            egovLogger.debug("************ 선수금사용등록 *********");
            egovLogger.debug("resultList: "  + resultList);
            
            ca0200Service.Call_savePrv_Use(paramList);
        }
    }
}
