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
import com.sfmes.se.service.Se3020Service;
import com.sfmes.sm.service.Sm1000Service;
import com.sfmes.sm.service.Sm1010Service;

/**
 * @Class Name : Se3020ServiceImpl.java
 * @Description : 매출반품등록/수정 및 조회
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.10.12  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) Se 모든솔루션 All right reserved.
 */

@Service("Se3020Service")
public class Se3020ServiceImpl extends CmnAbstractServiceImpl implements Se3020Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;   
    
    //입고 서비스 선언
    @Autowired
    private Sm1000Service sm1000Service;    
    
    //입고 서비스 선언
    @Autowired
    private Sm1010Service sm1010Service;     
    
    //정산 서비스 선언
    @Autowired
    private Ca0100Service ca0100Service;  
    
    //정산 서비스 선언
    @Autowired
    private Ca0200Service ca0200Service;     

    //매출반품기본내역 조회
    @Override
    public List<?> selectSe3020_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매출반품기본내역조회[SE3020] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3010_01", paramMap);
    }

    //매출반품상세내역 조회
    @Override
    public List<?> selectSe3020_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매출반품상세내역조회[SE3020] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3010_02", paramMap);
    }
    
    //매출정산내역 조회
    @Override
    public List<?> selectSe3020_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매출정산내역조회[SE3020] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe3010_05", paramMap);
    }    
    
    //매출반품기본,상세내역 등록
    @Override
    public String insertSe3020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception { 
        egovLogger.debug("************ 매출반품등록[SE3020] *********");
        
        /*
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        //마감여부 체크
        commonService.checkDdl(paramMap);
        */
        
        // 마감여부 체크 추가 (2022.02.08) 구민희
        LinkedHashMap<String, Object> chkDdlYnParam = new LinkedHashMap<String, Object>();
        chkDdlYnParam.put("CORP_C" , paramMap.get("CORP_C")); //회사코드
        chkDdlYnParam.put("BZPL_C" , paramMap.get("BZPL_C")); //사업장코드
        chkDdlYnParam.put("BSN_DSC", "SE");                   //업무구분 SE:매출
        chkDdlYnParam.put("BAS_DT" , paramMap.get("ACG_DT"));  //기준일자 BAS_DT:매출반품(회계)일자
        
        String ddlYn = commonService.checkDdlYn(chkDdlYnParam);
        if("Y".equals(ddlYn)) {
            throw infoException("해당 매출반품일자로 업무가 마감되었습니다. 날짜를 확인해주세요.");
        }
        
        String result = "";
        String resultMsg = null;  //결과메세지
        int chk_cnt = 0;
        
        //매출반품기본내역 저장 시 중복체크를 한다.
        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe3010Cnt", paramMap);
        if(chk_cnt > 0)
        {
            throw infoException("이미 등록된 매출반품내역입니다.");
        }
        
        //매출반품기본내역에 대한 정합성 체크를 한다.
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.se.selectSe3010Valid", paramMap);
        if(! resultMsg.equals("OK")) 
        {
            throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
        }
        
        //채번을 위한 변수설정
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_SL_DT  = paramMap.get("SL_DT").toString();
        
        //채번 서비스 호출(매출반품일련번호)
        String seqNo = commonService.getGvno(s_CORP_C,"TB_SE_M_SL",s_BZPL_C, s_SL_DT, 1);
        egovLogger.debug("생성된 매출반품일련번호 채번: " + seqNo);
        paramMap.put("SL_SQNO", seqNo);

        //채번 서비스 호출(거래일련번호)
        String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
        egovLogger.debug("생성된 거래일련번호 채번: " + tr_seqNo);
        paramMap.put("TR_SQNO", tr_seqNo);            
                
        //매출반품기본내역 저장
        egovLogger.debug("매출반품기본내역등록 TB_SE_M_SL");   
        egovLogger.debug("paramMap: " + paramMap.toString());
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_SL", paramMap);   
        
        //원매출참조 매출반품등록 시 
        if(! ("0".equals(paramMap.get("OGN_SL_SQNO").toString()) || "".equals(paramMap.get("OGN_SL_SQNO").toString()))) 
        {
            egovLogger.debug("원매출기본내역수정 TB_SE_M_SL");  
            sqlSession.update("sfmes.sqlmap.se.update_SE3020", paramMap);  
        }        
        
        //매출반품상세내역 저장        
        for(Map<String, Object> map : paramList) 
        {
            map.put("BZPL_C" , paramMap.get("BZPL_C"));
            map.put("SL_DT"  , paramMap.get("SL_DT"));
            map.put("SL_SQNO", paramMap.get("SL_SQNO"));
            
            // 매출반품상세내역에 대한 정합성 체크를 한다.
            resultMsg = sqlSession.selectOne("sfmes.sqlmap.se.selectSe3010ValidDet", map);
            if(! resultMsg.equals("OK")) 
            {
                throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
            } 
            
            egovLogger.debug("매출반품상세내역등록 TB_SE_D_SL"); 
            egovLogger.debug("map: " + map.toString());
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_D_SL", map);            
        }

        /**
         * 물품 재고 입출고 등록을 한다.
         * @param paramMap1, paramMap2(물품입출고 등록 기본항목)
         *         CORP_C : 회사코드 
         *         BZPL_C : 사업장코드
         *         STDV_DSC : 입출고구분코드(I.입고,O.출고)
         *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
         *         RLTR_DT : 입출고일자(검수일자)
         *         TRPL_C : 거래처코드
         *         STDV_STS_DSC : 입출고상태구분코드(1:검수입고/출고완료, 2:매입/매출, 3:매입반출/매출반품)
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
         * @return void형
         * @exception Exception
         */
        
        //물품 재고 입출고 저장하기 위해 컬럼명 Mapping
        paramMap.put("STDV_DSC"    , "I");  //입출고구분코드
        paramMap.put("STDV_STS_DSC", "3");  //입출고상태구분코드 (3 : 매입반품)
        
        for(int i=0; i<paramList.size(); i++) 
        {
            paramList.get(i).put("STDV_QT"    , paramList.get(i).get("SL_QT"));
            paramList.get(i).put("STDV_BOX_QT", paramList.get(i).get("SL_BOX_QT"));
            paramList.get(i).put("STDV_UPR"   , paramList.get(i).get("SL_UPR"));
            paramList.get(i).put("STDV_AM"    , paramList.get(i).get("SL_AM"));
            paramList.get(i).put("STDV_WT"    , paramList.get(i).get("SL_WT"));
        }
        
        egovLogger.debug("************ 입출고내역등록 TB_SM_M_GDS_RL_STDV *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        paramMap.put("STDV_REF_DSC", "1");
        sm1000Service.Call_saveSm1000(paramMap, paramList, null, null);
        
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
            egovLogger.debug("resultList: " + resultList);
            
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
            
            paramMap.put("CRE_SL_AM", paramMap.get("CRE_AM").toString());
            resultList.add(paramMap);
            
            egovLogger.debug("************ 외상매출금등록 *********");
            egovLogger.debug("resultList: " + resultList);
            
            ca0100Service.Call_saveClam_Ocr(resultList);
        }
        
        /**
         * 선수금 (입금)등록[/정정/삭제] 한다.
         * @param paramList(채무_선수금기본 등록)
         *        === 선수금등록 발생(입금)의 경우 입력정보 ===
         *         CORP_C     : 회사코드 
         *         BZPL_C     : 사업장코드
         *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
         *         RLTR_DT    : 실거래일자
         *         ACG_DT     : 회계일자
         *         ADJPL_C    : 정산처코드
         *         PRV_AM     : 선수금액 
         *         RMK_CNTN   : 비고내용 
         *         TR_BSN_DSC : 거래업무구분코드 (DT20:선수금입금등록 [미등록시:DT20으로 세팅함.])
         *         TR_SQNO    : 거래일련번호 
         *         GUSRID     : 사용자ID
         * @return void형
         * @exception Exception
         */
        if(! "0".equals(paramMap.get("PRV_AM").toString()))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            paramMap.put("TR_BSN_DSC", "DT20");
            resultList.add(paramMap);
            
            egovLogger.debug("************ 선수금입금등록 *********");
            egovLogger.debug("resultList: " + resultList);
            
            // 선수금 입금 등록
            ca0200Service.Call_savePrv_Ocr(resultList);
        }
        
        result = paramMap.toString();
        return result; 
    }

    //매출반품내역삭제
    @Override
    public void deleteSe3020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 매출반품삭제[SE3020] *********");
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
        chkDdlYnParam.put("BAS_DT" , paramMap.get("ACG_DT"));  //기준일자 BAS_DT:매출반품(회계)일자
        
        String ddlYn = commonService.checkDdlYn(chkDdlYnParam);
        if("Y".equals(ddlYn)) {
            throw infoException("해당 매출반품일자로 업무가 마감되었습니다. 날짜를 확인해주세요.");
        }
        
        //매출반품기본내역 수정(전표정상여부: "N")
        egovLogger.debug("매출반품기본내역수정 TB_SE_M_SL");        
        sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_SL", paramMap);  
        
        if(! ("0".equals(paramMap.get("OGN_SL_SQNO").toString()) || "".equals(paramMap.get("OGN_SL_SQNO").toString()))) 
        {
            egovLogger.debug("원매출참조 매출반품기본내역수정 TB_SE_M_SL"); 
            paramMap.put("SL_DT"  , null);
            paramMap.put("SL_SQNO", 0);
            sqlSession.update("sfmes.sqlmap.se.update_SE3020", paramMap);  
        }
        
        //입고테이블에 필요한 PARAMETER
        paramMap.put("STDV_DSC"    , "I");
        paramMap.put("STDV_STS_DSC", "3");

        for(int i=0; i<paramList.size(); i++) 
        {
            paramList.get(i).put("STDV_QT"    , paramList.get(i).get("SL_QT")    );
            paramList.get(i).put("STDV_BOX_QT", paramList.get(i).get("SL_BOX_QT"));
            paramList.get(i).put("STDV_UPR"   , paramList.get(i).get("SL_UPR")   );
            paramList.get(i).put("STDV_AM"    , paramList.get(i).get("SL_AM")    );
            paramList.get(i).put("STDV_WT"    , paramList.get(i).get("SL_WT")    );
        }     
        
        /**
         * 물품 재고 입출고 등록을 한다.
         * @param paramMap1, paramMap2(물품입출고 등록 기본항목)
         *         CORP_C : 회사코드 
         *         BZPL_C : 사업장코드
         *         STDV_DSC : 입출고구분코드(I.입고,O.출고)
         *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
         *         RLTR_DT : 입출고일자(검수일자)
         *         TRPL_C : 거래처코드
         *         STDV_STS_DSC : 입출고상태구분코드(1:검수입고/출고완료, 2:매입/매출, 3:매입반출/매출반입)
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
         *         BUDL_NO : 묶음번호W
         *         QT_WT_DSC : 수(중)량형구분코드
         *         WHT_QT : 단량
         *         WHT_UNT_C : 단량단위코드
         *         STDV_WT : 입출고중량l
         *         TXT_DSC : 과세구분코드
         *         RMK_CNTN : 비고내용l
         *         DEL_YN : 삭제여부
         *         FLAG_STDV_DSC_IO_YN : 출고,입고 값이 둘다 있는경우 
         * @return void형
         * @exception Exception
         */
        
        sm1000Service.Call_saveSm1000(paramMap, paramList, null, null);
        
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
            egovLogger.debug("resultList: " + resultList);
            
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
        if(! ("0".equals(paramMap.get("CRE_AM").toString()) || "".equals(paramMap.get("CRE_AM").toString())))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            paramMap.put("CRE_SL_AM", paramMap.get("CRE_AM").toString());
            resultList.add(paramMap);
            
            egovLogger.debug("************ 외상매출금등록 *********");
            egovLogger.debug("resultList: " + resultList);
            
            ca0100Service.Call_saveClam_Ocr(resultList);
        }
        
        /**
         * 선수금 (입금)등록[/정정/삭제] 한다.
         * @param paramList(채무_선수금기본 등록)
         *        === 선수금등록 발생(입금)의 경우 입력정보 ===
         *         CORP_C     : 회사코드 
         *         BZPL_C     : 사업장코드
         *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
         *         RLTR_DT    : 실거래일자
         *         ACG_DT     : 회계일자
         *         ADJPL_C    : 정산처코드
         *         PRV_AM     : 선수금액 
         *         RMK_CNTN   : 비고내용 
         *         TR_BSN_DSC : 거래업무구분코드 (DT20:선수금입금등록 [미등록시:DT20으로 세팅함.])
         *         TR_SQNO    : 거래일련번호 
         *         GUSRID     : 사용자ID
         * @return void형
         * @exception Exception
         */
        if(! "0".equals(paramMap.get("PRV_AM").toString()))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            paramMap.put("TR_BSN_DSC", "DT20");
            resultList.add(paramMap);
            
            egovLogger.debug("************ 선수금입금등록 *********");
            egovLogger.debug("resultList: " + resultList);
            
            // 선수금 입금 등록
            ca0200Service.Call_savePrv_Ocr(resultList);
        }             
    }
}
