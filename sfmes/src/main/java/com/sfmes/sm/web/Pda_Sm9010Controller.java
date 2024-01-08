package com.sfmes.sm.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.cm.web.MyBuilderData_PDA;
import com.sfmes.sm.service.Pda_Sm9010Service;


/**
 * @Class Name  : Pda_Sm9010Controller.java
 * @Description : Pda_Sm9010Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2021.10.07   이동훈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2021.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Pda_Sm9010Controller {
    
    @Resource(name = "myBuilderData_PDA")
    protected MyBuilderData_PDA myBuilderData_PDA;
    
    @Resource(name = "Pda_Sm9010Service")
    private Pda_Sm9010Service callService;
  
    /**
     * 공통코드등록을 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/pda_sm9010Controller.do")
    public String pda_sm9010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01  = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData_PDA.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData_PDA.getParam("SVCID");
        inMSV01  = myBuilderData_PDA.getParam("INMSV01");
                  
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData_PDA.getParamFromMSVHashMap(inMSV01);
 
        List<?> resultList = null;
        String strMsg = null;
        String result = null;
        
        switch(strSVCID) {
        case "SEARCHgds" :     //물품기본조회
            resultList = callService.search_Pda_Sm9010_gds(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCHtrpl" :   //거래처기본조회
            resultList = callService.search_Pda_Sm9010_trpl(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SAVE01" :     //PDA 저장
            resultList = callService.insertinfopda(paramMap);
            System.out.println("PDASM9010controller paramMap:::" + paramMap);
            model.addAttribute("resultList", resultList);
            break;   
            
        case "SAVE02" :     //PDA 박스이동저장
            resultList = callService.insertmovebox(paramMap);
            System.out.println("PDASM9010controller paramMap:::" + paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SAVE03" :     //PDA 오프라인 저장
            resultList = callService.insertoffdata(paramMap);
            System.out.println("PDASM9010controller resultList:::" + resultList);
            model.addAttribute("resultList", resultList);   
            break;
         
            
        case "SEARCH01" :     //PDA 지시번호 조회 
            resultList = callService.selectEmesbarcode(paramMap);
            System.out.println("PDASM9010controller resultList:::" + resultList);
            model.addAttribute("resultList", resultList);   
            break; 
            
        case "SEARCHwhse" :   //사업장기본입고창고조회
            resultList = callService.search_Pda_Sm9010_whse(paramMap);
            System.out.println("PDASM9010controller resultList:::" + resultList);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCHwhselist" :   //사업장기본입고창고조회
            resultList = callService.search_Pda_Sm9010_whse_list(paramMap);
            System.out.println("PDASM9010controller resultList:::" + resultList);
            model.addAttribute("resultList", resultList);
            break;  
            
        case "SEARCHwhsenm" :   //사업장기본입고창고조회
            resultList = callService.search_Pda_Sm9010_whse_nm(paramMap);
            System.out.println("PDASM9010controller resultList:::" + resultList);
            model.addAttribute("resultList", resultList);
            break; 
            
        case "SEARCH02" :     //PDA 입고등록 박스바코드 조회 
            resultList = callService.selectTmfbarcode(paramMap);
            System.out.println("PDASM9010controller resultList:::" + resultList);
            model.addAttribute("resultList", resultList);   
            break; 
            
        case "SEARCH03" :     //PDA 제품출고 박스바코드 조회 
            resultList = callService.selectgetprddata(paramMap);
            System.out.println("PDASM9010controller resultList:::" + resultList);
            model.addAttribute("resultList", resultList);   
            break; 
            
        case "SEARCH04" :     //PDA 생산출고 박스바코드 조회 
            resultList = callService.selectgetboxdata(paramMap);
            System.out.println("PDASM9010controller resultList:::" + resultList);
            model.addAttribute("resultList", resultList);   
            break;
            
        case "SEARCH05" :     //PDA 입고반품 박스바코드 조회
            resultList = callService.selectgetpucboxdata(paramMap);
            System.out.println("PDASM9010controller resultList:::" + resultList);
            model.addAttribute("resultList", resultList);   
            break;
            
        case "SEARCH06" :     //PDA 창고 이동 등록 박스조회
            resultList = callService.selectgetmoveboxdata(paramMap);
            System.out.println("PDASM9010controller resultList:::" + resultList);
            model.addAttribute("resultList", resultList);   
            break;
        
        case "SEARCH07" :     //PDA 생산투입할당 박스조회
            resultList = callService.selectgetboxdataO5(paramMap);
            System.out.println("PDASM9010controller resultList:::" + resultList);
            model.addAttribute("resultList", resultList);   
            break;
            
        case "SEARCH08" :     //PDA 박스코드정리 박스조회
            resultList = callService.selectcollectboxdata(paramMap);
            System.out.println("PDASM9010controller resultList:::" + resultList);
            model.addAttribute("resultList", resultList);   
            break;
         
        }
        return "responseToAppChef";
    }   
}
