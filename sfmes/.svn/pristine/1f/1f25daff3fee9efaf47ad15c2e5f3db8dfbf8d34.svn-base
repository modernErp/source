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

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.co.service.Co2030Service;

/**
* @Class Name : Co2030Controller.java
* @Description : Co2030Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.08.13   장경석     최초생성
*   2020.09.01   여다혜     서비스수정 (묶음내역대상 쿼리 추가, 등)
* @ 2020.12.28   이수빈     변경
*
* @author (주)모든솔루션
* @since 2020.08.13
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Co2030Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Co2030Service")
    private Co2030Service callService;

    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/co2030Controller.do")
    public String co2030Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;

        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02"));

        List<?> resultList = null;

        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
            case "INSERT" : //묶음번호등록
                callService.insertCo2030(paramMap, paramList);
                model.addAttribute("result", "OK");
                break;
                
            case "SEARCH01" : //묶음번호조회
                resultList = callService.selectCo2030List_01(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH02" : //묶음번호조회(기본)
                resultList = callService.selectCo2030_BUDL_NO_List(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH03" : //묶음번호조회(상세)
                resultList = callService.selectCo2030_BUDL_NO_Detail_List(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH_STD_PAT_C" : //표준부위코드(한우, 돈육)조회
                resultList = callService.selectCo2030_STD_PAT_C();
                model.addAttribute("resultList", resultList);
                break;
        }
        
        return "responseToMybuilder";
    }
}
