package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hsqldb.lib.StringUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd2020Service;
import com.sfmes.se.service.Se2010Service;


/**
 * @Class Name : Pd2020ServiceImpl.java
 * @Description : 작업지시일괄등록 등록
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.10  김종관  최초생성
 * @ 2021.12.10  여다혜  작업지시 물품 표기이력번호 정합성 체크 추가 
 *
 * @author (주)모든솔루션
 * @since 2020.08.
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Pd2020Service")
public class Pd2020ServiceImpl extends CmnAbstractServiceImpl implements Pd2020Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Resource(name = "CommonService")
    private CommonService commonService;

    @Autowired
    private Se2010Service se2010Service;

    @Override
    public void insertPd2020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {

        int BAN_DNTT_DNO = 0; // 반제품일경우 상세일련번호
        
        /*------------------------------------------------------------------------------------------
         작업지시등록 유효성 체크 
         ------------------------------------------------------------------------------------------*/
        String result = "";   // 유효성 체크 결과        
        result = selectValid_01(paramMap, paramList); //유효성 체크 메서드 호출
        
        if(!result.equals("OK")) {
            throw infoException(result);
        }
        
        /*------------------------------------------------------------------------------------------
         작업지시등록 
        ------------------------------------------------------------------------------------------*/
        // String temp_dntt_sqno = "";    // 마지막에 투입상세내역에 이력번호를 Update하는 시점을 알기위한 변수 

        for(Map<String, Object> map : paramList){
            
            String s_CORP_C = paramMap.get("CORP_C").toString();
            String s_BZPL_C = paramMap.get("BZPL_C").toString();
            String s_DNTT_DT = paramMap.get("DNTT_DT").toString();
            // 수주건별 분할 YN
            String s_RCPL_C_RVO_YN = paramMap.get("RCPL_C_RVO_YN").toString();

            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("DNTT_DT", paramMap.get("DNTT_DT"));
            map.put("RVO_CLO_DT", paramMap.get("RVO_CLO_DT"));      // 수주마감일자
            map.put("RVO_CLO_SQNO", paramMap.get("RVO_CLO_SQNO"));  // 수주마감일련번호 
            
            egovLogger.debug("=====제품지시량"+map.get("MFS_DNTT_QT")+"=====BOM기준 증감수량"+map.get("PQT"));

            
            if("10".equals(map.get("GDS_TP_DSC"))) {  // 제품이면

                //지시일련번호 채번
                String seqNo = commonService.getGvno(s_CORP_C,"TB_PD_M_WK_DNTT",s_BZPL_C, s_DNTT_DT, 1);
                egovLogger.debug("=====작업지시일련번호 채번" +seqNo);
                paramMap.put("DNTT_SQNO", seqNo);
                
                

                //거래업무구분코드(P10작업지시)
                paramMap.put("TR_BSN_DSC", "PD10");
                paramMap.put("MFC_WK_STS_C", "01");    // 가공작업상태코드 01.작업지시
                
                //기본정보저장
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_M_WK_DNTT",paramMap);

                map.put("DNTT_SQNO", paramMap.get("DNTT_SQNO"));   // 작업지시일련번호 

                map.put("DNTT_DNO","00"); // 작업지시 상세번호 제품이면 '00'으로 고정
                
                // 수주상세 작업지시일자 일련번호 수정 (물품별 sum으로 작업지시가 생성되면 수주도 물품이 같은내역에 같은 작업지시번호가 들어가도록 수정)   20211219 rchkorea
                
                //수주건별 분할체크에 따른 서비스 분리
                if(s_RCPL_C_RVO_YN.equals("Y")) {
	                // 작업지시서일광등록에서 수주건별 분할을 위해 수주번호 각각 입력이 됨 20220406 ksckorea
	                // 수주기본 작업지시일자 일련번호 수정   (수주기본에는 최종 마지막 작업지시일자, 일련번호가 저장됨) 20211219 rchkorea
                	se2010Service.Call_updateSe2010_WK_DNTT_D(map);
	                se2010Service.Call_updateSe2010_WK_DNTT(map);
                }else {
                	se2010Service.Call_updateSe2010_WK_DNTT_D_NON(map);
                	se2010Service.Call_updateSe2010_WK_DNTT_NON(map);
                }
                
                
            }else if("20".equals(map.get("GDS_TP_DSC"))) {// 반제품이면 

                map.put("DNTT_SQNO", paramMap.get("DNTT_SQNO"));   // 작업지시일련번호 

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
                String dntt_dNo = sqlSession.selectOne("sfmes.sqlmap.pd.selectdntt_dNo_01", map);    // 반제품 작업지시 상세번호 조회 
                map.put("DNTT_DNO", dntt_dNo);
            }
            
            egovLogger.debug("=================dntt_dNo"+map.get("DNTT_DNO"));

            //거래일련번호
            String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
            egovLogger.debug("=====작업지시거래일련번호 채번" +tr_seqNo);
            map.put("TR_SQNO", tr_seqNo);

            //제품상세저장
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_WK_DNTT_MFS",map);
                
            egovLogger.debug("========================list01===="+map);

            //투입자재상세저장
            sqlSession.insert("sfmes.sqlmap.pd.insert_TB_PD_D_WK_DNTT_MTRL02",map);
            
            // 원재료 이력구분이있으면 해당 제품 또는 반제품에 따라가도록 수정  20211219 rchkorea
            paramMap.put("DNTT_DNO", map.get("DNTT_DNO"));
            sqlSession.update("sfmes.sqlmap.pd.update_TB_PD_D_WK_DNTT_MTRL_02",paramMap); 
            

            
        }
    }

    @Override
    public List<?> selectWkDnttList(LinkedHashMap paramMap) throws Exception {
    	
    	 // 수주건별 분할 YN
        String s_RCPL_C_RVO_YN = paramMap.get("RCPL_C_RVO_YN").toString();
        if(s_RCPL_C_RVO_YN.equals("Y")) {
	        //제품조회
	        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2021_Mfs", paramMap);
        }else {
        	return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2021_Mfs_NON", paramMap);
        }
    }

    @Override
    public List<?> selectWkDnttMtrlList(LinkedHashMap paramMap) throws Exception {
        //투입품조회
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd2021_Mtrl", paramMap);
    }
    
    //20211210_여다혜 표기이력번호 유효성 체크 로직 추가
    //프로시저, VIEW모두 검사 후 양쪽에 없을 경우 오류체크 함 (대표님, 유이사님 요구사항)
    private String selectValid_01(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        
        for(Map<String, Object> map : paramList) {
            List<?> validProc = null;
            int validView = 0;
            
            //표기이력번호가 있을 경우 체크
            if(!StringUtil.isEmpty((String)map.get("GDS_HST_NO")) || !map.get("GDS_HST_NO").equals("")) {

                //프로시저검사
                map.put("TRACE_NO", map.get("GDS_HST_NO")); //화면의 입력된 이력번호를 프로시저 파라미터로 PUT처리
                map.put("STR_DT"  , "19000101");            //STR_DT 하드코딩   (NULL일 경우, 프로시저 조회안됨)
                map.put("AVAIL_YN", "Y");                   //AVAIL_YN 하드코딩 (NULL일 경우, 프로시저 조회안됨)
                validProc = sqlSession.selectList("sfmes.sqlmap.co.selectCo2030_BUDL_NO_List", map);
                    
                //VIEW검사 
                validView = sqlSession.selectOne("sfmes.sqlmap.pd.selectPd2020_valid_01", map); 

                egovLogger.info("@@ vaidProc ::" + validProc);
                egovLogger.info("@@ validView ::" + validView);
                
                //프로시저, view에 해당이력번호가 모두 없을 경우
                if(validProc.size() <= 0 && validView <= 0) {
                    throw infoException("해당 물품의 이력번호가 유효하지 않습니다. 이력번호 다시 입력 해주세요.\n\n" +
                                        "물품 : [" + map.get("GDS_C") + "] " + map.get("GDS_NM") + "\n" +
                                        "이력번호 : " + map.get("GDS_HST_NO"));
                }
            }
        }
        
        return "OK";
    }
}