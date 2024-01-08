package com.sfmes.cm.web;

import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

// import sun.misc.BASE64Decoder;
// import sun.misc.BASE64Encoder;

import java.util.Base64;

import com.sfmes.cm.web.KISA_SEED_CBC;

/**
 * @Class Name  : MyBuilderData.java
 * @Description : MyBuilderData 변환 및 가공 Class
 * @Modification Information 
 * @ 
 * @ 수정일        수정자    수정내용 
 * @ --------- --------- ------------------------------- 
 * @ 2020.05.02  이철홍    최초생성
 *   2020.09.10  여다혜    Session Null처리 방법 수정
 *
 * @author 
 * @since 2020.05.02
 * @version 1.0
 * @see Copyright (C) by 모든솔루션 All right reserved.
 */
@Repository("myBuilderData_PDA")
public class MyBuilderData_PDA  {

    private String _DELIM_COL  = "\010";
    private String _DELIM_ROW  = "\014";
    private String _BR         = " <br/>";
    private int _MAX_PARAM_CNT = 100;

    public Map reqMap = new LinkedHashMap<String, String>();
    
    public byte[] key =  { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
    public byte[] iv = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
    public LinkedHashMap<String,String> hm = null;
    // public BASE64Encoder encoder = new BASE64Encoder();   
    public KISA_SEED_CBC cbc = new KISA_SEED_CBC();
    private LinkedHashMap<String, Object> dataMap = new LinkedHashMap();		// 암호화 해제된 데이터맵
    
    // 클래스 생성자 선언
    public MyBuilderData_PDA() {
		// 암호화키를 설정한다.(향후 프로퍼티에서 설정하는 형태로 변경할 것)
		// this.setKey("modernsfmes78700");	//must 16 byte
		// this.setKey("1234567890360787");	//must 16 byte
		// this.setKey("1111111111111111");	//must 16 byte
		this.setKey("1234567890360787");	//must 16 byte
    }
    
    // MyBuilder MSV를 HashMap List로 변환하는 함수
    public List<Map<String, Object>> getParamFromMSVList(String paramVal) throws Exception 
    {
        List<Map<String, Object>> paramList = new LinkedList();
        Object usrinfoObj = null;
        String strGUSRID_VAL = "";
        String strGCORP_VAL = "";
        
        if (paramVal == null) {

            return paramList;
        }
        
//        System.out.println("MyBuilderData paramVal :::" + paramVal);
        
        String[] strLines = paramVal.split(_DELIM_ROW);
        String[] strCols = null;
        String[] strColVals = null;
        
//        System.out.println("MyBuilderData strLines.length :::" + strLines.length);

        for (int i = 0; i < strLines.length; i++) {

            if (i == 0) {
                strCols = strLines[i].split(_DELIM_COL);
                
            } else {
                LinkedHashMap<String, Object> param = new LinkedHashMap<String, Object>();

                strColVals = strLines[i].split(_DELIM_COL);
                

                for (int j = 0; j < strCols.length; j++) {
                    if (strColVals.length > j)
                        param.put(strCols[j], strColVals[j]);
                    else
                        param.put(strCols[j], "");
                }
                param.put("GUSRID", param.get("USR_ID"));
                
                paramList.add(i - 1, param);
            }
            
//            System.out.println("MyBuilderData paramList :::" + paramList);
        }

        return paramList;
    }
    
    // MyBuilder MSV를 HashMap으로 변환
    public LinkedHashMap<String, Object> getParamFromMSVHashMap(String paramVal) throws Exception 
    {
        LinkedHashMap<String, Object> paramMap = new LinkedHashMap<String, Object>();
        
        Object usrinfoObj = null;
        String strGUSRID_VAL = "";
        String strGCORP_VAL = "";
        
        // PDA 에서는 세션정보를 추출할 수 없다.
        usrinfoObj = RequestContextHolder.getRequestAttributes().getAttribute("LOGIN_INFO", RequestAttributes.SCOPE_SESSION);
        
        if(usrinfoObj != null) {
            LoginVO loginVO = (LoginVO)usrinfoObj;

            /* 최초등록자, 최종변경자 설정하기 위하여 세션정보에서 아이디를 추출 */
            strGUSRID_VAL = loginVO.getUSR_ID();
            strGCORP_VAL = loginVO.getCORP_C();
        }
        
        if (paramVal == null || "".equals(paramVal)) {

            return paramMap;
        }
        
        String[] strLines = paramVal.split(_DELIM_ROW);
        
        String[] strCols = null;
        String[] strColVals = null;

        for (int i = 0; i < strLines.length; i++) {
            if (i == 0) {
                strCols = strLines[i].split(_DELIM_COL);
            } else {
                strColVals = strLines[i].split(_DELIM_COL);
                
                int iGap = strLines.length - strColVals.length - 1;

                for (int j = 0; j < strCols.length; j++) {
                    if (strColVals.length > j)
                        paramMap.put(strCols[j], strColVals[j]);
                    else 
                        paramMap.put(strCols[j], "");
                }
                /* 최초등록자, 최종변경자 설정(GUSRID, "아이디") */
                paramMap.put("GUSRID", paramMap.get("USR_ID"));
//                paramMap.put("CORP_C", strGCORP_VAL);
            }
        }

        return paramMap;
    }
        
    // HashMap List에서 데이터 컬럼명과 컬럼값을 뽑아냄
    public StringBuffer getHeaderAndDataFromObject(List<Map<String, Object>> dataList) throws Exception {
        StringBuffer sb = new StringBuffer();
        StringBuffer resultSb = new StringBuffer();

        // 데이터리스트에서 헤더를 추출한다
        if (dataList.size() > 0) {
            Map<String, Object> headerMap = (Map<String, Object>) dataList.get(0);

            int headerCnt = 0;

            Iterator itrHeader = headerMap.keySet().iterator();

            while (itrHeader.hasNext()) {
            	// 컬럼구분자를 설정한다.
                if (headerCnt > 0) {
                    sb.append(_DELIM_COL);
                }
                sb.append((String) itrHeader.next());
                
                headerCnt++;
            }
            
            // 컬럼암호화를 한다.
            resultSb.append(this.Encode(sb.toString()));

            // Row구분자는 암호화 하지 않는다.
            resultSb.append(_DELIM_ROW);

            // 데이터리스트에서 데이터를 추출한다
            for (int i = 0; i < dataList.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) dataList.get(i);
                int counter = 0;
                Iterator itr = map.keySet().iterator();
                // 스트링버퍼를 초기화한다.
                sb.setLength(0);

                while (itr.hasNext()) {
                    String key = (String) itr.next();

                    if (counter > 0) {
                        sb.append(_DELIM_COL);
                    }

                    // 데이터를 추출하고 문자열을 결합한다. 
                    if (map.get(key) != null) {
                        sb.append(map.get(key).toString());
                    }
                    counter++;
                }
                
                // 데이터를 암호화한다.
                resultSb.append(this.Encode(sb.toString()));
                
                // Row구분자는 암호화 하지 않는다.
                resultSb.append(_DELIM_ROW);
            }
        }
        
        return resultSb;
    }

    
    // HashMap List에서 데이터 컬럼명과 컬럼값을 뽑아냄(암호화작업 생략)
    public StringBuffer getHeaderAndDataFromObjectHaveNoEncr(List<Map<String, Object>> dataList) throws Exception {
        StringBuffer sb = new StringBuffer();

        // 데이터리스트에서 헤더를 추출한다
        if (dataList.size() > 0) {
            Map<String, Object> headerMap = (Map<String, Object>) dataList.get(0);

            int headerCnt = 0;

            Iterator itrHeader = headerMap.keySet().iterator();

            while (itrHeader.hasNext()) {
                String headerKey = (String) itrHeader.next();
                
                if (headerCnt > 0) {
                    sb.append(_DELIM_COL);
               }
                sb.append(headerKey);
                
                headerCnt++;
            }

            sb.append(_DELIM_ROW);

            // 데이터리스트에서 데이터를 추출한다
            for (int i = 0; i < dataList.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) dataList.get(i);
                int counter = 0;
                Iterator itr = map.keySet().iterator();

                while (itr.hasNext()) {
                    String key = (String) itr.next();

                    if (counter > 0) {
                        sb.append(_DELIM_COL);
                    }

                    if (map.get(key) != null) {
                        
                        String strTarget = map.get(key).toString();
                        
                        sb.append(strTarget);
                    }
                    counter++;
                }

                sb.append(_DELIM_ROW);
            }
        }
        
