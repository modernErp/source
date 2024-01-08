package com.sfmes.sm.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Sm5020Service;


/**
 * @Class Name  : Sm5020ServiceImpl.java
 * @Description : Sm5020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.26   정성환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm5020Service")
public class Sm5020ServiceImpl extends CmnAbstractServiceImpl implements Sm5020Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
        
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    //재고실사등록 리스트 조회 
    @Override
    public List<?> searchSm5020_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.searchSm5020_01",paramMap);
    }
    
    // 재고실사등록 기본 조회 
    @Override
    public List<?> searchSm5020_02(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.searchSm5020_02",paramMap);
    }
    
    //재고실사등록 저장  
    @Override
    public String saveSm5020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception { 
        //리턴할 값 담아놓을 변수설정
        String result = "";
        
        egovLogger.debug("************ PDA용 테스트 [PDASm3010] *********");
        egovLogger.debug("paramMap : " + paramMap.toString());
        egovLogger.debug("paramList : " + paramList.toString());
        
        // 재고실사 진행 중 재고실사 삭제를 진행한 경우 문제가 되지 않게하기 위해.
        result = sqlSession.selectOne("sfmes.sqlmap.sm.searchSm5010_02", paramMap);
        if( result == null ) {
            throw infoException("USERMSG:재고실사 정보가 정상적이지 않습니다. 전산담당자에게 문의하세요.");
        }
        else if( "Y".equals(result) ) {
            throw infoException("USERMSG:이미 재고실사 진행이 삭제된 대상입니다. 조회 후 재확인해 보세요.");
        }
        
        // 재고실사등록 기본 저장 
        sqlSession.insert("sfmes.sqlmap.sm.saveSm5020_M",paramMap);
        
        long tempMaxDsqno = sqlSession.selectOne("sfmes.sqlmap.sm.searchSm5020_01_MAX_SSVY_RSV_DSQNO" ,paramMap);
        
        // 재고실사등록 상세 저장 
        for(Map<String,Object> map1 : paramList)
        {
            map1.put("SSVY_RSV_DT"      , paramMap.get("SSVY_RSV_DT"));
            map1.put("SSVY_RSV_SQNO"    , paramMap.get("SSVY_RSV_SQNO"));
            map1.put("CORP_C"           , paramMap.get("CORP_C"));
            map1.put("BZPL_C"           , paramMap.get("BZPL_C"));
            map1.put("DEL_YN"           , 'N');
            
            if(map1.get("SSVY_QT") == null || map1.get("SSVY_QT").equals(""))
            {
                map1.put("SSVY_QT"   , 0);
            }
            
            if(map1.get("SSVY_WT") == null || map1.get("SSVY_WT").equals(""))
            {
                map1.put("SSVY_WT"   , 0);
            }
            
            if(map1.get("SSVY_RSV_DSQNO") == null || map1.get("SSVY_RSV_DSQNO").equals(""))
            {
                map1.put("SSVY_RSV_DSQNO"   , tempMaxDsqno++);
            }
             
            if(map1.get("_status_").equals("+") || map1.get("GDS_ADT_YN").equals("Y"))
            {
                map1.put("GDS_ADT_YN", "Y");
            }
            
            if(map1.get("_status_").equals("-"))
            {
                map1.put("DEL_YN", "Y");
            }
                    
            sqlSession.insert("sfmes.sqlmap.sm.saveSm5020_D"    , map1);
        }
        
        
        result = paramMap.toString();
        return result;
    }

    // 재고실사등록 확정
    @Override
    public String updateSm5020_01(LinkedHashMap paramMap) throws Exception {
        String result = "";
        
        // 재고실사 진행 중 재고실사 삭제를 진행한 경우 문제가 되지 않게하기 위해.
        result = sqlSession.selectOne("sfmes.sqlmap.sm.searchSm5010_02", paramMap);
        if( result == null ) {
            throw infoException("USERMSG:재고실사 정보가 정상적이지 않습니다. 전산담당자에게 문의하세요.");
        }
        else if( "Y".equals(result) ) {
            throw infoException("USERMSG:이미 재고실사 진행이 삭제된 대상입니다. 조회 후 재확인해 보세요.");
        }
        
        sqlSession.insert("sfmes.sqlmap.sm.insertSm5020_TB_SM_M_SSVY_AJ", paramMap);
        sqlSession.insert("sfmes.sqlmap.sm.insertSm5020_TB_SM_D_SSVY_AJ", paramMap);
        sqlSession.insert("sfmes.sqlmap.sm.insertSm5020_TB_SM_D_SSVY_AJ_WHSE", paramMap);
        
        sqlSession.update("sfmes.sqlmap.sm.updateSm5020_01", paramMap);
        
        result = paramMap.toString();
        return result;
    }
    
    // 확정취소
    @Override
    public String updateSm5020_02(LinkedHashMap paramMap) throws Exception {
        String result = "";
        
        // 재고실사 진행 중 재고실사 삭제를 진행한 경우 문제가 되지 않게하기 위해.
        result = sqlSession.selectOne("sfmes.sqlmap.sm.searchSm5010_02", paramMap);
        if( result == null ) {
            throw infoException("USERMSG:재고실사 정보가 정상적이지 않습니다. 전산담당자에게 문의하세요.");
        }
        else if( "Y".equals(result) ) {
            throw infoException("USERMSG:이미 재고실사 진행이 삭제된 대상입니다. 조회 후 재확인해 보세요.");
        }
        
        // 실사상태구분코드["1":'재고실사준비등록']
        if("1".equals(paramMap.get("SSVY_STS_DSC"))) {
            if(paramMap.get("DEL_YN") != null)  // [재고조사조정등록]화면에서 확정취소시
            {
                if(paramMap.get("DEL_YN").equals("Y"))       
                {
                    sqlSession.delete("sfmes.sqlmap.sm.deleteSm5020_TB_SM_M_SSVY_AJ", paramMap);
                    sqlSession.delete("sfmes.sqlmap.sm.deleteSm5020_TB_SM_D_SSVY_AJ", paramMap);
                    sqlSession.delete("sfmes.sqlmap.sm.deleteSm5020_TB_SM_D_SSVY_AJ_WHSE", paramMap);
                }
            }            
        }

        sqlSession.update("sfmes.sqlmap.sm.updateSm5020_01", paramMap);
        
        result = paramMap.toString();
        return result;
    }
    
 // 재고실사등록 기본 조회 
    @Override
    public List<?> searchPDASm5020(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA용 재고준비등록 조회 [PDASm3010] *********");
        egovLogger.debug("paramMap : " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.sm.searchPDASm5020",paramMap);
    }

}
