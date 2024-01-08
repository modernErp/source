package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy1000Service;

/**
 * @Class Name  : Sy1000ServiceImpl.java
 * @Description : Sy1000Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.24   김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Sy1000Service")
public class Sy1000ServiceImpl extends CmnAbstractServiceImpl implements Sy1000Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    //회사신규등록
    @Override
    public void insertCorp_C(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception { 
        //중복회사코드확인
        int CORP_CNT = sqlSession.selectOne("sfmes.sqlmap.sy.selectSy1000",paramMap);
        if(CORP_CNT > 0) {
            throw infoException("중복된 회사코드가 존재합니다. 확인 후 다시 처리하시오.");
        }else {
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_CORP",paramMap);
        }
        //회사신규등록시 사업장기본정보, 사업장환경기본 저장
        for(Map<String, Object> map : paramList) {
            if (map.get("_status_").equals("+")) {
                //중복사업장코드확인
                int BZPL_CNT = sqlSession.selectOne("sfmes.sqlmap.sy.select2Sy1000", map);
                if(BZPL_CNT > 0){
                    throw infoException("중복된 사업장코드가 존재합니다. 확인 후 다시 처리하시오.");
                } 
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_BZPL",map);
                
                sqlSession.insert("sfmes.sqlmap.sy.insert_bzpl_env",map);  
                
                //사업장최초등록시 사업장을 거래처로 최초등록
                map.put("TRPL_C", map.get("BZPL_C"));
                map.put("TRPL_NM", map.get("BZPL_NM"));
                map.put("TRPL_ABR_NM", map.get("BZPL_ABR_NM"));
                map.put("CORP_C", map.get("S_CORP_C"));
        
                //거래처정보
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_TRPL",map);
                
                egovLogger.debug("::::[사업장등록 후 창고코드등록해요]::::" + map);
                
                //사업장최초등록한 후 가상창고 최초등록
                map.put("WHSE_C", "Z01");
                map.put("WHSE_NM", "위탁창고");
                map.put("WHSE_DSC", "02");
                map.put("CORP_C", map.get("S_CORP_C"));
                map.put("WHSE_CFC", "02");
                map.put("MINUS_YN", "Y");
                map.put("USE_YN", "Y");
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_WHSE",map);
                egovLogger.debug("::::[co1010위탁창고01]::::" + map);
                
                map.put("WHSE_C", "Z02");
                map.put("WHSE_NM", "수탁창고");
                map.put("WHSE_DSC", "03");
                map.put("CORP_C", map.get("S_CORP_C"));
                map.put("WHSE_CFC", "02");
                map.put("MINUS_YN", "Y");
                map.put("USE_YN", "Y");
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_WHSE",map);
                egovLogger.debug("::::[co1010수탁창고02]::::" + map);
                
                map.put("WHSE_C", "Z03");
                map.put("WHSE_NM", "폐기/반품창고");
                map.put("WHSE_DSC", "04");
                map.put("CORP_C", map.get("S_CORP_C"));
                map.put("WHSE_CFC", "02");
                map.put("MINUS_YN", "Y");
                map.put("USE_YN", "Y");
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_WHSE",map);
                egovLogger.debug("::::[co1010폐기/반품창고03]::::" + map);
                
                map.put("WHSE_C", "ZZZ");
                map.put("WHSE_NM", "생산창고");
                map.put("WHSE_DSC", "05");
                map.put("CORP_C", map.get("S_CORP_C"));
                map.put("WHSE_CFC", "02");
                map.put("MINUS_YN", "Y");
                map.put("USE_YN", "Y");
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_WHSE",map);
                egovLogger.debug("::::[co1010생산창고04]::::" + map);
                
            }
        } 
    }

    //회사정보수정
    @Override
    public void updateCorp_C(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception { 
        sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_CORP", paramMap); 
        
        //사업장그리드 수정 시 사업장기본정보(업데이트), 사업장기본이력 저장
        for(Map<String, Object> map : paramList) {
            if (map.get("_status_").equals("*")) {
                sqlSession.update("sfmes.sqlmap.sy.update_bzpl",map);
                sqlSession.insert("sfmes.sqlmap.sy.insert_l_bzpl",map); 
                
                egovLogger.debug("회사등록사업장수정시 거래처수정확인해01 " + map);
                //사업장수정시 사업장을 거래처로 수정
                map.put("TRPL_C", map.get("BZPL_C"));
                map.put("TRPL_NM", map.get("BZPL_NM"));
                map.put("TRPL_ABR_NM", map.get("BZPL_ABR_NM"));
                map.put("CORP_C", map.get("S_CORP_C"));
                //거래처정보
                egovLogger.debug("거래처update실행" + map);
                sqlSession.update("sfmes.sqlmap.co.update_co1000_trpl",map);
                egovLogger.debug("거래처update완료" + map);
            //사업장그리드 신규 입력 시 사업장기본정보, 사업장환경기본 저장
            }else if (map.get("_status_").equals("+")) {
                //중복사업장코드확인
                int BZPL_CNT = sqlSession.selectOne("sfmes.sqlmap.sy.select2Sy1000", map);
                if(BZPL_CNT > 0){
                    throw infoException("중복된 사업장코드가 존재합니다. 확인 후 다시 처리하시오.");
                } 
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_BZPL",map);

                sqlSession.insert("sfmes.sqlmap.sy.insert_bzpl_env",map); 
                
                //사업장수정시 사업장을 거래처로 수정
                map.put("TRPL_C", map.get("BZPL_C"));
                map.put("TRPL_NM", map.get("BZPL_NM"));
                map.put("TRPL_ABR_NM", map.get("BZPL_ABR_NM"));
                map.put("CORP_C", map.get("S_CORP_C"));
                egovLogger.debug("거래처신규insert실행" + map);
                //거래처정보
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_TRPL",map);
                
                //사업장최초등록한 후 가상창고 최초등록
                map.put("WHSE_C", "Z01");
                map.put("WHSE_NM", "위탁창고");
                map.put("WHSE_DSC", "02");
                map.put("CORP_C", map.get("S_CORP_C"));
                map.put("WHSE_CFC", "02");
                map.put("MINUS_YN", "Y");
                map.put("USE_YN", "Y");
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_WHSE",map);
                egovLogger.debug("::::[co1010위탁창고01]::::" + map);
                
                map.put("WHSE_C", "Z02");
                map.put("WHSE_NM", "수탁창고");
                map.put("WHSE_DSC", "03");
                map.put("CORP_C", map.get("S_CORP_C"));
                map.put("WHSE_CFC", "02");
                map.put("MINUS_YN", "Y");
                map.put("USE_YN", "Y");
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_WHSE",map);
                egovLogger.debug("::::[co1010수탁창고02]::::" + map);
                
                map.put("WHSE_C", "Z03");
                map.put("WHSE_NM", "폐기/반품창고");
                map.put("WHSE_DSC", "04");
                map.put("CORP_C", map.get("S_CORP_C"));
                map.put("WHSE_CFC", "02");
                map.put("MINUS_YN", "Y");
                map.put("USE_YN", "Y");
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_WHSE",map);
                egovLogger.debug("::::[co1010폐기/반품창고03]::::" + map);
                
                map.put("WHSE_C", "ZZZ");
                map.put("WHSE_NM", "생산창고");
                map.put("WHSE_DSC", "05");
                map.put("CORP_C", map.get("S_CORP_C"));
                map.put("WHSE_CFC", "02");
                map.put("MINUS_YN", "Y");
                map.put("USE_YN", "Y");
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_WHSE",map);
                egovLogger.debug("::::[co1010생산창고04]::::" + map);
            }
        } 
    }
    
    // 회사환경 초기 설정
    @Override
    public void insertSy1000(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug(":::::[insertSy1000]:::::" + paramMap);
        
		// 프로시저를 호출한다.
		sqlSession.selectOne("sfmes.sqlmap.sy.selectSy1000_init", paramMap);
		
		String resultYn = "N";
		resultYn = (String)paramMap.get("OUT_RESULT_YN");
        egovLogger.debug(":::::[insertSy1000 resultYn]:::::" + resultYn);
		
		if("N".equals(resultYn)) {
			throw infoException("USERMSG:" + (String)paramMap.get("OUT_ERROR_MSG"));
		}
    	
    	return;
    }
    
    // 회사환경 삭제
    @Override
    public void deleteSy1000(LinkedHashMap paramMap) throws Exception {
		// 프로시저를 호출한다.
		sqlSession.selectOne("sfmes.sqlmap.sy.selectSy1000_del", paramMap);
		
		String resultYn = "N";
		resultYn = (String)paramMap.get("OUT_RESULT_YN");
		
		if("N".equals(resultYn)) {
			throw infoException("USERMSG:" + (String)paramMap.get("OUT_ERROR_MSG"));
		}
    }
    
    //회사등록화면조회(SY1000.mvf)  
    @Override
    public List<?> selctDetailSy1000(LinkedHashMap paramMap) {
        egovLogger.debug(":::::[SY1000 회사조회]:::::" + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_CO_M_CORP",paramMap);
    }
    
    //회사내역화면조회(SY1005.mvf)
    @Override
    public List<?> selectSy1005(LinkedHashMap paramMap) {
        egovLogger.debug("회사내역:::"+paramMap);
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy1005",paramMap);
    }
    
    //사업장그리드조회
    @Override
    public List<?> select_bzpl(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.select_bzpl",paramMap);
    }
}
