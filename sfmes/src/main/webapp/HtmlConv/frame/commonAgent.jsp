<%@ page language="java" pageEncoding="utf-8"%><%@ page import="java.util.*"%><%!
/*--------------------------------------------------------------------------
 *
 *  Webcrea : ActiveSoft Web Version 7, 0, 2021, 0203, 002
 *
 *  (c) Copyright 2015. ActiveSoft. Co., Ltd.
 *  For details, see the activesoft web site: http://www.activesoft.co.kr
 *
 *--------------------------------------------------------------------------*/
public String AgentCall(javax.servlet.jsp.JspWriter out, Hashtable<String, String> requestParamHash, HttpServletRequest request, HttpServletResponse response)
throws ServletException
{
	String sRet = "";
	String dbid = "";
	String cmd = "";
	String sql = "";
	String url = "";
	String pos = "";
	try
	{
		if(requestParamHash != null)
		{
			if(requestParamHash.containsKey("dbid")) dbid = (String)requestParamHash.get("dbid");
			if(requestParamHash.containsKey("cmd")) cmd = (String)requestParamHash.get("cmd");
			if(requestParamHash.containsKey("sql")) sql = (String)requestParamHash.get("sql");
			if(requestParamHash.containsKey("url")) url = (String)requestParamHash.get("url");
			if(requestParamHash.containsKey("pos")) pos = (String)requestParamHash.get("pos");
		}
	}
	catch (Exception e){}
	return sRet;
}
%>