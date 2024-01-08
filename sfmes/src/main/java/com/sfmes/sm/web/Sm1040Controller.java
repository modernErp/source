package com.sfmes.sm.web;

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
import com.sfmes.sm.service.Sm1040Service;

/**
 * @Class Name : Sm1040Controller.java
 * @Description : 출고지시등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.06  곽환용   최초생성
 * @ 2020.12.28  이동훈   변경
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sm1040Controller {    
    /** EgovSampleService */
    @Resource(name = "Sm1040Service")
    private Sm1040Service sm1040service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/sm1040Controller.do")
    public String sm1040Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); //출고지시내역등록 parameterList 
        
        switch(strSVCID){
            case "SEARCH01" : //출고지시대상 조회
                System.out.println("paramMap for SEARCH01 ::::: " + paramMap);
                List<?> resultList01 = sm1040service.selectSm1040_01(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;    
                
            case "SEARCH02" : //출고지시대상 조회
                System.out.println("paramMap for SEARCH01 ::::: " + paramMap);
                List<?> resultList02 = sm1040service.selectSm1040_02(paramMap); 
                model.addAttribute("resultList", resultList02);
                break;  
                
            case "SEARCH03" : //출고지시내역찾기(팝업)
                System.out.println("paramMap for SEARCH01 ::::: " + paramMap);
                List<?> resultList03 = sm1040service.selectSm1040_03(paramMap); 
                model.addAttribute("resultList", resultList03);
                break;
                
            case "SEARCH04" : //출고지시상세내역(팝업)
                System.out.println("paramMap for SEARCH01 ::::: " + paramMap);
                List<?> resultList04 = sm1040service.selectSm1040_04(paramMap); 
                model.addAttribute("resultList", resultList04);
                break;                

            case "INSERT" :   //출고지시등록
                System.out.println("paramMap for SAVE ::::: " +paramMap);
                System.out.println("paramList for SAVE ::::: "+paramList);
                sm1040service.insertSm1040(paramMap, paramList);
                model.addAttribute("result", "OK");                
                break;                
        }
        
        return "responseToMybuilder";
    }
}
