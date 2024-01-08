package com.sfmes.co.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.*;
import com.sfmes.co.service.Co1070Service;

/**
 * @Class Name  : Co1070Controller.java
 * @Description : Co1070Service Class
 * @Modification Information
 * @
 * @  수정일      수정자      수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.30   김지혜      최초생성
 * @ 2020.08.04   곽환용      변경
 * @ 2020.12.28   이수빈      변경
 *
 * @author (주)모든솔루션
 * @since 2020.06.30
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Co1070Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Co1070Service")
    private Co1070Service co1070Service;    

    //거래처물품연결등록
    @RequestMapping(value = "/co1070Controller.do")
    public String co1070Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null; //서비스분기용 String
        
        myBuilderData.setParam(strData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); //거래처물품연결등록 parameterList
    
        switch(strSVCID) {
        
        case "SEARCH01" :   //거래처물품조회
            System.out.println("paramMap for SEARCH01 ::::: " + paramMap);
            List<?> resultList01 = co1070Service.selectCo1070(paramMap); 
            System.out.println("result for SARCH01 ::::: " + resultList01);
            model.addAttribute("resultList", resultList01);
            break;
            
        case "SAVE01" :    //거래처물품연결등록
            System.out.println("paramMap for INSERT ::::: "+paramMap);
            System.out.println("paramList for INSERT ::::: "+paramList);
            co1070Service.saveCo1070(paramMap, paramList);
            model.addAttribute("result", "OK");
            break;            
        }
        
        return "responseToMybuilder";
    }
}
