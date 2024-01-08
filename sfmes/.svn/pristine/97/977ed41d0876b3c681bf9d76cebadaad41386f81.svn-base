package com.sfmes.ca.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.ca.service.Ca0100Service;
import com.sfmes.ca.service.Ca3015Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Ca3015ServiceImpl.java
 * @Description : 선급금지급내역
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
@Service("Ca3015Service")
public class Ca3015ServiceImpl extends CmnAbstractServiceImpl implements Ca3015Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    //공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    //채무 서비스 선언
    @Resource(name = "Ca0100Service")
    private Ca0100Service ca0100Service;
    
    //선급금지급내역 조회
    @Override
    public List<?> selectCa3015List(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("=====외상매출금지급내역 selectCa3015List 호출: " + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.ca.selectCa3015List", paramMap);
    }
    
    //선급금지급/사용내역 정정 취소
    @Override
    public void updateCa3015List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("=====선급금지급/사용내역 정정취소 updateCa2015List 호출 : [paramMap]" + paramList);
        
        for(Map<String, Object> map : paramList) {
            /**
             * 마감일자의 마감여부를 체크하는 메소드
             * @param CORP_C(회사코드), BZPL_C(사업장코드), ACG_DT(회계일자), TR_DSC(업무구분:BY.매입,PD.생산,SE.매출)
             * @return 없음
             */
            paramMap.put("ACG_DT", map.get("ACG_DT"));
            paramMap.put("TR_DSC", "CA"); 
            commonService.checkDdl(paramMap);
            
            map.put("BZPL_C", paramMap.get("BZPL_C"));         //사업장코드
            map.put("DEL_YN", "N"                   );         //삭제여부
        }
        
        //선급금지급/사용내역 등록 처리(호출)
        ca0100Service.Call_savePryam(paramList);
        return;
    }
    //선급금지급/사용내역 삭제
    @Override
    public void deleteCa3015List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("=====선급금지급/사용내역 삭제 삭제 deleteCa2015List 호출: [paramMap]" + paramMap + "/[paramList]" + paramList);
        
        for(Map<String, Object> map : paramList) {
            /**
             * 마감일자의 마감여부를 체크하는 메소드
             * @param CORP_C(회사코드), BZPL_C(사업장코드), ACG_DT(회계일자), TR_DSC(업무구분:BY.매입,PD.생산,SE.매출)
             * @return 없음
             */
            paramMap.put("ACG_DT", map.get("ACG_DT"));
            paramMap.put("TR_DSC", "CA"); 
            commonService.checkDdl(paramMap);
            
            map.put("BZPL_C"      , paramMap.get("BZPL_C"));         //사업장코드
            map.put("SLP_NML_YN"  , "N"                   );         //전표정상여부
            map.put("DEL_YN"      , "N"                   );         //삭제여부
        }
        
        //선급금지급/사용내역 등록 처리(호출)
        ca0100Service.Call_savePryam(paramList);
        return;
    }
}
