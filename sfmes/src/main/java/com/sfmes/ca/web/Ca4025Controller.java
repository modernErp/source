package com.sfmes.ca.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.ca.service.Ca4025Service;
import com.sfmes.cm.web.MyBuilderData;

@Controller
public class Ca4025Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Ca4025Service")
    protected Ca4025Service callService;
    
    @RequestMapping(value = "/Ca4025Controller.do")
    public String Ca4025Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        
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
            resultList = callService.selectCa4020List(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH02" :
            resultList = callService.selectCa4020List_2(paramMap);
            model.addAttribute("resultList", resultList);
            break;
        }

        return "responseToMybuilder";
    }
}
