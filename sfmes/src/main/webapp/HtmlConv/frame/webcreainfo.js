/*--------------------------------------------------------------------------
 *
 *  Webcrea : ActiveSoft Web Version 7, 0, 2021, 0203, 002
 *
 *  (c) Copyright 2015. ActiveSoft. Co., Ltd.
 *  For details, see the activesoft web site: http://www.activesoft.co.kr
 *
 *--------------------------------------------------------------------------*/
var WebcreaInfo = new function()
{
	this.FileVersion = '7, 0, 2021, 0203, 002';
	this.CreateDate = '2021-02-08 09:58:57';
	this.FilePath = '';
	this.FileName = '';
	this.sCachePrevFile = '';

	this.runFileRoot = 'HtmlConv';
	this.sysDefaultPage = 'frame/sysdefault.htmls';
	this.bMasterDisplay = false;
	this.arrMasterFile = [];

	var sBrowser = navigator.userAgent.toLowerCase();
	this.Browser = {
		ie6 : sBrowser.indexOf('msie 6') != -1,
		ie7 : sBrowser.indexOf('msie 7') != -1,
		ie8 : sBrowser.indexOf('msie 8') != -1,
		ie9 : sBrowser.indexOf('msie 9') != -1,
		ie10 : sBrowser.indexOf('msie 10') != -1,
		ie11 : sBrowser.indexOf('trident/7') != -1
	};
	this.arrLoadJSFile = ['/frame/prototype.js','/frame/iscroll.js','/frame/sysmsg.js','/frame/calendar.js','/frame/webcrealang.js','/frame/webcrealicense.js','/frame/webcrea.js'];
	this.IsIE69 = function()
	{
		var name = navigator.appName;
		return name == 'Microsoft Internet Explorer' && (WebcreaInfo.Browser.ie6 || WebcreaInfo.Browser.ie7 || WebcreaInfo.Browser.ie8 || WebcreaInfo.Browser.ie9);
	},
	this.IsIE10 = function()
	{
		var name = navigator.appName;
		return name == 'Microsoft Internet Explorer' && WebcreaInfo.Browser.ie10;
	},
	this.LoadJsBase = function()
	{
		var sCachePrevFile = arguments[0];
		var bMDI = arguments[1];
		var sCachePrevMaster = '?v=';
		sCachePrevMaster += Math.floor(Math.random()*10000);
		this.sCachePrevMaster = sCachePrevMaster;
		var SetCSS = function(cssFile, bAdd)
		{
			var css = document.createElement('link');
			css.rel = 'stylesheet';
			css.href = cssFile;
			css.type = 'text/css';
			var nBase = 0;
			var arrLink = document.getElementsByTagName('link');
			if(arrLink.length>0)
			{
				if(bAdd) nBase = arrLink.length-1;
				var baseLink = arrLink[nBase];
				if(bAdd) baseLink.parentNode.appendChild(css);
				else baseLink.parentNode.insertBefore(css, baseLink);
			}
		};
		var sFileName = WebcreaInfo.FilePath + WebcreaInfo.FileName;
		SetCSS(sFileName+'.css'+sCachePrevFile);
		for(var i=WebcreaInfo.arrMasterFile.length-1; i>=0; i--)
		{
			var masterObj = WebcreaInfo.arrMasterFile[i];
			var cssFile = masterObj.path + masterObj.name;
			cssFile = cssFile + '.css' + sCachePrevMaster;
			SetCSS(cssFile);
		}
		for(var i=0; i<WebcreaInfo.arrMasterFile.length; i++)
		{
			var masterObj = WebcreaInfo.arrMasterFile[i];
			var sMaster = masterObj.path + masterObj.name;
			WebcreaInfo.arrLoadJSFile.push(sMaster+'_Func.js'+sCachePrevMaster);
			WebcreaInfo.arrLoadJSFile.push(sMaster+'.js'+sCachePrevMaster);
		}
		if(!bMDI) WebcreaInfo.arrLoadJSFile.push(sFileName+'_Func.js');
		WebcreaInfo.arrLoadJSFile.push(sFileName+'.js');
		WebcreaInfo.arrLoadJSFile.unshift('/frame/webcreaconfig.js');
	},
	this.LoadJs = function()
	{
		var seq = arguments[0];
		var framePath = arguments[1];
		var version = arguments[2];
		var sCachePrev = arguments[3];
		var sCachePrevFile = arguments[4];
		var bJQueryJs = arguments[5];
		this.sCachePrevFile = sCachePrevFile;
		var infoVersion = WebcreaInfo.FileVersion.replace(/[^0-9]/g, "");
		var cachePrev = sCachePrev.replace(/[^0-9]/g, "");
		if(infoVersion>cachePrev) sCachePrev = '?v='+infoVersion;
		var s = document.createElement('script');
		var loadJSFile = WebcreaInfo.arrLoadJSFile[seq];
		var jQueryScript;
		if(bJQueryJs)
		{
			if(loadJSFile.indexOf('prototype.js')>=0)
			{
				jQueryScript = document.createElement('script');
				jQueryScript.type = 'text/javascript';
				jQueryScript.innerText = 'jQuery.noConflict();var $j = jQuery;';
			}
		}
		var bFrameFile = false;
		var sFrameFile = framePath + '/frame';
		if(loadJSFile.indexOf('/frame')==0 || loadJSFile.indexOf(sFrameFile)==0)
		{
			bFrameFile = true;
			if(loadJSFile.indexOf('/frame')==0) loadJSFile = framePath + loadJSFile;
		}
		s.type = 'text/javascript';
		s.charset='utf-8';
		s.async = true;
		if(loadJSFile.indexOf('?v=')<0)
		{
			if(sCachePrevFile && !bFrameFile) s.src = loadJSFile + sCachePrevFile;
			else
			{
				if(loadJSFile.indexOf('webcrealicense.js')>=0) sCachePrev='?v='+WebcreaInfo.CreateDate.replace(/[^0-9]/g, "");
				s.src = loadJSFile + sCachePrev;
			}
		}
		else s.src = loadJSFile;
		var arrScript = document.getElementsByTagName('script');
		var x = document.getElementsByTagName('script')[arrScript.length-1];
		if(jQueryScript) x.parentNode.appendChild(jQueryScript);
		x.parentNode.appendChild(s);
		s.onload = function()
		{
			s.onload = null;
			seq++;
			if(seq<WebcreaInfo.arrLoadJSFile.length) WebcreaInfo.LoadJs(seq, framePath, version, sCachePrev, sCachePrevFile, bJQueryJs);
			else OnLOADED();
		}
	}
	setTimeout(function(){if(WebcreaInfo.IsIE69()){location.href=WebcreaInfo.sysDefaultPage;}});
};
