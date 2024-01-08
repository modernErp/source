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
import com.sfmes.cm.web.MyBuilderData_PDA;
import com.sfmes.sy.service.Sy1020Service;

/**
 * @Class Name : PDASy1020Controller.java
 * @Description : PDASy1020 Controller Class
 * @Modification Information
 * @
 * @  수정일             수정자        수정내용
 * @ ----------   ------   -------------------------------
 * @ 2020.06.08   여다혜       최초작성
 * @ 2021.03.19   장경석       PDA용 으로 변경
 *
 * @author (주)모든솔루션
 * @since 2020.06.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by (주)모든솔루션 All right reserved.
 */

@Controller
public class PDASy1020Controller {
    /** EgovSampleService */
    @Resource(name = "Sy1020Service")
    private Sy1020Service sy1020service;

    @Resource(name = "myBuilderData_PDA")
    protected MyBuilderData_PDA myBuilderData_PDA;
    
    @RequestMapping(value = "/PDAsy1020Controller.do")
    public String PDAsy1020Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String inMSV01 = null;  //.msv에서 input된 파라미터
        
        myBuilderData_PDA.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData_PDA.getParam("SVCID");
        inMSV01 = myBuilderData_PDA.getParam("INMSV01");
        
        
        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData_PDA.getParamFromMSVHashMap(inMSV01); 
//        System.out.println("controller paramMap:::" + paramMap);
//        System.out.println("controller strSVCID :::" + strSVCID);
//        System.out.println("controller inMSV01 :::" + inMSV01);
        List<?> resultList = null;
        
        switch(strSVCID){
            case "SEARCH04" : //사용자조회
                resultList = sy1020service.selectUsrList(paramMap);
                                
                model.addAttribute("resultList", resultList);
                break;

            case "SEARCH05" : //사업장조회(권한사업장) 200828 추가
                resultList = sy1020service.selectAuthBzplList(paramMap);
                model.addAttribute("resultList", resultList);
                break;                
                
        }
               
        return "responseToAppChef";
    }
}
