<%@ page language="java" pageEncoding="utf-8"%><%@ page import="java.util.*,java.io.*,java.text.*"%><%!

/*--------------------------------------------------------------------------
 *
 *  Webcrea : ActiveSoft Web Version 7, 0, 2021, 0203, 002
 *
 *  (c) Copyright 2015. ActiveSoft. Co., Ltd.
 *  For details, see the activesoft web site: http://www.activesoft.co.kr
 *
 *--------------------------------------------------------------------------*/
String g_commonVersion = "7, 0, 2021, 0203, 002";
String g_commonCreateDate = "2021-02-08 09:58:57";

boolean g_bDump=true;
boolean g_bServiceWebINF=false;
String g_servicePath="";
String g_servicePathR="";
String g_serviceFolder="";
String g_serverIp="";
String g_mdiFile="index.jsp";
String g_loginFile="MDI/login.html";
String g_mdiSection="";
String g_mdiList="MDI";
String g_mdiFileList="index.jsp";
String g_loginFileList="MDI/login.html";
String g_noSessionDir="";
String g_noSessionList="";
boolean g_bSessionCheck=false;
boolean g_bSessionShare=true;
int g_sessionTime=0;
String g_sessionKey="";
String g_cookiePath="";
String g_loginId="";

String g_encrypCipherType="RSA";
String g_endSessionMsg="Invalid session";
String g_endSessionPage="";
String g_sysDefaultPage="frame/sysdefault.htmls";
String g_sCharset = "";
String GetURL(String dbid)
{
	String sURL = "";
	int nFind = sURL.indexOf("{ServerIP}");
	if(nFind>0) sURL = sURL.replace("{ServerIP}", g_serverIp);
	return sURL;
}
int GetWebcreaAgent(String dbid)
{
	int nWebcreaAgent = 0;
	return nWebcreaAgent;
}
byte[] Encrypt(String key, byte[] bbuf)
{
	if(key.equals("")) key = "Webcrea";
	int cbKey = key.length();
	int key1[] = new int[257];
	int key2[] = new int[257];
	int i = 0, j = 0;
	for (i=0; i<256; i++) key1[i] = i;
	for (i=0; i<256; i++)
	{
		if (j == cbKey) j = 0;
		key2[i] = key.charAt(j++);
	}
	for (i=0, j=0; i<256; i++)
	{
		j = (j + key1[i] + key2[i]) % 256;
		int c = key1[i];
		key1[i] = key1[j];
		key1[j] = c;
	}
	i = j = 0;
	for (int x=0; x<bbuf.length; x++)
	{
		i = (i + 1) % 256;
		j = (j + key1[i]) % 256;
		int c = key1[i];
		key1[i] = key1[j];
		key1[j] = c;
		int t = (key1[i] + key1[j]) % 256;
		int k = key1[t];
		bbuf[x] = (byte)(bbuf[x] ^ k);
	}
	return bbuf;
}
String RandomChar(int cnt)
{
	String str = "";
	for (int i=1; i<=cnt; i++)
	{
		int rndNum = (int)(Math.random() * 62);
		if(rndNum < 10) str += rndNum;
		else if(rndNum > 35) str += (char)(rndNum + 61);
		else str += (char)(rndNum + 55);
	}
	return str;
}
String RandomCharByDay(int mode)
{
	String str = "";
	java.util.Date today = new java.util.Date();
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
	String currDate = dateFormat.format(today);
	str = currDate;
	return str;
}
int RandomNumber(int min, int max)
{
	int num = 0;
	Random r = new Random();
	num = r.nextInt(max) + min;
	if(num>max) num=num-min;
	return num;
}
ArrayList<String> MySplit(String pStr, String spChar, boolean bTrim)
{
	ArrayList<String> arr = new ArrayList<String>();
	if(pStr.equals(""))
	{
		arr.add("");
		return arr;
	}
	int n = pStr.indexOf(spChar);
	while(true)
	{
		if(n>=0)
		{
			String s = pStr.substring(0, n);
			if(bTrim) s = s.trim();
			arr.add(s);
			pStr = pStr.substring(n+spChar.length());
		}
		else
		{
			if(bTrim) pStr = pStr.trim();
			arr.add(pStr);
			pStr = "";
		}
		if(pStr.equals("")) break;
		n = pStr.indexOf(spChar);
	}
	return arr;
}
String GetLoginPage(String sRequestPage)
{
	String sFindPage = "";
	sRequestPage=sRequestPage.toLowerCase();
	int nDiv = sRequestPage.lastIndexOf(".");
	if(nDiv>0) sRequestPage = sRequestPage.substring(0, nDiv);
	ArrayList<String> arrLoginList = new ArrayList<String>();
	arrLoginList = MySplit(g_loginFileList, ",", true);
	ArrayList<String> arrMDIFileList = new ArrayList<String>();
	arrMDIFileList = MySplit(g_mdiFileList, ",", true);
	for(int i=0; i<arrLoginList.size(); i++)
	{
		String sMDI = arrMDIFileList.get(i);
		String sLogin = arrLoginList.get(i);
		sMDI = sMDI.trim();
		nDiv = sMDI.lastIndexOf(".");
		if(nDiv>0) sMDI = sMDI.substring(0, nDiv);
		sMDI=sMDI.toLowerCase();
		if(sRequestPage.equals(sMDI))
		{
			sFindPage = sLogin;
			break;
		}
	}
	if(sFindPage.equals("")) sFindPage = g_loginFile;
	return sFindPage;
}
String GetMDIPage(HttpServletRequest request)
{
	String sFindPage = "";
	String sRequestPage = GetRequestFilePage(request, true);
	sRequestPage=sRequestPage.toLowerCase();
	ArrayList<String> arrLoginList = new ArrayList<String>();
	arrLoginList = MySplit(g_loginFileList, ",", true);
	ArrayList<String> arrMDIFileList = new ArrayList<String>();
	arrMDIFileList = MySplit(g_mdiFileList, ",", true);
	if(g_mdiSection == null) g_mdiSection = "";
	HttpSession session = request.getSession(true);
	session.setAttribute("webcrea.mdisection", g_mdiSection);
	if(!g_mdiSection.equals(""))
	{
		ArrayList<String> arrMDIList = new ArrayList<String>();
		arrMDIList = MySplit(g_mdiList, ",", true);
		for(int i=0; i<arrMDIList.size(); i++)
		{
			String sMDI = arrMDIFileList.get(i);
			String sMDISection = arrMDIList.get(i);
			if(g_mdiSection.equals(sMDISection))
			{
				sFindPage = sMDI;
				break;
			}
		}
		return sFindPage;
	}
	for(int i=0; i<arrLoginList.size(); i++)
	{
		String sMDI = arrMDIFileList.get(i);
		String sLogin = arrLoginList.get(i);
		String sLoginOrg = sLogin;
		sLogin = sLogin.trim();
		int nDiv = sLogin.lastIndexOf(".");
		if(nDiv>0) sLogin = sLogin.substring(0, nDiv);
		sLogin=sLogin.toLowerCase();
		int nChk = sRequestPage.indexOf(sLogin);
		if(nChk>=0)
		{
			int cnt = sRequestPage.lastIndexOf(sLogin);
			String sLoginChk = sRequestPage.substring(cnt);
			if(!sLogin.equals(sLoginChk)) continue;
			g_loginFile = sLoginOrg;
			sFindPage = sMDI;
			break;
		}
	}
	if(sFindPage.equals("")) sFindPage = g_mdiFile;
	return sFindPage;
}
String GetRequestFilePage(HttpServletRequest request, boolean bFolder)
{
	String sRet = "";
	String sRequestPagePath = request.getHeader("REFERER");
	String sContextPath = GetHostFullUrl(request);
	if(sRequestPagePath != null && !sRequestPagePath.equals(""))
	{
		String sPath = "";
		int n = sRequestPagePath.indexOf(sContextPath);
		if(n>=0) sPath = sRequestPagePath.substring(n + sContextPath.length() + 1);
		else
		{
			int nPort = request.getServerPort();
			if(nPort>0)
			{
				String sPort = ":" + nPort;
				int n1 = sContextPath.indexOf(sPort);
				int n2 = sRequestPagePath.indexOf(sPort);
				if(n1>0 && n2<0)
				{
					if(n1>0) sContextPath = sContextPath.replaceAll(sPort, "");
					int n3 = sRequestPagePath.indexOf(sContextPath);
					if(n3>=0) sPath = sRequestPagePath.substring(n3 + sContextPath.length() + 1);
				}
			}
		}
		if(sPath.equals("")) return sRet;
		int nDiv = sPath.lastIndexOf(".");
		if(nDiv>0) sPath = sPath.substring(0, nDiv);
		if(!bFolder)
		{
			nDiv = sPath.lastIndexOf("/");
			if(nDiv>0) sPath = sPath.substring(nDiv+1);
		}
		sRet = sPath;
	}
	return sRet;
}
boolean IsNoSessionList(HttpServletRequest request)
{
	boolean bRet = false;
	if(g_noSessionDir.equals("") && g_noSessionList.equals("")) return bRet;
	String sRequestDir = "";
	String sRequestFile = GetRequestFilePage(request, true);
	if(sRequestFile.equals("")) return bRet;
	int nDir = sRequestFile.lastIndexOf("/");
	if(nDir>0)
	{
		sRequestDir = sRequestFile.substring(0, nDir);
		sRequestFile = sRequestFile.substring(nDir+1);
	}
	if(!g_noSessionDir.equals("") && !sRequestDir.equals(""))
	{
		sRequestDir=sRequestDir.toLowerCase();
		ArrayList<String> arrNoSessionDir = new ArrayList<String>();
		arrNoSessionDir = MySplit(g_noSessionDir, ",", true);
		for(int i=0; i<arrNoSessionDir.size(); i++)
		{
			String sDir = arrNoSessionDir.get(i);
			sDir = sDir.trim();
			sDir=sDir.toLowerCase();
			if(sRequestDir.indexOf(sDir)==0)
			{
				bRet = true;
				break;
			}
		}
	}
	if(bRet) return true;
	sRequestFile=sRequestFile.toLowerCase();
	ArrayList<String> arrNoSessionList = new ArrayList<String>();
	arrNoSessionList = MySplit(g_noSessionList, ",", true);
	for(int i=0; i<arrNoSessionList.size(); i++)
	{
		String sList = arrNoSessionList.get(i);
		sList = sList.trim();
		int nDiv = sList.lastIndexOf(".");
		if(nDiv>0) sList = sList.substring(0, nDiv);
		sList=sList.toLowerCase();
		if(sRequestFile.equals(sList))
		{
			bRet = true;
			break;
		}
	}
	return bRet;
}
boolean IsLoginCheck()
{
	return !g_loginFile.equals("");
}
boolean ConfirmLogin(HttpServletRequest request, String loginId, boolean bNoSessionListCheck)
{
	if(!g_bSessionCheck) return true;
	if(bNoSessionListCheck && IsNoSessionList(request)) return true;
	HttpSession session = request.getSession(true);
	String addKey = "Webcrea";
	String info2 = "";
	String keyInfo1 = "";
	Cookie[] c = request.getCookies();
	if(c == null) return false;
	String infoKey = g_sessionKey + loginId;
	String infoId1 = "wl" + infoKey;
	String infoId2 = "wk" + infoKey;
	for(int i=0; i<c.length; i++)
	{
		if(info2.equals("") && c[i].getName().equals(infoId1)) info2=c[i].getValue();
		if(keyInfo1.equals("") && c[i].getName().equals(infoId2)) keyInfo1=c[i].getValue();
		if(!info2.equals("") && !keyInfo1.equals("")) break;
	}
	String info1 = (String)session.getAttribute(infoId1);
	String keyInfo2 = (String)session.getAttribute(infoId2);
	if(info1 == null || keyInfo1 == null || info2 == null || keyInfo2 == null) return false;
	if(info1.equals("") || keyInfo1.equals("") || info2.equals("") || keyInfo2.equals("")) return false;

	try{
		String loginInfo1 = "";
		String loginInfo2 = "";
		BASE64Decoder decoder = new BASE64Decoder();
		for(int i=0; i<2; i++)
		{
			String keyInfo = "";
			String info = "";
			if(i==0)
			{
				keyInfo = keyInfo1;
				info = info1;
			}
			else
			{
				keyInfo = keyInfo2;
				info = info2;
			}
			byte[] bstrKeyInfo = decoder.decodeBuffer(keyInfo);
			keyInfo = new String(bstrKeyInfo);
			int nCnt = Integer.valueOf(keyInfo.substring(0, 1));
			String key = keyInfo.substring(1, nCnt+1);
			String keyEncrypt = keyInfo.substring(nCnt+1);

			byte[] bstrKey = decoder.decodeBuffer(keyEncrypt);
			byte[] bstrKeyDecrypt = Encrypt(key, bstrKey);
			String keyDecrypt = new String(bstrKeyDecrypt);
			key = keyDecrypt.substring(1) + addKey;

			byte[] bstrInfo = decoder.decodeBuffer(info);
			byte[] bstrInfoDecrypt = Encrypt(key, bstrInfo);
			String infoDecrypt = new String(bstrInfoDecrypt);
			if(i==0) loginInfo1 = infoDecrypt;
			else loginInfo2 = infoDecrypt;
		}
		if(!loginInfo1.equals("") && loginInfo1.equals(loginInfo2))
		{
			g_loginId = loginId;
			return true;
		}
	}catch (IOException e){}
	g_loginId = "";
	return false;
}
boolean IsLocalUrl(HttpServletRequest request)
{
	boolean bLocal = false;
	String sUrl = request.getRequestURI();
	int n = sUrl.indexOf("127.0.0.1");
	if(n<0) n = sUrl.indexOf("localhost");
	if(n>0) bLocal = true;
	return bLocal;
}
String GetHostFullUrl(HttpServletRequest request)
{
	String sUrl = request.getRequestURL().toString();
	int n = sUrl.lastIndexOf("/");
	if(n>0) sUrl = sUrl.substring(0, n);
	n = sUrl.indexOf("/frame");
	if(n>0) sUrl = sUrl.substring(0, n);
	return sUrl;
}
String GetHostUrl(HttpServletRequest request)
{
	String sUrl = request.getRequestURI();
	int n = sUrl.lastIndexOf("/");
	if(n>0) sUrl = sUrl.substring(0, n);
	n = sUrl.indexOf("/frame");
	if(n>0) sUrl = sUrl.substring(0, n);
	return sUrl;
}
String GetHostPage(HttpServletRequest request)
{
	String sUrl = request.getRequestURI();
	int n = sUrl.lastIndexOf("/");
	if(n>0) sUrl = sUrl.substring(n+1);
	n = sUrl.indexOf("?");
	if(n>0) sUrl = sUrl.substring(0, n);
	return sUrl;
}
void MoveLoginPage(HttpServletRequest request, HttpServletResponse response)
{
	try {
		String sUrl = GetHostUrl(request);
		sUrl = sUrl + "/" + g_loginFile;
		String sCache = "?v=";
		sCache += RandomCharByDay(0);
		sUrl += sCache;
		response.sendRedirect(sUrl);
	}catch (IOException e){}
}
String GetCommonPath(HttpServletRequest request, String version, String dir, String form, String appServiceDir, int mode)
{
	boolean bLocal = IsLocalUrl(request);
	boolean bFixServicePath = false;
	String sPath = request.getRealPath("");
	sPath = sPath.replaceAll("\\\\", "/");
	if(g_servicePath.equals(""))
	{
		if(!sPath.equals("") && sPath.substring(sPath.length()-1).equals("/")) sPath = sPath.substring(0, sPath.length()-1);
		if(g_bServiceWebINF)
		{
			sPath = sPath + "/WEB-INF";
		}
		else
		{
			String sContextPath = request.getContextPath();
			int n = sPath.indexOf(sContextPath);
			if(n>0) sPath = sPath.substring(0, n);
			n = sPath.indexOf("ROOT");
			if(n>0)
			{
				sPath = sPath.substring(0, n);
				n = sPath.lastIndexOf("/");
				if(n>0) sPath = sPath.substring(0, n);
			}
			n = sPath.lastIndexOf("/");
			if(n>0) sPath = sPath.substring(0, n);
		}
	}
	else
	{
		bFixServicePath = true;
		if(bLocal) sPath = g_servicePath;
		else sPath = g_servicePathR;
	}
	String sServiceFolder = g_serviceFolder;
	String sFullPath = sPath + "/WebcreaService" + version;
	if(!g_serviceFolder.equals(""))
	{
		int nFind = sServiceFolder.indexOf("{ServiceDir}");
		if(nFind>=0)
		{
			sServiceFolder = sServiceFolder.replaceAll("\\\\", "/");
			sServiceFolder = sServiceFolder.replace("{ServiceDir}", appServiceDir);
			if(appServiceDir.equals("") && !sServiceFolder.equals(""))
			{
				String sLast = sServiceFolder.substring(sServiceFolder.length()-1, sServiceFolder.length());
				if(sLast.equals("/")) sServiceFolder = sServiceFolder.substring(0, sServiceFolder.length()-1);
			}
		}
		sFullPath = sFullPath + "/" + sServiceFolder;
	}
	if(!dir.equals("") && mode != 1) sFullPath = sFullPath + "/" + dir;
	String sFileName = "";
	if(mode==1) sFileName = "common.webcreaInfo";
	else sFileName = form + ".webcrea";
	sFullPath = sFullPath + "/" + sFileName;

	File file = new File(sFullPath);
	if(!file.exists())
	{
		String sLogPath = "Runtime : " + sFullPath;
		boolean bFind = false;
		if(bFixServicePath)
		{
			if(bLocal) sPath = g_servicePathR;
			else sPath = g_servicePath;
			sFullPath = sPath + "/WebcreaService" + version;
			if(!g_serviceFolder.equals("")) sFullPath = sFullPath + "/" + sServiceFolder;
			if(!dir.equals("")) sFullPath = sFullPath + "/" + dir;
			sFullPath = sFullPath + "/" + form + ".webcrea";
			file = new File(sFullPath);
			if(file.exists()) bFind = true;
			else sLogPath = sLogPath + ", Local : " + sFullPath;
		}
		if(!bFind)
		{
			if(g_bDump) sFullPath = "[Error] " + sLogPath;
			else sFullPath = "";
		}
	}
	return sFullPath;
}
String GetSecurityPath(HttpServletRequest request, String version, String dir, String form, String appServiceDir)
{
	return GetCommonPath(request, version, dir, form, appServiceDir, 0);
}
boolean ClearSession(HttpServletRequest request, HttpServletResponse response, String loginId)
{
	HttpSession session = request.getSession(true);
	String infoKey = g_sessionKey + loginId;
	String infoId1 = "wl" + infoKey;
	String infoId2 = "wk" + infoKey;
	boolean bClose = true;
	Cookie[] c = request.getCookies();
    if(c != null)
	{
        for(int i=0; i < c.length; i++)
		{
			String cName = c[i].getName();
			if(cName.indexOf("wl")==0 || cName.indexOf("wk")==0)
			{
				if(cName.equals(infoId1) || cName.equals(infoId2))
				{
					c[i].setMaxAge(0);
					if(g_cookiePath.equals("")) c[i].setPath(GetHostUrl(request));
					else c[i].setPath(g_cookiePath);
					response.addCookie(c[i]) ;
				}
				else bClose = false;
			}
		}
    }
	session.removeAttribute(infoId1);
	session.removeAttribute(infoId2);
	session.removeAttribute("_my_rsa_key_");
	session.removeAttribute("webcrea.ssoId");
	session.removeAttribute("webcrea.mdisection");
	if(bClose) session.invalidate();
	return true;
}
boolean SetSession(HttpServletRequest request, HttpServletResponse response, String userid, String password, int sessionTime)
{
	if(!g_bSessionCheck) return true;
	HttpSession session = request.getSession(true);
	BASE64Encoder encoder = new BASE64Encoder();

	String infoKey = g_sessionKey + userid;
	String infoId1 = "wl" + infoKey;
	String infoId2 = "wk" + infoKey;

	String info = password + "" + userid;
	String addKey = "Webcrea";
	for(int i=0; i<2; i++)
	{
		int nCnt = RandomNumber(4, 9);
		String keyA1 = RandomChar(nCnt);
		String keyA2 = RandomChar(nCnt);

		String key = keyA2 + addKey;
		byte[] bstrInfo = Encrypt(key, info.getBytes());
		String infoEncrypt = encoder.encode(bstrInfo);

		String keyInfo = nCnt + keyA2;
		byte[] bstrKeyInfo = Encrypt(keyA1, keyInfo.getBytes());
		String keyInfoEncrypt = encoder.encode(bstrKeyInfo);
		String keyFullInfo = nCnt + keyA1 + keyInfoEncrypt;
		keyFullInfo = encoder.encode(keyFullInfo.getBytes());
		if(i==0)
		{
			session.setAttribute(infoId1, infoEncrypt);
			Cookie cookie = new Cookie(infoId2, keyFullInfo);
			if(g_cookiePath.equals("")) cookie.setPath(GetHostUrl(request));
			else cookie.setPath(g_cookiePath);
			response.addCookie(cookie);
		}
		else
		{
			session.setAttribute(infoId2, keyFullInfo);
			Cookie cookie = new Cookie(infoId1, infoEncrypt);
			if(g_cookiePath.equals("")) cookie.setPath(GetHostUrl(request));
			else cookie.setPath(g_cookiePath);
			response.addCookie(cookie);
		}
	}
	if(sessionTime != 0)
	{
		if(sessionTime>0) sessionTime *= 60;
		session.setMaxInactiveInterval(sessionTime);
	}
	return true;
}
boolean IsSqlFunction(String s)
{
	boolean bRet = false;
	String[] arr = {"abs", "ascii", "avg", "ceiling", "ceiling", "concat", "count"
					, "curdate", "current_date", "current_time", "current_timestamp", "curtime"
					, "database", "date_add", "date_format", "date_sub", "dayname", "dayofmonth", "dayofweek", "dayofyear"
					, "floor", "format", "from_days", "greatest", "greatest"
					, "insert", "instr", "interval", "lcase", "least", "left", "ltrim"
					, "max", "mid", "min", "mod", "month", "monthname", "now", "over", "password", "pow", "power"
					, "rank", "replace", "reverse", "right", "round", "rtrim", "row_number", "substr", "substring", "sum", "sysdate"
					, "to_days", "trim", "truncate", "truncate", "ucase", "week", "weekday", "year"};
	String s1 = s.toLowerCase();
	for(int i=0; i<arr.length; i++)
	{
		if(arr[i].equals(s1))
		{
			bRet = true;
			break;
		}
	}
	if(!bRet)
	{
		String sSqlFunction = "";
		sSqlFunction = sSqlFunction.trim();
		if(!sSqlFunction.equals(""))
		{
			ArrayList<String> arrSqlFunction = new ArrayList<String>();
			arrSqlFunction = MySplit(sSqlFunction, ",", true);
			for(int i=0; i<arrSqlFunction.size(); i++)
			{
				String sCom = arrSqlFunction.get(i).toLowerCase();
				if(sCom.equals(s1))
				{
					bRet = true;
					break;
				}
			}
		}
	}
	return bRet;
}
boolean IsSqlFilter(String sql, commonInfo objInfo, javax.servlet.jsp.JspWriter out)
{
	boolean bRet = false;
	try
	{
		for(int i=0; i<objInfo.arrSqlFilter.size(); i++)
		{
			String sSqlFilter = objInfo.arrSqlFilter.get(i);
			int n = sql.indexOf(sSqlFilter);
			if(n>=0)
			{
				bRet = true;
				break;
			}
		}
		if(bRet)
		{
			String sSqlFilterMsg = objInfo.sSqlFilterMsg;
			if(sSqlFilterMsg.equals("")) sSqlFilterMsg = "Contains unacceptable words.";
			System.out.println("[Error] " + sSqlFilterMsg);
			out.print("[Error] " + sSqlFilterMsg);
		}
	} catch(IOException e) {}
	return bRet;
}
private class commonInfo
{
	String sSqlFilterMsg = "";
	ArrayList<String> arrSqlFilter = new ArrayList<String>();
	public boolean loadInfo(String sPath)
	{
		if(sPath.equals("")) return true;
		try{
			FileInputStream info=new FileInputStream(sPath);
			InputStreamReader fr=new InputStreamReader(info, "UTF-8");
			BufferedReader reader = new BufferedReader(fr);
			String line = "";
			while ((line = reader.readLine()) != null) {
				int nKey = line.indexOf("=");
				if(nKey<=0) continue;
				String sKey = line.substring(0, nKey);
				sKey = sKey.trim();
				if(sKey.equals("SqlFilterMsg"))
				{
					String sValue = line.substring(nKey+1);
					sSqlFilterMsg = sValue.trim();
				}
				else if(sKey.equals("SqlFilter"))
				{
					String sValue = line.substring(nKey+1);
					sValue = sValue.trim();
					if(!sValue.equals("")) arrSqlFilter = MySplit(sValue, ",", true);
				}
			}
			reader.close();
			fr.close();
		} catch (IOException e) {}
		return true;
	}
}
%>