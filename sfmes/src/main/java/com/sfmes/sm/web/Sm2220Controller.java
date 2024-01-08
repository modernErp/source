package com.sfmes.sm.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.*;
import com.sfmes.sm.service.Sm2220Service;
@Controller
public class Sm2220Controller {


    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Sm2220Service")
    private Sm2220Service callService;

    @RequestMapping(value = "/Sm2220Controller.do")
    public String Sm2220Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01 = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");

        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<?> resultList = null;

        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
            case "SEARCH01" :
                // 출고처원장조회
                resultList = callService.selectSm2220List(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH02" :
                // 출고처원장조회
                resultList = callService.selectSm2220List2(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
        }

        return "responseToMybuilder";
    }
}