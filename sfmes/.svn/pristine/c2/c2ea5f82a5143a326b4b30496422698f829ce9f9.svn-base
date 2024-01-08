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
import com.sfmes.pd.service.Pd2530Service;

/**
* @Class Name : Pd2530Controller.java
* @Description : Pd2530Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.11.04   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.11.04
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Pd2530Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Pd2530Service")
    private Pd2530Service callService;
    
    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/pd2530Controller.do")
    public String pd2530Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
        List<Map<String, Object>> paramList01 = myBuilderData.getParamFromMSVList(inMSV02);
        List<Map<String, Object>> paramList02 = myBuilderData.getParamFromMSVList(inMSV03);
        List<?> resultList = null;
        
        
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
        case "INSERT" :
            //공정기록서 기반 작업보고등록
            callService.insertPd2530(paramMap, paramList01, paramList02);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("RPT_SQNO").toString());
            break;
            
        case "UPDATE" :
            //공정기록서 기반 작업보고변경
            callService.updatePd2530(paramMap, paramList01, paramList02);
            model.addAttribute("result", "OK");
            break;
            
        case "SEARCH01" :
            //공정기록서 기반 작업보고 기본정보조회
            resultList = callService.selectPd2530List_01(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH02" :
            //공정기록서 기반 작업보고 투입상세조회
            resultList = callService.selectPd2530List_02(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH03" :
            //공정기록서 기반 작업보고 생산상세조회
            resultList = callService.selectPd2530List_03(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH04" :
            //공정기록서 제품상세(저장전)
            resultList = callService.selectPd2530List_04(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH05" :
            //공정기록서 투입상세(저장전)
            resultList = callService.selectPd2530List_05(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH06" :
            //공정기록서 투입상세(저장전)
            resultList = callService.selectPd2530List_06(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        }
        
        return "responseToMybuilder";
       
    }

}
