<%@ page language="java" contentType="text/html; charset=euc-kr" pageEncoding="utf-8"%><%@ page import="java.util.*,java.io.*,java.net.*"%><%@ page import="sun.misc.*,javax.crypto.*,java.security.*,java.security.spec.*,javax.net.ssl.*"%><%@page session = "true"%><%@include file="common.jsp"%><%@include file="commonAgent.jsp"%><%
/*--------------------------------------------------------------------------
 *
 *  Webcrea : ActiveSoft Web Version 7, 0, 2021, 0203, 002
 *
 *  (c) Copyright 2015. ActiveSoft. Co., Ltd.
 *  For details, see the activesoft web site: http://www.activesoft.co.kr
 *
 *--------------------------------------------------------------------------*/
String g_crossVersion = "7, 0, 2021, 0203, 002";
String g_crossCreateDate = "2021-02-08 09:51:32";

String charset = "";
String url = "";
String agent = "";

String dbid = "";
String cid = "";
String uid = "";
String cmd = "";
String pos = "";
String mod = "";
String sql = "";
String findfile = "";
String form = "";
String dir = "";
String version = "";
String sqlIndex = "";
String paramIndex = "";
String urlIndex = "";
String urlParamInfo = "";
String sqlParamInfo = "";
String allParam = "";
String appServerIP = "";
String appServiceDir = "";

String loginId = "";
boolean bLoginStart=false;
boolean bLoginSession=false;
Hashtable<String, String> paramHash = new Hashtable<String, String>();
boolean bEncrypt=false;
allParam = request.getParameter("mywebb");
if(allParam != null && !allParam.equals("")) bEncrypt = true;
if(bEncrypt)
{
	PrivateKey privateKey = (PrivateKey)session.getAttribute("_my_rsa_key_");
	int nLoop = allParam.length();
	boolean bFirstSession = session.isNew();
	if(bFirstSession)
	{
		if(!g_endSessionPage.equals(""))
		{
			String sEndSessionMsg = "Invalid Encrypt Key";
			System.out.println("[End Session] " + sEndSessionMsg);
			out.print("[End Session] " + sEndSessionMsg);
		}
		else
		{
			String sEndSessionMsg = "Invalid Encrypt Key";
			System.out.println("[End Session Msg] " + sEndSessionMsg);
			out.print("[End Session Msg] " + sEndSessionMsg);
		}
		return;
	}
	String decrypt="";
	try{
		Cipher cipher = Cipher.getInstance(g_encrypCipherType);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		String[] arrEncrypt = allParam.split(",");
		for(int i=0; i<arrEncrypt.length;i++)
		{
			String param = arrEncrypt[i];
			byte[] encryptedBytes = HexToByteArray(param);
			byte[] sBytes = cipher.doFinal(encryptedBytes);
			param = new String(sBytes, "UTF-8");
			decrypt += param;
		}
	}catch(Exception e){
		System.out.println("[Error] crossurl.jsp (" + e.getMessage() + ")");
		out.print("[Error] crossurl.jsp (" + e.getMessage() + ")");
		return;
	}
	allParam = decrypt;
}
else
{
	Enumeration<String> eParam = request.getParameterNames();
	while (eParam.hasMoreElements()) {
		String pName = (String)eParam.nextElement();
		String pValue = request.getParameter(pName);

		if(pName.equals("dbid")) dbid = pValue;
		else if(pName.equals("charset")) charset = pValue;
		else if(pName.equals("url")) url = pValue;
		else if(pName.equals("agent")) agent = pValue;
		else if(pName.equals("cid")) cid = pValue;
		else if(pName.equals("uid")) uid = pValue;
		else if(pName.equals("cmd")) cmd = pValue;
		else if(pName.equals("pos")) pos = pValue;
		else if(pName.equals("mod")) mod = pValue;
		else if(pName.equals("sql")) sql = new String(pValue.getBytes("8859_1"));
		else if(pName.equals("findfile")) findfile = new String(pValue.getBytes("8859_1"));
		else if(pName.equals("form")) form = new String(pValue.getBytes("8859_1"));
		else if(pName.equals("dir")) dir = new String(pValue.getBytes("8859_1"));
		else if(pName.equals("version")) version = pValue;
		else if(pName.equals("_my_loginid")) loginId = new String(pValue.getBytes("8859_1"));
		else if(pName.equals("sqlIndex")) sqlIndex = pValue;
		else if(pName.equals("paramIndex")) paramIndex = pValue;
		else if(pName.equals("urlIndex")) urlIndex = pValue;
		else if(pName.equals("urlParamInfo")) urlParamInfo = new String(pValue.getBytes("8859_1"));
		else if(pName.equals("sqlParamInfo")) sqlParamInfo = new String(pValue.getBytes("8859_1"));
		else if(pName.equals("{ServerIP}")) appServerIP = pValue;
		else if(pName.equals("{ServiceDir}")) appServiceDir = pValue;
		else if(pName.equals("myweb"))
		{
			pValue = pValue.replaceAll(" ", "+");
			allParam = pValue;
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bstr = decoder.decodeBuffer(allParam);
			allParam = new String(bstr);
			allParam = URLDecoder.decode(allParam, "UTF-8");
		}
		else paramHash.put(pName, new String(pValue));
	}
}

