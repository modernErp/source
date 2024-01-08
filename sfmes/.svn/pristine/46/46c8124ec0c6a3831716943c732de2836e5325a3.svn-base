package com.sfmes.co.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.Co1070Service;

/**
 * @Class Name  : Co1070ServiceImpl.java
 * @Description : Co1070Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.30   김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.30
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Co1070Service")
public class Co1070SeveiceImpl extends CmnAbstractServiceImpl implements Co1070Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    //거래처물품연결내역
    @Override
    public List<?> selectCo1070(LinkedHashMap paramMap) {
        egovLogger.debug("************ 거래처물품연결내역[CO1070] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());         
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo1070",paramMap);
    }    

    //거래처물품연결등록
    @Override
    public void saveCo1070(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 거래처물품연결등록[CO1070] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        egovLogger.debug("paramList: "+paramList.toString());
        
        int M_CNT = 0;
        String bzplC = (String)paramMap.get("BZPL_C");
        for(Map<String, Object> map : paramList) {
            //각row정보에 사업장 정보 추가
            map.put("BZPL_C", bzplC);
            //가져온 row의 상태 값에 따라서 추가('+'), 수정('*'), 삭제('-')를 구분
            String rowStatus = (String)map.get("_status_");
            
            //insert, update시 당사 물품 validation체크
            M_CNT = sqlSession.selectOne("sfmes.sqlmap.co.validationCo0090", map);
            if(rowStatus.equals("-")) {
                egovLogger.debug("삭제 map: " + map);
                sqlSession.update("sfmes.sqlmap.tb.delete_TB_CO_M_TRPL_GDS", map);
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_L_TRPL_GDS", map);  
            } else if(rowStatus.equals("+")) {
                if(M_CNT > 0) {
                    throw infoException("등록된 물품이 존재합니다.");
                }
                egovLogger.debug("입력 map: " + map);
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_TRPL_GDS", map);  
            } else if(rowStatus.equals("*")) {
            	egovLogger.debug("수정 map: " + map);
            	//update 시 기존 정보 조회
            	LinkedHashMap rowInfo = sqlSession.selectOne("sfmes.sqlmap.tb.select_TB_CO_M_TRPL_GDS", map);
            	String orgTrplC = (String)rowInfo.get("TRPL_C");
            	String orgGdsC = (String)rowInfo.get("GDS_C");
            	String paramTrplC = (String)map.get("TRPL_C");
            	String paramGdsC = (String)map.get("GDS_C");
            	
            	//기존 거래처, 당사물품정보와 입력된 정보가 다를경우 진입
            	if(!orgTrplC.equals(paramTrplC) || !orgGdsC.equals(paramGdsC)) {
            		//거래처의 당사물품 중복시 진입
            		if(M_CNT > 0) {
                        throw infoException("등록된 물품이 존재합니다.");
                    }
            	}
            	
                sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_TRPL_GDS", map);
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_L_TRPL_GDS", map);  
            }
        }
    }    
}
