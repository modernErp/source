package com.sfmes.sm.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.*;
import com.sfmes.sm.service.Sm1020Service;

/**
 * @Class Name  : Sm1020ServiceImpl.java
 * @Description : Sm1020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.08.05   김지혜      최초생성
 * @ 2020.12.28   이동훈      변경
 *
 * @author (주)모든솔루션
 * @since 2020.08.05
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sm1020Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Sm1020Service")
    private Sm1020Service callService;
    
    @RequestMapping(value = "/sm1020Controller.do")
    public String sm1020Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01  = null;
        String inMSV02  = null;
        String strMsg   = null;
        String result   = "";

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01  = myBuilderData.getParam("INMSV01");
        inMSV02  = myBuilderData.getParam("INMSV02");
                  
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(inMSV02);
        List<?> resultList = null;
        
        switch(strSVCID) {
        
        case "SAVE01" :
            System.out.println("paramMap for INSERT ::::: "+paramMap);
            System.out.println("paramList for INSERT ::::: "+paramList);
            result = callService.saveSm1020(paramMap, paramList);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("DLR_RQT_DT").toString());
            model.addAttribute("returnValue02", paramMap.get("DLR_RQT_SQNO").toString());
            break;
            
        case "UPDATE01" :
            System.out.println("paramMap for UPDATE ::::: "+paramMap);
            System.out.println("paramList for UPDATE ::::: "+paramList);
            result = callService.updateSm1020(paramMap, paramList);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("DLR_RQT_DT").toString());
            model.addAttribute("returnValue02", paramMap.get("DLR_RQT_SQNO").toString());
            break;
            
        case "SEARCH01" : 
            resultList = callService.searchSm1020_01(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
            
        case "SEARCH02" : 
            resultList = callService.searchSm1020_02(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
            
        case "SEARCH03" : 
            resultList = callService.searchSm1020P01(paramMap);
            model.addAttribute("resultList", resultList);           
            break;

        }
        return "responseToMybuilder";
    }
}