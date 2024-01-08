package com.sfmes.sm.web;

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
import com.sfmes.sm.service.Sm1050Service;

/**
 * @Class Name  : Sm1050ServiceImpl.java
 * @Description : Sm1050Service Class
 * @Modification Information
 * @
 * @  수정일       수정자      수정내용
 * @ ----------   ---------   -------------------------------
 * @ 2020.08.26    곽환용      최초생성
 * @ 2020.12.28    이동훈      변경
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sm1050Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Sm1050Service")
    private Sm1050Service callService;
    
    /**
     * 공통코드등록을 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/sm1050Controller.do")
    public String sm1050Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01  = null;
        String inMSV02  = null;
        String result   = null;

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
        
        switch(strSVCID) {
            case "SEARCH01" :   //제품출고기본내역조회 
                resultList = callService.select_Sm1050_01(paramMap);
                model.addAttribute("resultList", resultList);           
                break;
            
            case "SEARCH02" :   //제품출고상세내역조회 
                resultList = callService.select_Sm1050_02(paramMap);
                model.addAttribute("resultList", resultList);           
                break;
                
            case "SEARCH03" :   //제품출고내역찾기팝업 
                resultList = callService.select_Sm1050_03(paramMap);
                model.addAttribute("resultList", resultList);    
                break;
                
            case "SEARCH04" :   //제품출고내역조회 
                resultList = callService.select_Sm1050_04(paramMap);
                model.addAttribute("resultList", resultList);    
                break;
                
            case "SEARCH05" :   //참조출고지시기본내역조회
                resultList = callService.select_Sm1050_M_DLR(paramMap);
                model.addAttribute("resultList", resultList);    
                break;            
        
            case "SEARCH06" :   //참조출고지시상세내역조회
                resultList = callService.select_Sm1050_D_DLR(paramMap);
                model.addAttribute("resultList", resultList);    
                break;        
                
            case "SAVE01" :     //제품출고내역저장
                System.out.println("paramMap for INSERT ::::: "+paramMap);
                System.out.println("paramList for INSERT ::::: "+paramList);
                result = callService.saveSm1050(paramMap, paramList);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("STDV_DT").toString());
                model.addAttribute("returnValue02", paramMap.get("STDV_SQNO").toString());
                break;
                
            case "UPDATE01" :   //제품출고내역수정
                System.out.println("paramMap for UPDATE ::::: "+paramMap);
                System.out.println("paramList for UPDATE ::::: "+paramList);            
                result = callService.updateSm1050(paramMap, paramList);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("STDV_DT").toString());
                model.addAttribute("returnValue02", paramMap.get("STDV_SQNO").toString());
                break;
                
            case "DELETE" :     //제품출고내역삭제 
                System.out.println("paramMap for DELETE01 ::::: "+paramMap);
                System.out.println("paramList for DELETE01 ::::: "+paramList);
                callService.deleteSm1050(paramMap, paramList);
                model.addAttribute("result", "OK");
                break;               
    }
        return "responseToMybuilder";
    }   
}
