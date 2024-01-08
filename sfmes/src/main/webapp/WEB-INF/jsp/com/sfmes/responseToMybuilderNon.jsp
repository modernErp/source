<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@page import="org.apache.taglibs.standard.lang.jstl.EnumeratedMap"%><%@page import="com.sfmes.cm.web.MyBuilderData"%><%@page import="java.util.*"%><%

    String _DELIM_COL  = "\010";
    String _DELIM_ROW  = "\014";
    MyBuilderData myBld = new MyBuilderData();
    StringBuffer sbMSV = new StringBuffer();

    // result결과가 존재하는 경우 메시지 처리한다.
    if ( request.getAttribute("result") != null )
    {
        String strVal = null;
        // 결과값 암호화를 한다.(resultMsg와 행구분자는 암호화를 제외한다.)
        sbMSV.append("{{{+resultMsg+}}}");
        sbMSV.append(_DELIM_ROW);
        sbMSV.append("msgCode" + _DELIM_COL + "msgName" + _DELIM_COL + "returnValue01" + _DELIM_COL + "returnValue02");
        sbMSV.append(_DELIM_ROW);
        
        // 리턴값이 존재하는 경우 문자열 편집 후 암호화한다.
        strVal = "OK" + _DELIM_COL + "정상적으로 처리 되었습니다." + _DELIM_COL;
        
        // 저장 후 리턴할 값이 있는 경우 추가 설정한다.
        if ( request.getAttribute("returnValue01") != null ) {
            strVal = strVal + request.getAttribute("returnValue01").toString();
        }
        
        strVal = strVal + _DELIM_COL;
        
        if ( request.getAttribute("returnValue02") != null ) {
            strVal = strVal + request.getAttribute("returnValue02").toString();
        }
        
        sbMSV.append(strVal);

    } else if( request.getAttribute("resultList") != null ) {
        // List 결과가 존재하는 경우 MSV타입으로 치환하여 리턴한다.
        List<Map<String, Object>> resultList = (List<Map<String, Object>>)request.getAttribute("resultList");
        
        // 리스트 객체가 비어 있는 경우
        if(resultList.isEmpty() == true) {
            sbMSV.append("{{{+resultMsg+}}}");
            sbMSV.append(_DELIM_ROW);
            sbMSV.append("msgCode" + _DELIM_COL + "msgName" + _DELIM_COL + "returnValue01" + _DELIM_COL + "returnValue02");
            sbMSV.append(_DELIM_ROW);
            sbMSV.append("NOT_FOUND" + _DELIM_COL + "조회 된 결과가 없습니다.");
        } else {
         
            // List객체를 먼저 출력한다.
            sbMSV.append(myBld.getHeaderAndDataFromObject(resultList));
            
            // 결과 메시지를 출력한다.
            sbMSV.append("{{{+resultMsg+}}}");
            sbMSV.append(_DELIM_ROW);
            sbMSV.append("msgCode" + _DELIM_COL + "msgName" + _DELIM_COL + "returnValue01" + _DELIM_COL + "returnValue02");
            sbMSV.append(_DELIM_ROW);
            sbMSV.append("LIST_OK" + _DELIM_COL + "정상적으로 조회되었습니다.");
        }
    }
    out.print(sbMSV.toString());
    out.flush();
%>