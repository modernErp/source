/**
 * 
 */
package com.sfmes.ge.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.ge.service.Ge1040Service;
import com.sfmes.ge.service.Ge3010Service;
import com.sfmes.ge.service.Ge3015Service;

/**
 * @Class Name  : Ge3015ServiceImpl.java
 * @Description : A/S접수
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2022.03.16  나명우  최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Ge3015Service")
public class Ge3015ServiceImpl extends CmnAbstractServiceImpl implements Ge3015Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
   
    @Override
    public List<?> selectGe3015_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************* AS내역 조회 [GE3015] ****************");
        egovLogger.debug("paramMap: " + paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe3015_01", paramMap);
       
    }

}
