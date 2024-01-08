package com.sfmes.se.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.se.service.Se4020Service;
/**
 * @Class Name  : Se4020Controller.java
 * @Description: 배송차량등록 및 수정 Controller
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.23   김지혜      최초생성
 * @ 2020.12.28   곽환용      수정
 *
 * @author (주)모든솔루션
 * @since 2020.07.23
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se4020Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Se4020Service")
    private Se4020Service callService;

    @RequestMapping(value = "/se4020Controller.do")
    public String se4020Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
    
        switch(strSVCID) {
            case "INSERT" : //배송차량등록[SE4020]
                callService.insert_Se4020(paramMap);
                model.addAttribute("result", "OK");         
                break;
            
            case "UPDATE" : //배송차량수정[SE4020]
                callService.update_Se4020(paramMap);
                model.addAttribute("result", "OK");         
                break;
            
            case "SEARCH01" : //배송차량조회[SE4020]
                List<?> resultList01 = callService.selct_Se4020(paramMap);
                model.addAttribute("resultList", resultList01);           
                break;
                
            case "SEARCH02" : //배송차량내역[SE4025]
                List<?> resultList02 = callService.select_Se4025(paramMap);
                model.addAttribute("resultList", resultList02);           
                break;
        }     
        
        return "responseToMybuilder";
    }
}
