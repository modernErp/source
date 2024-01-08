<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    // 최초설치 시 인증키를 설정한다.
    String validKey = request.getParameter("VALID_KEY");
    
    // 서버시간 구하기_20211214 여다혜 추가
    LocalDate now = LocalDate.now();
    System.out.println("@@ now: " + now);

%>
<HTML><HEAD>
<title>sfmes index</title>
</HEAD>

<script LANGUAGE=JavaScript>
function OnNotify(id) {
    if (id == "WindowClose")
    {
         window.opener = "MyBuilder";
         window.setTimeout(function(){window.open('', '_self', '').close();},1000);
    }
}
</script>

<script LANGUAGE=JavaScript FOR=View1 EVENT=OnNotify(id)>
  OnNotify(id);
</script>


<script language = JavaScript>
function setCookie(name, value, exp) {
	  var date = new Date();
	  date.setTime(date.getTime() + exp*24*60*60*1000);
	  document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
}

function getCookie(name) {
	  var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
	  return value? value[2] : null;
}

function deleteCookie(name) {
	  document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

function OnOpen()
{
	// 인증키를 설정한다.
	var validKey = "<%=validKey%>";
	
	// 인증키가 없는 경우 기본 쿠키를 설정한다.
    alert("validKey" + validKey);
	
	if(validKey == null) {
		validKey = getCookie("CORP_C");
		alert(validKey);
	} else {
	    alert("else");		
		document.cookie = "CORP_C=" + "<%=validKey%>";
	}

    View1.CoreDLL = "MyBuilderControlU.dll";
    View1.SetEnv("C:\\sfmes_test", "http://" + location.host + "/mybuilder/Files", "");
    
    View1.FileName = "mybrowser.mvf";
    
    View1.SetParameter("App.CORP_C", validKey);
    View1.SetParameter("App.ServerIP", location.host);
    View1.ToolBar = false;
    View1.RunMode = 32;
    View1.Run();
    View1.focus();
}
</script>

<BODY onload=OnOpen() leftmargin="0" topmargin="0">
<script src=/mybuilder/MyBuilderViewer32U.js></script>
</BODY>
</HTML>
