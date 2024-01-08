package com.sfmes.by.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.by.service.By2130_3Service;
import com.sfmes.cm.web.MyBuilderData;

/**
* @Class Name : By2130_3Controller.java
* @Description : 수주분 생산재료소요내역Controller
* @Modification Information
* @
* @  수정일                수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2022.10.21   강동현               최초생성
*
* @author (주)모든솔루션
* @since 2022.10.21
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class By2130_3Controller {
    @Resource(name = "By2130_3Service")
    private By2130_3Service by2130_3service;
    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/by2130_3Controller.do")
    public String by2130_3Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null;
        
      //파라미터 복호화
        myBuilderData.setParam(StrData);
        
        strSVCID = myBuilderData.getParam("SVCID");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        
        switch(strSVCID) {
            case "SEARCH01" :   // 수주 내역 조회
                List<?> resultLst01 = by2130_3service.selectBy2130_3_01(paramMap);
                model.addAttribute("resultList", resultLst01);
                break;
            case "SEARCH02" :   // 소요 재료 내역 조회
                List<?> resultLst02 = by2130_3service.selectBy2130_3_02(paramMap);
                model.addAttribute("resultList", resultLst02);
                break;
            case "SEARCH03" :   // 발주내역 조회
                List<?> resultLst03 = by2130_3service.selectBy2130_3_03(paramMap);
                model.addAttribute("resultList", resultLst03);
                break;
            case "SEARCH04" :   //팝업창 조회
                List<?> resultLst04 = by2130_3service.selectBy2130_3_04(paramMap);
                model.addAttribute("resultList", resultLst04);
                break;
        }
        return "responseToMybuilder";
    }
}