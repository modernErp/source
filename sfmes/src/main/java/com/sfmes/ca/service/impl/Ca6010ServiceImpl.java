package com.sfmes.ca.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.ca.service.Ca0200Service;
import com.sfmes.ca.service.Ca6010Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Ca6010ServiceImpl.java
 * @Description : 선수금입금/사용 등록 기준정보 조회
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.07  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Ca6010Service")
public class Ca6010ServiceImpl extends CmnAbstractServiceImpl implements Ca6010Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    // 채무 서비스 선언
    @Resource(name = "Ca0200Service")
    private Ca0200Service ca0200Service;
    
    @Override
    public List<?> selectCa6010List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.ca.selectCa6010List", paramMap);
    }
    
    // 선수금 입금/사용 등록
    @Override
    public void insertCa6010One(LinkedHashMap paramMap) throws Exception {
        
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
        egovLogger.debug("=====선수금 입금/사용[등록] 일련번호 채번 : " +tr_seqNo);

        // 선수금 입금/사용 등록 정보 설정.
        if( paramMap.get("RMK_CNTN") == null) {
            paramMap.put("RMK_CNTN"     , ""                   );                // 비고내용
        }
        paramMap.put("DEL_YN"       , "N"                      );                // 삭제여부
        if( paramMap.get("TR_BSN_DSC") == null ) {
            if( "N".equals(paramMap.get("REG_DSC")) ) {
                paramMap.put("TR_BSN_DSC"   , "DT20"           );                // 거래업무구분코드 ('DT20':선수금입금등록)
            }
            else {
                paramMap.put("TR_BSN_DSC"   , "DT21"           );                // 거래업무구분코드 ('DT21':선수금사용등록)
            }
        }
        paramMap.put("TR_SQNO"      , tr_seqNo                 );                // 거래일련번호
        
        List<Map<String, Object>> paramList = new ArrayList();
        paramList.add(paramMap);
                
        // 선수금 입금/사용 등록 처리(호출).
        ca0200Service.Call_savePrv(paramList);
    }

}
