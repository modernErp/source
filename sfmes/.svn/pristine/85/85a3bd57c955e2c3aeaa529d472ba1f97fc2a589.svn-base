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
 * @Class Name  : Sy5010Controller.java
 * @Description : Sy5010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.01   김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.01
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sy5010Controller {

        @Resource(name = "myBuilderData")
        protected MyBuilderData myBuilderData;
        
        @Resource(name = "Sy5010Service")
        private Sy5010Service callService;

        /**
         * 공통코드등록을 처리한다.
         * @param SVCID
         * @param INMSV01
         * @return "responseToMybuilder"
         * @exception Exception
         */
        @RequestMapping(value = "/sy5010Controller.do")
        public String sy5010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
        {
            String strSVCID = null;
            String inMSV01  = null;
            String inMSV02  = null;

            // 파라미터 복호화를 수행한다.
            myBuilderData.setParam(strData);
            
            // 전송된 파라미터를 추출한다.
            strSVCID = myBuilderData.getParam("SVCID");
            inMSV01  = myBuilderData.getParam("INMSV01");
            inMSV02  = myBuilderData.getParam("INMSV02");
                      
            // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
            LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
            List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(inMSV02);
            List<?> resultList = null;
            
            switch(strSVCID) {
            
            case "SAVE01" :
                // 그룹(공통)코드 신규 서비스를 호출한다.
                callService.save01Sy5010(paramMap, paramList);
                model.addAttribute("result", "OK");
                break;
                
            case "SAVE02" :
                // 그룹(공통)코드 수정 서비스를 호출한다.
                callService.save02Sy5010(paramMap, paramList);
                model.addAttribute("result", "OK");
                break;
                
            case "SAVE03" :
                // 그룹(공통)코드 복제 서비스를 호출한다.
                callService.save03Sy5010P01(paramList);
                model.addAttribute("result", "OK");
                break;
               
            case "DELETE" :
                // 그룹코드 삭제
                callService.deleteSy5010(paramMap, paramList);
                model.addAttribute("result", "OK");
                break;
                
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
            return "responseToMybuilder";
        }
    }
