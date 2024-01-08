package com.sfmes.co.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.Co1040Service;

/**
 * @Class Name : Co1040ServiceImpl.java
 * @Description : 물품 등록/수정/조회
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.03  여다혜  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.03
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Co1040Service")
public class Co1040ServiceImpl extends CmnAbstractServiceImpl implements Co1040Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    //CO1040P01_물품목록조회(공통용 팝업) - 기준정보성
    @Override
    public List<?> select_GDS_List(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.select_GDS_List", paramMap);
    }    

    //CO1040P01_물품이력조회(공통용 팝업) - 기준정보성
    @Override
    public List<?> select_GDS_List1(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.select_GDS_List1", paramMap);
    }   
    
    //CO1040P02_물품목록조회(업무용 팝업) - 매입 / 발주 / 생산 / 등
    @Override
    public List<?> select_GDS_List_ForWork(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.select_GDS_List_ForWork", paramMap);
    }
    
    //CO1040_물품조회(1건, GDS_C 조회)
    @Override
    public List<?> select_GDS_ONE(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_CO_M_GDS", paramMap);
    }

    //CO1040_물품등록(1건)
    @Override
    public String insert_Gds(LinkedHashMap paramMap) throws Exception {
        //저장 전 Validation check
        String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.co.selectCo1040_Gds_Insert_ValidCheck", paramMap);

        String gds_no = null;      //물품코드
        String rep_gds_no = null;  //대표물품코드
        String digi_gds_no = null; //DIGI장비코드
        
        //Validation Check 실패 시, 예외 메세지 발생
        if(!"OK".equals(resultMsg)){
           throw infoException(resultMsg);
        }else {
           //채번(물품코드와 DIGI장비코드는 동일하게 채번)  ===> 회사 내 물품코드는 1개이여야만 한다.
           gds_no = sqlSession.selectOne("sfmes.sqlmap.co.select_AutoNum_GDS_C", paramMap);      //물품코드     : 물품유형1자리 + 채번
           // 2021.01.20 JKS 수정
           // DIGI_GDS_C 는 화면에서 전달받는것으로 처리
           //rep_gds_no = sqlSession.selectOne("sfmes.sqlmap.co.select_AutoNum_GDS_C", paramMap);  //대표물품번호 : 채번된 물품코드(최초 등록 시)  
           //digi_gds_no = sqlSession.selectOne("sfmes.sqlmap.co.select_AutoNum_GDS_C", paramMap); //DIGI장비코드 : 채번된 물품코드
           rep_gds_no = gds_no;  //대표물품번호 : 채번된 물품코드(최초 등록 시)

           paramMap.put("GDS_C", gds_no);    //물품코드
           paramMap.put("REP_GDS_C", rep_gds_no); //대표물품번호
           paramMap.put("DIGI_GDS_C", gds_no); //DIGI장비코드
            
           //물품저장
           sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_GDS", paramMap);
           
           //회사물품저장
           sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_GDS_BAS", paramMap);
        }
        
        //저장 후 채번된 물품코드 Return(저장된 물품을 재조회 하기 위한 물품코드)
        return gds_no;
    }
    
    //CO1040_물품수정(1건)
    @Override
    public void update_Gds(LinkedHashMap paramMap) throws Exception {
        //수정 전 Validation check
        String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.co.selectCo1040_Gds_Update_ValidCheck", paramMap);
        
        //Validation Check 실패 시, 예외 메세지 발생
        if(!"OK".equals(resultMsg)){
            throw infoException(resultMsg);
        }else {
           //물품수정
           //paramMap.put("DIGI_GDS_C", (String)paramMap.get("GDS_C")); //DIGI장비코드 : 물품코드
           System.out.println("digi_gds_c :::" + paramMap.get("DIGI_GDS_C"));
           sqlSession.update("sfmes.sqlmap.tb.update_TB_CO_M_GDS", paramMap);
           //물품수정시 이력남기기
           sqlSession.update("sfmes.sqlmap.tb.insert_TB_CO_L_GDS", paramMap);
        }
    }

    //표준부위코드(대분류)조회
    @Override
    public List<?> select_LATC_LCLC(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo1040P02_LATC_LCLC", paramMap);
    }

    //표준부위코드(중분류)조회
    @Override
    public List<?> select_LATC_MCLC(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo1040P02_LATC_MCLC", paramMap);
    }
    
    //표준부위코드(소분류)조회
    @Override
    public List<?> select_LATC_SCLC(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.selectCo1040P02_LATC_SCLC", paramMap);
    }

    //물품분류코드(전체)조회
    @Override
    public List<?> select_GDS_CLF(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.select_ALL_GDS_CLF", paramMap);
    }

    //CO1040_물품번호(CORP_C)자동생성(채번)
    @Override
    public List<?> select_Auto_GDS_C(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.co.select_AutoNum_GDS_C", paramMap);
    }
}
