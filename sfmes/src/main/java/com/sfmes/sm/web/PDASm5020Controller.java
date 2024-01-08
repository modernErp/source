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
import com.sfmes.sm.service.Sm5020Service;

/**
 * @Class Name  : Sm5020ServiceImpl.java
 * @Description : Sm5020Service Class
 * @Modification Information
 * @
 * @  수정일             수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.26   정성환      최초작성
 * @ 2020.12.28   이동훈      변경
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class PDASm5020Controller {
    
    @Resource(name = "myBuilderData_PDA")
    protected MyBuilderData_PDA myBuilderData_PDA;
    
    @Resource(name = "Sm5020Service")
    private Sm5020Service callService;
    
    /**
     * 공통코드등록을 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/PDAsm5020Controller.do")
    public String PDAsm4010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01  = null;
        String inMSV02  = null;
        String result   = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData_PDA.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData_PDA.getParam("SVCID");
        inMSV01  = myBuilderData_PDA.getParam("INMSV01");
        inMSV02  = myBuilderData_PDA.getParam("INMSV02");
                  
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData_PDA.getParamFromMSVHashMap(inMSV01);
        List<Map<String, Object>> paramList = myBuilderData_PDA.getParamFromMSVList(inMSV02);     
 
        List<?> resultList = null;
        
        switch(strSVCID) 
        {
        case "SEARCH01" :   //재고실사등록 조회 
            resultList = callService.searchSm5020_01(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
            
        case "SEARCH02" :   //재고실사등록 조회 
            resultList = callService.searchSm5020_02(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
            
        case "SAVE01" :     //재고실사등록 저장
            result = callService.saveSm5020(paramMap, paramList);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("SSVY_RSV_DT").toString());
            model.addAttribute("returnValue02", paramMap.get("SSVY_RSV_SQNO").toString());
            break;
        
        case "SEARCHPDA" :   //PDA용 재고준비등록번호 조회 
            resultList = callService.searchPDASm5020(paramMap);
            model.addAttribute("resultList", resultList);  
            break; 
        }
        return "responseToAppChef";
    }   
}
