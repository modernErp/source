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
import com.sfmes.sm.service.Sm1010Service;

/**
 * @Class Name  : PDASm1010ServiceImpl.java
 * @Description : PDASm1010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.06   김지혜      최초생성
 * @ 2020.09.21   정성환      변경
 * @ 2020.12.28   이동훈      변경
 * @ 2021.03.17   장경석       PDA 용도 변경
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class PDASm1010Controller {
    
    @Resource(name = "myBuilderData_PDA")
    protected MyBuilderData_PDA myBuilderData_PDA;
    
    @Resource(name = "Sm1010Service")
    private Sm1010Service callService;
    
    /**
     * 공통코드등록을 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/PDAsm1010Controller.do")
    public String PDAsm1010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01  = null;
        String inMSV02  = null;
        String inMSV03  = null;
        String result   = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData_PDA.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData_PDA.getParam("SVCID");
        inMSV01  = myBuilderData_PDA.getParam("INMSV01");
        inMSV02  = myBuilderData_PDA.getParam("INMSV02");
        inMSV03  = myBuilderData_PDA.getParam("INMSV03");
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData_PDA.getParamFromMSVHashMap(inMSV01);
        List<Map<String, Object>> paramList = myBuilderData_PDA.getParamFromMSVList(inMSV02);       // 수정된 데이터
        List<Map<String, Object>> paramList3 = myBuilderData_PDA.getParamFromMSVList(inMSV03);       // 수정된 데이터
 
//        System.out.println("controller paramList3 :::" + paramList3);

        List<?> resultList = null;
        
        switch(strSVCID) {
        case "SAVE01" :     //입고내역저장
            result = callService.saveSm1010_PDA(paramMap, paramList);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("STDV_DT").toString());
            model.addAttribute("returnValue02", paramMap.get("STDV_SQNO").toString());
            break;
            
        case "UPDATE01" :   //입고내역수정
            result = callService.updateSm1010(paramMap, paramList);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("STDV_DT").toString());
            model.addAttribute("returnValue02", paramMap.get("STDV_SQNO").toString());
            break;
            
        case "DELETE" :     //입고내역삭제 
            callService.deleteSm1010(paramMap, paramList);
            model.addAttribute("result", "OK");
            break;               
            
        case "SEARCH01" :   //입고기본내역조회 
            resultList = callService.searchSm1010_01(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
        
        case "SEARCH02" :   //입고상세내역조회 
            resultList = callService.searchSm1010_02(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
            
        case "SEARCH03" :   //입고내역찾기팝업 
            resultList = callService.select_Sm1010P01(paramMap);
            model.addAttribute("resultList", resultList);    
            break;
            
        case "SEARCH04" :   //입고내역조회 
            resultList = callService.select_Sm1015(paramMap);
            model.addAttribute("resultList", resultList);    
            break;
    
        case "SEARCH05" :   //발주참조내역조회
            resultList = callService.select_Sm1010_BY(paramMap);
            model.addAttribute("resultList", resultList);    
            break;
            
        case "SEARCH06" :   //발주참조내역조회
            resultList = callService.select_Sm1015_M(paramMap);
            model.addAttribute("resultList", resultList);    
            break;  
            
        case "SEARCHTOTE" :   //TOTE_CODE 정보조회
            resultList = callService.select_PdTOTE_M(paramMap);
            model.addAttribute("resultList", resultList);    
            break;
            
        case "SEARCH_PDAGDS" :   // PDA 입고예정물품정보조회
            resultList = callService.select_PDA_GDS_C(paramMap);
            model.addAttribute("resultList", resultList);    
            break;
            
        case "SEARCH08" :   // 물품기본 발주참조내역조회
            resultList = callService.select_Sm1010_ODRGDS_M(paramMap);
            model.addAttribute("resultList", resultList);    
            break;  

        case "SEARCHPDADT" :   // PDA 입고예정일조회
            resultList = callService.select_Pda_ODRDT(paramMap);
            model.addAttribute("resultList", resultList);    
            break;
            
        case "SEARCHPDATRPL" :   // PDA 입고예정일의 거래처조회
            resultList = callService.select_Pda_TRPL(paramMap);
            model.addAttribute("resultList", resultList);    
            break;    

        case "SEARCH07" :   //일자-거래처기본 발주참조내역조회
            resultList = callService.select_Sm1010_ODR_M(paramMap);
            model.addAttribute("resultList", resultList);    
            break;             
        }

//        System.out.println("PDA resultList :: " + resultList);

        return "responseToAppChef";
    }   
}
