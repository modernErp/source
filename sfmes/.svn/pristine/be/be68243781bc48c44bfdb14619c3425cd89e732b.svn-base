package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy1035Service;

/**
 * @Class Name : Sy1035ServiceImpl.java
 * @Description : Sy1035ServiceImpl Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.01  김수민      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sy1035Service")
public class Sy1035ServiceImpl extends CmnAbstractServiceImpl implements Sy1035Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<?> selectSy1035List(LinkedHashMap paramMap) throws Exception {
        //사용자내역(사용자찾기팝업)조회
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy1035_01",paramMap);
        
    }

}
