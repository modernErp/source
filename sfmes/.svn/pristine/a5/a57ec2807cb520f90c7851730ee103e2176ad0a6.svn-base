package com.sfmes.et.web;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.et.service.Et9962Service;

/**
* @Class Name : Et9962Controller.java
* @Description : 등록 및 수정 테스트 Controller
* @Modification Information
* @
* @  수정일                수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2022.10.05   김주경               최초생성
*
* @author (주)모든솔루션
* @since 2022.10.05
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Et9962Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Et9962Service")
    private Et9962Service callService;
    
    /**
     * 테스트데이터 등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     * */
    
    @RequestMapping(value = "/Et9962Controller.do")
    public String Et9962Controller(HttpServletRequest strData, ModelMap model)throws Exception
    {
        String strSVCID = null;
        String inMSV01 = null;
        
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<?> resultList = null;
        
        //서비스 구문에 따라 분기 처리한다.
        switch(strSVCID) {
        
        case "INSERT" :
            // 데이터 등록
            callService.insertEt9962(paramMap);
            model.addAttribute("result", "OK");
            break;
            
        case "UPDATE" :
            // 데이터 수정
            callService.updateEt9962(paramMap);
            model.addAttribute("result", "OK");
            break;
            
        case "SEARCH01" :
            // 데이터 조회
            resultList = callService.selectEt9962(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        }
        return "responseToMybuilder";
    }
    
 
}
