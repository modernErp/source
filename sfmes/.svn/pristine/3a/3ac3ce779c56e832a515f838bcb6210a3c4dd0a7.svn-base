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
import com.sfmes.sm.service.Sm4010Service;

/**
 * @Class Name  : Sm4010ServiceImpl.java
 * @Description : Sm4010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.19   정성환      최초생성
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
public class SM4010Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Sm4010Service")
    private Sm4010Service callService;
    
    /**
     * 공통코드등록을 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/sm4010Controller.do")
    public String sm4010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01  = null;
        String inMSV02  = null;
        String inMSV03  = null;
        String result   = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01  = myBuilderData.getParam("INMSV01");
        inMSV02  = myBuilderData.getParam("INMSV02");
        inMSV03  = myBuilderData.getParam("INMSV03");
                  
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<Map<String, Object>> paramList1 = myBuilderData.getParamFromMSVList(inMSV02); // 품간대체처리 상세 출고
        List<Map<String, Object>> paramList2 = myBuilderData.getParamFromMSVList(inMSV03); // 품간대체처리 상세 입고

        List<?> resultList = null;
        
        switch(strSVCID) {
        case "SAVE01" :     //품간대체처리 저장
            result = callService.saveSm4010(paramMap, paramList1, paramList2);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("TFR_PRC_DT").toString());
            model.addAttribute("returnValue02", paramMap.get("TFR_PRC_SQNO").toString());
            break;
        
        case "UPDATE01" :     //품간대체처리 수정
            result = callService.updateSm4010(paramMap, paramList1, paramList2);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("TFR_PRC_DT").toString());
            model.addAttribute("returnValue02", paramMap.get("TFR_PRC_SQNO").toString());
            break;
       
        case "DELETE" :     //품간대체처리 전표삭제 
            result = callService.deleteSm4010(paramMap, paramList1, paramList2);
            model.addAttribute("result", "OK");
            model.addAttribute("resultList", resultList);           
            break;
          
        case "SEARCH01" :   //품간대체처리내역 기본 팝업
            resultList = callService.searchSm4010P01_M(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
            
        case "SEARCH03" :   //품간대체처리내역 상세 팝업
            resultList = callService.searchSm4010P01_D(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
            
        case "SEARCH02" :   //품간대체처리 - 창고찾기팝업창
            resultList = callService.searchSm4010P03(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
    }
        return "responseToMybuilder";
    }   
}
