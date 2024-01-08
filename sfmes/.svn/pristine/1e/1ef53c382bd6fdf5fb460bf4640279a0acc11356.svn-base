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
import com.sfmes.sy.service.Sy2040Service;
/**
 * @Class Name : Sy2040Controller.java
 * @Description : 사용자별역할 조회/등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.06.12  여다혜  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sy2040Controller {
    /** EgovSampleService */
    @Resource(name = "Sy2040Service")
    private Sy2040Service sy2040service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/sy2040Controller.do")
    public String sy2040Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");
        
        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV01"));

        List<?> resultList = null; //조회결과를 담을 List 
        
        switch(strSVCID){
            case "SEARCH01" : //사용자정보내역 조회
                resultList = sy2040service.selectUsrInfoList(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH02" : //사용자별역할그룹내역 조회
                resultList = sy2040service.selectUsrRole(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SAVE01" :   //사용자별역할그룹내역 저장 
                sy2040service.saveUsrRole(paramList);
                model.addAttribute("result", "OK");
                break;
        }
        
        return "responseToMybuilder";
    }
}
