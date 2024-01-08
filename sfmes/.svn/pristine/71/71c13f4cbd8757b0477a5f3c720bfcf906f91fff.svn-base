package com.sfmes.dl.web;

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
import com.sfmes.dl.service.Dl1020Service;

/**
 * @Class Name : Dl1020Controller.java
 * @Description : 회계계정코드 등록
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.22  손용찬      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.22
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Dl1020Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
	
	@Resource(name = "Dl1020Service")
	private Dl1020Service callService;

	/**
	 * 견적서등록 Request를 처리한다.
	 * @param SVCID
	 * @param INMSV01
	 * @return "responseToMybuilder"
	 * @exception Exception
	 */
	@RequestMapping(value = "/dl1020Controller.do")
	public String dl1010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
	{
		String strSVCID = null;
		String inMSV01 = null;
		String inMSV02 = null;
		
		// System.out.println("dl1010Controller : start ===== ");

		// 파라미터 복호화를 수행한다.
		myBuilderData.setParam(strData);
		
		// 전송된 파라미터를 추출한다.
		strSVCID = myBuilderData.getParam("SVCID");
		inMSV01 = myBuilderData.getParam("INMSV01");
		inMSV02 = myBuilderData.getParam("INMSV02");
		
		// System.out.println("SVCID : [" + strSVCID + "]");
		System.out.println("inMSV01 : [" + inMSV01 + "]");
		System.out.println("inMSV02 : [" + inMSV02 + "]");
		
		// 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
		LinkedHashMap paramMap = null;
		List<?> resultList = null;		
		
		// 서비스 구분에 따라 분기 처리한다.		
		switch(strSVCID) {
		case "INSERT" :
			// 회계분개기준 등록
			List<Map<String, Object>> paramList01 = myBuilderData.getParamFromMSVList(inMSV01);
			List<Map<String, Object>> paramList02 = myBuilderData.getParamFromMSVList(inMSV02);
			callService.insertDl1020(paramList01, paramList02);
			model.addAttribute("result", "OK");			
			break;
			
		case "SEARCH01" :
            // 회계분개기준 조회
			paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
            resultList = callService.selectDl1020List01(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
            
		case "SEARCH02" :
            // 회계분개기준 상세조회
			paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
            resultList = callService.selectDl1020List02(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
            
		}		
		return "responseToMybuilder";
	}
}