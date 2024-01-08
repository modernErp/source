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
import com.sfmes.pd.service.Pd1030Service;

/**
 * @Class Name : Pd1030ServiceImpl.java
 * @Description : BOM공정기로서등록 등록
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.10  김종관  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.08.
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Pd1030Service")
public class Pd1030ServiceImpl extends CmnAbstractServiceImpl implements Pd1030Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * BOM공정기로서등록 조회
     * @param paramMap 
     * @return 
     * @exception Exception
     */
    @Override
    public void insertPd1030(LinkedHashMap paramMap, List<Map<String, Object>> paramList, List<Map<String, Object>> paramList1) throws Exception {

        //공정코드 채번
        String seqNo = sqlSession.selectOne("sfmes.sqlmap.pd.prwseqNo_01", paramMap);
        egovLogger.debug("=====공정코드 채번" +seqNo);
        paramMap.put("PRW_C", seqNo);

        //공정기록서기본 등록
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_M_PRW_REC",paramMap);
        

        //int ptinsqNo = 0; // 생산일련번호
        for(Map<String, Object> map : paramList){
            //공정기록서생산내역 저장
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("PRW_C", paramMap.get("PRW_C"));
            //ptinsqNo ++;
            //map.put("SQNO", ptinsqNo);
            
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_PRW_REC_PTIN",map);
            
        }

        //int pdsqNo = 0; // 투입일련번호
        for(Map<String, Object> map : paramList1){
            //BOM상세내역저장
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("PRW_C", paramMap.get("PRW_C"));
            //pdsqNo ++;
            //map.put("SQNO", pdsqNo);
            
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_PRW_REC_PD_GDS",map);
        }

        //이력저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_L_PRW_REC_M", paramMap);
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_L_PRW_REC_PTIN", paramMap);
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_L_PRW_REC_PD_GDS", paramMap);
    }
    
    @Override
    public void updatePd1030(LinkedHashMap paramMap, List<Map<String, Object>> paramList, List<Map<String, Object>> paramList1) throws Exception {
        // 공정기록서 수정
        sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_M_PRW_REC",paramMap);
        
        // 공정기록서_투입내역 수정
        for(Map<String, Object> map : paramList){
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("PRW_C", paramMap.get("PRW_C"));
            
            if(map.get("_status_").equals("+")){
                
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_PRW_REC_PTIN",map);
                
            } else if(map.get("_status_").equals("*")){
                
                sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_PRW_REC_PTIN",map);
            }
        }
        
        // 공정기록서_생산내역 수정
        for(Map<String, Object> map : paramList1){
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("PRW_C", paramMap.get("PRW_C"));
            
            if(map.get("_status_").equals("+")){
                
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_PRW_REC_PD_GDS",map);
                
                
            } else if(map.get("_status_").equals("*")){
                
                sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_PRW_REC_PD_GDS",map);
            }
        }
       
        //이력저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_L_PRW_REC_M", paramMap);
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_L_PRW_REC_PTIN", paramMap);
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_L_PRW_REC_PD_GDS", paramMap);        
    }

    @Override
    public List<?> selectPrwPopupList(LinkedHashMap paramMap) throws Exception {
        // 공정기록서 POPUP
        return sqlSession.selectList("sfmes.sqlmap.pd.prwPopup_01", paramMap);
    }

    @Override
    public List<?> selectPrwRecList(LinkedHashMap paramMap) throws Exception {
        // 공정기록서 조회
        egovLogger.debug(":::::[paramMap]:::::"+paramMap);
        return sqlSession.selectList("sfmes.sqlmap.pd.select_TB_PD_M_PRW_REC_01", paramMap);
    }

    @Override
    public List<?> selectPrwPdGdsList(LinkedHashMap paramMap) throws Exception {
        // 공정기록서_생산내역 조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_TB_PD_D_PRW_REC_PD_GDS_01", paramMap);
    }

    @Override
    public List<?> selectPrwPtinList(LinkedHashMap paramMap) throws Exception {
        // 공정기록서_투입내역 조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_TB_PD_D_PRW_REC_PTIN_01", paramMap);
    }
    
    @Override
    public List<?> selectPrwPopupList_PdGds(LinkedHashMap paramMap) throws Exception {
        // 공정기록서 POPUP_생산물품
        return sqlSession.selectList("sfmes.sqlmap.pd.prwPopup_02", paramMap);
    }

    @Override
    public List<?> selectPrwPopupList_PtinGds(LinkedHashMap paramMap) throws Exception {
        // 공정기록서 POPUP_투입물품
        return sqlSession.selectList("sfmes.sqlmap.pd.prwPopup_03", paramMap);
    }
}