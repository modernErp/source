package com.sfmes.ca.web;


import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.*;
import com.sfmes.ca.service.Ca4130Service;
@Controller
public class Ca4130Controller {


    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
	
	@Resource(name = "Ca4130Service")
	private Ca4130Service callService;

	@RequestMapping(value = "/Ca4130Controller.do")
	public String Ca4130Controller(HttpServletRequest strData, ModelMap model) throws Exception 
	{
		String strSVCID = null;
		String inMSV01 = null;

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
		
		case "SEARCH01" :
			// 외상매입금잔액장조회
			resultList = callService.selectCa4130List(paramMap);
			model.addAttribute("resultList", resultList);
			break;		
		}

		return "responseToMybuilder";
	}
}