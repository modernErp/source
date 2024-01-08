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
import com.sfmes.sm.service.Sm0000Service;

/**
 * @Class Name  : PDASm0000ServiceImpl.java
 * @Description : PDASm0000Service Class
 * @Modification Information
 * @
 * @ 수정일              수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2021.04.22   장경석            PDA BARCODE SCAN 내용으로 공통 INFO 구성
 *
 * @author (주)모든솔루션
 * @since 2021.04.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class PDASm0000Controller {
    
    @Resource(name = "myBuilderData_PDA")
    protected MyBuilderData_PDA myBuilderData_PDA;
    
    @Resource(name = "Sm0000Service")
    private Sm0000Service callService;
    
    /**
     * 공통코드등록을 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/PDAsm0000Controller.do")
    public String PDAsm0000Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01  = null;
        String inMSV02  = null;
        String inMSV03  = null;
        String result   = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData_PDA.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData_PDA.getParam("SVCID");
        inMSV01  = myBuilderData_PDA.getParam("INMSV01");
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData_PDA.getParamFromMSVHashMap(inMSV01);
 
        List<?> resultList = null;
        
        switch(strSVCID) {
        case "SEARCH01" :   // BARCODE 내역조회 
            resultList = callService.searchSm0000_01(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
        }

//        System.out.println("PDA resultList :: " + resultList);
        return "responseToAppChef";
    }   
}
