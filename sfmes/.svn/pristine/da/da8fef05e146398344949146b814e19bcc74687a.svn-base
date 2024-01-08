package com.sfmes.by.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.by.service.By2310_2Service;
import com.sfmes.cm.web.MyBuilderData;
/**
 * @Class Name : By2310_2Controller.java
 * @Description : 매입현황조회 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2022.10.25  성명건   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class By2310_2Controller {

    @Resource(name = "By2310_2Service")
    private By2310_2Service by2310_2service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;


    @RequestMapping(value = "/by2310_2Controller.do")
    public String by2310_2Controller(HttpServletRequest StrData, ModelMap model) throws Exception{
        String strSVCID = null;

        myBuilderData.setParam(StrData);

        strSVCID = myBuilderData.getParam("SVCID");

        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); 

        switch(strSVCID){
        case "SEARCH01" : //수주별조회내역
            List<?> resultList01 = by2310_2service.selectBy2310_2_01(paramMap); 
            model.addAttribute("resultList", resultList01);
            break;

        case "SEARCH02" : //소요재료내역조회
            List<?> resultList02 = by2310_2service.selectBy2310_2_02(paramMap);
            model.addAttribute("resultList", resultList02);
            break; 
        case "SEARCH03" : //Popup창 조회내용
            List<?> resultList03 = by2310_2service.selectBy2310_2_03(paramMap);
            model.addAttribute("resultList", resultList03);
            break;
            
        case "SEARCH04" : //발주수량
            List<?> resultList04 = by2310_2service.selectBy2310_2_04(paramMap);
            model.addAttribute("resultList", resultList04);
            break;
        }
        return "responseToMybuilder";
    }
}

