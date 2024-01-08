package com.sfmes.ge.web;

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
import com.sfmes.ge.service.Ge2015Service;

/**
 * @Class Name : Ge2015Controller.java
 * @Description : 민원접수내역 조회
 * @Modification Information
 * @
 * @  수정일     수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.11.05  유승현      최초생성
 * @ 2020.12.28  이동훈      변경
 *
 * @author (주)모든솔루션
 * @since 2020.11.05
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Ge2015Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
	
	@Resource(name = "Ge2015Service")
	private Ge2015Service ge2015service;

	/**
	 * 민원접수내역 Request를 처리한다.
	 * @param SVCID
	 * @param INMSV01
	 * @return "responseToMybuilder"
	 * @exception Exception
	 */
	@RequestMapping(value = "/ge2015Controller.do")
	public String ge2015Controller(HttpServletRequest strData, ModelMap model) throws Exception 
	{
		String strSVCID = null;
		String result = "";

		// 파라미터 복호화를 수행한다.
		myBuilderData.setParam(strData);
		
		// 전송된 파라미터를 추출한다.
		strSVCID = myBuilderData.getParam("SVCID");
			
		// 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
		LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
		
		// 서비스 구분에 따라 분기 처리한다.
		switch(strSVCID) {	
		case "SEARCH01" :
		    List<?> resultList01 = ge2015service.selectGe2015_01(paramMap);
			model.addAttribute("resultList", resultList01);
			break;
			
		case "SEARCH02" :
		    List<?> resultList02 = ge2015service.selectGe2015_02(paramMap);
			model.addAttribute("resultList", resultList02);
			break;

		}
		
		return "responseToMybuilder";
	}
}
