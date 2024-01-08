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
import com.sfmes.se.service.Se6010Service;
/**
 * @Class Name : Se6010Controller.java
 * @Description : 출고지시등록 Controller
 * @Modification Information
 * @
 * @  수정일      수정자   수정내용
 * @ ----------  -------  -------------------------------
 * @ 2020.09.11   김지혜   최초생성 
 * @ 2020.12.28   곽환용   수정    
 *
 * @author (주)모든솔루션
 * @since 2020.09.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se6010Controller {    

    @Resource(name = "Se6010Service")
    private Se6010Service se6010service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/se6010Controller.do")
    public String se6010Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); //출고지시내역등록 parameterList 
        
        switch(strSVCID){
            case "SEARCH01" : //출고지시대상 조회
                List<?> resultList01 = se6010service.selectSe6010_01(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;    
                
            case "SEARCH02" : //출고지시대상 조회
                List<?> resultList02 = se6010service.selectSe6010_02(paramMap); 
                model.addAttribute("resultList", resultList02);
                break;  
                
            case "SEARCH03" : //출고지시내역찾기(팝업)
                List<?> resultList03 = se6010service.selectSe6010_03(paramMap); 
                model.addAttribute("resultList", resultList03);
                break;
                
            case "SEARCH04" : //출고지시상세내역(팝업)
                List<?> resultList04 = se6010service.selectSe6010_04(paramMap); 
                model.addAttribute("resultList", resultList04);
                break;                

            case "INSERT" :   //출고지시등록
                se6010service.insertSe6010(paramMap, paramList);
                model.addAttribute("result", "OK");                
                break;                
        }
        
        return "responseToMybuilder";
    }
}
