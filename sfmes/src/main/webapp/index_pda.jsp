<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    // 최초설치 시 인증키를 설정한다.
    String validKey = request.getParameter("VALID_KEY");
%>
<HTML><HEAD>
<title>E-mes_PDA index</title>
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
//	if(validKey == "null") {
//		validKey = getCookie("CORP_C");
//	} else {
//		document.cookie = "CORP_C=" + "<%=validKey%>";
//	}
	
    View1.CoreDLL = "MyBuilderControlU.dll";
    View1.SetEnv("C:\\E-mes_PDA", "http://211.117.60.137:8080/sfmes/mybuilder_PDA/Files", "");
    View1.FileName = "mybrowser.mvf";
    
    View1.SetParameter("App.CORP_C",validKey);
    View1.SetParameter("App.ServerIP",location.host);
    View1.ToolBar = false;
    View1.RunMode = 32;
    View1.Run();
    View1.focus();
}
</script>

<BODY onload=OnOpen() leftmargin="0" topmargin="0">
<script src=MyBuilderViewer32U.js></script>
</BODY>
</HTML>
