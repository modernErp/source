package com.sfmes.pd.service.impl;

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
import com.sfmes.pd.service.Pd2050Service;
import com.sfmes.sm.service.Sm1020Service;
import com.sfmes.se.service.Se2010Service;

/**
 * @Class Name : Pd2050ServiceImpl.java
 * @Description : 출고의뢰 등록
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.09.10  김종관  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Pd2050Service")
public class Pd2050ServiceImpl extends CmnAbstractServiceImpl implements Pd2050Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Resource(name = "CommonService")
    private CommonService commonService;

    @Autowired
    private Sm1020Service sm1020Service;

    @Autowired
    private Se2010Service se2010Service;

    //출고의뢰 등록
    @Override
    public void insertPd2050(LinkedHashMap paramMap, List<Map<String, Object>> paramList, List<Map<String, Object>> paramList1) throws Exception {
        paramMap.put("DLR_RQT_REF_DSC","1");
        List<Map<String, Object>> if_param_List_01 = new ArrayList<Map<String, Object>>();
        for(Map<String, Object> map : paramList){
            //출고의뢰내역 중 출고의뢰수량이 '0' 이상인 물품만 출고의뢰등록
            double ptin = Double.parseDouble((String) map.get("PTIN_DNTT_QT")) ;
            if(ptin > 0) {
                //Map을 List에 Add 
                LinkedHashMap if_list_map = new LinkedHashMap();
                if_list_map.putAll(map);
                if_list_map.put("DEPT_C", map.get("PD_LINE_C"));
                
                // i/f 구분자 Add (2020-11-20 JKS 추가)
                if_list_map.put("DLR_RQT_QT", map.get("PTIN_DNTT_QT"));
                if_list_map.put("TR_UNT_C", map.get("PTIN_UNT_C"));
                if_list_map.put("INF_YN", "Y");
                
                if_param_List_01.add(if_list_map);
            }
        }
        egovLogger.debug(":::::[PD2025작업지시에서 SM1020임플호출]:::::");
        sm1020Service.saveSm1020(paramMap, if_param_List_01);
        egovLogger.debug(":::::[SM1020테이블확인하세요OK]:::::");

        // 작업지시 상태값 변경
        for(Map<String, Object> map : paramList){
            
            map.put("MFC_WK_STS_C", "02");
            
            //작업지시 상태값 변경
            //sqlSession.update("sfmes.sqlmap.pd.TB_PD_D_WK_DNTT_MFS_change_2021",map);
            int trsqno_cnt = sqlSession.selectOne("sfmes.sqlmap.pd.select_trsqno_cnt", map);
            egovLogger.debug("================"+trsqno_cnt);
            if(trsqno_cnt > 0) {
                //작업지시 상태값 변경
                egovLogger.debug(":::::[작업상태변경03]:::::"+map);
                sqlSession.update("sfmes.sqlmap.pd.PRW_C_change", map);
            } else {
                egovLogger.debug(":::::[작업상태변경04]:::::"+map);
                sqlSession.update("sfmes.sqlmap.pd.BOM_C_change", map);
            }

            //매출_수주기본에 지시일자, 지시일련번호 UPDATE
            //egovLogger.debug(":::::[PD2020작업지시에서 SE2010임플호출]:::::");  
            //map.put("DLR_DNTT_DT", map.get("DNTT_DT"));
            //map.put("DLR_DNTT_SQNO", map.get("DNTT_SQNO"));
 
            //se2010Service.Call_updateSe2010_DLR_DNTT(paramList);
        }
    }
    
    // 작업지시 상태값 변경
    @Override
    public void updatePd2050(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("updatePd2050  paramMap  : "  + paramMap);
        
        for(Map<String, Object> map : paramList){

            // 출고의뢰정보 추가
            map.put("DLR_RQT_DT"  , paramMap.get("STDV_REF_DT"));
            map.put("DLR_RQT_SQNO", paramMap.get("STDV_REF_SQNO"));
            map.put("MFC_WK_STS_C", paramMap.get("MFC_WK_STS_C"));
            
            
            egovLogger.debug("updatePd2050  map  : "  + map);
            
            int trsqno_cnt = sqlSession.selectOne("sfmes.sqlmap.pd.DLR_RQT_select_trsqno_cnt", map);
            egovLogger.debug("================"+trsqno_cnt);
            
            if(trsqno_cnt > 0) {
                //작업지시 상태값 변경
                egovLogger.debug(":::::[작업상태변경03]:::::"+map);
                sqlSession.update("sfmes.sqlmap.pd.DLR_RQT_PRW_C_change", map);
            } else {
                egovLogger.debug(":::::[작업상태변경04]:::::"+map);
                sqlSession.update("sfmes.sqlmap.pd.DLR_RQT_BOM_C_change", map);
            }
        }
    }

    
    @Override
    public List<?> selectPd2050DnttList_01(LinkedHashMap paramMap) throws Exception {
        //작업지시내역 조회
        egovLogger.debug("==============="+paramMap.get("PRW_DSC"));
        if("1".equals(paramMap.get("PRW_DSC"))) {
            egovLogger.debug("===============1");
            //BOM기준
            return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2050_Dntt_01", paramMap);
        } else {
            egovLogger.debug("===============2");
            //공정기록서기준
            return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2050_Dntt_02", paramMap);
        }
    }

    @Override
    public List<?> selectPd2050MtrlList_01(LinkedHashMap paramMap) throws Exception {
        // 출고의뢰내역 조회
        egovLogger.debug("==============="+paramMap.get("PRW_DSC"));
        if("1".equals(paramMap.get("PRW_DSC"))) {
            egovLogger.debug("===============1");
            //BOM기준
            return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2050_Mtrl_01", paramMap);
        } else {
            egovLogger.debug("===============2");
            //공정기록서기준
            return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2050_Mtrl_02", paramMap);
        }
    }
}