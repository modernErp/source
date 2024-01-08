package com.sfmes.sy.web;

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
import com.sfmes.sy.service.Sy3010Service;

/**
 * @Class Name : Sy3010Controller.java
 * @Description : 연계전문등록
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.01  손용찬      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.01
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Sy3010Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
	
	@Resource(name = "Sy3010Service")
	private Sy3010Service callService;

	/**
	 * 연계전문등록 Request를 처리한다.
	 * @param SVCID
	 * @param INMSV01
	 * @return "responseToMybuilder"
	 * @exception Exception
	 */
	@RequestMapping(value = "/sy3010Controller.do")
	public String et0010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
	{
		String strSVCID = null;
		String inMSV01 = null;
		String inMSV02 = null;
		String inMSV03 = null;

		// 파라미터 복호화를 수행한다.
		myBuilderData.setParam(strData);
		
		// 전송된 파라미터를 추출한다.
		strSVCID = myBuilderData.getParam("SVCID");
		inMSV01 = myBuilderData.getParam("INMSV01");
		inMSV02 = myBuilderData.getParam("INMSV02");
		inMSV03 = myBuilderData.getParam("INMSV03");
		
		
		// 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
		LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
		List<Map<String, Object>> paramList01 = myBuilderData.getParamFromMSVList(inMSV02);
		List<Map<String, Object>> paramList02 = myBuilderData.getParamFromMSVList(inMSV03);
		List<?> resultList = null;			
		
		// 서비스 구분에 따라 분기 처리한다.
		switch(strSVCID) {
		case "INSERT" :
			//연계전문 데이터 등록
			callService.insertSy3010(paramMap, paramList01, paramList02);
			model.addAttribute("result", "OK");
			break;
			
		case "UPDATE" :
			//연계전문 데이터 수정
			callService.updateSy3010(paramMap, paramList01, paramList02);
			model.addAttribute("result", "OK");
			break;
			
		case "SEARCH01" :
			// 언계전문 리스트 데이터 상세조회 서비스를 호출한다.
			resultList = callService.selectSy3010One(paramMap);
			model.addAttribute("resultList", resultList);
			break;
		case "SEARCH02" :
			// 연계전문 폼 데이터 상세조회 서비스를 호출한다.
			resultList = callService.selectSy3010List(paramMap);
			model.addAttribute("resultList", resultList);
			break;
		}
		
		return "responseToMybuilder";
	}
}
