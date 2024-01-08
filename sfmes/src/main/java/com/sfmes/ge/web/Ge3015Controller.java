package com.sfmes.ge.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.ge.service.Ge1011Service;
import com.sfmes.ge.service.Ge3010Service;
import com.sfmes.ge.service.Ge3015Service;
/**
 * @Class Name  : Ge3015Controller.java
 * @Description : A/S 접수 조회 Controller
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.03.16  나명우  최초생성
 * 
 *
 * @author (주)모든솔루션
 * @since 2022.03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Ge3015Controller {
    /** EgovSampleService */
    @Resource(name = "Ge3015Service")
    private Ge3015Service ge3015service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/ge3015Controller.do")
    public String ge3015Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String result = "";
        
        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        
        switch(strSVCID) {
            
        case "SEARCH01" :
            List<?> resultList01 = ge3015service.selectGe3015_01(paramMap);
            model.addAttribute("resultList", resultList01);
            break;
        }
        
        
        return "responseToMybuilder";
    }
}
