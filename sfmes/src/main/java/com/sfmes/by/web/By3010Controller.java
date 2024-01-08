package com.sfmes.by.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.by.service.By3010Service;
import com.sfmes.cm.web.MyBuilderData;
/**
 * @Class Name : By3010Controller.java
 * @Description : 매입등록/수정 및 조회 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.11  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class By3010Controller {

    @Resource(name = "By3010Service")
    private By3010Service by3010service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/by3010Controller.do")
    public String by3010Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String result = "";     

        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); //매입등록 parameterList 
        
        switch(strSVCID){
            case "SEARCH01" : //매입기본내역 조회
                List<?> resultList01 = by3010service.selectBy3010_01(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;
                
            case "SEARCH02" : //매입상세내역 조회
                List<?> resultList02 = by3010service.selectBy3010_02(paramMap);
                model.addAttribute("resultList", resultList02);
                break;
                
            case "SEARCH03" : //매입내역찾기팝업 조회
                List<?> resultList03 = by3010service.selectBy3010_03(paramMap);
                model.addAttribute("resultList", resultList03);
                break; 
                
            case "SEARCH04" : //참조입고상세내역 조회
                List<?> resultList04 = by3010service.selectBy3010_04(paramMap);
                model.addAttribute("resultList", resultList04);
                break;     
                
            case "SEARCH05" : //매입정산내역 조회
                List<?> resultList05 = by3010service.selectBy3010_05(paramMap);
                model.addAttribute("resultList", resultList05);
                break;                
  
            case "INSERT" :   //매입내역기본,상세 저장 
                result = by3010service.insertBy3010(paramMap, paramList);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("BY_DT").toString());
                model.addAttribute("returnValue02", paramMap.get("BY_SQNO").toString());
                break;    
                
            case "UPDATE" :   //매입내역기본,상세 저장 (박지환 수정 - 2021.03.16)
            	paramMap.put("SLP_NML_YN", "N");
            	by3010service.deleteBy3010(paramMap, paramList);
            	paramMap.put("SLP_NML_YN", "Y");
            	paramMap.put("TR_BSN_DSC", "BY10");
            	result = by3010service.insertBy3010(paramMap, paramList);
            	model.addAttribute("result", "OK");
            	model.addAttribute("returnValue01", paramMap.get("BY_DT").toString());
            	model.addAttribute("returnValue02", paramMap.get("BY_SQNO").toString());
            	break;    
                
            case "DELETE" :   //매입내역삭제 
                by3010service.deleteBy3010(paramMap, paramList);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("BY_DT").toString());
                model.addAttribute("returnValue02", paramMap.get("BY_SQNO").toString());
                break;                
        }
        
        return "responseToMybuilder";
    }
}
