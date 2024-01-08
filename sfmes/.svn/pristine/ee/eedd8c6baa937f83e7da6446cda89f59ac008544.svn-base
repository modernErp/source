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
import com.sfmes.pd.service.Pd2020Service;
/**
 * @Class Name : Pd2020Controller.java
 * @Description : 작업지시일괄등록 등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.08.10  김종관  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.08.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Pd2020Controller {
    /** EgovSampleService */

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;

    @Resource(name = "Pd2020Service")
    private Pd2020Service callService;

    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */

    @RequestMapping(value = "/pd2020Controller.do")
    public String pd2020Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null;
        String inMSV01 = null;
        String inMSV02 = null;

        myBuilderData.setParam(StrData); 

        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        inMSV02 = myBuilderData.getParam("INMSV02");

        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(inMSV02);
        List<?> resultList = null; //조회결과를 담을 List 
        List<?> resultList1 = null; //조회결과를 담을 List 

        System.out.println("strSVCID :::" + strSVCID);
        // 서비스 구분에 따라 분기 처리한다.
        switch(strSVCID){
        case "INSERT" :
            System.out.println("paramMap   FOR INSERT :::" + paramMap);
            System.out.println("paramList  FOR INSERT :::" + paramList);

            System.out.println("=========== Call Service Start ===========");
            callService.insertPd2020(paramMap, paramList);
            System.out.println("=========== Call Service End   ===========");
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("DNTT_SQNO").toString());
            break;

        case "SEARCH01" :
            resultList = callService.selectWkDnttList(paramMap);
            model.addAttribute("resultList", resultList);
            break;

        case "SEARCH02" :
            resultList1 = callService.selectWkDnttMtrlList(paramMap);
            model.addAttribute("resultList", resultList1);
            break;
        }
        return "responseToMybuilder";
    }
}