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
import com.sfmes.se.service.Se6010Service;
import com.sfmes.sm.service.Sm1050Service;

/**
 * @Class Name  : Sm1050ServiceImpl.java
 * @Description : Sm1050Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.06   김지혜      최초생성
 * @ 2020.08.10   곽환용      변경
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm1050Service")
public class Sm1050ServiceImpl extends CmnAbstractServiceImpl implements Sm1050Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Autowired
    private Se6010Service se6010Service;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    //제품출고기본내역
    @Override
    public List<?> select_Sm1050_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 제품출고기본내역조회[SM1050] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SM_M_GDS_RL_STDV", paramMap);
    }
    
    //제품출고상세내역
    @Override
    public List<?> select_Sm1050_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 제품출고상세내역조회[SM1050] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SM_D_GDS_RL_STDV", paramMap);
    }

    //제품출고내역찾기팝업
    @Override
    public List<?> select_Sm1050_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 제품출고내역찾기팝업[SM1050P01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1050_03", paramMap);
    }

    //제품출고내역조회
    @Override
    public List<?> select_Sm1050_04(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 제품출고내역조회[SM1015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1050_04", paramMap);
    }

    //출고지시기본내역조회
    @Override
    public List<?> select_Sm1050_M_DLR(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시기본내역조회(참조)[SM1040] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1050_M_DLR", paramMap);
    }   
    
    //출고지시상세내역조회
    @Override
    public List<?> select_Sm1050_D_DLR(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시상세내역조회(참조)[SM1040] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1050_D_DLR", paramMap);
    }    

    //제품출고내역등록 
    @Override
    public String saveSm1050(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 제품출고등록[SM1050] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        //리턴할 값 담아놓을 변수설정
        String result = "";
        
        //매출등록에서 출고내역생성할 때 mapping처리
        if("SE10".equals(paramMap.get("TR_BSN_DSC")))
        {           
            paramMap.put("STDV_DT"     , paramMap.get("SL_DT"));
            paramMap.put("STDV_STS_DSC", "2");
            paramMap.put("STDV_REF_DSC", "1");
            paramMap.put("STDV_DSC"    , "O");
            egovLogger.debug("************ 매출에서 출고내역생성 *********");
            egovLogger.debug("paramMap: " + paramMap.toString()); 
        }

        //채번을 위한 변수설정
        String s_CORP_C  = paramMap.get("CORP_C").toString();
        String s_BZPL_C  = paramMap.get("BZPL_C").toString();
        String s_STDV_DT = paramMap.get("STDV_DT").toString();
        
        //채번 서비스 호출
        String seqNo = commonService.getGvno(s_CORP_C,"TB_SM_M_GDS_RL_STDV",s_BZPL_C, s_STDV_DT, 1);
        egovLogger.debug("생성된 일련번호 채번: " + seqNo);
        paramMap.put("STDV_SQNO", seqNo);

        //제품출고등록시 거래일련번호 채번
        //타업무에서는 거래일련번호 채번해서 넘겨주기 때문에 따로 채번할 필요 없음 
        //거래일련번호 채번생성
        //if(! paramMap.containsKey("TR_SQNO"))
        if("".equals(paramMap.get("TR_SQNO")) || "0".equals(paramMap.get("TR_SQNO")))
        {
            //채번 서비스 호출(거래일련번호)
            String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
            egovLogger.debug("생성된 거래일련번호 채번: " + tr_seqNo);
            paramMap.put("TR_SQNO", tr_seqNo);              
        }        
        
        //배송고객정보 등록
        if("2".equals(paramMap.get("DVY_OBJ_DSC")))
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
        
        //제품출고기본내역저장
        egovLogger.debug("************ 제품출고기본등록[SM1050] *********"); 
        egovLogger.debug("paramMap: " + paramMap.toString());
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_M_GDS_RL_STDV", paramMap);
                
        //제품출고상세내역저장
        for(Map<String, Object> map : paramList) 
        {
            map.put("BZPL_C"   , paramMap.get("BZPL_C")   );
            map.put("STDV_DT"  , paramMap.get("STDV_DT")  );
            map.put("STDV_SQNO", paramMap.get("STDV_SQNO"));
            map.put("STDV_DSC" , paramMap.get("STDV_DSC") );             

            //매출에서 출고상세내역생성할 때 mapping처리
            if("SE10".equals(paramMap.get("TR_BSN_DSC")))
            {                
                map.put("STDV_QT"    , map.get("SL_QT")    );
                map.put("STDV_BOX_QT", map.get("SL_BOX_QT"));
                map.put("STDV_UPR"   , map.get("SL_UPR")   );
                map.put("STDV_AM"    , map.get("SL_AM")    );
                map.put("STDV_WT"    , map.get("SL_WT")    );                
            }            

            egovLogger.debug("************ 제품출고상세등록 [SM1050] *********");
            egovLogger.debug("paramList: " + paramList.toString());
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_D_GDS_RL_STDV", map); 
        }
        
        //출고지시참조 시 출고지시내역의 출고상태구분 변경(2:출고등록)
        if(("3".equals(paramMap.get("STDV_REF_DSC"))) && ("SM20".equals(paramMap.get("TR_BSN_DSC")))) 
        {
            egovLogger.debug("************ 출고지시상태구분수정 [SM1040] *********");
            paramMap.put("DLR_DNTT_STS_DSC", "2");
            se6010Service.Call_updateSe6010(paramMap);
        }
        
        result = paramMap.toString();
        return result;
    }
    
    //제품출고내역수정
    @Override
    public String updateSm1050(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 제품출고수정[SM1050] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        //리턴할 값 담아놓을 변수설정
        String result = "";
        
        //배송고객기본 수정
        if("2".equals(paramMap.get("DVY_OBJ_DSC")))
        {
            if("0".equals(paramMap.get("DVY_CUS_REG_SQNO")))
            {
                //채번을 위한 변수설정
                String s_CORP_C = paramMap.get("CORP_C").toString();
                String s_BZPL_C = paramMap.get("BZPL_C").toString();
                
                //오늘날짜구하기
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Calendar c1 = Calendar.getInstance();
                String strToday = sdf.format(c1.getTime());      
                
                //채번 서비스 호출(수주일련번호)
                String seqNo2 = commonService.getGvno(s_CORP_C, "TB_SE_M_DVY_CUS", s_BZPL_C, strToday, 1);
                
                egovLogger.debug("생성된 배송고객등록일련번호 채번: " + seqNo2);
                paramMap.put("DVY_CUS_REG_DT", strToday);
                paramMap.put("DVY_CUS_REG_SQNO", seqNo2);
            }
            egovLogger.debug("배송고객수정 TB_SE_M_DVY_CUS");
            egovLogger.debug("paramMap: " + paramMap.toString());
            sqlSession.insert("sfmes.sqlmap.tb.update_TB_SE_M_DVY_CUS", paramMap);
        }          
        
        //출고기본내역 수정
        egovLogger.debug("************ 제품출고기본수정[SM1050] *********");
        sqlSession.update("sfmes.sqlmap.tb.update_TB_SM_M_GDS_RL_STDV", paramMap); 
        
        //출고상세내역 수정
        for(Map<String, Object> map : paramList) 
        {
            egovLogger.debug("************ 제품출고상세수정[SM1050] *********");
            if(map.get("_status_").equals("+")) 
            {
                map.put("BZPL_C"   , paramMap.get("BZPL_C"));
                map.put("STDV_DT"  , paramMap.get("STDV_DT"));
                map.put("STDV_SQNO", paramMap.get("STDV_SQNO"));
                map.put("STDV_DSC" , paramMap.get("STDV_DSC"));                   

                egovLogger.debug("입력 map: " + map.toString());
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_D_GDS_RL_STDV", map); 
            }
            else 
            {
                egovLogger.debug("수정 map: " + map.toString());
                sqlSession.update("sfmes.sqlmap.tb.update_TB_SM_D_GDS_RL_STDV", map); 
            }
        }
        
        result = paramMap.toString();
        return result;
    }
    
    //출고상태구분 수정
    @Override
    public void Call_updateSm1050(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 출고상태구분수정[SM1050] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());

        egovLogger.debug("출고기본내역수정 TB_SM_M_GDS_RL_STDV");
        egovLogger.debug("paramMap: " + paramMap.toString());
        sqlSession.update("sfmes.sqlmap.sm.updateSm1050_M_STS", paramMap);
        
        //전표정상여부가 "Y"인 경우에만 출고상세내역수정
        if("Y".equals(paramMap.get("SLP_NML_YN")))
        {
            for(Map<String, Object> map : paramList) {
                map.put("BZPL_C"   , paramMap.get("BZPL_C")   );
                map.put("STDV_DT"  , paramMap.get("STDV_DT")  );
                map.put("STDV_DSC" , paramMap.get("STDV_DSC") );
                map.put("STDV_SQNO", paramMap.get("STDV_SQNO"));
                
                //출고참조매출등록 시 참조한 출고상세내역의 금액부분도 업데이트
                map.put("STDV_UPR", map.get("SL_UPR"));
                map.put("SPY_AM"  , map.get("SPY_AM"));
                map.put("VAT"     , map.get("VAT")   );
                map.put("STDV_AM" , map.get("SL_AM") );
                
                egovLogger.debug("제품출고상세내역수정 TB_SM_D_GDS_RL_STDV");
                egovLogger.debug("map: " + map);
                sqlSession.update("sfmes.sqlmap.sm.updateSm1050_D_STS", map);   
            }            
        }
    }    
    
    //제품출고내역삭제
    @Override
    public void deleteSm1050(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 제품출고내역삭제[SM1050] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());   
        
        //출고기본내역 수정(전표정상여부: "N")
        egovLogger.debug("출고기본내역수정 TB_SM_M_GDS_RL_STDV");        
        sqlSession.update("sfmes.sqlmap.sm.deleteSm1050_BY", paramMap); 
        
        //참조 시 입고내역 등록 후 해당 발주내역의 발주상태구분 변경(입고등록)
        if((! "0".equals(paramMap.get("STDV_REF_SQNO"))) && ("SM20".equals(paramMap.get("TR_BSN_DSC")))) 
        {
            egovLogger.debug("************ 발주내역수정 [BY2010] *********");
            paramMap.put("STDV_DT"    , null);
            paramMap.put("STDV_SQNO"  , 0);
            paramMap.put("ODR_STS_DSC", "1");
            //by2010Service.Call_updateBy2010(paramMap);
        }
    }    
}
