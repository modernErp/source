package com.sfmes.ca.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.ca.service.Ca0100Service;
import com.sfmes.ca.service.Ca2020Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Ca2020ServiceImpl.java
 * @Description : 외상매출금원장계수정정등록
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.19  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since  2020.10.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Ca2020Service")
public class Ca2020ServiceImpl extends CmnAbstractServiceImpl implements Ca2020Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    // 채무 서비스 선언
    @Resource(name = "Ca0100Service")
    private Ca0100Service ca0100Service;

    // 외상매출금 원장계수정정등록 기준정보 조회
    @Override
    public List<?> selectCa2020List(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("=====기타미수금 원장계수정정등록 기준정보 조회 selectCa2020List 호출: " + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.ca.selectCa2020List", paramMap);
    }

    // 외상매출금 원장기초등록 정보 조회
    @Override
    public List<?> selectCa2020List_2(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("=====기타미수금 원장기초등록 기준정보 조회 selectCa2020List_2 호출: " + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.ca.selectCa2020List_2", paramMap);
    }

    // 외상매출금 원장계수정정등록
    @Override
    public void insertCa2020One(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("=====기타미수금 원장계수정정등록 insertCa2020One 호출: " + paramMap);
        
        /**
         * 마감일자의 마감여부를 체크하는 메소드
         * @param CORP_C(회사코드), BZPL_C(사업장코드), ACG_DT(회계일자), TR_DSC(업무구분:BY.매입,PD.생산,SE.매출)
         * @return 없음
         */
        paramMap.put("TR_DSC", "CA"); 
        commonService.checkDdl(paramMap);
        
        // 채번 서비스 호출 (거래일련번호)
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
        egovLogger.debug("=====기타미수금원장계수정정등록 일련번호 채번 : " +tr_seqNo);

        // 외상매출금 원장계수정정등록 정보 설정.
        if( paramMap.get("RMK_CNTN") == null) {
            paramMap.put("RMK_CNTN", "");                      // 비고내용
        }
        paramMap.put("DEL_YN", "N");                           // 삭제여부
        if( paramMap.get("TR_BSN_DSC") == null ) {
            paramMap.put("TR_BSN_DSC", "BD31");                // 거래업무구분코드 ('BD31':외상매출금계수정정등록)
        }
        paramMap.put("TR_SQNO", tr_seqNo);                     // 거래일련번호

        List<Map<String, Object>> paramList = new ArrayList();
        paramList.add(paramMap);
        ca0100Service.Call_saveEtcAcrv(paramList);
        return;
    }
}
