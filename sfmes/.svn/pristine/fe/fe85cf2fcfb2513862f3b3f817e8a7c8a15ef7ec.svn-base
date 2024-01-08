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
import com.sfmes.ge.service.Ge2010Service;

/**
 * @Class Name : Ge2010Controller.java
 * @Description : 민원접수 등록/수정/삭제 및 조회
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.11.03  유승현      최초생성
 * @ 2020.12.28  이동훈      변경
 *
 * @author (주)모든솔루션
 * @since 2020.11.03
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Ge2010Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
	
	@Resource(name = "Ge2010Service")
	private Ge2010Service ge2010service;

	/**
	 * 민원접수등록 Request를 처리한다.
	 * @param SVCID
	 * @param INMSV01
	 * @return "responseToMybuilder"
	 * @exception Exception
	 */
	@RequestMapping(value = "/ge2010Controller.do")
	public String ge2010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
	{
		String strSVCID = null;
		String result = "";

		// 파라미터 복호화를 수행한다.
		myBuilderData.setParam(strData);
		
		// 전송된 파라미터를 추출한다.
		strSVCID = myBuilderData.getParam("SVCID");
				
		// 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
		LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
		List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02"));
			
		// 서비스 구분에 따라 분기 처리한다.
		switch(strSVCID) {
		case "INSERT" :
		    result = ge2010service.insertGe2010(paramMap, paramList);
		    model.addAttribute("result", "OK");
		    model.addAttribute("returnValue01", paramMap.get("RC_DT").toString());
		    model.addAttribute("returnValue02", paramMap.get("RC_SQNO").toString());		
			break;
			
		case "UPDATE" :
		    result = ge2010service.updateGe2010(paramMap, paramList);
		    model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", paramMap.get("RC_DT").toString());
            model.addAttribute("returnValue02", paramMap.get("RC_SQNO").toString());		    
		    break;	    
			
		case "SEARCH01" :
		    List<?> resultList01 = ge2010service.selectGe2010_01(paramMap);
			model.addAttribute("resultList", resultList01);
			break;
			
		case "SEARCH02" :
		    List<?> resultList02 = ge2010service.selectGe2010_02(paramMap);
			model.addAttribute("resultList", resultList02);
			break;
			
        case "SEARCH03" :
            List<?> resultList03 = ge2010service.selectGe2010_03(paramMap);
            model.addAttribute("resultList", resultList03);
            break;			
		}
		
		return "responseToMybuilder";
	}
}
