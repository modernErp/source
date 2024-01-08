package com.sfmes.dl.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.dl.service.Dl1050Service;

/**
 * @Class Name  : Dl1050ServiceImpl.java
 * @Description : 마감등록
 * @Modification Information
 * @
 * @  수정일              수정자                수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2022.01.27    구민희               최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.01.27
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Dl1050Service")
public class Dl1050ServiceImpl extends CmnAbstractServiceImpl implements Dl1050Service {
        
        @Autowired
        private SqlSessionTemplate sqlSession;
    
        // 마감등록조회
        @Override
        public List<?> selectDl1050List(LinkedHashMap paramMap) throws Exception {
            egovLogger.debug("************ 마감등록조회[Dl1050] *********");
            egovLogger.debug("paramMap:"+ paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.dl.selectDl1050List",paramMap);
        }
        
        // 마감일보조회
        @Override
        public List<?> selectDl1050List_02(LinkedHashMap paramMap) throws Exception {
            return sqlSession.selectList("sfmes.sqlmap.dl.selectDl1050List02", paramMap);
        }
        
        // 마감등록변경
        @Override
        public void updateDl1050List(List<Map<String, Object>> paramList) throws Exception {
            egovLogger.debug("************ 마감등록변경[Dl1050] *********");
            egovLogger.debug("paramlist:"+ paramList.toString());
            
            // 변경 정보 일괄 처리
            for (int i = 0; i < paramList.size(); i++) { 
                
                egovLogger.debug("in loop idx @@@ [" + i + "]" + paramList.get(i));
                
                if ((paramList.get(i)).get("_status_").equals("*")) {
                    sqlSession.update("sfmes.sqlmap.dl.updateDl1050List", (LinkedHashMap) paramList.get(i));
                }
            }
           
        }
        
}
