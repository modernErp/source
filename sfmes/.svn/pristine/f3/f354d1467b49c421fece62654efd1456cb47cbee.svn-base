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
import com.sfmes.pd.service.Pd2010Service;
import com.sfmes.sm.service.Sm1020Service;

/**
* @Class Name : Pd2010ServiceImpl.java
* @Description : Pd2010Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.08.04   김수민     최초생성
* @ 2021.01.13   이수빈     작업지시내역출력조회, 원재료입고(예정)내역 추가
*
* @author (주)모든솔루션
* @since 2020.08.04
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd2010Service")
public class Pd2010ServiceImpl extends CmnAbstractServiceImpl implements Pd2010Service{

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    //20200818생산투입출고의뢰등록(SM1020)서비스호출_김지혜추가
    @Autowired
    private Sm1020Service sm1020Service;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Override
    public void insertPd2010(LinkedHashMap paramMap, List<Map<String, Object>> paramList01, List<Map<String, Object>> paramList02) throws Exception {
        //작업지시등록
        if(!"".equals(paramMap.get("GDS_HST_NO"))){
            String gds_no = ((String) paramMap.get("GDS_HST_NO")).substring(0,1);
            if( (!"L".equals(gds_no)) && (!"A".equals(gds_no)) ) {
                paramMap.put("GDS_NO", "");
            } else {
                paramMap.put("GDS_NO", gds_no);
            }
            egovLogger.debug("==============이력번호비교"+paramMap.get("GDS_NO"));
            
            //물품이력번호 ValidCheck
            //2021.10.20 서광석(대표님 요청) 으로 인한 막음
            /*
            String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.pd.selectPd2010_HSTNO_ValidCheck", paramMap);
            if(!"OK".equals(resultMsg)) {
                throw infoException(resultMsg);
            }
            */
        }
        
        //지시일련번호 채번
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_DNTT_DT = paramMap.get("DNTT_DT").toString();
        String seqNo = commonService.getGvno(s_CORP_C,"TB_PD_M_WK_DNTT",s_BZPL_C, s_DNTT_DT, 1);
        egovLogger.debug("=====작업지시일련번호 채번" +seqNo);
        paramMap.put("DNTT_SQNO", seqNo);
        
        if("10".equals(paramMap.get("GDS_TP_DSC"))) {
            //지시상세일련번호(제품00)
            paramMap.put("DNTT_DNO", "00");
        } else {
            //반제품 채번(01~09)
            String dntt_dNo = sqlSession.selectOne("sfmes.sqlmap.pd.selectdntt_dNo_01", paramMap);
            paramMap.put("DNTT_DNO", dntt_dNo);
        }
        
        //거래업무구분코드(P10작업지시)
        paramMap.put("TR_BSN_DSC", "PD10");
        
        //거래일련번호
        String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
        egovLogger.debug("=====작업지시거래일련번호 채번" +tr_seqNo);
        paramMap.put("TR_SQNO", tr_seqNo);
        
        egovLogger.debug("==============================" +paramMap.get("TR_SQNO"));
        egovLogger.debug("==============================" +paramMap.get("TR_BSN_DSC"));
        egovLogger.debug("==============================" +paramMap);
        //기본정보저장   기본저장 제품저장 투입물품저장 순서를 바꾸면 안됨 
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_M_WK_DNTT",paramMap);
        //제품상세저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_DNTT_MFS",paramMap);
        
        for(Map<String, Object> map : paramList02){
            //투입자재상세저장
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("DNTT_DT", paramMap.get("DNTT_DT"));
            map.put("DNTT_SQNO", paramMap.get("DNTT_SQNO"));
            map.put("DNTT_DNO",paramMap.get("DNTT_DNO")); 
            
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_DNTT_MTRL",map);
        }
        egovLogger.debug("SAVE_YN================================="+paramMap.get("SAVE_YN"));
        if("1".equals(paramMap.get("SAVE_YN"))) {
            //반제품작업지시여부가 "1"일경우, 반제품 작업지시 생성
            for(Map<String, Object> map : paramList01){
                //거래일련번호
               String tr_seqNo_d = commonService.getTrGvno(s_CORP_C, 1);
               egovLogger.debug("=====작업지시거래일련번호 채번" +tr_seqNo_d);
               map.put("TR_SQNO", tr_seqNo_d);
               
               //투입자재상세저장
               map.put("CORP_C", paramMap.get("CORP_C"));
               map.put("BZPL_C", paramMap.get("BZPL_C"));
               map.put("DNTT_DT", paramMap.get("DNTT_DT"));
               map.put("DNTT_SQNO", paramMap.get("DNTT_SQNO"));
               map.put("MFC_WK_STS_C", paramMap.get("MFC_WK_STS_C"));
               
                
               //BOM수량에서 지시량이 늘어난정도
               map.put("PQT",paramMap.get("PQT").toString());
               
               /**********************************************************************
                * 2021.04.29 JKS 반제품의 정보에서 표기이력번호 관리 Check
                * 2021.12.14 rchkorea 반제품의 이력번호, 생산라인 직접 입력받아서 저장 함. 
                **********************************************************************/
               //map.put("PD_LINE_C", paramMap.get("PD_LINE_C"));

//               Map<String, Object> map_gds = sqlSession.selectOne("sfmes.sqlmap.co.select_GDS_inf_List", map);
//               if(!"0".equals(map_gds.get("HST_AMN_DSC"))) {
//                   map.put("GDS_HST_NO", map_gds.get("GDS_HST_NO"));
//               }
//
//               egovLogger.debug("==============   생산라인 ::: "+map_gds.get("PD_LINE_C"));
//               map.put("PD_LINE_C", map_gds.get("PD_LINE_C"));               
               
               /**********************************************************************/
               
               egovLogger.debug("====================지시대비 늘어난 수량(투입물품에 적용대상)"+map.get("PQT"));
               egovLogger.debug("========================물품유형"+map.get("GDS_TP_DSC"));
               
               //반제품 채번(01~39)   20211214  rchkorea
               // 화면에서 받은 반품 레벨에 따른 채번 규칙 적용 
               if("2".equals(map.get("STP_DSC"))) {     // 단계구분코드  첫번째 반제품 01~09
                   map.put("ST_STRING","0");
               } else if ("3".equals(map.get("STP_DSC"))) {   // 반제품의 반제품 11~19
                   map.put("ST_STRING","1");
               } else if ("4".equals(map.get("STP_DSC"))) {   // 반제품의 반제품의 반제품 21~29
                   map.put("ST_STRING","2");
               } else if ("5".equals(map.get("STP_DSC"))) {   // 반제품의 반제품의 반제품의 반제품 31~39
                   map.put("ST_STRING","3");
               }
               String dntt_dNo = sqlSession.selectOne("sfmes.sqlmap.pd.selectdntt_dNo_01", map);
               paramMap.put("DNTT_DNO", dntt_dNo);
               map.put("DNTT_DNO", paramMap.get("DNTT_DNO"));
                   
               egovLogger.debug("========================list01===="+map);
                   
               //제품상세저장
               sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_DNTT_MFS",map);
                   
               //투입자재상세저장
               sqlSession.insert("sfmes.sqlmap.pd.insert_TB_PD_D_WK_DNTT_MTRL02",map);
           }
            
           // 원재료 이력구분이있으면 해당 제품 또는 반제품에 따라가도록 수정  20211219 rchkorea
            sqlSession.update("sfmes.sqlmap.pd.update_TB_PD_D_WK_DNTT_MTRL_02",paramMap); 
        }
