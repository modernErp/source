package com.sfmes.se.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.se.service.Se1030Service;
import com.sfmes.se.service.Se2010Service;
/**
 * @Class Name : Se2010Controller.java
 * @Description : 수주등록/수정 및 조회 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.14  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se2010Controller {    

    @Resource(name = "Se2010Service")
    private Se2010Service se2010service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/se2010Controller.do")
    public String se2010Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String result = "";
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); //수주등록 parameterList 
        
        switch(strSVCID){
            case "SEARCH01" : //수주기본내역 조회
                List<?> resultList01 = se2010service.selectSe2010_01(paramMap); 
                model.addAttribute("resultList", resultList01);
                break;
                
            case "SEARCH02" : //수주상세내역 조회
                List<?> resultList02 = se2010service.selectSe2010_02(paramMap);
                model.addAttribute("resultList", resultList02);
                break;
                
            case "SEARCH03" : //수주내역찾기팝업 조회
                List<?> resultList03 = se2010service.selectSe2010_03(paramMap);
                model.addAttribute("resultList", resultList03);
                break;
                
            case "SEARCH04" : //매출단가부가세포함여부조회
                List<?> resultList04 = se2010service.selectSe2010_04(paramMap);
                model.addAttribute("resultList", resultList04);
                break;                
      
            case "INSERT" :   //수주내역기본,상세 저장 
                result = se2010service.insertSe2010(paramMap, paramList);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("RVO_DT").toString());
                model.addAttribute("returnValue02", paramMap.get("RVO_SQNO").toString());
                break;
                
            case "UPDATE" :   //수주내역기본,상세 수정 
                result = se2010service.updateSe2010(paramMap, paramList);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("RVO_DT").toString());
                model.addAttribute("returnValue02", paramMap.get("RVO_SQNO").toString());
                break;                

            case "DELETE" :   //수주내역 삭제 
                se2010service.deleteSe2010(paramMap);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("RVO_DT").toString());
                model.addAttribute("returnValue02", paramMap.get("RVO_SQNO").toString());
                break;
        }
        
        return "responseToMybuilder";
    }
}
