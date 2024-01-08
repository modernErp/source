package com.sfmes.pd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.pd.service.IF_PD_SM_HST_MNGService;
import com.sfmes.co.service.infCoSraNoHistService;

/**
* @Class Name : IF_PD_SM_HST_MNGServiceImpl.java
* @Description : IF_PD_SM_HST_MNGService Class
* @Modification Information
* @
* @  수정일             수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.09.08   김수민            최초생성
* @ 2020.09.15   장경석            축산물이력조회 추가
*
* @author (주)모든솔루션
* @since 2020.08.24
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("IF_PD_SM_HST_MNGService")
public class IF_PD_SM_HST_MNGServiceImpl extends CmnAbstractServiceImpl implements IF_PD_SM_HST_MNGService{

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 20200915 JKS 추가
    // 축산물이력번호 조회 관련 Service 호출
    @Autowired
    private infCoSraNoHistService infCoSraNoHistService;

    @Override
    public void if_HST_MNG_insert(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        
        // 축산물이력결과 map
        LinkedHashMap<String, Object> SrcMap = new LinkedHashMap<>();
        Map<String, Object> Sra_map = new HashMap<String, Object>();
        
        // 입고물품이력등록        
        for(Map<String, Object> map : paramList){
            if(!"".equals(map.get("GDS_HST_NO"))){               
                map.put("MFC_DSC", paramMap.get("MFC_DSC"));
                
                // 20200915 JKS 추가
                // 20201006 JKS 변경 - 이력관리구분코드에 따른 분기 추가
                String sra_hst_amn_dsc = sqlSession.selectOne("sfmes.sqlmap.pd.select_IF_PD_SM_HST_MNG_04", map);
                egovLogger.debug("조회결과 (sra_hst_amn_dsc) : " + sra_hst_amn_dsc);
                
                if("0".equals(sra_hst_amn_dsc)) {
                    Sra_map.put("resultCode", "00");
                    Sra_map.put("resultMsg", "OK - 해당없음");
                } else if("3".equals(sra_hst_amn_dsc)) {
                    egovLogger.debug("조회결과 (resultMsg) : 수입육이력관리");
                    
                    Sra_map = infCoSraNoHistService.infCoMeatwatchHistselect((LinkedHashMap<String, Object>) map);
                    egovLogger.debug("조회결과 (Sra_map) : " + Sra_map);

                    if (!"00".equals(Sra_map.get("resultCode"))) {
                        throw infoException("수입축산물이력(묶음)번호 ["+map.get("GDS_HST_NO")+"]가 조회중 오류가 발생했습니다.");
                    }
                } else {
                    // 축산물이력조회 - 공공데이타 OpenApi 이용
                    Sra_map = infCoSraNoHistService.infCoSraNoHistselect((LinkedHashMap<String, Object>) map);
                    egovLogger.debug("조회결과 (Sra_map) : " + Sra_map);

                    if (!"00".equals(Sra_map.get("resultCode"))) {
                        throw infoException("축산물이력(묶음)번호 ["+map.get("GDS_HST_NO")+"]가 조회중 오류가 발생했습니다.");
                    }
                }
                
            }
        }
    }

    @Override
    public void if_HST_MNG_delete(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 축산물이력번호관리기본관리 또는 묶음번호 자료 삭제[deleteHST_MNG] *********");       
        // 입고물품이력삭제여부변경
        paramMap.put("STR_DT"  , paramMap.get("STDV_DT"));
        paramMap.put("STR_SQNO", paramMap.get("STDV_SQNO"));
        paramMap.put("STDV_DSC", "I");
            
        egovLogger.debug("paramMap: "+paramMap.toString());
        
        // 대상이 묶음번호인지 확인
        List<Map<String, Object>> sm1010_List = new ArrayList<Map<String, Object>>();
        
        String sub_gds_hss_no = "";
        String rst_msg = "";
        int CNT = 0;
        
        sm1010_List = sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SM_D_GDS_RL_STDV", paramMap);

        // 입고물품이력등록        
        for(Map<String, Object> map : sm1010_List) {
            if(!"".equals(map.get("GDS_HST_NO"))) {
                sub_gds_hss_no = map.get("GDS_HST_NO").toString().substring(0, 1);
                
                egovLogger.debug("map.sub_gds_hss_no  ::: "+sub_gds_hss_no);
                
                map.put("CORP_C"    , paramMap.get("CORP_C"));
                map.put("BZPL_C"    , paramMap.get("BZPL_C"));
                map.put("STR_DT"    , paramMap.get("RLTR_DT"));
                map.put("SRA_HST_NO", map.get("GDS_HST_NO"));
                map.put("STDV_DSC"  , paramMap.get("WHSE_C"));
                map.put("DLR_YN"    , "Y");
                
                if("L".equals(sub_gds_hss_no)) {
                    // 묶음번호인가 
                    map.put("STR_GBN_DSC", "BUDL_NO");                    

                    egovLogger.debug("select_IF_PD_SM_HST_MNG_validCheck_02_1  CHECK !!!");
                    
                    //묶음번호 출고된 이력이 있는지 체크
                    rst_msg = sqlSession.selectOne("sfmes.sqlmap.pd.select_IF_PD_SM_HST_MNG_validCheck_02_1", paramMap);

                    if(!"OK".equals(rst_msg)) {
                        throw infoException(rst_msg);
                    }
                    

                    // 신고 완료된 묶음 이력번호 체크
                    egovLogger.debug("select_IF_PD_SM_HST_MNG_validCheck_03_1  CHECK!!!");
                    CNT = sqlSession.selectOne("sfmes.sqlmap.pd.select_IF_PD_SM_HST_MNG_validCheck_03_1", paramMap);

                    if(0 < CNT) {
                        throw infoException("신고완료된 축산물이력번호가 존재하여 삭제할 수 없습니다.");            
                    }            
                    

                    egovLogger.debug("######################################"); 
                    egovLogger.debug("### paramMap  =>"+paramMap); 
                    egovLogger.debug("######################################");

                    // 묶음상세의 이력번호가 이력관리 기본에 존재하는가 
                    CNT = sqlSession.selectOne("sfmes.sqlmap.pd.select_update_IF_PD_SM_HST_MNG_02", paramMap);

                    if(0 < CNT) {
                        // 이력관리기본 마스터 자료 완전 삭제
                        sqlSession.delete("sfmes.sqlmap.pd.delete_IF_PD_SM_HST_MNG_03", paramMap);

                        // 묶음자료 상세 완전 삭제
                        sqlSession.delete("sfmes.sqlmap.pd.delete_IF_PD_SM_HST_MNG_01", paramMap);
                        
                        // 묶음자료 기본 완전 삭제
                        sqlSession.delete("sfmes.sqlmap.pd.delete_IF_PD_SM_HST_MNG_02", paramMap);
                        
                    }            
                } else {
                    // 개체이력번호인가 
                    map.put("STR_GBN_DSC", "HST_NO");

                    // 개체가 묶음번호로 신고되거나 출고된 이력이 있는지 체크
                    rst_msg = sqlSession.selectOne("sfmes.sqlmap.pd.select_IF_PD_SM_HST_MNG_validCheck_02", paramMap);
                
                    // 신고 완료되고 묶음번호로 묶음 이력번호 체크
                    CNT = sqlSession.selectOne("sfmes.sqlmap.pd.select_IF_PD_SM_HST_MNG_validCheck_03", paramMap);

                    if(!"OK".equals(rst_msg)) {
                        throw infoException(rst_msg);
                    }
                    egovLogger.debug("select_IF_PD_SM_HST_MNG_validCheck_02  OK!!!");               
                    
                    if(0 < CNT) {
                        throw infoException("신고완료된 축산물이력번호가 존재하여 삭제할 수 없습니다.");            
                    }            
                    egovLogger.debug("select_IF_PD_SM_HST_MNG_validCheck_03  OK!!!");

                    // 이력관리 기본에 존재하는가 
                    CNT = sqlSession.selectOne("sfmes.sqlmap.pd.select_update_IF_PD_SM_HST_MNG_02", paramMap);

                    egovLogger.debug("CNT  ===>"+CNT);
                    if(0 < CNT) {
                        sqlSession.insert("sfmes.sqlmap.pd.delete_IF_PD_SM_HST_MNG_05", paramMap);
                    }            
                }
                
            }            
        }        
        
    }

    /**
     * 물품이력출고정보등록
     */
    @Override
    public LinkedHashMap<String, Object> if_HST_MNG_Dlr_insert(LinkedHashMap paramMap) throws Exception {
        // Result Map 선언
        LinkedHashMap<String, Object> Sra_map = new LinkedHashMap<>();        
        
        // 출고물품이력등록        
        if(!"".equals(paramMap.get("GDS_HST_NO"))){
            // 이력번호조회 List
            List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
            
            String sra_hst_amn_dsc = (String) paramMap.get("HST_AMN_DSC");
            egovLogger.debug("조회결과 (sra_hst_amn_dsc) : " + paramMap.get("HST_AMN_DSC") + "     " + sra_hst_amn_dsc);
            
            if("0".equals(sra_hst_amn_dsc)) {
                Sra_map.put("resultCode", "99");
                Sra_map.put("resultMsg", "Error - 이력번호관리구분코드가 해당없음 이므로 이력번호는 공란이여야 합니다.");
            } else {
                int i_hst_no_cnt = 0;
                
                String sub_gds_hst_no = ((String) paramMap.get("BUDL_NO")).substring(0,2);
                egovLogger.debug("조회결과 (sub_gds_hst_no) : " + paramMap.get("BUDL_NO") + "     " + sub_gds_hst_no);
                
                //paramMap.put("GDS_HST_NO", paramMap.get("SRA_HST_NO"));
                if(!NumberUtils.isDigits(sub_gds_hst_no)) {
                    
                    // 묶음번호로 입고정보 찾기
                    i_hst_no_cnt = sqlSession.selectOne("select_IF_PD_SM_HST_MNG_validCheck_07", paramMap);

                    if(i_hst_no_cnt == 0) {
                        Sra_map.put("resultCode", "99");
                        Sra_map.put("resultMsg", "Error - 입력하신 축산물이력번호(묶음번호)가 존재하지 않습니다.");
                        throw infoException("표시이력번호(묶음번호)를 확인 해 주세오(7).========>");
                    }
                    
                    // 축산물이력번호대상 정보조회
                    itemList = sqlSession.selectList("select_STRINFO_HST_NO_01", paramMap);
                } else {
                    // 물품이력번호로 입고정보 찾기
                    i_hst_no_cnt = sqlSession.selectOne("select_IF_PD_SM_HST_MNG_validCheck_06", paramMap);
                    
                    if(i_hst_no_cnt == 0) {
                        Sra_map.put("resultCode", "99");
                        Sra_map.put("resultMsg", "Error - 입력하신 축산물이력번호가 존재하지 않습니다.");
                        throw infoException("표시(축산물)이력번호를 확인 해 주세오(6).========>");
                    }
                    
                    // 축산물이력번호대상 정보조회
                    itemList = sqlSession.selectList("select_STRINFO_HST_NO_02", paramMap);
                    
                    egovLogger.debug("조회결과 (itemList) : " + itemList);
                }
                            
                for(Map<String, Object> t_map : itemList) {
                    // 출고자정보 put
                    t_map.putAll(paramMap);
                    egovLogger.debug("조회결과 (t_map) : " + t_map);
                    
                    // 축산물이력번호 I/F (출고)
                    // 출고시 출고된 이력관리기본 마스트테이블 자료를 UPDATE
                    sqlSession.update("sfmes.sqlmap.pd.update_IF_PD_SM_HST_MNG_01", t_map);

                    // 출고시 출고된 묶음번호이력관리기본 마스트테이블 자료를 UPDATE
                    sqlSession.update("sfmes.sqlmap.pd.update_IF_PD_SM_HST_MNG_BUDL", t_map);

                    Sra_map.clear();
                    Sra_map.put("resultCode", "00");
                    Sra_map.put("resultMsg", "SERVICE OK");
                }
            }

        }
            
        return Sra_map;
    }

    /**
     * 물품이력출고정보삭제
     */
    @Override
    public LinkedHashMap<String, Object> if_HST_MNG_Dlr_delete(LinkedHashMap paramMap) throws Exception {
        // Result Map 선언
        LinkedHashMap<String, Object> Sra_map = new LinkedHashMap<>();

        // 출고물품이력등록        
        if(!"".equals(paramMap.get("GDS_HST_NO"))){
            // 이력번호조회 List
            List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
            
            String sra_hst_amn_dsc = (String) paramMap.get("HST_AMN_DSC");
            egovLogger.debug("조회결과 (sra_hst_amn_dsc) : " + paramMap.get("HST_AMN_DSC") + "     " + sra_hst_amn_dsc);
            
            if("0".equals(sra_hst_amn_dsc)) {
                Sra_map.put("resultCode", "99");
                Sra_map.put("resultMsg", "Error - 이력번호관리구분코드가 해당없음 이므로 이력번호는 공란이여야 합니다.");
            } else {
                int i_hst_no_cnt = 0;
                
                String sub_gds_hst_no = ((String) paramMap.get("GDS_HST_NO")).substring(0,2);
                
                if("L0".equals(sub_gds_hst_no) || "L1".equals(sub_gds_hst_no)) {
                    // 묶음번호로 입고정보 찾기
                    i_hst_no_cnt = sqlSession.selectOne("select_IF_PD_SM_HST_MNG_validCheck_06", paramMap);

                    if(i_hst_no_cnt == 0) {
                        Sra_map.put("resultCode", "99");
                        Sra_map.put("resultMsg", "Error - 입력하신 축산물이력번호(묶음번호)가 존재하지 않습니다.");
                        throw infoException("표시이력번호(묶음번호)를 확인 해 주세오.========>");
                    }
                    
                    // 축산물이력번호대상 정보조회
                    itemList = sqlSession.selectList("select_STRINFO_HST_NO_01", paramMap);
                } else {
                    // 물품이력번호로 입고정보 찾기
                    i_hst_no_cnt = sqlSession.selectOne("select_IF_PD_SM_HST_MNG_validCheck_07", paramMap);
                    
                    if(i_hst_no_cnt == 0) {
                        Sra_map.put("resultCode", "99");
                        Sra_map.put("resultMsg", "Error - 입력하신 축산물이력번호가 존재하지 않습니다.");
                        throw infoException("표시이력번호를 확인 해 주세오.========>");
                    }
                    
                    // 축산물이력번호대상 정보조회
                    itemList = sqlSession.selectList("select_STRINFO_HST_NO_02", paramMap);
                }
                         
                // 출고중량 변수 선언
                float f_dlr_wt = 0;
                for(Map<String, Object> t_map : itemList) {
                    // 출고자정보/출고중량 put
                    t_map.putAll(paramMap);
                    egovLogger.debug("조회결과 (t_map) : " + t_map);
                    
                    // 축산물이력번호 I/F (출고)
                    // 출고시 출고된 이력관리기본 마스트테이블 자료를 UPDATE
                    sqlSession.insert("sfmes.sqlmap.pd.update_IF_PD_SM_HST_MNG_01", t_map);

                    // 출고시 출고된 묶음번호이력관리기본 마스트테이블 자료를 UPDATE
                    sqlSession.insert("sfmes.sqlmap.pd.update_IF_PD_SM_HST_MNG_BUDL", t_map);

                    Sra_map.clear();
                    Sra_map.put("resultCode", "00");
                    Sra_map.put("resultMsg", "SERVICE OK");
                }
            }

        }
            
        return Sra_map;
    }
}
