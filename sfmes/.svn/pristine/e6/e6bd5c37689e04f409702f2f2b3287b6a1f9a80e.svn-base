package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd2510Service;

/**
* @Class Name : Pd2510ServiceImpl.java
* @Description : Pd2510Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.13   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.13
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2510Service")
public class Pd2510ServiceImpl extends CmnAbstractServiceImpl implements Pd2510Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;

    
    @Override
    public void insertPd2510(LinkedHashMap paramMap, List<Map<String, Object>> paramList01,List<Map<String, Object>> paramList02) throws Exception {
        //공정기록서 기반 작업지시등록
        
        //지시일련번호 채번
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_DNTT_DT = paramMap.get("DNTT_DT").toString();
        String seqNo = commonService.getGvno(s_CORP_C,"TB_PD_M_WK_DNTT",s_BZPL_C, s_DNTT_DT, 1);
        egovLogger.debug("=====작업지시일련번호 채번" +seqNo);
        paramMap.put("DNTT_SQNO", seqNo);
        paramMap.put("DNTT_DNO", "00");
        
        //거래업무구분코드(P10작업지시)
        paramMap.put("TR_BSN_DSC", "PD10");
        //거래일련번호
        String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
        egovLogger.debug("=====작업지시거래일련번호 채번" +tr_seqNo);
        paramMap.put("TR_SQNO", tr_seqNo);
        
        egovLogger.debug("==============================" +paramMap.get("TR_SQNO"));
        egovLogger.debug("==============================" +paramMap.get("TR_BSN_DSC"));
        
        //기본정보저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_M_WK_DNTT",paramMap);
        
        for(Map<String, Object> map : paramList01){
           //공정기록서 기반 작업지시 제품상세저장
           map.put("CORP_C", paramMap.get("CORP_C"));
           map.put("BZPL_C", paramMap.get("BZPL_C"));
           map.put("DNTT_DT", paramMap.get("DNTT_DT"));
           map.put("DNTT_SQNO", paramMap.get("DNTT_SQNO"));
           map.put("DNTT_DNO", paramMap.get("DNTT_DNO"));
           
           //제품상세저장
           sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_DNTT_MFS",map);
        }
        
        for(Map<String, Object> map : paramList02){
            //공정기록서 기반 작업지시 투입상세저장
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("DNTT_DT", paramMap.get("DNTT_DT"));
            map.put("DNTT_SQNO", paramMap.get("DNTT_SQNO"));
            map.put("DNTT_DNO", paramMap.get("DNTT_DNO"));
            
            //물품이력정합성체크
            if(!"".equals(map.get("GDS_HST_NO"))){
                String gds_no = ((String) map.get("GDS_HST_NO")).substring(0,1);
                if("L".equals(gds_no)) {
                    map.put("GDS_NO", "");
                } else {
                    map.put("GDS_NO", gds_no);
                }
                egovLogger.debug("==============이력번호비교"+map.get("GDS_NO"));
                //물품이력번호 ValidCheck
                String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.pd.selectPd2010_HSTNO_ValidCheck", map);
                if(!"OK".equals(resultMsg)) {
                    throw infoException(resultMsg);
                }
            }
                
            //투입상세저장
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_DNTT_MTRL",map);
         }
    }

    @Override
    public void updatePd2510(LinkedHashMap paramMap, List<Map<String, Object>> paramList01, List<Map<String, Object>> paramList02) throws Exception {
        // 공정기록서 기반 작업지시변경
        if("Y".equals(paramMap.get("DEL_YN"))) {
            //작업지시 삭제여부 Y 변경
            paramMap.put("MFC_WK_STS_C", "99");
            sqlSession.update("sfmes.sqlmap.pd.update_delyn_TB_PD_M_WK_DNTT",paramMap);
            for(Map<String, Object> map : paramList01){
                //제품상세저장
                map.put("CORP_C", paramMap.get("CORP_C"));
                map.put("BZPL_C", paramMap.get("BZPL_C"));
                map.put("DNTT_DT", paramMap.get("DNTT_DT"));
                map.put("DNTT_SQNO", paramMap.get("DNTT_SQNO"));
                map.put("DNTT_DNO",paramMap.get("DNTT_DNO")); 
                egovLogger.debug("삭제==================================="+map);
                sqlSession.update("sfmes.sqlmap.pd.update_delyn_TB_PD_D_WK_DNTT_MFS",map);
            }
            
            for(Map<String, Object> map : paramList02){
                //투입자재상세저장
                map.put("CORP_C", paramMap.get("CORP_C"));
                map.put("BZPL_C", paramMap.get("BZPL_C"));
                map.put("DNTT_DT", paramMap.get("DNTT_DT"));
                map.put("DNTT_SQNO", paramMap.get("DNTT_SQNO"));
                map.put("DNTT_DNO",paramMap.get("DNTT_DNO")); 
                egovLogger.debug("삭제==================================="+map);
                sqlSession.update("sfmes.sqlmap.pd.update_delyn_TB_PD_D_WK_DNTT_MTRL",map);
            }
            
        } else {
            //기본정보수정
            sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_M_WK_DNTT",paramMap);
            
            for(Map<String, Object> map : paramList01){
                //제품상세저장
                map.put("CORP_C", paramMap.get("CORP_C"));
                map.put("BZPL_C", paramMap.get("BZPL_C"));
                map.put("DNTT_DT", paramMap.get("DNTT_DT"));
                map.put("DNTT_SQNO", paramMap.get("DNTT_SQNO"));
                map.put("DNTT_DNO","00"); 
                
                if(map.get("_status_").equals("*")){
                    
                    sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_WK_DNTT_MFS",map);
                    
                }
            }
            
            for(Map<String, Object> map : paramList02){
                //투입자재상세저장
                map.put("CORP_C", paramMap.get("CORP_C"));
                map.put("BZPL_C", paramMap.get("BZPL_C"));
                map.put("DNTT_DT", paramMap.get("DNTT_DT"));
                map.put("DNTT_SQNO", paramMap.get("DNTT_SQNO"));
                map.put("DNTT_DNO","00"); 
                
                if(map.get("_status_").equals("*")){
                    
                    sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_WK_DNTT_MTRL",map);
                    
                }
            }
            
        }
        
    }

    @Override
    public List<?> selectPd2510List_01(LinkedHashMap paramMap) throws Exception {
        // 공정기록서작업지시 기본조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select2510_List_01",paramMap);
    }

    @Override
    public List<?> selectPd2510List_02(LinkedHashMap paramMap) throws Exception {
        //공정기록서작업지시 제품상세조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select2510_List_02",paramMap);
    }

    @Override
    public List<?> selectPd2510List_03(LinkedHashMap paramMap) throws Exception {
        //공정기록서작업지시 투입상세조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select2510_List_03",paramMap);
    }

    @Override
    public List<?> selectPd2510List_04(LinkedHashMap paramMap) throws Exception {
        // 공정기록서 제품상세(저장전)
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2510_prw_pd",paramMap);
    }

    @Override
    public List<?> selectPd2510List_05(LinkedHashMap paramMap) throws Exception {
        // 공정기록서 투입상세(저장전)
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2510_prw_ptin",paramMap);
    }

}
