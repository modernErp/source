package com.sfmes.sm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Sm5030Service;


/**
 * @Class Name  : Sm5030ServiceImpl.java
 * @Description : Sm5030Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.28   정성환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm5030Service")
public class Sm5030ServiceImpl extends CmnAbstractServiceImpl implements Sm5030Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
        
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    // 재고실사 진행 중 재고실사 삭제를 진행한 경우 문제가 되지 않게하기 위해.
    protected void checkSSVYDelYN(LinkedHashMap paramMap) throws Exception {
        String result = "";
        
        result = sqlSession.selectOne("sfmes.sqlmap.sm.searchSm5010_02", paramMap);
        if( result == null ) {
            throw infoException("USERMSG:재고실사 정보가 정상적이지 않습니다. 전산담당자에게 문의하세요.");
        }
        else if( "Y".equals(result) ) {
            throw infoException("USERMSG:이미 재고실사 진행이 삭제된 대상입니다. 조회 후 재확인해 보세요.");
        }
    }
    
    //재고실사조정등록 리스트 조회 
    @Override
    public List<?> searchSm5030_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.searchSm5030_01",paramMap);
    }

    @Override
    public String updateSm5030_01(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        String result = "";
        
        // 재고실사 진행 중 재고실사삭제를 진행한 경우 문제가 되지 않게하기 위해.
        checkSSVYDelYN(paramMap);
        
        for(Map<String,Object> map1:paramList)
        {
            //재고실사조정등록 창고상세
            sqlSession.update("sfmes.sqlmap.sm.updateSm5030_TB_SM_D_SSVY_AJ_WHSE"   , map1);
            //재고실사조정등록 상세
            sqlSession.update("sfmes.sqlmap.sm.updateSm5030_TB_SM_D_SSVY_AJ"        , map1);
        }
        
        //재고실사조정등록 기본
        sqlSession.update("sfmes.sqlmap.sm.updateSm5030_TB_SM_M_SSVY_AJ"     , paramMap);
        
        result = paramMap.toString();
        return result;
    }
    
    // 재고실사조정등록 확정 시 
    @Override
    public String updateSm5030_02(LinkedHashMap paramMap) throws Exception {
        String result = "";
        
        // 재고실사 진행 중 재고실사 삭제를 진행한 경우 문제가 되지 않게하기 위해.
        checkSSVYDelYN(paramMap);
        
        //재고실사준비등록 기본 상태값 변경 
        sqlSession.update("sfmes.sqlmap.sm.updateSm5020_01"     , paramMap);
        
        result = paramMap.toString();
        return result;
    }
   
    

}
