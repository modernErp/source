package com.sfmes.se.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.se.service.Se6015Service;
/**
 * @Class Name : Se6015Controller.java
 * @Description : 출고지시내역 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.10  박지환   최초생성
 * @ 2020.12.28  곽환용   수정   
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se6015Controller {

    @Resource(name = "Se6015Service")
    private Se6015Service se6015service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;

    @RequestMapping(value = "/se6015Controller.do")
    public String Se6015Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String

        myBuilderData.setParam(StrData);  //파라미터 복호화

        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));

        switch(strSVCID) {
            case "SEARCH01" : //출고지시기본내역
                List<?> resultList01 = se6015service.selectSe6015_01(paramMap);
                model.addAttribute("resultList", resultList01);                
                break;

            case "SEARCH02" : //출고지시상세내역
                List<?> resultList02 = se6015service.selectSe6015_02(paramMap);
                model.addAttribute("resultList", resultList02);
                break;
        }
        
        return "responseToMybuilder";
    }
}

