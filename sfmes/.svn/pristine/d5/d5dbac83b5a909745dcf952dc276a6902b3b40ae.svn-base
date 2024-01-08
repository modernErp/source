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
import com.sfmes.sm.service.Sm1020Service;

/**
 * @Class Name  : Sm1020ServiceImpl.java
 * @Description : Sm1020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.08.05   김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.08.05
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm1020Service")
public class Sm1020ServiceImpl extends CmnAbstractServiceImpl implements Sm1020Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    //생산투입출고의뢰 신규등록
    @Override
    public String saveSm1020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        //리턴할 값 담아놓을 변수설정
        String result = "";
        egovLogger.debug(":::::[SM1020FORM]:::::" + paramMap);
        egovLogger.debug(":::::[SM1020LIST]:::::" + paramList);
        
        if("1".equals(paramMap.get("DLR_RQT_REF_DSC"))){
            egovLogger.debug(":::::[SM1020참조구분변경테스트]:::::");
            // 2020.09.09 JKS 출고의뢰일자에서 시스템일자로 변경
            //paramMap.put("DLR_RQT_DT", paramMap.get("DNTT_DT"));//채번할때 지시일자를 출고의뢰일자로변경

            //오늘날짜구하기
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());
            
            paramMap.put("DLR_RQT_DT", strToday);
        }
          
        //채번을 위한 변수설정
        String s_CORP_C     = paramMap.get("CORP_C").toString();
        String s_BZPL_C     = paramMap.get("BZPL_C").toString();
        String s_DLR_RQT_DT = paramMap.get("DLR_RQT_DT").toString();
        
        //채번 서비스 호출
        String seqNo = commonService.getGvno(s_CORP_C,"TB_SM_M_DLR_RQT",s_BZPL_C, s_DLR_RQT_DT, 1);
        
        egovLogger.debug(":::::[SM1020생산투입출고의뢰등록채번]:::::" + seqNo);
        paramMap.put("DLR_RQT_SQNO", seqNo);
        paramMap.put("DLR_RQT_STS_DSC", "1");//출고의뢰참조구분([1]출고의뢰/ [2]출고완료)
        paramMap.put("DEL_YN", "N");

        egovLogger.debug(":::::[SM1020채번하고_INSERT]:::::" + paramMap);
        
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_M_DLR_RQT", paramMap);
        
        for(Map<String, Object> map : paramList) {
            if(map.get("_status_").equals("+")) {
                map.put("BZPL_C", paramMap.get("BZPL_C"));
                map.put("DLR_RQT_DT", paramMap.get("DLR_RQT_DT"));
                map.put("DLR_RQT_SQNO", paramMap.get("DLR_RQT_SQNO"));
                map.put("DEL_YN", "N");
                
                if("1".equals(paramMap.get("DLR_RQT_REF_DSC"))) {
                    egovLogger.debug(":::::[SM1020작업지시일때 출고의뢰수량확인하기]:::::");
                    map.put("DLR_RQT_QT", map.get("PTIN_DNTT_QT"));//투입지시량(작업지시) - > 출고의뢰수량(생산투입출고의뢰등록)
                    egovLogger.debug(":::::[SM1020작업지시일때 무참조일때 컬럼테이터 확인]:::::");
                    
                    egovLogger.debug(":::::[SM1020]:::::" + map);
                    
                    if("0".equals(map.get("HST_AMN_DSC")) || "".equals(map.get("HST_AMN_DSC"))){
                    }
                    else if("4".equals(map.get("HST_AMN_DSC"))){
                        if(map.get("GDS_HST_NO") == null){
                            throw infoException("LOT번호를 입력해주십시오.");
                        }
                    }else{
                        if("Y".equals(map.get("INF_YN"))) {
                            // 라벨이력번호구분
                            if(!"".equals(map.get("GDS_HST_NO"))) {
                                egovLogger.debug("==============="+((String) map.get("GDS_HST_NO")).substring(0,2));
                                if("L0".equals(((String) map.get("GDS_HST_NO")).substring(0,2)) || "L1".equals(((String) map.get("GDS_HST_NO")).substring(0,2)) || "3".equals(map.get("HST_AMN_DSC"))) {
                                    map.put("BUDL_NO",map.get("GDS_HST_NO"));
                                    egovLogger.debug("=================="+map.get("BUDL_NO"));
                                    map.put("GDS_HST_NO","");
                                    egovLogger.debug("=================="+map.get("GDS_HST_NO"));
                                }                                
                            }
                        } else {
                            if(map.get("BUDL_NO") == null){
                                throw infoException("묶음번호를 입력해주십시오.");
                            }
                        }
                    }
                }
                
                
                egovLogger.debug(":::::[SM1020LIST_INSERT]:::::" + map);
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_D_DLR_RQT",map);
                
            }
        }
        result = paramMap.toString();
        return result;
    }

    //생산투입출고의뢰 수정
    @Override
    public String updateSm1020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        //리턴할 값 담아놓을 변수설정
        String result = "";
        
        //생산투입출고의뢰기본내역 수정
        egovLogger.debug(":::::[생산투입출고의뢰 기본내역 수정]:::::" + paramMap);
        //paramMap.put("DEL_YN", "N");
        sqlSession.update("sfmes.sqlmap.tb.update_TB_SM_M_DLR_RQT",paramMap); 
        
        //생산투입출고의뢰상세내역 수정
        for(Map<String, Object> map : paramList) {
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("DLR_RQT_DT", paramMap.get("DLR_RQT_DT"));
            map.put("DLR_RQT_SQNO", paramMap.get("DLR_RQT_SQNO"));  
            //map.put("DEL_YN", "N");
            if (map.get("_status_").equals("+")) {
                egovLogger.debug(":::::[SM1020생산투입출고의뢰LIST_수정에서신규]:::::" + map);
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SM_D_DLR_RQT",map); 
            }else {
                egovLogger.debug(":::::[SM1020생산투입출고의뢰LIST_수정에서수정]:::::" + map);
                sqlSession.update("sfmes.sqlmap.tb.update_TB_SM_D_DLR_RQT",map); 
            }
        }
        result = paramMap.toString();
        return result;
    }
    
    //생산투입출고의뢰등록폼조회
    @Override
    public List<?> searchSm1020_01(LinkedHashMap paramMap) throws Exception {
       egovLogger.debug(":::::[SM1020FORM조회01]:::::" + paramMap);
       return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1020_01", paramMap);
       //return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SM_M_DLR_RQT", paramMap);   // 생산투입출고등록의뢰를 참조여부에 무관하게 처리하였으나, 무참조로 처리가 필요하여 위 내용으로 변경함. ( 다른 곳에서 참조 여부에 따라 차후 해당 코드는 삭제 필요함. )
    }

    //생산투입출고의뢰등록리스트조회
    @Override
    public List<?> searchSm1020_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug(":::::[SM1020 상세 LIST조회]:::::" + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SM_D_DLR_RQT", paramMap);
    }
    
    //생산투입출고의뢰내역팝업조회
    @Override
    public List<?> searchSm1020P01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug(":::::[SM1020P01 LIST조회]:::::" + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1020P01", paramMap);
    }

    //PDA 용 생산투입출고의뢰내역팝업조회
    @Override
    public List<?> searchSm1020PDA(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug(":::::[SM1020PDA LIST조회]:::::" + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm1020PDA", paramMap);
    }

}
