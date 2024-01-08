package com.sfmes.sy.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy3040Service;

/**
 * @Class Name  : Sy3040ServiceImpl.java
 * @Description : Sy3040Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.18   김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.18
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sy3040Service")
public class Sy3040ServiceImpl extends CmnAbstractServiceImpl implements Sy3040Service {

@Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<?> selectSy3040List(LinkedHashMap paramMap) throws Exception {
        
        List<Map<String, Object>> resMap00 = new ArrayList<Map<String, Object>>();
        resMap00 = sqlSession.selectList("sfmes.sqlmap.sy.selectSy3040List", paramMap);
        
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss",Locale.KOREA);
        
        for(Map<String, Object> map : resMap00) {
            if(null == map.get("BAT_ED_TM") || "".equals(map.get("BAT_ED_TM"))){
                map.put("DIFSEC", 0);
            }else{
                Date stTime = f.parse(map.get("BAT_ST_DTM").toString());
                Date edTime = f.parse(map.get("BAT_ED_DTM").toString()); 

                long getTime = edTime.getTime() - stTime.getTime();
                long diffSec = (getTime / 1000);
              
                map.put("DIFSEC", diffSec);
            }
        }
        return resMap00;
    }
}
