package com.sfmes.sm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.IF_PD_SM_HST_MNGService;
import com.sfmes.sm.service.Sm1000Service;

/**
 * @Class Name  : Sm1000ServiceImpl.java
 * @Description : 재고 입출고 등록 및 월집계 인터페이스
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.09.01  이철홍      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Sm1000Service")
public class Sm1000ServiceImpl extends CmnAbstractServiceImpl implements Sm1000Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    //20200908ksm_추가
    @Autowired
    private IF_PD_SM_HST_MNGService IF_PD_SM_HST_MNGService;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
        
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
	 *         STDV_REF_DSC : 입출고참조구분코드(1:무참조, 2:발주참조, 3:출고지시참조, 4:출고의뢰참조)
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
	 *         CONDU_NO : 도체(지육)번호
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
    @Override
    public void Call_saveSm1000(LinkedHashMap paramMap1, List<Map<String, Object>> paramList1, LinkedHashMap paramMap2, List<Map<String, Object>> paramList2) throws Exception {
        egovLogger.debug("************ 입출고동시등록[Sm1000] *********");
        egovLogger.debug("paramMap1: "  + paramMap1.toString());
        egovLogger.debug("paramList1: " + paramList1.toString());

        // 재고 입출고 기본 내역이 없는 경우 오류 처리
        if(paramMap1 == null) {
            throw infoException("USERMSG:재고 입출고 기본 내역이 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 재고 입출고 기본 내역이 없는 경우 오류 처리
        if(paramMap1.isEmpty()) {
            throw infoException("USERMSG:재고 입출고 기본 내역이 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 재고 입출고 상세 내역이 없는 경우 오류 처리
        if(paramList1 == null) {
        	throw infoException("USERMSG:재고 입출고 상세 내역이 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 재고 입출고 상세 내역이 없는 경우 오류 처리
        if(paramList1.size() == 0) {
            throw infoException("USERMSG:재고 입출고 상세 내역이 없습니다. 전산담당자에게 문의하세요.");
        }
        
        String resultMsg = null;  //결과메세지
        
        // 재고 입출고 기본 내역에 대한 정합성 체크를 한다.
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.sm.selectSm1000Valid", paramMap1);

        if  (!resultMsg.equals("OK")) {
            throw infoException("USERMSG:" + resultMsg + "전산담당자에게 문의하세요");
        }
        
        // 재고 입출고(입출고 동시발생 시) 기본 내역이 있는 경우
        if( paramMap2 != null ) {
            egovLogger.debug("paramMap2: "  + paramMap2.toString());
            egovLogger.debug("paramList2: " + paramList2.toString());
	        if(!paramMap2.isEmpty()) {
	        	
	            // 재고 입출고 기본 내역에 대한 정합성 체크를 한다.
	            resultMsg = sqlSession.selectOne("sfmes.sqlmap.sm.selectSm1000Valid", paramMap2);
	
	            if  (!resultMsg.equals("OK")) {
	                throw infoException("USERMSG:" + resultMsg + "전산담당자에게 문의하세요(추가내역)");
	            }
	        	
	            // 재고 입출고(입출고 동시발생 시) 상세 내역이 없는 경우 오류 처리
	            if(paramList2.size() == 0) {
	                throw infoException("USERMSG:재고 입출고 추가 상세 내역이 없습니다. 전산담당자에게 문의하세요.");
	            }
	        }
        }
        
        
     
        
        // 기 등록된 입출고 기본 내역이 있는지 확인하고 이미 등록된 내역이 있는 경우 삭제 처리한다.(월별 재고 재집계 처리)
        //2022.01.12 서광석 해당함수 사용안함으로 인한 막음
        //아래에 있는 물품월집계에서 모두 처리
        //this.Call_Sm1000MonthChk(paramMap1);

        if(paramMap1.get("SLP_NML_YN").equals("N"))
        {
            return ;
        }
        
        //채번을 위한 변수설정
        String s_CORP_C  = paramMap1.get("CORP_C").toString();
        String s_BZPL_C  = paramMap1.get("BZPL_C").toString();
        
        //오늘날짜구하기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        
        //채번 서비스 호출
        String seqNo = commonService.getGvno(s_CORP_C,"TB_SM_M_GDS_RL_STDV",s_BZPL_C, strToday, 1);
        egovLogger.debug("생성된 입고일련번호 채번: " + seqNo);
        paramMap1.put("STDV_DT"  , strToday);
        paramMap1.put("STDV_SQNO", seqNo);

        // 입출고기본내역저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_M_GDS_RL_STDV", paramMap1);
        
        int idx = 0;				// 입출고상세일련번호 설정

        // 2020-11-13 JKS Add
        // 축산물이럭번호 I/F 를 위한 map 선언
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> inf_dlr_map = new LinkedHashMap<>();
                
        // 입출고상세내역저장
        for(Map<String, Object> map : paramList1) {
            map.put("CORP_C"   , paramMap1.get("CORP_C"));
            map.put("BZPL_C"   , paramMap1.get("BZPL_C"));
            map.put("STDV_DT"  , paramMap1.get("STDV_DT"));
            map.put("STDV_DSC" , paramMap1.get("STDV_DSC"));
            map.put("STDV_SQNO", paramMap1.get("STDV_SQNO"));
            map.put("GUSRID"   , paramMap1.get("GUSRID"));
            map.put("STDV_DSQNO", ++idx);
            
            // 사전 정합성 체크를 한다.
            resultMsg = sqlSession.selectOne("sfmes.sqlmap.sm.selectSm1000ValidDet", map);

            if  (!resultMsg.equals("OK")) {
                throw infoException("USERMSG:" + resultMsg);
            }
            egovLogger.debug("map: " + map);
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_D_GDS_RL_STDV", map);   
        
        }
        
        
        // paramMap2가 존재하면
        if( paramMap2 != null ) {
	        if(! paramMap2.isEmpty()) {
	            paramMap2.put("STDV_SQNO", seqNo);
	            paramMap2.put("STDV_DT"  , strToday);
	
	            //입출고기본내역저장
	            egovLogger.debug("입출고기본내역등록 TB_SM_M_GDS_RL_STDV");
	            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_M_GDS_RL_STDV", paramMap2);
	            idx = 0;				// 입출고상세일련번호 설정
	            //입출고상세내역저장
	            for(Map<String, Object> map2 : paramList2) {
	            	map2.put("CORP_C"   , paramMap2.get("CORP_C"));
	            	map2.put("BZPL_C"   , paramMap2.get("BZPL_C"));
	            	map2.put("STDV_DT"  , paramMap2.get("STDV_DT"));
	            	map2.put("STDV_DSC" , paramMap2.get("STDV_DSC"));
	            	map2.put("STDV_SQNO", paramMap2.get("STDV_SQNO"));
	            	map2.put("GUSRID"   , paramMap2.get("GUSRID"));
	            	map2.put("STDV_DSQNO", ++idx);
	                
	            	if( map2.get("FLAG_STDV_DSC_IO_YN").equals("N") ) {
                        continue;
                    }
	            	
	                // 사전 정합성 체크를 한다.
	                resultMsg = sqlSession.selectOne("sfmes.sqlmap.sm.selectSm1000ValidDet", map2);
	
	                if  (!resultMsg.equals("OK")) {
	                    throw infoException("USERMSG:" + resultMsg);
	                }
	            	
	                egovLogger.debug("map: " + map2);
	                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_D_GDS_RL_STDV", map2);   
	            }
	        }
        }
        
        // 축산물이력처리
        //2021.10.16 서광석
        //축산물이력처리 임시막음(대표님 요청사항) : MES에서 축산물이력관리 안함
        //Call_Sm1000GdsHstNoProc(paramMap1, "N");
        
        
        
        //2022.01.12 물품 월집계 처리방식 변경(직접쿼리 -> DB프로시저 호출방식으로 변경)
        //DB프로시저의 트랜잭션이 분리되어 있으므로 물품 월집계 함수는 항상 마지막에 위치하도록 처리필요
        //Sm1000ServiceImpl.java 에 기능추가시 물품월집계 기능 위에 선언바람!!
        this.Call_Sm1000MonthTot(paramMap1);
    }
    
	/**
	 * 재고 월별집계 변경 처리 집계 인터페이스
	 * @param paramMap (물품입출고 등록 기본항목)
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
	 * @return void형
	 * @exception Exception
	 */
    @Override
    public void Call_Sm1000MonthChk(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("paramMap: "  + paramMap.toString());
        
        // 재고 입출고 기본 내역이 없는 경우 오류 처리
        if(paramMap.isEmpty()) {
            throw infoException("USERMSG:재고 입출고 기본 내역이 없습니다. 전산담당자에게 문의하세요.");
        }
        
        String resultMsg = null;  //결과메세지
        
        egovLogger.debug("debug1: ");
        // 재고 입출고 기본 내역에 대한 정합성 체크를 한다.
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.sm.selectSm1000Valid", paramMap);

        if  (!resultMsg.equals("OK")) {
            throw infoException("USERMSG:" + resultMsg + "전산담당자에게 문의하세요");
        }
        
        egovLogger.debug("debug2: ");
        // 기 등록된 입출고 기본 내역이 있는지 확인한다.
        LinkedHashMap resultMap = (LinkedHashMap)sqlSession.selectOne("sfmes.sqlmap.sm.selectSm1000_01", paramMap);
        
        if( resultMap != null ) {
        	
            egovLogger.debug("resultMap: " + resultMap.toString());
            
            // 축산물이력처리
            //2021.10.16 서광석
            //축산물이력처리 임시막음(대표님 요청사항) : MES에서 축산물이력관리 안함            
            //Call_Sm1000GdsHstNoProc(paramMap, "Y");
            
        	// 기 등록된 입출고 기본 내역은 모두 삭제처리한다.
            if(paramMap.get("SLP_NML_YN").equals("N")) // 전표 삭제시 로직
            {
                sqlSession.update("sfmes.sqlmap.sm.deleteSm1000_SLP_NML_YN", resultMap);
                sqlSession.update("sfmes.sqlmap.sm.updateSm1000_04", resultMap);
                sqlSession.update("sfmes.sqlmap.sm.updateSm1000_05", resultMap);
            }
            else
            {
                sqlSession.update("sfmes.sqlmap.sm.updateSm1000_01", resultMap);
                // 삭제된 내역을 기준으로 월계를 재 집계 처리한다.(상세)
                sqlSession.update("sfmes.sqlmap.sm.updateSm1000_02", resultMap);
                // 삭제된 내역을 기준으로 월계를 재 집계 처리한다.(기본)
                sqlSession.update("sfmes.sqlmap.sm.updateSm1000_03", resultMap);
            }

        }
    }
    
    /**
     * 축산물이력처리
     * @param paramMap (물품입출고 등록 기본항목)
     *         CORP_C : 회사코드 
     *         BZPL_C : 사업장코드
     *         STDV_DT : 입출고일자
     *         STDV_SQNO : 입출고일련번호
     * @return void형
     * @exception Exception
     */
    public void Call_Sm1000GdsHstNoProc(LinkedHashMap paramMap, String reverseYn) throws Exception {
        egovLogger.debug("Call_Sm1000GdsHstNoProc [Start]");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        
        // 재고 입출고 기본 내역이 없는 경우 오류 처리
        if(paramMap.isEmpty()) {
            throw infoException("USERMSG:재고 입출고 기본 내역이 없습니다. 전산담당자에게 문의하세요.");
        }
        
        String resultMsg = null;  //결과메세지
        
        egovLogger.debug("debug1: ");
        // 축산물이력처리를 위한 재고 입출고 기본 내역에 대한 정합성 체크를 한다.
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.sm.selectSm1000Valid04", paramMap);

        if  (!resultMsg.equals("OK")) {
            throw infoException("USERMSG:" + resultMsg + "전산담당자에게 문의하세요");
        }
        
        egovLogger.debug("debug2: ");
        
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> inf_dlr_map = new LinkedHashMap<>();
        
        // 기 등록된 축산물이력처리 대상조회한다.
        List<Map<String, Object>> resultList = sqlSession.selectList("sfmes.sqlmap.sm.selectSm1000_02", paramMap);
        
        for( Map<String, Object> sra_info_map : resultList ) {
            
            egovLogger.debug("resultMap: " + sra_info_map.toString());
            
            // 물품이력번호 확인 - 없으면 다음행으로
            if("".equals(sra_info_map.get("GDS_HST_NO"))) {                
                continue;
            }
            
            // ( 정상처리 && 출고 ) 일 경우 : 출고처리
            if( ( "N".equals(reverseYn) && "O".equals(sra_info_map.get("STDV_DSC")) ) ) {
                // 출고일경우. 
                if( (!sra_info_map.get("WHSE_C_O").equals("ZZZ")) && (sra_info_map.get("WHSE_C_I").equals("ZZZ")) && (!sra_info_map.get("GDS_HST_NO").equals("")) ) {
                    // 출고번호 정보 추가
                    //sra_info_map.put("CORP_C"   , paramMap.get("CORP_C"));
                    //sra_info_map.put("BZPL_C"   , paramMap.get("BZPL_C"));
                    sra_info_map.put("DLR_DT"   , sra_info_map.get("STDV_DT"));
                    sra_info_map.put("DLR_SQNO" , sra_info_map.get("STDV_SQNO"));
                    sra_info_map.put("DLR_WT"   , sra_info_map.get("STDV_QT"));
                    sra_info_map.put("DLR_YN"   , "Y");
                    sra_info_map.put("GUSRID"   , paramMap.get("GUSRID"));

                    inf_dlr_map.putAll(sra_info_map);
                    egovLogger.debug("조회Map (inf_dlr_map) : " + inf_dlr_map);
                    // 축산물이력정보 출고등록 i/f 호출
                    resultMap = IF_PD_SM_HST_MNGService.if_HST_MNG_Dlr_insert(inf_dlr_map);

                    if (!"00".equals(resultMap.get("resultCode"))) {
                        throw infoException("축산물이력(묶음)번호 ["+resultMap.get("GDS_HST_NO")+"] 출고등록중 오류가 발생했습니다.");
                    }
                }
            }
            // (반환처리 && 출고) 일 경우 : 입고(출고반환)처리 [입고등록화면에서는 입고시 축산물이력이 맞는지를 검사하나, 여기서는 축산물이력 유효여부를 체크하지 않는다.]
            else if( ( "Y".equals(reverseYn) && "O".equals(sra_info_map.get("STDV_DSC")) ) ) {
                // 입고일경우.
                if( (!sra_info_map.get("WHSE_C_O").equals("ZZZ")) && (sra_info_map.get("WHSE_C_I").equals("ZZZ")) && (!sra_info_map.get("GDS_HST_NO").equals("")) ) {
                    //sra_info_map.put("CORP_C"   , paramMap.get("CORP_C"));
                    //sra_info_map.put("BZPL_C"   , paramMap.get("BZPL_C"));
                    sra_info_map.put("DLR_DT"   , sra_info_map.get("STDV_DT"));
                    sra_info_map.put("DLR_SQNO" , sra_info_map.get("STDV_SQNO"));
                    sra_info_map.put("DLR_WT"   , Double.parseDouble(sra_info_map.get("STDV_QT").toString()) * -1 );
                    sra_info_map.put("DLR_YN"   , "N");
                    sra_info_map.put("GUSRID"   , paramMap.get("GUSRID"));

                    inf_dlr_map.putAll(sra_info_map);
                    egovLogger.debug("조회Map (inf_dlr_map) : " + inf_dlr_map);
                    // 축산물이력정보 출고등록 i/f 호출
                    resultMap = IF_PD_SM_HST_MNGService.if_HST_MNG_Dlr_insert(inf_dlr_map);

                    if (!"00".equals(resultMap.get("resultCode"))) {
                        throw infoException("축산물이력(묶음)번호 ["+resultMap.get("GDS_HST_NO")+"] 출고반환등록중 오류가 발생했습니다.");
                    }
                }
            }
        }
    }

    
	/**
	 * 재고 월별집계 재집계 인터페이스
	 * @param paramMap (물품입출고 등록 기본항목)
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
	 * @return void형
	 * @exception Exception
	 */
    @Override
    public void Call_Sm1000MonthTot(LinkedHashMap paramMap) throws Exception {
        
        //2022.01.12 서광석
        //물품 월집계 처리로직 변경        
        String resultMsg = null;
        String resultYn = "N";
        
        // 프로시저를 호출한다.
        sqlSession.selectOne("sfmes.sqlmap.sm.selectSm1000_GDS_RL_MON_TOT", paramMap);
        
        resultYn  = (String)paramMap.get("OUT_RESULT_YN");
        resultMsg = (String)paramMap.get("OUT_ERROR_MSG");
        
        if("N".equals(resultYn)) {
            throw infoException("물품 월집계 프로시저 호출 중 오류가 발생하였습니다: " + resultMsg);
        }        
        
        /** 기존소스코드 막음
        egovLogger.debug("paramMap: "  + paramMap.toString());
        
        // 재고 입출고 기본 내역이 없는 경우 오류 처리
        if(paramMap.isEmpty()) {
            throw infoException("USERMSG:재고 입출고 기본 내역이 없습니다. 전산담당자에게 문의하세요.");
        }
        
        String resultMsg = null;  //결과메세지
        
        // 재고 입출고 기본 내역에 대한 정합성 체크를 한다.
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.sm.selectSm1000Valid", paramMap);

        if  (!resultMsg.equals("OK")) {
            throw infoException("USERMSG:" + resultMsg + "전산담당자에게 문의하세요");
        }
        
        // 재고 입출고 내역 기준으로 월별집계를 재 처리한다.(상세)
    	sqlSession.insert("sfmes.sqlmap.sm.insertSm1000_01", paramMap);
    	
        // 재고 입출고 내역 기준으로 월별집계를 재 처리한다.(상세)
    	sqlSession.insert("sfmes.sqlmap.sm.insertSm1000_02", paramMap);
    	**/
    }
    
	/**
	 * 재고 월별집계 재집계 인터페이스
	 * @param paramMap (물품입출고 등록 기본항목)
	 *         CORP_C : 회사코드 
	 *         BZPL_C : 사업장코드
	 *         ACG_MN : 집계년월
	 *         GDS_CO : 물품코드
	 * @return void형
	 * @exception Exception
	 */
    @Override
    public void Call_Sm1000MonthTot_LE(LinkedHashMap paramMap) throws Exception {
        
        String resultMsg = null;
        String resultYn = "N";
        
        // 프로시저를 호출한다.
        sqlSession.selectOne("sfmes.sqlmap.sm.selectSm1000_GDS_RL_MON_TOT_LE", paramMap);
        
        resultYn  = (String)paramMap.get("OUT_RESULT_YN");
        resultMsg = (String)paramMap.get("OUT_ERROR_MSG");
        
        if("N".equals(resultYn)) {
            throw infoException("재고 월집계 프로시저 호출 중 오류가 발생하였습니다: " + resultMsg);
        }        
    }

    /**
	 * 재고 입출고내역의 상태값을 변경하는 인터페이스
	 * @param paramMap (물품입출고 등록 기본항목)
	 *         CORP_C : 회사코드 
	 *         BZPL_C : 사업장코드
	 *         STDV_DT : 입출고일자
	 *         STDV_SQNO : 입출고일련번호
	 *         STDV_DSC : 입출고구분코드(I.입고,O.출고)
	 *         STDV_STS_DSC : 입출고상태구분코드(1:검수입고/출고완료, 2:매입/매출, 3:매입반출/매출반입)
	 *         GUSRID : 사용자ID
	 * @return void형
	 * @exception Exception
	 */
    @Override
    public void Call_Sm1000StsUpd(LinkedHashMap paramMap) throws Exception {

        // 재고 입출고 기본 내역이 없는 경우 오류 처리
        if( paramMap == null ) {
            throw infoException("USERMSG:재고 입출고 기본 내역이 없습니다. 전산담당자에게 문의하세요.");
        }
        
        egovLogger.debug("paramMap: "  + paramMap.toString());
        
        String resultMsg = null;  //결과메세지
        
        // 재고 입출고 기본 내역에 대한 정합성 체크를 한다.
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.sm.selectSm1000Valid2", paramMap);

        if  (!resultMsg.equals("OK")) {
            throw infoException("USERMSG:" + resultMsg + "전산담당자에게 문의하세요");
        }
        
        sqlSession.update("sfmes.sqlmap.sm.updateSm1000_M_STS", paramMap);
    }
    
	/**
	 * 재고 입출고내역의 상태값을 변경하는 인터페이스
	 * @param paramMap (물품입출고 등록 기본항목)
	 *         CORP_C : 회사코드 
	 *         BZPL_C : 사업장코드
	 *         TR_SQNO : 거래일련번호(원거래)
	 *         STDV_STS_DSC : 입출고상태구분코드(1:검수입고/출고완료, 2:매입/매출, 3:매입반출/매출반입)
	 *         GUSRID : 사용자ID
	 * @return void형
	 * @exception Exception
	 */
    @Override
    public void Call_Sm1000StsUpdTrno(LinkedHashMap paramMap) throws Exception {

        // 재고 입출고 기본 내역이 없는 경우 오류 처리
        if( paramMap == null ) {
            throw infoException("USERMSG:재고 입출고 기본 내역이 없습니다. 전산담당자에게 문의하세요.");
        }
        
        egovLogger.debug("paramMap: "  + paramMap.toString());
        
        String resultMsg = null;  //결과메세지
        
        // 재고 입출고 기본 내역에 대한 정합성 체크를 한다.
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.sm.selectSm1000Valid3", paramMap);

        if  (!resultMsg.equals("OK")) {
            throw infoException("USERMSG:" + resultMsg + "전산담당자에게 문의하세요");
        }
        
        sqlSession.update("sfmes.sqlmap.sm.updateSm1000_M_STS_TR", paramMap);
    }        
}
