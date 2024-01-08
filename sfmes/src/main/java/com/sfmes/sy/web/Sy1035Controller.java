package com.sfmes.sy.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.sy.service.Sy1035Service;

/**
 * @Class Name : Sy1035Controller.java
 * @Description : 사용자내역 컨트롤러 로직
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.01  김수민      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.01
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sy1035Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Sy1035Service")
    private Sy1035Service callService;

    /**
     * 사용자내역 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/sy1035Controller.do")
    public String sy1035Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01 = null;

        myBuilderData.setParam(strData);
        
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<?> resultList = null;
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
        case "SEARCH01" : 
            //사용자내역&사용자찾기팝업 조회
            resultList = callService.selectSy1035List(paramMap);
            model.addAttribute("resultList", resultList);
            break;
        }
        
        return "responseToMybuilder";
    }

}
