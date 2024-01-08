package com.sfmes.se.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.se.service.Se2110Service;

/**
 * @Class Name  : Se2110Service.java
 * @Description : 수주현황 조회 Se2110Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.09.11   김지혜     최초생성
 * @ 2020.12.28   곽환용     수정
 *
 * @author (주)모든솔루션
 * @since 2020.09.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se2110Controller {
    
    @Resource(name = "Se2110Service")
    private Se2110Service se2110Service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/se2110Controller.do")
    public String se2110Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
                  
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        
        switch(strSVCID){
            case "SEARCH01" : //수주현황(거래처)거래처별내역
                List<?> resultList = se2110Service.selectSe2110_trpl_01(paramMap); 
                model.addAttribute("resultList", resultList);
                break; 
                
            case "SEARCH02" : //수주현황(거래처)전표별내역
                List<?> resultList2 = se2110Service.selectSe2110_trpl_02(paramMap); 
                model.addAttribute("resultList", resultList2);
                break; 
                
            case "SEARCH03" : //수주현황(거래처)물품별내역
                List<?> resultList3 = se2110Service.selectSe2110_trpl_03(paramMap); 
                model.addAttribute("resultList", resultList3);
                break; 
                
            case "SEARCH04" : //수주현황(물품)물품별내역
                List<?> resultList4 = se2110Service.selectSe2110_gds_01(paramMap); 
                model.addAttribute("resultList", resultList4);
                break; 
                
            case "SEARCH05" : //수주현황(물품)거래처별내역
                List<?> resultList5 = se2110Service.selectSe2110_gds_02(paramMap); 
                model.addAttribute("resultList", resultList5);
                break; 
                
            case "SEARCH06" : //수주현황(물품) 전표별내역
                List<?> resultList6 = se2110Service.selectSe2110_gds_03(paramMap); 
                model.addAttribute("resultList", resultList6);
                break; 
                
        }  
        return "responseToMybuilder";
    }
}
