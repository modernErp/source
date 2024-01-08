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
import com.sfmes.pd.service.Pd2010Service;

/**
* @Class Name : Pd2010ServiceImpl.java
* @Description : Pd2010Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.08.04   김수민     최초생성
* @ 2021.01.13   이수빈     작업지시내역출력조회, 원재료입고(예정)내역 추가
*
* @author (주)모든솔루션
* @since 2020.08.04
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Pd2010Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Pd2010Service")
    private Pd2010Service callService;
    
    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/pd2010Controller.do")
    public String pd2010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
            //작업지시등록
            callService.insertPd2010(paramMap, paramList01, paramList02);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("DNTT_SQNO").toString());
            model.addAttribute("returnValue02", paramMap.get("DNTT_DNO").toString());
            break;
            
        case "UPDATE" :
            //작업지시수정
            callService.updatePd2010(paramMap, paramList01, paramList02);
            model.addAttribute("result", "OK");
            break;
            
        case "SEARCH01" :
            //작업지시 기본조회
            resultList = callService.selectPd2010List_01(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH02" :
            //작업지시 제품조회
            resultList = callService.selectPd2010List_02(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH03" :
            //작업지시상세내역조회
            resultList = callService.select_Pd2010List03(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH04" :
            //제품상세
            resultList = callService.selectPd2010List_04(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH05" :
            //제품상세
            resultList = callService.selectPd2010List_05(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH06" :
            // 기등록된 반제품조회  20211214 rchkorea
            resultList = callService.selectPd2010List_06(paramMap);
            model.addAttribute("resultList", resultList);
            break;

        }
        
        return "responseToMybuilder";
       
    }

}
