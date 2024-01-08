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
import com.sfmes.co.service.Co1010Service;
import com.sfmes.pd.service.Pd5030Service;

/**
* @Class Name : Pd5030Controller.java
* @Description : Pd5030Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.11.27   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.11.27
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Pd5030Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Pd5030Service")
    private Pd5030Service callService;
    
    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @exception Exception
     */
    @RequestMapping(value = "/pd5030Controller.do")
    public String pd5030Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        String strSVCID = null;
        String inMSV01 = null;
        String inMSV02 = null;

        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        inMSV02 = myBuilderData.getParam("INMSV02");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(inMSV02);
        List<?> resultList01 = null; // 폼 조회결과 변수
        List<?> resultList02 = null; // 리스트 조회결과 변수
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
        case "INSERT" :
        	callService.insertPd5030(paramMap, paramList);
            model.addAttribute("result", "OK");
            break;
            
        case "UPDATE" :
            callService.updatePd5030(paramMap, paramList);
            model.addAttribute("result", "OK");
            break;
            
        case "SEARCH01" :  //위탁의뢰접수 조회
        	resultList01 = callService.selectPd5030_01(paramMap);
        	model.addAttribute("resultList", resultList01);
        	break;
            
        case "SEARCH02" :  //위탁의뢰제품 입고내역 조회
        	resultList02 = callService.selectPd5030_02(paramMap);
        	model.addAttribute("resultList", resultList02);
        	break;
        }
        
        return "responseToMybuilder";
       
    }

}
