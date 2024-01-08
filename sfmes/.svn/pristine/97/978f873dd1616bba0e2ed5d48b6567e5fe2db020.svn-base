package com.sfmes.se.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.se.service.Se6020Service;
/**
 * @Class Name  : Se6020ServiceImpl.java
 * @Description : 제품출고등록 Controller
 * @Modification Information
 * @
 * @  수정일       수정자      수정내용
 * @ ----------   ---------   -------------------------------
 * @ 2020.09.14    곽환용      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se6020Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Se6020Service")
    private Se6020Service callService;

    @RequestMapping(value = "/se6020Controller.do")
    public String se6020Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String result   = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
                  
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02"));
        
        switch(strSVCID) {
            case "SEARCH01" :   //제품출고기본내역조회 
                List<?> resultList01 = callService.selectSe6020_01(paramMap);
                model.addAttribute("resultList", resultList01);           
                break;
            
            case "SEARCH02" :   //제품출고상세내역조회 
                List<?> resultList02 = callService.selectSe6020_02(paramMap);
                model.addAttribute("resultList", resultList02);           
                break;
                
            case "SEARCH03" :   //제품출고내역찾기팝업 
                List<?> resultList03 = callService.selectSe6020_03(paramMap);
                model.addAttribute("resultList", resultList03);    
                break;

            case "SEARCH05" :   //참조출고지시기본내역조회
                List<?> resultList04 = callService.selectSe6020_M_DLR(paramMap);
                model.addAttribute("resultList", resultList04);    
                break;            
        
            case "SEARCH06" :   //참조출고지시상세내역조회
                List<?> resultList05 = callService.selectSe6020_D_DLR(paramMap);
                model.addAttribute("resultList", resultList05);    
                break;
                
            case "SEARCH07" :   //거래명세서출력
                List<?> resultList = callService.selectSe6020_R(paramMap);
                model.addAttribute("resultList", resultList);    
                break;                
                
            case "SEARCH_PDAGDS" :   // PDA 입고예정물품정보조회
                resultList = callService.select_PDA_DLR_GDS(paramMap);
                model.addAttribute("resultList", resultList);    
                break;
                
            case "SEARCH09" :   // 물품기본 출고지시참조내역조회
                resultList = callService.select_Pda_Sm1050_DLRGDS_M(paramMap);
                model.addAttribute("resultList", resultList);    
                break;  
                
            case "SEARCH_PDADT" :   // PDA 출고예정일조회
                resultList = callService.select_Pda_Sm1050_DLRDT(paramMap);
                model.addAttribute("resultList", resultList);    
                break;
                
            case "SEARCH_PDATRPL" :   // PDA 출고예정일의 거래처조회
                resultList = callService.select_Pda_TRPL(paramMap);
                model.addAttribute("resultList", resultList);    
                break;    

            case "SEARCH08" :   //발주참조내역조회
                resultList = callService.select_Pda_Sm1050_DLR_M(paramMap);
                model.addAttribute("resultList", resultList);    
                break;  

            case "SAVE01" :     //제품출고내역저장
                result = callService.saveSe6020(paramMap, paramList);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("DLR_DT").toString());
                model.addAttribute("returnValue02", paramMap.get("DLR_SQNO").toString());
                break;
                
            case "UPDATE01" :   //제품출고내역수정
                result = callService.updateSe6020(paramMap, paramList);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("DLR_DT").toString());
                model.addAttribute("returnValue02", paramMap.get("DLR_SQNO").toString());
                break;
                
            case "DELETE" :     //제품출고내역삭제 
                callService.deleteSe6020(paramMap);
                model.addAttribute("result", "OK");
                break;               
    }
        return "responseToMybuilder";
    }   
}
