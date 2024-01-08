package com.sfmes.sm.web;

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
import com.sfmes.sm.service.Sm1045Service;

/**
 * @Class Name : Sm1045Controller.java
 * @Description : 출고지시등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.10  박지환   최초생성
 * @ 2020.12.28  이동훈   변경
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sm1045Controller {
    /** EgovSampleService */
    @Resource(name = "Sm1045Service")
    private Sm1045Service sm1045service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;

    @RequestMapping(value = "/sm1045Controller.do")
    public String Sm1045Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String

        myBuilderData.setParam(StrData);  //파라미터 복호화

        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); //출고지시내역등록 parameterList

        switch(strSVCID) {
            case "SEARCH01" : //출고지시기본내역
                System.out.println("paramMap for SEARCH01 ::::: " + paramMap);
                List<?> resultList01 = sm1045service.selectSm1045_01(paramMap);
                System.out.println("result for SARCH01 ::::: " + resultList01);
                model.addAttribute("resultList", resultList01);                
                break;

            case "SEARCH02" : //출고지시상세내역
                System.out.println("paramMap for SEARCH02 ::::: " + paramMap);
                List<?> resultList02 = sm1045service.selectSm1045_02(paramMap);
                System.out.println("result for SARCH02 ::::: " + resultList02);
                model.addAttribute("resultList", resultList02);
                break;
        }
        
        return "responseToMybuilder";
    }
}
