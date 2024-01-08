package com.sfmes.co.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.Co1000Service;


/**
 * @Class Name  : Co1000ServiceImpl.java
 * @Description : 회사정보수정
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.09.04   여다혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.04
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("Co1000Service")
public class Co1000ServiceImpl extends CmnAbstractServiceImpl implements Co1000Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    //회사정보조회
    @Override
    public List<?> selctCorpInfo(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_CO_M_CORP", paramMap);
    }

    //사업장정보조회
    @Override
    public List<?> selectBzplList(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sy.select_bzpl", paramMap);
    }

    //회사, 사업장 정보 수정
    @Override
    public void updateCo1000(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        
        //회사정보 수정
        sqlSession.update("sfmes.sqlmap.co.updateCo1000CorpInfo", paramMap);
        
        //사업장정보 저장(수정 및 등록)
        /*
         *  1. 사업장정보저장(MERGE / INSERT & UPDATE)
         *  2. 사업장환경저장(INSERT)
         *  3. 거래처정보등록(MERGE / INSERT & UPDATE)
         *  4. 가상창고정보등록(INSERT)
         * 
         */
        for(Map<String, Object> map : paramList) {
            sqlSession.insert("sfmes.sqlmap.co.insertCo1000BzplInfo", map); //사업장정보저장(MERGE)
            sqlSession.insert("sfmes.sqlmap.co.insertCo1000TrplInfo", map); //거래처정보저장(MERGE)
            sqlSession.insert("sfmes.sqlmap.sy.insert_l_bzpl", map);        //사업장변경이력(INSERT)
            
            
            //신규사업장 일때는 사업장환경정보, 가상창고정보를 INSERT해준다.
            //사업장 최초 등록 시, 1번만 수행되는 작업 입니다.
            if(map.get("_status_").equals("+")) {
                //Validation Check (사업장중복체크)
                String rtnMsg = sqlSession.selectOne("sfmes.sqlmap.co.selectCo1000ValidationChk", map);
                if(!"OK".equals(rtnMsg)) throw infoException(rtnMsg);

                sqlSession.insert("sfmes.sqlmap.sy.insert_bzpl_env", map); //사업장환경정보(INSERT)
                
                //사업장최초등록한 후 가상창고 최초등록
                map.put("WHSE_C"  , "Z01");
                map.put("WHSE_NM" , "위탁창고");
                map.put("WHSE_DSC", "02");
                map.put("CORP_C"  , map.get("S_CORP_C"));
                map.put("WHSE_CFC", "02");
                map.put("MINUS_YN", "Y");
                map.put("USE_YN"  , "Y");
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_WHSE", map);
                
                map.put("WHSE_C"  , "Z02");
                map.put("WHSE_NM" , "수탁창고");
                map.put("WHSE_DSC", "03");
                map.put("CORP_C"  , map.get("S_CORP_C"));
                map.put("WHSE_CFC", "02");
                map.put("MINUS_YN", "Y");
                map.put("USE_YN"  , "Y");
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_WHSE",map);
                
                map.put("WHSE_C"  , "Z03");
                map.put("WHSE_NM" , "폐기/반품창고");
                map.put("WHSE_DSC", "04");
                map.put("CORP_C"  , map.get("S_CORP_C"));
                map.put("WHSE_CFC", "02");
                map.put("MINUS_YN", "Y");
                map.put("USE_YN"  , "Y");
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_WHSE",map);
                
                map.put("WHSE_C"  , "ZZZ");
                map.put("WHSE_NM" , "생산창고");
                map.put("WHSE_DSC", "05");
                map.put("CORP_C"  , map.get("S_CORP_C"));
                map.put("WHSE_CFC", "02");
                map.put("MINUS_YN", "Y");
                map.put("USE_YN"  , "Y");
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_WHSE",map);
            }
        }
    }
}