int nLoop = 0;
if(allParam != null && !allParam.equals("")) nLoop = allParam.length();
while(nLoop>0)
{
	int n = allParam.indexOf("&");
	String param = "";
	if(n<0)
	{
		param = allParam;
		allParam = "";
	}
	else if(n==0) param = allParam.substring(n+1);
	else param = allParam.substring(0, n);
	int n1 = param.indexOf("=");
	String pName="";
	String pValue="";
	if(n1>0)
	{
		pName = param.substring(0, n1);
		pValue = param.substring(n1+1);
	}
	if(pName.equals("dbid")) dbid = pValue;
	else if(pName.equals("charset")) charset = pValue;
	else if(pName.equals("url")) url = pValue;
	else if(pName.equals("agent")) agent = pValue;
	else if(pName.equals("cid")) cid = pValue;
	else if(pName.equals("uid")) uid = pValue;
	else if(pName.equals("cmd")) cmd = pValue;
	else if(pName.equals("pos")) pos = pValue;
	else if(pName.equals("mod")) mod = pValue;
	else if(pName.equals("sql")) sql = pValue;
	else if(pName.equals("findfile")) findfile = pValue;
	else if(pName.equals("form")) form = pValue;
	else if(pName.equals("dir")) dir = pValue;
	else if(pName.equals("version")) version = pValue;
	else if(pName.equals("_my_loginid")) loginId = pValue;
	else if(pName.equals("sqlIndex")) sqlIndex = pValue;
	else if(pName.equals("paramIndex")) paramIndex = pValue;
	else if(pName.equals("urlIndex")) urlIndex = pValue;
	else if(pName.equals("urlParamInfo")) urlParamInfo = pValue;
	else if(pName.equals("sqlParamInfo")) sqlParamInfo = pValue;
	else if(pName.equals("{ServerIP}")) appServerIP = pValue;
	else if(pName.equals("{ServiceDir}")) appServiceDir = pValue;
	else paramHash.put(pName, new String(pValue));

	allParam = allParam.substring(n+1);
	nLoop = allParam.length();
}
if(g_bSessionCheck && loginId.equals("")) loginId = (String)session.getAttribute("webcrea.ssoId");
if(loginId == null) loginId = "";
if(cmd == null || cmd.equals(""))
{
	System.out.println("[Error] crossurl.jsp no parameter value");
	out.print("[Error] crossurl.jsp no parameter value");
	return;
}
String sInfoPath = GetCommonPath(request, version, dir, form, appServiceDir, 1);
commonInfo objInfo = new commonInfo();
objInfo.loadInfo(sInfoPath);
boolean bAgentError = false;
boolean bCookie = false;
if(!form.equals(""))
{
	try{
		form = form.replaceAll("___NP___", "&");
		String sFullPath = GetSecurityPath(request, version, dir, form, appServiceDir);
		if(sFullPath.equals(""))
		{
			System.out.println("[Error] crossurl.jsp File Not Found(" + form + ")");
			out.print("[Error] crossurl.jsp File Not Found(" + form + ")");
			return;
		}
		else
		{
			int nError = sFullPath.indexOf("[Error]");
			if(nError>=0)
			{
				String sLog = form;
				sFullPath = sFullPath.substring(nError + 8);
				if(g_bDump) sLog = sLog + " - " + sFullPath;
				System.out.println("[Error] crossurl.jsp File Not Found(" + sLog + ")");
				out.print("[Error] crossurl.jsp File Not Found(" + sLog + ")");
				return;
			}
		}
		String spChar = "{{{+}}}";
		ArrayList<String> arrUrlIndex = new ArrayList<String>();
		ArrayList<String> arrSqlIndex = new ArrayList<String>();
		ArrayList<String> arrParamIndex = new ArrayList<String>();
		ArrayList<String> arrUrlParamInfo = new ArrayList<String>();
		ArrayList<String> arrSqlParamInfo = new ArrayList<String>();
		boolean bNoSql = false;
		boolean bUrl = false;
		boolean bSql = false;
		boolean bParam = false;
		boolean bUrlParam = false;
		boolean bSqlParam = false;
		if(!urlIndex.equals(""))
		{
			arrUrlIndex = MySplit(urlIndex, spChar, false);
			bUrl = true;
		}
		if(!sqlIndex.equals(""))
		{
			arrSqlIndex = MySplit(sqlIndex, spChar, false);
			bSql = true;
		}
		if(!paramIndex.equals(""))
		{
			arrParamIndex = MySplit(paramIndex, spChar, false);
			bParam = true;
			bNoSql = true;
		}
		if(!urlParamInfo.equals(""))
		{
			arrUrlParamInfo = MySplit(urlParamInfo, spChar, false);
			bUrlParam = true;
		}
		if(!sqlParamInfo.equals(""))
		{
			arrSqlParamInfo = MySplit(sqlParamInfo, spChar, false);
			bSqlParam = true;
		}
		int nCntInfo = 0;
		if(bSql) nCntInfo = arrSqlIndex.size();
		else if(bUrl) nCntInfo = arrUrlIndex.size();
		else nCntInfo = arrParamIndex.size();
		String[] arrAgentUrl = new String[nCntInfo];
		for(int i=0; i<nCntInfo; i++)
		{
			boolean bUrlParam1=bUrlParam;
			Hashtable<String, String> urlParamHash = new Hashtable<String, String>();
			if(bUrlParam)
			{
				urlParamInfo = arrUrlParamInfo.get(i);
				if(!urlParamInfo.equals(""))
				{
					int n = urlParamInfo.indexOf("{}");
					if(n>0)
					{
						String urlParam = urlParamInfo.substring(0, n);
						String urlValue = urlParamInfo.substring(n+3);
						ArrayList<String> arrParam = new ArrayList<String>();
						ArrayList<String> arrValue = new ArrayList<String>();
						arrParam = MySplit(urlParam, "{}", false);
						arrValue = MySplit(urlValue, "{}", false);
						for(int j=0; j<arrParam.size(); j++)
						{
							String pName = arrParam.get(j);
							String pValue = "";
							if(arrValue.size()>j) pValue = arrValue.get(j);
							urlParamHash.put(pName, new String(pValue));
						}
					}
				}
				else bUrlParam1=false;
			}
			boolean bSqlParam1=bSqlParam;
			Hashtable<String, String> sqlParamHash = new Hashtable<String, String>();
			if(bSqlParam)
			{
				sqlParamInfo = arrSqlParamInfo.get(i);
				if(!sqlParamInfo.equals(""))
				{
					int n = sqlParamInfo.indexOf("{}");
					if(n>0)
					{
						String sqlParam = sqlParamInfo.substring(0, n);
						String sqlValue = sqlParamInfo.substring(n+3);
						ArrayList<String> arrParam = new ArrayList<String>();
						ArrayList<String> arrValue = new ArrayList<String>();
						arrParam = MySplit(sqlParam, "{}", false);
						arrValue = MySplit(sqlValue, "{}", false);
						for(int j=0; j<arrParam.size(); j++)
						{
							String pName = arrParam.get(j);
							String pValue = "";
							if(arrValue.size()>j) pValue = arrValue.get(j);
							sqlParamHash.put(pName, new String(pValue));
						}
					}
				}
				else bSqlParam1=false;
			}

			FileInputStream input=new FileInputStream(sFullPath);
	        InputStreamReader fr=new InputStreamReader(input, "UTF-8");
			BufferedReader reader = new BufferedReader(fr);
			String line = "";
			boolean bUrl1=bUrl;
			boolean bSql1=bSql;
			boolean bParam1=bParam;
			String sGetSql="";
			while ((line = reader.readLine()) != null) {
				int nKey = line.indexOf("=");
				if(nKey<=0) continue;
				String sKey = line.substring(0, nKey);
				sKey = sKey.trim();
				if(bUrl1)
				{
					urlIndex = arrUrlIndex.get(i);
					int nIndex = sKey.indexOf(urlIndex);
					if(nIndex>=0)
					{
						bUrl1 = false;
						String sUrl = line.substring(nKey+1);
						sUrl = sUrl.trim();
						nIndex = sUrl.indexOf("cookie=1&");
						if(nIndex>=0)
						{
							sUrl = sUrl.substring(nIndex+9);
							bCookie = true;
						}
						nIndex = sUrl.indexOf("url=");
						if(nIndex>=0)
						{
							url = sUrl.substring(nIndex+4);
							arrAgentUrl[i] = url;
						}
						else
						{
							nIndex = sUrl.indexOf("agent=");
							if(nIndex>=0)
							{
								agent = sUrl.substring(nIndex+6);
								arrAgentUrl[i] = agent;
							}
							else
							{
								url = sUrl;
								arrAgentUrl[i] = url;
							}
						}
						if(bUrlParam1)
						{
							 if(!agent.equals("")) agent = MakeService(agent, urlParamHash, true, bNoSql, request, false);
							 else url = MakeService(url, urlParamHash, true, bNoSql, request, false);
						}
					}
				}
				if(bSql1)
				{
					sqlIndex = arrSqlIndex.get(i);
					int nIndex = sKey.indexOf(sqlIndex);
					if(nIndex>=0)
					{
						bSql1 = false;
						sGetSql = line.substring(nKey+1);
						sGetSql = sGetSql.trim();
						if(bSqlParam1) sGetSql = MakeService(sGetSql, sqlParamHash, false, bNoSql, request, false);
					}
				}
				if(bParam1)
				{
					paramIndex = arrParamIndex.get(i);
					int nIndex = sKey.indexOf(paramIndex);
					if(nIndex>=0)
					{
						bParam1 = false;
						String sParam = line.substring(nKey+1);
						sParam = sParam.trim();
						if(bSqlParam || !sParam.equals(""))
						{
							if(bSqlParam) sParam = MakeService(sParam, sqlParamHash, true, bNoSql, request, false);
							String[] arr = sParam.split("&");
							for(int j=0; j<arr.length; j++)
							{
								String param = arr[j];
								String pName="";
								String pValue="";
								int n = param.indexOf("=");
								if(n>0)
								{
									pName = param.substring(0, n);
									pValue = param.substring(n+1);
								}
								else pName = param;
								paramHash.put(pName, new String(pValue));
							}
						}
					}
				}
				if(!bUrl1 && !bSql1 && !bParam1)
				{
					if(!sGetSql.equals(""))
					{
						if(!sql.equals("")) sql += spChar;
						sql += sGetSql;
					}
					break;
				}
			}
			reader.close();
			fr.close();
		}
		if(bUrl)
		{
			for(int i=0; i<arrAgentUrl.length-1; i++)
			{
				if(!arrAgentUrl[i].equals(arrAgentUrl[i+1]))
				{
					bAgentError=true;
					break;
				}
			}
		}
	} catch (IOException e) {
		System.out.println("[Error] crossurl.jsp " + e.getMessage());
		out.print("[Error] crossurl.jsp " + e.getMessage());
	}
}
if(url != null && !url.equals("") && agent != null && !agent.equals("")) bAgentError = true;
if(bAgentError)
{
	System.out.println("[Error] Commit: Not unique agent");
	out.print("[Error] Commit: Not unique agent");
	return;
}

