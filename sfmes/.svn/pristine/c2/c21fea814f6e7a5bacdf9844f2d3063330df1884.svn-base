package com.sfmes.dl.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.dl.service.Dl1030Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Dl1030ServiceImpl.java
 * @Description : 회계전표수기등록
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.12.07  이수빈  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.12.07
 * @version 1.0
 * @see
 *
 *  dlpyright (C) by 모든솔루션 All right reserved.
 */

@Service("Dl1030Service")
public class Dl1030ServiceImpl extends CmnAbstractServiceImpl implements Dl1030Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    
    // 회계전표수기등록조회
    @Override
    public List<?> selectDl1030List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.dl.selectDl1030List", paramMap);
    }
    
    // 회계전표수기등록조회_01
    @Override
    public List<?> selectDl1030List_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.dl.selectDl1030List_01", paramMap);
    }
        
    @Override
    public void insertDl1030List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug(":::::[DL1030INSERT]:::::" + paramList);
        
        //회계전표수기채번
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_ACG_DT = paramMap.get("ACG_DT").toString();
        
        String seqNo = commonService.getGvno(s_CORP_C, "TB_DL_M_ACG_SLP", s_BZPL_C, s_ACG_DT, 1);
        egovLogger.debug(":::::회계전표수기채번:::::" +seqNo);
        paramMap.put("ACG_SQNO", seqNo);
        
        //마스터테이블 저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_DL_M_ACG_SLP", paramMap);
        
        
        for(Map<String, Object> map : paramList){    
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("ACG_DT", paramMap.get("ACG_DT"));
            map.put("ACG_SQNO", paramMap.get("ACG_SQNO"));
            map.put("TR_BSN_DSC", paramMap.get("TR_BSN_DSC"));
            
            String dseqNo = sqlSession.selectOne("sfmes.sqlmap.dl.selectseqNo", map);
            map.put("ACG_DSQNO", dseqNo);
            
            if("1".equals(map.get("DB_CR_DSC"))) {
                map.put("ACG_AM", map.get("DB_AM"));
                
            } else if("2".equals(map.get("DB_CR_DSC"))) {
                map.put("ACG_AM", map.get("CR_AM"));
                
            }
            
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_DL_D_ACG_SLP", map);
                           
        }
    }

    @Override
    public void updateDl1030List(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        
        egovLogger.debug("===========수정마스터"+paramMap);
        egovLogger.debug("===========수정리스트"+paramList);
        //마스터테이블 수정
        sqlSession.update("sfmes.sqlmap.tb.update_TB_DL_M_ACG_SLP", paramMap);
        
        //디테일테이블수정 포문
        for(Map<String, Object> map : paramList){
            egovLogger.debug("===========수정리스트"+map);
            
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("ACG_DT", paramMap.get("ACG_DT"));
            map.put("ACG_SQNO", paramMap.get("ACG_SQNO"));
            map.put("TR_BSN_DSC", paramMap.get("TR_BSN_DSC"));
            
            if("1".equals(map.get("DB_CR_DSC"))) {
                map.put("ACG_AM", map.get("DB_AM"));
                
            } else if("2".equals(map.get("DB_CR_DSC"))) {
                map.put("ACG_AM", map.get("CR_AM"));
                
            }
            
            if(map.get("_status_").equals("+")){
                String seqNo = sqlSession.selectOne("sfmes.sqlmap.dl.selectseqNo", map);
                map.put("ACG_DSQNO", seqNo);
                
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_DL_D_ACG_SLP",map);
                
            } else if(map.get("_status_").equals("*")){
                egovLogger.debug("==========구분값"+map.get("DB_CR_DSC"));
                egovLogger.debug("==========금액1"+map.get("ACG_AM"));
                sqlSession.update("sfmes.sqlmap.tb.update_TB_DL_D_ACG_SLP",map);
                
            } 
        }
    }
}