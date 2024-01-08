package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy2020Service;

/**
* @Class Name : Sy2020ServiceImpl.java
* @Description : Sy2020Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.06.09   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.06.09
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/
@Service("Sy2020Service")
public class Sy2020ServiceImpl extends CmnAbstractServiceImpl implements Sy2020Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void saveSy2020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        //프로그램등록
        for (Map<String, Object> map : paramList) {
            map.put("CORP_C", paramMap.get("CORP_C"));
            
            if(map.get("_status_").equals("+")) {
                List<Map<String, Object>> result = sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SY_M_PGID", map);
                if(result.size() > 0) {
                    throw infoException("중복된 프로그램ID가 존재합니다.");
                }
                //프로그램 신규 추가 
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_M_PGID", map); 
                
                //2021.10.23 서광석
                //신규프로그램등록시 역할별프로그램 자동생성기능 추가 
                sqlSession.insert("sfmes.sqlmap.sy.insertSy2020_RolePgm", map);                
            } else if(map.get("_status_").equals("*")) {
                //프로그램 수정 
                sqlSession.insert("sfmes.sqlmap.tb.update_TB_SY_M_PGID", map); 
                
            }
         }
    }

    @Override
    public List<?> selectSy2020_01(LinkedHashMap paramMap) throws Exception {
        //프로그램내역&팝업조회
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy2020_01",paramMap);
    }

}
