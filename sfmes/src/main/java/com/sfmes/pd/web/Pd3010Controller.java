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
import com.sfmes.pd.service.Pd3010Service;
/**
 * @Class Name  : Pd3010Controller.java
 * @Description : 예정원가 조회/등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.13  여다혜  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.08.13
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Pd3010Controller {
    /** EgovSampleService */
    @Resource(name = "Pd3010Service")
    private Pd3010Service pd3010service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/pd3010Controller.do")
    public String sy2040Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");
        
        //입력된 MSV 타입의 파라미터를 MAP, List형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV01"));

        List<?> resultList = null; //조회결과를 담을 List 
        
        System.out.println("SVCID ::::" + strSVCID);
        
        switch(strSVCID){
            case "SEARCH01" : //최종예정원가 조회
                resultList = pd3010service.selectPd3010_01(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH02" : //적용일자원가 조회
                resultList = pd3010service.selectPd3010_02(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH03" : //예정원가 미등록 품목 조회(예정원가 없는 신규 품목)
                resultList = pd3010service.selectPd3010_03(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                               
            case "SEARCH04" : //예정원가변경이력 조회
                resultList = pd3010service.selectPd3010_04(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "INSERT" : //예정원가 INSERT 
                pd3010service.insertPd3010(paramList);
                model.addAttribute("result", "OK");
                break;
                
            case "UPDATE" : //예정원가 UPDATE   
                pd3010service.updatePd3010(paramList);
                model.addAttribute("result", "OK");
                break;
        }
        
        return "responseToMybuilder";
    }
}
