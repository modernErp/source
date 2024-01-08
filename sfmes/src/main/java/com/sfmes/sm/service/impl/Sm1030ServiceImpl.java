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
import com.sfmes.se.service.Se2010Service;
import com.sfmes.sm.service.Sm1030Service;
import com.sfmes.sm.service.Sm1000Service;
import com.sfmes.sm.service.Sm1010Service;
import com.sfmes.pd.service.Pd2050Service;

/**
 * @Class Name  : Sm1030ServiceImpl.java
 * @Description : Sm1030Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.09.14   정성환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm1030Service")
public class Sm1030ServiceImpl extends CmnAbstractServiceImpl implements Sm1030Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
     
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Resource(name = "Sm1000Service")
    private Sm1000Service sm1000Service;
    
    @Resource(name = "Sm1010Service")
    private Sm1010Service sm1010Service;

    @Autowired
    private Pd2050Service pd2010Service;
    
    //출고기본내역
    @Override
    public List<?> searchSm1030_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 입고기본내역조회[SM1030] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SM_M_GDS_RL_STDV",paramMap);
    }
    
    //출고상세내역
    @Override
    public List<?> searchSm1030_02(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1030_02",paramMap);
    } 
    
    //출고내역조회
    @Override
    public List<?> select_Sm1015(LinkedHashMap paramMap) throws Exception {        
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1015",paramMap);
    }

    // TOTE_CODE 정보조회
    public List<?> select_Sm1035_TOTE_M(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ TOTE_CODE 내역조회(참조)[Sm1030] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.tb.select_DLR_TOTECD_STDV", paramMap);
    }    

    //출고내역등록 
    @Override
    public String saveSm1030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
      //리턴할 값 담아놓을 변수설정
        String result = "";
        
        List<Map<String, Object>> paramList2 = new ArrayList<Map<String, Object>>(paramList.size());
        LinkedHashMap paramMap2 = new LinkedHashMap();
        
        int checkWhseC = 0 ; // 입고 창고 데이터가 있는지 확인 없으면 리스트 복사 안함
        
        
        //채번을 위한 변수설정
        String s_CORP_C  = paramMap.get("CORP_C").toString();
        String s_BZPL_C  = paramMap.get("BZPL_C").toString();
        String s_STDV_DT = paramMap.get("STDV_DT").toString();
        
        for(Map<String, Object> m0 : paramList) {    
            if(m0.get("_status_").equals("+")) {
                m0.put("DEL_YN", 'N');       //DEL_YN 은 필수값 신규 행은 해당 변수가 없어서 추가 
            }
            
            if(m0.get("WHSE_C_I").equals("") ) {
                
            }else {
                checkWhseC++;
            }
            
            if("".equals(paramMap.get("TR_SQNO")) || "0".equals(paramMap.get("TR_SQNO")))
            {
                //채번 서비스 호출(거래일련번호)
                String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
                m0.put("TR_SQNO", tr_seqNo);            
                paramMap.put("TR_SQNO", tr_seqNo);      
            }
        }
        
        if(checkWhseC != 0) {           // 입고 창고 데이터가 있으면 리스트 복사
            
            paramMap2.putAll(paramMap); // form 변수 복사
            
            for(Map<String, Object> m : paramList) {    // paramList 변수를 복사 paramList2
                Map<String,Object> map3 = new HashMap<String , Object>();
                map3.putAll(m);
                paramList2.add(map3);
            }
            
            for(Map<String, Object> map2 : paramList2) {
                    
                if(map2.get("WHSE_C_I").equals("") ) {
                    map2.put("FLAG_STDV_DSC_IO_YN", "N");

                }
                else {// 창고입고 데이터가 있으면 구분자 Y로 입력
                    map2.put("FLAG_STDV_DSC_IO_YN", "Y");   // 입력창고 값이 있으면
                    map2.put("WHSE_C", map2.get("WHSE_C_I"));
                    map2.put("STDV_DSC", "I");
                }
            }

            //입고창고용 
            paramMap2.put("STDV_DSC", "I");   
        }
               
        for(Map<String, Object> map1 : paramList) {  // 창고용 리스트 변수
            map1.put("STDV_DSC" , "O");
            map1.put("WHSE_C"   , map1.get("WHSE_C_O"));
            
        }
        
        
        if(paramMap.get("FSRG_ID").equals(""))      // 최초등록자 id가 출고의뢰찾기 입력(처음입력이라 변수값이 없음)
        {
        }
        else
        {
         // 입고상태 확인 출고완료 [1]일 경우에만 화면에서 수정 가능 
            String checkStdvStsDsc = sqlSession.selectOne("sfmes.sqlmap.sm.checkSm1030_STDV_STS_DSC", paramMap);
            
            if(!checkStdvStsDsc.equals("OK")) { // 검수입고 상태가 아니면
                throw infoException("출고완료 상태가 아닌경우 수정할 수 없습니다.");
            }
        }
        
        // 재고 집계
        sm1000Service.Call_saveSm1000(paramMap, paramList, paramMap2, paramList2  ); // 출고 출고 입고 입고
               
        // 출고상태 변경 출고의롸찾기 최초 입력시 출고완료로 변경 
        sm1000Service.Call_Sm1000StsUpdTrno(paramMap);
        
        //====================================================================
        // 2021.01.25 JKS TOTE CODE 관련 추가
        //====================================================================
        // TOTE_CODE가 입력될 경우 TOTE_CODE 정보를 저장한다.
        if (paramMap.get("PGM_ID") == null || paramMap.get("PGM_ID").equals("")) {
            paramMap.put("PGM_ID", "SM1030");
        }
        
        sm1010Service.Call_insertPdTote(paramMap, paramList, paramMap2, paramList2);
        
        // 참조구분이 출고의뢰등록 기반일 경우 해당 작업지시 상태 변경
        if (("4").equals(paramMap.get("STDV_REF_DSC"))) {
            // 출고의뢰등록번호로 작업지시번호를 찾는다.
            // 존재한다면 출고등록으로 작업지시상태값 변경
            
            // 출고상태
            paramMap.put("MFC_WK_STS_C", "03");
            
            pd2010Service.updatePd2050(paramMap, paramList);   
            
            //2021.10.16 서광석
            //출고의뢰내역도 출고완료상태로 변경처리
            paramMap.put("DLR_RQT_STS_DSC", "2");
            
            egovLogger.debug(":::::[출고의뢰내역상태값 변경(1.출고의뢰 -> 2.출고완료]:::::"+paramMap);
            sqlSession.update("sfmes.sqlmap.sm.update_SM1020_STS_UPDATE", paramMap);                
        }
            
        result = paramMap.toString();
        return result;
    }
    
    // 출고의뢰조회
    @Override
    public List<?> select_Sm1030_05(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug(":::::[select_Sm1030_05 LIST조회]:::::" + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1030_05",paramMap);
    }

    // 출고등록 전표삭제
    @Override
    public String deleteSm1030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        String result = "";
        
        List<Map<String, Object>> paramList2 = new ArrayList<Map<String, Object>>(paramList.size());
        LinkedHashMap paramMap2 = new LinkedHashMap();
      
        int checkWhseC = 0 ; // 입고 창고 데이터가 있는지 확인 없으면 리스트 복사 안함
        
        for(Map<String, Object> m0 : paramList) {    
            if(m0.get("WHSE_C_I").equals("") ) {
                
            }else {
                checkWhseC++;
            }
        }
        
        if(checkWhseC != 0) {           // 입고 창고 데이터가 있으면 리스트 복사
            
            paramMap2.putAll(paramMap); // form 변수 복사
            
            for(Map<String, Object> m : paramList) {    // paramList 변수를 복사 paramList2
                Map<String,Object> map3 = new HashMap<String , Object>();
                map3.putAll(m);
                paramList2.add(map3);
            }
            
            for(Map<String, Object> map2 : paramList2) {
                    
                if(map2.get("WHSE_C_I").equals("") ) {
                    map2.put("FLAG_STDV_DSC_IO_YN", "N");

                }
                else {// 창고입고 데이터가 있으면 구분자 Y로 입력
                    map2.put("FLAG_STDV_DSC_IO_YN", "Y");   // 입력창고 값이 있으면
                    map2.put("WHSE_C", map2.get("WHSE_C_I"));
                    map2.put("STDV_DSC", "I");
                }
            }

            //입고창고용 
            paramMap2.put("STDV_DSC", "I");   
        }
               
        for(Map<String, Object> map1 : paramList) {  // 창고용 리스트 변수
            map1.put("STDV_DSC" , "O");
            map1.put("WHSE_C"   , map1.get("WHSE_C_O"));
            
        }
        
        //sqlSession.update("sfmes.sqlmap.sm.deleteSm1030",paramMap); 삭제예정
        
        sm1000Service.Call_saveSm1000(paramMap, paramList, paramMap2, paramList2  ); // 출고 출고 입고 입고
        
        //====================================================================
        // 2021.01.25 JKS TOTE CODE 관련 추가
        // 2021.04.21 JKS CONDU_NO (개체번호) 컬럼 추가
        //====================================================================
        // TOTE_CODE가 입력될 경우 TOTE_CODE 정보를 저장한다.
        paramMap.put("PGM_ID", "SM1030");
        sm1010Service.Call_deletePdTote(paramMap, paramList, null, null);
        
        // 참조구분이 출고의뢰등록 기반일 경우 해당 작업지시 상태 변경
        if (("4").equals(paramMap2.get("STDV_REF_DSC"))) {
            // 출고의뢰등록번호로 작업지시번호를 찾는다.
            // 존재한다면 출고등록으로 작업지시상태값 변경
            
            // 출고의뢰상태
            paramMap.put("MFC_WK_STS_C", "02");
            
            pd2010Service.updatePd2050(paramMap, paramList2);                    
            
            //2021.10.16 서광석
            //출고의뢰내역도 출고완료상태로 변경처리
            paramMap.put("DLR_RQT_STS_DSC", "1");
            
            egovLogger.debug(":::::[출고의뢰내역상태값 변경(2.출고완료 -> 1.출고의뢰]:::::"+paramMap);
            sqlSession.update("sfmes.sqlmap.sm.update_SM1020_STS_UPDATE", paramMap);            
        }
            
        result = paramMap.toString();
        return result;
    }
    
  //출고기본내역
    @Override
    public List<?> select_Sm1035_M(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1035_M",paramMap);
    }
    
  //출고내역조회
    @Override
    public List<?> select_Sm1035_D(LinkedHashMap paramMap) throws Exception {        
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1035_D",paramMap);
    }
    
  //출고내역찾기팝업
    @Override
    public List<?> search_Sm1030P01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.search_Sm1030P01",paramMap);
    }
}
