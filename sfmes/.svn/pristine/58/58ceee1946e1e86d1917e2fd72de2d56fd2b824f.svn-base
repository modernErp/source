package com.sfmes.dl.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.dl.service.Dl1250Service;

/**
 * @Class Name  : Dl1250ServiceImpl.java
 * @Description : 마감등록(김주경사원테스트)
 * @Modification Information
 * @
 * @  수정일              수정자                수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2022.09.19    김주경               최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.09.19
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Dl1250Service")
public class Dl1250ServiceImpl extends CmnAbstractServiceImpl implements Dl1250Service{

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 마감등록조회
    @Override
    public List<?> selectDl1250List_01(LinkedHashMap paramMap) throws Exception {
       egovLogger.debug("********** 마감등록조회[Dl1250] **********");
       egovLogger.debug("paramMap:" + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.dl.selectDl1250List_01", paramMap);
    }

    @Override
    public void updateDl1250List_01(List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("********** 마감등록삭제[Dl1250] **********");
        egovLogger.debug("paramList: " + paramList.toString());
        
        // 사용여부 변경 일괄 처리
        for (int i = 0; i < paramList.size(); i++) {
            
            egovLogger.debug("in loop idx @@@ [" + i + "]" + paramList.get(i));
           
            sqlSession.update("sfmes.sqlmap.dl.updateDl1250List_01", (LinkedHashMap)paramList.get(i));
        }
    }
    
    
    
}
