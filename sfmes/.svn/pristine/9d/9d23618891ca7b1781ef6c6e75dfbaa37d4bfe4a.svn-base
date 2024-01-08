package com.sfmes.co.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * 공통 기능을 처리하는 비즈니스 임플리먼트 클래스
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
 *  2020.07.23   장경석    거래일련번호 호출함수 추가
 *  2022.02.04   여다헤    마감업무 체크 함수 추가
 */
@Service("CommonService")
public class CommonServiceImpl extends CmnAbstractServiceImpl implements CommonService {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    /**
     * 신규 채번을 생성하는 메소드
     * @param paramMap - 채번 기준이 담긴 Map
     * @param iCount - 채번 건수
     * @return 상세 내역
     * @exception
     */
	@Override
	public String getGvno(String inCORP_C, String inGVNO_TBL_ID, String inBZPL_C, String inGVNO_DT, int iCount) throws Exception {
		LinkedHashMap resultMap = null;
		LinkedHashMap spParamMap = new LinkedHashMap();
		
		// 입력파라미터를 설정한다.
		spParamMap.put("CORP_C", inCORP_C);
		spParamMap.put("GVNO_TBL_ID", inGVNO_TBL_ID);
		spParamMap.put("BZPL_C", inBZPL_C);
		spParamMap.put("GVNO_DT", inGVNO_DT);
		spParamMap.put("GVNO_LEN", iCount);
		
		String resultGvno = null;
		String resultYn = "N";
		
		// 프로시저를 호출한다.
		sqlSession.selectOne("sfmes.sqlmap.co.selectCo0000Gvno", spParamMap);
		
		resultYn = (String)spParamMap.get("OUT_RESULT_YN");
		resultGvno = (String)spParamMap.get("OUT_GVNO_NO");
		
		if("N".equals(resultYn)) {
			throw infoException("채번 생성 중 오류발생 : " + resultGvno);
		}
		
		return resultGvno;
	}

	/**
     * 거래일련번호 신규 채번을 생성하는 메소드
     * @param paramMap - 채번 기준이 담긴 Map
     * @param iCount - 채번 건수
     * @return 상세 내역
     * @exception
     */
    @Override
    public String getTrGvno(String inCORP_C, int iCount) throws Exception {
        LinkedHashMap resultMap = null;
        LinkedHashMap spParamMap = new LinkedHashMap();
        
        // 입력파라미터를 설정한다.
        spParamMap.put("CORP_C", inCORP_C);
        spParamMap.put("GVNO_LEN", iCount);
        
        String resultGvno = null;
        String resultYn = "N";
        
        // 프로시저를 호출한다.
        sqlSession.selectOne("sfmes.sqlmap.co.selectCo0000TrGvno", spParamMap);
        
        resultYn = (String)spParamMap.get("OUT_RESULT_YN");
        resultGvno = (String)spParamMap.get("OUT_TRGVNO_NO");
        
        if("N".equals(resultYn)) {
            throw infoException("거래일련번호 채번 생성 중 오류발생 : " + resultGvno);
        }
        
        return resultGvno;
    }

    
    /**
     * 기준단위코드와 변환될 계산단위코드를 받아서 수량을 환산하여 리턴하는 메소드
     * @param inBAS_UNT_C(기준단위코드)
     * @param inCAL_UNT_C(계산단위코드)
     * @param iUNT_QT(변환할 값)
     * @param iDOT_POINT(소숫점자릿수)
     * @return 환산수량(더블)
     * @exception
     */
    @Override
    public double getCvsQt(String inBAS_UNT_C, String inCAL_UNT_C, float inUNT_QT, int inDOT_POINT) throws Exception {
        LinkedHashMap resultMap = null;
        LinkedHashMap spParamMap = new LinkedHashMap();
        
        egovLogger.debug("************ 공통_환산중량계산[Common] *********");
        
        // 입력파라미터를 설정한다.
        spParamMap.put("BAS_UNT_C"  , inBAS_UNT_C);
        spParamMap.put("CAL_UNT_C"  , inCAL_UNT_C);
        spParamMap.put("UNT_QT"     , inUNT_QT   );
        spParamMap.put("DOT_POINT"  , inDOT_POINT);
        
        egovLogger.debug("inBAS_UNT_C : "  + inBAS_UNT_C);
        egovLogger.debug("inCAL_UNT_C : "  + inCAL_UNT_C);
        egovLogger.debug("inUNT_QT    : "  + inUNT_QT); 
        egovLogger.debug("inDOT_POINT : "  + inDOT_POINT);
        egovLogger.debug("*******************************************");
        egovLogger.debug("paramMap: "  + spParamMap.toString());

        // 프로시저를 호출한다.
        double resultQt = sqlSession.selectOne("sfmes.sqlmap.co.selectCo0000Cvs_Qt", spParamMap);
        
        egovLogger.debug("resultQt : "  + resultQt);
        return resultQt;
    }


	@Override
	public String testError(LinkedHashMap paramMap) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("sfmes.sqlmap.tb.insert_TB_TE_M_TEST_USR",paramMap);
		return null;
	}
	
	//환산기준정보 조회
    @Override
    public List<?> selectUnitConversionTable() throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectUnitConversionTable");
    }
    
    /**
     * 마감일자의 마감여부를 체크하는 메소드
     * @param CORP_C(회사코드), BZPL_C(사업장코드), AGC_DT(회계일자), TR_DSC(업무구분:BY.매입,PD.생산,SE.매출)
     * @return 없음
     * @exception
     */    
	@Override
	public void checkDdl(LinkedHashMap paramMap) throws Exception {
		String resultMsg = "";
		
		resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.co.selectDdlCheck",paramMap);

		// 마감여부 체크 후 오류처리한다.
		if( !"OK".equals(resultMsg) ) {
			throw infoException(resultMsg);			
		}
		return;
	}

    /**
     * 마감일자의 마감여부를 체크하는 메소드 (DB함수 콜) _ 20220203 여다혜 추가
     * @param CORP_C(회사코드), BZPL_C(사업장코드), BAS_DT(기준일자)
     *        BSN_DSC(업무구분) - BY:매입, SE:매출, PD:생산, CA:채권채무(정산), HLDY:공휴일
     * @return String, 마감여부(DDL_YN)
     * @exception
     * 
     * checkDdl (기존마감업무 체크 로직)의 경우 쿼리 체크하며 TB_DL_M_DAY_DDL 테이블을 바라봄
     * checkDdlYn 을 추가하여 DB함수 처리로 수정하며, TB_SY_B_DDL 테이블에서 마감여부 조회하도록 수정함
     * DBO.SF_GET_DDL_YN 함수 참조
     *  
     * 2022.02.03 여다혜 추가
     */    	
	
    @Override
    public String checkDdlYn(LinkedHashMap paramMap) throws Exception {
        String ddlYn = "";
        String resultMsg = "";
        
        //마감여부 체크 전 파라미터 null체크
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.co.selectDdlYn_valid", paramMap);
        if(!"OK".equals(resultMsg)) {
            throw infoException(resultMsg); 
        }
        
        //마감여부 조회
        ddlYn = sqlSession.selectOne("sfmes.sqlmap.co.selectDdlYn", paramMap);
        
        //마감여부 조회 결과 NULL 일 때
        if(ddlYn == null || ddlYn.equals("")) {
            throw infoException("해당일자 마감여부를 확인할 수 없습니다.\n\n관리자에게 문의하세요.");
        }
        
        return ddlYn;
    }    
}
