package com.sfmes.by.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.by.service.By3020Service;
import com.sfmes.ca.service.Ca0100Service;
import com.sfmes.ca.service.Ca0200Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Sm1000Service;
import com.sfmes.sm.service.Sm1010Service;

/**
 * @Class Name : By3020ServiceImpl.java
 * @Description : 매입반품등록/수정 및 조회
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.06  곽환용   최초생성
 * @ 2020.09.23  김지혜   매입반품등록화면으로 수정
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("By3020Service")
public class By3020ServiceImpl extends CmnAbstractServiceImpl implements By3020Service {
    
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

    //매입반품기본내역 조회
    @Override
    public List<?> selectBy3020_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매입반품기본내역조회[BY3020] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy3010_01", paramMap);
    }
    
    //매입반품상세내역 조회
    @Override
    public List<?> selectBy3020_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매입반품상세내역조회[BY3020] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy3010_02", paramMap);
    }
    
    //매입정산내역 조회
    @Override
    public List<?> selectBy3020_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매입정산내역조회[BY3020] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy3010_05", paramMap);
    } 
    
    //매입반품기본,상세내역 등록
    @Override
    public String insertBy3020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception { 
        egovLogger.debug("************ 매입반품등록[BY3020] *********");
        
        /*
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        //마감여부 체크
        commonService.checkDdl(paramMap);
        */
        
        // 마감여부 체크 추가 (2022.02.08)
        LinkedHashMap<String, Object> chkDdlYnParam = new LinkedHashMap<String, Object>();
        chkDdlYnParam.put("CORP_C" , paramMap.get("CORP_C")); //회사코드
        chkDdlYnParam.put("BZPL_C" , paramMap.get("BZPL_C")); //사업장코드
        chkDdlYnParam.put("BSN_DSC", "BY");                   //업무구분 BY:매입
        chkDdlYnParam.put("BAS_DT" , paramMap.get("ACG_DT"));  //기준일자 BAS_DT:매입반품(회계)일자
        
        String ddlYn = commonService.checkDdlYn(chkDdlYnParam);
        if("Y".equals(ddlYn)) {
            throw infoException("해당 매입반품일자로 업무가 마감되었습니다. 날짜를 확인해주세요.");
        }
        
        String result = "";
        String resultMsg = null;  //결과메세지
        
        //매입반품기본내역에 대한 정합성 체크를 한다.
        egovLogger.debug("매입반품기본내역 Validation Check"); 
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.by.selectBy3010Valid", paramMap);

        if(!resultMsg.equals("OK")) {
            throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
        }
        
        //채번을 위한 변수설정
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_BY_DT  = paramMap.get("BY_DT").toString();
        
        //채번 서비스 호출(매입일련번호)
        String seqNo = commonService.getGvno(s_CORP_C,"TB_BY_M_BUY",s_BZPL_C, s_BY_DT, 1);
        egovLogger.debug("생성된 매입반품일련번호 채번: " + seqNo);
        paramMap.put("BY_SQNO", seqNo);
        
        //채번 서비스 호출(거래일련번호)
        String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
        egovLogger.debug("생성된 거래일련번호 채번: " + tr_seqNo);
        paramMap.put("TR_SQNO", tr_seqNo);   

        //[4]입고반품(환출) 시, 화면의 원매입일자/일련번호를 출고일자/일련번호로 변경
        //20210105 여다혜 추가 
        if("4".equals(String.valueOf(paramMap.get("BY_REF_DSC")))) {
            paramMap.put("STR_DT"   , paramMap.get("OGN_BY_DT"));   //출고일자
            paramMap.put("STR_SQNO" , paramMap.get("OGN_BY_SQNO")); //출고일련번호
            paramMap.put("OGN_BY_DT"  , ""); //원매입일자 값은 ""로 닦아줌
            paramMap.put("OGN_BY_SQNO", ""); //원매입일련번호 값은 ""로 닦아줌
            egovLogger.debug("@@@@@@@@@@@@@@@@@ 입고반품(환출) 값변경 작업 @@@@@@@@@@@@@@@@@@@@@");
        }
        
        egovLogger.debug("@@STR_DT :::" + paramMap.get("STR_DT"));
        egovLogger.debug("@@STR_SQNO :::" + paramMap.get("STR_SQNO"));
        egovLogger.debug("@@OGN_BY_DT :::" + paramMap.get("OGN_BY_DT"));
        egovLogger.debug("@@OGN_BY_SQNO :::" + paramMap.get("OGN_BY_SQNO"));
        
        //매입기본내역 저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_BY_M_BUY", paramMap); 
        
        //원매입참조 매입반품등록 시 
        if(! ("0".equals(paramMap.get("OGN_BY_SQNO").toString()) || "".equals(paramMap.get("OGN_BY_SQNO").toString()))) 
        {
            egovLogger.debug("원매입기본내역수정 TB_BY_M_BUY");  
            sqlSession.update("sfmes.sqlmap.by.update_BY3020", paramMap);  
        }
        
        //매입상세내역 저장        
        for(Map<String, Object> map : paramList) 
        {
            map.put("BZPL_C" , paramMap.get("BZPL_C"));
            map.put("BY_DT"  , paramMap.get("BY_DT"));
            map.put("BY_SQNO", paramMap.get("BY_SQNO"));
            
            // 매입반품상세내역에 대한 정합성 체크를 한다.
            egovLogger.debug("매입반품상세내역 Validation Check"); 
            resultMsg = sqlSession.selectOne("sfmes.sqlmap.by.selectBy3010ValidDet", map);

            if(!resultMsg.equals("OK")) 
            {
                throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
            } 
            
            egovLogger.debug("매입반품상세내역등록 TB_BY_D_BUY");
            egovLogger.debug("map: " + map.toString());
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_BY_D_BUY", map);
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
        paramMap.put("STDV_DSC"    , "O");  //입출고구분코드
        paramMap.put("STDV_STS_DSC", "3");  //입출고상태구분코드 (3 : 매입반품)
        paramMap.put("STDV_REF_DSC", "1");
        
        for(int i=0; i<paramList.size(); i++) 
        {
            paramList.get(i).put("STDV_QT"    , paramList.get(i).get("BY_QT"));
            paramList.get(i).put("STDV_BOX_QT", paramList.get(i).get("BY_BOX_QT"));
            paramList.get(i).put("STDV_UPR"   , paramList.get(i).get("BY_UPR"));
            paramList.get(i).put("STDV_AM"    , paramList.get(i).get("BY_AM"));
            paramList.get(i).put("STDV_WT"    , paramList.get(i).get("BY_WT"));
        }
        
        egovLogger.debug("************ 입출고내역등록 TB_SM_M_GDS_RL_STDV *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        sm1000Service.Call_saveSm1000(paramMap, paramList, null, null);
        
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
        if(! "0".equals(paramMap.get("CRE_AM").toString()))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            paramMap.put("CRE_BY_AM", paramMap.get("CRE_AM").toString());
            resultList.add(paramMap);
            
            egovLogger.debug("************ 외상매입금등록 *********");
            egovLogger.debug("resultList: "  + resultList);
            
            ca0200Service.Call_saveCbam_Ocr(resultList);
        }
        
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
         *         RMK_CNTN   : 비고내용 
         *         TR_BSN_DSC : 거래업무구분코드 (BD20:선급금지급등록 [미등록시:BD20으로 세팅함.])
         *         TR_SQNO    : 거래일련번호 
         *         GUSRID     : 사용자ID
         * @return void형
         * @exception Exception
         */
        if(! "0".equals(paramMap.get("PRY_AM").toString()))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            paramMap.put("PPY_AM"    , paramMap.get("PRY_AM").toString());
            paramMap.put("TR_BSN_DSC", "BD20");
            resultList.add(paramMap);
            
            egovLogger.debug("************ 선급금지급등록 *********");
            egovLogger.debug("resultList: "  + resultList);
            
            //선급금 지급 등록
            ca0100Service.Call_savePryam_Ocr(resultList);
        } 
        
        result = paramMap.toString();
        return result;     
    }

    //매입반품내역삭제
    @Override
    public void deleteBy3020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 매입반품삭제[BY3020] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());  
        
        /*
        //마감여부 체크
        commonService.checkDdl(paramMap);
        */
        
        // 마감여부 체크 추가 (2022.02.08)
        LinkedHashMap<String, Object> chkDdlYnParam = new LinkedHashMap<String, Object>();
        chkDdlYnParam.put("CORP_C" , paramMap.get("CORP_C")); //회사코드
        chkDdlYnParam.put("BZPL_C" , paramMap.get("BZPL_C")); //사업장코드
        chkDdlYnParam.put("BSN_DSC", "BY");                   //업무구분 BY:매입
        chkDdlYnParam.put("BAS_DT" , paramMap.get("ACG_DT"));  //기준일자 BAS_DT:매입반품(회계)일자
        
        String ddlYn = commonService.checkDdlYn(chkDdlYnParam);
        if("Y".equals(ddlYn)) {
            throw infoException("해당 매입반품일자로 업무가 마감되었습니다. 날짜를 확인해주세요.");
        }
        
        //매입기본내역 수정(전표정상여부: "N")
        egovLogger.debug("매입반품기본내역수정 TB_BY_M_BUY");        
        sqlSession.update("sfmes.sqlmap.tb.update_TB_BY_M_BUY", paramMap);
        
        if(! ("0".equals(paramMap.get("OGN_BY_SQNO").toString()) || "".equals(paramMap.get("OGN_BY_SQNO").toString()))) 
        {
            egovLogger.debug("원매입참조 매입반품기본내역수정 TB_BY_M_BUY");  
            sqlSession.update("sfmes.sqlmap.by.update_BY3020_01", paramMap);  
        }
        
        //입고테이블에 필요한 PARAMETER
        paramMap.put("STDV_DSC"    , "O");
        paramMap.put("STDV_STS_DSC", "3");

        for(int i=0; i<paramList.size(); i++) 
        {
            paramList.get(i).put("STDV_QT"    , paramList.get(i).get("BY_QT")    );
            paramList.get(i).put("STDV_BOX_QT", paramList.get(i).get("BY_BOX_QT"));
            paramList.get(i).put("STDV_UPR"   , paramList.get(i).get("BY_UPR")   );
            paramList.get(i).put("STDV_AM"    , paramList.get(i).get("BY_AM")    );
            paramList.get(i).put("STDV_WT"    , paramList.get(i).get("BY_WT")    );
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
        if(! "0".equals(paramMap.get("CRE_AM").toString()))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            paramMap.put("CRE_BY_AM", paramMap.get("CRE_AM").toString());
            resultList.add(paramMap);
            
            egovLogger.debug("************ 외상매입금등록 *********");
            egovLogger.debug("resultList: "  + resultList);
            
            ca0200Service.Call_saveCbam_Ocr(resultList);
        }
        
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
         *         RMK_CNTN   : 비고내용 
         *         TR_BSN_DSC : 거래업무구분코드 (BD20:선급금지급등록 [미등록시:BD20으로 세팅함.])
         *         TR_SQNO    : 거래일련번호 
         *         GUSRID     : 사용자ID
         * @return void형
         * @exception Exception
         */
        if(! "0".equals(paramMap.get("PRY_AM").toString()))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            paramMap.put("PPY_AM"    , paramMap.get("PRY_AM").toString());
            paramMap.put("TR_BSN_DSC", "BD20");
            resultList.add(paramMap);
            
            egovLogger.debug("************ 선급금지급등록 *********");
            egovLogger.debug("resultList: "  + resultList);
            
            //선급금 지급 등록
            ca0100Service.Call_savePryam_Ocr(resultList);
        } 
    }

    //입고반품내역 조회
    /*
     * PDA로 입고반품할 박스 스캔된 상세내역을 조회한다. (SM16:입고반품)
     * */
    @Override
    public List<?> selectBy3020_04(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy3020_03", paramMap);
    }        
}
