package com.sfmes.cm.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// import sun.misc.BASE64Decoder;
// import sun.misc.BASE64Encoder;

import java.util.Base64;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @Class Name  : MyBuilderData.java
 * @Description : MyBuilderData 변환 및 가공 Class
 * @Modification Information 
 * @ 
 * @ 수정일        수정자    수정내용 
 * @ --------- --------- ------------------------------- 
 * @ 2020.05.02  이철홍    최초생성
 *   2020.09.10  여다혜    Session Null처리 방법 수정
 * @ 2021.11.14  여다혜    서버시간 추가
 *
 * @author 
 * @since 2020.05.02
 * @version 1.0
 * @see Copyright (C) by 모든솔루션 All right reserved.
 */
@Repository("myBuilderData")
public class MyBuilderData  {

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
    public MyBuilderData() {
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
        String strGSVR_DATE = "";
        
        // 세션정보를 추출한다.
        usrinfoObj = RequestContextHolder.getRequestAttributes().getAttribute("LOGIN_INFO", RequestAttributes.SCOPE_SESSION);
        
        if(usrinfoObj != null) {
        	
            LoginVO loginVO = (LoginVO)usrinfoObj;

            /* 최초등록자, 최종변경자 설정하기 위하여 세션정보에서 아이디를 추출 */
            strGUSRID_VAL = loginVO.getUSR_ID();   //USR ID 
            strGCORP_VAL = loginVO.getCORP_C();    //CORP_C
            strGSVR_DATE = loginVO.getSVR_DATE();  //서버날짜 추가_20211114
        }
        
        if (paramVal == null) {

            return paramList;
        }
        
        String[] strLines = paramVal.split(_DELIM_ROW);
        String[] strCols = null;
        String[] strColVals = null;

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
                /* 최초등록자, 최종변경자 설정(GUSRID, "아이디") */
                param.put("GUSRID"  , strGUSRID_VAL);
                param.put("CORP_C"  , strGCORP_VAL);
                param.put("SVR_DATE", strGSVR_DATE);
                paramList.add(i - 1, param);
            }
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
        String strGSVR_DATE = "";

        
        // 세션정보를 추출한다.
        usrinfoObj = RequestContextHolder.getRequestAttributes().getAttribute("LOGIN_INFO", RequestAttributes.SCOPE_SESSION);
        
        if(usrinfoObj != null) {
            LoginVO loginVO = (LoginVO)usrinfoObj;

            /* 최초등록자, 최종변경자 설정하기 위하여 세션정보에서 아이디를 추출 */
            strGUSRID_VAL = loginVO.getUSR_ID();
            strGCORP_VAL = loginVO.getCORP_C();
            strGSVR_DATE = loginVO.getSVR_DATE();
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
                paramMap.put("GUSRID", strGUSRID_VAL);
                paramMap.put("CORP_C", strGCORP_VAL);
                paramMap.put("SVR_DATE", strGSVR_DATE);
                
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
    	return (String)dataMap.get(item);
    }
    
    // 초기 data 파라미터를 설정하고 복호화 하는 함수(request객체이용)
    public void setParam(HttpServletRequest request) {
//        System.out.println("NonPDA setParam  request.data :: " + request.getParameter("data"));
        
        this.setParam(request.getParameter("data"));
    }

    // 초기 data 파라미터를 설정하고 복호화 하는 함수
    public void setParam(String sql)
    {
    	if (sql.length() < 1) return;

    	try {
//    	    System.out.println("NonPDA sql :: " + sql);
    		
    		// BASE64Decoder decoder = new BASE64Decoder();
    		// byte[] bsql = decoder.decodeBuffer(sql.replace(" ", "+"));
    		byte[] bsql = Base64.getDecoder().decode(sql.replace(" ", "+"));
    		// byte[] bsql = decoder.decodeBuffer(sql);
//    		System.out.println("NonPDA bsql :: " + bsql);
    		
    		byte[] bstr = cbc.SEED_CBC_Decrypt(key, iv, bsql, 0, bsql.length);
    		String str = new String(bstr);
    		
//    		System.out.println("NonPDA str :: " + str);
    		
    		StringTokenizer st = new StringTokenizer(str, "&");
    		
    		while (st.hasMoreTokens())
    		{
    			String thisToken = st.nextToken();
    			StringTokenizer st2 = new StringTokenizer(thisToken, "=");
    			String param = st2.nextToken();
    			String value = "";
    			
    			try { value = st2.nextToken(); } catch(Exception e) { value = ""; }
    			
    			// 복호화 후 데이터맵에 설정한다.
    			this.dataMap.put(param, Decode(value));
    		}
    		
    		LocalDateTime ld = LocalDateTime.now(); 
    		String sdt = ld.format(DateTimeFormatter.ISO_DATE_TIME);
    		
    		System.out.println("[" + sdt.replace("T", " ") + "] " + "dataMap :: " + this.dataMap);
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}

    }
    
    // 문자열 복호화 함수
    public String Decode(String str) {
    	if (str.length() < 1) return str;

    	byte[] bstr = new byte[str.length()+1];
    	int i = 0, j = 0;
    	for (i=0; i<str.length(); i++) {
    		if (str.charAt(i) == '+') {
    			bstr[j++] = (byte)' ';
    		}
    		else if (str.charAt(i) != '%') {
    			bstr[j++] = (byte)str.charAt(i);
    		}
    		else {
    			int c = getHexNibble(str.charAt(++i)) * 16;
    			c += getHexNibble(str.charAt(++i));
    			bstr[j++] = (byte)c;
    		}
    	}
    	String ret = "";
    	try { ret = new String(bstr, 0, j, "utf-8"); } catch(Exception e) {}
    	return ret;
    }

    public byte getHexNibble(char c)
    {
    	if(c >= '0' && c<='9') return (byte)(c - '0');
    	if(c >= 'A' && c<='F') return (byte)(c - 'A' + 10);
    	if(c >= 'a' && c<='f') return (byte)(c - 'a' + 10);
    	return 0;
    }

    // 문자열 암호화 함수
    public String Encode(String str)
    {
    	if (str.length() < 1) return str;

    	byte[] bstr = null;
    	try { bstr = str.getBytes("utf-8"); } catch(Exception e) {}
    	byte[] bret = cbc.SEED_CBC_Encrypt(key, iv, bstr, 0, bstr.length);
    	// return encoder.encodeBuffer(bret);
    	return Base64.getEncoder().encodeToString(bret);
    }
}
