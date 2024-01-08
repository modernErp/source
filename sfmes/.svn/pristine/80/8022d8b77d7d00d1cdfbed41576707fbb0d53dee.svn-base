package com.sfmes.ca.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.ca.service.Ca0200Service;
import com.sfmes.ca.service.Ca6015Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Ca6015ServiceImpl.java
 * @Description : 선수금입금내역
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.09.14  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.14
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Ca6015Service")
public class Ca6015ServiceImpl extends CmnAbstractServiceImpl implements Ca6015Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    // 채무 서비스 선언
    @Resource(name = "Ca0200Service")
    private Ca0200Service ca0200Service;
    
    // 선수금입금내역 조회
    @Override
    public List<?> selectCa6015List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.ca.selectCa6015List", paramMap);
    }

    // 선수금입금/사용내역 정정취소
    @Override
    public void updateCa6015List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("=====선수금입금/사용내역 정정취소 updateCa5015List 호출: [paramMap]" + paramMap + "/[paramList]" + paramList);
        
        for( Map<String, Object> map : paramList ) {
            /**
             * 마감일자의 마감여부를 체크하는 메소드
             * @param CORP_C(회사코드), BZPL_C(사업장코드), ACG_DT(회계일자), TR_DSC(업무구분:BY.매입,PD.생산,SE.매출)
             * @return 없음
             */
            paramMap.put("ACG_DT", map.get("ACG_DT"));
            paramMap.put("TR_DSC", "CA"); 
            commonService.checkDdl(paramMap);
            
            map.put("BZPL_C"     , paramMap.get("BZPL_C")    );        // 사업장코드
            map.put("DEL_YN"     , "N"                       );        // 삭제여부
        }
        
        // 선수금입금/사용내역 등록 처리(호출).
        ca0200Service.Call_savePrv(paramList);
        return;
    }
    
    // 선수금입금/사용내역 삭제
    @Override
    public void deleteCa6015List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("=====선수금입금/사용내역 삭제 deleteCa5015List 호출: [paramMap]" + paramMap + "/[paramList]" + paramList);
        
        for( Map<String, Object> map : paramList ) {
            /**
             * 마감일자의 마감여부를 체크하는 메소드
             * @param CORP_C(회사코드), BZPL_C(사업장코드), ACG_DT(회계일자), TR_DSC(업무구분:BY.매입,PD.생산,SE.매출)
             * @return 없음
             */
            paramMap.put("ACG_DT", map.get("ACG_DT"));
            paramMap.put("TR_DSC", "CA"); 
            commonService.checkDdl(paramMap);
            
            map.put("BZPL_C"     , paramMap.get("BZPL_C"));        // 사업장코드
            map.put("SLP_NML_YN" , "N"                   );        // 전표정상여부
            map.put("DEL_YN"     , "N"                   );        // 삭제여부
        }

        // 선수금입금/사용내역 등록 처리(호출).
        ca0200Service.Call_savePrv(paramList);
        return;
    }
    
}
