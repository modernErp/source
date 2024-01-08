package com.sfmes.sm.web;

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
import com.sfmes.sm.service.Sm5045Service;

/**
 * @Class Name  : Sm5045ServiceImpl.java
 * @Description : Sm5045Service Class
 * @Modification Information
 * @
 * @  수정일             수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.12.28   이동훈      변경  (이 주석이 없어서 추가)
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sm5045Controller {

    @Resource(name="myBuilderData")
    public MyBuilderData myBuilderData;
    
    @Resource(name="Sm5045Service")
    public Sm5045Service callService;

    @RequestMapping(value = "/sm5045Controller.do")
    public String sm5045Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        
        String strSVCID = null;
        String strINMSV01 = null;
        
        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        strSVCID = myBuilderData.getParam("SVCID");
        strINMSV01 = myBuilderData.getParam("INMSV01");
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(strINMSV01);
        List<?> resultList = null;
        
        switch( strSVCID ) {
        
        case "SEARCH01" :   //재고실사확정내역 조회
            resultList = callService.searchSm5045_01(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "DELETE01" :   //재고실사-전표삭제 및 실사삭제처리
            callService.deleteSm5045_01(paramMap);
            model.addAttribute("result", "OK");
            break;
        }
        
        return "responseToMybuilder";
    }
    
}
