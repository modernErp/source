package com.sfmes.pd.web;

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
import com.sfmes.pd.service.Pd2110Service;

/**
* @Class Name : Pd2110Controller.java
* @Description : Pd2110Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.09.21   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.09.21
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

@Controller
public class Pd2110Controller {
    
    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "Pd2110Service")
    private Pd2110Service callService;
    
    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/pd2110Controller.do")
    public String pd2110Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        String inMSV01 = null;

        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        inMSV01 = myBuilderData.getParam("INMSV01");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<?> resultList = null;
        
	    resultList = callService.selectPd2110List(paramMap);
	    model.addAttribute("resultList", resultList);
        
        return "responseToMybuilder";
       
    }

}
