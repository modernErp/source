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
import com.sfmes.sy.service.Sy4010Service;

/**
 * @Class Name  : Sy4010erviceImpl.java
 * @Description : Sy4010ServiceImpl Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  ---------   -------------------------------
 * @ 2020.06.19   김수민      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sy4010Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Sy4010Service")
    private Sy4010Service callService;
    
    /**
     * 시스템사용내역을 조회한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/sy4010Controller.do")
    public String sy4010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
    {
        String strSVCID = null;
        String inMSV01  = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01  = myBuilderData.getParam("INMSV01");        
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<?> resultList = null;
        
        switch(strSVCID) {
        case "INSERT" :
            //시스템사용이력등록
            callService.insertSy4010(paramMap);
            model.addAttribute("result", "OK");
            break;
            
        case "SEARCH01" :
            // 시스템사용이력조회
            resultList = callService.selectSy4010UgList(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "SEARCH02" :
            // 시스템사용이력상세조회
            resultList = callService.selectSy4010UgDetail(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        }
        
        return "responseToMybuilder";
    }

}
