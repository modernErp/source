package com.sfmes.by.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.by.service.By2310_1Service;
import com.sfmes.cm.web.MyBuilderData;

/**
* @Class Name : By2310_1Controller.java
* @Description : 수주분 생산재료소요내역Controller
* @Modification Information
* @
* @  수정일                수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2022.10.21   김주경               최초생성
*
* @author (주)모든솔루션
* @since 2022.10.21
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class By2310_1Controller {

    @Resource(name = "By2310_1Service")
    private By2310_1Service by2310_1service;
    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/by2310_1Controller.do")
    public String by2310_1Controller(HttpServletRequest StrData, ModelMap model) throws Exception{
        
        // 서비스분기용 String
        String strSVCID = null;
        String inMSV01 = null;
        //파라미터 복호화
        myBuilderData.setParam(StrData);
        
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        
        switch(strSVCID) {
            case "SEARCH01" : // 수주 내역 조회
                List<?> resultList01 = by2310_1service.selectBy2310_1_01(paramMap);
                model.addAttribute("resultList", resultList01);
                break;
                
            case "SEARCH02" : // 소요 재료 내역 조회
                List<?> resultList02 = by2310_1service.selectBy2310_1_02(paramMap);
                model.addAttribute("resultList", resultList02);
                break;
                
            case "SEARCH03" : // 발주내역 조회
                List<?> resultList03 = by2310_1service.selectBy2310_1_03(paramMap);
                model.addAttribute("resultList", resultList03);
                break;
                
            case "SEARCH04" : //팝업창 조회
                List<?> resultList04 = by2310_1service.selectBy2310_1p01(paramMap);
                model.addAttribute("resultList", resultList04);
                break;
        }
        
        return "responseToMybuilder";
    }
}
