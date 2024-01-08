package com.sfmes.dl.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.dl.service.Dl1110Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Dl1110ServiceImpl.java
 * @Description : 회계전표집계표
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.12.07  이수빈  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.12.07
 * @version 1.0
 * @see
 *
 *  dlpyright (C) by 모든솔루션 All right reserved.
 */

@Service("Dl1110Service")
public class Dl1110ServiceImpl extends CmnAbstractServiceImpl implements Dl1110Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    
    // 회계전표집계표
    @Override
    public List<?> selectDl1110List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.dl.selectDl1110List", paramMap);
    }    
}