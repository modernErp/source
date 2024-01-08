package com.sfmes.se.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.se.service.Se2020Service;

/**
 * @Class Name : Se2020ServiceImpl.java
 * @Description : 온라인몰수주일괄등록/수정 및 조회
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.03  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Se2020Service")
public class Se2020ServiceImpl extends CmnAbstractServiceImpl implements Se2020Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;        

    //온라인몰수주일괄등록내역
    @Override
    public List<?> selectSe2020_01(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 온라인몰수주일괄등록내역[SE2020] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe2020_01", paramMap);
    }
    
    //거래처물품연결내역 조회
    @Override
    public List<?> selectSe2020_03(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        List<Map<String, Object>> resultList = new LinkedList();
        egovLogger.debug("************ 거래처물품연결내역Mapping조회[SE2020] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        for(Map<String, Object> map : paramList) {
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            resultList.addAll(sqlSession.selectList("sfmes.sqlmap.se.selectSe2020_03", map));
            egovLogger.debug("resultList: " + resultList.toString());
        }
        return resultList;
    }    

    //온라인몰수주일괄등록내역찾기팝업
    @Override
    public List<?> selectSe2020_02(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 온라인몰수주일괄등록내역찾기팝업[SE2020P01] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.se.selectSe2020_02", paramMap);
    }

    //온라인몰수주일괄등록
    @Override
    public void insertSe2020(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 온라인몰수주일괄등록[SE2020] *********");
        egovLogger.debug("paramMap: "  + paramMap.toString());
        egovLogger.debug("paramList: " + paramList.toString());
        
        //변수 초기화
        String resultMsg = "";
        int Cnt = 0;
        int s_VAT = 0;
        int s_SPY_AM = 0;
        
        //오늘날짜구하기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());       
        //채번을 위한 변수설정
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();         
        //채번 서비스 호출(등록일련번호)
        String seqNo_rg = commonService.getGvno(s_CORP_C, "TB_SE_M_ONL_OPR", s_BZPL_C, strToday, 1);
        
        //온라인몰수주일괄등록 저장
        egovLogger.debug("온라인주문등록 TB_SE_M_ONL_OPR");
        for(Map<String, Object> map : paramList) 
        {
            //Validation Check 실패 시, 예외 메세지 발생
            resultMsg = sqlSession.selectOne("sfmes.sqlmap.se.selectSe2020_Insert_ValidCheck", map);
            if(!"OK".equals(resultMsg))
            {
                throw infoException(resultMsg);
            }
            else 
            {
                Cnt = sqlSession.selectOne("sfmes.sqlmap.se.selectSe2020_trpl", map);
                if(Cnt < 1) 
                {
                    throw infoException("거래처코드 : ["+map.get("TRPL_C").toString()+"]\n"+
                                        "거래처명   : "+map.get("TRPL_NM").toString()+"\n"+
                                        "해당 거래처는 매출계약되지 않은 거래처입니다.");
                }
                map.put("BZPL_C", paramMap.get("BZPL_C"));            
                //배송고객내역생성
                //채번 서비스 호출(배송고객등록일련번호)
                String seqNo_cus = commonService.getGvno(s_CORP_C, "TB_SE_M_DVY_CUS", s_BZPL_C, strToday, 1);
                map.put("DVY_CUS_REG_DT", strToday);
                map.put("DVY_CUS_REG_SQNO", seqNo_cus);
                //배송고객내역 저장
                egovLogger.debug("배송고객내역등록 TB_SE_M_DVY_CUS" + map.toString());
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_DVY_CUS", map);
    
                //수주내역생성
                //채번 서비스 호출(수주일련번호일련번호)
                String seqNo_rvo = commonService.getGvno(s_CORP_C, "TB_SE_M_RVO", s_BZPL_C, strToday, 1);
                map.put("RVO_DT", strToday);
                map.put("RVO_SQNO", seqNo_rvo);
                //거래처를 수령처로 
                map.put("RCPL_C", map.get("TRPL_C").toString());
                //세액,공급가액 계산(수주내역에 반영)
                s_VAT = Integer.parseInt(map.get("OPR_AM").toString()) / 10;
                s_SPY_AM = Integer.parseInt(map.get("OPR_AM").toString()) - s_VAT;
                map.put("VAT", s_VAT);
                map.put("SPY_AM", s_SPY_AM);
                
                egovLogger.debug("OPR_QT" +map.get("OPR_QT"));
                egovLogger.debug("OPR_AM"+ map.get("OPR_AM"));
                egovLogger.debug("SPY_AM" + map.get("SPY_AM"));
                egovLogger.debug("VAT" + map.get("VAT"));

                //수주내역 저장
                egovLogger.debug("수주기본내역등록 TB_SE_M_RVO" + map.toString());
                sqlSession.insert("sfmes.sqlmap.se.insertSe2020_TB_SE_M_RVO", map);
                egovLogger.debug("수주상세내역등록 TB_SE_D_RVO" + map.toString());
                sqlSession.insert("sfmes.sqlmap.se.insertSe2020_TB_SE_D_RVO", map);
    
                //온라인몰주문내역생성
                map.put("RG_DT", strToday);
                map.put("RG_SQNO", seqNo_rg);
                //온라인몰주문내역 저장
                egovLogger.debug("온라인몰조몬내역등록 TB_SE_M_ONL_OPR" + map.toString());
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SE_M_ONL_OPR", map);
            }
        }
    }
}
