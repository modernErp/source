package com.sfmes.co.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.co.service.Co2010Service;

/**
* @Class Name : Co2010Controller.java
* @Description : Co2010Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.06.24   김수민     최초생성
* @ 2020.12.28   이수빈     변경
*
* @author (주)모든솔루션
* @since 2020.06.24
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Co2010Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Co2010Service")
    private Co2010Service callService;

    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/co2010Controller.do")
    public String co2010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01 = null;
        String inMSV02 = null;
        String inMSV03 = null;

        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        inMSV02 = myBuilderData.getParam("INMSV02");
        inMSV03 = myBuilderData.getParam("INMSV03");
        
        LinkedHashMap paramMap01 = myBuilderData.getParamFromMSVHashMap(inMSV01);
        LinkedHashMap paramMap02 = myBuilderData.getParamFromMSVHashMap(inMSV02);
        LinkedHashMap paramMap03 = myBuilderData.getParamFromMSVHashMap(inMSV03);
        List<?> resultList = null;
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
        case "INSERT" :
            //거래처등록
            String TRPL_C_no = callService.insertCo2010(paramMap01, paramMap02, paramMap03);
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", TRPL_C_no); // 거래처코드 자동채번  20220311 구민희 추가
            break;
            
        case "UPDATE" :
            //거래처수정
            callService.updateCo2010(paramMap01, paramMap02, paramMap03);
            model.addAttribute("result", "OK");
            break;
            
        case "SEARCH01" :
            //거래처정보조회
            resultList = callService.selectCo2010List_01(paramMap01);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH02" :
            //거래처계약정보조회(매입)
            resultList = callService.selectCo2010List_02(paramMap01);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH03" :
            //거래처계약정보조회(매출)
            resultList = callService.selectCo2010List_03(paramMap01);
            model.addAttribute("resultList", resultList);
            break;
        }
        
        return "responseToMybuilder";
    }
}
