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
import com.sfmes.sm.service.Sm1030Service;

/**
 * @Class Name  : Sm1030ServiceImpl.java
 * @Description : Sm1030Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.09.14   정성환      최초생성
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
public class Sm1030Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Sm1030Service")
    private Sm1030Service callService;
    
    /**
     * 공통코드등록을 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/sm1030Controller.do")
    public String sm1030Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
        case "SAVE01" :     //입고내역저장
            result = callService.saveSm1030(paramMap, paramList);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("STDV_DT").toString());
            model.addAttribute("returnValue02", paramMap.get("STDV_SQNO").toString());
            break;
       
        case "DELETE" :   //출고등록전표삭제
            result = callService.deleteSm1030(paramMap, paramList);
            model.addAttribute("result", "OK");
            model.addAttribute("resultList", resultList);           
            break;
            
        case "SEARCH01" :   //출고기본내역조회 
            resultList = callService.searchSm1030_01(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
        
        case "SEARCH02" :   //출고상세내역조회 
            resultList = callService.searchSm1030_02(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
        case "SEARCH03" :   //출고상세내역조회 
            resultList = callService.search_Sm1030P01(paramMap);
            model.addAttribute("resultList", resultList);           
            break;    
        case "SEARCH04" :   //출고내역조회 
            resultList = callService.select_Sm1035_D(paramMap);
            model.addAttribute("resultList", resultList);    
            break;
        case "SEARCH05" :   //출고의뢰조회 
            resultList = callService.select_Sm1030_05(paramMap);
            model.addAttribute("resultList", resultList);    
            break;
        case "SEARCH06" :   //출고의뢰조회 
            resultList = callService.select_Sm1035_M(paramMap);
            model.addAttribute("resultList", resultList);    
            break;
        case "SEARCHTOTE" :   //TOTE_CODE 정보조회
            resultList = callService.select_Sm1035_TOTE_M(paramMap);
            model.addAttribute("resultList", resultList);    
            break;    
    }
        return "responseToMybuilder";
    }   
}
