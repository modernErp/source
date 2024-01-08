package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd4010Service;

/**
* @Class Name : Pd4010ServiceImpl.java
* @Description : Pd4010Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.16   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.16
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd4010Service")
public class Pd4010ServiceImpl extends CmnAbstractServiceImpl implements Pd4010Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    // 수탁가공 접수 등록
    @Override
    public String insertPd4010(LinkedHashMap paramMap) throws Exception {
    	egovLogger.debug(":::::[Pd4010INSERT]::::::" + paramMap);

    	// 등록 validate
    	int validChk = sqlSession.selectOne("sfmes.sqlmap.pd.selectPd4010ValidateInsert", paramMap);
    	if(validChk > 0) {
    		throw infoException("같은 접수일자에 동일 거래처, 물품, 계약수량이 있습니다.");
    	}
    	
    	// 채번을 위한 변수 설정
		String sCorpC = paramMap.get("CORP_C").toString();
		String sBzplC = paramMap.get("BZPL_C").toString();
		String sEtrMfcRcDt = paramMap.get("ETR_MFC_RC_DT").toString();
		
		// 채번 서비스 호출
		String sRcSqno = commonService.getGvno(sCorpC,"TB_PD_M_ETR_MFC_RC", sBzplC, sEtrMfcRcDt, 1);
    	egovLogger.debug("=====일련번호 채번" + sRcSqno);
    	
    	// 일련번호 및 default 설정 map에 put
    	paramMap.put("ETR_MFC_RC_SQNO", sRcSqno);
    	paramMap.put("DEL_YN", "N");
    	paramMap.put("ETR_MFC_STS_DSC", "01");
    	
    	int result = sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_M_ETR_MFC_RC",paramMap);
    	
    	// 정상 등록 확인 후 정상 등록 되었을때 일련번호 return
    	if(result != 0) {
    		return sRcSqno;
    	} else {
    		return null;
    	}
    }

    // 수탁가공 접수 수정
    @Override
    public void updatePd4010(LinkedHashMap paramMap) throws Exception {
    	egovLogger.debug(":::::[Pd4010UPDATE]::::::" + paramMap);
    	sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_M_ETR_MFC_RC", paramMap);
    }
    
    // 수탁가공 접수 삭제
    @Override
    public void deletePd4010(LinkedHashMap paramMap) throws Exception {
    	egovLogger.debug(":::::[Pd4010DELETE]::::::" + paramMap);
    	
		// 삭제 validate
    	int validChk = sqlSession.selectOne("sfmes.sqlmap.pd.selectPd4010ValidateDelete", paramMap);
    	if(validChk > 0) {
    		throw infoException("해당 접수정보에 하위전표 내역이 있습니다.");
    	}
    	
    	sqlSession.update("sfmes.sqlmap.pd.updatePd4010DelYn", paramMap);
    }

    // 수탁가공 조회
    @Override
    public List<?> selectPd4010(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd4010_Mfc_Rc", paramMap);
    }
}
