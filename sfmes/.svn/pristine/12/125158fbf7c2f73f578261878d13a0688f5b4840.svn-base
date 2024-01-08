package com.sfmes.sm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.by.service.By2010Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.IF_PD_SM_HST_MNGService;
import com.sfmes.sm.service.Sm1010Service;
import com.sfmes.sm.service.Sm1000Service;

/**
 * @Class Name  : Sm1010ServiceImpl.java
 * @Description : Sm1010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.06   김지혜      최초생성
 * @ 2020.08.10   곽환용      변경
 * @ 2020.09.21   정성환      변경
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm1010Service")
public class Sm1010ServiceImpl extends CmnAbstractServiceImpl implements Sm1010Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Autowired
    private By2010Service by2010Service;
    
    //20200908ksm_추가
    @Autowired
    private IF_PD_SM_HST_MNGService IF_PD_SM_HST_MNGService;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Resource(name = "Sm1000Service")
    private Sm1000Service sm1000Service;
    
    //입고기본내역
    @Override
    public List<?> searchSm1010_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 입고기본내역조회[SM1010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SM_M_GDS_RL_STDV",paramMap);
    }
    
    //입고상세내역
    @Override
    public List<?> searchSm1010_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 입고상세내역조회[SM1010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SM_D_GDS_RL_STDV",paramMap);
    }

    //입고내역찾기팝업
    @Override
    public List<?> select_Sm1010P01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 입고내역찾기팝업[SM1010P01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1010P01",paramMap);
    }

    //입고내역조회
    @Override
    public List<?> select_Sm1015(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 입고내역조회[SM1015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1015",paramMap);
    }
    
  //입고내역조회 메인
    @Override
    public List<?> select_Sm1015_M(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1015_M",paramMap);
    }

    //발주참조내역조회
    @Override
    public List<?> select_Sm1010_BY(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 발주상세내역조회(참조)[BY2010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1010_BY",paramMap);
    }    

    // TOTE_CODE 정보조회
    public List<?> select_PdTOTE_M(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ TOTE_CODE 내역조회(참조)[Sm1010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.tb.select_VIEW_TOTECD",paramMap);
    }    

    // PDA 입고예정 물품정보조회
    public List<?> select_PDA_GDS_C(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA GDS 정보조회(참조)[PDA_ODR_GDS] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.sm.select_PDA_ODR_GDS",paramMap);
    }    

    // PDA 물품으로 입고예정내역조회
    public List<?> select_Sm1010_ODRGDS_M(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 입고예정일조회[PDA_Sm1010_ODRGDS_M] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1010_ODR_GDS",paramMap);
    }    

    // PDA 입고예정일조회
    public List<?> select_Pda_ODRDT(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 입고예정일조회[PDA_Sm1010_ODRDT] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.sm.select_PDA_Sm1010_ODRDT",paramMap);
    }    

    // PDA 입고예정일의 거래처조회
    public List<?> select_Pda_TRPL(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 입고예정일의 거래처조회[PDA_Sm1010_ODR_TRPL] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.sm.select_PDA_Sm1010_ODR_TRPL",paramMap);
    }    

    //발주참조내역조회
    @Override
    public List<?> select_Sm1010_ODR_M(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 발주기본내역조회(참조)[PDA_Sm1010_ODR_TRPL] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1010_ODR",paramMap);
    }    
    
    //PDA 입고 내역 등록 (2021.03.30 - 박지환 생성)
    @Override
    public String saveSm1010_PDA(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
    	egovLogger.debug("************ PDA 입고 내역 등록 *********");
    	
    	//등록후 조회를 위한 값저장 변수
        String result = "";
        
        String tempStdvDt   = (String) paramMap.get("STDV_DT");
        String tempStdvSqno = (String) paramMap.get("STDV_SQNO");
        
        String s_CORP_C  = paramMap.get("CORP_C").toString();
        String s_BZPL_C  = paramMap.get("BZPL_C").toString();
        String s_STDV_DT = paramMap.get("STDV_DT").toString();
        
        paramMap.put("ACG_DT", paramMap.get("RLTR_DT"));
        paramMap.put("STDV_STS_DSC", '1');
        paramMap.put("STDV_REF_DSC", '2');
        
        //거래일련번호 채번
        String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
        egovLogger.debug("생성된 거래일련번호 채번: " + tr_seqNo);
        paramMap.put("TR_SQNO", tr_seqNo);  
        
    	//디테일 정보 키값 세팅
        for(Map<String, Object> map2 : paramList) {
        	map2.put("CORP_C"   , paramMap.get("CORP_C"));
            map2.put("BZPL_C"   , paramMap.get("BZPL_C"));
            map2.put("STDV_DT"  , paramMap.get("STDV_DT"));
            map2.put("STDV_SQNO", paramMap.get("STDV_SQNO"));
            map2.put("STDV_DSC" , paramMap.get("STDV_DSC"));
            map2.put("STDV_DSC" , paramMap.get("GUSRID"));
        }
        
        // 재고 등록 및 집계 처리
    	sm1000Service.Call_saveSm1000(paramMap, paramList, null, null);
    	
    	// tr_sqno 처리
    	sm1000Service.Call_Sm1000StsUpdTrno(paramMap);
    	
    	paramMap.put("ODR_STS_DSC", "2");
        by2010Service.Call_updateBy2010(paramMap);
        
        paramMap.put("PGM_ID", "SM1010");
        this.Call_insertPdTote(paramMap, paramList, null, null);
       
        // 신규        
        paramMap.put("OLD_STDV_DT"  ,tempStdvDt);
        paramMap.put("OLD_STDV_SQNO",tempStdvSqno);
        paramMap.put("MFC_DSC"      , "1");
        
        IF_PD_SM_HST_MNGService.if_HST_MNG_insert(paramMap, paramList);
        
               
        result = paramMap.toString();
        return result;
    }

    //입고내역등록 
    @Override
    public String saveSm1010_2(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        // !! paramList 변수는 수정된 리스트의 행 paramList3 은 화면에서 조회된 리스트의 모든 데이터 
        
        //리턴할 값 담아놓을 변수설정
        String result = "";
        
        String tempStdvDt   = (String) paramMap.get("STDV_DT");
        String tempStdvSqno = (String) paramMap.get("STDV_SQNO");
        
        //매입에서 입고내역생성할 때 mapping처리
        if("BY10".equals(paramMap.get("TR_BSN_DSC")))
        {           
            paramMap.put("STDV_DT"     , paramMap.get("BY_DT"));
            paramMap.put("STDV_STS_DSC", "2");
            paramMap.put("STDV_REF_DSC", "1");
            paramMap.put("STDV_DSC"    , "I");
        }
        
        //채번을 위한 변수설정
        String s_CORP_C  = paramMap.get("CORP_C").toString();
        String s_BZPL_C  = paramMap.get("BZPL_C").toString();
        String s_STDV_DT = paramMap.get("STDV_DT").toString();
        
        //채번 서비스 호출
        String seqNo = commonService.getGvno(s_CORP_C,"TB_SM_M_GDS_RL_STDV",s_BZPL_C, s_STDV_DT, 1);
        egovLogger.debug("생성된 일련번호 채번: " + seqNo);
        paramMap.put("STDV_SQNO", seqNo);
        
        if("".equals(paramMap.get("TR_SQNO")) || "0".equals(paramMap.get("TR_SQNO")))
        {
            //채번 서비스 호출(거래일련번호)
            String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
            egovLogger.debug("생성된 거래일련번호 채번: " + tr_seqNo);
            paramMap.put("TR_SQNO", tr_seqNo);              
        }
        
        for(Map<String, Object> map2 : paramList) 
        {
            map2.put("BZPL_C"   , paramMap.get("BZPL_C")   );
            map2.put("STDV_DT"  , paramMap.get("STDV_DT")  );
            map2.put("STDV_SQNO", paramMap.get("STDV_SQNO"));
            map2.put("STDV_DSC" , paramMap.get("STDV_DSC") );             
        }
        
        if(paramMap.get("FSRG_ID").equals(""))      // 최초등록자 id가 비어있으면 발주참조 입력(처음입력이라 변수값이 없음)
        {
        }
        else
        {
         // 입고상태 확인 검수입고 [1]일 경우에만 화면에서 수정 가능 
            String checkStdvStsDsc = sqlSession.selectOne("sfmes.sqlmap.sm.checkSm1010_STDV_STS_DSC", paramMap);
            
            if(!checkStdvStsDsc.equals("OK")) { // 검수입고 상태가 아니면
                    throw infoException("검수입고 상태가 아닌경우 수정할 수 없습니다.");
            }
        }

        sm1000Service.Call_saveSm1000(paramMap, paramList, null, null  ); // 입고, 입고, 출고, 출고
        
        // 입고상태 변경 발주상태일때는 최초로 검수입고록 변경 
        sm1000Service.Call_Sm1000StsUpdTrno(paramMap);              
        
        //발주참조 시 입고내역 등록 후 해당 발주내역의 발주상태구분 변경(입고등록)
        if(("2".equals(paramMap.get("STDV_REF_DSC"))) && ("SM10".equals(paramMap.get("TR_BSN_DSC")))) 
        {
            //2021.10.06 서광석
            //발ㄹ주전표 재사용 기능 추가
            //발주내역수정 [BY2010]
            String s_ORD_RE_USE_YN  = paramMap.get("ORD_RE_USE_YN").toString();
            
            if("Y".equals(s_ORD_RE_USE_YN)) {
                paramMap.put("ODR_STS_DSC", "1");                
            }else {
                paramMap.put("ODR_STS_DSC", "2");
            }
            by2010Service.Call_updateBy2010(paramMap);
        }
        
        //====================================================================
        // 2021.01.25 JKS TOTE CODE 관련 추가
        //====================================================================
        // TOTE_CODE가 입력될 경우 TOTE_CODE 정보를 저장한다.
        paramMap.put("PGM_ID", "SM1010");
        this.Call_insertPdTote(paramMap, paramList, null, null);
        //====================================================================
       
        // 신규        
        paramMap.put("OLD_STDV_DT"  ,tempStdvDt);
        paramMap.put("OLD_STDV_SQNO",tempStdvSqno);
        paramMap.put("MFC_DSC"      , "1");
        
        IF_PD_SM_HST_MNGService.if_HST_MNG_insert(paramMap, paramList);
        
               
        result = paramMap.toString();
        return result;
    }
    
    //입고내역수정
    @Override
    public String updateSm1010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        //리턴할 값 담아놓을 변수설정
        String result = "";
        
        String tempStdvDt   = (String) paramMap.get("STDV_DT");
        String tempStdvSqno = (String) paramMap.get("STDV_SQNO");
        
        for(Map<String, Object> map : paramList) {
            map.put("BZPL_C"   , paramMap.get("BZPL_C"));
            map.put("STDV_DT"  , paramMap.get("STDV_DT"));
            map.put("STDV_SQNO", paramMap.get("STDV_SQNO"));
            map.put("STDV_DSC" , paramMap.get("STDV_DSC"));
        }
        
        if(paramMap.get("FSRG_ID").equals(""))      // 최초등록자 id가 비어있으면 발주참조 입력(처음입력이라 변수값이 없음)
        {
        }
        else
        {
         // 입고상태 확인 검수입고 [1]일 경우에만 화면에서 수정 가능 
            String checkStdvStsDsc = sqlSession.selectOne("sfmes.sqlmap.sm.checkSm1010_STDV_STS_DSC", paramMap);
            
            if(!checkStdvStsDsc.equals("OK")) { // 검수입고 상태가 아니면
                    throw infoException("검수입고 상태가 아닌경우 수정할 수 없습니다.");
            }
        }
        
        sm1000Service.Call_saveSm1000(paramMap, paramList, null, null  ); // 입고, 입고,

        // 입고상태 변경 
        sm1000Service.Call_Sm1000StsUpdTrno(paramMap);
        
        //====================================================================
        // 2021.01.25 JKS TOTE CODE 관련 추가
        //====================================================================
        // TOTE_CODE 여부 확인 삭제
        paramMap.put("PGM_ID", "SM1010");
        this.Call_deletePdTote(paramMap, paramList, null, null);
        
        // TOTE_CODE가 입력될 경우 TOTE_CODE 정보를 저장한다.
        this.Call_insertPdTote(paramMap, paramList, null, null);
        //====================================================================
        
        //(물품이력테이블 축산물이력번호 변경)( 삭제 -> 등록)
        paramMap.put("OLD_STDV_DT"  ,tempStdvDt);
        paramMap.put("OLD_STDV_SQNO",tempStdvSqno);
        paramMap.put("MFC_DSC"      , "1");
        
        IF_PD_SM_HST_MNGService.if_HST_MNG_delete(paramMap);
        IF_PD_SM_HST_MNGService.if_HST_MNG_insert(paramMap, paramList);
        
        
        result = paramMap.toString();
        return result;
    }
    
    //입고내역삭제
    @Override
    public void deleteSm1010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        //입고기본내역 수정(전표정상여부: "N")        
        //sqlSession.update("sfmes.sqlmap.sm.deleteSm1010_BY", paramMap); 삭제예정
        
        egovLogger.debug("====> paramMap ::: " + paramMap);
        
        // TOTE_CODE 여부 확인 삭제
        for(Map<String, Object> map2 : paramList) 
        {
            map2.put("BZPL_C"   , paramMap.get("BZPL_C")   );
            map2.put("STDV_DT"  , paramMap.get("STDV_DT")  );
            map2.put("STDV_SQNO", paramMap.get("STDV_SQNO"));
            map2.put("STDV_DSC" , paramMap.get("STDV_DSC") );
        }

        sm1000Service.Call_saveSm1000(paramMap, paramList, null, null  ); // 입고, 입고,
        
        String tempStdvDt   = (String) paramMap.get("STDV_DT");
        String tempStdvSqno = (String) paramMap.get("STDV_SQNO");
        
        //====================================================================
        // 2021.01.25 JKS TOTE CODE 관련 추가
        //====================================================================
        // TOTE_CODE 여부 확인 삭제
        paramMap.put("PGM_ID", "SM1010");
        this.Call_deletePdTote(paramMap, paramList, null, null);
        //====================================================================
        
        //20200908ksm_추가(물품이력테이블 삭제여부변경)
        paramMap.put("OLD_STDV_DT"  ,tempStdvDt);
        paramMap.put("OLD_STDV_SQNO",tempStdvSqno);
        paramMap.put("MFC_DSC"      , "1");
        
        IF_PD_SM_HST_MNGService.if_HST_MNG_delete(paramMap);
        
        
        //발주참조 시 입고내역 등록 후 해당 발주내역의 발주상태구분 변경(입고등록)
        if((! "0".equals(paramMap.get("STDV_REF_SQNO"))) && ("SM10".equals(paramMap.get("TR_BSN_DSC")))) 
        {
            egovLogger.debug("************ 발주내역수정 [BY2010] *********");
            paramMap.put("STDV_DT"    , null);
            paramMap.put("STDV_SQNO"  , 0);
            paramMap.put("ODR_STS_DSC", "1");
            by2010Service.Call_updateBy2010(paramMap);
        }        
    }
    
    // TOTE 입출 내역 등록
    @Override
    public void Call_insertPdTote_MSTTBL(LinkedHashMap paramMap) throws Exception {

    }

    // TOTE 내역 삭제
    @Override
    public void Call_deletePdTote_MSTTBL(LinkedHashMap paramMap) throws Exception {

    }
    
    // TOTE 입출 내역 등록
    @Override
    public void Call_insertPdTote(LinkedHashMap paramMap1, List<Map<String, Object>> paramList1, LinkedHashMap paramMap2, List<Map<String, Object>> paramList2) throws Exception {

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
        
        for(Map<String, Object> map2 : paramList1) 
        {
            map2.put("BZPL_C"   , paramMap1.get("BZPL_C")   );
            map2.put("STDV_DT"  , paramMap1.get("STDV_DT")  );
            map2.put("STDV_SQNO", paramMap1.get("STDV_SQNO"));
            map2.put("STDV_DSC" , paramMap1.get("STDV_DSC") );
            map2.put("MSTB_YN"  , paramMap1.get("MSTB_YN")  );
             
            if(map2.get("TOTE_CODE") != null && !("").equals(map2.get("TOTE_CODE"))) {
                Map<String, Object> tote_map = new HashMap<>();
                
                tote_map.putAll(map2);
                // 추가 map element
                tote_map.put("ACG_DT"     , paramMap1.get("RLTR_DT"));   // 실입고일을 회례기준일로
                tote_map.put("SLP_NML_YN" , "Y"                    );
                
                
                // VIEW 에서 TOTE_CODE가 존재하는지 확인한다.
                // 존재할 경우 TB_PD_M_TOTECD Table 에 저장한다.
                Integer cd_cnt = sqlSession.selectOne("sfmes.sqlmap.pd.select_VMF_TOTE_CODES_validCheck_01", tote_map);
                
                if(cd_cnt > 0) {
                    egovLogger.debug("====> tote_map ::: " + tote_map);
                    
                    // TOTE_CODE Master Table insert/update
                    if(("SM1010").equals(map2.get("PGM_ID"))) {
                        // 물품입고
                        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_M_TOTECD", tote_map);
                    } else if(("SE6020").equals(map2.get("PGM_ID"))) {
                        //제품출고
                        sqlSession.insert("sfmes.sqlmap.tb.update_TB_PD_M_TOTECD_DLR", tote_map);
                    }
                    
                    // TOTE_CODE STDV 내역  insert
                    sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_TOTECD_STDV", tote_map);
                } else {
                    throw infoException("입고 자료의 TOTE_CODE 가 존재하지 않아 입력 할 수 없습니다.");
                }
            }
        }
    }

    // TOTE 내역 삭제
    @Override
    public void Call_deletePdTote(LinkedHashMap paramMap1, List<Map<String, Object>> paramList1, LinkedHashMap paramMap2, List<Map<String, Object>> paramList2) throws Exception {
        
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
        
        for(Map<String, Object> map2 : paramList1) 
        {
            map2.put("BZPL_C"   , paramMap1.get("BZPL_C")   );
            map2.put("STDV_DT"  , paramMap1.get("STDV_DT")  );
            map2.put("STDV_SQNO", paramMap1.get("STDV_SQNO"));
            map2.put("STDV_DSC" , paramMap1.get("STDV_DSC") );
                           
            // TOTE_CODE가 입력될 경우 TOTE_CODE 정보를 저장한다.
            if(!("").equals(map2.get("TOTE_CODE"))) {
                egovLogger.debug("====> TOTE_CODE 전표정상여부 수정 시작");

                Map<String, Object> tote_map = new HashMap<>();
                
                tote_map.putAll(map2);
                
                egovLogger.debug("====> tote_map ::: " + tote_map);
                
                // VIEW 에서 TOTE_CODE가 존재하는지 확인한다.
                // 존재할 경우 TB_PD_M_TOTECD Table 에 저장한다.
                Integer cd_cnt = sqlSession.selectOne("sfmes.sqlmap.pd.select_TB_PD_M_TOTECD_validCheck_01", tote_map);
                
                if(cd_cnt > 0) {
                    if(("SM1010").equals(map2.get("PGM_ID"))) {
                        // 물품출고
                        sqlSession.delete("sfmes.sqlmap.tb.delete_TB_PD_M_TOTECD", tote_map);
                    } else if(("SE6020").equals(map2.get("PGM_ID"))) {
                        //제품출고 
                        // TOTE_CODE STDV 내역  delete( 잔표정상여부 'N;' 으로 변경
                        sqlSession.delete("sfmes.sqlmap.tb.delete_TB_PD_D_TOTECD_STDV_SLP_NML", tote_map);
                    }
                } else {
                    throw infoException("입고 자료의 TOTE_CODE 가 존재하지 않아 입력 할 수 없습니다.");
                }
            }
        }
    }
}
