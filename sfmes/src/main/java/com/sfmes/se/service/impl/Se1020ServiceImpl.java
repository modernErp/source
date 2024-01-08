package com.sfmes.se.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se1020Service;

/**
 * @Class Name : Se1020ServiceImpl.java
 * @Description : 가격군별물품등록
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.06  김선규   최초생성
 * @ 2020.09.02  곽환용   수정
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se1020Service")
public class Se1020ServiceImpl extends CmnAbstractServiceImpl implements Se1020Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;        
    
    //가격군내역
    @Override
    public List<?> selectSe1020_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 가격군내역[SE1020] *********");
        egovLogger.debug("paramMap: "+paramMap);
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SE_M_PRGR", paramMap);        
    }

    //가격군별물품내역
    @Override
    public List<?> selectSe1020_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 가격군별물품내역[SE1020] *********");
        egovLogger.debug("paramMap: "+paramMap);
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SE_M_PRGR_GDS", paramMap);
    }    
    
    //도매대출단가조회
    @Override
    public List<?> selectSe1020_03(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 도매매출단가조회[SE1020] *********");
        egovLogger.debug("paramMap: "+paramMap);
        
        //오늘날짜구하기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        
        //오늘자 도매매출단가조회
        paramMap.put("APL_BAS_DT", strToday);
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe1020_UPR", paramMap);
    }    
    
    //최근적용일자의 매출단가만 조회
    @Override
    public List<?> selectSe1020_04(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 최근적용일자의 매출단가만 조회[SE1020] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe1020_MAX", paramMap);
    }    
    
    //가격군등록
    @Override
    public void insertSe1020_01(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 가격군등록[SE1020] *********");
        egovLogger.debug("paramMap: " + paramMap);
        egovLogger.debug("paramList: " + paramList);
                
        //가격군등록
        for(Map<String, Object> map : paramList) 
        {
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            
            egovLogger.debug("************ 가격군등록 TB_SE_M_PRGR *********");
            if(map.get("_status_").equals("+")) 
            {
                egovLogger.debug("입력 map: " + map.toString());
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_PRGR", map); 
            }
            else 
            {
                egovLogger.debug("수정 map: " + map.toString());
                sqlSession.insert("sfmes.sqlmap.tb.update_TB_SE_M_PRGR", map);
            }
        }
    }    
    
    //가격군별물품등록
    @Override
    public void insertSe1020_02(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {        
        egovLogger.debug("************ 가격군별물품등록[SE1020] *********");
        egovLogger.debug("paramMap: " + paramMap);
        egovLogger.debug("paramList: " + paramList);
        
        int chk_cnt = 0;
        for(Map<String, Object> map : paramList) 
        {
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("PRGR_C", paramMap.get("PRGR_C"));
            
            //Validation Check 실패 시, 예외 메세지 발생
            String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.se.selectSe1020_ValidCheck", map);
            if(! resultMsg.equals("OK")) 
            {
                throw infoException(resultMsg);
            } 
            else 
            {                                
                if(map.get("_status_").equals("+")) 
                {
                    egovLogger.debug("************ 등록물품여부체크 ************");
                    chk_cnt = 0;
                    chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe1020_chk1", map);
                    egovLogger.debug("chk_cnt: "+ chk_cnt);
                    if (chk_cnt < 1) 
                    {
                        throw infoException("\n물품코드: " + map.get("GDS_C").toString() +
                                            "\n물품명: " + map.get("GDS_DTL_NM").toString() + 
                                            "\n사업장물품등록에 등록되지 않은 물품입니다.");
                    }
                    
                    egovLogger.debug("************ 물품사용여부체크 ************");
                    chk_cnt = 0;
                    chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe1020_chk2", map);
                    egovLogger.debug("chk_cnt: "+ chk_cnt);
                    if(chk_cnt < 1)
                    {
                        throw infoException("\n물품코드: " + map.get("GDS_C").toString() +
                                            "\n물품명: " + map.get("GDS_DTL_NM").toString() + 
                                            "\n사용중지된 물품입니다. \n사업장물품등록화면에서 해당 물품의 사용여부를 확인하십시요.");
                    }                    
                    
                    egovLogger.debug("************ 등록물품중복체크 ************");
                    chk_cnt = 0;
                    chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe1020_chk3", map);
                    if (chk_cnt > 0) 
                    {
                        throw infoException("\n물품코드: " + map.get("GDS_C").toString() +
                                            "\n물품명: " + map.get("GDS_DTL_NM").toString() +
                                            "\n적용기준일자: " + map.get("APL_BAS_DT").toString() +
                                            "\n동일한 기준일자에 이미 등록된 물품매출단가가 존재합니다.");
                    }
                    
                    egovLogger.debug("가격군별물품등록 TB_SE_M_PRGR_GDS");
                    sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_PRGR_GDS", map);
                } 
                else if(map.get("_status_").equals("*"))
                {
                    egovLogger.debug("************ 등록물품중복여부체크 ************");
                    chk_cnt = 0;
                    chk_cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe1020_chk3", map);
                    egovLogger.debug("chk_cnt: "+ chk_cnt);
                    if(chk_cnt > 0) 
                    {
                        egovLogger.debug("가격군별물품수정 TB_SE_M_PRGR_GDS");
                        sqlSession.update("sfmes.sqlmap.tb.update_TB_SE_M_PRGR_GDS", map);
                    }
                    else
                    {
                        egovLogger.debug("가격군별물품등록 TB_SE_M_PRGR_GDS");
                        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_PRGR_GDS", map);
                    }                    
                }   
                else
                {
                    egovLogger.debug("가격군별물품삭제 TB_SE_M_PRGR_GDS");
                    sqlSession.delete("sfmes.sqlmap.tb.delete_TB_SE_M_PRGR_GDS", map);
                }
                
                egovLogger.debug("가격군별물품이력등록 TB_SE_L_PRGR_GDS");
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_L_PRGR_GDS", map);
            }
        }
    }
}
