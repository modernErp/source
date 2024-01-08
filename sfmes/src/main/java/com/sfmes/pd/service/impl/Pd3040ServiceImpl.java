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
import com.sfmes.pd.service.Pd3040Service;

/**
 * @Class Name  : Pd3040ServiceImpl.java
 * @Description : Pd3040Service Class
 * @Modification Information
 * @
 * @  수정일           수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2022.02.10  유춘호            원가계산
 *
 * @author (주)모든솔루션
 * @since 2022.02.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Pd3040Service")
public class Pd3040ServiceImpl extends CmnAbstractServiceImpl implements Pd3040Service{

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;

    @Override
    public List<?> selectPd3040_01(LinkedHashMap paramMap) throws Exception {
        // 원가계산 기본 조회
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_CP_M_PCS", paramMap);
    }

    @Override
    public List<?> selectPd3040_02(LinkedHashMap paramMap) throws Exception {
        // 원가계산 상세 조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_pd3040List02", paramMap);
    }

    @Override
    public List<?> selectPd3040_04(LinkedHashMap paramMap) throws Exception {
        // 원가계산 팝업 조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_pd3040List04", paramMap);
    }

    @Override
    public void insertPd3040(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 원가계산 시작 *****************");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        
        egovLogger.debug("************ 원가계산 사전 정합성 체크  *****************");
        String resultMsg = null;  //결과메세지
        resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.pd.pd3040_ValidChk",paramMap);
        
        if(! resultMsg.equals("OK")) 
        {
            throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
        }
        
        // 원가기본 일련번호 채번
        //채번을 위한 변수설정
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_PCS_CLC_DT = paramMap.get("PCS_CLC_DT").toString();    // 원가계산일자
         
        egovLogger.debug("************ 일련번호 채번   *****************");
        String seqNo = commonService.getGvno(s_CORP_C, "TB_CP_M_PCS", s_BZPL_C, s_PCS_CLC_DT, 1);
        egovLogger.debug("생성된 원가계산 일련번호: " + seqNo);        
        paramMap.put("PCS_CLC_SQNO", seqNo);   // 원가계산일련번호 
        
        paramMap.put("PCS_CLC_ST_YM", ((String) paramMap.get("PCS_CLC_ST_DT")).substring(0,6) );    // 월별계정잔액 시작월 = 원가계산시작일자의 년월 
        paramMap.put("PCS_CLC_ED_YM", ((String) paramMap.get("PCS_CLC_ED_DT")).substring(0,6) );    // 원별계정잔액 종료월 = 원가계산종료일자의 년월
        
        egovLogger.debug("paramMap 추가후 : "  + paramMap.toString());

        egovLogger.debug("************ 원가계정별 잔액 집계   *****************");
        sqlSession.insert("sfmes.sqlmap.pd.insert_pd3040_01",paramMap);
        
        egovLogger.debug("************ 작업보고 투입내역 집계   *****************");
        sqlSession.insert("sfmes.sqlmap.pd.insert_pd3040_02",paramMap);
        
        egovLogger.debug("************ 직압보고 셍신내역 집계   *****************");
        sqlSession.insert("sfmes.sqlmap.pd.insert_pd3040_03",paramMap);
        
        egovLogger.debug("************ 원가기본 입력  *****************");
        sqlSession.insert("sfmes.sqlmap.pd.insert_pd3040_05",paramMap);
        
        egovLogger.debug("************ 원가상세 입력   *****************");
        sqlSession.insert("sfmes.sqlmap.pd.insert_pd3040_06",paramMap);

        egovLogger.debug("************ 원가상세 배부기준 계산 물품별 노무비,제조경비 수정   *****************");
        sqlSession.update("sfmes.sqlmap.pd.update_pd3040_03",paramMap);

        egovLogger.debug("************ 원가상세 노무비 짜투리 금액 큰금액에 수정  *****************");
        sqlSession.update("sfmes.sqlmap.pd.update_pd3040_04",paramMap);
        
        egovLogger.debug("************ 원가상세 제조경비 짜투리 금액 큰금액에 수정  *****************");
        sqlSession.update("sfmes.sqlmap.pd.update_pd3040_05",paramMap);

        egovLogger.debug("************ 원가상세 실제원가, 배부차액 수정  *****************");
        sqlSession.update("sfmes.sqlmap.pd.update_pd3040_06",paramMap);
        
        egovLogger.debug("************ 원가계산 후 정합성 체크  *****************");
        resultMsg = null;  //결과메세지
        resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.pd.pd3040_ValidChk_After",paramMap);
        
        if(! resultMsg.equals("OK")) 
        {
            throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
        }
    }

    @Override
    public void updatePd3040_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 원가계산 확정 시작 *****************");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        
        // 원가계산 확정 
        sqlSession.insert("sfmes.sqlmap.pd.update_pd3040_01",paramMap);
    }

    @Override
    public void updatePd3040_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 원가계산 취소 시작 *****************");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        
        // 원가계산 취소
        sqlSession.insert("sfmes.sqlmap.pd.update_pd3040_02",paramMap);
    }
    
}
