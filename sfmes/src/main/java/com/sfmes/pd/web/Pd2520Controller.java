package com.sfmes.pd.web;

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
import com.sfmes.pd.service.Pd2520Service;

/**
* @Class Name : Pd2520Controller.java
* @Description : Pd2520Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.27   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.27
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Pd2520Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Pd2520Service")
    private Pd2520Service callService;
    
    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/pd2520Controller.do")
    public String pd2520Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01 = null;
        String inMSV02 = null;
        String inMSV03 = null;
        String inMSV04 = null;

        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        inMSV02 = myBuilderData.getParam("INMSV02");
        inMSV03 = myBuilderData.getParam("INMSV03");
        inMSV04 = myBuilderData.getParam("INMSV04");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<Map<String, Object>> paramList01 = myBuilderData.getParamFromMSVList(inMSV02);
        List<Map<String, Object>> paramList02 = myBuilderData.getParamFromMSVList(inMSV03);
        List<Map<String, Object>> paramList03 = myBuilderData.getParamFromMSVList(inMSV04);
        List<?> resultList = null;
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
        case "INSERT" :
            System.out.println("paramList01"+paramList01);
            System.out.println("paramList02"+paramList02);
            System.out.println("paramList03"+paramList03);
            callService.insertPd2520(paramMap, paramList01, paramList02, paramList03);
            model.addAttribute("result", "OK");
            break;
            
        case "SEARCH01" :
            //공정기록서 투입상세(저장전)
            resultList = callService.selectPd2520List_01(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH02" :
            //공정기록서 제품상세(저장전)
            resultList = callService.selectPd2520List_02(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        }
        
        return "responseToMybuilder";
       
    }


}
