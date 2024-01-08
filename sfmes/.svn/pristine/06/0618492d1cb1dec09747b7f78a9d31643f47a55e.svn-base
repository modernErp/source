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
import com.sfmes.sy.service.Sy1020Service;

/**
 * @Class Name : Sy1020Controller.java
 * @Description : Sy1020 Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------   ------   -------------------------------
 * @ 2020.06.08   여다혜   최초작성
 *
 * @author (주)모든솔루션
 * @since 2020.06.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by (주)모든솔루션 All right reserved.
 */

@Controller
public class Sy1020Controller {
    /** EgovSampleService */
    @Resource(name = "Sy1020Service")
    private Sy1020Service sy1020service;

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/sy1020Controller.do")
    public String sy1020Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String inMSV01 = null;  //.msv에서 input된 파라미터
        
        myBuilderData.setParam(StrData);  //파라미터 복호화 
        
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        
        
        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01); 
        List<?> resultList = null;
        
        switch(strSVCID){
            case "SEARCH01" : //전체메뉴 조회
                resultList = sy1020service.selectAllMenu(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH02" : //사용중메뉴 조회
                resultList = sy1020service.selectUsingMenu(paramMap);
                model.addAttribute("resultList", resultList);
                break;
             
            case "SEARCH03" : //사업장조회(전체)
                resultList = sy1020service.selectBzplList(paramMap);
                model.addAttribute("resultList", resultList);
                break;
             
            case "SEARCH04" : //사용자조회
                resultList = sy1020service.selectUsrList(paramMap);
                model.addAttribute("resultList", resultList);
                break;                
                
            case "SEARCH05" : //사업장조회(권한사업장) 200828 추가
                resultList = sy1020service.selectAuthBzplList(paramMap);
                model.addAttribute("resultList", resultList);
                break;   
                
            case "SEARCH06" : //마이메뉴조회 추가_20211216 여다혜 
                resultList = sy1020service.selectSy1020_MyMenu(paramMap);
                model.addAttribute("resultList", resultList);
                break;                
                
            case "SEARCH_PGM_AUTH" : //프로그램 권한 조회  20.10.16 추가
                resultList = sy1020service.selectPgmAuth(paramMap);
                model.addAttribute("resultList", resultList);
                break;

            case "INSERT" : //마이메뉴 등록
                sy1020service.insertSy1020_MyMenu(paramMap);
                model.addAttribute("result", "OK");
                break;
            
            case "DELETE" : //마이메뉴 삭제
                sy1020service.deleteSy1020_MyMenu(paramMap);
                model.addAttribute("result", "OK");
                break;
        }
        
        
        return "responseToMybuilder";
    }
}
