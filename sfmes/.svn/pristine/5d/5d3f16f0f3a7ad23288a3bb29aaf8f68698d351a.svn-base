package com.sfmes.sm.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Sm1000Service;
import com.sfmes.sm.service.Sm5045Service;

/**
 * @Class Name  : Sm5045ServiceImpl.java
 * @Description : Sm5045Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.11.02   정성환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sm5045Service")
public class Sm5045ServiceImpl extends CmnAbstractServiceImpl implements Sm5045Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Resource(name = "Sm1000Service")
    private Sm1000Service sm1000Service;

    //재고실사확정내역
    @Override
    public List<?> searchSm5045_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm5045_01", paramMap);
    }
    
    //재고실사-전표삭제 및 실사삭제처리
    @Override
    public void deleteSm5045_01(LinkedHashMap paramMap) throws Exception {
        
        int iRet = 0;
        
        // 재고실사확정내역-(실사일련번호1개기준)확정내역조회
        List<LinkedHashMap<String, Object>> paramList = sqlSession.selectList("sfmes.sqlmap.sm.select_Sm5045_02", paramMap);
        
        if( paramList == null ) {
            throw infoException("USERMSG:" + "전표삭제하려는 재고실사확정내역이 존재하지 않습니다." + "전산담당자에게 문의하세요.");
        }
        
        if( paramList.get(0).get("SLP_NML_YN") == null ) {
            throw infoException("USERMSG:" + "전표삭제하려는 전표상태를 확인해주세요." + "전산담당자에게 문의하세요.");
        }
        
        if( "N".equals(paramList.get(0).get("SLP_NML_YN")) ) {
            throw infoException("USERMSG:" + "전표삭제하려는 전표는 이미 삭제되었습니다." + "전산담당자에게 문의하세요.");
        }
        
        List<Map<String, Object>> paramList1 = new ArrayList<Map<String, Object>>(paramList.size());
        List<Map<String, Object>> paramList2 = new ArrayList<Map<String, Object>>(paramList.size());
        List<Map<String, Object>> tempParamListI = new ArrayList<Map<String, Object>>(paramList.size());
        List<Map<String, Object>> tempParamListO = new ArrayList<Map<String, Object>>(paramList.size());
        
        LinkedHashMap paramMap2 = new LinkedHashMap();
        
        int checkFlagO = 0 ;
        int checkFlagI = 0 ;
        
        paramMap2.putAll(paramMap); // 입고 출고용 paramMap 복사
        
        for(Map<String,Object> map1 : paramList )
        {
            if(Double.parseDouble(map1.get("DIF_QT").toString()) > 0)   // 출고
            {
                map1.put("STDV_DSC"             , "O");     // 양수이면 출고
                map1.put("DEL_YN"               , "N");
                map1.put("FLAG_STDV_DSC_IO_YN"  , "Y");   
                tempParamListO.add(map1);
                checkFlagO++;
            }
            else if (Double.parseDouble(map1.get("DIF_QT").toString()) < 0) // 입고
            {
                map1.put("STDV_DSC"             , "I");     // 음수이면 입고
                map1.put("DEL_YN"               , "N");
                map1.put("FLAG_STDV_DSC_IO_YN"  , "Y");   
                tempParamListI.add(map1);
                checkFlagI++;
            }
        }
        
       
        if(checkFlagI > checkFlagO)
        {
            paramMap.put("STDV_DSC" , "I");
            paramMap2.put("STDV_DSC" , "O");
            paramMap.put("SLP_NML_YN" , "N");
            paramMap2.put("SLP_NML_YN" , "N");
            
            paramList1.addAll(tempParamListI);
            paramList2.addAll(tempParamListO);
        }
        else
        {
            paramMap.put("STDV_DSC" , "O");
            paramMap2.put("STDV_DSC" , "I");
            paramMap.put("SLP_NML_YN" , "N");
            paramMap2.put("SLP_NML_YN" , "N");
            
            paramList1.addAll(tempParamListO);
            paramList2.addAll(tempParamListI);
        }

        // 물품 재고 입출고 등록을 한다.
        sm1000Service.Call_saveSm1000(paramMap, paramList1, paramMap2, paramList2  ); 
        
        // 전표삭제상태로 변경
        iRet = sqlSession.delete("sfmes.sqlmap.sm.update_Sm5045_TB_SM_M_SSVY_RSV_01", paramMap);
        egovLogger.debug(" 전표삭제상태로 변경: [" + iRet + "]건");
        
        // 실사삭제상태로 변경
        iRet = sqlSession.delete("sfmes.sqlmap.sm.update_Sm5045_TB_SM_M_SSVY_DFN_01", paramMap);
        egovLogger.debug(" 실사삭제상태로 변경: [" + iRet + "]건");
        
        return;
    }
    
}
