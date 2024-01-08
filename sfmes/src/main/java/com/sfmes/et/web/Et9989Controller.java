package com.sfmes.et.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.et.service.Et9989Service;
import com.sfmes.cm.web.MyBuilderData;
/**
 * @Class Name : Et9989Controller.java
 * @Description : 매입등록/수정 및 조회 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2022.10.17  성명건   최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.10.17
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Et9989Controller {

    @Resource(name = "Et9989Service")
    private Et9989Service Et9989service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/Et9989Controller.do")
    public String Et9989Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String   

        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); 
        
        switch(strSVCID){
            case "SEARCH01" : //전표별매입기본내역
                List<?> resultList01 = Et9989service.selectEt9989_01(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;
                
            case "SEARCH02" : //전표별매입상세내역
                List<?> resultList02 = Et9989service.selectEt9989_02(paramMap);
                model.addAttribute("resultList", resultList02);
                break;
                
            case "SEARCH03" : //전표-물품별매입내역
                List<?> resultList03 = Et9989service.selectEt9989_03(paramMap);
                model.addAttribute("resultList", resultList03);
                break; 
        }
        
        return "responseToMybuilder";
    }
}
