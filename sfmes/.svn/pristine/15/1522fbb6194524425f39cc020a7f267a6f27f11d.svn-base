package com.sfmes.se.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.se.service.Se2120Service;

/**
 * @Class Name  : Se2120Service.java
 * @Description : 수주대비(미)출고현황 조회 Controller
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.09.16   김지혜     최초생성
 * @ 2020.12.28   곽환용     수정
 * @ 2022.04.18   나명우     수정
 *
 * @author (주)모든솔루션
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se2120Controller {

    @Resource(name = "Se2120Service")
    private Se2120Service se2120Service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/se2120Controller.do")
    public String se2120Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String result = "";

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
                  
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        
        switch(strSVCID){
            case "SEARCH01" : //수주대비(미)출고현황 조회
                List<?> resultList = se2120Service.selectSe2120(paramMap); 
                model.addAttribute("resultList", resultList);
                break; 
            case "SEARCH02" : //수주대비(미)출고 대상 조회 2022.04.18 나명우추가
                List<?> resultList02 = se2120Service.selectSe2120_02(paramMap); 
                model.addAttribute("resultList", resultList02);
                break; 
            case "UPDATE" : //미출고내용 수정 2022.04.18 나명우추가
                result = se2120Service.updateSe2120(paramMap);
                model.addAttribute("result", result);         
                break;  
            }
                
          
        return "responseToMybuilder";
    }
}
