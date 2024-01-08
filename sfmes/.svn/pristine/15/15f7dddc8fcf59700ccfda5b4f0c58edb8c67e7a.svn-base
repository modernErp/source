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
import com.sfmes.sm.service.Sm1025Service;

/**
 * @Class Name  : Sm1025ServiceImpl.java
 * @Description : Sm1025Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.08.19   김지혜      최초생성
 * @ 2020.12.28   이동훈      변경
 * 
 * @author (주)모든솔루션
 * @since 2020.08.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sm1025Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Sm1025Service")
    private Sm1025Service callService;
    
    @RequestMapping(value = "/sm1025Controller.do")
    public String sm1025Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
        
        case "SEARCH01" : 
            resultList = callService.search_sm1025_01(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
            
        case "SEARCH02" : 
            resultList = callService.search_sm1025_02(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
            
        case "SEARCH03" : 
            resultList = callService.search_sm1025_03(paramMap);
            model.addAttribute("resultList", resultList);           
            break;

        }
        return "responseToMybuilder";
    }
}
