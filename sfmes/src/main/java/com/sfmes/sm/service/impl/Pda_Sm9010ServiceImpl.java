package com.sfmes.sm.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Pda_Sm9010Service;


/**
 * @Class Name  : Pda_Sm9010ServiceImpl.java
 * @Description : Pda_Sm9010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2021.10.07   이동훈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2021.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Pda_Sm9010Service")
public class Pda_Sm9010ServiceImpl extends CmnAbstractServiceImpl implements Pda_Sm9010Service {
 
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Resource(name = "Pda_Sm9010Service")
    private Pda_Sm9010Service pdasm9010Service;
    
    //물품 기본조회
    @Override
    public List<?> search_Pda_Sm9010_gds(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 물품기본조회[PDA_SM9010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Pda_Sm9010_gds",paramMap);
    }
    
   //거래처 기본조회
    @Override
    public List<?> search_Pda_Sm9010_trpl(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 거래처기본조회[PDA_SM9010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());        
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Pda_Sm9010_trpl",paramMap);
    }
    
    //PDA 저장 
    @Override
    public List<?> insertinfopda(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 저장[PDA_SM] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());            
        return sqlSession.selectList("sfmes.sqlmap.sm.insertinfopda",paramMap);
    	
    }
    
  //PDA 오프라인 저장 
    @Override
    public List<?> insertoffdata(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 오프라인 저장[PDA_SAVEOFF] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());            
        
        
        
        String s_CORP_C = paramMap.get("CORP_C").toString();
        String s_BZPL_C = paramMap.get("BZPL_C").toString();
        String s_TR_DT = paramMap.get("TR_DT").toString();
        
        String seqNo = commonService.getGvno(s_CORP_C, "TB_SM_D_GDS_OFF", s_BZPL_C, s_TR_DT, 1);
        egovLogger.debug("생성된 매입일련번호 채번: " + seqNo);
        
        paramMap.put("OFF_SQNO", seqNo);
        
        String save_TR_DT = paramMap.get("TR_DT").toString();
        
        
        
        
//        String in_data_path = "BulkColumn as IN_DATA";
//        String data_path = "BULK 'C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\sfmes\\mybuilder_PDA\\Files\\Work\\"+save_TR_DT+".txt',SINGLE_CLOB";
        
//        paramMap.put("IN_DATA_PATH", in_data_path);
//        paramMap.put("DATA_PATH", data_path);
        
        
        return sqlSession.selectList("sfmes.sqlmap.tb.insert_TB_SM_D_GDS_OFF",paramMap);
    	
    }

    //PDA 박스이동 
    @Override
    public List<?> insertmovebox(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 박스이동저장[PDA_SM] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());            
        return sqlSession.selectList("sfmes.sqlmap.sm.insertmovebox",paramMap);
        
    }
    
    //PDA 지시번호 조회 프로시저
    @Override
    public List<?> selectEmesbarcode(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 지시번호 조회[PDA_SM9010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());            
        return sqlSession.selectList("sfmes.sqlmap.sm.selectEmesbarcode",paramMap);
    }

   //사업장 기본창고조회
    @Override
    public List<?> search_Pda_Sm9010_whse(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 사업장기본창고조회[PDA_SM9010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        List resultList = sqlSession.selectList("sfmes.sqlmap.sm.select_Pda_Sm9010_whse",paramMap);
        egovLogger.debug("resultList: "+ resultList.toString());
        
        return resultList;
    }
    
  //사업장 기본창고조회
    @Override
    public List<?> search_Pda_Sm9010_whse_list(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 사업장창고조회[PDA_SM9010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        List resultList = sqlSession.selectList("sfmes.sqlmap.sm.select_Pda_Sm9010_whse_list",paramMap);
        egovLogger.debug("resultList: "+ resultList.toString());
        
        return resultList;
    }
    
  //사업장 창고명 조회
    @Override
    public List<?> search_Pda_Sm9010_whse_nm(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ 사업장창고명조회[PDA_SM9010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        List resultList = sqlSession.selectList("sfmes.sqlmap.sm.select_Pda_Sm9010_whse_nm",paramMap);
        egovLogger.debug("resultList: "+ resultList.toString());
        
        return resultList;
    }
    
    
    
  //PDA 입고등록 물품바코드 조회 프로시저
    @Override
    public List<?> selectTmfbarcode(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 입고 물품바코드 조회[PDA_SM9010] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());            
        return sqlSession.selectList("sfmes.sqlmap.sm.selectTmfbarcode",paramMap);
    
    }
    
  //PDA 제품출고 물품바코드 조회 프로시저
    @Override
    public List<?> selectgetprddata(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 제품출고 물품바코드 조회[PDA_SM9020] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());            
        return sqlSession.selectList("sfmes.sqlmap.sm.selectgetprddata",paramMap);
    
    }
    
  //PDA 투입지시 물품바코드 조회 프로시저
    @Override
    public List<?> selectgetboxdata(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 투입지시 물품바코드 조회[PDA_SM9030] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());            
        return sqlSession.selectList("sfmes.sqlmap.sm.selectgetboxdata",paramMap);
    
    }
    
  //PDA 입고반품 물품바코드 조회 프로시저
    @Override
    public List<?> selectgetpucboxdata(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 입고반품 물품바코드 조회[PDA_SM9015] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());            
        return sqlSession.selectList("sfmes.sqlmap.sm.selectgetpucboxdata",paramMap);
    
    }
    
  //PDA 창고 이동 박스 조회 프로시저
    @Override
    public List<?> selectgetmoveboxdata(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 창고 이동 박스 조회[PDA_SM9050] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());            
        return sqlSession.selectList("sfmes.sqlmap.sm.selectgetmoveboxdata",paramMap);
    
    }
    
  //PDA 생산투입할당 박스 조회 프로시저
    @Override
    public List<?> selectgetboxdataO5(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 생산투입할당 조회[PDA_SM9050] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());            
        return sqlSession.selectList("sfmes.sqlmap.sm.selectgetboxdataO5",paramMap);
    
    }
    
   //PDA 박스코드정리 박스조회
    @Override
    public List<?> selectcollectboxdata(LinkedHashMap paramMap) throws Exception {
        egovLogger.debug("************ PDA 박스코드정리 조회[PDA_SM9045] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());            
        return sqlSession.selectList("sfmes.sqlmap.sm.selectcollectboxdata",paramMap);
    
    }
    
}
