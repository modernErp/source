package com.sfmes.by.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.by.service.By2110Service;
import com.sfmes.cm.web.MyBuilderData;

/**
 * @Class Name  : By2110Service.java
 * @Description : By2110Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.09.10   김지혜     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class By2110Controller {
    
    @Resource(name = "By2110Service")
    private By2110Service by2110Service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/by2110Controller.do")
    public String by2110Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
        
        switch(strSVCID){
            case "SEARCH01" : //발주현황(거래처)거래처별내역
                resultList = by2110Service.selectBy2110_trpl_01(paramMap); 
                model.addAttribute("resultList", resultList);
                break; 
                
            case "SEARCH02" : //발주현황(거래처)전표별내역
                resultList = by2110Service.selectBy2110_trpl_02(paramMap); 
                model.addAttribute("resultList", resultList);
                break; 
                
            case "SEARCH03" : //발주현황(거래처)물품별내역
                resultList = by2110Service.selectBy2110_trpl_03(paramMap); 
                model.addAttribute("resultList", resultList);
                break; 
                
            case "SEARCH04" : //발주현황(물품) 물품별내역
                resultList = by2110Service.selectBy2110_gds_01(paramMap); 
                model.addAttribute("resultList", resultList);
                break; 
                
            case "SEARCH05" : //발주현황(물품) 거래처별내역
                resultList = by2110Service.selectBy2110_gds_02(paramMap); 
                model.addAttribute("resultList", resultList);
                break; 
                
            case "SEARCH06" : //발주현황(물품) 전표별내역
                resultList = by2110Service.selectBy2110_gds_03(paramMap); 
                model.addAttribute("resultList", resultList);
                break; 
                
        }  
        return "responseToMybuilder";
    }
}
