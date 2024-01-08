package com.sfmes.sm.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;
import com.sfmes.sm.service.Sm1000Service;
import com.sfmes.sm.service.Sm6015Service;

@Service("Sm6015Service")
public class Sm6015ServiceImpl extends CmnAbstractServiceImpl implements Sm6015Service {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Resource(name = "CommonService")
    private CommonService commonService;
    
    @Resource(name = "Sm1000Service")
    private Sm1000Service sm1000Service;
    
    //품원장계수정정내역 조회
    @Override
    public List<?> searchSm6015_01(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.sm.select_Sm6015_01", paramMap);
    }
    
    //품원장계수정정내역 삭제
    @Override
    public void deleteSm6015_01(LinkedHashMap paramMap) throws Exception {
        
        // 원장계수정정 대상의 재고_물품실재고입출고기본 조회
        paramMap.put("STDV_DT", paramMap.get("CRC_DT"));    // 품원장계수정정시 CRC_DT 기준으로 STDV_DT를 세팅하기 때문에 조회 성능을 높이기 위해서 세팅함. 차후 로직적으로 문제가 된다면 제외하여 조회하면 정확히 조회가 될 수 있음. 
        List<Map<String, Object>> paramTmpList = null;
        paramTmpList = sqlSession.selectList("sfmes.sqlmap.sm.select_Sm6015_02", paramMap);
        
        LinkedHashMap<String, Object> paramMap1 = new LinkedHashMap();
        if( paramTmpList == null || paramTmpList.size() == 0 ) {
            throw infoException("USERMSG:품원장계수정정 대상의 재고 전표가 존재하지 않습니다. 전산담당자에게 문의하세요.");
        }
        else {
            for( Map map1 : paramTmpList ) {
                paramMap1.putAll( map1 );
                
            }
        }
        
        if( "N".equals( paramMap1.get("SLP_NML_YN") ) ) {
            throw infoException("USERMSG:이미 삭제된 전표입니다. 전산담당자에게 문의하세요.");
        }
        
        paramMap1.put("SLP_NML_YN", "N");

        // 재고_물품실재고입출고상세 조회
        List<Map<String, Object>> paramList1 = sqlSession.selectList("sfmes.sqlmap.tb.select_TB_SM_D_GDS_RL_STDV", paramMap1);
        
        // 물품 재고 입출고 등록을 한다. [전표정상여부를 'N'으로 세팅하여 기존 전표를 삭제처리한다]
        sm1000Service.Call_saveSm1000(paramMap1, paramList1, null, null);
        
        
        // 재고_원장계수정정기본(전표삭제 상태변경)
        paramMap.put("SLP_NML_YN", "N");
        sqlSession.update("sfmes.sqlmap.sm.update_Sm6015_TB_SM_M_LED_FGS_CRC_01", paramMap);
        
        return;
    }

}
