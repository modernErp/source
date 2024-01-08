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
import com.sfmes.se.service.Se1020Service;
/**
 * @Class Name : Se1020Controller.java
 * @Description : 가격군별물품등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.14  김선규   최초생성
 * @ 2020.09.02  곽환용   수정
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se1020Controller {    

    @Resource(name = "Se1020Service")
    private Se1020Service se1020service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/se1020Controller.do")
    public String se1020Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String result = "";
        String strMsg = "";
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02"));
        
        switch(strSVCID){
            case "SEARCH01" : //가격군내역
                List<?> resultList01 = se1020service.selectSe1020_01(paramMap);
                model.addAttribute("resultList", resultList01);
                break;
            
            case "SEARCH02" : //가격군별물품내역
                List<?> resultList02 = se1020service.selectSe1020_02(paramMap);
                model.addAttribute("resultList", resultList02);
                break;
                
            case "SEARCH03" : //도매매출단가조회
                List<?> resultList03 = se1020service.selectSe1020_03(paramMap);
                model.addAttribute("resultList", resultList03);
                break;   
                
            case "SEARCH04" : ////최근적용일자의 매출단가만 조회
                List<?> resultList04 = se1020service.selectSe1020_04(paramMap);
                model.addAttribute("resultList", resultList04);
                break;                 
                
            case "INSERT01" : //가격군등록
                se1020service.insertSe1020_01(paramMap, paramList);
                model.addAttribute("result", "OK");
                break;
                
            case "INSERT02" : //가격군별물품등록
                se1020service.insertSe1020_02(paramMap, paramList);
                model.addAttribute("result", "OK");
                break;
        }
        
        return "responseToMybuilder";
    }
}
