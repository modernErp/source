package com.sfmes.dl.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.dl.service.Dl1050Service;

/**
 * @Class Name  : Dl1050Controller.java
 * @Description : Dl1050Service Class
 * @Modification Information
 * @
 * @  수정일              수정자                수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2022.01.27    구민희               최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.01.27
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Dl1050Controller {

        @Resource(name = "myBuilderData")
        protected MyBuilderData myBuilderData;
        
        @Resource(name = "Dl1050Service")
        private Dl1050Service callService;
        
        @RequestMapping(value = "/Dl1050Controller.do")
        public String dl1050Controller(HttpServletRequest strData, ModelMap model) throws Exception 
        {   
            String strSVCID = null;
            String inMSV01  = null;

            // 파라미터 복호화를 수행한다.
            myBuilderData.setParam(strData);
                
            // 전송된 파라미터를 추출한다.
            strSVCID = myBuilderData.getParam("SVCID");
            inMSV01  = myBuilderData.getParam("INMSV01");

            List<?> resultList = null;

            // 서비스 구분에 따라 분기 처리한다.
            switch(strSVCID) { 
                case "SEARCH01" :
                    // 마감등록조회
                    LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
                    resultList = callService.selectDl1050List(paramMap);
                    model.addAttribute("resultList", resultList);
                    break;
                
                case "SEARCH02" :
                    // 마감일보조회
                    LinkedHashMap paramMap01 = myBuilderData.getParamFromMSVHashMap(inMSV01);
                    resultList = callService.selectDl1050List_02(paramMap01);
                    model.addAttribute("resultList", resultList);
                    break;
                    
                case "UPDATE" :
                    //INMSV02 (마이빌더 msv데이터)를 list로 변경
                    List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(inMSV01);

                    // 마감등록변경
                    callService.updateDl1050List(paramList);
                    
                    model.addAttribute("result", "OK");
                    break;
            }
                
            return "responseToMybuilder";

        }
}