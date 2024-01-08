package com.sfmes.sm.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Sm1000Service;
import com.sfmes.sm.service.Sm6010Service;

/**
 * @Class Name  : Sm6010ServiceImpl.java
 * @Description : Sm6010ServiceImpl Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.11.05   정성환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm6010Service")
public class Sm6010ServiceImpl extends CmnAbstractServiceImpl implements Sm6010Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Resource(name = "Sm1000Service")
    private Sm1000Service sm1000Service;

    //재고_원장계수정정기본 (신규등록을 위한 일련번호 채번 호출) 조회
    @Override
    public List<?> searchSm6010_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm6010_01", paramMap);
    }

    //품원장계수정정 등록
    @Override
    public void insertSm6010_01(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        
        //채번을 위한 변수설정
        String s_CORP_C         = paramMap.get("CORP_C").toString();
        String s_BZPL_C         = paramMap.get("BZPL_C").toString();
        String s_CRC_DT         = paramMap.get("CRC_DT").toString();       // 품원장정정일자 (금일)
        
        //채번 서비스 호출
        String seqNo = commonService.getGvno(s_CORP_C,"TB_SM_M_LED_FGS_CRC",s_BZPL_C, s_CRC_DT, 1);
        paramMap.put("CRC_SQNO", seqNo);    // 정정일련번호
        
        //채번 서비스 호출(거래일련번호)
        String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1); 
        
        paramMap.put("SLP_NML_YN"    , "Y"        );    // 전표정상여부
        paramMap.put("TR_BSN_DSC"    , "SM32"     );    // 거래업무구분코드
        paramMap.put("TR_SQNO"       , tr_seqNo   );    // 거래일련번호
        
        //재고_원장계수정정기본 등록
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_M_LED_FGS_CRC", paramMap);
        
        int dseqNo = 1;
        for( Map map1 : paramList) {
            
            map1.put("CORP_C"        , paramMap.get("CORP_C")       );   // 회사코드
            map1.put("BZPL_C"        , paramMap.get("BZPL_C")       );   // 사업장코드
            map1.put("CRC_DT"        , paramMap.get("CRC_DT")       );   // 정정일자
            map1.put("CRC_SQNO"      , paramMap.get("CRC_SQNO")     );   // 정정일련번호
            map1.put("CRC_DSQNO"     , dseqNo++                     );   // 정정상세일련번호
            
            //재고_원장계수정정상세 등록
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_D_LED_FGS_CRC", map1);
        }

        
        LinkedHashMap<String, Object> paramMap1 = new LinkedHashMap();
        List<Map<String, Object>> paramList1 = new ArrayList<Map<String, Object>>(paramList.size());
        
        paramMap1.putAll(paramMap);
        paramList1.addAll(paramList);
        
        //채번을 위한 변수설정
        String s_STDV_DT = paramMap.get("CRC_DT").toString();       // 입출고일자 (금일)
        
        //채번 서비스 호출
        String seqNo1 = commonService.getGvno(s_CORP_C,"TB_SM_M_GDS_RL_STDV",s_BZPL_C, s_STDV_DT, 1);
        
        /*
         * CORP_C       : 회사코드 
         * BZPL_C       : 사업장코드
         * STDV_DSC     : 입출고구분코드(I.입고,O.출고)
         * SLP_NML_YN   : 전표정상여부(Y.정상,N.삭제)
         * RLTR_DT      : 입출고일자(검수일자)
         * TRPL_C       : 거래처코드
         * STDV_STS_DSC : 입출고상태구분코드(1:검수입고/출고완료, 2:매입/매출, 3:매입반출/매출반입)
         * STDV_REF_DSC : 입출고참조구분코드(1:무참조, 2:발주참조, 3:출고지시참조, 4:출고의뢰참조)
         * RMK_CNTN     : 비고내용
         * TR_BSN_DSC   : 거래업무구분코드
         * TR_SQNO      : 거래일련번호
         * GUSRID       : 사용자ID
         */
        paramMap1.put("STDV_DT"       , paramMap.get("CRC_DT") );     // 입출고일자
        paramMap1.put("STDV_SQNO"     , seqNo1                 );     // 입출고일련번호
        paramMap1.put("STDV_STS_DSC"  , "2"                    );     // 입출고상태구분코드  [2:매입/매출]
        paramMap1.put("STDV_REF_DSC"  , "1"                    );     // 입출고참조구분코드 [1:무참조]
        paramMap1.put("STDV_REF_DT"   , ""                     );     // 입출고참조일자
        paramMap1.put("STDV_REF_SQNO" , 0                      );     // 입출고참조일련번호
        paramMap1.put("DEL_YN"        , "N"                    );     // 삭제여부

        for( Map map1 : paramList1) {
            
            /*
             * GDS_C         : 물품코드 
             * STDV_QT       : 입출고수량
             * STDV_BOX_QT   : 입출고박스수량
             * TR_UNT_C      : 거래단위코드
             * STDV_UPR      : 입출고단가
             * SPY_AM        : 공급금액
             * VAT           : 부가세
             * STDV_AM       : 입출고금액
             * WHSE_C        : 창고코드
             * DSTR_TERDT    : 유통기한일자
             * HST_AMN_DSC   : 이력관리구분코드
             * GDS_HST_NO    : 물품이력번호
             * BUDL_NO       : 묶음번호
             * QT_WT_DSC     : 수(중)량형구분코드
             * WHT_QT        : 단량
             * WHT_UNT_C     : 단량단위코드
             * STDV_WT       : 입출고중량
             * TXT_DSC       : 과세구분코드
             * RMK_CNTN      : 비고내용
             * DEL_YN        : 삭제여부
             * FLAG_STDV_DSC_IO_YN : 출고,입고 값이 둘다 있는경우
            */
            map1.put("STDV_BOX_QT"    , 1                              );   // 입출고박스수량
            map1.put("HST_AMN_DSC"    , "0"                            );   // 이력관리구분코드
            map1.put("GDS_HST_NO"     , ""                             );   // 물품이력번호
            map1.put("BUDL_NO"        , ""                             );   // 묶음번호
            map1.put("DEL_YN"         , "N"                            );   // 삭제여부
            map1.put("DSTR_TERDT"     , ""                             );   // 유통기한일자
            
        }
        
        // 물품 재고 입출고 등록을 한다. [품원장계수정정에 따른 재고원장 반영.]
        sm1000Service.Call_saveSm1000(paramMap1, paramList1, null, null);
        
        return;
    }

}
