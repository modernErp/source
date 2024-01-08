package com.sfmes.sy.web;

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
import com.sfmes.sy.service.Sy4020Service;

/**
 * @Class Name  : Sy4020Controller.java
 * @Description : Sy4020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.10   김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.10
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sy4020Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Sy4020Service")
    private Sy4020Service callService;
    
    /**
     * 시스템오류내역을 조회한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/sy4020Controller.do")
    public String sy4020Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
            // 시스템오류이력조회
            resultList = callService.selectSy4020ErrList(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH02" :
            // 시스템오류이력상세조회
            resultList = callService.selectSy4020ErrDetail(paramMap);
            model.addAttribute("resultList", resultList);
            break;    
        }
        
        String packageName = (String) paramMap.get("METHOD_NM");
        
        if(packageName.indexOf("PDA") < 0) {
            return "responseToMybuilder";
        } else {
            return "responseToAppChef";
        }
    }
}
