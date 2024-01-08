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
import com.sfmes.co.service.Co1000Service;

/**
 * @Class Name  : Co1000Controller.java
 * @Description : 
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.09.04   여다혜      최초생성
 * @ 2020.12.28   이수빈      변경
 *
 * @author (주)모든솔루션
 * @since 2020.09.04
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Co1000Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Co1000Service")
    private Co1000Service callService;

    @RequestMapping(value = "/co1000Controller.do")
    public String co1000Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV02  = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02"));
        List<?> resultList = null; 
    
        switch(strSVCID) {
            case "SEARCH01" :  //회사정보조회
                System.out.println("CO1000 SEARCH01 PARAMMAP ::: " + paramMap);
                resultList = callService.selctCorpInfo(paramMap);
                System.out.println("resultList for SEARCH01 :::" + resultList);
                model.addAttribute("resultList", resultList);           
                break;    
              
            case "SEARCH02" :  //사업장목록조회
                System.out.println("CO1000 SEARCH02 PARAMMAP :::" + paramMap);
                resultList = callService.selectBzplList(paramMap);
                System.out.println("resultList for SEARCH02 :::" + resultList);
                model.addAttribute("resultList", resultList);
                break;
                
            
            case "SAVE01" : //회사정보, 사업자정보 수정
                System.out.println("CO1000 SAVE01 paramMap :::" + paramMap);
                System.out.println("CO1000 SAVE01 paramList :::" + paramList);
                callService.updateCo1000(paramMap, paramList);
                model.addAttribute("result", "OK");
                break;
        }  
        return "responseToMybuilder";
    }
}
