package com.sfmes.co.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.codehaus.jackson.map.ObjectMapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.co.service.Co3020Service;
/**
 * @Class Name  : Co3020Controller.java
 * @Description : Co3020Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.01   곽환용     최초생성
 * @ 2020.11.12   이수빈     변경
 * @ 2020.12.28   이수빈     변경
 *
 * @author (주)모든솔루션
 * @since 2020.06.09
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Co3020Controller {

        @Resource(name = "myBuilderData")
        protected MyBuilderData myBuilderData;
        
        @Resource(name = "Co3020Service")
        private Co3020Service callService;

        /**
         * 행안부 오픈API를 통해 주소를 검색하여 처리한다.
         * @param SVCID
         * @param INMSV01
         * @return "responseToMybuilder"
         * @exception Exception
         */
        @RequestMapping(value = "/co3020Controller.do")
        public String co3020Controller(HttpServletRequest strData, ModelMap model) throws Exception 
        {
            String strSVCID = null;
            
            strSVCID = myBuilderData.getParam("SVCID");

            // 파라미터 복호화를 수행한다.
            myBuilderData.setParam(strData);
            
         // 전송된 파라미터를 추출한다.
            strSVCID = myBuilderData.getParam("SVCID");
            
            
            
            // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
            LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            
            switch(strSVCID) {
                case "SEARCH01" :
                    String addrKey = "U01TX0FVVEgyMDIwMDYwODE2MzMyNTEwOTg0NDM="; //인증키                     
                    String currentPage = "1";       //요청 변수 설정 (현재 페이지. currentPage : n > 0)
                    String countPerPage = "100";    //요청 변수 설정 (페이지당 출력 개수. countPerPage 범위 : 0 < n <= 100)
                    String resultType = "json";     //요청 변수 설정 (검색결과형식 설정, json)
                    String confmKey = addrKey;      //요청 변수 설정 (승인키)
                    String keyword = (String) paramMap.get("SearchCondition");  //요청 변수 설정 (키워드)
                    // OPEN API 호출 URL 정보 설정
                    String apiUrl = "http://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+currentPage+"&countPerPage="+countPerPage+"&keyword="+URLEncoder.encode(keyword,"UTF-8")+"&confmKey="+confmKey+"&resultType="+resultType; 
                    URL url = new URL(apiUrl);
                    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
                    StringBuffer sb = new StringBuffer();
                    String tempStr = null;

                    while(true){
                        tempStr = br.readLine();
                        if(tempStr == null) break;
                        sb.append(tempStr);     // 응답결과 JSON 저장
                    }
                    br.close();

                    //JSON타입으로 형변환
                    JSONObject jsonResult = (JSONObject) JSONSerializer.toJSON(sb.toString().trim());
                    JSONObject JsonObject1 = new JSONObject().fromObject(jsonResult.get("results"));
                    JSONArray JsonArray1 = JsonObject1.getJSONArray("juso");
                    
                    //Map타입으로 형변환
                    for(int i = 0; i < JsonArray1.size() ; i++){
                        Map<String, Object> map = new ObjectMapper().readValue(String.valueOf(JsonArray1.get(i)), Map.class);
                        resultList.add(map);
                    }
                    model.addAttribute("resultList", resultList);
                    break;
                    
                case "SEARCH02" : //묶음번호조회
                    resultList = (List<Map<String, Object>>) callService.selectCo3020List(paramMap);
                    model.addAttribute("resultList", resultList);
                    break;
                    
            }
            
            return "responseToMybuilder";
        }
    }
