package com.sfmes.ca.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.ca.service.Ca0200Service;
import com.sfmes.cm.web.MyBuilderData;

/**
 * 채무잔액을 조회.
 * @param "SVCID" : "SEARCH01"
 *        "INMSV01" : Map (아래 정보 기준)
 *        === 채무잔액 조회 입력정보 ===
 *         CORP_C     : 회사코드 
 *         BZPL_C     : 사업장코드
 *         TRPL_C     : 거래처코드(거래처코드나 정산처코드 둘중 하나의 코드만 존재하면 됨)
 *         ADJPL_C    : 정산처코드(거래처코드나 정산처코드 둘중 하나의 코드만 존재하면 됨)
 *         REG_DT     : 등록일자(기준일자)
 *         PRC_TP_C   : 업무구분코드(처리구분코드 ['1':외상매입금]['2':기타미지급금]['3':선수금])
 * @return resultList : "DTBac"(채무잔액) 단건
 * @exception Exception
 */
@Controller
public class Ca0200Controller {

    @Resource(name = "myBuilderData")
    public MyBuilderData myBuilderData;
    
    @Resource(name = "Ca0200Service")
    public Ca0200Service ca0200Service;
    
    @RequestMapping(value = "/Ca0200Controller.do")
    public String Ca0100Controller(HttpServletRequest strData, ModelMap model) throws Exception {

        long lDTBac = 0;
        String strSVCID = null;
        String strINMSV01 = null;
        
        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        strSVCID = myBuilderData.getParam("SVCID");
        strINMSV01 = myBuilderData.getParam("INMSV01");
        
        LinkedHashMap<String, Object> paramMap = myBuilderData.getParamFromMSVHashMap(strINMSV01);
        List<Map<String, Object>> resultList = new ArrayList<>();
        
        switch(strSVCID) {
        
        case "SEARCH01" :
            lDTBac = ca0200Service.getDTBac(paramMap);
            LinkedHashMap<String, Object> resultMap = new LinkedHashMap();
            resultMap.put("DTBac", lDTBac);
            resultList.add(resultMap);
            model.addAttribute("resultList", resultList);
            break;
        }

        return "responseToMybuilder";
    }
}
