package com.sfmes.pd.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.pd.service.Pd1010Service;

/**
* @Class Name : Pd1010Controller.java
* @Description : Pd1010Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.07.20   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.07.20
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Pd1010Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Pd1010Service")
    private Pd1010Service callService;
    
    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/pd1010Controller.do")
    public String pd1010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01 = null;
        String inMSV02 = null;

        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        inMSV02 = myBuilderData.getParam("INMSV02");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(inMSV02);
        List<?> resultList01 = null;
        List<?> resultList02 = null;
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
        case "INSERT" :
            //BOM등록
            callService.insertPd1010(paramMap, paramList);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("BOM_C").toString());
            break;
            
        case "UPDATE01" :
            //BOM수정
            callService.updatePd1010_01(paramMap, paramList);
            model.addAttribute("result", "OK");
            break;
            
        case "UPDATE02" :
            //BOM수정
            callService.updatePd1010_02(paramMap);
            model.addAttribute("result", "OK");
            break;
            
        case "SEARCH01" :
            //BOM조회
            resultList01 = callService.selectPd1010List_01(paramMap);
            model.addAttribute("resultList", resultList01);
            break;
            
        case "SEARCH02" :
            //BOMt상세내역조회
            resultList02 = callService.selectPd1010List_02(paramMap);
            model.addAttribute("resultList", resultList02);
            break;
            
        }
        
        return "responseToMybuilder";
       
    }

}
