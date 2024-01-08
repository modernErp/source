package com.sfmes.co.web;

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
import com.sfmes.co.service.Co1010Service;

/**
 * @Class Name : Co1010Controller.java
 * @Description : 사업장등록
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.22  손용찬      최초생성
 * @ 2020.12.28  이수빈      변경
 *
 * @author (주)모든솔루션
 * @since 2020.06.22
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Co1010Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Co1010Service")
    private Co1010Service callService;

    /**
     * 사업장등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/co1010Controller.do")
    public String co1010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01 = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap01 = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<Map<String, Object>> paramList01 = myBuilderData.getParamFromMSVList(inMSV01);
        List<?> resultList = null;            
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
            case "UPDATE01" :
                    // 사업장수정
                callService.updateCo1010_01(paramMap01);
                model.addAttribute("result", "OK");            
                break;
                
            case "SEARCH01" :
                // 사업장 상세조회
                resultList = callService.selectCo1010List(paramMap01);
                model.addAttribute("resultList", resultList);            
                break;

            case "INSERT01" :
                    // 인정감모율 등록
                callService.insertCo1010_01(paramList01);
                model.addAttribute("result", "OK");            
                break;

            case "SEARCH03" :
                // 인정감모율 조회
                resultList = callService.selectCo1010List03(paramMap01);
                model.addAttribute("resultList", resultList);            
                break;

            case "UPDATE02" :
                    // 인정감모율 수정
                callService.updateCo1010_02(paramList01);
                model.addAttribute("result", "OK");            
                break;
    
            case "SEARCH04" :
                // 사업장등록시 회사 주소 조회
                resultList = callService.selectCo1010List04(paramMap01);
                model.addAttribute("resultList", resultList);           
                break;
            case "SEARCH05" :
                // 사업장 공정구분 조회
                resultList = callService.selectCo1010List05(paramMap01);
                model.addAttribute("resultList", resultList);           
                break;
        }        
        return "responseToMybuilder";
    }
}
