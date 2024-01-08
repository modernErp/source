package com.sfmes.dl.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.*;
import com.sfmes.dl.service.Dl1040Service;
@Controller
public class Dl1040Controller {


    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Dl1040Service")
    private Dl1040Service callService;

    @RequestMapping(value = "/Dl1040Controller.do")
    public String Dl1040Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
            // 영업등록마감조회
            resultList = callService.selectDl1040List(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH02" :
            // 영업등록마감팝업조회
            resultList = callService.selectDl1040List_01(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH03" :
            // 마감일보를 조회한다.
            resultList = callService.selectDl1040List_02(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "INSERT" :
            // 영업등록마감팝업조회
            callService.insert1040List(paramMap);
            model.addAttribute("result", "OK");
            break;
            
        case "INSERT02" :
            // 최종마감등록 처리
            callService.insert1040Last(paramMap);
            model.addAttribute("result", "OK");
            break;
            
        case "UPDATE" :
            // 영업등록마감팝업수정
            callService.update1040List(paramMap);
            model.addAttribute("result", "OK");
            break;
            
        case "DELETE" :
            // 최종마감취소 처리
            callService.insert1040LastCancle(paramMap);
            model.addAttribute("result", "OK");
            break;
            
            
            
        }

        return "responseToMybuilder";
    }
}