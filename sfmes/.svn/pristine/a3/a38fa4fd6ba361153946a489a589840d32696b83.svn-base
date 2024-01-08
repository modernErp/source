package com.sfmes.by.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.by.service.By2010Service;
import com.sfmes.cm.web.MyBuilderData;
/**
 * @Class Name : By2010Controller.java
 * @Description : 발주등록/수정 및 조회 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.06  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class By2010Controller {
    /** EgovSampleService */
    @Resource(name = "By2010Service")
    private By2010Service by2010service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/by2010Controller.do")
    public String by2010Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String result = "";     

        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); //발주등록 parameterList 
        
        switch(strSVCID){
            case "SEARCH01" : //발주기본내역 조회
                List<?> resultList01 = by2010service.selectBy2010_01(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;
                
            case "SEARCH02" : //발주상세내역 조회
                List<?> resultList02 = by2010service.selectBy2010_02(paramMap);
                model.addAttribute("resultList", resultList02);
                break;
                
            case "SEARCH03" : //발주내역찾기팝업 조회
                List<?> resultList03 = by2010service.selectBy2010_03(paramMap);
                model.addAttribute("resultList", resultList03);
                break;         
                
            case "SEARCH04" : //발주서내역 조회
                List<?> resultList04 = by2010service.selectBy2010_04(paramMap);
                model.addAttribute("resultList", resultList04);
                break;
      
            case "INSERT" :   //발주내역기본,상세 저장 
                result = by2010service.insertBy2010(paramMap, paramList);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("ODR_DT").toString());
                model.addAttribute("returnValue02", paramMap.get("ODR_SQNO").toString());
                break;
                
            case "UPDATE" :   //발주내역기본,상세 수정 
                result = by2010service.updateBy2010(paramMap, paramList);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("ODR_DT").toString());
                model.addAttribute("returnValue02", paramMap.get("ODR_SQNO").toString());
                break;      
                
            case "UPDATE02" : //발주취소
                by2010service.updateBy2010_cancel(paramMap);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("ODR_DT").toString());
                model.addAttribute("returnValue02", paramMap.get("ODR_SQNO").toString());
        }
        
        return "responseToMybuilder";
    }
}
