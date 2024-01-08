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
import com.sfmes.dl.service.Dl1250Service;

/**
 * @Class Name  : Dl1250Controller.java
 * @Description : Dl1250Service Class
 * @Modification Information
 * @
 * @  수정일              수정자                수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2022.09.19    김주경               최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.09.19
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Dl1250Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Dl1250Service")
    private Dl1250Service callService;
    
    @RequestMapping(value = "/Dl1250Controller.do")
    public String dl1250Controller(HttpServletRequest strData, ModelMap model) throws Exception{
        
        String strSVCID = null;
        String inMSV01 = null;
        
        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        
        List<?> resultList = null;
        
        switch(strSVCID) {
            
            case"SEARCH01" :
                //마감등록조회
                LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
                resultList = callService.selectDl1250List_01(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case"DELETE" :
                // 마감등록 사용여부 'N' 으로 변경
                List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(inMSV01);
                
                callService.updateDl1250List_01(paramList);
                model.addAttribute("result", "OK");
                break;
        }
        return "responseToMybuilder";
    }
}
