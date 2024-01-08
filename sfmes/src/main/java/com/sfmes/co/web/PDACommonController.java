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
import com.sfmes.co.service.CommonService;

/**
 * @Class Name  : CommonController.java
 * @Description : CommonController
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.06.24   여다혜    최초생성
 * @ 2020.12.28   이수빈    변경
 * @ 2021.03.19   장경석       PDA용 으로 변경
 *
 * @author (주)모든솔루션
 * @since 2020.07.30
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class PDACommonController {
    
    @Resource(name = "myBuilderData_PDA")
    protected MyBuilderData_PDA myBuilderData_PDA;
    
    @Resource(name = "CommonService")
    private CommonService callService;

    @RequestMapping(value = "/PDACommonController.do")
    public String PDACommonController(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null; //서비스 분기용 ID
        String returnMsg = null; //사용자메세지
        
        // 파라미터 복호화를 수행한다.
        myBuilderData_PDA.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData_PDA.getParam("SVCID");  
        
        System.out.println(strSVCID + "=====> 분기id");
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        //LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        
    
        switch(strSVCID) {
            case "CVS_QT" :
                //도량형 환산표 자료 조회
                List<?> resultList = callService.selectUnitConversionTable();
                model.addAttribute("resultList", resultList);          
                break;
        }  
        
        return "responseToAppChef";
    }
}
