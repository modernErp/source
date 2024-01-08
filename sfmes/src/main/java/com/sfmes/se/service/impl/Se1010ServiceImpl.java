package com.sfmes.se.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se1010Service;

/**
 * @Class Name : Se1010ServiceImpl.java
 * @Description : 물품별매출단가등록
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.06  김선규   최초생성
 * @ 2020.09.01  곽환용   수정
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se1010Service")
public class Se1010ServiceImpl extends CmnAbstractServiceImpl implements Se1010Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;        

    //물품변매출단가 조회
    @Override
    public List<?> selectSe1010_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 물품별단가등록조회[SE1010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SE_M_GDS_UPR", paramMap);
    }
    
    //물품변매출단가이력 조회
    @Override
    public List<?> selectSe1010_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 물품별단가이력조회[SE1010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SE_L_GDS_UPR", paramMap);
    }
    
    //매출단가조회
    @Override
    public List<?> selectSe1010_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 매출단가조회[SE1010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe1010_SL_UPR", paramMap);
    }    
    
    //최근적용일자의 매출단가만 조회
    @Override
    public List<?> selectSe1010_04(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 최근적용일자의 매출단가만 조회[SE1010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe1010_MAX", paramMap);
    }    
    
    //물품별매출단가 등록
    @Override
    public void insertSe1010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 물품별매출단가등록[SE1010] *********");
        egovLogger.debug("paramMap: " + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());

        int chk_cnt = 0;
        for(Map<String, Object> map : paramList) 
        {
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            
            //Validation Check 실패 시, 예외 메세지 발생
            String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.se.selectSe1010_ValidCheck", map);
            if(! resultMsg.equals("OK")) 
            {
                throw infoException(resultMsg);
            } 
            else 
            { 
                egovLogger.debug("************ 등록물품여부체크 ************");
                chk_cnt = 0;
                chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe1010_chk1", map);
                egovLogger.debug("chk_cnt: "+ chk_cnt);
                if(chk_cnt < 1) 
                {
                    throw infoException("\n물품코드: " + map.get("GDS_C").toString() +
                                        "\n물품명: " + map.get("GDS_DTL_NM").toString() + 
                                        "\n사업장물품등록에 등록되지 않은 물품입니다.");
                }
                
                egovLogger.debug("************ 물품사용여부체크 ************");
                chk_cnt = 0;
                chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe1010_chk3", map);
                egovLogger.debug("chk_cnt: "+ chk_cnt);
                if(chk_cnt < 1)
                {
                    throw infoException("\n물품코드: " + map.get("GDS_C").toString() +
                                        "\n물품명: " + map.get("GDS_DTL_NM").toString() + 
                                        "\n사용중지된 물품입니다. \n사업장물품등록화면에서 해당 물품의 사용여부를 확인하십시요.");
                }
                
                if(map.get("_status_").equals("+"))
                {
                    egovLogger.debug("************ 등록물품중복여부체크 ************");
                    chk_cnt = 0;
                    chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe1010_chk2", map);
                    egovLogger.debug("chk_cnt: "+ chk_cnt);
                    if(chk_cnt > 0) 
                    {
                        throw infoException("\n물품코드: " + map.get("GDS_C").toString() + 
                                            "\n물품명: " + map.get("GDS_DTL_NM").toString() + 
                                            "\n적용기준일자: " + map.get("APL_BAS_DT").toString() +
                                            "\n동일한 일자에 이미 등록된 물품매출단가가 존재합니다.");
                    }
                    
                    egovLogger.debug("+물품별매출단가등록 TB_SE_M_GDS_UPR");
                    sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_GDS_UPR", map);
                }
                else if(map.get("_status_").equals("*"))
                {
                    egovLogger.debug("************ 등록물품중복여부체크 ************");
                    chk_cnt = 0;
                    chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe1010_chk2", map);
                    egovLogger.debug("chk_cnt: "+ chk_cnt);
                    if(chk_cnt > 0) 
                    {
                        egovLogger.debug("*물품별매출단가수정 TB_SE_M_GDS_UPR");
                        sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_GDS_UPR", map);
                    }
                    else
                    {
                        egovLogger.debug("+물품별매출단가등록 TB_SE_M_GDS_UPR");
                        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_GDS_UPR", map);
                    }
                }
                else 
                {
                    egovLogger.debug("물품별매출단가삭제 TB_SE_M_GDS_UPR");
                    map.put("RMK_CNTN", "삭제");
                    sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_GDS_UPR", map);
                }
                
                egovLogger.debug("물품별매출단가이력등록 TB_SE_L_GDS_UPR");
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_L_GDS_UPR", map);            
            }
        }
    }
}
