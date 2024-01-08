package com.sfmes.mi.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.mi.service.Mi1130Service;
/**
 * @Class Name  : Mi1130Controller.java
 * @Description : 거래처별매출현황 조회 Controller
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.11.12  곽환용  최초생성
 * @ 2020.12.28  이동훈  변경
 *
 * @author (주)모든솔루션
 * @since 2020.10.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Mi1130Controller {
    /** EgovSampleService */
    @Resource(name = "Mi1130Service")
    private Mi1130Service mi1130service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/mi1130Controller.do")
    public String mi1130Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");
        
        //입력된 MSV 타입의 파라미터를 MAP, List형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        
        switch(strSVCID)
        {
            case "SEARCH01" : //거래처별매출현황
                List<?> resultList = mi1130service.selectMi1130_01(paramMap);
                model.addAttribute("resultList", resultList);
                break;  
        }
        
        return "responseToMybuilder";
    }
}
