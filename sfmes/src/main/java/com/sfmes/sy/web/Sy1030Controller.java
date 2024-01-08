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
import com.sfmes.sy.service.Sy1030Service;

/**
* @Class Name : Sy1030Controller.java
* @Description : Sy1030Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.05.28   김수민     최초생성
*
* @author (주)모든솔루션
* @since 2020.05.28
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Sy1030Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Sy1030Service")
    private Sy1030Service callService;

    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/sy1030Controller.do")
    public String sy1030Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
        case "INSERT" :
            //사용자신규등록
            callService.insertSy1030(paramMap);
            model.addAttribute("result", "OK");
            break;
            
        case "UPDATE" :
            //사용자수정
            callService.updateSy1030(paramMap);
            model.addAttribute("result", "OK");
            break;
            
        case "UPDATE01" :
            //비밀번호초기화
            callService.updateSy1030_01(paramMap);
            model.addAttribute("result", "OK");
            break;
            
        case "UPDATE02" :
            //비밀번호변경
            callService.updateSy1030_02(paramMap);
            model.addAttribute("result", "OK");
            break;
            
        case "SEARCH01" :
            //사용자조회
            resultList = callService.selectSy1030List(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        }
        
        return "responseToMybuilder";
    }

}
