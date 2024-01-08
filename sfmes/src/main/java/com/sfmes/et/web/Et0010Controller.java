package com.sfmes.et.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.*;
import com.sfmes.et.service.Et0010Service;

/**
 * @Class Name : Et0010Controller.java
 * @Description : 테스트 예제 컨트롤러 로직
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.05.22  이철홍      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.05.22
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Et0010Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
	
	@Resource(name = "Et0010Service")
	private Et0010Service callService;

	/**
	 * 테스트 예제 Request를 처리한다.
	 * @param SVCID
	 * @param INMSV01
	 * @return "responseToMybuilder"
	 * @exception Exception
	 */
	@RequestMapping(value = "/et0010Controller.do")
	public String et0010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
	{
		String strSVCID = null;
		String inMSV01 = null;

		// 파라미터 복호화를 수행한다.
		myBuilderData.setParam(strData);
		
		// 전송된 파라미터를 추출한다.
		strSVCID = myBuilderData.getParam("SVCID");
		inMSV01 = myBuilderData.getParam("INMSV01");
		
		System.out.println("== 서비스수행 시작1 ==" + inMSV01);
		System.out.println("== 서비스수행 strSVCID ==" + strSVCID);
		
		
		// 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
		LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
		List<?> resultList = null;
		
		System.out.println("== 서비스수행 paramMap ==" + paramMap.toString());
		
		// 서비스 구분에 따라 분기 처리한다.
		switch(strSVCID) {
		case "INSERT" :
			// 테스트 데이터 신규 서비스를 호출한다.
			callService.insertEt0010(paramMap);
			model.addAttribute("result", "OK");			
			break;
			
		case "UPDATE" :
			// 테스트 데이터 수정 서비스를 호출한다.
			callService.updateEt0010(paramMap);
			model.addAttribute("result", "OK");
			break;

		case "DELETE" :
			// 테스트 데이터 삭제 서비스를 호출한다.
			callService.deleteEt0010(paramMap);
			model.addAttribute("result", "OK");
			break;
			
		case "SEARCH01" :
			// 테스트 데이터 조회 서비스를 호출한다.
			resultList = callService.selectEt0010List(paramMap);
			model.addAttribute("resultList", resultList);
			break;
			
		case "SEARCH02" :
			// 테스트 데이터 상세조회 서비스를 호출한다.
			resultList = callService.selectEt0010One(paramMap);
			model.addAttribute("resultList", resultList);
			break;
			
		case "SEARCH03" :
			// 테스트 데이터 조회 서비스를 호출한다.
			resultList = callService.selectEt0010List02(paramMap);
			model.addAttribute("resultList", resultList);
			break;
		}

		System.out.println("== 서비스수행 종료 ==");
		
		return "responseToMybuilder";
	}
}
