package com.sfmes.se.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.enterprise.inject.New;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.compiler.STParser.mapExpr_return;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se6010Service;
import com.sfmes.se.service.Se6020Service;
import com.sfmes.sm.service.Sm1000Service;

/**
 * @Class Name  : Se6020ServiceImpl.java
 * @Description : Se6020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.06   김지혜      최초생성
 * @ 2020.08.10   곽환용      변경
 * @ 2020.09.13   곽환용      변경(Sm1050 -> Se6020)
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se6020Service")
public class Se6020ServiceImpl extends CmnAbstractServiceImpl implements Se6020Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    //제품출고지시서비스
    @Autowired
    private Se6010Service se6010Service;
    
    //재고입출고서비스
    @Autowired
    private Sm1000Service sm1000Service;
    
    //공통서비스
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    //제품출고기본내역
    @Override
    public List<?> selectSe6020_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 제품출고기본내역조회[SE6020] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SE_M_DLR", paramMap);
    }
    
    //제품출고상세내역 2022.04.21 나명우추가 물품별, 이력번호별 조회추가
    @Override
    public List<?> selectSe6020_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 제품출고상세내역조회[SE6020] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        
        String DLR_DSC_GUBUN = paramMap.get("DLR_DSC_GUBUN").toString();
        List<?> resultList = null;
        if(DLR_DSC_GUBUN.equals("0")) {
            resultList = sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SE_D_DLR", paramMap);
        } else if(DLR_DSC_GUBUN.equals("1")) {
            resultList = sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SE_D_DLR_02", paramMap);
        } else if(DLR_DSC_GUBUN.equals("2")) {
            resultList = sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SE_D_DLR_03", paramMap);
        } else {
        	resultList = sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SE_D_DLR", paramMap);
        }
        
        
        return resultList;
    }

    //제품출고내역찾기팝업
    @Override
    public List<?> selectSe6020_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 제품출고내역찾기팝업[SE6020P01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe6020_01", paramMap);
    }

    //출고지시기본내역조회
    @Override
    public List<?> selectSe6020_M_DLR(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시기본내역조회(참조)[SM1040] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe6020_M_DLR", paramMap);
    }   
    
    //출고지시상세내역조회
    @Override
    public List<?> selectSe6020_D_DLR(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시상세내역조회(참조)[selectSe6020_D_DLR] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe6020_D_DLR", paramMap);
    }    
    
    // PDA 출고예정 물품정보조회
    public List<?> select_PDA_DLR_GDS(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA GDS 정보조회(참조)[PDA_DLR_GDS] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.se.select_PDA_DLR_GDS",paramMap);
    }    

    // PDA 물품으로 출고예정내역조회
    public List<?> select_Pda_Sm1050_DLRGDS_M(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 출고예정내역조회[select_Sm1050_DLR_GDS] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.se.select_Sm1050_DLR_GDS",paramMap);
    }    

    // PDA 출고예정일조회
    public List<?> select_Pda_Sm1050_DLRDT(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 출고예정일조회[select_Pda_Sm1050_DLRDT] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.se.select_PDA_Sm1050_DLRDT",paramMap);
    }    

    // PDA 출고예정일의 거래처조회
    public List<?> select_Pda_TRPL(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 출고예정일의 거래처조회[PDA_Sm1050_DLR_TRPL] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.se.select_PDA_Sm1050_DLR_TRPL",paramMap);
    }    

    // 제품출고의뢰내역조회
    @Override
    public List<?> select_Pda_Sm1050_DLR_M(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 제품출고의뢰내역조회(참조)[Pda_Sm1050_DLR_M] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.se.select_PDA_Sm1050_DLR_TRPL",paramMap);
    }    

    //거래명세서출력
    @Override
    public List<?> selectSe6020_R(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 거래명세서출력[SE6020R01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        List<?> resultList = null;
        
        
        //UPR_YN : 단가출력여부 
        if(paramMap.get("UPR_YN").equals("Y")) { //단가출력O
            
            //GDS_GRP_OPT : 물품그룹화옵션
            if(paramMap.get("GDS_GRP_OPT").equals("1")) {         // 1:전체 
                resultList = sqlSession.selectList("sfmes.sqlmap.se.selectSe6020_R_Y", paramMap);
            } else if (paramMap.get("GDS_GRP_OPT").equals("2")) { // 2:물품이력번호별 그룹 
                resultList = sqlSession.selectList("sfmes.sqlmap.se.selectSe6020_R3_Y", paramMap);
            } else if (paramMap.get("GDS_GRP_OPT").equals("3")) { // 3:물품코드별 그룹
                resultList = sqlSession.selectList("sfmes.sqlmap.se.selectSe6020_R2_Y", paramMap);
            } else {
                throw infoException("잘못된 물품그룹 옵션 입니다.");
            }
        } else if (paramMap.get("UPR_YN").equals("N")) { //단가출력X
            
            //GDS_GRP_OPT : 물품그룹화옵션
            if(paramMap.get("GDS_GRP_OPT").equals("1")) {         // 1:전체
                resultList = sqlSession.selectList("sfmes.sqlmap.se.selectSe6020_R_N", paramMap);
            } else if (paramMap.get("GDS_GRP_OPT").equals("2")) { // 2:물품이력번호별 그룹
                resultList = sqlSession.selectList("sfmes.sqlmap.se.selectSe6020_R3_N", paramMap);
            } else if (paramMap.get("GDS_GRP_OPT").equals("3")) { // 3:물품코드별 그룹
                resultList = sqlSession.selectList("sfmes.sqlmap.se.selectSe6020_R2_N", paramMap);
            } else {
                throw infoException("잘못된 물품그룹 옵션 입니다.");
            }
            
        }
        /*
        if (paramMap.get("CHK_GDS_GRP").equals("Y")) {
            //물품별 집계
            if(paramMap.get("CHK_DLR_UPR").equals("Y")) {
                resultList = sqlSession.selectList("sfmes.sqlmap.se.selectSe6020_R2_Y", paramMap);
            }else {
                resultList = sqlSession.selectList("sfmes.sqlmap.se.selectSe6020_R2_N", paramMap);
            }
        } else {
            //물품변 건건이
            if(paramMap.get("CHK_DLR_UPR").equals("Y")) {
                resultList = sqlSession.selectList("sfmes.sqlmap.se.selectSe6020_R_Y", paramMap);
            }else {
                resultList = sqlSession.selectList("sfmes.sqlmap.se.selectSe6020_R_N", paramMap);
            }
        }
        */
        
        
        return resultList;
    }    

    //제품출고내역등록 
    @Override
    public String saveSe6020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 제품출고등록[SE6020] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        String result = "";
        String resultMsg = null;  //결과메세지
        int chk_cnt = 0;
        
        //제품출고기본내역 저장 시 중복체크를 한다.
        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe6020Cnt", paramMap);
        if(chk_cnt > 0)
        {
            throw infoException("이미 등록된 제품출고내역입니다.");
        }
        
        //제품출고기본내역에 대한 정합성 체크를 한다.
        egovLogger.debug("제품출고기본내역 Validation Check"); 
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.se.selectSe6020Valid", paramMap);
        if(! resultMsg.equals("OK")) 
        {
            throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
        }
        
        //채번을 위한 변수설정
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_DLR_DT = paramMap.get("DLR_DT").toString();
        
        //채번 서비스 호출(제품출고일련번호)
        String seqNo = commonService.getGvno(s_CORP_C,"TB_SE_M_DLR",s_BZPL_C, s_DLR_DT, 1);
        egovLogger.debug("생성된 일련번호 채번: " + seqNo);
        paramMap.put("DLR_SQNO", seqNo);

        //제품출고등록시 거래일련번호 채번
        //타업무에서는 거래일련번호 채번해서 넘겨주기 때문에 따로 채번할 필요 없음 
        if("".equals(paramMap.get("TR_SQNO").toString()) || "0".equals(paramMap.get("TR_SQNO").toString()))
        {
            //채번 서비스 호출(거래일련번호)
            String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
            egovLogger.debug("생성된 거래일련번호 채번: " + tr_seqNo);
            paramMap.put("TR_SQNO", tr_seqNo);              
        }    
        
        //제품출고등록 시에만 배송지관련 등록되도록
        //SM20 : 출고등록
        //SE10 : 매출등록
        //SE11 : 매출반입등록
        if("SM20".equals(paramMap.get("TR_BSN_DSC").toString()))
        {
            //배송고객정보 등록
            if("2".equals(paramMap.get("DVY_OBJ_DSC").toString()))
            {
                //오늘날짜구하기
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Calendar c1 = Calendar.getInstance();
                String strToday = sdf.format(c1.getTime());      
                
                //채번 서비스 호출(수주일련번호)
                String seqNo2 = commonService.getGvno(s_CORP_C, "TB_SE_M_DVY_CUS", s_BZPL_C, strToday, 1);
                
                egovLogger.debug("생성된 배송고객등록일련번호 채번: " + seqNo2);
                paramMap.put("DVY_CUS_REG_DT"  , strToday);
                paramMap.put("DVY_CUS_REG_SQNO", seqNo2);
                
                egovLogger.debug("배송고객등록 TB_SE_M_DVY_CUS");
                egovLogger.debug("paramMap: " + paramMap.toString());
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_DVY_CUS", paramMap);
            } 
        }
        
        //제품출고기본내역저장
        egovLogger.debug("************ 제품출고기본등록 TB_SE_M_DLR *********"); 
        egovLogger.debug("paramMap: " + paramMap.toString());
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_DLR", paramMap);
        
        //제품출고상세내역저장
        for(Map<String, Object> map : paramList) 
        {
            map.put("BZPL_C"  , paramMap.get("BZPL_C")  );
            map.put("DLR_DT"  , paramMap.get("DLR_DT")  );
            map.put("DLR_SQNO", paramMap.get("DLR_SQNO"));         

            //매출등록에서 출고상세내역생성할 때 mapping처리
            if("SE10".equals(paramMap.get("TR_BSN_DSC").toString()))
            {                
                map.put("DLR_QT"    , map.get("SL_QT")    );
                map.put("DLR_BOX_QT", map.get("SL_BOX_QT"));
                map.put("DLR_UPR"   , map.get("SL_UPR")   );
                map.put("DLR_AM"    , map.get("SL_AM")    );
                map.put("DLR_WT"    , map.get("SL_WT")    );                
            }            
            
            //제품출고상세내역에 대한 정합성 체크를 한다.
            egovLogger.debug("제품출고상세내역 Validation Check"); 
            resultMsg = sqlSession.selectOne("sfmes.sqlmap.se.selectSe6020ValidDet", map);

            if(! resultMsg.equals("OK")) 
            {
                throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
            } 
            
            egovLogger.debug("************ 제품출고상세등록 TB_SE_D_DLR *********");
            egovLogger.debug("map: " + map);
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_D_DLR", map); 
        }
        
        //출고지시참조 제품출고등록 시 출고지시내역의 출고상태구분 변경(1:출고지시 -> 2:출고완료)
        if(("1".equals(paramMap.get("DLR_REF_DSC").toString())) && ("SM20".equals(paramMap.get("TR_BSN_DSC").toString()))) 
        {
            egovLogger.debug("************ 출고지시내역수정 [SE6010] *********");
            paramMap.put("DLR_DNTT_STS_DSC", "2");
            se6010Service.Call_updateSe6010(paramMap);
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
        paramMap.put("STDV_DSC", "O");      //입출고구분코드
        
        //입출고참조구분코드
        if("SM20".equals(paramMap.get("TR_BSN_DSC").toString()))
        {
            paramMap.put("STDV_STS_DSC", "1");  //입출고상태구분코드
        }
        else
        {
            paramMap.put("STDV_STS_DSC", "2");  //입출고상태구분코드
        }
        
        for(int i=0; i<paramList.size(); i++) 
        {
            paramList.get(i).put("STDV_QT"    , paramList.get(i).get("DLR_QT"));
            paramList.get(i).put("STDV_BOX_QT", paramList.get(i).get("DLR_BOX_QT"));
            paramList.get(i).put("STDV_UPR"   , paramList.get(i).get("DLR_UPR"));
            paramList.get(i).put("STDV_AM"    , paramList.get(i).get("DLR_AM"));
            paramList.get(i).put("STDV_WT"    , paramList.get(i).get("DLR_WT"));
        }
        
        // 매출 매입 재고 함수 타지 않도록 수정 20220510 ksckorea
        if("SE10".equals(paramMap.get("TR_BSN_DSC").toString())) {
        	
        	
        }else {
        	//물품 재고 입출고 등록하는 함수 호출
        	sm1000Service.Call_saveSm1000(paramMap, paramList, null, null);
        }
        
        result = paramMap.toString();
        return result;           
    }
    
    //제품출고내역수정
    @Override
    public String updateSe6020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 제품출고수정[SE6020] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        //리턴할 값 담아놓을 변수설정
        String result = "";
        
        //배송고객기본 수정
        if("2".equals(paramMap.get("DVY_OBJ_DSC").toString()))
        {
            if("0".equals(paramMap.get("DVY_CUS_REG_SQNO").toString()))
            {
                //채번을 위한 변수설정
                String s_CORP_C = paramMap.get("CORP_C").toString();
                String s_BZPL_C = paramMap.get("BZPL_C").toString();
                
                //오늘날짜구하기
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Calendar c1 = Calendar.getInstance();
                String strToday = sdf.format(c1.getTime());      
                
                //채번 서비스 호출(배송고객등록일련번호)
                String seqNo2 = commonService.getGvno(s_CORP_C, "TB_SE_M_DVY_CUS", s_BZPL_C, strToday, 1);
                
                egovLogger.debug("생성된 배송고객등록일련번호 채번: " + seqNo2);
                paramMap.put("DVY_CUS_REG_DT"  , strToday);
                paramMap.put("DVY_CUS_REG_SQNO", seqNo2);
                
                egovLogger.debug("배송고객등록 TB_SE_M_DVY_CUS");
                egovLogger.debug("paramMap: " + paramMap.toString());
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_DVY_CUS", paramMap);
            }
            else
            {
                egovLogger.debug("배송고객수정 TB_SE_M_DVY_CUS");
                paramMap.put("DEL_YN", "N");
                egovLogger.debug("paramMap: " + paramMap.toString());
                sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_DVY_CUS", paramMap);                
            }
        }          
        
        //출고기본내역 수정
        egovLogger.debug("************ 제품출고기본수정[SE6020] *********");
        sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_DLR", paramMap);

        //출고상세내역 수정
        for(Map<String, Object> map : paramList) 
        {
            egovLogger.debug("************ 제품출고상세수정[SE6020] *********");
            if(! ("".equals(map.get("DLR_DSQNO").toString()) || "0".equals(map.get("DLR_DSQNO").toString())))
            {
                egovLogger.debug("수정 map: " + map.toString());
                sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_D_DLR", map); 
            }
            else
            {
                map.put("BZPL_C"  , paramMap.get("BZPL_C"));
                map.put("DLR_DT"  , paramMap.get("DLR_DT"));
                map.put("DLR_SQNO", paramMap.get("DLR_SQNO"));                 

                egovLogger.debug("입력 map: " + map.toString());
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_D_DLR", map);                 
            }
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
        paramMap.put("STDV_DSC", "O");      //입출고구분코드
        
        //입출고참조구분코드
        if("SM20".equals(paramMap.get("TR_BSN_DSC").toString()))
        {
            paramMap.put("STDV_STS_DSC", "1");  //입출고상태구분코드
        }
        else
        {
            paramMap.put("STDV_STS_DSC", "2");  //입출고상태구분코드
        }
        
        for(int i=0; i<paramList.size(); i++) 
        {
            paramList.get(i).put("STDV_QT"    , paramList.get(i).get("DLR_QT"));
            paramList.get(i).put("STDV_BOX_QT", paramList.get(i).get("DLR_BOX_QT"));
            paramList.get(i).put("STDV_UPR"   , paramList.get(i).get("DLR_UPR"));
            paramList.get(i).put("STDV_AM"    , paramList.get(i).get("DLR_AM"));
            paramList.get(i).put("STDV_WT"    , paramList.get(i).get("DLR_WT"));
        }
        //물품 재고 입출고 등록하는 함수 호출
        // 매출 매입 재고 함수 타지 않도록 수정 20220510 ksckorea
        if("SE10".equals(paramMap.get("TR_BSN_DSC").toString())) {
        	
        	
        }else {
        	//물품 재고 입출고 등록하는 함수 호출
        	sm1000Service.Call_saveSm1000(paramMap, paramList, null, null);
        }
        result = paramMap.toString();
        return result;
    }
    
    //출고상태구분 수정
    @Override
    public void Call_updateSe6020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 출고상태구분수정[SE6020] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());

        egovLogger.debug("출고기본내역수정 TB_SE_M_DLR");
        egovLogger.debug("paramMap: " + paramMap.toString());
        sqlSession.update("sfmes.sqlmap.se.updateSe6020_M_STS", paramMap);
        
        //전표정상여부가 "Y"인 경우에만 출고상세내역수정
        if("Y".equals(paramMap.get("SLP_NML_YN").toString()) && "SM20".equals(paramMap.get("TR_BSN_DSC").toString()))
        {
            for(Map<String, Object> map : paramList) 
            {
                map.put("BZPL_C"  , paramMap.get("BZPL_C")  );
                map.put("DLR_DT"  , paramMap.get("DLR_DT")  );
                map.put("DLR_SQNO", paramMap.get("DLR_SQNO"));
                
                egovLogger.debug("제품출고상세내역수정 TB_SE_D_DLR");
                egovLogger.debug("map: " + map);
                sqlSession.update("sfmes.sqlmap.se.updateSe6020_D_STS", map);   
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
        //출고참조매출등록 시 출고내역 수정 후 물품재고 입출고 함수 호출
        if(! ("".equals(paramMap.get("DLR_SQNO").toString()) || "0".equals(paramMap.get("DLR_SQNO").toString())))
        {
            egovLogger.debug("재고입출고내역수정 TB_SM_M_GDS_RL_STDV");

            sm1000Service.Call_Sm1000StsUpdTrno(paramMap);
        }
    }    
    
    //제품출고내역삭제
    @Override
    public void deleteSe6020(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 제품출고내역삭제[SE6020] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        
        LinkedHashMap resultMap = new LinkedHashMap();
        
        //무참조매출 시 거래일련번호로 조회 후 처리
        if("SE10".equals(paramMap.get("TR_BSN_DSC").toString()))
        {
            resultMap = sqlSession.selectOne("sfmes.sqlmap.se.selectSe6020_TR", paramMap);
            resultMap.put("STDV_DT", paramMap.get("STDV_DT"));
            resultMap.put("STDV_SQNO", paramMap.get("STDV_SQNO"));
        }
        else
        {
            resultMap = sqlSession.selectOne("sfmes.sqlmap.tb.select_TB_SE_M_DLR", paramMap);
            //출고상태구분코드가 "[2]매출등록" 이면 삭제 안되게
            if("2".equals(resultMap.get("DLR_STS_DSC").toString()))
            {
                throw infoException("이미 매출등록된 전표는 삭제할 수 없습니다."); 
            }
        }
                    
        resultMap.put("SLP_NML_YN", paramMap.get("SLP_NML_YN"));
        resultMap.put("GUSRID"    , paramMap.get("GUSRID"));
        egovLogger.debug("************ 제품출고기본내역수정 TB_SE_M_DLR *********");
        egovLogger.debug("resultMap: "  + resultMap.toString());
        sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_DLR", resultMap);
        
        //출고지시내역 참조 시 재참조 가능하게 출고지시상태구분 수정(2:출고완료 -> 1:출고지시)
        if(("1".equals(resultMap.get("DLR_REF_DSC").toString())) && ("SM20".equals(resultMap.get("TR_BSN_DSC").toString()))) 
        {
            egovLogger.debug("************ 제품출고지시내역수정 TB_SE_M_DLR_DNTT *********");
            resultMap.put("DLR_DT"          , null);
            resultMap.put("DLR_SQNO"        , 0);
            resultMap.put("DLR_DNTT_STS_DSC", "1");
            se6010Service.Call_updateSe6010(resultMap);
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
        resultMap.put("STDV_DSC"    , "O");  
        resultMap.put("STDV_STS_DSC", "2");          
        
        List<Map<String, Object>> resultList = sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SE_D_DLR", paramMap);
        for(int i=0; i<resultList.size(); i++) 
        {
            resultList.get(i).put("STDV_QT"    , resultList.get(i).get("DLR_QT"));
            resultList.get(i).put("STDV_BOX_QT", resultList.get(i).get("DLR_BOX_QT"));
            resultList.get(i).put("STDV_UPR"   , resultList.get(i).get("DLR_UPR"));
            resultList.get(i).put("STDV_AM"    , resultList.get(i).get("DLR_AM"));
            resultList.get(i).put("STDV_WT"    , resultList.get(i).get("DLR_WT"));
        } 
        
        //물품 재고 입출고 등록하는 함수 호출
        egovLogger.debug("************ 재고입출고등록 TB_SM_M_GDS_RL_STDV *********");
        egovLogger.debug("resultMap: "  + resultMap.toString());
        egovLogger.debug("resultList: "  + resultList.toString());
        
        sm1000Service.Call_saveSm1000(resultMap, resultList, null, null);
    }
}
