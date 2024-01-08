package com.sfmes.pd.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.co.service.Co1010Service;
import com.sfmes.pd.service.Pd4020Service;

/**
* @Class Name : Pd4020Controller.java
* @Description : Pd4020Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.22   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.22
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Pd4020Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Pd4020Service")
    private Pd4020Service callService;
    
    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @exception Exception
     */
    @RequestMapping(value = "/pd4020Controller.do")
    public String pd4020Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        String strSVCID = null;
        String inMSV01 = null;
        String inMSV02 = null;

        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        inMSV02 = myBuilderData.getParam("INMSV02");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(inMSV02);
        List<?> resultList01 = null;
        List<?> resultList02 = null;
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
        case "INSERT" :  //수탁가공재료 입출고내역 등록
        	callService.insertPd4020(paramMap, paramList);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01");
            break;
            
        case "UPDATE" :  //수탁가공재료 입출고내역 수정
            callService.updatePd4020(paramMap, paramList);
            model.addAttribute("result", "OK");
            break;
            
        case "SEARCH01" :  //수탁가공접수 조회
        	resultList01 = callService.selectPd4020_01(paramMap);
        	model.addAttribute("resultList", resultList01);
        	break;
            
        case "SEARCH02" :  //수탁가공재료 인수내역 조회
        	resultList02 = callService.selectPd4020_02(paramMap);
        	model.addAttribute("resultList", resultList02);
        	break;
        }
        
        return "responseToMybuilder";
       
    }

}
