package com.sfmes.co.web;

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
import com.sfmes.co.service.Co1040Service;
/**
 * @Class Name : Co1040Controller.java 
 * @Description : 물품등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.06.25  여다혜  최초생성
 * @ 2020.12.28  이수빈  변경
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Co1040Controller {
    /** EgovSampleService */
    @Resource(name = "Co1040Service")
    private Co1040Service co1040service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;
    
    @RequestMapping(value = "/co1040Controller.do")
    public String co1040Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        
        myBuilderData.setParam(strData);  //파라미터 복호화
        
        String strSVCID = null; //서비스분기용 String
        strSVCID = myBuilderData.getParam("SVCID");
        
        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        
        switch(strSVCID){
            case "SEARCH_LCLC" : //표준부위코드(대분류) 조회
                List<?> resultList_LATC_LCLC = co1040service.select_LATC_LCLC(paramMap); 
                model.addAttribute("resultList", resultList_LATC_LCLC);
                break;
                
            case "SEARCH_MCLC" : //표준부위코드(중분류) 조회
                List<?> resultList_LATC_MCLC = co1040service.select_LATC_MCLC(paramMap); 
                model.addAttribute("resultList", resultList_LATC_MCLC);
                break;
                
            case "SEARCH_SCLC" : //표준부위코드(소분류) 조회
                List<?> resultList_LATC_SCLC = co1040service.select_LATC_SCLC(paramMap); 
                model.addAttribute("resultList", resultList_LATC_SCLC);
                break;   
                
            case "SEARCH_GDS_CLF" : //물품분류코드(전체) 조회
                List<?> resultList_GDS_CLF = co1040service.select_GDS_CLF(paramMap); 
                model.addAttribute("resultList", resultList_GDS_CLF);
                break;
                
            case "SEARCH_GDS" : //물품목록조회(팝업용) - 기준정보(공통)
                List<?> resultList_GDS = co1040service.select_GDS_List(paramMap); 
                //System.out.println(resultList_GDS + ":::: resultList 팝업");
                model.addAttribute("resultList", resultList_GDS);               
                break;

                
           case "SEARCH_GDS1" : //물품이력조회(팝업용) - 기준정보(공통)
                List<?> resultList_GDS1 = co1040service.select_GDS_List1(paramMap); 
                //System.out.println(resultList_GDS + ":::: resultList 팝업");
                model.addAttribute("resultList", resultList_GDS1);               
                break;

            case "SEARCH_GDS_FOR_WORK" : //물품목록조회(팝업용) - 업무화면용(매입,발주,생산,등)
                List<?> resultList_GDS_ForWork = co1040service.select_GDS_List_ForWork(paramMap); 
                model.addAttribute("resultList", resultList_GDS_ForWork);               
                break;    
                
            case "SEARCH_GDS_ONE" : //물품조회
                List<?> resultList_GDS_ONE = co1040service.select_GDS_ONE(paramMap); 
                model.addAttribute("resultList", resultList_GDS_ONE);               
                break;                
                
//            case "GET_AUTO_GDS_C" : //물품코드자동생성
//                System.out.println(paramMap + ":::: 물품코드 자동생성 param");
//                List<?> result_GDS_C = co1040service.select_Auto_GDS_C(paramMap);
//                System.out.println(result_GDS_C + "::: 생성된 번호");
//                model.addAttribute("resultList", result_GDS_C);
//                break;
                
            case "INSERT_GDS" : //물품등록
                String gds_no = co1040service.insert_Gds(paramMap);
                
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", gds_no);
                break;
                
            case "UPDATE_GDS" :
                System.out.println(paramMap + "====> UPDATE //// 물품1건 수정 parameter");
                co1040service.update_Gds(paramMap);
                model.addAttribute("result", "OK");
                break;
        }
        
        return "responseToMybuilder";
    }
}
