package com.sfmes.sm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Sm5010Service;
import com.sfmes.sm.service.Sm1000Service;

/**
 * @Class Name  : Sm5010ServiceImpl.java
 * @Description : Sm5010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.16   정성환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm5010Service")
public class Sm5010ServiceImpl extends CmnAbstractServiceImpl implements Sm5010Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Resource(name = "Sm1000Service")
    private Sm1000Service sm1000Service;
    
    //재고실사준비등록 기본
    @Override
    public List<?> searchSm5010_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.selectSm5010_01",paramMap);
    }
    
    //재고실사준비등록 저장  
    @Override
    public String saveSm5010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        //리턴할 값 담아놓을 변수설정
        String result = "";
       
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_SSVY_RSV_DT = paramMap.get("SSVY_RSV_DT").toString();
        
        /* 오늘날짜구하기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        */
        
        //채번 서비스 호출
        String seqNo = commonService.getGvno(s_CORP_C,"TB_SM_M_SSVY_RSV",s_BZPL_C, s_SSVY_RSV_DT, 1);
        
        paramMap.put("SSVY_RSV_SQNO" , seqNo);      // 실사준비일련번호
        sqlSession.insert("sfmes.sqlmap.sm.insertSm5010_TB_SM_D_SSVY_RSV_WHSE"  , paramMap);       // 재고_실사준비창고상세
        sqlSession.insert("sfmes.sqlmap.sm.insertSm5010_TB_SM_D_SSVY_RSV"       , paramMap);       // 재고_실사준비상세
        sqlSession.insert("sfmes.sqlmap.sm.insertSm5010_TB_SM_M_SSVY_RSV"       , paramMap);       // 재고_실사준비기본
        
        result = paramMap.toString();
        return result;
    }

    //재고실사준비등록 삭제
    @Override
    public String deleteSm5010(LinkedHashMap paramMap) throws Exception {
        String result = "";
        
        // 재고실사 진행 중 재고실사삭제를 진행한 경우 문제가 되지 않게하기 위해.
        result = sqlSession.selectOne("sfmes.sqlmap.sm.searchSm5010_02", paramMap);
        if( result == null ) {
            throw infoException("USERMSG:재고실사 정보가 정상적이지 않습니다. 전산담당자에게 문의하세요.");
        }
        else if( "Y".equals(result) ) {
            throw infoException("USERMSG:이미 재고실사 진행이 삭제된 대상입니다. 조회 후 재확인해 보세요.");
        }
        
        sqlSession.update("sfmes.sqlmap.sm.delete5010_TB_SM_M_SSVY_RSV", paramMap);    
        
        result = paramMap.toString();
        return result;
    }

    // 재고실사준비내역 팝업 기본 조회 
    @Override
    public List<?> searchSm5010P01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.searchSm5010P01",paramMap);
    }
    
    // 재고실사준비내역 팝업 창고상세 조회 
    @Override
    public List<?> searchSm5010P02(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.searchSm5010P02",paramMap);
    }
}

