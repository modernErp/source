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
import com.sfmes.pd.service.Pd2050Service;
/**
 * @Class Name : Pd2050Controller.java
 * @Description : 출고의뢰일괄등록 등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.09.10  김종관  최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Pd2050Controller {
    /** EgovSampleService */

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;

    @Resource(name = "Pd2050Service")
    private Pd2050Service callService;

    /**
     * 사용자등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */

    @RequestMapping(value = "/pd2050Controller.do")
    public String pd2050Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String inMSV01 = null;
        String inMSV02 = null;
        String inMSV03 = null;

        myBuilderData.setParam(StrData);  //파라미터 복호화 

        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        inMSV02 = myBuilderData.getParam("INMSV02");
        inMSV03 = myBuilderData.getParam("INMSV03");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02"));
        List<Map<String, Object>> paramList1 = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV03"));

        List<?> resultList = null; //조회결과를 담을 List 
        List<?> resultList1 = null; //조회결과를 담을 List

        switch(strSVCID){
            case "INSERT" :
                System.out.println("paramMap   FOR INSERT :::" + paramMap);
                System.out.println("paramList  FOR INSERT :::" + paramList);
                System.out.println("paramList1 FOR INSERT :::" + paramList1);

                System.out.println("=========== Call Service Start ===========");
                callService.insertPd2050(paramMap, paramList, paramList1);
                System.out.println("=========== Call Service End   ===========");
                model.addAttribute("result", "OK");
                //model.addAttribute("returnValue01", paramMap.get("DNTT_SQNO").toString());
                break;

            case "SEARCH01" : //작업보고서 조회
                resultList = callService.selectPd2050DnttList_01(paramMap);
                model.addAttribute("resultList", resultList);
                break;

            case "SEARCH02" :
                resultList1 = callService.selectPd2050MtrlList_01(paramMap);
                model.addAttribute("resultList", resultList1);
                break;
        }
        return "responseToMybuilder";
    }
}