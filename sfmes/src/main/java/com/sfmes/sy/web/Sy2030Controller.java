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
import com.sfmes.sy.service.Sy2030Service;

import egovframework.rte.fdl.logging.util.EgovJdkLogger;

/**
 * @Class Name : Sy2030Controller.java
 * @Description : Sy2030 Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------   ------   -------------------------------
 * @ 2020.06.09   여다혜   최초작성
 *
 * @author (주)모든솔루션
 * @since 2020.06.09
 * @version 1.0
 * @see
 *
 *  Copyright (C) by (주)모든솔루션 All right reserved.
 */

@Controller
public class Sy2030Controller {
    /** EgovSampleService */
    @Resource(name = "Sy2030Service")
    private Sy2030Service sy2030service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/sy2030Controller.do")
    public String sy2030Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        
        System.out.println("sy2030복호화 전=======");
        System.out.println("sy2030 StrData ::" + StrData);
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 

        System.out.println("sy2030복호화 후=======");
        
        strSVCID = myBuilderData.getParam("SVCID");
        
        System.out.println("strSVCID ::" + strSVCID);
        
        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));  
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV01"));  
        
        List<?> resultList = null;
        
        switch(strSVCID){
            case "SEARCH01" : //역할그룹내역(공통그룹코드 : 'EMP_ROL_DSC' 인 공통코드 조회) 
                resultList  = sy2030service.selectCmncForEmpRole(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH02" : //역할그룹별 프로그램 내역 조회
                System.out.println("paramMap :::" + paramMap);
                
                resultList = sy2030service.selectPgmAuth(paramMap);
                
                System.out.println("resultList :::" + resultList);
                
                
                model.addAttribute("resultList", resultList);
                break;
            
            case "SAVE01" :   //역할그룹별 권한 저장
                sy2030service.saveMenuAuth(paramList);
                model.addAttribute("result", "OK");
                break;
        }
        
        return "responseToMybuilder";
    }
}
