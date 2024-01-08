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
import com.sfmes.se.service.Se2030Service;

/**
 * @Class Name : Se2030Controller.java
 * @Description : 수주마감등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.20  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se2030Controller {    

    @Resource(name = "Se2030Service")
    private Se2030Service se2030service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/se2030Controller.do")
    public String se2030Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); //수주마감등록 parameterList 
        
        switch(strSVCID){
            case "SEARCH01" : //수주마감대상 조회
                List<?> resultList01 = se2030service.selectSe2030_01(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;

            case "SEARCH02" : //수주마감내역
                List<?> resultList02 = se2030service.selectSe2030_02(paramMap); 
                model.addAttribute("resultList", resultList02);
                break;
                
            case "SEARCH03" : //수주마감기본내역 조회
                List<?> resultList03 = se2030service.selectSe2030_03(paramMap);
                model.addAttribute("resultList", resultList03);
                break;
                
            case "SEARCH04" : //수주마감상세내역 조회
                List<?> resultList04 = se2030service.selectSe2030_04(paramMap);
                model.addAttribute("resultList", resultList04);
                break;                

            case "UPDATE" :   //수주마감등록 
                se2030service.updateSe2030(paramMap, paramList);
                model.addAttribute("result", "OK");                
                break;                

            case "UPDATE_02" :   //수주마감 취소 
                se2030service.updateSe2030_02(paramMap, paramList);
                model.addAttribute("result", "OK");                
        }
        
        return "responseToMybuilder";
    }
}
