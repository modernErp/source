package com.sfmes.ge.web;

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
import com.sfmes.ge.service.Ge1040Service;
/**
 * @Class Name  : Ge1040Controller.java
 * @Description : 사용자목소리 조회/등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.10.28  여다혜  최초생성
 * @ 2020.12.28  이동훈  변경
 *
 * @author (주)모든솔루션
 * @since 2020.10.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Ge1040Controller {
    /** EgovSampleService */
    @Resource(name = "Ge1040Service")
    private Ge1040Service ge1040service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/ge1040Controller.do")
    public String ge1020Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null;   //서비스분기용 String
        String voice_sqno = null; //재조회를 위한 리턴값(사용자목소리seq)
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");
        
        //입력된 MSV 타입의 파라미터를 MAP, List형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));

        List<?> resultList = null; //조회결과를 담을 List 
        
        switch(strSVCID){
            case "SEARCH01" : //GE1040 : 사용자 목소리 조회 1건 (팝업)
                resultList = ge1040service.selectGe1040(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH02" : //GE1045 : 사용자목소리 내역 조회 
                resultList = ge1040service.selectGe1045(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "INSERT" :   //사용자목소리 저장
                voice_sqno = ge1040service.insertGe1040(paramMap);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", voice_sqno);
                break;

            case "UPDATE" :   //사용자목소리 수정
                voice_sqno = ge1040service.updateGe1040(paramMap);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", voice_sqno);
                break;                 
        }
        
        return "responseToMybuilder";
    }
}
