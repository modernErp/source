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
import com.sfmes.pd.service.Pd1030Service;
/**
 * @Class Name : Pd1030Controller.java
 * @Description : BOM공정기로서등록 등록 Controller
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
public class Pd1030Controller {
    /** EgovSampleService */

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;

    @Resource(name = "Pd1030Service")
    private Pd1030Service callService;

    /**
     * BOM공정기로서등록 Request를 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */

    @RequestMapping(value = "/pd1030Controller.do")
    public String pd1030Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null;
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
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(inMSV02);
        List<Map<String, Object>> paramList1 = myBuilderData.getParamFromMSVList(inMSV03);

        List<?> resultList = null; //조회결과를 담을 List
        List<?> resultList1 = null; //조회결과를 담을 List
        List<?> resultList2 = null; //조회결과를 담을 List
        List<?> resultList3 = null; //조회결과를 담을 List
        
        switch(strSVCID){
            case "INSERT" : //공정기록서 등록
                callService.insertPd1030(paramMap, paramList, paramList1);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("PRW_C").toString());
                break;
                
            case "UPDATE" : //공정기록서 수정
                callService.updatePd1030(paramMap, paramList, paramList1);
                model.addAttribute("result", "OK");
                break;

            case "SEARCH01" : //공정기록서POPUP 조회
                resultList = callService.selectPrwPopupList(paramMap);
                model.addAttribute("resultList", resultList);
                break;

            case "SEARCH02" : //공정기록서기본 조회
                resultList1 = callService.selectPrwRecList(paramMap);
                model.addAttribute("resultList", resultList1);
                break;

            case "SEARCH03" : //공정기록서_생산내역 조회
                resultList2 = callService.selectPrwPdGdsList(paramMap);
                model.addAttribute("resultList", resultList2);
                break;

            case "SEARCH04" : //공정기록서_투입내역 조회
                resultList3 = callService.selectPrwPtinList(paramMap);
                model.addAttribute("resultList", resultList3);
                break;

            case "SEARCH05" : //공정기록서POPUP 조회
                if ("1".equals(paramMap.get("GDS_GBN"))) {
                    resultList = callService.selectPrwPopupList_PdGds(paramMap);
                } else {
                    resultList = callService.selectPrwPopupList_PtinGds(paramMap);
                }
                model.addAttribute("resultList", resultList);
                break;
        }
        return "responseToMybuilder";
    }
}