package com.sfmes.by.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.by.service.By3110Service;
import com.sfmes.cm.web.MyBuilderData;
/**
 * @Class Name : By3110Controller.java
 * @Description : 매입현황조회 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.10.27  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class By3110Controller {

    @Resource(name = "By3110Service")
    private By3110Service by3110service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/by3110Controller.do")
    public String by3110Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String   

        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); 
        
        switch(strSVCID){
            case "SEARCH01" : //거래처별매입현황
                List<?> resultList01 = by3110service.selectBy3110_01(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;
                
            case "SEARCH02" : //물품별매입현황
                List<?> resultList02 = by3110service.selectBy3110_02(paramMap);
                model.addAttribute("resultList", resultList02);
                break;
        }
        
        return "responseToMybuilder";
    }
}
