package com.sfmes.se.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.se.service.Se1030Service;
/**
 * @Class Name : Se1030Controller.java
 * @Description : 가격군별거래처등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.09.03  곽환용   최초등록
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se1030Controller {    

    @Resource(name = "Se1030Service")
    private Se1030Service se1030service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/se1030Controller.do")
    public String se1030Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null;
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02"));
        
        switch(strSVCID){
            case "SEARCH01" : //가격군내역
                List<?> resultList01 = se1030service.selectSe1030_01(paramMap);
                model.addAttribute("resultList", resultList01);
                break;
            
            case "SEARCH02" : //가격군별거래처내역
                List<?> resultList02 = se1030service.selectSe1030_02(paramMap);
                model.addAttribute("resultList", resultList02);
                break;

            case "INSERT" : //가격군별거래처등록
                se1030service.insertSe1030(paramMap, paramList);
                model.addAttribute("result", "OK");
                break;
        }
        
        return "responseToMybuilder";
    }
}
