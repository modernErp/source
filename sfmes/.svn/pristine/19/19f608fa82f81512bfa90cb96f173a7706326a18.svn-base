package com.sfmes.pd.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.pd.service.Pd3070Service;

/**
* @Class Name : Pd3070Controller.java
* @Description : Pd3070Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.12.14   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.12.14
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Pd3070Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Pd3070Service")
    private Pd3070Service callService;
    
    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/pd3070Controller.do")
    public String pd3070Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        String strSVCID = null; // 서비스분기용 param
        String inMSV01 = null;  // 서비스로직 처리 param

        myBuilderData.setParam(strData); //파라미터 복호화
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID"); 
        inMSV01 = myBuilderData.getParam("INMSV01");
        
        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<?> resultList = null;
        
        // 서비스 구분에 따라 분기 처리한다. - 추후 추가를 고려해 switch문 사용
        switch(strSVCID) {
            case "SEARCH01" :  // 배부차액정리 기본정보 팝업리스트조회
                resultList = callService.selectPd3070_01(paramMap);
                model.addAttribute("resultList", resultList);
                break;
            case "SEARCH02" :  // 배부차액정리 기본정보조회
                resultList = callService.selectPd3070_02(paramMap);
                model.addAttribute("resultList", resultList);
                break;
            case "SEARCH03" :  // 배부차액정리 상세정보조회
                resultList = callService.selectPd3070_03(paramMap);
                model.addAttribute("resultList", resultList);
                break;
        }
        
        return "responseToMybuilder";
       
    }

}
