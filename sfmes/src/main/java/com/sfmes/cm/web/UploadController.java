package com.sfmes.cm.web;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.service.UploadService;


/**
 * @Class Name  : UploadController.java
 * @Description : File Upload Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------   ------   -------------------------------
 * @ 2020.10.07   여다혜   최초작성
 *
 * @author (주)모든솔루션
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by (주)모든솔루션 All right reserved.
 */

@Controller
public class UploadController {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    @Resource(name = "UploadService")
    private UploadService callService;

    /**
     * 파일업로드 Controller
     * @param   
     * @return 
     * @exception 
     */
    @RequestMapping(value = "/uploadController.do")
    public String uploadController( HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
 
        //File Upload Call - TB_CO_M_APDFL TABLE에 저장, APD_FILE_AMN_NO 채번하여 리턴
        String uploadKey = callService.insertUploadFile(request);
        model.addAttribute("result", "OK");
        model.addAttribute("uploadKey", uploadKey);
        
        //APD_FILE_AMN_NO (upload key) 리턴 
        return "upload";
    }


    /**
     * 업로드 파일 관련 Controller
     * @param 
     * @return 
     * @exception 
     */
    @RequestMapping(value = "/fileController.do")
    public String apdFileController(@RequestParam("data") String strData, ModelMap model) throws Exception {
        String strSVCID = null;

        // 파라미터 복호화를 수행한다.
        myBuilderData.setParam(strData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        List<Map<String, Object>> paramList = myBuilderData.getParamFromMSVList(myBuilderData.getParam("INMSV02"));
        List<?> resultList = null; 
    
        switch(strSVCID) {
            case "SEARCH01" :  //회사정보조회
                System.out.println("fileController, SEARCH01 paramMap ==>" + paramMap);
                resultList = callService.selectUploadFileList(paramMap);
                System.out.println("fileController, SEARCH01 paramList ==>" + paramList);
                model.addAttribute("resultList", resultList);           
                break;    
              
//            case "SEARCH02" :  //사업장목록조회
//                System.out.println("CO1000 SEARCH02 PARAMMAP :::" + paramMap);
//                resultList = callService.selectBzplList(paramMap);
//                System.out.println("resultList for SEARCH02 :::" + resultList);
//                model.addAttribute("resultList", resultList);
//                break;
                
            
//            case "SAVE01" : //회사정보, 사업자정보 수정
//                System.out.println("CO1000 SAVE01 paramMap :::" + paramMap);
//                System.out.println("CO1000 SAVE01 paramList :::" + paramList);
//                callService.updateCo1000(paramMap, paramList);
//                model.addAttribute("result", "OK");
//                break;
        }             
        
        return "responseToMybuilder";
    }    
    
}
