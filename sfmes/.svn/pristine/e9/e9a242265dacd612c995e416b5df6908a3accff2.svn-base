package com.sfmes.by.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.by.service.By3020Service;
import com.sfmes.cm.web.MyBuilderData;
/**
 * @Class Name : By3020Controller.java
 * @Description : 매입반품등록/수정 및 조회 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.11  곽환용   최초생성
 * @ 2020.09.23  김지혜   매입반품등록화면으로 수정
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class By3020Controller {

    @Resource(name = "By3020Service")
    private By3020Service by3020service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/by3020Controller.do")
    public String by3020Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String result = "";     

        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); 
        
        switch(strSVCID){
            case "SEARCH01" : //매입반품기본내역 조회
                List<?> resultList01 = by3020service.selectBy3020_01(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;
                
            case "SEARCH02" : //매입반품상세내역 조회
                List<?> resultList02 = by3020service.selectBy3020_02(paramMap);
                model.addAttribute("resultList", resultList02);
                break;
                
            case "SEARCH03" : //매입정산내역 조회
                List<?> resultList03 = by3020service.selectBy3020_03(paramMap);
                model.addAttribute("resultList", resultList03);
                break;                     
                
            case "SEARCH04" : //입고반품내역(SM16, PDA스캔내역) 조회
                List<?> resultList = by3020service.selectBy3020_04(paramMap);
                model.addAttribute("resultList", resultList);
                break;                     
                
            case "INSERT" :   //매입반품내역기본,상세 저장 
                result = by3020service.insertBy3020(paramMap, paramList);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("BY_DT").toString());
                model.addAttribute("returnValue02", paramMap.get("BY_SQNO").toString());
                break;    
                
            case "DELETE" :   //매입반품내역삭제 
                by3020service.deleteBy3020(paramMap, paramList);
                model.addAttribute("result", "OK");
                break;                
        }
        
        return "responseToMybuilder";
    }
}
