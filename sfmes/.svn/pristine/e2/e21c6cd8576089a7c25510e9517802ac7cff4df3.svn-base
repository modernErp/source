package com.sfmes.pd.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.pd.service.Pd1010Service;

/**
* @Class Name : Pd1010ServiceImpl.java
* @Description : Pd1010Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.07.20   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.07.20
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Service("Pd1010Service")
public class Pd1010ServiceImpl extends CmnAbstractServiceImpl implements Pd1010Service{
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;

    @Override
    public void insertPd1010(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        //BOM기본등록
        
        //기본BOM코드가 있는 물품의 BOM코드를 바꾸기 전 이전 기본BOM코드 여부 변경
        if("Y".equals(paramMap.get("BSC_BOM_YN"))) {
            sqlSession.update("sfmes.sqlmap.pd.update_M_BOMYN",paramMap);
        }
        String seqNo = sqlSession.selectOne("sfmes.sqlmap.pd.selectseqNo_01", paramMap);
        paramMap.put("BOM_C", seqNo);
        
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_M_BOM_MFC",paramMap);
        
        //이력저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_L_BOM_MFC_M",paramMap); 
        
        if("Y".equals(paramMap.get("BSC_BOM_YN"))){
            //기본BOM여부 Y일 경우 물품테이블에 기본BOM코드 입력
            sqlSession.update("sfmes.sqlmap.pd.update_BAS_BOM",paramMap);
        }
        
        for(Map<String, Object> map : paramList){            
            //BOM상세내역저장
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("GDS_C", paramMap.get("GDS_C"));
            map.put("BOM_C", paramMap.get("BOM_C"));
            
            String seqNo01 = sqlSession.selectOne("sfmes.sqlmap.pd.selectseqNo_02", map);
            map.put("MTRL_SQNO", seqNo01);
            
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_BOM_MTRL",map);
            
            //이력저장
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_L_BOM_MTRL_D",map);
           
        }
        
    }

    @Override
    public void updatePd1010_01(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
      //BOM기본수정
        
        String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.pd.select_validCheck_01", paramMap);
        if(!"OK".equals(resultMsg)) {
            throw infoException(resultMsg);
        }
        
        //기본BOM코드가 있는 물품의 BOM코드를 바꾸기 전 이전 기본BOM코드 여부 변경
        if(!"".equals(paramMap.get("BOM_C")) && "Y".equals(paramMap.get("BSC_BOM_YN"))) {
            sqlSession.update("sfmes.sqlmap.pd.update_M_BOMYN",paramMap);
                
            //기본BOM여부 Y일 경우 물품테이블에 기본BOM코드 입력
            sqlSession.update("sfmes.sqlmap.pd.update_BAS_BOM",paramMap);
        }
        
        sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_M_BOM_MFC",paramMap);
        
        //이력저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_L_BOM_MFC_M",paramMap);
        
        for(Map<String, Object> map : paramList){
            //BOM상세내역저장
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("GDS_C", paramMap.get("GDS_C"));
            map.put("BOM_C", paramMap.get("BOM_C"));
            
            if(map.get("_status_").equals("+")){
                String seqNo01 = sqlSession.selectOne("sfmes.sqlmap.pd.selectseqNo_02", map);
                map.put("MTRL_SQNO", seqNo01);
                
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_D_BOM_MTRL",map);
                
            } else if(map.get("_status_").equals("*")){
                
                sqlSession.update("sfmes.sqlmap.tb.update_TB_PD_D_BOM_MTRL",map);
                
            } else if(map.get("_status_").equals("-")){
                
                sqlSession.update("sfmes.sqlmap.pd.update_D_USEYN",map);
                
            }
            
            //이력저장
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_L_BOM_MTRL_D",map);
        }
        
    }
    
    @Override
    public void updatePd1010_02(LinkedHashMap paramMap) throws Exception {
        //BOM기본사용여부수정
        sqlSession.update("sfmes.sqlmap.pd.update_M_USEYN",paramMap);
        
        //이력저장
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_PD_L_BOM_MFC_M",paramMap);
        
        
    }

    @Override
    public List<?> selectPd1010List_01(LinkedHashMap paramMap) throws Exception {
        //BOM기본 조회
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd1010_01",paramMap);
    }

    @Override
    public List<?> selectPd1010List_02(LinkedHashMap paramMap) throws Exception {
        //BOM상세 조회
        return sqlSession.selectList("sfmes.sqlmap.pd.selectPd1010_02",paramMap);
    }

}
