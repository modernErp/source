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
import com.sfmes.pd.service.Pd3040Service;

@Controller
public class Pd3040Controller {
    
    @Resource(name = "Pd3040Service")
    private Pd3040Service pd3040Service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/pd3040Controller.do")
    public String Pd3040Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        
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
            case "SEARCH01" : //원가계산기본조회
                resultList = pd3040Service.selectPd3040_01(paramMap); 
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH02" : //원가계산상세조회
                resultList = pd3040Service.selectPd3040_02(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH04" : //원가계산팝업조회
                resultList = pd3040Service.selectPd3040_04(paramMap);
                model.addAttribute("resultList", resultList);
                break;

            case "INSERT" : // 원가계산
            	pd3040Service.insertPd3040(paramMap);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("PCS_CLC_SQNO").toString());
                break;
                
            case "UPDATE01" : // 원가계산확정   
            	pd3040Service.updatePd3040_01(paramMap);
                model.addAttribute("result", "OK");
                break;

            case "UPDATE02" : // 원가계산취소  
            	pd3040Service.updatePd3040_02(paramMap);
                model.addAttribute("result", "OK");
                break;
        }
        
        return "responseToMybuilder";
    }

}
