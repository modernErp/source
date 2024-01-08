<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@page import="com.sfmes.cm.web.MyBuilderData"%><%@page import="java.util.*"%><%

   // 마이빌더 형태 오류 처리를 위한 정의 
   String _DELIM_COL  = "\010";
   String _DELIM_ROW  = "\014";
   MyBuilderData myBld = new MyBuilderData();
   StringBuffer sbMSV = new StringBuffer();
   
   // 프레임웍에서 발생한 오류를 추출한다.
   Throwable exception = (Throwable)request.getAttribute("exception");
   
   String strVal = null;
   // 결과값 암호화를 한다.(resultMsg와 행구분자는 암호화를 제외한다.)
   sbMSV.append("{{{+resultMsg+}}}");
   sbMSV.append(_DELIM_ROW);
   sbMSV.append(myBld.Encode("msgCode" + _DELIM_COL + "msgName"));
   sbMSV.append(_DELIM_ROW);
   
   String strMsg = exception.getMessage();
   
   // 오류메시지가 INFO인지 오류인지 확인한다.
   if(strMsg.indexOf("INFO") > -1) {
	   strVal = "INFO";
   } else {
       strVal = "ERROR";
   }
   
   strVal = strVal + _DELIM_COL + strMsg + _DELIM_COL;
   
   // 편집된 문자열을 암호화한다.
   sbMSV.append(myBld.Encode(strVal));
   
   // 오류 결과를 출력한다.
   out.print(sbMSV.toString());
   out.flush();
%>
