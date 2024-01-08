/**
 * 
 */
package com.sfmes.ge.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.ge.service.Ge1040Service;
import com.sfmes.ge.service.Ge3010Service;

/**
 * @Class Name  : Ge3010ServiceImpl.java
 * @Description : A/S접수
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.03.16  나명우  최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Ge3010Service")
public class Ge3010ServiceImpl extends CmnAbstractServiceImpl implements Ge3010Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;


    //AS 접수
    @Override
    public String insertGe3010(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ A/S접수 등록 Insert [GE3010] ************");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        
        String result = "";
        String resultMsg = null;
        int chk_cnt = 0;
        //중복체크
        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.ge.selectGe3010Cnt_1", paramMap);
        if(chk_cnt > 0)
        {
            throw infoException("이미 등록된 수주내역입니다.");
        }
        //AS 기본내역에대한 정합성 체크
        resultMsg = sqlSession.selectOne("sfmes.sqlmap.ge.selectGe3010Valid", paramMap);
        if(! resultMsg.equals("OK")) 
        {
            throw infoException(resultMsg + "\n전산담당자에게 문의하세요");
        }
        
        //채번을 위한 변수설정
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_RC_DT  = paramMap.get("AS_DT").toString();
        
        String seqNo = commonService.getGvno(s_CORP_C, "TB_GE_M_AS_RC", s_BZPL_C, s_RC_DT, 1);
        egovLogger.debug("AS일련번호 채번: " + seqNo);
        paramMap.put("AS_SQNO", seqNo);
        
        //AS 등록
        egovLogger.debug("AS 접수 등록 TB_GE_M_AS_RC");
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_GE_M_AS_RC", paramMap);
        
        result = paramMap.toString();
        return result;
    }
    
   
    @Override
    public List<?> selectGe3010_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************* AS기본내역 조회 [GE3010] ****************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe3010_01", paramMap);
       
    }
    


    @Override
    public List<?> selectGe3010_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************* AS기본내역 조회 [GE3010P01] ****************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe3010_02", paramMap);
 
    }


    @Override
    public String updateGe3010(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ AS접수 수정및삭제 Update [GE3010] ************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        String result = "";
        
        int chk_cnt = 0;
        String conn_id = paramMap.get("CONN_ID").toString();
  
        boolean isOk = false;
        
        //AS접수 등록된 내역 존재 확인
        chk_cnt = sqlSession.selectOne("sfmes.sqlmap.ge.selectGe3010Cnt", paramMap);        
        if(chk_cnt == 0) 
        {
            throw infoException("민원접수내역이 존재하지 않습니다.");
        }
        
        String fsrg_id = sqlSession.selectOne("sfmes.sqlmap.ge.selectGe3010Valid_2", paramMap);
        if(conn_id.equals(fsrg_id)) {
            isOk = true;
        }
        
        if(!isOk) {
            throw infoException("작성자만 변경가능합니다.");
        }
        
        if("N".equals(paramMap.get("DEL_YN")))
        {
            //AS내역 수정
            egovLogger.debug("AS내역 수정 TB_GE_M_AS_RC");
            egovLogger.debug("수정 paramMap: " + paramMap.toString());
            sqlSession.update("sfmes.sqlmap.tb.update_TB_GE_M_AS_RC", paramMap);
                 
        } else {
            //AS내역 삭제
            egovLogger.debug("AS내역삭제 TB_GE_M_AS_RC");
            egovLogger.debug("삭제 paramMap: " + paramMap.toString());
            
            sqlSession.update("sfmes.sqlmap.tb.delete_TB_GE_M_AS_RC", paramMap);               
                    
        }
        return result;
    }


}
