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
import com.sfmes.se.service.Se3020Service;
/**
 * @Class Name : Se3020Controller.java
 * @Description : 매출반품등록/수정 및 조회 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.10.12  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se3020Controller {
    
    @Resource(name = "Se3020Service")
    private Se3020Service se3020service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/se3020Controller.do")
    public String se3020Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String result = ""; 
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); //매출등록 parameterList 
        
        switch(strSVCID){
            case "SEARCH01" : //매출반입기본내역 조회
                List<?> resultList01 = se3020service.selectSe3020_01(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;
            
            case "SEARCH02" : //매출반입상세내역 조회
                List<?> resultList02 = se3020service.selectSe3020_02(paramMap);
                model.addAttribute("resultList", resultList02);
                break;

            case "SEARCH03" : //매출정산내역 조회
                List<?> resultList03 = se3020service.selectSe3020_03(paramMap);
                model.addAttribute("resultList", resultList03);
                break;                                
                
            case "INSERT" :   //매출반입내역기본,상세 저장 
                result = se3020service.insertSe3020(paramMap, paramList);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("SL_DT").toString());
                model.addAttribute("returnValue02", paramMap.get("SL_SQNO").toString());
                break;    
            
            case "DELETE" :   //매출반입내역삭제 
                se3020service.deleteSe3020(paramMap, paramList);
                model.addAttribute("result", "OK");
                break;                
        }
        
        return "responseToMybuilder";
    }
}
