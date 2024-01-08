package com.sfmes.ca.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.ca.service.Ca0100Service;
import com.sfmes.ca.service.Ca2010Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Ca2010ServiceImpl.java
 * @Description : 기타미수금회수등록
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.09.28  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Ca2010Service")
public class Ca2010ServiceImpl extends CmnAbstractServiceImpl implements Ca2010Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    // 채무 서비스 선언
    @Resource(name = "Ca0100Service")
    private Ca0100Service ca0100Service;
    
    @Override
    public List<?> selectCa2010List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.ca.selectCa2010List01", paramMap);
    }

    @Override
    public void insertCa2010List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        
        /**
         * 마감일자의 마감여부를 체크하는 메소드
         * @param CORP_C(회사코드), BZPL_C(사업장코드), ACG_DT(회계일자), TR_DSC(업무구분:BY.매입,PD.생산,SE.매출)
         * @return 없음
         */
//        paramMap.put("TR_DSC", "CA"); 
//        commonService.checkDdl(paramMap);
        
        // 마감업무 로직 추가 2022-02-08 ksckorea
        
        LinkedHashMap<String, Object> chkDdlYnParam = new LinkedHashMap<String, Object>();
        chkDdlYnParam.put("CORP_C" , paramMap.get("CORP_C")); //회사코드
        chkDdlYnParam.put("BZPL_C" , paramMap.get("BZPL_C")); //사업장코드
        chkDdlYnParam.put("BSN_DSC", "CA");                   //업무구분 CA:정산
        chkDdlYnParam.put("BAS_DT" , paramMap.get("ACG_DT"));  //기준일자 ACG_DT:회계일자
        
        String ddlYn = commonService.checkDdlYn(chkDdlYnParam);
        if("Y".equals(ddlYn)) {
            throw infoException("해당 매출일자로 업무가 마감되었습니다. 날짜를 확인해주세요.");
        }
        
        String result = "";
        String resultMsg = null;  //결과메세지
        
        // 채번 서비스 호출 (거래일련번호)
        String s_CORP_C = ( (LinkedHashMap<String, Object>) paramList.get(0) ).get("CORP_C").toString();
        String tr_seqNo = commonService.getTrGvno(s_CORP_C, 1);
        egovLogger.debug("=====기타미수금내역 저장[등록] 일련번호 채번 : " +tr_seqNo);

        //  기타미수금 등록 정보 설정.
        for( Map<String, Object> map : paramList ) {
            map.put("CORP_C"       , paramMap.get("CORP_C")   );                // 회사코드
            map.put("BZPL_C"       , paramMap.get("BZPL_C")   );                // 사업장코드
            map.put("SLP_NML_YN"   , "Y"                      );                // 전표정상여부
            map.put("RLTR_DT"      , paramMap.get("RLTR_DT")  );                // 실거래일자
            map.put("ACG_DT"       , paramMap.get("ACG_DT")   );                // 회계일자
            map.put("WDR_PLA_DT"   , ""                       );                // 회수예정일자 ( 특별히 지정이 없어 우선 null 처리함 )
            map.put("REG_DSC"      , "R"                      );                // 등록구분 ('R':회수)
            if( map.get("RMK_CNTN") == null) {
                map.put("RMK_CNTN"     , ""                   );                // 비고내용
            }
            map.put("DEL_YN"       , "N"                      );                // 삭제여부
            map.put("TR_BSN_DSC"   , "BD30"                   );                // 거래업무구분코드 ('BD30':기타미수금회수등록)
            map.put("TR_SQNO"      , tr_seqNo                 );                // 거래일련번호
            map.put("GUSRID"       , paramMap.get("GUSRID")   );                // 사용자ID
        }
        
        // 기타미수금 등록 처리(호출).
        ca0100Service.Call_saveEtcAcrv(paramList);
    }

}
