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
import com.sfmes.sy.service.Sy6010Service;

/**
 * @Class Name  : Sy6010Controller.java
 * @Description : Sy6010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.12.01   이철홍      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.01
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sy6010Controller {

        @Resource(name = "myBuilderData")
        protected MyBuilderData myBuilderData;
        
        @Resource(name = "Sy6010Service")
        private Sy6010Service callService;

        @RequestMapping(value = "/sy6010Controller.do")
        public String Sy6010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
        {
            String strSVCID = null;
            String inMSV01  = null;

            // 파라미터 복호화를 수행한다.
            myBuilderData.setParam(strData);
            
            // 전송된 파라미터를 추출한다.
            strSVCID = myBuilderData.getParam("SVCID");
            inMSV01  = myBuilderData.getParam("INMSV01");
                      
            // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
            LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
            List<?> resultList = null;
            
            switch(strSVCID) {
            
            case "SEARCH01" :
                // 테이블목록 조회 서비스를 호출한다.
                resultList = callService.selectSy6010List(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH02" :
                // 테이블정의서 조회 서비스를 호출한다.
                resultList = callService.selectSy6010List02(paramMap);
                model.addAttribute("resultList", resultList);
                break;
            }     
            return "responseToMybuilder";
        }
    }
