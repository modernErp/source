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
import com.sfmes.co.service.Co1090Service;

/**
 * @Class Name : Co1090Controller.java
 * @Description : 거래처물품바코드정보
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.08.06  이수빈      최초생성
 * @ 2020.12.28  이수빈      변경
 *
 * @author (주)모든솔루션
 * @since 2020.08.06
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Co1090Controller {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;

    @Resource(name = "Co1090Service")
    private Co1090Service callService;

    /**
     * 테스트 예제 Request를 처리한다.
     * 
     * @param SVCID
     * @param INMSV01
     * @return "responseToMybuilder"
     * @exception Exception
     */
    @RequestMapping(value = "/co1090Controller.do")
    public String co1090Controller(HttpServletRequest strData, ModelMap model) throws Exception {
        String strSVCID = null;
        String inMSV01 = null;
        String inMSV02 = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);

        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        inMSV01 = myBuilderData.getParam("INMSV01");
        inMSV02 = myBuilderData.getParam("INMSV02");

        System.out.println("== 서비스수행 시작1 ==" + inMSV01);
        System.out.println("== 서비스수행 strSVCID ==" + strSVCID);

        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(inMSV02);
        List<?> resultList = null;

        System.out.println("== 서비스수행 paramMap ==" + paramMap.toString());

        // 서비스 구분에 따라 분기 처리한다.
        switch (strSVCID) {

        case "INSERT01":
            callService.insertCo1090(paramMap);
            model.addAttribute("result", "OK");
            break;

        case "UPDATE01":
            // 사업장수정
            callService.updateCo1090(paramMap);
            model.addAttribute("result", "OK");
            break;

        case "SEARCH01":
            // 테스트 데이터 조회 서비스를 호출한다.
            resultList = callService.selectCo1090List(paramMap);
            model.addAttribute("resultList", resultList);
            break;
        }

        System.out.println("== 서비스수행 종료 ==");

        return "responseToMybuilder";
    }
}