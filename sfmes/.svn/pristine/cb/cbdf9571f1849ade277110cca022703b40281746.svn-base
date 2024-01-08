package com.sfmes.et.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.et.service.Et9950Service;
import com.sfmes.cm.web.MyBuilderData;
/**
 * @Class Name : Et9970Controller.java
 * @Description : 매입등록/수정 및 조회 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2022.10.11  박보현   최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.10.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Et9950Controller {

    @Resource(name = "Et9950Service")
    private Et9950Service Et9950service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/Et9950Controller.do")
    public String Et9950Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String   

        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); 
        
        switch(strSVCID){
            case "SEARCH01" : //전표별매입기본내역
                List<?> resultList01 = Et9950service.selectEt9950_01(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;       
        }
        
        return "responseToMybuilder";
    }
}
