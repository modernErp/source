package com.sfmes.sm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Sm4010Service;
import com.sfmes.sm.service.Sm1000Service;

/**
 * @Class Name  : Sm4010ServiceImpl.java
 * @Description : Sm4010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.19   정성환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm4010Service")
public class Sm4010ServiceImpl extends CmnAbstractServiceImpl implements Sm4010Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Resource(name = "Sm1000Service")
    private Sm1000Service sm1000Service;
    
    //품간대체처리내역 등록 
    @Override
    public String saveSm4010(LinkedHashMap paramMap, List<Map<String, Object>> paramList1, List<Map<String, Object>> paramList2) throws Exception {
        
        //리턴할 값 담아놓을 변수설정
        String result = "";
        
        /**
         * 마감일자의 마감여부를 체크하는 메소드
         * @param CORP_C(회사코드), BZPL_C(사업장코드), AGC_DT(회계일자), TR_DSC(업무구분:BY.매입,PD.생산,SE.매출)
         * @return 없음
         */
        paramMap.put("TR_DSC", "SM"); 
        commonService.checkDdl(paramMap);
        
        LinkedHashMap paramMap2 = new LinkedHashMap();
        
        //채번을 위한 변수설정
        String s_CORP_C  = paramMap.get("CORP_C").toString();
        String s_BZPL_C  = paramMap.get("BZPL_C").toString();
        
        //채번 서비스 호출(거래일련번호)
        String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);            
        paramMap.put("TR_SQNO", tr_seqNo);      
        
        //오늘날짜구하기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        
        //채번 서비스 호출
        String seqNo = commonService.getGvno(s_CORP_C,"TB_SM_M_GDS_TFR",s_BZPL_C, strToday, 1);
        
        paramMap.put("TFR_PRC_SQNO", seqNo);
        paramMap.put("TFR_PRC_DT"  , strToday);
        paramMap.put("TFR_PRC_DSQNO", 1);
        
        // 물품대체처리 기본 저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_M_GDS_TFR", paramMap);
        
        // 물품대체처리상세  저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_D_GDS_TFR", paramMap);
        
        // 품원간대체처리 상세 (출고)
        for(Map<String, Object> map1 : paramList1)  //출고 
        {
            map1.put("CORP_C"        , paramMap.get("CORP_C"));             // 회사코드
            map1.put("BZPL_C"        , paramMap.get("BZPL_C"));             // 사업장코드
            map1.put("STDV_DT"       , paramMap.get("TFR_PRC_DT"));         // 대체처리일자 변수 변경 SM1000SERVICEIMPL 에서 사용
            map1.put("TFR_PRC_SQNO"  , paramMap.get("TFR_PRC_SQNO"));       // 대체처리번호
            map1.put("RLTR_DT"       , paramMap.get("RLTR_DT"));            // 실거래일자
            map1.put("DEL_YN"        , 'N');                                // 삭제여부
            map1.put("TR_UNT_C"      , map1.get("DLR_UNT_C"));              // 단위
            map1.put("STDV_STS_DSC"  , '1');                                // 입출고상태구분코드  1=검수입고/출고완료
            map1.put("STDV_DSC"      , 'O');                                // 입출고구분코드
            map1.put("STDV_QT"       , map1.get("DLR_QT"));                 // 출고수량
            map1.put("WHSE_C"        , map1.get("DLR_WHSE_C"));             // 출고창고코드
            map1.put("GDS_C"         , map1.get("DLR_GDS_C"));              // 출고물품코드
            map1.put("TXT_DSC"       , map1.get("DLR_TXT_DSC"));            // 출고과세구분
        }
        
        paramMap2.putAll(paramMap); // form 변수 복사
        
        for(Map<String, Object> map2 : paramList2) {     //입고  
            map2.put("CORP_C"               , paramMap.get("CORP_C"));             // 회사코드
            map2.put("BZPL_C"               , paramMap.get("BZPL_C"));             // 사업장코드
            map2.put("STDV_DT"              , paramMap.get("TFR_PRC_DT"));         // 대체처리일자 변수 변경 SM1000SERVICEIMPL 에서 사용
            map2.put("TFR_PRC_SQNO"         , paramMap.get("TFR_PRC_SQNO"));       // 대체처리번호
            map2.put("RLTR_DT"              , paramMap.get("RLTR_DT"));            // 실거래일자
            map2.put("DEL_YN"               , 'N');                                // 삭제여부
            map2.put("TR_UNT_C"             , map2.get("STR_UNT_C"));               // 단위
            map2.put("STDV_DSC"             , 'I');                                 // 입출고구분코드 입고
            map2.put("STDV_QT"              , map2.get("STR_QT"));                  // 입고수량
            map2.put("WHSE_C"               , map2.get("STR_WHSE_C"));              // 입고창고코드
            map2.put("GDS_C"                , map2.get("STR_GDS_C"));               // 입고물품코드
            map2.put("TXT_DSC"              , map2.get("STR_TXT_DSC"));             // 입고과세구분
            map2.put("FLAG_STDV_DSC_IO_YN"  , "Y");                                 // 입고데이터 구분자
        }
        
        paramMap.put("STDV_DSC", "O");          //입출고구분자=출고
        paramMap.put("STDV_STS_DSC", "1");      //입출고상태구분코드
        
        paramMap2.put("STDV_DSC", "I");         //입출고구분자=입고
        paramMap2.put("STDV_STS_DSC", "1");     //입출고상태구분코드
        
        sm1000Service.Call_saveSm1000(paramMap, paramList1, paramMap2, paramList2  ); // 출고 출고 입고 입고
        
        result = paramMap.toString();
        return result;
    }

    //품원간대체처리 전표삭제
    @Override
    public String deleteSm4010(LinkedHashMap paramMap, List<Map<String, Object>> paramList1, List<Map<String, Object>> paramList2) throws Exception {
        
        String result = "";
        
        /**
         * 마감일자의 마감여부를 체크하는 메소드
         * @param CORP_C(회사코드), BZPL_C(사업장코드), AGC_DT(회계일자), TR_DSC(업무구분:BY.매입,PD.생산,SE.매출)
         * @return 없음
         */
        paramMap.put("TR_DSC", "SM"); 
        commonService.checkDdl(paramMap);
        
        sqlSession.update("sfmes.sqlmap.sm.updateSm4010_SLP_NML_YN", paramMap);
         
        LinkedHashMap paramMap2 = new LinkedHashMap();
        
        // 품원간대체처리 상세 (출고)
        for(Map<String, Object> map1 : paramList1)  //출고 
        {
            map1.put("CORP_C"        , paramMap.get("CORP_C"));             // 회사코드
            map1.put("BZPL_C"        , paramMap.get("BZPL_C"));             // 사업장코드
            map1.put("STDV_DT"       , paramMap.get("TFR_PRC_DT"));         // 대체처리일자 변수 변경 SM1000SERVICEIMPL 에서 사용
            map1.put("TFR_PRC_SQNO"  , paramMap.get("TFR_PRC_SQNO"));       // 대체처리번호
            map1.put("RLTR_DT"       , paramMap.get("RLTR_DT"));            // 실거래일자
            map1.put("DEL_YN"        , 'N');                                // 삭제여부
            map1.put("TR_UNT_C"      , map1.get("DLR_UNT_C"));              // 단위
            map1.put("STDV_STS_DSC"  , '1');                                // 입출고상태구분코드  1=검수입고/출고완료
            map1.put("STDV_DSC"      , 'O');                                // 입출고구분코드
            map1.put("STDV_QT"       , map1.get("DLR_QT"));                 // 출고수량
            map1.put("WHSE_C"        , map1.get("DLR_WHSE_C"));             // 출고창고코드
            map1.put("GDS_C"         , map1.get("DLR_GDS_C"));              // 출고물품코드
            map1.put("TXT_DSC"       , map1.get("DLR_TXT_DSC"));            // 출고과세구분
        }
        
        paramMap2.putAll(paramMap); // form 변수 복사
        
        for(Map<String, Object> map2 : paramList2) {     //입고  
            map2.put("CORP_C"               , paramMap.get("CORP_C"));             // 회사코드
            map2.put("BZPL_C"               , paramMap.get("BZPL_C"));             // 사업장코드
            map2.put("STDV_DT"              , paramMap.get("TFR_PRC_DT"));         // 대체처리일자 변수 변경 SM1000SERVICEIMPL 에서 사용
            map2.put("TFR_PRC_SQNO"         , paramMap.get("TFR_PRC_SQNO"));       // 대체처리번호
            map2.put("RLTR_DT"              , paramMap.get("RLTR_DT"));            // 실거래일자
            map2.put("DEL_YN"               , 'N');                                // 삭제여부
            map2.put("TR_UNT_C"             , map2.get("STR_UNT_C"));       // 단위
            map2.put("STDV_DSC"             , 'I');                         // 입출고구분코드 입고
            map2.put("STDV_QT"              , map2.get("STR_QT"));          // 입고수량
            map2.put("WHSE_C"               , map2.get("STR_WHSE_C"));      // 입고창고코드
            map2.put("GDS_C"                , map2.get("STR_GDS_C"));       // 입고물품코드
            map2.put("TXT_DSC"              , map2.get("STR_TXT_DSC"));             // 입고과세구분
            map2.put("FLAG_STDV_DSC_IO_YN"  , "Y");                         // 입고데이터 구분자
        }
        
        paramMap.put("STDV_DSC", "O");          //입출고구분자=출고
        paramMap.put("STDV_STS_DSC", "1");      //입출고상태구분코드
        
        paramMap2.put("STDV_DSC", "I");         //입출고구분자=입고
        paramMap2.put("STDV_STS_DSC", "1");     //입출고상태구분코드
        
        sm1000Service.Call_saveSm1000(paramMap, paramList1, paramMap2, paramList2  ); // 출고 출고 입고 입고
        
        result = paramMap.toString();
        return result;
    }

    //품원간대체처리 수정 
    @Override
    public String updateSm4010(LinkedHashMap paramMap, List<Map<String, Object>> paramList1, List<Map<String, Object>> paramList2) throws Exception {
        
        String result = "";
        
        /**
         * 마감일자의 마감여부를 체크하는 메소드
         * @param CORP_C(회사코드), BZPL_C(사업장코드), AGC_DT(회계일자), TR_DSC(업무구분:BY.매입,PD.생산,SE.매출)
         * @return 없음
         */
        paramMap.put("TR_DSC", "SM"); 
        commonService.checkDdl(paramMap);
        
        LinkedHashMap paramMap2 = new LinkedHashMap();
        
        // 품원간대체처리 기본
        sqlSession.update("sfmes.sqlmap.tb.update_TB_SM_M_GDS_TFR", paramMap);
        
        // 품원간대체처리 상세
        sqlSession.update("sfmes.sqlmap.tb.update_TB_SM_D_GDS_TFR", paramMap);
        
     // 품원간대체처리 상세 (출고)
        for(Map<String, Object> map1 : paramList1)  //출고 
        {
            map1.put("CORP_C"        , paramMap.get("CORP_C"));             // 회사코드
            map1.put("BZPL_C"        , paramMap.get("BZPL_C"));             // 사업장코드
            map1.put("STDV_DT"       , paramMap.get("TFR_PRC_DT"));         // 대체처리일자 변수 변경 SM1000SERVICEIMPL 에서 사용
            map1.put("TFR_PRC_SQNO"  , paramMap.get("TFR_PRC_SQNO"));       // 대체처리번호
            map1.put("RLTR_DT"       , paramMap.get("RLTR_DT"));            // 실거래일자
            map1.put("DEL_YN"        , 'N');                                // 삭제여부
            map1.put("TR_UNT_C"      , map1.get("DLR_UNT_C"));              // 단위
            map1.put("STDV_STS_DSC"  , '1');                                // 입출고상태구분코드  1=검수입고/출고완료
            map1.put("STDV_DSC"      , 'O');                                // 입출고구분코드
            map1.put("STDV_QT"       , map1.get("DLR_QT"));                 // 출고수량
            map1.put("WHSE_C"        , map1.get("DLR_WHSE_C"));             // 출고창고코드
            map1.put("GDS_C"         , map1.get("DLR_GDS_C"));              // 출고물품코드
            map1.put("TXT_DSC"       , map1.get("DLR_TXT_DSC"));            // 출고과세구분
        }
        
        paramMap2.putAll(paramMap); // form 변수 복사
        
        for(Map<String, Object> map2 : paramList2) {     //입고  
            map2.put("CORP_C"               , paramMap.get("CORP_C"));             // 회사코드
            map2.put("BZPL_C"               , paramMap.get("BZPL_C"));             // 사업장코드
            map2.put("STDV_DT"              , paramMap.get("TFR_PRC_DT"));         // 대체처리일자 변수 변경 SM1000SERVICEIMPL 에서 사용
            map2.put("TFR_PRC_SQNO"         , paramMap.get("TFR_PRC_SQNO"));       // 대체처리번호
            map2.put("RLTR_DT"              , paramMap.get("RLTR_DT"));            // 실거래일자
            map2.put("DEL_YN"               , 'N');                                // 삭제여부
            map2.put("TR_UNT_C"             , map2.get("STR_UNT_C"));       // 단위
            map2.put("STDV_DSC"             , 'I');                         // 입출고구분코드 입고
            map2.put("STDV_QT"              , map2.get("STR_QT"));          // 입고수량
            map2.put("WHSE_C"               , map2.get("STR_WHSE_C"));      // 입고창고코드
            map2.put("GDS_C"                , map2.get("STR_GDS_C"));       // 입고물품코드
            map2.put("TXT_DSC"              , map2.get("STR_TXT_DSC"));             // 입고과세구분
            map2.put("FLAG_STDV_DSC_IO_YN"  , "Y");                         // 입고데이터 구분자
        }
        
        paramMap.put("STDV_DSC", "O");          //입출고구분자=출고
        paramMap.put("STDV_STS_DSC", "1");      //입출고상태구분코드
        
        paramMap2.put("STDV_DSC", "I");         //입출고구분자=입고
        paramMap2.put("STDV_STS_DSC", "1");     //입출고상태구분코드
        
        sm1000Service.Call_saveSm1000(paramMap, paramList1, paramMap2, paramList2  ); // 출고 출고 입고 입고
        
        result = paramMap.toString();
        return result;
    }
    
    // 품간대체처리내역 - 창고찾기팝업
    @Override
    public List<?> searchSm4010P03(LinkedHashMap paramMap) {
        return sqlSession.selectList("sfmes.sqlmap.sm.searchSm4010P03",paramMap);
    }
    
    // 품간대체처리내역 - 기본 팝업
    @Override
    public List<?> searchSm4010P01_M(LinkedHashMap paramMap) {
        return sqlSession.selectList("sfmes.sqlmap.sm.searchSm4010P01_M",paramMap);
    }
    
    // 품간대체처리내역 - 상세 팝업
    @Override
    public List<?> searchSm4010P01_D(LinkedHashMap paramMap) {
        return sqlSession.selectList("sfmes.sqlmap.sm.searchSm4010P01_D",paramMap);
    }
}
