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

import com.sfmes.ca.service.Ca6010Service;
import com.sfmes.cm.web.MyBuilderData;

@Controller
public class Ca6010Controller {

    @Resource(name = "myBuilderData")
    public MyBuilderData myBuilderData;
    
    @Resource(name = "Ca6010Service")
    public Ca6010Service callService;
    
    @RequestMapping(value = "/Ca6010Controller.do")
    public String Ca6010Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        
        String strSVCID = null;
        String strINMSV01 = null;
        
        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        strINMSV01 = myBuilderData.getParam("INMSV01");
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap<String, Object> paramMap = myBuilderData.getParamFromMSVHashMap(strINMSV01);
        List<?> resultList = null;
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
        
        case "SEARCH01" :
            // 선수금입금/사용등록 기준정보 조회
            resultList = callService.selectCa6010List(paramMap);
            model.addAttribute("resultList", resultList);
            break;
        
        case "INSERT" :
            // 선수금 입금/사용 등록
            callService.insertCa6010One(paramMap);
            model.addAttribute("result", "OK");
            break;
            
        }
        
        return "responseToMybuilder";
    }
}
