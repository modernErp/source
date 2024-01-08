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

import com.sfmes.cm.web.*;
import com.sfmes.sm.service.Sm2120Service;

/**
 * @Class Name  : Sm2120ServiceImpl.java
 * @Description : Sm2120Service Class
 * @Modification Information
 * @
 * @  수정일       수정자      수정내용
 * @ ----------   ---------   -------------------------------
 * @ 2020.09.17    정성환      최초생성
 * @ 2020.12.28    이동훈      변경
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sm2120Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Sm2120Service")
    private Sm2120Service callService;
    
    /**
     * 공통코드등록을 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/sm2120Controller.do")
    public String sm2120Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01  = null;
        String result   = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01  = myBuilderData.getParam("INMSV01");
                  
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<?> resultList = null;
        
        switch(strSVCID) {
            case "SEARCH01" :   // 품원장일별집계내역
                resultList = callService.select_Sm2120_01(paramMap);
                model.addAttribute("resultList", resultList);           
                break;
    }
        return "responseToMybuilder";
    }   
}
