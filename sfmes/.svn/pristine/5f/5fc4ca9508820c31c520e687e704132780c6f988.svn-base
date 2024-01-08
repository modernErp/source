/*--------------------------------------------------------------------------
 *
 *  Webcrea : ActiveSoft Web Version 7, 0, 2021, 0203, 002
 *
 *  (c) Copyright 2015. ActiveSoft. Co., Ltd.
 *  For details, see the activesoft web site: http://www.activesoft.co.kr
 *
 *--------------------------------------------------------------------------*/

Win.FileName = 'test';
Win.FileTitle = 'test';

var _my_File;

var _my_Page00;
var _my_Page00_Form1;
var _my_Page00_Form2;
var _my_Page00_Form3;
var _my_Page00_Form4;

var Page00 = {};

Page00.Form1 = new function() {
};

Page00.Form2 = new function() {
	this.pb1 = new function() {
		this.OnCLICK = function(currObj) {
			let ret = FuncPage00_Form2_pb1_OnCLICK(currObj);
			return ret;
		},
		this.OnGOTFOCUS = function(currNode, currObj) {
			let ret = FuncPage00_Form2_pb1_OnGOTFOCUS(currNode, currObj);
			return ret;
		},
		this.OnLOSTFOCUS = function(currNode, currObj) {
			if(currNode && (Webcrea.IsReadOnly(currNode) || !Webcrea.IsEnable(currNode))) return;
			let ret = FuncPage00_Form2_pb1_OnLOSTFOCUS(currNode, currObj);
			return ret;
		}
	}
};

Page00.Form3 = new function() {
};

Page00.Form4 = new function() {
};

function OnWinLoad() {
	Webcrea.OnLoaded();
}

function OnWinResize() {
	Webcrea.OnResized();
}

function OnWinClose() {
	Webcrea.OnClose(this);
	var bClose = Webcrea.IsClose();
	if(!bClose) return false;
}

function OnWinError(msg,url,line,col,error) {
	var sSysMsg = Webcrea.GetErrorMsg(error);
	if(!Webcrea.IsEmpty(sSysMsg)) msg = sSysMsg;
	var str = "Error: " + msg + "\nLine : " + line + "\nURL: " + url + "\nFileName: " + Win.FileName;
	if(typeof Webcrea != 'undefined' && typeof Webcrea.ErrorMsgBox != 'undefined') Webcrea.ErrorMsgBox(str, error);
	else
	{
		if(Webcrea.bAlert)
		{
			Webcrea.Trace(error.stack);
			alert(str);
		}
	}
}

function OnLOADED(){
	if(typeof OnINIT != 'undefined') OnINIT();
	OnLayout();
}

function OnLOADEDComplete() {
	try{
		window.onerror = OnWinError;
		window.onbeforeunload = OnWinClose;
		
		Webcrea.OnInit(this);
		
		window.onload = OnWinLoad();
		window.onresize = OnWinResize;
	}catch(e) {
		console.log("[OnLOADEDComplete : " + Win.FileTitle + "]" + e.stack);
		alert("There is an error. Check the console window.");
	}
}
