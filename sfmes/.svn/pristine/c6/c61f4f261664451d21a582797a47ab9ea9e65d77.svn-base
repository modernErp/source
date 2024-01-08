package com.sfmes.sy.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.sy.service.Sy4010Service;

/**
 * @Class Name  : Sy4010erviceImpl.java
 * @Description : Sy4010ServiceImpl Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  ---------   -------------------------------
 * @ 2020.06.19   김수민      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Sy4010Service")
public class Sy4010ServiceImpl extends CmnAbstractServiceImpl implements Sy4010Service {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    /**
     * 시스템사용이력 등록
     */
    @Override
    public void insertSy4010(LinkedHashMap paramMap) throws Exception {
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_L_PGM_UG",paramMap);
        
    }
    
    /**
     * 시스템사용이력 조회
     */
    @Override
    public List<?> selectSy4010UgList(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy4010UgList",paramMap);
    }

    /**
     * 시스템사용이력상세 조회
     */
    @Override
    public List<?> selectSy4010UgDetail(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("++++++++++++++++++++++"+paramMap);
        return sqlSession.selectList("sfmes.sqlmap.sy.selectSy4010UgDetail",paramMap);
    }

}
