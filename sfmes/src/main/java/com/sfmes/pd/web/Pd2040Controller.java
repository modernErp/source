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
import com.sfmes.pd.service.Pd2040Service;
/**
 * @Class Name : Pd2040Controller.java
 * @Description : 작업지시일괄등록 등록 Controller
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ----------  ------  -------------------------------
 * @ 2040.08.10  김종관  최초생성
 *
 * @author (주)모든솔루션
 * @since 2040.08.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Pd2040Controller {
    /** EgovSampleService */

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;

    @Resource(name = "Pd2040Service")
    private Pd2040Service callService;

    /**
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */

    @RequestMapping(value = "/pd2040Controller.do")
    public String pd2040Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String inMSV01 = null;
        String inMSV02 = null;

        myBuilderData.setParam(StrData);  //파라미터 복호화 

        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        inMSV02 = myBuilderData.getParam("INMSV02");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
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
                callService.insertPd2040(paramMap, paramList);
                System.out.println("=========== Call Service End   ===========");
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("RPT_SQNO").toString());
                break;

            case "INSERT11" :    // 투입내역 바코드라벨 적용 저장  
                System.out.println("paramMap   FOR INSERT :::" + paramMap);
                System.out.println("paramList  FOR INSERT :::" + paramList);

                System.out.println("=========== Call Service Start ===========");
                callService.insertPd2040_11(paramMap, paramList); 
                System.out.println("=========== Call Service End   ===========");
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("RPT_SQNO").toString());
                break;

            case "SEARCH01" : //제품상세 조회(작업지시기준)
                resultList = callService.selectPd2040List_01(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH02" : //제품상세 조회(생산내역기준)
                resultList = callService.selectPd2040List_02(paramMap);
                model.addAttribute("resultList", resultList);
                break;
                
            case "SEARCH03" : //투입자재상세내역 조회
                //작업지시상세내역조회
                resultList1 = callService.selectPd2040List_03(paramMap);
                model.addAttribute("resultList", resultList1);
                break;
            case "SEARCH13" : //투입자재상세내역 조회 바코드라벨조회 
                //작업지시상세내역조회
                resultList1 = callService.selectPd2040List_13(paramMap);
                model.addAttribute("resultList", resultList1);
                break;
        }        
        return "responseToMybuilder";
    }
}
