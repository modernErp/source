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
import com.sfmes.pd.service.Pd4010Service;

/**
* @Class Name : Pd4010Controller.java
* @Description : Pd4010Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.16   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.16
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Pd4010Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Pd4010Service")
    private Pd4010Service callService;
    
    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @exception Exception
     */
    @RequestMapping(value = "/pd4010Controller.do")
    public String pd4010Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        String strSVCID = null; // 서비스 구분 변수
        String inMSV01 = null;

        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<?> resultList = null;
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
	        case "INSERT" : // 수탁가공 접수 등록
	        	String returnValue = callService.insertPd4010(paramMap);
	            model.addAttribute("result", "OK");
	            model.addAttribute("returnValue01", returnValue);
	            break;
	            
	        case "UPDATE" : // 수탁가공 접수 수정 
	            callService.updatePd4010(paramMap);
	            model.addAttribute("result", "OK");
	            break;
	            
	        case "DELETE" : // 수탁가공 접수 삭제
	        	callService.deletePd4010(paramMap);
	        	model.addAttribute("result", "OK");
	        	break;
	        	
	        case "SEARCH01" : // 수탁가공 접수 조회
	        	resultList = callService.selectPd4010(paramMap);
	            model.addAttribute("resultList", resultList);
	            break;
        }
        
        return "responseToMybuilder";
       
    }

}
