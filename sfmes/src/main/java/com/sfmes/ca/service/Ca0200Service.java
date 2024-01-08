package com.sfmes.ca.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : Ca0200Service.java
 * @Description : 채무처리 Interface
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.09.21  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.21
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Ca0200Service {
    
    /* *********************************************************************************** */
    /* ********************************* 채무등록 ******************************************* */
    /* *********************************************************************************** */
    
    /**
     * 외상매입금 (지급)등록[/정정/삭제] 한다.
     * @param paramList(채무_외상매입금기본 등록)
     *        === 지급등록 발생의 경우 입력정보 === [REG_DSC:N]
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         PY_PLA_DT  : 지급예정일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (외상매입금 등록일 경우 'N'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생] / ['P':지급])
     *         CRE_BY_AM  : 외상매입금  
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BY10:매입등록 [미등록시:BY10으로 세팅함.] / BY11:매입반출등록)
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     *         
     *        === 지급등록 지급의 경우 입력정보 === [REG_DSC:P]
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (외상매입금 지급일 경우 'P'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생] / ['P':지급])
     *         CRE_BY_AM  : 외상매입금 
     *         PY_STL_DSC : 지급결제구분코드 
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT10:외상매입금지급등록 [미등록시:DT10으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 외상매입금내역 저장(다건)
    void Call_saveCbam( List<Map<String, Object>> paramList ) throws Exception;
    
    /**
     * 외상매입금 (발생)등록[/정정/삭제] 한다.
     * @param paramList(채무_외상매입금기본 [발생]등록)
     *        === 지급등록 발생 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         PY_PLA_DT  : 지급예정일자
     *         ADJPL_C    : 정산처코드
     *         CRE_BY_AM  : 외상매입금 
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BY10:매입등록 [미등록시:BY10으로 세팅함.] / BY11:매입반출등록)
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 외상매입금 등록[발생] 저장
    void Call_saveCbam_Ocr( List<Map<String, Object>> paramList ) throws Exception;
    
    /**
     * 외상매입금 (지급)등록[/정정/삭제] 한다.
     * @param paramList(채무_외상매입금기본 지급[등록])
     *        === 지급등록 지급 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         CRE_BY_AM  : 외상매입금 
     *         PY_STL_DSC : 지급결제구분코드 
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT10:외상매입금지급등록 [미등록시:DT10으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 외상매입금 지급 저장
    void Call_saveCbam_Pay( List<Map<String, Object>> paramList ) throws Exception;
    
    /* =================================================================================== */
    /* ============================= 기타미지급금 ============================================ */
    /**
     * 기타미지급금 (지급)등록[/정정/삭제] 한다.
     * @param paramList(채무_기타미지급금기본 등록)
     *        === 지급등록 발생의 경우 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         PY_PLA_DT  : 지급예정일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (기타미지급금 등록일 경우 'N'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생] / ['P':지급])
     *         CRE_BY_AM  : 기타미지급금 
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BY10:매입등록 [미등록시:BY10으로 세팅함.])
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     *         
     *        === 지급등록 지급의 경우 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (기타미지급금 지급일 경우 'P'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생] / ['P':지급])
     *         CRE_BY_AM  : 기타미지급금 
     *         PY_STL_DSC : 지급결제구분코드 
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT30:기타미지급금지급등록 [미등록시:DT30으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 기타미지급금내역 저장(다건)
    void Call_saveEtcUpy( List<Map<String, Object>> paramList ) throws Exception;
    
    /**
     * 기타미지급금 (발생)등록[/정정/삭제] 한다.
     * @param paramList(채무_기타미지급금기본 [발생]등록)
     *        === 지급등록 발생 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         PY_PLA_DT  : 지급예정일자
     *         ADJPL_C    : 정산처코드
     *         CRE_BY_AM  : 기타미지급금 
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BY10:매입등록 [미등록시:BY10으로 세팅함.] / BY11:매입반출등록)
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 기타미지급금 등록[발생] 저장
    void Call_saveEtcUpy_Ocr( List<Map<String, Object>> paramList ) throws Exception;
    
    /**
     * 기타미지급금 (지급)등록[/정정/삭제] 한다.
     * @param paramList(채무_기타미지급금기본 [지급]등록)
     *        === 지급등록 지급 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         CRE_BY_AM  : 기타미지급금 
     *         PY_STL_DSC : 지급결제구분코드 
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT30:기타미지급금지급등록 [미등록시:DT30으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 기타미지급금 지급 저장
    void Call_saveEtcUpy_Pay( List<Map<String, Object>> paramList ) throws Exception;
    
    /* =================================================================================== */
    /* =============================== 선수금 ============================================== */
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
     *         REG_DSC    : 등록구분코드 (선수금 등록일 경우 'N'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생|입금] / ['U':사용])
     *         PRV_AM     : 선수금액 
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT20:선수금입금등록 [미등록시:DT20으로 세팅함.])
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     *         
     *        === 선수금등록 사용의 경우 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (선수금 등록일 경우 'U'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생|입금] / ['U':사용])
     *         PRV_AM     : 선수금액
     *         PY_STL_DSC : 지급결제구분코드 
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT21:선수금사용등록 [미등록시:DT21으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 선수금 입금/사용 등록
    void Call_savePrv( List<Map<String, Object>> paramList ) throws Exception;

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
     *         PY_STL_DSC : 지급결제구분코드 ([미등록시(null일경우):'대체'코드로 세팅함.])
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT20:선수금입금등록 [미등록시:DT20으로 세팅함.])
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 선수금 입금 등록
    void Call_savePrv_Ocr( List<Map<String, Object>> paramList ) throws Exception;

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
     *         PY_STL_DSC : 지급결제구분코드 ([미등록시(null일경우):'대체'코드로 세팅함.])
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT21:선수금사용등록 [미등록시:DT21으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 선수금 사용 등록
    void Call_savePrv_Use( List<Map<String, Object>> paramList ) throws Exception;

    
    /* *********************************************************************************** */
    /* ******************************** 채무잔액 ******************************************** */
    /* *********************************************************************************** */
    
    /**
     * 채무잔액을 조회한다.
     * @param paramList
     *        === 채무잔액 조회 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         TRPL_C     : 거래처코드(거래처코드나 정산처코드 둘중 하나의 코드만 존재하면 됨)
     *         ADJPL_C    : 정산처코드(거래처코드나 정산처코드 둘중 하나의 코드만 존재하면 됨)
     *         REG_DT     : 등록일자(기준일자)
     *         PRC_TP_C   : 업무구분코드(처리구분코드 ['1':외상매입금]['2':기타미지급금]['3':선수금])
     * @return long형
     * @exception Exception
     */
    // 채무잔액조회
    long getDTBac(LinkedHashMap paramMap) throws Exception;

    /**
     * 외상매입금 잔액을 조회한다.
     * @param paramList
     *        === 외상매입금 잔액 조회 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         ADJPL_C    : 정산처코드
     *         REG_DT     : 등록일자(기준일자)
     * @return long형
     * @exception Exception
     */
    // 외상매입금 잔액조회
    long getCbamBac(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 기타미지급금 잔액을 조회한다.
     * @param paramList
     *        === 기타미지급금 잔액 조회 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         ADJPL_C    : 정산처코드
     *         REG_DT     : 등록일자(기준일자)
     * @return long형
     * @exception Exception
     */
    // 기타미지급금 잔액 조회
    long getEtcUpyBac(LinkedHashMap paramMap) throws Exception;

    /**
     * 선수금 잔액을 조회한다.
     * @param paramList
     *        === 선수금 잔액 조회 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         ADJPL_C    : 정산처코드
     *         REG_DT     : 등록일자(기준일자)
     * @return long형
     * @exception Exception
     */
    // 선수금 잔액 조회
    long getPrvBac(LinkedHashMap paramMap) throws Exception;
    
}
