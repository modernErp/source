package com.sfmes.se.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se2030Service;

/**
 * @Class Name : Se2030ServiceImpl.java
 * @Description : 수주마감등록
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.20  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se2030Service")
public class Se2030ServiceImpl extends CmnAbstractServiceImpl implements Se2030Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;        

    //수주마감대상 조회
    @Override
    public List<?> selectSe2030_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주마감대상조회[SE2030] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe2030_01", paramMap);
    }

    //수주마감내역(팝업)
    @Override
    public List<?> selectSe2030_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주마감내역조회[SE2030] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe2030_02", paramMap);
    }
    
    //수주마감기본내역 조회
    @Override
    public List<?> selectSe2030_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주마감기본내역조회[SE2030] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe2030_03", paramMap);
    } 
    
    //수주마감상세내역 조회
    @Override
    public List<?> selectSe2030_04(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 수주마감상세내역조회[SE2030] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe2030_04", paramMap);
    }      
    
    //수주마감등록
    @Override
    public void updateSe2030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 수주마감등록[SE2030] *********");
        egovLogger.debug("paramMap : " + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());

        //채번을 위한 변수설정
        String s_CORP_C = paramList.get(0).get("CORP_C").toString();
        String s_BZPL_C = paramList.get(0).get("BZPL_C").toString();
        
        //오늘날짜구하기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String sToday = sdf.format(c1.getTime());    // 오늘날짜 사용안함 
        
        // 2022.02.10  rchkorea   미트맵 요청으로 수정 
        // 수주마감일은 오늘날짜이어야 하는데 과거자료 입력을 위해 마감일자를 화면에서 입력받은값으로 가능하도록 수정 
        String strToday = paramMap.get("RVO_CLO_DT").toString();
        
        //채번 서비스를 호출한다.
        String seqNo = commonService.getGvno(s_CORP_C, "TB_SE_M_RVO_CLO", s_BZPL_C, strToday, 1);
        egovLogger.debug("생성된 수주마감일련번호: " + seqNo);        
            
        //수주마감등록(수주상태 2:수주마감)
        egovLogger.debug("수주마감등록 TB_SE_M_RVO");
        for(Map<String, Object> map : paramList) 
        {
            map.put("RVO_CLO_DT"  , strToday);
            map.put("RVO_CLO_SQNO", seqNo);
            map.put("RVO_STS_DSC" , "2");
            egovLogger.debug("수정 map: " + map.toString());            
            sqlSession.update("sfmes.sqlmap.se.updateSe2030", map);             
        }  
    }
    
    //수주마감취소
    @Override
    public void updateSe2030_02(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 수주마감취소[SE2030] *********");
        egovLogger.debug("paramMap : " + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());

    	// 수주마감 취소는 작업지시가 처리된것은 할수 없음 정합성 체크 
        int select_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe2030_05", paramMap);  // 작업지시된 수주마감내역이 있는 건수    
        egovLogger.debug("작업지시 처리된 건수 : "+select_cnt);
        if(select_cnt > 0) {
            throw infoException("이미 작업지시 완료된 내역이있어 마감취소할 수 없습니다."); 
        }

        //수주마감취소(수주상태 2:수주마감 => 1.수주상태  로 변경 )
        egovLogger.debug("수주마감취소 TB_SE_M_RVO");
        for(Map<String, Object> map : paramList) 
        {
            map.put("RVO_STS_DSC" , "1");     // 수주상태구분코드 1.수주상태, 2.수주마감, 3.출고지시, 9.수주취소
            egovLogger.debug("취소수정 map: " + map.toString());            
            sqlSession.update("sfmes.sqlmap.se.updateSe2030_02", map);             
        }  
    }
    
}
