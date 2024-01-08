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
import com.sfmes.ge.service.Ge1010Service;
/**
 * @Class Name  : Ge1010Controller.java
 * @Description : 공지사항 조회/등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.10.19  여다혜  최초생성
 * @ 2020.12.28  이동훈  변경
 *
 * @author (주)모든솔루션
 * @since 2020.10.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Ge1010Controller {
    /** EgovSampleService */
    @Resource(name = "Ge1010Service")
    private Ge1010Service ge1010service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/ge1010Controller.do")
    public String ge1010Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String ofanc_sqno = null; //재조회를 위한 리턴값(공지사항seq)
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");
        
        //입력된 MSV 타입의 파라미터를 MAP, List형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02"));

        List<?> resultList = null; //조회결과를 담을 List 
        
        switch(strSVCID){
            case "SEARCH01" : //공지사항 전체 조회 (GE1010 등록화면)
                resultList = ge1010service.selectGe1010_OFANC_List(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH02" : //공지사항 1건 조회
                System.out.println("GE1010 Controller, SEARCH02, PARAMETER ==>" + paramMap);
                resultList = ge1010service.selectGe1010_OFANC_Detail(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "INSERT" :   //공지사항 저장
                System.out.println("GE1010 Controller, INSERT, PARAMETER ==>" + paramMap);
                ofanc_sqno = ge1010service.insertGe1010_OFANC(paramMap);
                System.out.println("controller 공지사항번호seq ===>" + ofanc_sqno);
                
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", ofanc_sqno);
                break;

            case "UPDATE" :   //공지사항 수정
                System.out.println("GE1010 Controller, UPDATE, PARAMETER ==>" + paramMap);
                ofanc_sqno = ge1010service.updateGe1010_OFANC(paramMap);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", ofanc_sqno);
                break;    
        }
        
        return "responseToMybuilder";
    }
}
