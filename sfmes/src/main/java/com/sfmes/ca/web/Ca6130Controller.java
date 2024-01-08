package com.sfmes.ca.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.*;
import com.sfmes.ca.service.Ca6130Service;
@Controller
public class Ca6130Controller {


    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Ca6130Service")
    private Ca6130Service callService;

    @RequestMapping(value = "/Ca6130Controller.do")
    public String Ca6130Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
            // 선수금잔액장조회
            resultList = callService.selectCa6130List(paramMap);
            model.addAttribute("resultList", resultList);
            break;
        }

        return "responseToMybuilder";
    }
}