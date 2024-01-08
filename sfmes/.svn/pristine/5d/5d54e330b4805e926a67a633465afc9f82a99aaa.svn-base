package com.sfmes.by.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.engine.mapping.result.ResultMap;
import com.sfmes.by.service.By3010Service;
import com.sfmes.ca.service.Ca0100Service;
import com.sfmes.ca.service.Ca0200Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Sm1000Service;
import com.sfmes.sm.service.Sm1010Service;

/**
 * @Class Name : By3010ServiceImpl.java
 * @Description : 매입등록/수정 및 조회
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.06  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("By3010Service")
public class By3010ServiceImpl extends CmnAbstractServiceImpl implements By3010Service {
    
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
    private Ca0200Service ca0200Service;
    
    //정산 서비스 선언
    @Autowired
    private Ca0100Service ca0100Service;    

    //매입기본내역 조회
    @Override
    public List<?> selectBy3010_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매입기본내역조회[BY3010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy3010_01", paramMap);
    }

    //매입상세내역 조회
    @Override
    public List<?> selectBy3010_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매입상세내역조회[BY3010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy3010_02", paramMap);
    }
    
    //매입내역찾기팝업 조회
    @Override
    public List<?> selectBy3010_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매입내역찾기팝업조회[BY3010P01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy3010_03", paramMap);
    }
    
    //참조입고상세내역 조회
    @Override
    public List<?> selectBy3010_04(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 참조입고상세내역조회[SM1010P01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy3010_04", paramMap);
    }    
    
    //매입정산내역 조회
    @Override
    public List<?> selectBy3010_05(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매입정산내역조회[BY3010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.by.selectBy3010_05", paramMap);
    }    
    
    //매입기본,상세내역 등록
    @Override
    public String insertBy3010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception { 
        egovLogger.debug("************ 매입등록[BY3010] *********");
        
        /*
        //전표 일자는 등록 및 수정 하는 당일의 날짜로 세팅
        paramMap.put("BY_DT", paramMap.get("writeDate"));
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        //마감여부 체크
        commonService.checkDdl(paramMap);
        */
        
        // 마감여부 체크 추가 (2022.02.08) 구민희
        LinkedHashMap<String, Object> chkDdlYnParam = new LinkedHashMap<String, Object>();
        chkDdlYnParam.put("CORP_C" , paramMap.get("CORP_C")); //회사코드
        chkDdlYnParam.put("BZPL_C" , paramMap.get("BZPL_C")); //사업장코드
        chkDdlYnParam.put("BSN_DSC", "BY");                   //업무구분 BY:매입
        chkDdlYnParam.put("BAS_DT" , paramMap.get("ACG_DT"));  //기준일자 BAS_DT:매입(회계)일자
        
        String ddlYn = commonService.checkDdlYn(chkDdlYnParam);
        if("Y".equals(ddlYn)) {
            throw infoException("해당 매입일자로 업무가 마감되었습니다. 날짜를 확인해주세요.");
        }
        
        
        String result = "";
        String resultMsg = null;  //결과메세지
        
        
        //2021.10.06 서광석
        //아래 벨리데이션 임시막음
        //"selectSe3010Valid_02" 소스가 mappers 에 존재하지 않아 에러남
        //소스관리가 제대로 안된것 같음..
        //동시 등록시 같은 입고내역을 참조하는지 validate
        /*
        if("2".contentEquals((String)paramMap.get("BY_REF_DSC"))) {
            int dlrStsDscCheck = sqlSession.selectOne("sfmes.sqlmap.se.selectSe3010Valid_02", paramMap);
            if(dlrStsDscCheck == 2) {
                throw infoException("매입 등록된 입고내역입니다\n전산담당자에게 문의하세요");
            }
        }
        */
        
        //매입기본내역에 대한 정합성 체크를 한다.
        egovLogger.debug("매입기본내역 Validation Check"); 
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
        egovLogger.debug("생성된 매입일련번호 채번: " + seqNo);
        paramMap.put("BY_SQNO", seqNo);
        
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
        paramMap.put("STDV_DSC"    , "I");  //입출고구분코드
        paramMap.put("STDV_STS_DSC", "2");  //입출고상태구분코드
        
        for(int i=0; i<paramList.size(); i++) 
        {
            paramList.get(i).put("TR_STDV_QT" , paramList.get(i).get("TR_BY_QT"));
            paramList.get(i).put("STDV_QT"    , paramList.get(i).get("BY_QT"));
            paramList.get(i).put("STDV_BOX_QT", paramList.get(i).get("BY_BOX_QT"));
            paramList.get(i).put("STDV_UPR"   , paramList.get(i).get("BY_UPR"));
            paramList.get(i).put("STDV_AM"    , paramList.get(i).get("BY_AM"));
            paramList.get(i).put("STDV_WT"    , paramList.get(i).get("BY_WT"));
        }
        
        //입고참조매입등록
        if(!"1".equals((String)paramMap.get("BY_REF_DSC"))) {                        
            //참조한입고내역 수정
            egovLogger.debug("************ 입출고내역수정 TB_SM_M_GDS_RL_STDV *********");
            
            paramMap.put("STDV_DT"  , paramMap.get("STR_DT")  );
            paramMap.put("STDV_SQNO", paramMap.get("STR_SQNO"));
            sm1000Service.Call_Sm1000StsUpd(paramMap);
        } else { //무참조매입등록 시 입고내역 생성 
            
            //채번 서비스 호출(거래일련번호)
            String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
            egovLogger.debug("생성된 거래일련번호 채번: " + tr_seqNo);
            paramMap.put("TR_SQNO", tr_seqNo); 
            paramMap.put("STDV_STS_DSC", '2');
            paramMap.put("STDV_REF_DSC", '1');
            
            egovLogger.debug("************ 입출고내역등록 TB_SM_M_GDS_RL_STDV *********");
            egovLogger.debug("paramMap: "  + paramMap.toString());
            egovLogger.debug("paramList: " + paramList.toString());

            /*
             * 재고I/F 처리안함 - 대표님/유이사님 요청사항 (2022-01-12 여다혜 수정)
             * 
            sm1000Service.Call_saveSm1000(paramMap, paramList, null, null); 
            
            paramMap.put("STR_DT"  , paramMap.get("STDV_DT"));
            paramMap.put("STR_SQNO", paramMap.get("STDV_SQNO"));
            
            egovLogger.debug("STR_DT: " + paramMap.get("STR_DT"));
            egovLogger.debug("STR_SQNO: " + paramMap.get("STR_SQNO"));
            */
        } 
        
        //매입기본내역 저장
        egovLogger.debug("매입기본내역등록 TB_BY_M_BUY");        
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_BY_M_BUY", paramMap); 
        
        //매입상세내역 저장        
        for(Map<String, Object> map : paramList) 
        {
            map.put("BZPL_C" , paramMap.get("BZPL_C"));
            map.put("BY_DT"  , paramMap.get("BY_DT"));
            map.put("BY_SQNO", paramMap.get("BY_SQNO"));
            map.put("OEM_YN" , paramMap.get("OEM_YN"));    // 20220104 추가 rchkorea
            
            // 매입상세내역에 대한 정합성 체크를 한다.
            egovLogger.debug("매입상세내역 Validation Check"); 
            resultMsg = sqlSession.selectOne("sfmes.sqlmap.by.selectBy3010ValidDet", map);
            if(!resultMsg.equals("OK")) 
            {
                throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
            } 
            
            egovLogger.debug("매입상세내역등록 TB_BY_D_BUY");
            egovLogger.debug("map: " + map.toString());
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_BY_D_BUY", map);
        }    
       
        
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
            egovLogger.debug("resultList: " + resultList);
            
            ca0200Service.Call_saveCbam_Ocr(resultList);
        }
        
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
         *         RMK_CNTN   : 비고내용 
         *         TR_BSN_DSC : 거래업무구분코드 (BD21:선급금사용등록 [미등록시:BD21으로 세팅함.])
         *         TR_SQNO    : 거래일련번호
         *         GUSRID     : 사용자ID
         * @return void형
         * @exception Exception
         */
        if(! "0".equals(paramMap.get("PRY_AM").toString()))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            paramMap.put("PPY_AM"    , paramMap.get("PRY_AM").toString());
            paramMap.put("TR_BSN_DSC", "BD21");
            resultList.add(paramMap);
            
            egovLogger.debug("************ 선급금사용 *********");
            egovLogger.debug("resultList: " + resultList);
            
            ca0100Service.Call_savePryam_Use(resultList);
        }
        
        result = paramMap.toString();
        return result;     
    }

    //매입내역삭제
    @Override
    public void deleteBy3010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 매입삭제[BY3010] *********");
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
        chkDdlYnParam.put("BSN_DSC", "BY");                   //업무구분 BY:매입
        chkDdlYnParam.put("BAS_DT" , paramMap.get("ACG_DT"));  //기준일자 BAS_DT:매입(회계)일자
        
        String ddlYn = commonService.checkDdlYn(chkDdlYnParam);
        if("Y".equals(ddlYn)) {
            throw infoException("해당 매입일자로 업무가 마감되었습니다. 날짜를 확인해주세요.");
        }
        
        int chk_cnt = 0;
        
        LinkedHashMap result = sqlSession.selectOne("sfmes.sqlmap.tb.select_TB_BY_M_BUY", paramMap);
        if(! "0".equals(result.get("OGN_BY_SQNO").toString()))
        {
            throw infoException("매입반품등록된 전표는 삭제할 수 없습니다.");
        }
        
        //매입기본내역 삭제 시 중복체크를 한다.
        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.by.selectBy3010Cnt", paramMap);
        if(chk_cnt > 0)
        {
            throw infoException("이미 삭제된 매입내역입니다.");
        }
        
        //매입기본내역 수정(전표정상여부: "N")
        egovLogger.debug("매입기본내역수정 TB_BY_M_BUY");        
        paramMap.put("STDV_DSC"    , "I");
        paramMap.put("STDV_STS_DSC", "1");
        paramMap.put("STDV_DT"     , paramMap.get("STR_DT"));
        paramMap.put("STDV_SQNO"   , paramMap.get("STR_SQNO"));
        sqlSession.update("sfmes.sqlmap.by.update_BY3020_03", paramMap);
        
        //무참조매입내역 삭제
        if("1".equals((String)paramMap.get("BY_REF_DSC"))) { 
            paramMap.put("TR_BSN_DSC", "SM10");
            
            for(int i=0; i<paramList.size(); i++) 
            {
                paramList.get(i).put("STDV_QT"    , paramList.get(i).get("BY_QT")    );
                paramList.get(i).put("STDV_BOX_QT", paramList.get(i).get("BY_BOX_QT"));
                paramList.get(i).put("STDV_UPR"   , paramList.get(i).get("BY_UPR")   );
                paramList.get(i).put("STDV_AM"    , paramList.get(i).get("BY_AM")    );
                paramList.get(i).put("STDV_WT"    , paramList.get(i).get("BY_WT")    );
            } 
            
            egovLogger.debug("************ 매입삭제(무참조) *********");
            egovLogger.debug("paramMap: "+ paramMap.toString());
            egovLogger.debug("paramList: " + paramList.toString());
            
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
             *         FLAG_STDV_DSC_IO_YN : 출고,입고 값이 둘다 있는경우 
             * @return void형
             * @exception Exception
             */
            //입출고생성내역 삭제
            /*
             * 재고I/F 처리안함 - 대표님/유이사님 요청사항 (2022-01-14 여다혜 수정) 
             */
//            sm1000Service.Call_saveSm1000(paramMap, paramList, null, null);
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
        
        /*
         무참조가 아닐 때만 재고입출고내역 상태값 변경한다.
         무참조는 변경할 입출고내역을 생성하지 않아(22-01-14 입출고 생성 I/F삭제)
         참조값이 없으므로 예외처리됨 20220114 여다혜 수정
        */
        if(!"1".equals((String)paramMap.get("BY_REF_DSC"))) {
            sm1000Service.Call_Sm1000StsUpd(paramMap);
        }
        
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
         *         TR_BSN_DSC : 거래업무구분코드 (BY10:매입등록 [미등록시:BY10으로 세팅함.])
         *         TR_SQNO    : 거래일련번호
         *         GUSRID     : 사용자ID
         * @return void형
         * @exception Exception
         */
        if(! "0".equals(paramMap.get("CRE_AM").toString()) || "".equals(paramMap.get("CRE_AM").toString()))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            paramMap.put("CRE_BY_AM", paramMap.get("CRE_AM").toString());
            resultList.add(paramMap);
            
            egovLogger.debug("************ 외상매입금등록 *********");
            egovLogger.debug("resultList: " + resultList);
            
            ca0200Service.Call_saveCbam_Ocr(resultList);
        }
        
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
         *         RMK_CNTN   : 비고내용 
         *         TR_BSN_DSC : 거래업무구분코드 (BD21:선급금사용등록 [미등록시:BD21으로 세팅함.])
         *         TR_SQNO    : 거래일련번호
         *         GUSRID     : 사용자ID
         * @return void형
         * @exception Exception
         */
        if(! "0".equals(paramMap.get("PRY_AM").toString()))
        {
            List<Map<String, Object>> resultList = new ArrayList();
            
            paramMap.put("PPY_AM"    , paramMap.get("PRY_AM").toString());
            paramMap.put("TR_BSN_DSC", "BD21");
            resultList.add(paramMap);
            
            egovLogger.debug("************ 선급금사용 *********");
            egovLogger.debug("resultList: "  + resultList);
            
            ca0100Service.Call_savePryam_Use(resultList);
        }        
    }
}