        return sb;
    }
    

    // 암호화 키를 설정하는 함수
    private void setKey(String sKey)
    {
    	for (int i=0; i<16; i++)
    	{
    		if (i >= sKey.length()) break;
    		key[i] = (byte)sKey.charAt(i);
    	}
    }

    // 복호화된 파라미터를 get하는 함수
    public String getParam(String item)
    {
//        System.out.println("controller item :::" + item);
        return (String)dataMap.get(item);
    }

    // 복호화된 파라미터를 get하는 함수
//    public String getParam(String item)
//    {
//        byte[] bsql = Base64.getDecoder().decode(item.replace(" ", "+"));
//        
//    	return new String(bsql);
//    }
    
    // 초기 data 파라미터를 설정하고 복호화 하는 함수(request객체이용)
    public void setParam(HttpServletRequest request) {
        // 순차적을 보낸다.
        this.setParam("cid",        request.getParameter("cid"));
        this.setParam("uid",        request.getParameter("uid"));
        this.setParam("cmd",        request.getParameter("cmd"));
        this.setParam("pos",        request.getParameter("pos"));
        this.setParam("mod",        request.getParameter("mod"));
        this.setParam("paramValue", request.getParameter("paramValue"));
        this.setParam("end",        request.getParameter("end"));
        this.setParam("run",        request.getParameter("run"));
        this.setParam("SVCID",      request.getParameter("SVCID"));
        this.setParam("INMSV01",    request.getParameter("INMSV01"));
        this.setParam("INMSV02",    request.getParameter("INMSV02"));
        this.setParam("INMSV03",    request.getParameter("INMSV03"));
        this.setParam("INMSV04",    request.getParameter("INMSV04"));
        this.setParam("INMSV05",    request.getParameter("INMSV05"));
    }

    // 초기 data 파라미터를 설정하고 복호화 하는 함수
    public void setParam(String sql, String req_str)
    {
        if (req_str == null) req_str = "";

        String value = "";
        
        if (sql.indexOf("INMSV") < 0) {
            value = req_str;
        } else {
            byte[] bsql = Base64.getDecoder().decode(req_str.replace("\n", "").replace(" ", "+"));

            value = new String(bsql);
        }
        
        this.dataMap.put(sql, value);
    }

    // 문자열 암호화 함수
    public String Encode(String str)
    {
    	if (str.length() < 1) return str;
    	
    	return str;

//    	System.out.println("PDA str :: " + str);
//    	
//    	byte[] bstr = null;
//    	try { bstr = str.getBytes("utf-8"); } catch(Exception e) {}
//    	
//    	
//    	System.out.println("PDA str :: " + Base64.getEncoder().encodeToString(bstr));
    	//return Base64.getEncoder().encodeToString(bstr);    	
    }
}