//        for(Map<String, Object> map : paramList01){
//             //거래일련번호
//            String tr_seqNo_d = commonService.getTrGvno(s_CORP_C, 1);
//            egovLogger.debug("=====작업지시거래일련번호 채번" +tr_seqNo_d);
//            map.put("TR_SQNO", tr_seqNo_d);
//            
//            //투입자재상세저장
//            map.put("CORP_C", paramMap.get("CORP_C"));
//            map.put("BZPL_C", paramMap.get("BZPL_C"));
//            map.put("DNTT_DT", paramMap.get("DNTT_DT"));
//            map.put("DNTT_SQNO", paramMap.get("DNTT_SQNO"));
//            map.put("MFC_WK_STS_C", paramMap.get("MFC_WK_STS_C"));
//            
//            //BOM수량에서 지시량이 늘어난정도
//            map.put("PQT",paramMap.get("PQT").toString()); 
//            
//            egovLogger.debug("========================수량"+map.get("PQT"));
//            egovLogger.debug("========================물품유형"+map.get("GDS_TP_DSC"));
//            
//            //반제품 채번(01~09)
//            String dntt_dNo = sqlSession.selectOne("sfmes.sqlmap.pd.selectdntt_dNo_01", map);
//            paramMap.put("DNTT_DNO", dntt_dNo);
//            map.put("DNTT_DNO", paramMap.get("DNTT_DNO"));
//                
//            Float mfs_qt = Float.parseFloat((String)map.get("MFS_DNTT_QT")) ;
//            egovLogger.debug("========================지시량"+mfs_qt);
//            Float pqt = Float.parseFloat((String)map.get("PQT"));
//            egovLogger.debug("========================변경량"+pqt);
//                
//            Float total =  mfs_qt * pqt;
//            
//            egovLogger.debug("========================합계지시량"+total);
//            
//            map.put("MFS_DNTT_QT", total); 
//            map.put("PD_LINE_C", paramMap.get("PD_LINE_C"));
//            
//            egovLogger.debug("========================list01===="+map);
//                
//            //제품상세저장
//            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_DNTT_MFS",map);
//                
//            //투입자재상세저장
//            sqlSession.insert("sfmes.sqlmap.pd.insert_TB_PD_D_WK_DNTT_MTRL02",map);
//        }

        //작업지시 갯수 return
        String save_c = sqlSession.selectOne("sfmes.sqlmap.pd.select_save_count", paramMap);
        paramMap.put("SAVE_C", save_c);
        
    }

    @Override
    public void updatePd2010(LinkedHashMap paramMap, List<Map<String, Object>> paramList01, List<Map<String, Object>> paramList02) throws Exception {
        //작업지시수정
        
        if("Y".equals(paramMap.get("DEL_YN"))) {
            //작업지시 삭제여부 Y 변경
            paramMap.put("MFC_WK_STS_C", "99");
            sqlSession.update("sfmes.sqlmap.pd.update_delyn_TB_PD_M_WK_DNTT",paramMap);
            sqlSession.update("sfmes.sqlmap.pd.update_delyn_TB_PD_D_WK_DNTT_MFS",paramMap);
            for(Map<String, Object> map : paramList01){
                //투입자재상세저장
                map.put("CORP_C", paramMap.get("CORP_C"));
                map.put("BZPL_C", paramMap.get("BZPL_C"));
                map.put("DNTT_DT", paramMap.get("DNTT_DT"));
                map.put("DNTT_SQNO", paramMap.get("DNTT_SQNO"));
                map.put("DNTT_DNO",paramMap.get("DNTT_DNO")); 
                egovLogger.debug("삭제==================================="+map);
                sqlSession.update("sfmes.sqlmap.pd.update_delyn_TB_PD_D_WK_DNTT_MTRL",map);
            }
            //작업상태 '99작업삭제' 변경
            sqlSession.update("sfmes.sqlmap.pd.TB_PD_D_WK_DNTT_MFS_change_2021",paramMap);
            
        } else {
            //기본정보수정   기본저장 제품저장 투입물품저장 순서를 바꾸면 안됨 
            sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_M_WK_DNTT",paramMap);
            //제품상세수정
            paramMap.put("TR_SQNO", paramMap.get("D_TR_SQNO"));
            sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_WK_DNTT_MFS",paramMap);
            
            //하위 작업지시 제품상세수정
            paramMap.put("PD2010", "Y");
            sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_WK_DNTT_MFS",paramMap);
            
            // 반제품 리스트 수정   20211215 추가 rchkorea
            for(Map<String, Object> map : paramList01){
                // 제품상세수정
            	map.put("PD2010", "Y");
                map.put("CORP_C", paramMap.get("CORP_C"));
                map.put("BZPL_C", paramMap.get("BZPL_C"));
                sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_WK_DNTT_MFS",map);
            }

            //투입자재상세저장
            for(Map<String, Object> map : paramList02){
                map.put("CORP_C", paramMap.get("CORP_C"));
                map.put("BZPL_C", paramMap.get("BZPL_C"));
                map.put("DNTT_DT", paramMap.get("DNTT_DT"));
                map.put("DNTT_SQNO", paramMap.get("DNTT_SQNO"));
                map.put("DNTT_DNO",paramMap.get("DNTT_DNO")); 
                
//                if(map.get("_status_").equals("+")){
//                    
//                    sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_DNTT_MTRL",map);
//                    
//                     //BOM코드가 있을 경우
//                    if(!"".equals(map.get("BOM_C"))){
//                        //반제품 채번(01~09)
//                        String dntt_dNo = sqlSession.selectOne("sfmes.sqlmap.pd.selectdntt_dNo_01", map);
//                        paramMap.put("DNTT_DNO", dntt_dNo);
//                        map.put("DNTT_DNO", paramMap.get("DNTT_DNO")); 
//                        
//                        //제품상세저장
//                        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_DNTT_MFS",map);
//                        //투입자재상세저장
//                        sqlSession.insert("sfmes.sqlmap.pd.insert_TB_PD_D_WK_DNTT_MTRL02",map);
//                    
//                        map.put("DNTT_DNO",""); 
//                    }
//                    
//                } else 
                
                  // 20211124 rchkorea  삭제가 아닌 내역은 모두 수정 되도록 변경 이력번호를 변경한경우 투입물품에도 이력번호를 반영하여야 하기때문 
//                if(map.get("_status_").equals("*")){
//                    
//                    sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_WK_DNTT_MTRL",map);
//                    
//                } else if(map.get("_status_").equals("-")){
//                    
//                    sqlSession.update("sfmes.sqlmap.pd.update_delyn_TB_PD_D_WK_DNTT_MTRL",map);
//                    
//                }
                if(map.get("_status_").equals("-")){
                    
                    sqlSession.update("sfmes.sqlmap.pd.update_delyn_TB_PD_D_WK_DNTT_MTRL",map);
                    
                } else {
                    sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_WK_DNTT_MTRL",map);
                }
            }
            
        }
        
    }
    
    @Override
    public List<?> selectPd2010List_01(LinkedHashMap paramMap) throws Exception {
        // 작업지시 기본조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2010List01",paramMap);
    }
    
    @Override
    public List<?> selectPd2010List_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("===================="+paramMap);
        // 작업지시 제품 조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2010List02",paramMap);
    }

    @Override
    public List<?> select_Pd2010List03(LinkedHashMap paramMap) throws Exception {
        // 작업지시 투입자재상세 조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2010List03",paramMap);
    }
    
    @Override
    public List<?> selectPd2010List_04(LinkedHashMap paramMap) throws Exception {
        // 제품상세 생성용 (반제품조회)
        return sqlSession.selectList("sfmes.sqlmap.pd.selectBOM_D_01",paramMap);
    }
    
    @Override
    public List<?> selectPd2010List_05(LinkedHashMap paramMap) throws Exception {
        // BOM상세 조회(반제품투입내역조회에 사용)
        return sqlSession.selectList("sfmes.sqlmap.pd.selectBOM_D",paramMap);
    }
    
    @Override
    public List<?> selectPd2010List_06(LinkedHashMap paramMap) throws Exception {
        // 반제품 조회 (등록된내역 조회)  20211214  rchkorea
        return sqlSession.selectList("sfmes.sqlmap.pd.select_Pd2010List06",paramMap);
    }
}
