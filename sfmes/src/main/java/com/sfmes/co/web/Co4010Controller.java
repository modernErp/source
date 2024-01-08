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

/**
 * @Class Name  : Co4010Controller.java
 * @Description : Co4010Service Class
 * @Modification Information
 * @
 * @  수정일             수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.23   장경석            최초생성
 * @ 2020.12.28   이수빈           변경
 *
 * @author (주)모든솔루션
 * @since 2020.06.09
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class Co4010Controller {

        @Resource(name = "myBuilderData")
        protected MyBuilderData myBuilderData;

        /**
         * 축산물이력번호 이력제 데이터 전송 오픈API를 통해 처리한다.(REST 방식 - json 전송)
         * @param SVCID
         * @param INMSV01
         * @return "responseToMybuilder"
         * @exception Exception
         */
        @RequestMapping(value = "/co4010Controller.do")
        public String Co4010Controller(HttpServletRequest strData, ModelMap model) throws Exception 
        {
            String strSVCID = myBuilderData.getParam("SVCID");

            // 매입실적신고
            String apiUrl_se_p = "http://api.mtrace.go.kr/rest/pig/processIn/uploadProcessIn?";
            // 포장처리실적신고
            String apiUrl_pd_p = "http://api.mtrace.go.kr/rest/pig/processPacking/uploadProcessPacking?"; 
            // 묶음번호구성내역신고
            String apiUrl_bn_p = "http://api.mtrace.go.kr/rest/pig/processLot/uploadProcessLot?";
            // 매출실적신고
            String apiUrl_sm_p = "http://api.mtrace.go.kr/rest/pig/processOut/uploadProcessOut?";

            // 파라미터 복호화를 수행한다.
            myBuilderData.setParam(strData);
            
            // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
            LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            
            switch(strSVCID) {
                case "SEARCH01" :
                    String authori_Key = "openApiTestApiKey"; // 테스트인증키                     
                    String currentPage = "1";       //요청 변수 설정 (현재 페이지. currentPage : n > 0)
                    String countPerPage = "100";    //요청 변수 설정 (페이지당 출력 개수. countPerPage 범위 : 0 < n <= 100)
                    String resultType = "json";     //요청 변수 설정 (검색결과형식 설정, json)
                    String confmKey = authori_Key;      //요청 변수 설정 (승인키)
                    String keyword = (String) paramMap.get("SearchCondition");  //요청 변수 설정 (키워드)
                    
                    String apiUrl = "";
                    
                    // OPEN API 호출 URL 정보 설정
                    if("1".equals(paramMap.get("search_gbn"))) {
                        apiUrl = apiUrl_se_p;
                    } else if("2".equals(paramMap.get("search_gbn"))) {
                        apiUrl = apiUrl_pd_p;
                    } else if("3".equals(paramMap.get("search_gbn"))) {
                        apiUrl = apiUrl_bn_p;
                    } else if("4".equals(paramMap.get("search_gbn"))) {
                        apiUrl = apiUrl_sm_p;
                    }
                    
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
            }
            
            return "responseToMybuilder";
        }
    }
