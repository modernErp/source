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
import com.sfmes.pd.service.Pd3030Service;

/**
 * @Class Name  : Pd3030ServiceImpl.java
 * @Description : Pd3030Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.12.07   김수민    최초생성
 * @ 2022.04.05   나명우    원가상태체크 추가
 *
 * @author (주)모든솔루션
 * @since 2020.12.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Pd3030Service")
public class Pd3030ServiceImpl extends CmnAbstractServiceImpl implements Pd3030Service{

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public List<?> selectPd3030_01(LinkedHashMap paramMap) throws Exception {
        //노무비/제조경비 조회
        return sqlSession.selectList("sfmes.sqlmap.pd.select_pd3030List01", paramMap);
        
    }
    

    @Override
    public void insertPd3030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
      
        String resultMsg = (String)sqlSession.selectOne("sfmes.sqlmap.pd.pd3030_ValidChk",paramMap);
        
        if(!"OK".equals(resultMsg)) {
            egovLogger.debug("==================메세지");
            throw infoException(resultMsg);
        }
        
        for(Map<String, Object> map : paramList){
            //노무비/제조경비 등록
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("PCS_CLC_YM", paramMap.get("PCS_CLC_YM"));
            map.put("ACG_DT", paramMap.get("ACG_DT"));
            
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CP_D_MM_ACC_BAC",map);
            
        }
        
    }

    @Override
    public void updatePd3030(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception {
        
        //원가상태 체크 나명우 추가
        int pcsChk = (Integer)sqlSession.selectOne("sfmes.sqlmap.pd.select_pd3030PCS_CHK",paramMap);
        
        if(pcsChk == 1 ) {
            throw infoException("원가계산이후에는 수정 할 수 없습니다.");
        }
        
        for(Map<String, Object> map : paramList){
            
            map.put("CORP_C", paramMap.get("CORP_C"));
            map.put("BZPL_C", paramMap.get("BZPL_C"));
            map.put("PCS_CLC_YM", paramMap.get("PCS_CLC_YM"));
            map.put("ACG_DT", paramMap.get("ACG_DT"));
            
            if(map.get("_status_").equals("*")){
                //노무비/제조경비 수정
                sqlSession.update("sfmes.sqlmap.tb.update_TB_CP_D_MM_ACC_BAC",map);
                
            } else if(map.get("_status_").equals("+")) {
                //노무비/제조경비 등록
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CP_D_MM_ACC_BAC",map);
            
            }
        }
        
    }

}
