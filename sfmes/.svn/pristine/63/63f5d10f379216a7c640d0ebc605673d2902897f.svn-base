package com.sfmes.pd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.pd.service.Pd2055Service;
import com.sfmes.sm.service.Sm1020Service;

/**
* @Class Name : Pd2055ServiceImpl.java
* @Description : Pd2055Service Class
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

@Service("Pd2055Service")
public class Pd2055ServiceImpl extends CmnAbstractServiceImpl implements Pd2055Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Autowired
    private Sm1020Service sm1020Service;
    
    @Override
    public void updatePd2055(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        //착업지시출고의뢰내역 삭제여부변경        
        String  t_date = "";
        String t_sqno = "";
        
        LinkedHashMap             if_param_Map  = null;
        List<Map<String, Object>> if_param_List = null;
        
        egovLogger.debug(":::::[paramMap]:::::"+paramMap);

        if_param_Map = new LinkedHashMap();
        if_param_List = new ArrayList<Map<String, Object>>();

        for(Map<String, Object> t_map : paramList){
             
            egovLogger.debug(":::::[01t_map]:::::"+t_map);
            if_param_Map.put("TR_SQNO", t_map.get("TR_SQNO"));

            // 날자와 일련번호가 다르면 삭제interface를 호출
            if((!t_map.get("DLR_RQT_DT").equals(t_date)) || (!t_map.get("DLR_RQT_SQNO").equals(t_sqno)) ) {
                                
                if(!("").equals(t_date)) {
                    egovLogger.debug(":::::[t_date]:::::"+t_date);
                    egovLogger.debug(":::::[t_sqno]:::::"+t_sqno); 
                    
                    int trsqno_cnt = sqlSession.selectOne("sfmes.sqlmap.pd.select_trsqno_cnt", if_param_Map);
                    t_map.put("MFC_WK_STS_C"   , "01");
                    if(trsqno_cnt > 0) {
                        //작업지시 상태값 변경
                        egovLogger.debug(":::::[작업상태변경01]:::::"+t_map);
                        sqlSession.update("sfmes.sqlmap.pd.PRW_C_change", t_map);
                    } else {
                        egovLogger.debug(":::::[작업상태변경02]:::::"+t_map);
                        sqlSession.update("sfmes.sqlmap.pd.BOM_C_change", t_map);
                    }
                    
                    egovLogger.debug(":::::[if_param_Map_01]:::::"+if_param_Map);
                    egovLogger.debug(":::::[if_param_List_01]:::::"+if_param_List);
                    
                    // interface 호출
                    egovLogger.debug(":::::[PD2025작업지시에서 SM1020임플호출]:::::");
                    sm1020Service.updateSm1020(if_param_Map, if_param_List);
                    egovLogger.debug(":::::[SM1020테이블확인하세요OK]:::::");

                    // 등록할 구조체 생성
                    if_param_Map = new LinkedHashMap();
                    if_param_List = new ArrayList<Map<String, Object>>();
                }

                // 출고의뢰일자를 임시변수에 담는다.
                t_date = t_map.get("DLR_RQT_DT").toString();
                t_sqno = t_map.get("DLR_RQT_SQNO").toString();

                // Interface 에 사용할 Map을 만든다.
                if_param_Map.put("CORP_C"         , paramMap.get("CORP_C"));
                if_param_Map.put("BZPL_C"         , paramMap.get("BZPL_C"));
                if_param_Map.put("DLR_RQT_DT"     , t_map.get("DLR_RQT_DT"));
                if_param_Map.put("DLR_RQT_SQNO"   , t_map.get("DLR_RQT_SQNO"));
                if_param_Map.put("DLR_RQT_STS_DSC", t_map.get("DLR_RQT_STS_DSC"));
                if_param_Map.put("DLR_RQT_REF_DSC", t_map.get("DLR_RQT_REF_DSC"));
                if_param_Map.put("RMK_CNTN"       , t_map.get("RMK_CNTN_M"));
                if_param_Map.put("DEL_YN"         , "Y");
                if_param_Map.put("GUSRID"         , paramMap.get("GUSRID"));
            }
            
            // row status 를 삭제로 변경            
            t_map.put("_status_", "-");
            t_map.put("DEL_YN"  , "Y");
            //if_param_Map.put("MFC_WK_STS_C", "01");
            
            egovLogger.debug(":::::[02t_map]:::::"+t_map);
            
            if_param_Map.put("TR_SQNO", t_map.get("TR_SQNO"));
            if_param_Map.put("MFC_WK_STS_C", "01");
            
            if(("1").equals(if_param_Map.get("DLR_RQT_REF_DSC"))) {
                int trsqno_cnt = sqlSession.selectOne("sfmes.sqlmap.pd.select_trsqno_cnt", if_param_Map);
                if(trsqno_cnt > 0) {
                    //작업지시 상태값 변경
                    egovLogger.debug(":::::[작업상태변경03]:::::"+if_param_Map);
                    sqlSession.update("sfmes.sqlmap.pd.PRW_C_change", if_param_Map);
                } else {
                    egovLogger.debug(":::::[작업상태변경04]:::::"+if_param_Map);
                    sqlSession.update("sfmes.sqlmap.pd.BOM_C_change", if_param_Map);
                }
            }
            
            // Map을 List에 Add
            if_param_List.add(t_map);
            
        }
        
        egovLogger.debug(":::::[if_param_Map_01]:::::"+if_param_Map);
        egovLogger.debug(":::::[if_param_List_01]:::::"+if_param_List);
        
        // interface 호출
        egovLogger.debug(":::::[PD2025작업지시에서 SM1020임플호출]:::::");
        sm1020Service.updateSm1020(if_param_Map, if_param_List);
        egovLogger.debug(":::::[SM1020테이블확인하세요OK]:::::");
    }

    @Override
    public List<?> selectPd2055List_01(LinkedHashMap paramMap) throws Exception {
        //착업지시출고의뢰내역 조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2055List01", paramMap);
    }
    
}
