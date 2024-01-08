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
import com.sfmes.co.service.Co3030Service;

/**
 * @Class Name  : Co3030Controller.java
 * @Description : SMS수신대상자내역
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.11.09   이수빈     최초생성
 * @ 2020.12.28   이수빈     변경
 *
 * @author (주)모든솔루션
 * @since 2020.11.09
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Co3030Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Co3030Service")
    private Co3030Service callService;

    @RequestMapping(value = "/co3030Controller.do")
    public String co3030Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String strINMSV01  = null;
        String strINMSV02  = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        strINMSV01 = myBuilderData.getParam("INMSV01");
        strINMSV02 = myBuilderData.getParam("INMSV02");
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap<String, Object> paramMap = myBuilderData.getParamFromMSVHashMap(strINMSV01);
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(strINMSV02);
        List<?> resultList = null;
    
        switch(strSVCID) {
        
        case "SEARCH01" :
            resultList = callService.selectCo3030List(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH02" :
            resultList = callService.selectCo3030_01List(paramMap);
            model.addAttribute("resultList", resultList);
            break;
                   
        case "INSERT" :
            callService.insertCo3030List(paramMap, paramList);
            model.addAttribute("result", "OK");
            break;
            
        case "UPDATE" :
            callService.updateCo3030List(paramMap, paramList);
            model.addAttribute("result", "OK");
            break;
            
        case "DELETE" :
            callService.deleteCo3030List(paramMap, paramList);
            model.addAttribute("result", "OK");
            break;
        }
        return "responseToMybuilder";
    }
}
