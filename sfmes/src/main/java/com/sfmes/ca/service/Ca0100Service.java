package com.sfmes.ca.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : Ca0100Service.java
 * @Description : 채권처리 Interface
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
public interface Ca0100Service {

    /* =================================================================================== */
    /* ============================= 외상매출금 ============================================= */
    
    /**
     * 외상매출금 (회수)등록[/정정/삭제] 한다.
     * @param paramList(채무_외상매출금기본 등록)
     *        === 회수등록 발생의 경우 입력정보 === [REG_DSC:N]
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         WDR_PLA_DT : 회수예정일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (외상매출금 등록일 경우 'N'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생] / ['R':회수])
     *         CRE_SL_AM  : 외상매출금  
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (SE10:매출등록 [미등록시:SE10으로 세팅함.] / SE11:매출반입등록)
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     *         
     *        === 회수등록 회수의 경우 입력정보 === [REG_DSC:R]
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (외상매출금 회수일 경우 'R'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생] / ['R':회수])
     *         CRE_SL_AM  : 외상매출금 
     *         PY_STL_DSC : 지급결제구분코드 
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BD10:외상매출금회수등록 [미등록시:BD10으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 외상매출금내역 저장(다건)
    void Call_saveClam( List<Map<String, Object>> paramList ) throws Exception;
    
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
    // 외상매출금 등록[발생] 저장
    void Call_saveClam_Ocr( List<Map<String, Object>> paramList ) throws Exception;
    
    /**
     * 외상매출금 (회수)등록[/정정/삭제] 한다.
     * @param paramList(채권_외상매출금기본 [회수]등록)
     *        === 회수등록 회수 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         CRE_SL_AM  : 외상매출금 
     *         PY_STL_DSC : 지급결제구분코드 
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BD10:외상매출금회수등록 [미등록시:BD10으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 외상매출금 회수 저장
    void Call_saveClam_Rcv( List<Map<String, Object>> paramList ) throws Exception;
    
    
    /* =================================================================================== */
    /* ============================= 기타미수금 ============================================= */
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
     *         REG_DSC    : 등록구분코드 (외상매출금 등록일 경우 'N'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생] / ['R':회수])
     *         CRE_SL_AM  : 외상매출금  
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (SE10:매출등록 [미등록시:SE10으로 세팅함.] / SE11:매출반입등록)
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     *         
     *        === 회수등록 회수의 경우 입력정보 === [REG_DSC:R]
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (외상매출금 회수일 경우 'R'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생] / ['R':회수])
     *         CRE_SL_AM  : 외상매출금 
     *         PY_STL_DSC : 지급결제구분코드 
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BD30:기타미수금회수등록 [미등록시:BD30으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 기타미수금 등록/회수 저장
    void Call_saveEtcAcrv( List<Map<String, Object>> paramList ) throws Exception;
    
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
    // 기타미수금 등록[발생] 저장
    void Call_saveEtcAcrv_Ocr( List<Map<String, Object>> paramList ) throws Exception;

    /**
     * 기타미수금 (회수)등록[/정정/삭제] 한다.
     * @param paramList(채권_외상매출금기본 등록)
     *        === 회수등록 회수의 경우 입력정보 === [REG_DSC:R]
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         CRE_SL_AM  : 외상매출금 
     *         PY_STL_DSC : 지급결제구분코드 
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BD30:기타미수금회수등록 [미등록시:BD30으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 기타미수금 회수 저장
    void Call_saveEtcAcrv_Rcv( List<Map<String, Object>> paramList ) throws Exception;
    
    /* =================================================================================== */
    /* =============================== 선급금 ============================================== */
    /**
     * 선급금 (사용)등록[/정정/삭제] 한다.
     * @param paramList(채권_선급금기본 등록)
     *        === 선급금등록 발생(지급)의 경우 입력정보 === [REG_DSC:N]
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (선급금 등록일 경우 'N'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생|지급] / ['U':사용])
     *         PPY_AM     : 선급금액  
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BD20:선급금지급등록 [미등록시:BD20으로 세팅함.])
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     *         
     *        === 선급금등록 사용의 경우 입력정보 === [REG_DSC:U]
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (선급금 회수일 경우 'U'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생|지급] / ['U':사용])
     *         PPY_AM     : 선급금액 
     *         PY_STL_DSC : 지급결제구분코드 
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BD21:선급금사용등록 [미등록시:BD21으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 선급금 지급/사용 등록
    void Call_savePryam( List<Map<String, Object>> paramList ) throws Exception;
    
    /**
     * 선급금 (사용)등록[/정정/삭제] 한다.
     * @param paramList(채권_선급금기본 등록)
     *        === 선급금등록 발생(지급)의 경우 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         PPY_AM     : 선급금액  
     *         PY_STL_DSC : 지급결제구분코드 ([미등록시(null일경우):'대체'코드로 세팅함.])
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BD20:선급금지급등록 [미등록시:BD20으로 세팅함.])
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 선급금 지급 등록
    void Call_savePryam_Ocr( List<Map<String, Object>> paramList ) throws Exception;

    /**
     * 선급금 (사용)등록[/정정/삭제] 한다.
     * @param paramList(채권_선급금기본 등록)
     *        === 선급금등록 사용의 경우 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         PPY_AM     : 선급금액 
     *         PY_STL_DSC : 지급결제구분코드 ([미등록시(null일경우):'대체'코드로 세팅함.])
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BD21:선급금사용등록 [미등록시:BD21으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 선급금 사용 등록
    void Call_savePryam_Use( List<Map<String, Object>> paramList ) throws Exception;

    
    /* *********************************************************************************** */
    /* ******************************** 채권잔액 ******************************************** */
    /* *********************************************************************************** */
    
    /**
     * 채권잔액을 조회한다.
     * @param paramList
     *        === 채권잔액 조회 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         TRPL_C     : 거래처코드(거래처코드나 정산처코드 둘중 하나의 코드만 존재하면 됨)
     *         ADJPL_C    : 정산처코드(거래처코드나 정산처코드 둘중 하나의 코드만 존재하면 됨)
     *         REG_DT     : 등록일자(기준일자)
     *         PRC_TP_C   : 업무구분코드(['1':외상매출금]['2':기타미수금]['3':선급금])
     * @return long형
     * @exception Exception
     */
    // 채권잔액조회
    long getBDBac(LinkedHashMap paramMap) throws Exception;

    /**
     * 외상매출금 잔액을 조회한다.
     * @param paramList
     *        === 외상매출금 잔액 조회 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         ADJPL_C    : 정산처코드
     *         REG_DT     : 등록일자(기준일자)
     * @return long형
     * @exception Exception
     */
    // 외상매출금 잔액조회
    long getClamBac(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 기타미수금 잔액을 조회한다.
     * @param paramList
     *        === 기타미수금 잔액 조회 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         ADJPL_C    : 정산처코드
     *         REG_DT     : 등록일자(기준일자)
     * @return long형
     * @exception Exception
     */
    // 기타미수금 잔액 조회
    long getEtcAcrvBac(LinkedHashMap paramMap) throws Exception;

    /**
     * 선급금 잔액을 조회한다.
     * @param paramList
     *        === 선급금 잔액 조회 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         ADJPL_C    : 정산처코드
     *         REG_DT     : 등록일자(기준일자)
     * @return long형
     * @exception Exception
     */
    // 선급금 잔액 조회
    long getPryamBac(LinkedHashMap paramMap) throws Exception;
    
}
