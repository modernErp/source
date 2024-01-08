package com.sfmes.ge.web;

import java.util.LinkedHashMap;
import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.*;
import com.sfmes.ge.service.Ge1030Service;

/**
 * @Class Name : Ge1030Controller.java
 * @Description : 자료실 등록
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.30  손용찬      최초생성
 * @ 2020.12.28  이동훈      변경
 *
 * @author (주)모든솔루션
 * @since 2020.06.30
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Ge1030Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
	
	@Resource(name = "Ge1030Service")
	private Ge1030Service callService;

	/**
	 * 자료실 등록 Request를 처리한다.
	 * @param SVCID
	 * @param INMSV01
	 * @return "responseToMybuilder"
	 * @exception Exception
	 */
	@RequestMapping(value = "/ge1030Controller.do")
	public String ge1030Controller(HttpServletRequest strData, ModelMap model) throws Exception 
	{
		String strSVCID = null;
		String inMSV01 = null;		
		String doc_sqno = null; //리턴할 문서sequence

		// 파라미터 복호화를 수행한다.
		myBuilderData.setParam(strData);
		
		// 전송된 파라미터를 추출한다.
		strSVCID = myBuilderData.getParam("SVCID");
		inMSV01 = myBuilderData.getParam("INMSV01");				
		
		// 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
		LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);		
		List<?> resultList = null;		
		
		// 서비스 구분에 따라 분기 처리한다.
		switch(strSVCID) {
		case "INSERT" :
		    // 자료실 등록
		    doc_sqno = callService.insertGe1030(paramMap);
			
			model.addAttribute("result", "OK");		
			model.addAttribute("returnValue01", doc_sqno);
			break;
			
		case "UPDATE" :
			// 자료실 수정
		    doc_sqno = callService.updateGe1030(paramMap);
		    
			model.addAttribute("result", "OK");	
			model.addAttribute("returnValue01", doc_sqno);
			break;

		case "SEARCH01" :
			// 자료실찾기 팝업 내역조회
			resultList = callService.selectGe1030ListP01(paramMap);
			model.addAttribute("resultList", resultList);			
			break;
			
		case "SEARCH02" :
			// 자료실 상세내역 조회
			resultList = callService.selectGe1030List01(paramMap);
			model.addAttribute("resultList", resultList);			
			break;	
		}
		
		return "responseToMybuilder";
	}
}