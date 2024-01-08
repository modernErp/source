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
import com.sfmes.sm.service.Sm6010Service;

/**
 * @Class Name  : Sm6010ServiceImpl.java
 * @Description : Sm6010Service Class
 * @Modification Information
 * @
 * @  수정일             수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.12.28   이동훈      변경  (이 주석이 없어서 추가)
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sm6010Controller {
    
    @Resource(name="myBuilderData")
    private MyBuilderData myBuilderData;
    
    @Resource(name="Sm6010Service")
    private Sm6010Service callService;

    @RequestMapping(value = "/sm6010Controller.do")
    public String sm6010Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        
        String strSVCID   = null;
        String strINMSV01 = null;
        String strINMSV02 = null;
        
        // 파라미터 복호화를 수행한다. 
        myBuilderData.setParam(strData);
        
        strSVCID = myBuilderData.getParam("SVCID");
        strINMSV01 = myBuilderData.getParam("INMSV01");
        strINMSV02 = myBuilderData.getParam("INMSV02");
        
        LinkedHashMap<String, Object> paramMap = myBuilderData.getParamFromMSVHashMap(strINMSV01);
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(strINMSV02);
        List<?> resultList = null;
        
        switch( strSVCID ) {
        
        case "SEARCH01" :
            resultList = callService.searchSm6010_01(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SAVE01" :
            callService.insertSm6010_01(paramMap, paramList);
            model.addAttribute("result", "OK");
            break;
        }
        
        return "responseToMybuilder";
    }

}
