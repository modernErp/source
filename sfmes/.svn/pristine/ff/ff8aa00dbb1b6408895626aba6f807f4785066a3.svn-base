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
import com.sfmes.sy.service.Sy2010Service;

/**
* @Class Name : Sy2020Controller.java
* @Description : Sy2020Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.06.09   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.06.09
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/
@Controller
public class Sy2010Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Sy2010Service")
    private Sy2010Service callService;

    /**
     * 메뉴등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/sy2010Controller.do")
    public String sy2020Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01 = null;
        String inMSV02 = null;
        
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        System.out.println("sy2020Controller inMSV01 : " + inMSV01 );
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<?> resultList = null;
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) { 
        case "INSERT" :
            //메뉴등록
            callService.insertSy2010(paramMap);
            model.addAttribute("result", "OK");
            break;
            
        case "UPDATE" :
            //메뉴수정
            callService.updateSy2010(paramMap);
            model.addAttribute("result", "OK");
            break;
            
        case "SEARCH01" :
            //메뉴조회
            resultList = callService.selectSy2010List(paramMap);
            model.addAttribute("resultList", resultList);
            break;
        }
        
        return "responseToMybuilder";
    }

}
