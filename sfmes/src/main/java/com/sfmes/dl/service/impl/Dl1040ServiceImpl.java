package com.sfmes.dl.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.dl.service.Dl1040Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Dl1040ServiceImpl.java
 * @Description : 영업마감등록
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.11.30  이수빈  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.11.30
 * @version 1.0
 * @see
 *
 *  dlpyright (C) by 모든솔루션 All right reserved.
 */

@Service("Dl1040Service")
public class Dl1040ServiceImpl extends CmnAbstractServiceImpl implements Dl1040Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    
    // 영업등록마감조회
    @Override
    public List<?> selectDl1040List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.dl.selectDl1040List", paramMap);
    }
        
    // 영업등록마감팝업조회
    @Override
    public List<?> selectDl1040List_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_DL_M_DAY_DDL", paramMap);
    }
    
    // 마감일보를 조회한다.
    @Override
    public List<?> selectDl1040List_02(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.dl.selectDl1040List02", paramMap);
    }
    
    //영업등록마감등록
    public void insert1040List(LinkedHashMap paramMap) throws Exception {
		String resultMsg = null;
		
		// 프로시저를 호출한다.
		resultMsg = sqlSession.selectOne("sfmes.sqlmap.dl.selectDl1040Chk01", paramMap);
		
		if( !"OK".equals(resultMsg) ) {
			throw infoException(resultMsg);
		}
    	
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_DL_M_DAY_DDL", paramMap);
    }
    
    //영업등록마감수정
    public void update1040List(LinkedHashMap paramMap) throws Exception {
        
        sqlSession.update("sfmes.sqlmap.tb.update_TB_DL_M_DAY_DDL", paramMap);
    }
    
    // 최종마감등록
    public void insert1040Last(LinkedHashMap paramMap) throws Exception {
		String resultMsg = null;
		String resultYn = "N";
    	
		// 마감구분코드 설정
		paramMap.put("DDL_DSC", "SE");
		
		// 프로시저를 호출한다.
		sqlSession.selectOne("sfmes.sqlmap.dl.selectDl1040Last", paramMap);
		
		resultYn = (String)paramMap.get("OUT_RESULT_YN");
		resultMsg = (String)paramMap.get("OUT_ERROR_MSG");
		
		if("N".equals(resultYn)) {
			throw infoException("일마감 처리 중 오류 발생: " + resultMsg);
		}
    }
    
    // 최종마감취소등록
    public void insert1040LastCancle(LinkedHashMap paramMap) throws Exception {
    	
		String resultMsg = null;
		String resultYn = "N";
		
		// 마감구분코드 설정
		paramMap.put("DDL_DSC", "SE");
		
		// 프로시저를 호출한다.
		sqlSession.selectOne("sfmes.sqlmap.dl.selectDl1040LastCancel", paramMap);
		
		resultYn = (String)paramMap.get("OUT_RESULT_YN");
		resultMsg = (String)paramMap.get("OUT_ERROR_MSG");
		
		if("N".equals(resultYn)) {
			throw infoException("일마감 취소 중 오류 발생: " + resultMsg);
		}
    }
    
}