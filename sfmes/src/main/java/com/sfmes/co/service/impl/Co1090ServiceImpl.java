package com.sfmes.co.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.Co1090Service;
import com.sfmes.co.service.CommonService;

/**
 * @Class Name : Co1090ServiceImpl.java
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.08.06  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.08.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Co1090Service")
public class Co1090ServiceImpl extends CmnAbstractServiceImpl implements Co1090Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    /**
     * 사업장 수정
     * 
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */

    @Override
    public void insertCo1090(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug(":::::[CO1090INSERT]::::::" + paramMap);
        String resultMsg = sqlSession.selectOne("sfmes.sqlmap.co.selectCo1090ValidCheck", paramMap);
        if (!resultMsg.equals("OK")) {
            throw infoException(resultMsg);
        } else {
            // 사업장환경설정 등록
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_TRPL_BAR_INF", paramMap);
        }
    }

    @Override
    public void updateCo1090(LinkedHashMap paramMap) throws Exception {

        // 사업장수정
        sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_TRPL_BAR_INF", paramMap);
    }

    /**
     * 테스트 데이터를 목록조회한다.
     * 
     * @param paramMap - 조회할 조건이 담긴 Map
     * @return 결과 목록
     * @exception Exception
     */

    @Override
    public List<?> selectCo1090List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo1090List", paramMap);
    }
}
