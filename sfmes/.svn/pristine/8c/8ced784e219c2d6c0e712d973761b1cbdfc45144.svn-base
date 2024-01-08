package com.sfmes.ge.web;

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
import com.sfmes.ge.service.Ge1011Service;
import com.sfmes.ge.service.Ge3010Service;
/**
 * @Class Name  : Ge3010Controller.java
 * @Description : A/S 등록 수정 Controller
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2022.03.16  나명우  최초생성
 * 
 *
 * @author (주)모든솔루션
 * @since 2022.03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Ge3010Controller {
    /** EgovSampleService */
    @Resource(name = "Ge3010Service")
    private Ge3010Service ge3010service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/ge3010Controller.do")
    public String ge3010Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String result = "";
        
        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02"));
        
        switch(strSVCID) {
        case "INSERT" :
            result = ge3010service.insertGe3010(paramMap);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("AS_DT").toString());
            model.addAttribute("returnValue02", paramMap.get("AS_SQNO").toString());        
            break;
            
        case "SEARCH01" :
            List<?> resultList01 = ge3010service.selectGe3010_01(paramMap);
            model.addAttribute("resultList", resultList01);
            break;
            
        case "SEARCH02" :
            List<?> resultList02 = ge3010service.selectGe3010_02(paramMap);
            model.addAttribute("resultList", resultList02);
            break;
            
        case "UPDATE" :
            result = ge3010service.updateGe3010(paramMap);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("AS_DT").toString());
            model.addAttribute("returnValue02", paramMap.get("AS_SQNO").toString());            
            break;  
        }
    
        
        return "responseToMybuilder";
    }
}
