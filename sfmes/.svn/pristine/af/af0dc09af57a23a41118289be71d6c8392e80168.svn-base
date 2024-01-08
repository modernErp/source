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

import com.sfmes.cm.web.*;
import com.sfmes.sy.service.Sy1000Service;

/**
 * @Class Name  : Sy1000Controller.java
 * @Description : Sy1000Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.24   김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sy1000Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Sy1000Service")
    private Sy1000Service callService;

    @RequestMapping(value = "/sy1000Controller.do")
    public String sy5010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
        
        case "INSERT" :
            //회사신규등록
            callService.insertCorp_C(paramMap, paramList);
            model.addAttribute("result", "OK");         
            break;
            
        case "UPDATE" :
            //회사정보수정
            callService.updateCorp_C(paramMap, paramList);
            model.addAttribute("result", "OK");         
            break;
            
        case "INSERT02" :
            // 회사환경 초기 설정
            callService.insertSy1000(paramMap);
            model.addAttribute("result", "OK");         
            break;
            
        case "DELETE" :
            // 회사환경 삭제 설정
            callService.deleteSy1000(paramMap);
            model.addAttribute("result", "OK");         
            break;
            
        //SEARCH01,02 조회조건이 다르고 받아오는 변수명이 달라서 구분해놓음
        case "SEARCH01" :
            //회사등록화면조회(CO1000.mvf)     
            resultList = callService.selctDetailSy1000(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
            
        case "SEARCH02" :
            //회사내역화면조회(CO1005.mvf)
            resultList = callService.selectSy1005(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
            
        case "SEARCH03" :
            //사업장그리드조회
            resultList = callService.select_bzpl(paramMap);
            model.addAttribute("resultList", resultList);           
            break;

        }  
        return "responseToMybuilder";
    }
}
