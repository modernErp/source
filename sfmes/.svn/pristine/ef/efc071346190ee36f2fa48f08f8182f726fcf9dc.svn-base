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
import com.sfmes.se.service.Se2020Service;
/**
 * @Class Name : Se2020Controller.java
 * @Description : 온라인몰수주일괄등록/수정 및 조회 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.03  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se2020Controller {    

    @Resource(name = "Se2020Service")
    private Se2020Service se2020service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/se2020Controller.do")
    public String se2020Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String result = "";
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); //온라인몰수주일괄등록 parameterList 
        
        switch(strSVCID){
            case "SEARCH01" : //온라인몰수주일괄등록내역
                List<?> resultList01 = se2020service.selectSe2020_01(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;
                
            case "SEARCH02" : //온라인몰수주일괄등록내역찾기팝업
                List<?> resultList02 = se2020service.selectSe2020_02(paramMap);
                model.addAttribute("resultList", resultList02);
                break;

            case "SEARCH03" : //거래처물품연결등록 조회
                List<?> resultList03 = se2020service.selectSe2020_03(paramMap, paramList);
                model.addAttribute("resultList", resultList03);
                break;
      
            case "INSERT" :   //온라인몰수주일괄등록 
                se2020service.insertSe2020(paramMap, paramList);
                model.addAttribute("result", "OK");                
                break;      
        }
        
        return "responseToMybuilder";
    }
}
