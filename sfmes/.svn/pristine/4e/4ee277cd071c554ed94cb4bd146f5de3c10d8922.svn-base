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

import com.sfmes.cm.web.*;
import com.sfmes.co.service.Co1020Service;

/**
 * @Class Name : Co1020Controller.java
 * @Description : 사업장환경설정 등록
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.22  손용찬      최초생성
 * @ 2020.12.28  이수빈      변경
 *
 * @author (주)모든솔루션
 * @since 2020.06.22
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Co1020Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
	
	@Resource(name = "Co1020Service")
	private Co1020Service callService;

	/**
	 * 사업장환경설정 등록 Request를 처리한다.
	 * @param SVCID
	 * @param INMSV01
	 * @return "responseToMybuilder"
	 * @exception Exception
	 */
	@RequestMapping(value = "/co1020Controller.do")
	public String co1020Controller(HttpServletRequest strData, ModelMap model) throws Exception 
	{
		String strSVCID = null;
		String inMSV01 = null;
		String inMSV02 = null;

		// 파라미터 복호화를 수행한다.
		myBuilderData.setParam(strData);
		
		// 전송된 파라미터를 추출한다.
		strSVCID = myBuilderData.getParam("SVCID");
		inMSV01 = myBuilderData.getParam("INMSV01");
		inMSV02 = myBuilderData.getParam("INMSV02");
		
		
		// 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
		LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
		List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(inMSV02);
		List<?> resultList = null;			
		
		// 서비스 구분에 따라 분기 처리한다.
		switch(strSVCID) {
		case "INSERT" :
			// 사업장환경설정 등록
			callService.insertCo1020(paramMap, paramList);
			model.addAttribute("result", "OK");			
			break;
			
		case "UPDATE" :
			// 사업장환결설정 수정
			callService.updateCo1020(paramMap, paramList);
			model.addAttribute("result", "OK");			
			break;

		case "SEARCH01" :
			// 사업장환경설정 조회
			resultList = callService.selectCo1020List01(paramMap);
			model.addAttribute("resultList", resultList);			
			break;
			
		case "SEARCH02" :
			// 사업장환경설정 변경이력 조회
			resultList = callService.selectCo1020List02(paramMap);
			model.addAttribute("resultList", resultList);			
			break;
			
		case "SEARCH03" :
			// 사업장환경설정 인정감모율 조회
			resultList = callService.selectCo1020List03(paramMap);
			model.addAttribute("resultList", resultList);			
			break;
		}
		
		return "responseToMybuilder";
	}
}