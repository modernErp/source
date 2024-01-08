package com.sfmes.ca.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.ca.service.Ca3010Service;
import com.sfmes.cm.web.MyBuilderData;

@Controller
public class Ca3010Controller {

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @Resource(name = "Ca3010Service")
    private Ca3010Service callService;
    
    @RequestMapping(value = "/Ca3010Controller.do")
    public String Ca3010Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        
        String strSVCID = null;
        String strINMSV01 = null;
        
        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        strSVCID = myBuilderData.getParam("SVCID");
        strINMSV01 = myBuilderData.getParam("INMSV01");
        
        LinkedHashMap<String, Object> paramMap = myBuilderData.getParamFromMSVHashMap(strINMSV01);
        List<?> resultList = null;
        
        switch(strSVCID) {
        
        case "SEARCH01" :
            resultList = callService.selectCa3010List(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "INSERT" :
            callService.insertCa3010One(paramMap);
            model.addAttribute("result", "OK");
            break;
        }
        
        return "responseToMybuilder";
    }
}
