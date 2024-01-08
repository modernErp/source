package com.sfmes.by.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.by.service.By2120Service;
import com.sfmes.cm.web.MyBuilderData;

/**
 * @Class Name  : By2120Service.java
 * @Description : By2120Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.09.16   김지혜     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Controller
public class By2120Controller {
    
    @Resource(name = "By2120Service")
    private By2120Service by2120service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/by2120Controller.do")
    public String by2120Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String result = "";     

        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); 
        
        switch(strSVCID){
            case "SEARCH01" : //전표별 발주기본내역 조회
                List<?> resultList01 = by2120service.selectBy2120(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;
        }
        
        return "responseToMybuilder";
    }
}

