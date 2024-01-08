package com.sfmes.ca.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.ca.service.Ca0200Service;
import com.sfmes.ca.service.Ca5015Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Ca5015ServiceImpl.java
 * @Description : 기타미지급금지급내역
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
@Service("Ca5015Service")
public class Ca5015ServiceImpl extends CmnAbstractServiceImpl implements Ca5015Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    // 채무 서비스 선언
    @Resource(name = "Ca0200Service")
    private Ca0200Service ca0200Service;

    // 기타미지급금지급내역
    @Override
    public List<?> selectCa5015List(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("=====외상매입금집급내역 selectCa5015List 호출:" + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.ca.selectCa5015List", paramMap);
    }

    // 기타미지급금지급내역 정정취소
    @Override
    public void updateCa5015List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("=====외상매입금지급 정정취소 updateCa5015List 호출: [paramMap]" + paramMap + "/[paramList]" + paramList);
        
        for( Map<String, Object> map : paramList ) {
            /**
             * 마감일자의 마감여부를 체크하는 메소드
             * @param CORP_C(회사코드), BZPL_C(사업장코드), ACG_DT(회계일자), TR_DSC(업무구분:BY.매입,PD.생산,SE.매출)
             * @return 없음
             */
//            paramMap.put("ACG_DT", map.get("ACG_DT"));
//            paramMap.put("TR_DSC", "CA"); 
//            commonService.checkDdl(paramMap);
            
            
            map.put("BZPL_C"     , paramMap.get("BZPL_C")    );        // 사업장코드
            
            // 마감업무 로직 추가 2022-02-08 ksckorea
            
            LinkedHashMap<String, Object> chkDdlYnParam = new LinkedHashMap<String, Object>();
            chkDdlYnParam.put("CORP_C" , map.get("CORP_C")); //회사코드
            chkDdlYnParam.put("BZPL_C" , map.get("BZPL_C")); //사업장코드
            chkDdlYnParam.put("BSN_DSC", "CA");                   //업무구분 CA:정산
            chkDdlYnParam.put("BAS_DT" , map.get("ACG_DT"));  //기준일자 ACG_DT:회계일자
            
            String ddlYn = commonService.checkDdlYn(chkDdlYnParam);
            if("Y".equals(ddlYn)) {
                throw infoException("해당 매출일자로 업무가 마감되었습니다. 날짜를 확인해주세요.");
            }
            
            String result = "";
            String resultMsg = null;  //결과메세지
            
            
            map.put("REG_DSC"    , "P"                       );        // 등록구분코드 ['N':발생/'P':지급]
            map.put("DEL_YN"     , "N"                       );        // 삭제여부
        }
        
        // 기타미지급금 등록 처리(호출).
        ca0200Service.Call_saveEtcUpy(paramList);
        return;
    }
    
    // 기타미지급금지급내역 삭제
    @Override
    public void deleteCa5015List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("=====외상매입금지급 삭제 deleteCa5015List 호출: [paramMap]" + paramMap + "/[paramList]" + paramList);
        
        for( Map<String, Object> map : paramList ) {
            /**
             * 마감일자의 마감여부를 체크하는 메소드
             * @param CORP_C(회사코드), BZPL_C(사업장코드), ACG_DT(회계일자), TR_DSC(업무구분:BY.매입,PD.생산,SE.매출)
             * @return 없음
             */
//            paramMap.put("ACG_DT", map.get("ACG_DT"));
//            paramMap.put("TR_DSC", "CA"); 
//            commonService.checkDdl(paramMap);
            
        	// 마감삭제업무 로직 추가 2022-03-21 ksckorea
        	
        	map.put("BZPL_C"     , paramMap.get("BZPL_C")    );
            
            LinkedHashMap<String, Object> chkDdlYnParam = new LinkedHashMap<String, Object>();
            chkDdlYnParam.put("CORP_C" , map.get("CORP_C")); //회사코드
            chkDdlYnParam.put("BZPL_C" , map.get("BZPL_C")); //사업장코드
            chkDdlYnParam.put("BSN_DSC", "CA");                   //업무구분 CA:정산
            chkDdlYnParam.put("BAS_DT" , map.get("ACG_DT"));  //기준일자 ACG_DT:회계일자
            
            String ddlYn = commonService.checkDdlYn(chkDdlYnParam);
            if("Y".equals(ddlYn)) {
                throw infoException("해당 매출일자로 업무가 마감되었습니다. 날짜를 확인해주세요.");
            }
            
            String result = "";
            String resultMsg = null;  //결과메세지
        	
            map.put("BZPL_C"     , paramMap.get("BZPL_C"));        // 사업장코드
            map.put("SLP_NML_YN" , "N"                   );        // 전표정상여부
            map.put("REG_DSC"    , "P"                   );        // 등록구분코드 ['N':발생/'P':지급]
            map.put("DEL_YN"     , "N"                   );        // 삭제여부
        }

        // 기타미지급금 등록 처리(호출).
        ca0200Service.Call_saveEtcUpy(paramList);
        return;
    }
    
}