if(cmd.equalsIgnoreCase("login"))
{
	if(g_mdiFile.equals(""))
	{
		System.out.println("[Error] No MDI Page");
		out.println("<script>alert('No MDI Page');history.back();</script>");
	}
	else if(!ConfirmLogin(request, loginId, true))
	{
		String sEndSessionMsg = g_endSessionMsg;
		System.out.println("[Error] " + sEndSessionMsg);
		out.println("<script>history.back();</script>");
	}
	else
	{
		if(g_mdiSection.equals("")) g_mdiSection = (String)session.getAttribute("webcrea.mdisection");
		String mdiFile = GetMDIPage(request);
		if(mdiFile.equals(""))
		{
			System.out.println("[Error] No MDI Page");
			out.println("<script>alert('No MDI Page');history.back();</script>");
		}
		else
		{
			String sMdiUrl = GetHostUrl(request) + "/" + mdiFile;
			session.setAttribute("webcrea.loginid", loginId);
			session.setAttribute("webcrea.redirect", "redirect");
			String sCache = "?v=";
			sCache += RandomCharByDay(0);
			sMdiUrl += sCache;
			response.sendRedirect(sMdiUrl);
		}
	}
	return;
}
else if(cmd.equals("logincheck"))
{
	String sNoSessionList_b = g_noSessionList;
	g_noSessionList = "";
	if(g_mdiFile.equals(""))
	{
		System.out.println("[Error] No MDI Page");
		out.println("[Error] No MDI Page");
	}
	else if(!ConfirmLogin(request, loginId, false))
	{
		System.out.println("[Error] Not Login");
		out.println("[Error] Not Login");
	}
	else out.println("[Login]");
	g_noSessionList = sNoSessionList_b;
	return;
}
else if(cmd.equals("confirmlogin"))
{
	if(g_bSessionCheck && loginId.equals(""))
	{
		System.out.println("[Error] Not Login");
		out.println("[Error] Not Login");
	}
	else out.println("[OK]");
	return;
}
else if(cmd.equals("endsession"))
{
	if(g_endSessionPage.equalsIgnoreCase("login") || g_endSessionPage.equalsIgnoreCase(""))
	{
		String requestPage = GetRequestFilePage(request, false);
		String loginFile = GetLoginPage(requestPage);
		if(!loginFile.equals("")) g_loginFile = loginFile;
		ClearSession(request, response, loginId);
		MoveLoginPage(request, response);
	}
	else
	{
		String endSessionPage = g_endSessionPage;
		String sCache = "?v=";
		sCache += RandomCharByDay(0);
		endSessionPage += sCache;
		response.sendRedirect(endSessionPage);
	}
	return;
}
else if(cmd.equals("sysdefault"))
{
	String sysDefaultPage = g_sysDefaultPage;
	String sCache = "?v=";
	sCache += RandomCharByDay(0);
	sysDefaultPage += sCache;
	response.sendRedirect(sysDefaultPage);
	return;
}
if(IsLoginCheck() && !cmd.equalsIgnoreCase("logout") && !cmd.equalsIgnoreCase("httpkey"))
{
	String sLogin = GetParamValue("login", paramHash, request);
	if(sLogin.equalsIgnoreCase("login") || sLogin.equalsIgnoreCase("loginsession"))
	{
		bLoginStart = true;
		if(sLogin.equalsIgnoreCase("loginsession")) bLoginSession = true;
	}
	if(!bLoginStart && !ConfirmLogin(request, loginId, true))
	{
		if(!g_endSessionPage.equals(""))
		{
			String sEndSessionMsg = g_endSessionMsg;
			System.out.println("[End Session] " + sEndSessionMsg);
			out.print("[End Session] " + sEndSessionMsg);
		}
		else
		{
			String sEndSessionMsg = g_endSessionMsg;
			System.out.println("[End Session Msg] " + sEndSessionMsg);
			out.print("[End Session Msg] " + sEndSessionMsg);
		}
		return;
	}
}
dbid = dbid.toUpperCase();
if (cmd.equalsIgnoreCase("httpkey"))
{
	try{
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(512);
		KeyPair keyPair = generator.genKeyPair();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();

		session.setAttribute("_my_rsa_key_", privateKey);
		RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		String publicKeyModulus = publicSpec.getModulus().toString(16);
		String publicKeyExponent = publicSpec.getPublicExponent().toString(16);

		String result = "publicKey" + "";
		result = result + "modulus="+publicKeyModulus + "exponent="+publicKeyExponent;
		out.println(result);
	} catch (IOException e) {
		System.out.println("[Error] crossurl.jsp create httpkey "+ e.getMessage());
		out.print("[Error] crossurl.jsp " + e.getMessage());
	}
}
else if(cmd.equalsIgnoreCase("export"))
{
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	String data = request.getParameter("data");
	data = data.replaceAll(" ", "+");
	String filename = request.getParameter("filename");
	BASE64Decoder decoder = new BASE64Decoder();
	byte[] dataByte = decoder.decodeBuffer(data);
	outputStream.write(dataByte);
	response.reset();
	String header = request.getHeader("User-Agent");
	if (header.contains("MSIE") || header.contains("Trident") || header.contains("Edge"))
	{
		filename = new String(filename.getBytes("8859_1"), "UTF-8");
		filename = URLEncoder.encode(filename,"UTF-8").replaceAll("\\+", "%20");
	}
	if(charset.equals("")) charset = "utf-8";
	String str = outputStream.toString("utf-8");
	String sExt = "";
	int nExt = filename.lastIndexOf(".");
	if(nExt>0)
	{
		sExt = filename.substring(nExt+1);
		sExt=sExt.toLowerCase();
	}
	String fileLen="";
	if(sExt.equals("xls") || sExt.equals("xlsx")) fileLen=String.valueOf(outputStream.size());
	else fileLen=String.valueOf(str.getBytes(charset).length);
	response.setHeader("Content-Disposition","attachment;filename=" + filename);
	response.setHeader("Content-Length",fileLen);
	response.setContentType("application/octet-stream;charset=UTF-8");
	ServletOutputStream sos = response.getOutputStream();
	if(sExt.equals("csv") && charset.equals("utf-8"))
	{
		sos.write(0xEF);
		sos.write(0xBB);
		sos.write(0xBF);
	}
	if(sExt.equals("xlsx")) sos.write(outputStream.toByteArray());
	else sos.write(str.getBytes(charset));
	sos.flush();
	sos.close();
	return;
}
else if(cmd.equalsIgnoreCase("logout"))
{
	ClearSession(request, response, loginId);
	String sLoginUrl = "";
	if(!g_loginFile.equals(""))
	{
		String sCurrUrl = request.getRequestURL().toString();
		int n = sCurrUrl.indexOf("/frame");
		if(n>0)
		{
			String requestPage = GetRequestFilePage(request, false);
			String loginFile = GetLoginPage(requestPage);
			sLoginUrl = sCurrUrl.substring(0, n);
			sLoginUrl = sLoginUrl + "/" + loginFile;
		}
	}
	if(sLoginUrl != null && !sLoginUrl.equals(""))
	{
		String sCache = "?v=";
		sCache += RandomCharByDay(0);
		sLoginUrl += sCache;
		response.sendRedirect(sLoginUrl);
	}
}
else if(cmd.equalsIgnoreCase("getfileversion") || cmd.equalsIgnoreCase("getservicefileversion"))
{
	if(findfile.equals(""))
	{
		System.out.println("[Error] File Not Found(" + findfile + ")");
		out.print("[Error] File Not Found(" + findfile + ")");
		return;
	}
	findfile = findfile.replaceAll("___NP___", "&");
	if(findfile.equalsIgnoreCase("crossurl") || findfile.equalsIgnoreCase("commonurl"))
	{
		String result = "fileInfo" + "";
		if(findfile.equalsIgnoreCase("crossurl")) result = result + "version=" + g_crossVersion + " createdate=" + g_crossCreateDate;
		else result = result + "version=" + g_commonVersion + " createdate=" + g_commonCreateDate;
		out.println(result);
		return;
	}
	boolean bServiceFile = false;
	String sFullPath = "";
	if(cmd.equalsIgnoreCase("getservicefileversion"))
	{
		bServiceFile = true;
		sFullPath = GetSecurityPath(request, version, dir, findfile, appServiceDir);
	}
	else
	{
		String sPath1  = request.getRealPath("");
		sPath1 = sPath1.replaceAll("\\\\", "/");
		String sPath2 = new File(request.getServletPath()).getParent();
		sPath2 = sPath2.replaceAll("\\\\", "/");
		int n = sPath2.indexOf("frame");
		if(n>0) sPath2 = sPath2.substring(0, n-1);
		sFullPath = sPath1+sPath2+"/"+findfile+"_Func.js";
	}

	try{
		File file = new File(sFullPath);
		if(!file.exists()){
			System.out.println("[Error] File Not Found(" + findfile + ")");
			out.print("[Error] File Not Found(" + findfile + ")");
			return;
		}
		FileReader fr = new FileReader(file);
		BufferedReader reader = new BufferedReader(fr);

		String sManagerVersion = "";
		String sFileVersion = "";
		String sFileCreateDate = "";
		String sKey0 = "_my_File.ManagerVersion";
		String sKey1 = "_my_File.FileVersion";
		String sKey2 = "_my_File.CreateDate";
		if(bServiceFile)
		{
			sKey1 = "FileVersion";
			sKey2 = "CreateDate";
		}
		int nLine = 0;
		String line = "";
		while ((line = reader.readLine()) != null)
		{
			int nFind = line.indexOf(sKey0);
			if(nFind>=0 && sManagerVersion.equalsIgnoreCase("") && !bServiceFile)
			{
				sManagerVersion = line.substring(nFind+sKey0.length());
				String sChar = "=";
				nFind = sManagerVersion.indexOf(sChar);
				sManagerVersion = sManagerVersion.substring(nFind+1);
				nFind = sManagerVersion.indexOf(";");
				if(nFind>0) sManagerVersion = sManagerVersion.substring(0, nFind);
				sManagerVersion = sManagerVersion.replaceAll("'", "");
				sManagerVersion = sManagerVersion.trim();
			}
			nFind = line.indexOf(sKey1);
			if(nFind>=0 && sFileVersion.equalsIgnoreCase(""))
			{
				sFileVersion = line.substring(nFind+sKey1.length());
				String sChar = "=";
				if(bServiceFile) sChar = ":";
				nFind = sFileVersion.indexOf(sChar);
				sFileVersion = sFileVersion.substring(nFind+1);
				if(!bServiceFile)
				{
					nFind = sFileVersion.indexOf(";");
					if(nFind>0) sFileVersion = sFileVersion.substring(0, nFind);
					sFileVersion = sFileVersion.replaceAll("'", "");
				}
				else
				{
					nFind = sFileVersion.indexOf("-");
					if(nFind>0) sFileVersion = sFileVersion.substring(0, nFind);
				}
				sFileVersion = sFileVersion.trim();
			}
			nFind = line.indexOf(sKey2);
			if(nFind>=0 && sFileCreateDate.equalsIgnoreCase(""))
			{
				sFileCreateDate = line.substring(nFind+sKey2.length());
				String sChar = "=";
				if(bServiceFile) sChar = ":";
				nFind = sFileCreateDate.indexOf(sChar);
				sFileCreateDate = sFileCreateDate.substring(nFind+1);
				if(!bServiceFile)
				{
					nFind = sFileCreateDate.indexOf(";");
					if(nFind>0) sFileCreateDate = sFileCreateDate.substring(0, nFind);
					sFileCreateDate = sFileCreateDate.replaceAll("'", "");
				}
				else sFileCreateDate = sFileCreateDate.replaceAll("]", "");
				sFileCreateDate = sFileCreateDate.trim();
			}
			if(!sFileVersion.equalsIgnoreCase("") && !sFileCreateDate.equalsIgnoreCase("")) break;
			nLine++;
			if(nLine>16) break;
		}
		reader.close();
		fr.close();
		String result = "fileInfo" + "";
		result = result + "version=" + sFileVersion + " createdate=" + sFileCreateDate + " managerversion=" + sManagerVersion;
		out.println(result);
	} catch (IOException e) {
		System.out.println("[Error] crossurl.jsp "+ findfile + e.getMessage());
		out.print("[Error] crossurl.jsp " + e.getMessage());
	}
	return;
}
else if(cmd.equalsIgnoreCase("getip"))
{
	String result = "hostInfo" + "";
	result = result + "ip="+getClientIP(request);
	out.println(result);
}
else if(url.equals("") && (dbid.equals("TEXT") || dbid.equals("OBJECT") || dbid.equals("MEMORY")))
{
	try{
		int nPos1 = Integer.parseInt(pos);
		int nPos2 = nPos1 + 100;
		int n = sql.indexOf("from");
		if(n<1)
		{
			if(sql.equals("")) sql = " Sql Not Found";
			System.out.println("[Error] crossurl.jsp" + sql);
			out.print("[Error] crossurl.jsp" + sql);
			return;
		}

		if(IsSqlFilter(sql, objInfo, out)) return;
		String sTableName = sql.substring(n+5);
		sTableName = sTableName.trim();
		n = sTableName.indexOf(" ");
		if(n>0) sTableName = sTableName.substring(0, n);

		String sExt = ".tsv";
		String sFile = "/work/" + sTableName;
		String sPath1  = request.getRealPath("");
		sPath1 = sPath1.replaceAll("\\\\", "/");
		String sPath2 = new File(request.getServletPath()).getParent();
		sPath2 = sPath2.replaceAll("\\\\", "/");
		n = sPath2.indexOf("frame");
		if(n>0) sPath2 = sPath2.substring(0, n-1);
		String sFullPath = sPath1+sPath2+sFile+sExt;

		File file = new File(sFullPath);
		if(!file.exists()){
			sExt = ".csv";
			sFullPath = sPath1+sPath2+sFile+sExt;
			file = new File(sFullPath);
		}
		if(file.exists()){
			int nCnt = 0;
			String line = "";
			if(IsMSVFile(file))
			{
				String lineAll = ReadMSVFile(file);
				ArrayList<String> arrLine = new ArrayList<String>();
				arrLine = MySplit(lineAll, "", true);
				for(int i=0; i<arrLine.size(); i++)
				{
					line = arrLine.get(i);
					if(nCnt == 0) {
						out.println(line);
						nCnt++;
						continue;
					}
					if (nPos1 >= 0) {
						if (nCnt <= nPos1) {
							nCnt++;
							continue;
						}
						if (nCnt > nPos2) break;
					}
					out.println(line);
					nCnt++;
				}
			}
			else
			{
				FileReader fr = new FileReader(file);
				BufferedReader reader = new BufferedReader(fr);
				while ((line = reader.readLine()) != null) {
					line = RemoveUTF8BOM(line);
					if(nCnt == 0) {
						out.println(line);
						nCnt++;
						continue;
					}
					if (nPos1 >= 0) {
						if (nCnt <= nPos1) {
							nCnt++;
							continue;
						}
						if (nCnt > nPos2) break;
					}
					out.println(line);
					nCnt++;
				}
				reader.close();
				fr.close();
			}
			if (nPos1 >= 0 && nCnt >= nPos2) out.print("--- More ---" + "\r\n");
		}
		else
		{
			System.out.println("[Error] crossurl.jsp File Not Found(" + sTableName + ")");
			out.print("[Error] crossurl.jsp File Not Found(" + sTableName + ")");
		}
	} catch (IOException e) {
		System.out.println("[Error] crossurl.jsp "+ sql + e.getMessage());
		out.print("[Error] crossurl.jsp " + e.getMessage());
	}
}
else
{
  String cross_url = "";
  boolean bHttps = false;
  HttpURLConnection conn = null;
  HttpsURLConnection conn_https = null;
  try{
		g_serverIp = appServerIP;
		if(url != null && !url.equals("")) cross_url = url;
		else if(agent != null && !agent.equals(""))
		{
			url = GetURL(dbid);
			int n = url.lastIndexOf("/");
			if(n>0) url = url.substring(0, n);
			cross_url = url + "/" + agent;
		}
		else cross_url = GetURL(dbid);
		if(cross_url == null || cross_url.equals(""))
		{
			System.out.println("[Error] crossurl.jsp URL Not Found(dbid : "+ dbid + ")");
			out.print("[Error] crossurl.jsp URL Not Found");
			return;
		}
		int nWebcreaAgent = GetWebcreaAgent(dbid);
		if(nWebcreaAgent == 1)
		{
			Hashtable<String, String> requestParamHash = new Hashtable<String, String>();
			requestParamHash.put("dbid", new String(dbid));
			requestParamHash.put("cmd", new String(cmd));
			if(sql != null && !sql.equals("")) sql = replaceAll(sql, "___NP___", "&");
			requestParamHash.put("sql", new String(sql));
			requestParamHash.put("url", new String(cross_url));
			if(agent != null && !agent.equals("")) requestParamHash.put("agent", new String(agent));
			requestParamHash.put("pos", new String(pos));
			Enumeration<String> getKey = paramHash.keys();
			while(getKey.hasMoreElements()){
				String pName = (String)getKey.nextElement();
				String pValue = (String)paramHash.get(pName);
				pValue  = pValue.replaceAll("___NP___", "&");
				requestParamHash.put(pName, new String(pValue));
			}
			String sResult = AgentCall(out, requestParamHash, request, response);
			if(bLoginStart)
			{
				String userid = "";
				String password = "";
				boolean bLoginOK = false;
				if(!sResult.equals(""))
				{
					int n = sResult.indexOf("[Error]");
					if(n<0)
					{
						n = sResult.indexOf("[STATUS]");
						if(n<0)
						{
							userid = GetParamValue("userid", paramHash, request);
							password = GetParamValue("password", paramHash, request);
							if(userid.equals(sResult))
							{
								sResult = "";
								bLoginOK = true;
							}
						}
					}
				}
				else sResult = "[Error] Login Fail!";
				if(bLoginOK)
				{
					SetLogin(request, response, userid, password, g_sessionTime);
					g_mdiSection = GetParamValue("MDI", paramHash, request);
					if(g_mdiSection == null) g_mdiSection = "";
					String mdiFile = GetMDIPage(request);
					if(mdiFile.equals(""))
					{
						System.out.println("[Error] No MDI Page");
						out.println("[Error] No MDI Page");
					}
					else
					{
						String sMdiUrl = GetHostUrl(request) + "/" + mdiFile;
						out.println("[Login][ID]" + userid);
					}
				}
				else out.println("[Login]" + sResult);
			}
			else if(!sResult.equals(""))
			{
				int nEos = sResult.indexOf("--- EOS");
				if(nEos>=0 && nEos <=2)
				{
					if(!g_endSessionPage.equals(""))
					{
						String sEndSessionMsg = g_endSessionMsg;
						System.out.println("[End Session] " + sEndSessionMsg);
						out.print("[End Session] " + sEndSessionMsg);
					}
					else
					{
						String sEndSessionMsg = g_endSessionMsg;
						System.out.println("[End Session Msg] " + sEndSessionMsg);
						out.print("[End Session Msg] " + sEndSessionMsg);
					}
				}
			}
		}
		else
		{
			if(g_sCharset != "" && charset.equals("")) charset = g_sCharset;
			if(charset.equals("")) charset = "euc-kr";
			/*URLConnection을 이용한 스트림 생성*/
			URL objUrl = new URL(cross_url);
			int nHttps = cross_url.indexOf("https:");
			if(nHttps>=0)
			{
				bHttps = true;
				conn_https = (HttpsURLConnection) objUrl.openConnection(); 
				conn_https.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
				conn_https.setRequestProperty("User-Agent", "Webcrea");
				conn_https.setDoOutput(true);
			}
			else
			{
				conn = (HttpURLConnection) objUrl.openConnection(); 
				conn.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
				conn.setRequestProperty("User-Agent", "Webcrea");
				conn.setDoOutput(true);
			}

			if((!bLoginStart || bLoginSession) && g_bSessionShare && request.getCookies() != null)
			{
				java.util.Iterator<Cookie> iHeader = java.util.Arrays.asList(request.getCookies()).iterator();
				String sCookies = "";
				while (iHeader.hasNext()) {
					sCookies = sCookies + encodeCookie((Cookie)iHeader.next()) + ";";
				}
				if(!bHttps) conn.setRequestProperty("Cookie", sCookies);
				else conn_https.setRequestProperty("Cookie", sCookies);
			}

			String parameter = URLEncoder.encode("cid", charset) + "=" + URLEncoder.encode(cid, charset);
			parameter += "&" + URLEncoder.encode("uid", charset) + "=" + URLEncoder.encode(uid, charset);
			parameter += "&" + URLEncoder.encode("cmd", charset) + "=" + URLEncoder.encode(cmd, charset);
			parameter += "&" + URLEncoder.encode("pos", charset) + "=" + URLEncoder.encode(pos, charset);
			parameter += "&" + URLEncoder.encode("mod", charset) + "=" + URLEncoder.encode(mod, charset);

			boolean bSqlParam=false;
			Enumeration<String> getKey = paramHash.keys();
			while(getKey.hasMoreElements()){
				String pName = (String)getKey.nextElement();
				if(!bSqlParam && pName.equals("sql")) bSqlParam=true;
				String pValue = (String)paramHash.get(pName);
				pValue  = pValue.replaceAll("___NP___", "&");
				parameter += "&" + URLEncoder.encode(pName, charset) + "=" + URLEncoder.encode(pValue, charset);
			}
			if(!bSqlParam)
			{
				sql = replaceAll(sql, "___NP___", "&");
				parameter += "&" + URLEncoder.encode("sql", charset) + "=" + URLEncoder.encode(sql, charset);
			}
			/*HTTP 요청을 한다.*/
			OutputStreamWriter wr = null;
			if(!bHttps) wr = new OutputStreamWriter(conn.getOutputStream());
			else wr = new OutputStreamWriter(conn_https.getOutputStream());
			wr.write(parameter);
			wr.flush();
			wr.close();

			if(bCookie)
			{
				Map<String, List<String>> header = conn.getHeaderFields();
				if(header.containsKey("Set-Cookie")){
					List<String> cookie = header.get("Set-Cookie");
					for(int i=0; i<cookie.size(); i++) {
						String cookies = cookie.get(i);
						if(cookies == "" || cookies.indexOf("JSESSIONID=") != -1) continue;
						response.addHeader("Set-Cookie", cookies);
					}
				}
			}

			BufferedReader reader = null;
			if(!bHttps) reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
			else reader = new BufferedReader(new InputStreamReader(conn_https.getInputStream(), charset));

			if(bLoginStart)
			{
				String userid = "";
				String password = "";
				boolean bLoginOK = false;
				String lineAll = "";
				String line = null;
				while ((line = reader.readLine()) != null) {
					lineAll = lineAll + line;
				}
				reader.close();
				if(!lineAll.equals(""))
				{
					int n = lineAll.indexOf("[Error]");
					if(n<0)
					{
						n = lineAll.indexOf("[STATUS]");
						if(n<0)
						{
							userid = GetParamValue("userid", paramHash, request);
							password = GetParamValue("password", paramHash, request);
							if(userid.equals(lineAll))
							{
								lineAll = "";
								bLoginOK = true;
							}
						}
					}
				}
				else lineAll = "[Error] Login Fail!";
				if(bLoginOK)
				{
					SetLogin(request, response, userid, password, g_sessionTime);
					g_mdiSection = GetParamValue("MDI", paramHash, request);
					if(g_mdiSection == null) g_mdiSection = "";
					String mdiFile = GetMDIPage(request);
					if(mdiFile.equals(""))
					{
						System.out.println("[Error] No MDI Page");
						out.println("[Error] No MDI Page");
					}
					else
					{
						String sMdiUrl = GetHostUrl(request) + "/" + mdiFile;
						out.println("[Login][ID]" + userid);
					}
				}
				else out.println("[Login]" + lineAll);
			}
			else
			{
				int nSeq = 0;
				String line = null;
				while ((line = reader.readLine()) != null) {
					if(nSeq == 0 && !line.equals(""))
					{
						int nEos = line.indexOf("--- EOS");
						if(nEos>=0 && nEos <=2)
						{
							if(!g_endSessionPage.equals(""))
							{
								String sEndSessionMsg = g_endSessionMsg;
								System.out.println("[End Session] " + sEndSessionMsg);
								out.print("[End Session] " + sEndSessionMsg);
							}
							else
							{
								String sEndSessionMsg = g_endSessionMsg;
								System.out.println("[End Session Msg] " + sEndSessionMsg);
								out.print("[End Session Msg] " + sEndSessionMsg);
							}
							break;
						}
					}
					out.println(line);
				}
				reader.close();
			}
		}
	}catch (IllegalArgumentException e) { 
		String sLog = "";
		if(g_bDump)	sLog = "[Error] crossurl.jsp(bad url:"+ cross_url + ", sql:" + sql +  ") " + e.toString();
		else sLog = "[Error] crossurl.jsp(bad url) " + e.toString();
		System.out.println("[Error] crossurl.jsp(bad url:"+ cross_url + ", sql:" + sql +  ") " + e.toString());
		out.print(sLog);
	}catch (UnsupportedEncodingException e) { 
		String sLog = "";
		if(g_bDump)	sLog = "[Error] crossurl.jsp(url encoding:"+ cross_url + ", sql:" + sql +  ") " + e.toString();
		else sLog = "[Error] crossurl.jsp(url encoding) " + e.toString();
		System.out.println("[Error] crossurl.jsp(url:"+ cross_url + ", sql:" + sql +  ") " + e.toString());
		out.print(sLog);
	}catch (IOException e) { 
		String sLog = "";
		if(g_bDump)	sLog = "[Error] crossurl.jsp(url:"+ cross_url + ", sql:" + sql +  ") " + e.toString();
		else sLog = "[Error] crossurl.jsp " + e.toString();
		System.out.println("[Error] crossurl.jsp(url:"+ cross_url + ", sql:" + sql +  ") " + e.toString());
		out.print(sLog);
	}finally {
		if(conn != null)
		{
			conn.getInputStream().close();
			conn.disconnect();
		}
		if(conn_https != null)
		{
			conn_https.getInputStream().close();
			conn_https.disconnect();
		}
	}
}
%><%!
String getClientIP(HttpServletRequest request)
{
	String ip = request.getHeader("X-FORWARDED-FOR");
	if (ip == null) ip = request.getHeader("Client-IP");
	if (ip == null) ip = request.getHeader("Proxy-Client-IP");
	if (ip == null) ip = request.getHeader("WL-Proxy-Client-IP");
	if (ip == null) ip = request.getHeader("HTTP_CLIENT_IP");
	if (ip == null) ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	if (ip == null) ip = request.getRemoteAddr();
	return ip;
}
String getCoreInfo()
{
	Integer nCores = Runtime.getRuntime().availableProcessors();
	String sRet = nCores.toString();
	return sRet;
}
boolean SetLogin(HttpServletRequest request, HttpServletResponse response, String userid, String password, int sessionTime)
{
	return SetSession(request, response, userid, password, g_sessionTime);
}
String RemoveUTF8BOM(String s)
{
	if (s.startsWith("\uFEFF")){
		s = s.substring(1);
	}
	return s;
}
byte[] HexToByteArray(String hex)
{
	if (hex == null || hex.length() % 2 != 0) {
		return null;
	}

	byte[] ba = new byte[hex.length()/2];
	for (int i=0; i<ba.length; i++) {
		ba[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
	}
	return ba;
}
boolean IsMSVFile(File file)
{
	boolean bRet = false;
	try{
		FileReader fr = new FileReader(file);
		BufferedReader reader = new BufferedReader(fr);
		String line = "";
		int nCnt = 0;
		while ((line = reader.readLine()) != null) {
			int nFileChk = line.indexOf("");
			if(nFileChk>0)
			{
				bRet = true;
				break;
			}
			nCnt++;
			if(nCnt>5) break;
		}
		reader.close();
		fr.close();
	} catch (IOException e) {}
	return bRet;
}
String ReadMSVFile(File file)
{
	String result = "";
	try{
		FileReader fr = new FileReader(file);
		BufferedReader reader = new BufferedReader(fr);
		String line = "";
		int nCnt = 0;
		while ((line = reader.readLine()) != null) {
			line = RemoveUTF8BOM(line);
			result = result + line + "\r\n";
			nCnt++;
		}
		reader.close();
		fr.close();
		if(nCnt>0) result = result.substring(0, result.length()-2);
	} catch (IOException e) { result = ""; }
	return result;
}
String MakeService(String service, Hashtable<String, String> sqlParamHash, boolean bParam, boolean bNoSql, HttpServletRequest request, boolean bQueryBySub)
{
	if(bParam)
	{
		Enumeration<String> getKey = sqlParamHash.keys();
		while(getKey.hasMoreElements()){
			String pName = (String)getKey.nextElement();
			String pValue = (String)sqlParamHash.get(pName);
			boolean  bCheck = false;
			String sCheck = "";
			if(!pName.equals("")) sCheck = pName.substring(0, 1);
			if(sCheck.equals(":") || sCheck.equals("#")) bCheck = true;
			if(bCheck)
			{
				int nSession = pName.indexOf("_my_Security");
				if(nSession>0) continue;
				service = convReplace(service, pName, pValue);
			}
			else
			{
				pName = "&" + pName;
				service = service.replace(pName, pValue);
			}
		}
		int nSession = service.indexOf("Session.");
		while(nSession>0)
		{
			String s1 = service.substring(0, nSession);
			int nStart = s1.lastIndexOf("=");
			String s2 = service.substring(nStart+1);
			int nEnd = s2.indexOf("&");
			String sName = s2;
			if(nEnd>0) sName = s2.substring(0, nEnd);
			String sNameSession = sName.substring(sName.indexOf(".")+1,sName.length());
			HttpSession session = request.getSession(true);
			String sValue = (String)session.getAttribute(sNameSession);
			service = convReplace(service, sName, sValue);
			nSession = service.indexOf("Session.");
		}
		return service;
	}
	if(!bNoSql)
	{
		boolean bInsert = false;
		String sSql = service;
		String sSqlT="";
		boolean bQueryBySubT = false;
		if(bQueryBySub) bQueryBySubT = bQueryBySub;
		ArrayList<String> arrSql = MakeToken(sSql, true);
		for(int i=0; i<arrSql.size(); i++)
		{
			String s = arrSql.get(i);
			if(!s.equals("") && s.toLowerCase().equals("insert"))
			{
				bInsert = true;
				break;
			}
			if(!bQueryBySubT && !s.equals("") && (s.toLowerCase().equals("merge") || s.toLowerCase().equals("select") || s.toLowerCase().equals("insert") ||	s.toLowerCase().equals("update") || s.toLowerCase().equals("delete")))
			{
				bQueryBySubT = true;
			}
			boolean bSubQuery = false;
			if(s.equals("(")) bSubQuery = true;
			if(bSubQuery)
			{
				int n=1;
				String sSubSql="";
				for (++i; i<arrSql.size(); i++)
				{
					s = arrSql.get(i);
					if (s.equals("(")) n++;
					else if (s.equals(")")) n--;
					if(n == 0) break;
					sSubSql += s;
				}
				if(!sSubSql.equals("")) sSubSql = MakeService(sSubSql, sqlParamHash, bParam, bNoSql, request, bQueryBySubT);
				sSqlT = sSqlT + "(" + sSubSql + ")";
				continue;
			}
			sSqlT += s;
		}
		if(!bInsert && !sSqlT.equals("")) service = sSqlT;
	}
	boolean bDelConv = false;
	boolean bQuery = false;
	boolean bSelectQuery = false;
	boolean bWhere = false;
	String sRet="";
	ArrayList<String> arr = MakeToken(service, false);
	if (arr.size()<1) return service;
	ArrayList<String> arrConvItem = new ArrayList<String>();
	ArrayList<Integer> arrConvType = new ArrayList<Integer>();
	for(int i=0; i<arr.size(); i++)
	{
		String itemB = "";
		if(arrConvItem.size()>0) itemB = arrConvItem.get(arrConvItem.size()-1);
		String s = arr.get(i);
		int type = 0;
		if(!bQuery && !s.equals("") && (s.toLowerCase().equals("select") || s.toLowerCase().equals("insert") || s.toLowerCase().equals("update") || s.toLowerCase().equals("delete")))
		{
			bQuery = true;
			if(s.toLowerCase().equals("select")) bSelectQuery = true;
		}
		if(bQuery && !s.equals("") && s.toLowerCase().equals("where")) bWhere = true;
		if(s.equals("") || !IsItemChar(s.charAt(0)))
		{
			if(IsWhereOper(s) && (itemB.equals("(") || itemB.toLowerCase().equals("where"))) continue;
			arrConvItem.add(s);
			arrConvType.add(type);
			continue;
		}
		boolean bVariable = (s.charAt(0) == '&') ? true : false;
		boolean bDigit = (s.charAt(0) == '#') ? true : false;
		boolean bText = (s.charAt(0) == ':') ? true : false;
		boolean bDouble = false;
		boolean bTriple = false;
		String sOrg = s;
		if(s.length() > 1 && s.charAt(0) == s.charAt(1))
		{
			bDouble = true;
			s = s.substring(1);
			if (s.length() > 1 && s.charAt(0) == s.charAt(1))
			{
				bTriple = true;
				s = s.substring(1);
			}
		}
		if(bVariable) type = 7;
		else if(bText)
		{
			if(bDouble) type = 2;
			else if(bTriple) type = 3;
			else type = 1;
		}
		else if(bDigit)
		{
			if(bDouble) type = 5;
			else if(bTriple) type = 6;
			else type = 4;
		}
		String sItem = s.substring(1);
		if (sItem.equals("")) return service;
		int n = arrConvItem.size()-1;
		boolean bLike = false;
		if(itemB.toLowerCase().equals("like")) bLike=true;
		String sData = "";
		String sItem1 = "";
		if(!bQuery && (bText || bDigit))
		{
			int nItem = sItem.indexOf("&");
			if(nItem>0)
			{
				sItem1 = sItem.substring(nItem);
				sItem = sItem.substring(0, nItem);
			}
		}
		sData = GetParamValue(sOrg, sqlParamHash, request);
		sData += sItem1;
		sItem += sItem1;
		if(bWhere && bSelectQuery && sData.equals("") && (((bText || bDigit) && !bDouble && !bTriple) || bLike))
		{
			arrConvItem.remove(n);
			arrConvType.remove(n--);
			if (arrConvItem.get(n).equals(")"))
			{
				int n1 = 0;
				for (; n>0; n--)
				{
					if (arrConvItem.get(n).equals("(")) n1--;
					else if (arrConvItem.get(n).equals(")")) n1++;
					arrConvItem.remove(n);
					arrConvType.remove(n);
					if(n1 == 0) break;
				}
				n--;
			}
			arrConvItem.remove(n);
			arrConvType.remove(n--);
			if(arrConvItem.size()>0 && IsWhereOper(arrConvItem.get(n)))
			{
				arrConvItem.remove(n);
				arrConvType.remove(n);
			}
			bDelConv = true;
			continue;
		}
		arrConvItem.add(sOrg);
		arrConvType.add(type);
	}
	if(bQueryBySub && !bQuery) bQuery = true;
	if(bDelConv)
	{
		for(int i=0; i<arrConvItem.size(); i++)
		{
			String objConv = arrConvItem.get(i);
			String objConvB = "";
			String objConvN = "";
			boolean bContinue = false;
			if(i>0)
			{
				objConvB = arrConvItem.get(i-1);
				bContinue = IsSqlFunction(arrConvItem.get(i-1));
			}
			if(i+1<arrConvItem.size()) objConvN = arrConvItem.get(i+1);
			if(!bContinue && objConv.equals("(") && objConvN.equals(")"))
			{
				arrConvItem.remove(i);
				arrConvType.remove(i);
				arrConvItem.remove(i);
				arrConvType.remove(i--);
				if(IsWhereOper(objConvB))
				{
					arrConvItem.remove(i);
					arrConvType.remove(i--);
				}
				i--;
			}
		}
	}
	for(int i=0; i<arrConvItem.size(); i++)
	{
		boolean bSpace = true;
		String itemB = "";
		if(i>0) itemB = arrConvItem.get(i-1);
		String s = arrConvItem.get(i);
		int type = arrConvType.get(i);

		if(s.toLowerCase().equals("where"))
		{
			if(i==arrConvItem.size()-1) continue;
			else if(i+2<arrConvItem.size())
			{
				String s1 = arrConvItem.get(i+1);
				String s2 = arrConvItem.get(i+2);
				if((s1.toLowerCase().equals("order") || s1.toLowerCase().equals("group")) && s2.toLowerCase().equals("by")) continue;
			}
		}
		if(sRet.equals("")) bSpace = false;
		if(s.equals(",") || s.equals(")") || s.equals("=")) bSpace = false;
		if(i>0)
		{
			if(itemB.equals("(") || itemB.equals("=")) bSpace = false;
			if(IsSqlFunction(itemB)	&& s.equals("(")) bSpace = false;
		}
		boolean bQuote = false;
		if(type==1 || type==2 || type==3 || type==4 || type==5 || type==6 || type==7)
		{
			String sItem1 = "";
			if(type==1 || type==2 || type==3 || type==4 || type==5 || type==6)
			{
				int nItem = s.indexOf("&");
				if(nItem>0)
				{
					sItem1 = s.substring(nItem);
					s = s.substring(0, nItem);
				}
			}
			if((bQuery || (!bQuery && !bNoSql)) && type==1 || type==2 || type==3) bQuote = true;
			s = GetParamValue(s, sqlParamHash, request);
			s += sItem1;
		}
		if(itemB.toLowerCase().equals("like"))
		{
			if(type==2) s = "%" + s + "%";
			else if(type==1) s = s + "%";
		}
		if(bSpace) sRet += " ";
		if(bQuote)
		{
			if(bQuery && s.indexOf("'")>=0) s = s.replaceAll("'", "''");
			sRet += "'";
		}
		else if(type==4 || type==5 || type==6)
		{
			if(s == null || s.equals("")) s = "0";
		}
		sRet += s;
		if(bQuote) sRet += "'";
	}
	return sRet;
}
String GetParamValue(String key, Hashtable<String, String> sqlParamHash, HttpServletRequest request)
{
	String sRet = "";
	Enumeration<String> getKey = sqlParamHash.keys();
	while(getKey.hasMoreElements()){
		String pName = (String)getKey.nextElement();
		String pValue = (String)sqlParamHash.get(pName);
		if(key.charAt(0) == '&') pName = "&" + pName;
		int nSession = key.toLowerCase().indexOf("session.");
		if(nSession>=0)
		{
			HttpSession session = request.getSession(true);
			String sName = key.substring(key.indexOf(".")+1,key.length());
			String sValue = (String)session.getAttribute(sName);
			sRet = sValue;
			break;
		}
		else
		{
			if(key.equals(pName))
			{
				int n = key.indexOf(pName);
				if(n>=0)
				{
					sRet = key.replace(pName, pValue);
					break;
				}
			}
		}
	}
	return sRet;
}
ArrayList<String> MakeToken(String pStr, boolean bSpace)
{
	if(pStr.equals("")) return null;
	ArrayList<String> arr = new ArrayList<String>();
	int cb = pStr.length();
	String psz = "";
	int nsz = 0;
	boolean opr = true;
	for (int i=0; i<cb; i++)
	{
		char c = pStr.charAt(i);
		if(c == '\'' || c == '\"')
		{
			if (nsz > 0 && psz.charAt(nsz-1) != '.')
			{
				nsz = 0;
				arr.add(psz.trim());
				psz = "";
			}
			psz += c;
			nsz++;
			for (++i; i<cb; i++)
			{
				psz += pStr.charAt(i);
				nsz++;
				if (pStr.charAt(i) == c)
				{
					if (i+1 < cb && pStr.charAt(i+1) == '.')
					{
						psz += pStr.charAt(++i);
						nsz++;
					}
					if (i+1 >= cb || pStr.charAt(i+1) != c) break;
					psz += pStr.charAt(++i);
					nsz++;
					continue;
				}
			}
			if (nsz > 0 && psz.charAt(nsz-1) != '.')
			{
				nsz = 0;
				arr.add(psz.trim());
				psz = "";
			}
			opr = false;
		}
		else if (c == '.' || IsItemChar(c))
		{
			opr = false;
			psz += c;
			nsz++;
			continue;
		}
		else if (c==','||c==';'||c=='('||c==')')
		{
			if (nsz > 0)
			{
				nsz = 0;
				arr.add(psz.trim());
				psz = "";
			}
			nsz = 0;
			psz += c;
			nsz++;
			if (nsz > 0)
			{
				nsz = 0;
				arr.add(psz.trim());
				psz = "";
			}
			opr = (c==','||c==';'||c=='(') ? true : false;
		}
		else if (c == ' ')
		{
			if (nsz > 0)
			{
				nsz = 0;
				arr.add(psz.trim());
				psz = "";
			}
			if(bSpace)
			{
				String s = "";
				s += c;
				arr.add(s);
			}
		}
		else
		{
			if (c == '*' && nsz > 0 && psz.charAt(nsz-1) == '.')
			{
				psz += c;
				nsz++;
				opr = false;
				continue;
			}

			String sOper = "=!<>+-*/\\%|&";
			int nOperaterChar = sOper.indexOf(c);
			if(nOperaterChar>=0)
			{
				if (nsz > 0)
				{
					nsz = 0;
					arr.add(psz.trim());
					psz = "";
				}
				nsz = 0;
				psz += c;
				nsz++;
				for (++i; i<cb; i++)
				{
					if (pStr.charAt(i) == c)
					{
						psz += c;
						nsz++;
						continue;
					}

					nOperaterChar = sOper.indexOf(pStr.charAt(i));
					if (nOperaterChar<0) break;
					psz += pStr.charAt(i);
					nsz++;
				}
				i--;
				if (nsz > 0)
				{
					nsz = 0;
					arr.add(psz.trim());
					psz = "";
				}
				opr = true;
				continue;
			}
			psz += c;
			nsz++;
			opr = false;
		}
	}
	if (nsz > 0)
	{
		nsz = 0;
		arr.add(psz.trim());
		psz = "";
	}
	return arr;
}
boolean IsItemChar(char c)
{
	boolean bRet = false;
	String[] arrOper = {":","#","&"};
	for(int i=0; i<arrOper.length; i++)
	{
		String sCh = Character.toString(c);
		if(arrOper[i].equals(sCh))
		{
			bRet = true;
			break;
		}
	}
	return bRet;
}
boolean IsWhereOper(String s)
{
	boolean bRet = false;
	String[] arrOper = {"==", "*=", "+=", "!=", ">", "<", ">=", "<=", "<>", "between", "and", "or", "like"};
	for(int i=0; i<arrOper.length; i++)
	{
		if(arrOper[i].equals(s))
		{
			bRet = true;
			break;
		}
	}
	return bRet;
}
%><%!
public static String encodeCookie(Cookie cookie) {
	StringBuffer buf = new StringBuffer( cookie.getName() );
	buf.append("=");
	buf.append(cookie.getValue());
	return (buf.toString());
}
%><%!
public static String replaceAll(String source, String regex, String replacement) {
    StringBuffer newReplacement = new StringBuffer();
    char[] replaceChar = replacement.toCharArray();
    for (int i = 0; i < replaceChar.length; i++) {
        if (replaceChar[i] == '\\' || replaceChar[i] == '$') {
            newReplacement.append('\\');
        }
        newReplacement.append(replaceChar[i]);
    }
    return source.replaceAll(regex, newReplacement.toString());
}
public static String convReplace(String sService, String sFind, String sConv) {
	String sConvService = "";
	int n = sService.indexOf(sFind);
	if(n<0) return sService;
	while(n>=0)
	{
		String s1 = sService.substring(0, n);
		String s2 = sService.substring(n+sFind.length());
		sConvService += s1;
		if(s2.equals("") || s2.charAt(0) == '&' || s2.charAt(0) == '=') sConvService += sConv;
		else sConvService += sFind;
		if(s2.equals("")) break;
		sService = s2;
		n = sService.indexOf(sFind);
		if(n<0) sConvService += s2;
	}
	return sConvService;
}
%>
