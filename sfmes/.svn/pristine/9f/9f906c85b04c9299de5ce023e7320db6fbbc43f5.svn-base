package com.sfmes.sm.web;

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
import com.sfmes.se.service.Se6010Service;
import com.sfmes.se.service.Se6020Service;
import com.sfmes.sm.service.Sm1030Service;

/**
 * @Class Name  : PDASm1030ServiceImpl.java
 * @Description : PDASm1030Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2021.03.31   박지환       최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class PDASm1030Controller {

    @Resource(name = "myBuilderData_PDA")
    protected MyBuilderData_PDA myBuilderData_PDA;

    @Resource(name = "Sm1030Service")
    private Sm1030Service sm1030Service;

    @Resource(name = "Se6010Service")
    private Se6010Service se6010Service;
    
    @Resource(name = "Se6020Service")
    private Se6020Service se6020Service;

    /**
     * 공통코드등록을 처리한다.
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/PDAsm1030Controller.do")
    public String PDAsm1030Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        String strSVCID = null;
        String inMSV01  = null;
        String inMSV02  = null;
        String inMSV03  = null;
        String result   = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData_PDA.setParam(strData);

        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData_PDA.getParam("SVCID");
        inMSV01  = myBuilderData_PDA.getParam("INMSV01");
        inMSV02  = myBuilderData_PDA.getParam("INMSV02");
        inMSV03  = myBuilderData_PDA.getParam("INMSV03");

        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData_PDA.getParamFromMSVHashMap(inMSV01);
        List<Map<String, Object>> paramList = myBuilderData_PDA.getParamFromMSVList(inMSV02);       // 수정된 데이터
        List<Map<String, Object>> paramList2 = myBuilderData_PDA.getParamFromMSVList(inMSV03);       // 수정된 데이터

        List<?> resultList = null;

        switch(strSVCID) {
	        case "SEARCH01" :   //출고지시 기본 조회
	            resultList = se6010Service.selectPdaSe6010_01(paramMap);
	            model.addAttribute("resultList", resultList);
	            break;
	        
	        case "SEARCH02" :   //출고지시 상세 조회
	        	resultList = se6020Service.selectSe6020_D_DLR(paramMap);
	            model.addAttribute("resultList", resultList);
	            break;
	            
	        case "SAVE01" :     // 창고간이동내역저장
	            result = se6020Service.saveSe6020(paramMap, paramList);
	            model.addAttribute("result", "OK");
	            model.addAttribute("returnValue01", paramMap.get("MVE_DT").toString());
	            model.addAttribute("returnValue02", paramMap.get("MVE_SQNO").toString());
	            break;            
        }

        return "responseToAppChef";
    }   
}
