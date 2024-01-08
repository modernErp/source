package com.sfmes.se.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.se.service.Se3010Service;
/**
 * @Class Name : Se3010Controller.java
 * @Description : 매출등록/수정 및 조회 Controller
 * @Modification Information
 * @
 * @  수정일     수정자   수정내용
 * @ ----------  ------  -------------------------------
 * @ 2020.07.14  곽환용   최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Se3010Controller {

    @Resource(name = "Se3010Service")
    private Se3010Service se3010service;

    @Resource(name = "myBuilderData")
    private MyBuilderData myBuilderData;

    @RequestMapping(value = "/se3010Controller.do")
    public String se3010Controller(HttpServletRequest StrData, ModelMap model) throws Exception {
        String strSVCID = null; //서비스분기용 String
        String result = "";

        myBuilderData.setParam(StrData);  //파라미터 복호화

        strSVCID = myBuilderData.getParam("SVCID");

        //입력된 MSV 타입의 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02")); //매출등록 parameterList

        switch(strSVCID){
            case "SEARCH01" : //매출기본내역 조회
                List<?> resultList01 = se3010service.selectSe3010_01(paramMap);
                model.addAttribute("resultList", resultList01);
                break;

            case "SEARCH02" : //매출상세내역 조회
                List<?> resultList02 = se3010service.selectSe3010_02(paramMap);
                model.addAttribute("resultList", resultList02);
                break;

            case "SEARCH03" : //매출내역찾기팝업 조회
                List<?> resultList03 = se3010service.selectSe3010_03(paramMap);
                model.addAttribute("resultList", resultList03);
                break;

            case "SEARCH04" : //참조출고상세내역 조회
                List<?> resultList04 = se3010service.selectSe3010_04(paramMap);
                model.addAttribute("resultList", resultList04);
                break;

            case "SEARCH05" : //매출정산내역 조회
                List<?> resultList05 = se3010service.selectSe3010_05(paramMap);
                model.addAttribute("resultList", resultList05);
                break;

            case "SEARCH06" : //매출단가부가세포함여부조회
                List<?> resultList06 = se3010service.selectSe3010_06(paramMap);
                model.addAttribute("resultList", resultList06);
                break;

            case "INSERT" :   //매출내역기본,상세 저장
                result = se3010service.insertSe3010(paramMap, paramList);
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", paramMap.get("SL_DT").toString());
                model.addAttribute("returnValue02", paramMap.get("SL_SQNO").toString());
                break;

            case "UPDATE" :   //매출내역기본,상세 저장
            	paramMap.put("SLP_NML_YN", "N"); // 삭제를 위한 전표정상여부 N값으로 설정

            	// 매출등록내역 삭제시 출고내역 정보 수정을 위해 매출등록 날짜가 null로 설정됨
            	// 삭제 후 저장을 위한 변수 세팅
            	String slDt = (String)paramMap.get("SL_DT");
            	se3010service.deleteSe3010(paramMap, paramList);

            	// 삭제후 전표정상여부 N 값을 저장을 위해 Y로 변경
            	paramMap.put("SLP_NML_YN", "Y");
            	paramMap.put("SL_DT", slDt);
            	result = se3010service.insertSe3010(paramMap, paramList);
            	model.addAttribute("result", "OK");
            	model.addAttribute("returnValue01", paramMap.get("SL_DT").toString());
            	model.addAttribute("returnValue02", paramMap.get("SL_SQNO").toString());
            	break;

            case "DELETE" :   //매출내역삭제 
                se3010service.deleteSe3010(paramMap, paramList);
                model.addAttribute("result", "OK");
                break;
        }

        return "responseToMybuilder";
    }
}
