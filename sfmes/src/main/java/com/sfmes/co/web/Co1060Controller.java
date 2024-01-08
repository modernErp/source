package com.sfmes.co.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.co.service.Co1060Service;
/**
 * @Class Name : Co1060Controller.java
 * @Description : 물품분류 조회 및 등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.06.29  곽환용   최초생성
 * @ 2020.12.28  이수빈   변경
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Co1060Controller {
    /** EgovSampleService */
    @Resource(name = "Co1060Service")
    private Co1060Service co1060service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/co1060Controller.do")
    public String co1060Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList_LCLC = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); //물품대분류 parameterList 
        List<Map<String, Object>> paramList_MCLC = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV03")); //물품중분류 parameterList 
        List<Map<String, Object>> paramList_SCLC = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV04")); //물품소분류 parameterList
        
        switch(strSVCID){
            case "SEARCH01" : //물품대분류 조회
                List<?> resultList_LCLC = co1060service.selectGdsLCLC(paramMap); 
                model.addAttribute("resultList", resultList_LCLC);
                break;
                
            case "SEARCH02" : //물품중분류 조회
                List<?> resultList_MCLC = co1060service.selectGdsMCLC(paramMap);
                model.addAttribute("resultList", resultList_MCLC);
                break;
                
            case "SEARCH03" : //물품소분류 조회
                List<?> resultList_SCLC = co1060service.selectGdsSCLC(paramMap);
                model.addAttribute("resultList", resultList_SCLC);
                break;   
                                 
            case "SAVE01" :   //물품분류 등록/수정 
                co1060service.insertCo1060Gds(paramList_LCLC, paramList_MCLC, paramList_SCLC);
                model.addAttribute("result", "OK");
                break;
        }
        
        return "responseToMybuilder";
    }
}
