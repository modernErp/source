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
import com.sfmes.co.service.Co2020Service;

/**
 * @Class Name  : Co2020Controller.java
 * @Description : Co2020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.12   김지혜      최초생성
 * @ 2020.12.28   이수빈     변경
 *
 * @author (주)모든솔루션
 * @since 2020.06.12
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Co2020Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Co2020Service")
    private Co2020Service callService;

    /**
     * 공휴일정보를 조회한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/co2020Controller.do")
    public String co2020Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01  = null;
        String inMSV02  = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01  = myBuilderData.getParam("INMSV01");
        inMSV02  = myBuilderData.getParam("INMSV02");
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(inMSV02);
        List<?> resultList = null;
    
        switch(strSVCID) {
        
        case "UPDATE" :
            //공휴일 수정 서비스를 호출한다.
            callService.updateCo2020(paramList);
            model.addAttribute("result", "OK");
            break;
            
        case "SEARCH01" :
            //공휴일정보를 조회한다.
            resultList = callService.selectCo2020List(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH02" :
            //기준연도를 조회한다.
            resultList = callService.selectCo2020_Basyy(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH03" :
            //영업주차기준연도를 조회한다.
            resultList = callService.selectCo2020_weekBasyy(paramMap);
            model.addAttribute("resultList", resultList);
            break;
        
        }
        return "responseToMybuilder";
    }
}