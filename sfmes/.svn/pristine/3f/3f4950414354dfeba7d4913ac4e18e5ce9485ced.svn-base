package com.sfmes.ca.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.ca.service.Ca4015Service;
import com.sfmes.cm.web.MyBuilderData;
@Controller
public class Ca4015Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;

    @Resource(name = "Ca4015Service")
    protected Ca4015Service callService;
    
    @RequestMapping(value = "/Ca4015Controller.do")
    public String Ca4015Controller(HttpServletRequest strData, ModelMap model) throws Exception
    {
        String strSVCID = null;
        String strINMSV01 = null;
        String strINMSV02 = null;
     
        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        strINMSV01 = myBuilderData.getParam("INMSV01");
        strINMSV02 = myBuilderData.getParam("INMSV02");
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap<String, Object> paramMap = myBuilderData.getParamFromMSVHashMap(strINMSV01);
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(strINMSV02);
        List<?> resultList = null;
        
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID) {
        
        case "SEARCH01" :
            // 외상매입금지급내역조회
            resultList = callService.selectCa4015List(paramMap);
            model.addAttribute("resultList", resultList);
            break;
            
        case "UPDATE" :
            // 외상매입금지급내역 정정취소
            callService.updateCa4015List(paramMap, paramList);
            model.addAttribute("result", "OK");
            break;
            
        case "DELETE" :
            // 외상매입금지급내역 삭제
            callService.deleteCa4015List(paramMap, paramList);
            model.addAttribute("result", "OK");
            break;
        }
        
        return "responseToMybuilder";
    }

}
