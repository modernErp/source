package com.sfmes.se.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se5010Service;

/**
 * @Class Name : Se5010ServiceImpl.java
 * @Description : 견적서 등록
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.06  손용찬      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Se5010Service")
public class Se5010ServiceImpl extends CmnAbstractServiceImpl implements Se5010Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
 // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    /**
     * 견적서 등록한다.
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    @Override
    public void insertSe5010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
                   
        egovLogger.debug(":::::[SE5010_견적서등록]:::::");
        //견적일련번호 채번 
        String seqNo = commonService.getGvno(
                String.valueOf(paramMap.get("CORP_C")),                                                     //회사코드
                "TB_SE_M_EMT",                                                //채번대상 테이블
                String.valueOf(paramMap.get("BZPL_C")),                       //사업장코드 
                String.valueOf(paramMap.get("ESMT_DT")),                      //채번일자
                1                                                             //건수
        );      
        paramMap.put("ESMT_SQNO", seqNo);
        //견적서 기본등록
        
        egovLogger.debug(":::::[견적서 기본저장_TB_SE_M_EMT]::::::" + paramMap);
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_EMT", paramMap);
               
    
        //견적서 상세등록
        for(Map<String, Object> map : paramList) {
            if(map.get("_status_").equals("+")) {
                map.put("CORP_C"    , paramMap.get("CORP_C"));
                map.put("BZPL_C"    , paramMap.get("BZPL_C"));
                map.put("ESMT_DT"   , paramMap.get("ESMT_DT"));
                map.put("ESMT_SQNO" , paramMap.get("ESMT_SQNO"));
                
                egovLogger.debug(":::::[견적서 상세저장_TB_SE_D_EMT]::::::" + map);
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_D_EMT", map);
            }
        }
    }
    
    /**
     * 견적서 수정
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    @Override
    public void updateSe5010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        
        egovLogger.debug(":::::[SE5010_견적서수정]:::::");
        
        //견적서 기본수정
        egovLogger.debug(":::::[견적서 기본수정_TB_SE_M_EMT]::::::" + paramMap);
        sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_EMT", paramMap);
        
        //견적서 상세 수정
        for(Map<String, Object> map : paramList) {
            
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("ESMT_DT", paramMap.get("ESMT_DT"));
            map.put("ESMT_SQNO", paramMap.get("ESMT_SQNO"));
            
            if(map.get("_status_").equals("+")) {
                //견적서 상세등록(행추가)
                egovLogger.debug(":::::[견적서 상세수정_TB_SE_D_EMT]::::::" + map);
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_D_EMT", map);
            } else {
                //견적서등록 상세데이터 수정
                egovLogger.debug(":::::[견적서 상세수정_TB_SE_D_EMT]::::::" + map);
                sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_D_EMT", map);
            } 
        }
    }
    
    /**
     * 견적서 삭제
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    @Override
    public void deleteSe5010(LinkedHashMap paramMap) throws Exception {
        //견적서 기본 삭제(update del_yn=N)
        egovLogger.debug(":::::[견적서 기본_삭제_TB_SE_M_EMT]::::::" + paramMap);
        sqlSession.update("sfmes.sqlmap.se.deleteSe5010_01", paramMap);
    }
        
    /**
     * @return 
     * 견적서 기본조회
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */
    @Override
    public List<?> selectSe5010List01(LinkedHashMap paramMap) {      
        egovLogger.debug(":::::[견적서 기본_조회_TB_SE_M_EMT]::::::" + paramMap);
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe5010List01",paramMap);
    }
    
    /**
     * @return 
     * 견적서 상세조회
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */
    @Override
    public List<?> selectSe5010List02(LinkedHashMap paramMap) {      
        egovLogger.debug(":::::[견적서 상세_조회_TB_SE_D_EMT]::::::");
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe5010List02",paramMap);
    }
    
    /**
     * @return 
     * 견적서 출력(리포트) 조회
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 상세 내역
     * @exception
     */
    @Override
    public List<?> selectSe5010List03(LinkedHashMap paramMap) {
        egovLogger.debug(":::::[견적서_레포트조회]::::::");
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe5010List03",paramMap);
    }
}
