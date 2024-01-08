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
import com.sfmes.se.service.Se5010Service;
/**
 * @Class Name : Se5010Controller.java
 * @Description : 견적서 등록
 * @Modification Information
 * @
 * @  수정일       수정자      수정내용
 * @ ----------   ---------   -------------------------------
 * @ 2020.07.06    손용찬      최초생성
 * @ 2020.12.28    곽환용      수정
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se5010Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Se5010Service")
    private Se5010Service callService;

    @RequestMapping(value = "/se5010Controller.do")
    public String se5010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
               
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02"));                   
        
        // 서비스 구분에 따라 분기 처리한다.      
        switch(strSVCID) {
        case "INSERT" :
            // 견적서 등록
            callService.insertSe5010(paramMap, paramList);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("ESMT_SQNO").toString());
            break;
            
        case "UPDATE" :
            // 견적서 수정
            callService.updateSe5010(paramMap, paramList);
            model.addAttribute("returnValue01", paramMap.get("ESMT_SQNO").toString());
            model.addAttribute("result", "OK");         
            break;
            
        case "DELETE" :
            // 견적서 삭제
            callService.deleteSe5010(paramMap);
            model.addAttribute("result", "OK");         
            break;

        case "SEARCH01" :
            // 견적서 기본조회
            List<?> resultList01 = callService.selectSe5010List01(paramMap);
            model.addAttribute("resultList", resultList01);           
            break;
            
        case "SEARCH02" :
            // 견적서 상세조회
            List<?> resultList02 = callService.selectSe5010List02(paramMap);
            model.addAttribute("resultList", resultList02);           
            break;
            
        case "SEARCH03" :
            // 견적서 출력(리포트) 조회
            List<?> resultList03 = callService.selectSe5010List03(paramMap);
            model.addAttribute("resultList", resultList03);           
            break;      
        }  
        
        return "responseToMybuilder";
    }
}