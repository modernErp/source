package com.sfmes.se.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se6010Service;

/**
 * @Class Name : Se6010ServiceImpl.java
 * @Description : 출고지시등록
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.09.11   김지혜     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se6010Service")
public class Se6010ServiceImpl extends CmnAbstractServiceImpl implements Se6010Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    //수주마감기본내역 조회
    @Override
    public List<?> selectSe6010_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주마감기본내역[SE6010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe6010_01", paramMap);
    }

    //수주마감상세내역 조회
    @Override
    public List<?> selectSe6010_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주마감상세내역[SE6010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe6010_02", paramMap);
    }

    //출고지시기본내역찾기(팝업)
    @Override
    public List<?> selectSe6010_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시기본내역찾기팝업[SE6010P01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe6010_03", paramMap);
    }

    //출고지시상세내역찾기(팝업)
    @Override
    public List<?> selectSe6010_04(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시상세내역찾기팝업[SE6010P01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe6010_04", paramMap);
    }

    //출고지시등록
    @Override
    public void insertSe6010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 출고지시등록[SE6010] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());

        //채번을 위한 변수설정
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();

        //오늘날짜구하기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());

        //출고지시등록
        for(Map<String, Object> map : paramList) {
            //채번 서비스를 호출한다.
            String seqNo = commonService.getGvno(s_CORP_C, "TB_SE_M_DLR_DNTT", s_BZPL_C, strToday, 1);
            egovLogger.debug("생성된 출고지시일련번호: " + seqNo);

            map.put("BZPL_C"          , paramMap.get("BZPL_C").toString());
            map.put("DLR_DNTT_DT"     , strToday);
            map.put("DLR_DNTT_SQNO"   , seqNo);
            map.put("DLR_DNTT_STS_DSC", "1");
            
            //출고지시 M 등록
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_DLR_DNTT", map);

            //수주마감 상세 조회
            List<Map<String, Object>> rvoInfoData = sqlSession.selectList("sfmes.sqlmap.se.selectSe6010_02", map);
            
            //조회한 수주마감 상세정보 -> 출고지시 상세정보 data세팅 후 등록
            for(Map<String, Object> detailMap : rvoInfoData) {
            	detailMap.put("DLR_DNTT_DT", strToday);
            	detailMap.put("DLR_DNTT_SQNO", seqNo);
            	detailMap.put("GUSRID", map.get("GUSRID"));
            	detailMap.put("DLR_DNTT_QT", detailMap.get("RVO_QT"));
            	detailMap.put("DLR_DNTT_BOX_QT", detailMap.get("RVO_BOX_QT"));
            	detailMap.put("DLR_DNTT_UPR", detailMap.get("RVO_UPR"));
            	detailMap.put("DLR_DNTT_AM", detailMap.get("RVO_AM"));
            	detailMap.put("DLR_DNTT_WT", detailMap.get("RVO_WT"));
	            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_D_DLR_DNTT", detailMap);
            }

            //수주상태(3:출고등록) 및 출구지시일자,출고지시번호 수정
            sqlSession.update("sfmes.sqlmap.se.updateSe6010_rvo", map);
        }
    }

    //제품출고등록에서 출고지시참조 시 출고지시상태구분 수정
    @Override
    public void Call_updateSe6010(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 출고지시상태구분수정[SE6010] *********");

        egovLogger.debug("출고지시기본내역수정 TB_SE_M_DLR_DNTT");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        sqlSession.insert("sfmes.sqlmap.se.updateSe6010_STS", paramMap);
   
    }

    // 출고지시기본내역 조회(PDA) - 박지환 추가 (2021.03.31)
    @Override
    public List<?> selectPdaSe6010_01(LinkedHashMap paramMap) throws Exception {
    	egovLogger.debug("************ 출고지시기본내역 조회[PdaSe6010_01] *********");
    	return sqlSession.selectList("sfmes.sqlmap.se.select_PDA_Se6010_01", paramMap);
    }
}
