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
import com.sfmes.co.service.Co2015Service;

/**
* @Class Name : Co2015Controller.java
* @Description : Co2015Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.06.30   김수민     최초생성
* @ 2020.12.28   이수빈     변경
*
* @author (주)모든솔루션
* @since 2020.06.30
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Co2015Controller {
    

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Co2015Service")
    private Co2015Service callService;

    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/co2015Controller.do")
    public String co2015Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01 = null;

        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<?> resultList = null;
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {        
        case "SEARCH01" :
            //거래처내역조회
            resultList = callService.selectCo2015List_01(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH02" :
            //거래처정보변경이력조회
            resultList = callService.selectCo2015List_02(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH03" :
            //거래처조회팝업
            resultList = callService.selectCo2015List_03(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH04" :
            //거래처계약정보변경이력조회
            resultList = callService.selectCo2015List_04(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        }
        
        return "responseToMybuilder";
    }

}
