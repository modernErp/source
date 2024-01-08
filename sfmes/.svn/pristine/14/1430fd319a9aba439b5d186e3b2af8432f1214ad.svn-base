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
import com.sfmes.pd.service.Pd3060Service;

@Controller
public class Pd3060Controller {
    
    @Resource(name = "Pd3060Service")
    private Pd3060Service pd3060Service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/pd3060Controller.do")
    public String pd3060Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        
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
	        case "INSERT01" : // 배부차액계산 
	        	pd3060Service.insertPd3060_01(paramMap);
	            model.addAttribute("result", "OK");
	            break;

	        case "INSERT02" : // 배부차액정리 
	        	pd3060Service.insertPd3060_02(paramMap);
	            model.addAttribute("result", "OK");
	            break;

        	case "UPDATE01" : // 배부차액취소 
	        	pd3060Service.updatePd3060_01(paramMap);
	            model.addAttribute("result", "OK");
	            break;
        
        }
        
        return "responseToMybuilder";
    }

}
