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
import com.sfmes.sm.service.Sm1020Service;
import com.sfmes.sm.service.Sm1030Service;
import com.sfmes.sm.service.Sm3010Service;
import com.sfmes.se.service.Se6020Service;

/**
 * @Class Name  : Sm3010ServiceImpl.java
 * @Description : Sm3010Service Class
 * @Modification Information
 * @
 * @  수정일           수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.08  정성환             최초생성
 * @ 2020.12.28  이동훈             변경
 * @ 2021.03.19  장경석             PDA용 으로 변경
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class PDASm3010Controller {
    
    @Resource(name = "myBuilderData_PDA")
    protected MyBuilderData_PDA myBuilderData_PDA;
    
    @Resource(name = "Sm3010Service")
    private Sm3010Service Sm3010Service;

    @Resource(name = "Sm1030Service")
    private Sm1030Service Sm1030Service;
    
    @Resource(name = "Sm1020Service")
    private Sm1020Service Sm1020Service;
    
    /**
     * 공통코드등록을 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/PDAsm3010Controller.do")
    public String PDAsm3010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
        
        switch(strSVCID) {
        case "SAVE01" :     // 창고간이동내역저장
            result = Sm3010Service.savePDA_Sm3010(paramMap, paramList);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("MVE_DT").toString());
            model.addAttribute("returnValue02", paramMap.get("MVE_SQNO").toString());
            break;
            
        case "SAVE02" :     //입고내역저장
            result = Sm1030Service.saveSm1030(paramMap, paramList);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("STDV_DT").toString());
            model.addAttribute("returnValue02", paramMap.get("STDV_SQNO").toString());
            break;

        case "SEARCH01" :   // 창고별 물품내역조회 
            resultList = Sm3010Service.searchPDA_Sm3010_01(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
        
        case "SEARCH02" :   // 물품별 창고물품내역조회 
            resultList = Sm3010Service.searchPDA_Sm3010_02(paramMap);
            model.addAttribute("resultList", resultList);           
            break;

        case "SEARCH03" :   // 출고의뢰내역기본조회 
            resultList = Sm1020Service.searchSm1020PDA(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
        case "SEARCH04" :   // 출고의뢰내역상세조회
            resultList = Sm1030Service.select_Sm1030_05(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
        }
        return "responseToAppChef";
    }   
}
