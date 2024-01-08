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
import com.sfmes.pd.service.Pd3030Service;

@Controller
public class Pd3030Controller {
    
    @Resource(name = "Pd3030Service")
    private Pd3030Service callService;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/pd3030Controller.do")
    public String pd3030Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        
        //서비스분기용 String
        String strSVCID = null; 
        String inMSV01 = null;
        String inMSV02 = null;
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        inMSV02 = myBuilderData.getParam("INMSV02");
        
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(inMSV02);
        List<?> resultList = null; //조회결과를 담을 List 
        
        switch(strSVCID){
            case "SEARCH01" : //노무비/제조경비 조회
                resultList = callService.selectPd3030_01(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "INSERT" : //노무비/제조경비 INSERT 
                callService.insertPd3030(paramMap,paramList);
                model.addAttribute("result", "OK");
                break;
                
            case "UPDATE" : //노무비/제조경비 UPDATE   
                callService.updatePd3030(paramMap,paramList);
                model.addAttribute("result", "OK");
                break;
        }
        
        return "responseToMybuilder";
    }

}
