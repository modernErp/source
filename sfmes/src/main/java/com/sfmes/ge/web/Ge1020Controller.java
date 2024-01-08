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
import com.sfmes.ge.service.Ge1020Service;
/**
 * @Class Name  : Ge1020Controller.java
 * @Description : 업무연락 조회/등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.10.23  여다혜  최초생성
 * @ 2020.12.28  이동훈  변경
 *
 * @author (주)모든솔루션
 * @since 2020.10.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Ge1020Controller {
    /** EgovSampleService */
    @Resource(name = "Ge1020Service")
    private Ge1020Service ge1020service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/ge1020Controller.do")
    public String ge1020Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String bsn_ctc_sqno = null; //재조회를 위한 리턴값(업무연락seq)
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");
        
        //입력된 MSV 타입의 파라미터를 MAP, List형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02"));

        List<?> resultList = null; //조회결과를 담을 List 
        
        switch(strSVCID){
            case "SEARCH01" : //GE1020 : 업무연락 등록 조회 (*사용자가 등록한 업무연락)
                resultList = ge1020service.selectGe1020(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH02" : //GE1025 : 업무연락 내역 조회 (*사용자가 수신한 업무연락)
                resultList = ge1020service.selectGe1025(paramMap);
                model.addAttribute("resultList", resultList);
                break;
            
            case "SEARCH03" : //GE1010P01 : 업무연락 상세 조회 
                resultList = ge1020service.selectGe1020P01_Detail(paramMap);
                model.addAttribute("resultList", resultList);
                break;

            case "SEARCH04" : //GE1010P01 : 업무연락 수신자목록 조회 
                resultList = ge1020service.selectGe1020P01_Rcst_id_List(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "INSERT" :   //공지사항 저장
                bsn_ctc_sqno = ge1020service.insertGe1020(paramMap, paramList);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", bsn_ctc_sqno);
                break;

            case "UPDATE" :   //공지사항 수정
                bsn_ctc_sqno = ge1020service.updateGe1020(paramMap, paramList);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", bsn_ctc_sqno);
                break;    
                
            case "UPDATE_RC_STS_C" : //수신자 수신확인 업데이트
                ge1020service.update_RC_STS_C(paramMap);
                model.addAttribute("result", "OK");
                break;                
                
        }
        
        return "responseToMybuilder";
    }
}
