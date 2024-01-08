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
import com.sfmes.sm.service.Sm5010Service;

/**
 * @Class Name  : Sm5010ServiceImpl.java
 * @Description : Sm5010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.16   정성환      최초생성
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
public class SM5010Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Sm5010Service")
    private Sm5010Service callService;
    
    /**
     * 공통코드등록을 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/sm5010Controller.do")
    public String sm5010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
        case "SAVE01" :     //재고실사준비등록 저장
            result = callService.saveSm5010(paramMap, paramList);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("SSVY_RSV_DT").toString());
            break;
            
        case "SEARCH01" :   //재고실사준비등록  조회
            resultList = callService.searchSm5010_01(paramMap);
            model.addAttribute("resultList", resultList);           
            break;     
        
        case "SEARCH02" :   //재고실사준비내역팝업찾기 
            resultList = callService.searchSm5010P01(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
            
        case "SEARCH03" :   //재고실사준비내역팝업찾기 
            resultList = callService.searchSm5010P02(paramMap);
            model.addAttribute("resultList", resultList);           
            break;    
            
        case "DELETE" :     //재고실사준비등록 삭제
            result = callService.deleteSm5010(paramMap);
            model.addAttribute("result", "OK");
            break;
    }
        return "responseToMybuilder";
    }   
}
