package com.sfmes.dl.web;

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
import com.sfmes.dl.service.Dl1030Service;
@Controller
public class Dl1030Controller {


    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Dl1030Service")
    private Dl1030Service callService;

    @RequestMapping(value = "/Dl1030Controller.do")
    public String Dl1030Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String strinMSV01 = null;
        String strinMSV02 = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        strinMSV01 = myBuilderData.getParam("INMSV01");
        strinMSV02 = myBuilderData.getParam("INMSV02");

        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap<String, Object> paramMap = myBuilderData.getParamFromMSVHashMap(strinMSV01);
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(strinMSV02);
        List<?> resultList = null;

        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
    
        case "SEARCH01" :
            // 회계전표수기등록조회
            resultList = callService.selectDl1030List(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH02" :
            // 회계전표수기등록조회_01
            resultList = callService.selectDl1030List_01(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "INSERT01" :
            // 회계전표수기등록
            callService.insertDl1030List(paramMap, paramList);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("ACG_SQNO").toString());
            break;
        
            
        case "UPDATE01" :
            // 회계전표수기등록수정
            callService.updateDl1030List(paramMap, paramList);
            model.addAttribute("result", "OK");
            break;
            
        }

        return "responseToMybuilder";
    }
}