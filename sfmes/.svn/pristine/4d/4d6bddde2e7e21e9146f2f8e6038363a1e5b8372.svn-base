package com.sfmes.pd.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.pd.service.Pd2515Service;

/**
* @Class Name : Pd2515Controller.java
* @Description : Pd2515Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.16   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.16
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Pd2515Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Pd2515Service")
    private Pd2515Service callService;
    
    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/pd2515Controller.do")
    public String pd2515Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01 = null;

        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<?> resultList = null;
        
        
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
        case "SEARCH01" :
            //공정기록서 기반 작업지시 내역조회
            resultList = callService.selectPd2515List_01(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
//        case "SEARCH02" :
//            //공정기록서 기반 작업지시 제품상세조회
//            resultList = callService.selectPd2510List_02(paramMap);
//            model.addAttribute("resultList", resultList);
//            break;
            
        }
        
        return "responseToMybuilder";
       
    }

}
