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
import com.sfmes.sm.service.Sm3010Service;
import com.sfmes.sm.service.Sm1000Service;

/**
 * @Class Name  : Sm3010ServiceImpl.java
 * @Description : Sm3010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.08   정성환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm3010Service")
public class Sm3010ServiceImpl extends CmnAbstractServiceImpl implements Sm3010Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Resource(name = "Sm1000Service")
    private Sm1000Service sm1000Service;
    
    //창고간기본내역조회 기본
    @Override
    public List<?> searchSm3010_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm3010_01",paramMap);
    }
    
    //창고간기본내역조회 상세
    @Override
    public List<?> searchSm3010_02(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm3010_02",paramMap);
    } 

    // 창고별 물품내역조회
    @Override
    public List<?> searchPDA_Sm3010_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm2130_01",paramMap);
    }
    
    // 물품별 창고물품내역조회
    @Override
    public List<?> searchPDA_Sm3010_02(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm2140_01",paramMap);
    } 

    //창고간이동내역등록 
    @Override
    public String savePDA_Sm3010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        //리턴할 값 담아놓을 변수설정
        String result = "";

        // 창고간이동내역 저장 변수 확인 
        if(paramMap == null) {
            throw infoException("USERMSG:창고간이동내역 기본 데이터가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        if(paramList == null) {
            throw infoException("USERMSG:창고간이동내역상세 데이터가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        List<Map<String, Object>> paramList2 = new ArrayList<Map<String, Object>>(paramList.size());
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
        String seqNo = commonService.getGvno(s_CORP_C,"TB_SM_M_STPL_MVE",s_BZPL_C, strToday, 1);
        
        paramMap.put("MVE_SQNO", seqNo);
        paramMap.put("MVE_DT"  , strToday);
        // 창고간이동내역 기본 저장 
        sqlSession.insert("sfmes.sqlmap.sm.saveSm3010_M", paramMap);
        
        int idx = 0;
        for(Map<String, Object> map1 : paramList)
        {
            map1.put("CORP_C"  , paramMap.get("CORP_C"));
            map1.put("BZPL_C"  , paramMap.get("BZPL_C"));
            map1.put("MVE_DT"  , paramMap.get("MVE_DT"));
            map1.put("MVE_SQNO", paramMap.get("MVE_SQNO"));
            map1.put("GUSRID"  , paramMap.get("GUSRID"));
            map1.put("DEL_YN"  , 'N');
            map1.put("MVE_DSQNO", ++idx);
            
            // 창고간이동내역 상세 저장
            sqlSession.insert("sfmes.sqlmap.sm.saveSm3010_D", map1);
        }
        
        paramMap2.putAll(paramMap); // form 변수 복사
        
        for(Map<String, Object> m : paramList) {    // paramList 변수를 복사 paramList2
            // 변경되지 않은 RECODRD SKIP
            if( m.get("STDV_QT").equals("0")) continue; 
            
            Map<String,Object> map3 = new HashMap<String , Object>();
            m.put("STDV_DT"     , paramMap.get("MVE_DT"));              // 이동일자
            m.put("RLTR_DT"     , paramMap.get("RLTR_DT"));              // 실제이동일자
            m.put("GUSRID"      , paramMap.get("GUSRID"));
            
            map3.putAll(m);                                             // 리스트데이터 복사
            paramList2.add(map3);                                       // 리스트데이터 복사
            
            m.put("WHSE_C"      , m.get("DLR_WHSE_C"));                 // 출고창고코드
            m.put("STDV_DSC"    , "O");                                 // 입출구구분 출고창고 O            
        }
        
        paramMap.put("STDV_DSC", "O");      //입출고구분자=출고
        paramMap.put("STDV_STS_DSC", "1");  //입출고상태구분코드
        
        
        for(Map<String, Object> map2 : paramList2) {            
            map2.put("FLAG_STDV_DSC_IO_YN"  , "Y");                         // 입력창고 값이 있으면
            map2.put("WHSE_C"               , map2.get("STR_WHSE_C"));      // 입고창고코드
            map2.put("STDV_DSC"             , "I");                         // 입출구구분 입고창고 I
            map2.put("STDV_DT"              , paramMap.get("MVE_DT"));      // 이동일자
            map2.put("RLTR_DT"              , paramMap.get("RLTR_DT"));     // 실제이동일자
            map2.put("DEL_YN"               , 'N');
            map2.put("GUSRID"      , paramMap.get("GUSRID"));
        }

        paramMap2.put("STDV_DSC", "I"); //입출고구분자=입고
        paramMap2.put("STDV_STS_DSC", "1");  //입출고상태구분코드
        
        sm1000Service.Call_saveSm1000(paramMap, paramList, paramMap2, paramList2  ); // 출고 출고 입고 입고
            
        result = paramMap.toString();
        return result;
    }

    //창고간이동내역등록 
    @Override
    public String saveSm3010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        //리턴할 값 담아놓을 변수설정
        String result = "";
       
        // 창고간이동내역 저장 변수 확인 
        if(paramMap == null) {
            throw infoException("USERMSG:창고간이동내역 기본 데이터가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        if(paramList == null) {
            throw infoException("USERMSG:창고간이동내역상세 데이터가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        List<Map<String, Object>> paramList2 = new ArrayList<Map<String, Object>>(paramList.size());
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
        String seqNo = commonService.getGvno(s_CORP_C,"TB_SM_M_STPL_MVE",s_BZPL_C, strToday, 1);
        
        paramMap.put("MVE_SQNO", seqNo);
        paramMap.put("MVE_DT"  , strToday);
        // 창고간이동내역 기본 저장 
        sqlSession.insert("sfmes.sqlmap.sm.saveSm3010_M", paramMap);
        
        int idx = 0;
        for(Map<String, Object> map1 : paramList)
        {
            map1.put("CORP_C"  , paramMap.get("CORP_C"));
            map1.put("BZPL_C"  , paramMap.get("BZPL_C"));
            map1.put("MVE_DT"  , paramMap.get("MVE_DT"));
            map1.put("MVE_SQNO", paramMap.get("MVE_SQNO"));
            map1.put("DEL_YN"  , 'N');
            map1.put("MVE_DSQNO", ++idx);
            
            // 창고간이동내역 상세 저장
            sqlSession.insert("sfmes.sqlmap.sm.saveSm3010_D", map1);
        }
        
        /*
        if(paramMap.get("FSRG_ID").equals(""))      // 최초등록자 id가 출고의뢰찾기 입력(처음입력이라 변수값이 없음)
        {
        }
        else
        {
         // 입고상태 확인 출고완료 [1]일 경우에만 화면에서 수정 가능 
            String checkStdvStsDsc = sqlSession.selectOne("sfmes.sqlmap.sm.checkSm3010_STDV_STS_DSC", paramMap);
            
            if(!checkStdvStsDsc.equals("OK")) { // 검수입고 상태가 아니면
                    throw infoException("출고완료 상태가 아닌경우 수정할 수 없습니다.");
            }
        }
        */
        
        paramMap2.putAll(paramMap); // form 변수 복사
        
        for(Map<String, Object> m : paramList) {    // paramList 변수를 복사 paramList2
            Map<String,Object> map3 = new HashMap<String , Object>();
            m.put("STDV_DT"     , paramMap.get("MVE_DT"));              // 이동일자
            m.put("RLTR_DT"     , paramMap.get("RLTR_DT"));              // 실제이동일자
            
            map3.putAll(m);                                             // 리스트데이터 복사
            paramList2.add(map3);                                       // 리스트데이터 복사
            
            m.put("WHSE_C"      , m.get("DLR_WHSE_C"));                 // 출고창고코드
            m.put("STDV_DSC"    , "O");                                 // 입출구구분 출고창고 O
            
        }
        
        paramMap.put("STDV_DSC", "O");      //입출고구분자=출고
        paramMap.put("STDV_STS_DSC", "1");  //입출고상태구분코드
        
        
        for(Map<String, Object> map2 : paramList2) {
            map2.put("FLAG_STDV_DSC_IO_YN"  , "Y");                     // 입력창고 값이 있으면
            map2.put("WHSE_C"               , map2.get("STR_WHSE_C"));  // 입고창고코드
            map2.put("STDV_DSC"             , "I");                     // 입출구구분 입고창고 I
            map2.put("STDV_DT"     , paramMap.get("MVE_DT"));              // 이동일자
            map2.put("RLTR_DT"     , paramMap.get("RLTR_DT"));              // 실제이동일자
            map2.put("DEL_YN"      , 'N');
        }

        paramMap2.put("STDV_DSC", "I"); //입출고구분자=입고
        paramMap2.put("STDV_STS_DSC", "1");  //입출고상태구분코드
        
        sm1000Service.Call_saveSm1000(paramMap, paramList, paramMap2, paramList2  ); // 출고 출고 입고 입고
            
        result = paramMap.toString();
        return result;
    }

    //창고간재고이동 전표삭제
    @Override
    public String deleteSm3010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        String result = "";
        
        if(paramMap == null) {
            throw infoException("USERMSG:창고간이동내역 기본 데이터가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        if(paramList == null) {
            throw infoException("USERMSG:창고간이동내역상세 데이터가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        sqlSession.update("sfmes.sqlmap.sm.updateSm3010_SLP_NML_YN", paramMap);
        
        List<Map<String, Object>> paramList2 = new ArrayList<Map<String, Object>>(paramList.size());
        LinkedHashMap paramMap2 = new LinkedHashMap();
        
        paramMap2.putAll(paramMap); // form 변수 복사
        
        for(Map<String, Object> m : paramList) {    // paramList 변수를 복사 paramList2
            Map<String,Object> map3 = new HashMap<String , Object>();
            m.put("STDV_DT"     , paramMap.get("MVE_DT"));              // 이동일자
            m.put("RLTR_DT"     , paramMap.get("RLTR_DT"));              // 실제이동일자
            
            map3.putAll(m);                                             // 리스트데이터 복사
            paramList2.add(map3);                                       // 리스트데이터 복사
            
            m.put("WHSE_C"      , m.get("DLR_WHSE_C"));                 // 출고창고코드
            m.put("STDV_DSC"    , "O");                                 // 입출구구분 출고창고 O
            m.put("DEL_YN"      , 'N');                              // 삭제여부
            
        }
        
        paramMap.put("STDV_DSC", "O");      //입출고구분자=출고
        paramMap.put("STDV_STS_DSC", "1");  //입출고상태구분코드
        
        
        for(Map<String, Object> map2 : paramList2) {
            map2.put("FLAG_STDV_DSC_IO_YN"  , "Y");                     // 입력창고 값이 있으면
            map2.put("WHSE_C"               , map2.get("STR_WHSE_C"));  // 입고창고코드
            map2.put("STDV_DSC"             , "I");                     // 입출구구분 입고창고 I
            map2.put("STDV_DT"     , paramMap.get("MVE_DT"));              // 이동일자
            map2.put("RLTR_DT"     , paramMap.get("RLTR_DT"));              // 실제이동일자
            map2.put("DEL_YN"      , 'N');                              // 삭제여부 
        }

        paramMap2.put("STDV_DSC", "I"); //입출고구분자=입고
        paramMap2.put("STDV_STS_DSC", "1");  //입출고상태구분코드
        
        
        
        sm1000Service.Call_saveSm1000(paramMap, paramList, paramMap2, paramList2  ); // 출고 출고 입고 입고
        
        result = paramMap.toString();
        return result;
    }

    //창고간이동내역수정
    @Override
    public String updateSm3010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        String result = "";
        
     // 창고간이동내역 저장 변수 확인 
        if(paramMap == null) {
            throw infoException("USERMSG:창고간이동내역 기본 데이터가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        if(paramList == null) {
            throw infoException("USERMSG:창고간이동내역상세 데이터가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        List<Map<String, Object>> paramList2 = new ArrayList<Map<String, Object>>(paramList.size());
        LinkedHashMap paramMap2 = new LinkedHashMap();
        
        // 창고간이동내역수정 기본
        sqlSession.update("sfmes.sqlmap.sm.updateSm3010_M", paramMap);
        
        // 창고간이동내역수정 상세
        for(Map<String, Object> map1 : paramList)
        {
            if(map1.get("_status_").equals("-"))
            {
                sqlSession.delete("sfmes.sqlmap.sm.deleteSm3010_D", map1);
                map1.put("DEL_YN"      , 'Y');
            }
            else if(map1.get("_status_").equals("*")) 
            {
                sqlSession.update("sfmes.sqlmap.sm.updateSm3010_D", map1);  
                map1.put("DEL_YN"      , 'N');
            }
            else if(map1.get("_status_").equals("+"))
            {
                
                map1.put("CORP_C"  , paramMap.get("CORP_C"));
                map1.put("BZPL_C"  , paramMap.get("BZPL_C"));
                map1.put("MVE_DT"  , paramMap.get("MVE_DT"));
                map1.put("MVE_SQNO", paramMap.get("MVE_SQNO"));
                map1.put("DEL_YN"      , 'N');
                
                int mveDsqnoCount = sqlSession.selectOne("sfmes.sqlmap.sm.selectSm3010MveDsqnoCount", map1);
                
                map1.put("MVE_DSQNO", mveDsqnoCount);
                // 창고간이동내역 상세 저장
                sqlSession.insert("sfmes.sqlmap.sm.saveSm3010_D", map1);
            }
            else if(map1.get("_status_").equals("")) // 변경없는 데이터
            {
                map1.put("DEL_YN"      , 'N');
            }
        }
        
        
        
        
        paramMap2.putAll(paramMap); // form 변수 복사
        
        for(Map<String, Object> m : paramList) {    // paramList 변수를 복사 paramList2
            Map<String,Object> map3 = new HashMap<String , Object>();
            m.put("STDV_DT"     , paramMap.get("MVE_DT"));              // 이동일자
            m.put("RLTR_DT"     , paramMap.get("RLTR_DT"));              // 실제이동일자
            
            map3.putAll(m);                                             // 리스트데이터 복사
            paramList2.add(map3);                                       // 리스트데이터 복사
            
            m.put("WHSE_C"      , m.get("DLR_WHSE_C"));                 // 출고창고코드
            m.put("STDV_DSC"    , "O");                                 // 입출구구분 출고창고 O
        }
        
        paramMap.put("STDV_DSC", "O");      //입출고구분자=출고
        paramMap.put("STDV_STS_DSC", "1");  //입출고상태구분코드
        
        
        for(Map<String, Object> map2 : paramList2) {
            map2.put("FLAG_STDV_DSC_IO_YN"  , "Y");                     // 입력창고 값이 있으면
            map2.put("WHSE_C"               , map2.get("STR_WHSE_C"));  // 입고창고코드
            map2.put("STDV_DSC"             , "I");                     // 입출구구분 입고창고 I
            map2.put("STDV_DT"     , paramMap.get("MVE_DT"));              // 이동일자
            map2.put("RLTR_DT"     , paramMap.get("RLTR_DT"));              // 실제이동일자
        }

        paramMap2.put("STDV_DSC", "I"); //입출고구분자=입고
        paramMap2.put("STDV_STS_DSC", "1");  //입출고상태구분코드
        
        sm1000Service.Call_saveSm1000(paramMap, paramList, paramMap2, paramList2  ); // 출고 출고 입고 입고
        
        result = paramMap.toString();
        return result;
    }
}
