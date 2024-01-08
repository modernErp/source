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
import com.sfmes.sy.service.Sy9000Service;

/**
 * @Class Name  : Sy9000Controller.java
 * @Description : Sy9000Service Class
 * @Modification Information
 * @
 * @  수정일             수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2021.01.28   장경석            최초생성
 *
 * @author (주)모든솔루션
 * @since 2021.01.28
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sy9000Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Sy9000Service")
    private Sy9000Service sy9000Service;

    @RequestMapping(value = "/sy9000Controller.do")
    public String sy9010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
    
        String strMsg = ""; // 메시지
        String result = ""; // 쿼리수행결과(정상:"OK")

        switch (strSVCID) {
        case "SEARCH01": /** 조회용 SQL 수행 서비스 */
            resultList = sy9000Service.selectSe9000(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SAVE01": /** CUD용 SQL 수행 서비스 */
            sy9000Service.saveSy9000(paramMap);
            model.addAttribute("result", "OK");
            break;
        }

        return "responseToMybuilder";
    }
}
