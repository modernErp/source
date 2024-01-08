package com.sfmes.ge.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.popbill.api.MessageService;
import com.popbill.api.PopbillException;
import com.popbill.api.message.Message;
import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.ge.service.Ge2040Service;

/**
 * @Class Name : Ge2040Controller.java
 * @Description : 민원사후 처리내역 조회
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2021.02.04  박지환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2021.02.04
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Ge2040Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Ge2040Service")
    private Ge2040Service ge2040service;

    /**
     * 민원사후 처리 내역 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/ge2040Controller.do")
    public String ge2040Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        
        String strSVCID = null;
        String inMSV01 = null;
        String inMSV02 = null;
        String inMSV03 = null;
        
        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        inMSV02 = myBuilderData.getParam("INMSV02");
        inMSV03 = myBuilderData.getParam("INMSV03");

        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap<String, Object> paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        // 수신자 리스트
        List<Map<String, Object>> paramList02 = myBuilderData.getParamFromMSVList(inMSV02);
        // fax파일 리스트
        List<Map<String, Object>> paramList03 = myBuilderData.getParamFromMSVList(inMSV03);
        
        switch(strSVCID) {
            case "SEARCH01" : //수신자 리스트
                List<?> resultList = ge2040service.selectGe2040_01(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH02" : //카카오 알림톡 템플릿 리슽크
            	List<?> resultList01 = ge2040service.kakaoTemplate();
            	model.addAttribute("resultList", resultList01);
            	break;
            	
            case "SMS" :
                String resultState01 = ge2040service.msgSend(paramMap, paramList02);
                model.addAttribute("result", resultState01);
                break;
            
            case "FAX" :
            	String resultState02 = ge2040service.faxSend(paramList02, paramList03);
                model.addAttribute("result", resultState02);
                break;
                
            case "KAKAO" :
            	String resultState03 = ge2040service.kakaoSend(paramMap, paramList02);
                model.addAttribute("result", resultState03);
                break;
        }
        
        
        return "responseToMybuilder";
    }
    
}
