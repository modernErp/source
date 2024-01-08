package com.sfmes.by.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.by.service.By2130Service_4;
import com.sfmes.cm.web.MyBuilderData;


@Controller
public class By2130Controller_4 {

    @Resource(name = "By2130Service_4")
    private By2130Service_4 by2130Service_4;
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    
    @RequestMapping(value = "/by2130Controller_4.do")
    public String By2130Controller_4(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null;
        String result = "";
        
        myBuilderData.setParam(StrData);
        
        strSVCID = myBuilderData.getParam("SVCID");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02"));
        
        switch(strSVCID) {
        
            case "SEARCH01" :
                List<?> resultList01 = by2130Service_4.select01By2130List(paramMap);
                model.addAttribute("resultList", resultList01);
                break;
            case "SEARCH02" :
                List<?> resultList02 = by2130Service_4.select02By2130List(paramMap);
                model.addAttribute("resultList", resultList02);
                break;
                
            case "SEARCH03" :
                List<?> resultList03 = by2130Service_4.select03By2130List(paramMap);
                model.addAttribute("resultList", resultList03);
                break;
                
            case "SEARCH04" :
                List<?> resultList04 = by2130Service_4.select04By2130List(paramMap);
                model.addAttribute("resultList", resultList04);
                break;
        }
        
        return "responseToMybuilder";
    }
}
