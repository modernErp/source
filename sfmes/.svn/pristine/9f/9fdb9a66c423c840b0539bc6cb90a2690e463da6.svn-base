package com.sfmes.co.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.XML;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.expression.spel.ast.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.co.service.CoSraNoHistService;
import com.sfmes.pd.service.Pd1010Service;

/**
 * @Class Name  : CoSrsNoHistController.java
 * @Description : CoSrsNoHistController Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.08.27   장경서     최초생성
 * @ 2020.12.28   이수빈     변경
 *
 * @author (주)모든솔루션
 * @since 2020.08.27
 * @version 1.0
 * @see
 *
 * Copyright (C) by 모든솔루션 All right reserved.
 */

@Controller
public class CoSraNoHistController {

        @Resource(name = "myBuilderData")
        protected MyBuilderData myBuilderData;

        @Resource(name = "CoSraNoHistService")
        private CoSraNoHistService callService;
        
        /**
         * 행안부 오픈API를 통해 주소를 검색하여 처리한다.
         * @param SVCID
         * @param INMSV01
         * @return "responseToMybuilder"
         * @exception Exception
         */
        @RequestMapping(value = "/coSrsNoHistController.do")
        public String coSrsNoHistController(HttpServletRequest strData, ModelMap model) throws Exception 
        {
            String strSVCID = null;
            String inMSV01  = null;

            // 파라미터 복호화를 수행한다.
            myBuilderData.setParam(strData);
            
            strSVCID = myBuilderData.getParam("SVCID");
            inMSV01  = myBuilderData.getParam("INMSV01");

            // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
            LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(inMSV01);
            List<Map<String, Object>> resultList     = new ArrayList<Map<String, Object>>();
            LinkedHashMap<String, Object> resultMap  = new LinkedHashMap<String, Object>();
            //List<?> resultList = null;
            
            String svcrKey = "U5fQaZ7kgq2iTivqH8W1mHOAsYgP6oozvMDQKUISNy%2Bs8G8jYj8KrYzvNcS83m0rQbCf%2Bw2KAg7lqGqkjDMV6Q%3D%3D"; //인증키                     
//            String srsHisNo = "410002046636437";
            String srsHisNo = (String) paramMap.get("SRA_HST_NO");  // 조회하고자하는 축산물이력(묶음)번호
//          String opt_dsc = (String) paramMap.get("opt_dsc");   // 조회하고자하는 축산물이력(묶음)번호 정보구분자
//          String corpNo  = (String) paramMap.get("corpNo");    // 묶음번호생성 사업자번호

            StringBuilder sb = new StringBuilder();
            
            switch(strSVCID) {
                case "SEARCH01" :  // XML to JSON
                    resultMap = callService.CoSraNoHistselect(paramMap);
                    
                    // OpenApi 에서 가져온 Map을 풀어서 DB() 에 저장(Update)
                    
                    callService.CoSraNoHistupdate(resultMap);
                    break;
                case "SEARCH02" :  // XML to JSON
                    Map<String, Object> map = new HashMap<String, Object>();
                    
                    StringBuilder urlBuilder = new StringBuilder("http://data.ekape.or.kr/openapi-data/service/user/mtrace/breeding/cattle"); /*URL*/
                    urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + svcrKey); /*Service Key*/
                    urlBuilder.append("&" + URLEncoder.encode("cattleNo","UTF-8") + "=" + URLEncoder.encode(srsHisNo, "UTF-8")); /*이력번호*/
                    URL url = new URL(urlBuilder.toString());
                    
                    /* 공공데이타 OPENAPI 연결 */
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-type", "application/json");
                    System.out.println("Response code: " + conn.getResponseCode());
                    
                    BufferedReader rd;
                    sb = null;
                    
                    if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    } else {
                        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                    }

                    String line;
                    while ((line = rd.readLine()) != null) {
                        sb.append(line);
                    }
                    rd.close();
                    conn.disconnect();
//                    System.out.println(sb.toString());  //Original
                    
                    // 받아온 xml데이타를 JSON 형태로 파싱
                    org.json.JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
                    String xmlJSONObjString = xmlJSONObj.toString();
                    
                    ObjectMapper objMapper = new ObjectMapper();

                    JSONObject jsonResult = (JSONObject) JSONSerializer.toJSON(sb.toString().trim());
                    JSONObject JsonObject1 = new JSONObject().fromObject(jsonResult.get("items"));
                    JSONArray JsonArray1 = JsonObject1.getJSONArray("item");
                    
                    //Map타입으로 형변환
                    for(int i = 0; i < JsonArray1.size() ; i++){
                        map = new ObjectMapper().readValue(String.valueOf(JsonArray1.get(i)), Map.class);
                        resultList.add(map);
                    }
                    model.addAttribute("resultList", resultList);

                    break;
                    
                case "SEARCH03" : // JSON Type
                    String resultType = "xml";     //요청 변수 설정 (검색결과형식 설정, json)

                    // OPEN API 호출 URL 정보 설정
                    // 데이타확인용
                    // http://data.ekape.or.kr/openapi-data/service/user/animalTrace/traceNoSearch?serviceKey=KqlVhMJBvvx1EZvPokjO7rEOOncXh2hqprNmBbTH6UsBtsvD%2BB0bD51IDTwEr5PcEFvoLiyV20aWYoz0ZbVcCQ%3D%3D&traceNo=L01709271277007&optionNo=9&corpNo=1178522046&
                    String apiUrl = "http://data.ekape.or.kr/openapi-data/service/user/mtrace/breeding/cattle?"
                                  + URLEncoder.encode("ServiceKey","UTF-8") + '=' + svcrKey + "&"+URLEncoder.encode("cattleNo","UTF-8") + "=" + URLEncoder.encode(srsHisNo, "UFT-8"); 
                    // String CowapiUrl = "http://data.ekape.or.kr/openapi-data/service/user/animalTrace/traceNoSearch?ServiceKey="+confmKey+"&optionNo="+opt_dsc+"&corpNo="+corpNo;
                    // String PigapiUrl = "https://www.mtrace.go.kr/mtracesearch/pigLotNoSearch.do?lotNo=L11702093749012";
                    URL data_url = new URL(apiUrl);
                    BufferedReader br = new BufferedReader(new InputStreamReader(data_url.openStream(),"UTF-8"));
                    sb = null;
                    String tempStr = null;

                    while(true){
                        tempStr = br.readLine();
                        if(tempStr == null) break;
                        sb.append(tempStr);     // 응답결과 JSON 저장
                    }
                    br.close();

                    //JSON타입으로 형변환
                    /*
                    JSONObject jsonResult = (JSONObject) JSONSerializer.toJSON(sb.toString().trim());
                    JSONObject JsonObject1 = new JSONObject().fromObject(jsonResult.get("results"));
                    JSONArray JsonArray1 = JsonObject1.getJSONArray("juso");
                    
                    //Map타입으로 형변환
                    for(int i = 0; i < JsonArray1.size() ; i++){
                        Map<String, Object> map = new ObjectMapper().readValue(String.valueOf(JsonArray1.get(i)), Map.class);
                        resultList.add(map);
                    }
                    model.addAttribute("resultList", resultList);
                    */
                    break;
            }
            
            return "responseToMybuilder";
        }
    }
