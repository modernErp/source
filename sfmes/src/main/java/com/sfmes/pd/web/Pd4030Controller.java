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
import com.sfmes.pd.service.Pd4030Service;

/**
* @Class Name : Pd4030Controller.java
* @Description : Pd4030Service Class
* @Modification Information
* @
* @  수정일             수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.11.09   장경석             최초생성
*
* @author (주)모든솔루션
* @since 2020.11.09
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Pd4030Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Pd4030Service")
    private Pd4030Service callService;
    
    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/pd4030Controller.do")
    public String Pd4030Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
	            //공정기록서 기반 작업지시등록
	            callService.insertPd4030(paramMap, paramList01, paramList02);
	            model.addAttribute("result", "OK");
	            model.addAttribute("returnValue01", paramMap.get("DNTT_SQNO").toString());
	            model.addAttribute("returnValue02", paramMap.get("DNTT_DNO").toString());
	            break;
	            
	        case "UPDATE" :
	            //공정기록서 기반 작업지시변경
	            callService.updatePd4030(paramMap, paramList01, paramList02);
	            model.addAttribute("result", "OK");
	            break;
	            
	        case "SEARCH01" :
	            //공정기록서 기반 작업지시 기본정보조회
	            resultList = callService.selectPd4030List_01(paramMap);
	            model.addAttribute("resultList", resultList);
	            break;
	            
	        case "SEARCH02" :
	            //공정기록서 기반 작업지시 제품상세조회
	            resultList = callService.selectPd4030List_02(paramMap);
	            model.addAttribute("resultList", resultList);
	            break;
	            
	        case "SEARCH03" :
	            //공정기록서 기반 작업지시 투입상세조회
	            resultList = callService.selectPd4030List_03(paramMap);
	            model.addAttribute("resultList", resultList);
	            break;
	            
	        case "SEARCH04" :
	            //공정기록서 제품상세(저장전)
	            resultList = callService.selectPd4030List_04(paramMap);
	            model.addAttribute("resultList", resultList);
	            break;
	            
	        case "SEARCH05" :
	            //공정기록서 투입상세(저장전)
	            resultList = callService.selectPd4030List_05(paramMap);
	            model.addAttribute("resultList", resultList);
	            break;
        }
        
        return "responseToMybuilder";
       
    }

}
