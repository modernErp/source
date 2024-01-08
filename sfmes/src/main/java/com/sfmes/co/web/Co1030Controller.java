package com.sfmes.co.web;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.*;
import com.sfmes.co.service.Co1030Service;

/**
 * @Class Name : Co1030Controller.java
 * @Description : 창고등록
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.15  손용찬      최초생성
 * @ 2020.12.28  이수빈      변경
 *
 * @author (주)모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Co1030Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
	
	@Resource(name = "Co1030Service")
	private Co1030Service callService;

	/**
	 * 창고등록 Request를 처리한다.
	 * @param SVCID
	 * @param INMSV01
	 * @return "responseToMybuilder"
	 * @exception Exception
	 */
	@RequestMapping(value = "/co1030Controller.do")
	public String co1030Controller(HttpServletRequest strData, ModelMap model) throws Exception 
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
		case "INSERT" :
			// 창고등록
			callService.insertCo1030(paramMap);
			model.addAttribute("result", "OK");			
			break;
			
		case "UPDATE" :
			// 창고수정
			callService.updateCo1030(paramMap);
			model.addAttribute("result", "OK");			
			break;
			
		case "SEARCH01" :
			// 창고 상세조회
			resultList = callService.selectCo1030List01(paramMap);
			model.addAttribute("resultList", resultList);			
			break;
			
		case "SEARCH02" :
            // 창고 등록시 사업장주소 조회
            resultList = callService.selectCo1030List02(paramMap);
            model.addAttribute("resultList", resultList);           
            break;
		}
		
		return "responseToMybuilder";
	}
}
