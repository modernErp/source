package com.sfmes.se.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.se.service.Se1015Service;
/**
 * @Class Name : Se1015Controller.java
 * @Description : 물품별판매단가내역 Controller
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
public class Se1015Controller {    

    @Resource(name = "Se1015Service")
    private Se1015Service se1015service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/se1015Controller.do")
    public String se1015Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        
        switch(strSVCID) {
            case "SEARCH01" : //물품매출단가내역
                List<?> resultList01 = se1015service.selectSe1015_01(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;
        }
        
        return "responseToMybuilder";
    }
}
