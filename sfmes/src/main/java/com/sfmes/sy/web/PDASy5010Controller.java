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
import com.sfmes.sy.service.Sy5010Service;

/**
 * @Class Name  : PDASy5010Controller.java
 * @Description : PDASy5010Service Class
 * @Modification Information
 * @
 * @  수정일             수정자             수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.01   김지혜            최초생성
 * @ 2021.03.22   장경석            PDA용 으로 변경
 *
 * @author (주)모든솔루션
 * @since 2020.06.01
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class PDASy5010Controller {

        @Resource(name = "myBuilderData_PDA")
        protected MyBuilderData_PDA myBuilderData_PDA;
        
        @Resource(name = "Sy5010Service")
        private Sy5010Service callService;

        /**
         * 공통코드등록을 처리한다.
         * @param SVCID
         * @param INMSV01
         * @return "responseToAppChef"
         * @exception Exception
         */
        @RequestMapping(value = "/PDAsy5010Controller.do")
        public String PDAsy5010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
        {
            String strSVCID = null;
            String inMSV01  = null;
            String inMSV02  = null;

            // 파라미터 복호화를 수행한다.
            myBuilderData_PDA.setParam(strData);
            
            // 전송된 파라미터를 추출한다.
            strSVCID = myBuilderData_PDA.getParam("SVCID");
            inMSV01  = myBuilderData_PDA.getParam("INMSV01");
            inMSV02  = myBuilderData_PDA.getParam("INMSV02");
            
            // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
            LinkedHashMap paramMap = myBuilderData_PDA.getParamFromMSVHashMap(inMSV01);
            List<Map<String, Object>> paramList = myBuilderData_PDA.getParamFromMSVList(inMSV02);
            List<?> resultList = null;
            
            switch(strSVCID) {
            
            case "SEARCH01" :
                // 그룹코드 상세조회 서비스를 호출한다.
                resultList = callService.selectSy5010List(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH02" :
                // 공통코드 상세조회 서비스를 호출한다.
                resultList = callService.selectSy5010List02(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH03" :
                //공통코드내역조회(메모리적재용)
                resultList = callService.selectSy5010List03(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH04" :
                //그룹코드 상세조회 서비스를 호출한다.(사용자용)
                resultList = callService.selectSy5015List(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH05" :
                //SY5010P01 공통코드복제리스트조회
                resultList = callService.select_TB_SY_M_COMNGRP(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH06" :
                //SY5010P01 공통코드복제리스트조회
                resultList = callService.select_TB_SY_M_COMNGRPP_COPY(paramMap);
                model.addAttribute("resultList", resultList);
                break;
            }     
            
            return "responseToAppChef";
        }
    }
