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
import com.sfmes.se.service.Se1010Service;
/**
 * @Class Name : Se1010Controller.java
 * @Description : 물품별판매단가등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.14  김선규   최초생성
 * @ 2020.09.01  곽환용   수정
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se1010Controller {    

    @Resource(name = "Se1010Service")
    private Se1010Service se1010service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/se1010Controller.do")
    public String se1010Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; 
        
        //파라미터 복호화
        myBuilderData.setParam(StrData);   
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); 
        
        switch(strSVCID){
            case "SEARCH01" : //물품별매출단가내역
                List<?> resultList01 = se1010service.selectSe1010_01(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;
                
            case "SEARCH02" : //물품별매출단가이력내역
                List<?> resultList02 = se1010service.selectSe1010_02(paramMap); 
                model.addAttribute("resultList", resultList02);
                break;

            case "SEARCH03" : //매출단가조회
                List<?> resultList03 = se1010service.selectSe1010_03(paramMap); 
                model.addAttribute("resultList", resultList03);
                break;                
                
            case "SEARCH04" : //최근적용일자의 매출단가만 조회
                List<?> resultList04 = se1010service.selectSe1010_04(paramMap); 
                model.addAttribute("resultList", resultList04);
                break;                 
                
            case "INSERT" : //물품별매출단가등록
                se1010service.insertSe1010(paramMap, paramList);
                model.addAttribute("result", "OK");
                break;
        }
        
        return "responseToMybuilder";
    }
}
