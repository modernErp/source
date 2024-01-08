package com.sfmes.pd.web;

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
import com.sfmes.pd.service.Pd2055Service;

/**
* @Class Name : Pd2055Controller.java
* @Description : Pd2055Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.13   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.13
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Pd2055Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Pd2055Service")
    private Pd2055Service callService;
    
    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/pd2055Controller.do")
    public String pd2015Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
        case "UPDATE" :
            //작업지시출고의뢰내역삭제여부변경
            callService.updatePd2055(paramMap,paramList);
            model.addAttribute("result", "OK");
            break;
            
        case "SEARCH01" :
            //작업지시출고의뢰내역조회
            resultList01 = callService.selectPd2055List_01(paramMap);
            model.addAttribute("resultList", resultList01);
            break;
            
        }
        
        return "responseToMybuilder";
       
    }

}
