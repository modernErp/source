package com.sfmes.se.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.se.service.Se2015Service;
/**
 * @Class Name : Se2015Controller.java
 * @Description : 수주내역조회 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.30  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se2015Controller {

    @Resource(name = "Se2015Service")
    private Se2015Service se2015service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/se2015Controller.do")
    public String se2015Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String

        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01")); 
        
        switch(strSVCID){
            case "SEARCH01" : //전표별 수주기본내역 조회
                List<?> resultList01 = se2015service.selectSe2015_01(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;
                
            case "SEARCH02" : //전표별 수주상세내역 조회
                List<?> resultList02 = se2015service.selectSe2015_02(paramMap);
                model.addAttribute("resultList", resultList02);
                break;
                
            case "SEARCH03" : //물품별 수주내역 조회
                List<?> resultList03 = se2015service.selectSe2015_03(paramMap);
                model.addAttribute("resultList", resultList03);
                break;                
        }
        
        return "responseToMybuilder";
    }
}
