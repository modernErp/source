package com.sfmes.co.service;

import java.util.LinkedHashMap;
import java.util.List;


/**
 * 공통 기능을 처리하는 비즈니스 인터페이스 클래스
 * 
 * @author 
 * @since 2020.05.28
 * @version 1.0
 * @see
 * 
 * 
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  ----------   ------    ---------------------------
 *  2020.06.23   이철홍    최초작성 
 */
public interface CommonService {
    
    /**
     * 신규 채번을 생성하는 메소드
     * @param inCORP_C(회사코드), inGVNO_TBL_ID(테이블id), inBZPL_C(사업장코드:옵션), inGVNO_DT(기준일자:옵션), iCount(건수)
     * @return 채번번호(문자열)
     * @exception
     */     
    String getGvno(String inCORP_C, String inGVNO_TBL_ID, String inBZPL_C, String inGVNO_DT, int iCount) throws Exception;
    
    /**
     * 신규 거래일련번호 채번을 생성하는 메소드
     * @param inCORP_C(회사코드), iCount(건수)
     * @return 채번번호(문자열)
     * @exception
     */     
    String getTrGvno(String inCORP_C, int iCount) throws Exception;
    
    /**
     * 기준단위코드와 변환될 계산단위코드를 받아서 수량을 환산하여 리턴하는 메소드
     * @param inBAS_UNT_C(기준단위코드), iCount(계산단위코드), inUNT_QT(변환할 값), inDOT_POINT(소숫점자릿수)
     * @return 환산수량(더블)
     * @exception
     */     
    double getCvsQt(String inBAS_UNT_C, String inCAL_UNT_C, float inUNT_QT, int inDOT_POINT) throws Exception;
    
    /**
     * 비지니스 호출을 위한 테스트 메소드
     * @param inCORP_C(회사코드), inGVNO_TBL_ID(테이블id), inBZPL_C(사업장코드:옵션), inGVNO_DT(기준일자:옵션), iCount(건수)
     * @return 채번번호(문자열)
     * @exception
     */     
    String testError(LinkedHashMap paramMap) throws Exception;
    
    
    /**
     * 단위환산표 기준정보 조회
     * @param  N/A
     * @return 단위환산정보List
     * @exception
     */    
    List<?> selectUnitConversionTable() throws Exception;
    
    /**
     * 마감일자의 마감여부를 체크하는 메소드
     * @param CORP_C(회사코드), BZPL_C(사업장코드), AGC_DT(회계일자), TR_DSC(업무구분:BY.매입,PD.생산,SE.매출)
     * @return 없음
     * @exception
     * 
     */     
    void checkDdl(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 마감일자의 마감여부를 체크하는 메소드 (DB함수 콜) _ 20220203 여다혜 추가
     * @param CORP_C(회사코드), BZPL_C(사업장코드), BAS_DT(기준일자)
     *        BSN_DSC(업무구분) - BY:매입, SE:매출, PD:생산, CA:채권채무(정산), HLDY:공휴일
     * @return String, 마감여부(DDL_YN)
     * @exception
     * 
     */        
    String checkDdlYn(LinkedHashMap paramMap) throws Exception;
}
