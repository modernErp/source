package com.sfmes.sm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
import com.sfmes.sm.service.Sm5040Service;


/**
 * @Class Name  : Sm5040ServiceImpl.java
 * @Description : Sm5040Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.29   정성환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm5040Service")
public class Sm5040ServiceImpl extends CmnAbstractServiceImpl implements Sm5040Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
        
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Resource(name = "Sm1000Service")
    private Sm1000Service sm1000Service;
    
    // 재고실사 진행 중 재고실사 삭제를 진행한 경우 문제가 되지 않게하기 위해.
    protected void checkSSVYDelYN(LinkedHashMap paramMap) throws Exception {
        String result = "";
        
        result = sqlSession.selectOne("sfmes.sqlmap.sm.searchSm5010_02", paramMap);
        if( result == null ) {
            throw infoException("USERMSG:재고실사 정보가 정상적이지 않습니다. 전산담당자에게 문의하세요.");
        }
        else if( "Y".equals(result) ) {
            throw infoException("USERMSG:이미 재고실사 진행이 삭제된 대상입니다. 조회 후 재확인해 보세요.");
        }
    }
    
    //재고실사확정등록 리스트 조회 
    @Override
    public List<?> searchSm5040_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.searchSm5040_01",paramMap);
    }

    @Override
    public String updateSm5040_01(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        String result = "";
        
        /**
         * 마감일자의 마감여부를 체크하는 메소드
         * @param CORP_C(회사코드), BZPL_C(사업장코드), AGC_DT(회계일자), TR_DSC(업무구분:BY.매입,PD.생산,SE.매출)
         * @return 없음
         */
        paramMap.put("TR_DSC", "SM"); 
        commonService.checkDdl(paramMap);
        
        // 재고실사 진행 중 재고실사삭제를 진행한 경우 문제가 되지 않게하기 위해.
        checkSSVYDelYN(paramMap);
        
        List<Map<String, Object>> paramList1 = new ArrayList<Map<String, Object>>(paramList.size());
        List<Map<String, Object>> paramList2 = new ArrayList<Map<String, Object>>(paramList.size());
        List<Map<String, Object>> tempParamListI = new ArrayList<Map<String, Object>>(paramList.size());
        List<Map<String, Object>> tempParamListO = new ArrayList<Map<String, Object>>(paramList.size());
        
        LinkedHashMap paramMap2 = new LinkedHashMap();
        
        //채번을 위한 변수설정
        String s_CORP_C         = paramMap.get("CORP_C").toString();
        String s_BZPL_C         = paramMap.get("BZPL_C").toString();
        String s_SSVY_DFN_DT    = paramMap.get("SSVY_DFN_DT").toString();       // 실사확정일자 (금일)
        
        //채번 서비스 호출
        String seqNo = commonService.getGvno(s_CORP_C,"TB_SM_M_SSVY_DFN",s_BZPL_C, s_SSVY_DFN_DT, 1);
        paramMap.put("SSVY_DFN_SQNO", seqNo);
        
        //채번 서비스 호출(거래일련번호)
        String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
        
        paramMap.put("TR_SQNO"                , tr_seqNo);       // 거래일련번호
        paramMap.put("STDV_REF_DSC"           , "1");            // 입출고참조구분코드 [1:무참조]
        
        int checkFlagO = 0 ;
        int checkFlagI = 0 ;
        
        paramMap2.putAll(paramMap); // 입고 출고용 paramMap 복사
        
        for(Map<String,Object> map1 : paramList )
        {
            if(Double.parseDouble(map1.get("DIF_QT").toString()) > 0)   // 출고
            {
                map1.put("STDV_DSC"             , "O");     // 양수이면 출고
                map1.put("TR_SQNO"              , tr_seqNo);
                map1.put("TR_UNT_C"             , map1.get("UNT_C"));
                map1.put("STDV_QT"              , map1.get("DIF_QT"));
                map1.put("DEL_YN"               , "N");
                map1.put("FLAG_STDV_DSC_IO_YN"  , "Y");   
                tempParamListO.add(map1);
                checkFlagO++;
            }
            else if (Double.parseDouble(map1.get("DIF_QT").toString()) < 0) // 입고
            {
                map1.put("STDV_DSC"             , "I");     // 음수이면 입고
                map1.put("TR_SQNO"              , tr_seqNo);
                map1.put("TR_UNT_C"             , map1.get("UNT_C"));
                map1.put("STDV_QT"              , Double.parseDouble(map1.get("DIF_QT").toString()) * -1);
                map1.put("DEL_YN"               , "N");
                map1.put("FLAG_STDV_DSC_IO_YN"  , "Y");   
                tempParamListI.add(map1);
                checkFlagI++;
            }
        }
        
       
        if(checkFlagI > checkFlagO)
        {
            paramMap.put("STDV_DSC" , "I");
            paramList1.addAll(tempParamListI);
            
            if( checkFlagO > 0 ) {
                paramMap2.put("STDV_DSC" , "O");
                paramList2.addAll(tempParamListO);
            }
            else {
                paramMap2.clear();
            }
        }
        else
        {
            paramMap.put("STDV_DSC" , "O");
            paramList1.addAll(tempParamListO);
            
            if( checkFlagI > 0 ) {
                paramMap2.put("STDV_DSC" , "I");
                paramList2.addAll(tempParamListI);
            }
            else {
                paramMap2.clear();
            }
        }

        // 
        sm1000Service.Call_saveSm1000(paramMap, paramList1, paramMap2, paramList2  ); // 입고, 출고 중 더 많은 쪽이 Map/List1로 세팅하며, 적은 쪽이 Map/List2로 세팅함.
        
        //재고실사확정등록 기본
        sqlSession.insert("sfmes.sqlmap.sm.insertSm5040_TB_SM_M_SSVY_DFN"     , paramMap);
        
        //재고실사확정등록 상세
        sqlSession.insert("sfmes.sqlmap.sm.insertSm5040_TB_SM_D_SSVY_DFN"     , paramMap);
        
        //재고실사확정등록 창고상세
        sqlSession.insert("sfmes.sqlmap.sm.insertSm5040_TB_SM_D_SSVY_DFN_WHSE", paramMap);
        
        //재고실사준비등록 기본 최종 확정
        sqlSession.update("sfmes.sqlmap.sm.updateSm5040_01" , paramMap);
        
        result = paramMap.toString();
        return result;
    }
}

