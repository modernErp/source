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
import com.sfmes.mi.service.Mi2140Service;

/**
 * @Class Name  : Mi2140Controller.java
 * @Description : Controller
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.12.28  이동훈  변경 (주석 생성)
 *
 * @author (주)모든솔루션
 * @since 2020.10.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */


@Controller
public class Mi2140Controller {
    
    @Resource(name = "Mi2140Service")
    private Mi2140Service callService;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/mi2140Controller.do")
    public String mi2140Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
       
        String strSVCID = null; 
        String inMSV01 = null;
        
        myBuilderData.setParam(StrData);  
        
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        
        //입력된 MSV 타입의 파라미터를 MAP, List형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<?> resultList = null;
        
        switch(strSVCID){
            case "SEARCH01" :
                resultList = callService.selectMi2140_01(paramMap);
                model.addAttribute("resultList", resultList);
                break;  
        }
        
        return "responseToMybuilder";
    }

}
