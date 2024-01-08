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
import com.sfmes.pd.service.Pd2030Service;

/**
* @Class Name : Pd2030Controller.java
* @Description : Pd2030Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.08.31   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.08.31
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Pd2030Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Pd2030Service")
    private Pd2030Service callService;
    
    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/pd2030Controller.do")
    public String pd2030Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01 = null;
        String inMSV02 = null;
        String inMSV03 = null;

        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        inMSV02 = myBuilderData.getParam("INMSV02");
        inMSV03 = myBuilderData.getParam("INMSV03");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(inMSV02);
        List<Map<String, Object>> paramList01 = myBuilderData.getParamFromMSVList(inMSV03);
        List<?> resultList = null;
        //List<?> resultList01 = null;
        //List<?> resultList02 = null;
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
        case "INSERT" :
            //작업보고등록
            callService.insertPd2030(paramMap, paramList, paramList01);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("RPT_SQNO").toString());
            break;
            
        case "UPDATE" :
            //작업보고수정
            callService.updatePd2030(paramMap, paramList, paramList01);
            model.addAttribute("result", "OK");
            break;
            
        case "SEARCH01" :
            //작업보고기본조회
            resultList = callService.selectPd2030List_01(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH02" :
            //작업보고상세내역조회
            resultList = callService.selectPd2030List_02(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH03" :
            //작업지시기본조회
            resultList = callService.selectPd2030List_03(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH04" :
            //작업지시상세내역조회
            resultList = callService.selectPd2030List_04(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH05" :
            //투입인력내역조회
            resultList = callService.selectPd2030List_05(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        }
        
        return "responseToMybuilder";
       
    }

}
