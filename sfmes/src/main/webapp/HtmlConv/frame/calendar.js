/*--------------------------------------------------------------------------
 *
 *  Webcrea : ActiveSoft Web Version 7, 0, 2020, 0107, 001
 *
 *  (c) Copyright 2015. ActiveSoft. Co., Ltd.
 *  For details, see the activesoft web site: http://www.activesoft.co.kr
 *
 *--------------------------------------------------------------------------*/
var CalendarObj = new function()
{
	this.divCalendarGroup = null;
	this.currDivCalendar = null;
	this.fixedX = -1;
	this.fixedY = -1;
	this.startAt = 0;
	this.currMonthOnly = 1;
	if(WebcreaInfo) 
	{
		if(WebcreaInfo.CalendarMode == 0 || WebcreaInfo.CalendarMode == 1) this.currMonthOnly = 0;
		if(WebcreaInfo.CalendarMode == 1 || WebcreaInfo.CalendarMode == 3) this.startAt = 1;
	}
	this.showToday = 1;
	this.imgDir = '';

	this.crossobj, this.crossMonthObj, this.crossYearObj, this.monthSelected, this.yearSelected, this.dateSelected; 
	this.omonthSelected, this.oyearSelected, this.odateSelected, this.monthConstructed, this.yearConstructed;
	this.intervalID1, this.intervalID2, this.timeoutID1, this.timeoutID2, this.ctlToPlaceValue, this.ctlNow, this.dateFormat, this.nStartingYear;

	this.bPageLoaded=false;
	var today = new Date();
	this.dateNow = today.getDate();
	this.monthNow = today.getMonth();
	this.yearNow = today.getFullYear();

	this.dayNow=this.yearNow+this.monthNow+this.dateNow;

	this.bTimeShow = false;
	this.timeSelected = '00';
	this.minSelected = '00';
	this.secSelected = '00';
	this.selectObj = null;
	this.selectObj_b = null;

	this.imgsrc = new Array("down.png","drop2.png","arrow.png","ok.png","left2.png","right1.png","right2.png");
	this.img	= new Array()

	this.bShow = false;
	this.bInit = false;
	this.bInitYearMonth = false;

	this.monthName = new Array("1","2","3","4","5","6","7","8","9","10","11","12");
	if(this.startAt==1) this.dayName = new Array("MON","TUE","WED","THU","FRI","SAT","SUN");
	else this.dayName = new Array("SUN","MON","TUE","WED","THU","FRI","SAT");

	this.styleAnchor="text-decoration:none;color:black;";
	this.styleLightBorder="border-style:solid;border-width:1px;border-color:#a0a0a0;";

	this.DisplayCalendar = function()
	{
		var ctl = arguments[0];
		var ctl2 = arguments[1];
		var format = arguments[2];
		if(arguments.length>4) this.bYearMonth = arguments[4];
		else 
		{
			this.bYearMonth = false;
			this.bTimeShow = false;
			if(arguments[3] == 1) this.bYearMonth = true;
			else if(arguments[3] == 2) this.bTimeShow = true;
		}

		if(ctl && ctl.readOnly) return;
		this.HideCalendar();
		if(this.bYearMonth) this.CalendarYearMonthInit();
		else this.CalendarInit();
		this.FnPopUpCalendar(ctl, ctl2, format);
		if(this.bYearMonth) this.bInitYearMonth = true;
		else this.bInit = true;

		if(this.bTimeShow)
		{
			var tableTime = Webcrea.FindNode(this.currDivCalendar, 'id', 'tableTime');
			if(tableTime) 
			{
				this.timeObj = Webcrea.FindNode(tableTime, 'id', 'time');
				this.minObj = Webcrea.FindNode(tableTime, 'id', 'minute');
				this.secObj = Webcrea.FindNode(tableTime, 'id', 'second');
				this.timeObj.value = this.timeSelected;
				this.minObj.value = this.minSelected;
				this.secObj.value = this.secSelected;
			}
		}
	},
	this.SwapImage = function()
	{
		var srcImg = arguments[0];
		var destImg = arguments[1];
		if(Webcrea.IE()) 
		{
			var node = Webcrea.FindNode(this.divCalendarGroup, srcImg);
			if(node) node.setAttribute("src", this.imgDir + destImg);
		}
	},
	this.CalendarYearMonthInit = function()
	{
		if(Webcrea.IsEmpty(this.imgDir)) this.imgDir = Webcrea.GetImageLinkPath() +  "/frame/calendar/";
		for	(var i=0;i<this.imgsrc.length;i++)
		{
			this.img[i] = new Image;
			this.img[i].src = this.imgDir + this.imgsrc[i]
		}
		if(!this.divCalendarGroup) 
		{
			this.divCalendarGroup = document.createElement('div');
			this.divCalendarGroup.setAttribute('id', 'divCalendarGroup');
			document.body.appendChild(this.divCalendarGroup);
		}

		var divCalendar = Webcrea.FindNode(this.divCalendarGroup, 'id', 'divCalendarYearMonth');
		if(divCalendar) 
		{
			var calendar = Webcrea.FindNode(divCalendar, 'id', 'calendar');
			this.crossobj=calendar.style;
			this.crossMonthObj=null;
			var selectYear = Webcrea.FindNode(divCalendar, 'id', 'selectYear');
			this.crossYearObj=selectYear.style;
			this.monthConstructed=false;
			this.yearConstructed=false;
			this.currDivCalendar = divCalendar;
			return;
		}

		var divCalendar = document.createElement('div');
		divCalendar.setAttribute('id', 'divCalendarYearMonth');
		var str = "<div id='calendar' style='z-index:+9999;position:absolute;visibility:hidden;'><table cellspacing='0' style='font-family:arial;font-size:12px;border:solid;border-style:solid;border-color:#383838;' bgcolor='#383838'>";
		str += "<tr style='border:none' bgcolor='#323232'><td>";

		str += "<table style='width:100%;'><tr><td style='font-family:arial; font-size:12px;'><font color='#ffffff'><B><span id='caption'></span></B></font></td>";
		str += "<td><a title='Today' href='javascript:CalendarObj.dateSelected="+this.dateNow+";javascript:CalendarObj.monthSelected="+this.monthNow+";javascript:CalendarObj.yearSelected="+this.yearNow+";CalendarObj.CloseCalendar();'><IMG SRC='"+this.imgDir+"arrow.png' WIDTH='15' HEIGHT='14' BORDER='0' TITLE='Today' ALT='Close the Calendar'></a></td>";
		str += "<td align='right'><a href='javascript:CalendarObj.HideCalendar()'><IMG SRC='"+this.imgDir+"close.png' WIDTH='20' HEIGHT='20' BORDER='0' TITLE='Close the Calendar' ALT='Close the Calendar'></a></td>";
		str += "</tr></table>";

		str += "</td></tr>";
		str += "<tr><td style='padding:0px' bgcolor=#ffffff><span id='content'></span></td></tr></table>";
		str += "</div>";
		str += "<div id='selectYear' style='z-index:+9999;position:absolute;visibility:hidden;'></div>";
		divCalendar.innerHTML = str;
		this.divCalendarGroup.appendChild(divCalendar);

		var calendar = Webcrea.FindNode(divCalendar, 'id', 'calendar');
		this.crossobj=calendar.style;

		this.crossMonthObj=null;

		var selectYear = Webcrea.FindNode(divCalendar, 'id', 'selectYear');
		this.crossYearObj=selectYear.style;

		this.monthConstructed=false;
		this.yearConstructed=false;

		var sHTML1="<span id='spanLeft'	style='border-width:0;cursor:pointer' onmouseover='CalendarObj.SwapImage(\"changeLeft\",\"left2.png\");' onclick='javascript:CalendarObj.DecYear(true)' onmouseout='clearInterval(CalendarObj.intervalID1);CalendarObj.SwapImage(\"changeLeft\",\"left1.png\");window.status=\"\"' onmousedown='clearTimeout(CalendarObj.timeoutID1);CalendarObj.timeoutID1=setTimeout(\"CalendarObj.StartDecMonth()\",500)' onmouseup='clearTimeout(CalendarObj.timeoutID1);clearInterval(CalendarObj.intervalID1)'>&nbsp<IMG TITLE='Go to previous year' ALT='Go to previous year' id='changeLeft' SRC='"+this.imgDir+"left1.png' width=15 height=14 BORDER=0>&nbsp</span>&nbsp;";
		sHTML1+="<span id='spanYear' style='border-width:0;cursor:pointer' onmouseover='CalendarObj.SwapImage(\"changeYear\",\"drop2.png\");' onmouseout='CalendarObj.SwapImage(\"changeYear\",\"drop1.png\");window.status=\"\"' onclick='CalendarObj.PopUpYear()'></span>&nbsp;";
		sHTML1+="<span id='spanRight' style='border-width:0;cursor:pointer'	onmouseover='CalendarObj.SwapImage(\"changeRight\",\"right2.png\");' onmouseout='clearInterval(CalendarObj.intervalID1);CalendarObj.SwapImage(\"changeRight\",\"right1.png\");window.status=\"\"' onclick='CalendarObj.IncYear(true)' onmousedown='clearTimeout(CalendarObj.timeoutID1);CalendarObj.timeoutID1=setTimeout(\"CalendarObj.StartIncMonth()\",500)'	onmouseup='clearTimeout(CalendarObj.timeoutID1);clearInterval(CalendarObj.intervalID1)'>&nbsp<IMG TITLE='Go to next year' ALT='Go to next year' id='changeRight' SRC='"+this.imgDir+"right1.png' width=15 height=14 BORDER=0>&nbsp</span>&nbsp";
		var captionNode = Webcrea.FindNode(divCalendar, 'id', 'caption');
		captionNode.innerHTML = sHTML1;
		this.bPageLoaded=true;
		this.currDivCalendar = divCalendar;
	},
	this.CalendarInit = function()
	{
		if(Webcrea.IsEmpty(this.imgDir)) this.imgDir = Webcrea.GetImageLinkPath() +  "/frame/calendar/";
		for(var i=0;i<this.imgsrc.length;i++)
		{
			this.img[i] = new Image
			this.img[i].src = this.imgDir + this.imgsrc[i]
		}
		if(!this.divCalendarGroup) 
		{
			this.divCalendarGroup = document.createElement('div');
			this.divCalendarGroup.setAttribute('id', 'divCalendarGroup');
			document.body.appendChild(this.divCalendarGroup);
		}

		var divCalendar = Webcrea.FindNode(this.divCalendarGroup, 'id', 'divCalendar');
		if(divCalendar) 
		{
			var calendar = Webcrea.FindNode(divCalendar, 'id', 'calendar');
			this.crossobj=calendar.style;
			var selectMonth = Webcrea.FindNode(divCalendar, 'id', 'selectMonth');
			this.crossMonthObj=selectMonth.style;
			var selectYear = Webcrea.FindNode(divCalendar, 'id', 'selectYear');
			this.crossYearObj=selectYear.style;
			this.monthConstructed=false;
			this.yearConstructed=false;
			this.currDivCalendar = divCalendar;

			var timeObj = Webcrea.FindNode(divCalendar, 'id', 'tableTime');
			if(timeObj)
			{
				if(this.bTimeShow) timeObj.style.visibility = '';
				else timeObj.style.visibility = 'hidden';
			}
			return;
		}
		divCalendar = document.createElement('div');
		divCalendar.setAttribute('id', 'divCalendar');
		var str = "<div id='calendar' style='z-index:+9999;position:absolute;visibility:hidden;'><table cellspacing='0' width='210' style='font-family:arial;font-size:12px;border:solid;border-style:solid;border-color:#383838;' bgcolor='#383838'>";
		str += "<tr style='border:none' bgcolor='#323232'><td>";
		
		str += "<table style='width:100%'><tr><td style='font-family:arial; font-size:12px;'><font color='#ffffff'><B><span id='caption'></span></B></font></td>";
		str += "<td><a id='today' title='Today' href='javascript:CalendarObj.dateSelected="+this.dateNow+";javascript:CalendarObj.monthSelected="+this.monthNow+";javascript:CalendarObj.yearSelected="+this.yearNow+";CalendarObj.CloseCalendar(true);'><IMG id='today' SRC='"+this.imgDir+"arrow.png' WIDTH='15' HEIGHT='14' BORDER='0' TITLE='Today' ALT='Close the Calendar'></a></td>";
		str += "<td><a id='ok' title='OK' href='javascript:CalendarObj.CloseCalendar();'><IMG id='ok' SRC='"+this.imgDir+"ok.png' WIDTH='15' HEIGHT='14' BORDER='0' TITLE='OK' ALT='Close the Calendar'></a></td>";
		str += "<td align='right'><a href='javascript:CalendarObj.HideCalendar()'><IMG SRC='"+this.imgDir+"close.png' WIDTH='20' HEIGHT='20' BORDER='0' TITLE='Close the Calendar' ALT='Close the Calendar'></a></td>";
		str += "</tr></table>";
		
		str += "</td></tr>";
		str += "<tr><td style='padding:0px' bgcolor=#ffffff><span id='content'></span></td></tr></table>";

		var sTime = "<table id='tableTime' style='font-family:arial;font-size:12px;width:210px;background:#ffffff;visibility:hidden;'>";
		sTime += "<tr><td colspan=3 style='height:5px;'></td></tr>";
		sTime += "<tr><td><select id='time' onchange='CalendarObj.SelectTime(this);' style='width:40px;height:25px;'></select> " + Webcrea.LoadString('idsTimeMinSecName')[0] + "</td>";
		sTime += "<td><select id='minute' onchange='CalendarObj.SelectTime(this);' style='width:40px;height:25px;'></select> "+ Webcrea.LoadString('idsTimeMinSecName')[1] + "</td>";
		sTime += "<td><select id='second' onchange='CalendarObj.SelectTime(this);' style='width:40px;height:25px;'></select> "+ Webcrea.LoadString('idsTimeMinSecName')[2] + "</td></tr>";
		sTime += "</table>";
		str += sTime;

		str += '</div>';
		str += "<div id='selectMonth' style='z-index:+9999;position:absolute;visibility:hidden;'></div><div id='selectYear' style='z-index:+9999;position:absolute;visibility:hidden;'></div>";
		divCalendar.innerHTML = str;
		this.divCalendarGroup.appendChild(divCalendar);

		var calendar = Webcrea.FindNode(divCalendar, 'id', 'calendar');
		this.crossobj=calendar.style;

		var selectMonth = Webcrea.FindNode(divCalendar, 'id', 'selectMonth');
		this.crossMonthObj=selectMonth.style;

		var selectYear = Webcrea.FindNode(divCalendar, 'id', 'selectYear');
		this.crossYearObj=selectYear.style;

		this.monthConstructed=false;
		this.yearConstructed=false;
		var sHTML1="<span id='spanLeft'	style='border-width:0;cursor:pointer' onmouseover='CalendarObj.SwapImage(\"changeLeft\",\"left2.png\");' onclick='javascript:CalendarObj.DecMonth()' onmouseout='clearInterval(CalendarObj.intervalID1);CalendarObj.SwapImage(\"changeLeft\",\"left1.png\");window.status=\"\"' onmousedown='clearTimeout(CalendarObj.timeoutID1);CalendarObj.timeoutID1=setTimeout(\"CalendarObj.StartDecMonth()\",500)' onmouseup='clearTimeout(CalendarObj.timeoutID1);clearInterval(CalendarObj.intervalID1)'>&nbsp<IMG TITLE='Go to previous month' ALT='Go to previous month' id='changeLeft' SRC='"+this.imgDir+"left1.png' width=15 height=14 BORDER=0>&nbsp</span>&nbsp;";
		sHTML1+="<span id='spanYear' style='border-width:0;cursor:pointer' onmouseover='CalendarObj.SwapImage(\"changeYear\",\"drop2.png\");' onmouseout='CalendarObj.SwapImage(\"changeYear\",\"drop1.png\");window.status=\"\"' onclick='CalendarObj.PopUpYear()'></span>&nbsp;";
		sHTML1+="<span id='spanMonth' style='border-width:0;cursor:pointer'	onmouseover='CalendarObj.SwapImage(\"changeMonth\",\"drop2.png\");' onmouseout='CalendarObj.SwapImage(\"changeMonth\",\"drop1.png\");window.status=\"\"' onclick='CalendarObj.PopUpMonth()'></span>&nbsp;";
		sHTML1+="<span id='spanRight' style='border-width:0;cursor:pointer'	onmouseover='CalendarObj.SwapImage(\"changeRight\",\"right2.png\");' onmouseout='clearInterval(CalendarObj.intervalID1);CalendarObj.SwapImage(\"changeRight\",\"right1.png\");window.status=\"\"' onclick='CalendarObj.IncMonth()' onmousedown='clearTimeout(CalendarObj.timeoutID1);CalendarObj.timeoutID1=setTimeout(\"CalendarObj.StartIncMonth()\",500)'	onmouseup='clearTimeout(CalendarObj.timeoutID1);clearInterval(CalendarObj.intervalID1)'>&nbsp<IMG TITLE='Go to next month' ALT='Go to next month' id='changeRight' SRC='"+this.imgDir+"right1.png' width=15 height=14 BORDER=0>&nbsp</span>&nbsp";
		var captionNode = Webcrea.FindNode(divCalendar, 'id', 'caption');
		captionNode.innerHTML = sHTML1;
		this.bPageLoaded=true;
		this.currDivCalendar = divCalendar;

		var timeObj = Webcrea.FindNode(divCalendar, 'id', 'tableTime');
		if(timeObj)
		{
			if(this.bTimeShow) 
			{
				var currObj = this;
				var SetOpt = function(obj, seq)
				{
					var strOption = '';
					for(var i=0; i<=seq; i++)
					{
						var sData = currObj.PadZero(i);
						strOption += "<option>" + sData + "</option>";
					}
					obj.innerHTML = strOption;
				};
				var time = Webcrea.FindNode(timeObj, 'id', 'time');
				if(time && !time.childern) SetOpt(time, 24);
				var minute = Webcrea.FindNode(timeObj, 'id', 'minute');
				if(minute && !minute.childern) SetOpt(minute, 59);
				var second = Webcrea.FindNode(timeObj, 'id', 'second');
				if(second && !second.childern) SetOpt(second, 59);
				timeObj.style.visibility = '';
			}
			else timeObj.style.visibility = 'hidden';
		}
	},
	this.HideCalendar = function()
	{
		if(this.crossobj != null) this.crossobj.visibility="hidden";
		if(this.crossMonthObj != null) this.crossMonthObj.visibility="hidden";
		if(this.crossYearObj !=	null) this.crossYearObj.visibility="hidden";
	},
	this.PadZero = function()
	{
		var num = arguments[0];
		return (num	< 10)? '0' + num : num;
	},
	this.CloseCalendar = function(bToday)
	{
		if(CalendarObj.bDateSelect || bToday)
		{
			this.odateSelected=this.dateSelected;
			this.omonthSelected=this.monthSelected;
			this.oyearSelected=this.yearSelected;
		}

		if(!this.bTimeShow || !CalendarObj.bDateSelect) this.HideCalendar();
		var value = this.oyearSelected.toString() + this.PadZero(this.omonthSelected+1).toString();
		if(!this.bYearMonth) value += this.PadZero(this.odateSelected).toString();

		if(this.bTimeShow) 
		{
			var currTime = CurTime();
			if(!bToday) currTime = this.timeSelected + this.minSelected + this.secSelected;
			value += currTime;
		}
		if(!this.bTimeShow || !CalendarObj.bDateSelect)
		{
			var targetObj = Webcrea.GetObject(this.ctlToPlaceValue);
			if(targetObj)
			{
				var targetId = '';
				if(Webcrea.IsCrossTab(targetObj)) targetId = targetObj.GetCellIdByCrossTab(this.ctlToPlaceValue);
				else targetId = targetObj.GetCellId(this.ctlToPlaceValue);
				if(targetId) targetObj.SetValue(targetId, value);
			}
		}
		else
		{
			if(this.selectObj_b)
			{
				if(this.selectObj_b.tagName == 'FONT' || this.selectObj_b.tagName == 'A') 
				{
					if(this.selectObj_b.tagName == 'FONT') this.selectObj_b.color = '#474747';
					else if(this.selectObj_b.children[0]) this.selectObj_b.children[0].color = '#474747';
					var tdNode = Webcrea.FindParentNode(this.selectObj_b, 'name', 'TD');
					if(tdNode) tdNode.style.backgroundColor = '#FFFFFF';
				}
			}
			
			if(this.selectObj)
			{
				if(this.selectObj.tagName == 'FONT' || this.selectObj.tagName == 'A') 
				{
					if(this.selectObj.tagName == 'FONT') this.selectObj.color = '#ffffff';
					else if(this.selectObj.children[0]) this.selectObj.children[0].color = '#ffffff';
					var tdNode = Webcrea.FindParentNode(this.selectObj, 'name', 'TD');
					if(tdNode) tdNode.style.backgroundColor = '#FFB21E';
				}
			}
		}
	},
	this.SelectTime = function()
	{
		var obj = arguments[0];
		if(obj)
		{
			var value = obj.value;
			if(obj.id == 'time') this.timeSelected = value;
			else if(obj.id == 'minute') this.minSelected = value;
			else if(obj.id == 'second') this.secSelected = value;
		}
	},
	this.StartDecMonth = function()
	{
		this.intervalID1=setInterval("DecMonth()",80);
	},
	this.StartIncMonth = function()
	{
		this.intervalID1=setInterval("IncMonth()",80)
	},
	this.IncMonth = function()
	{
		this.PopDownYear();
		this.PopDownMonth();
		this.monthSelected++;
		if(this.monthSelected>11) 
		{
			this.monthSelected=0;
			this.yearSelected++;
		}
		this.ConstructCalendar();
		this.bShow=true;
	},
	this.DecMonth = function()
	{
		this.PopDownYear();
		this.PopDownMonth();
		this.monthSelected--;
		if(this.monthSelected<0)
		{
			this.monthSelected=11;
			this.yearSelected--;
		}
		this.ConstructCalendar();
		this.bShow=true;
	},
	this.ConstructMonth = function()
	{
		this.PopDownYear();
		if(!this.monthConstructed) 
		{
			var sHTML =	"";
			for(var i=0; i<12;	i++) 
			{
				var sName =	this.monthName[i];
				if(i==this.monthSelected) sName = "<B>" + sName + "</B>";
				sHTML += "<tr><td id='m" + i + "' onmouseover='this.style.backgroundColor=\"#cee7ff\"' onmouseout='this.style.backgroundColor=\"\"' style='cursor:pointer' onclick='CalendarObj.monthConstructed=false;CalendarObj.monthSelected=" + i + ";CalendarObj.ConstructCalendar();CalendarObj.PopDownMonth();event.cancelBubble=true'>&nbsp;" + sName + "&nbsp;</td></tr>";
			}
			var node = Webcrea.FindNode(this.currDivCalendar, 'id', 'selectMonth');
			node.innerHTML = "<table width=70 style='font-family:arial; font-size:11px; border-width:1; border-style:solid; border-color:#a0a0a0;' bgcolor='white' cellspacing=0 onmouseover='clearTimeout(CalendarObj.timeoutID1)'	onmouseout='clearTimeout(CalendarObj.timeoutID1);CalendarObj.timeoutID1=setTimeout(\"CalendarObj.PopDownMonth()\",100);event.cancelBubble=true'>" +	sHTML +	"</table>";
			this.monthConstructed=true;
		}
	},
	this.PopUpMonth = function()
	{
		this.ConstructMonth();
		this.crossMonthObj.visibility = "visible";
		this.crossMonthObj.left = parseInt(this.crossobj.left) + 50 + "px";
		this.crossMonthObj.top = parseInt(this.crossobj.top) + 26 + "px";
		this.bShow=true;
	},
	this.PopDownMonth = function()
	{
		if(this.crossMonthObj) this.crossMonthObj.visibility= "hidden";
	},
	this.IncYear = function(bTitle)
	{
		if(!bTitle)
		{
			var txtYear = '';
			for	(var i=0; i<7; i++)
			{
				var newYear	= (i+this.nStartingYear)+1;
				if(newYear==this.yearSelected) txtYear = "&nbsp;<B>" + newYear + "</B>&nbsp;";
				else txtYear = "&nbsp;" + newYear + "&nbsp;";
				var node = Webcrea.FindNode(this.currDivCalendar, 'id', 'y'+i);
				node.innerHTML = txtYear;
			}
			this.nStartingYear++;
		}
		else
		{
			this.PopDownYear();
			this.yearSelected++;
			this.ConstructYearMonthCalendar();
		}
		this.bShow=true;
	},
	this.DecYear = function(bTitle)
	{
		if(!bTitle)
		{
			var txtYear = '';
			for(var i=0; i<7; i++)
			{
				var newYear	= (i+this.nStartingYear)-1;
				if (newYear==this.yearSelected) txtYear = "&nbsp;<B>" + newYear + "</B>&nbsp;";
				else txtYear = "&nbsp;" + newYear + "&nbsp;";
				var node = Webcrea.FindNode(this.currDivCalendar, 'id', 'y'+i);
				node.innerHTML = txtYear;
			}
			this.nStartingYear--;
		}
		else
		{
			this.PopDownYear();
			this.yearSelected--;
			this.ConstructYearMonthCalendar();
		}
		this.bShow=true;
	},
	this.SelectYear = function()
	{
		var nYear = arguments[0];
		this.yearSelected=parseInt(nYear+this.nStartingYear);
		this.yearConstructed=false;
		if(this.bYearMonth) this.ConstructYearMonthCalendar();
		else this.ConstructCalendar();
		this.PopDownYear();
	},
	this.ConstructYear = function()
	{
		this.PopDownMonth();
		if (!this.yearConstructed) 
		{
			var sHTML =	"<tr><td align='center'	onmouseover='this.style.backgroundColor=\"#cee7ff\"' onmouseout='clearInterval(CalendarObj.intervalID1);this.style.backgroundColor=\"\"' style='cursor:pointer'	onmousedown='clearInterval(CalendarObj.intervalID1);CalendarObj.intervalID1=setInterval(\"CalendarObj.DecYear()\",30)' onmouseup='clearInterval(CalendarObj.intervalID1)'>-</td></tr>";
			var j = 0;
			this.nStartingYear = this.yearSelected-3;
			for(var i=(this.yearSelected-3); i<=(this.yearSelected+3); i++) 
			{
				var sName =	i;
				if(i==this.yearSelected) sName =	"<B>" +	sName +	"</B>";
				sHTML += "<tr><td id='y" + j + "' onmouseover='this.style.backgroundColor=\"#cee7ff\"' onmouseout='this.style.backgroundColor=\"\"' style='cursor:pointer' onclick='CalendarObj.SelectYear("+j+");event.cancelBubble=true'>&nbsp;" + sName + "&nbsp;</td></tr>";
				j++;
			}
			sHTML += "<tr><td align='center' onmouseover='this.style.backgroundColor=\"#cee7ff\"' onmouseout='clearInterval(CalendarObj.intervalID2);this.style.backgroundColor=\"\"' style='cursor:pointer' onmousedown='clearInterval(CalendarObj.intervalID2);CalendarObj.intervalID2=setInterval(\"CalendarObj.IncYear()\",30)'	onmouseup='clearInterval(CalendarObj.intervalID2)'>+</td></tr>";
			var node = Webcrea.FindNode(this.currDivCalendar, 'id', 'selectYear');
			node.innerHTML	= "<table width=44 style='font-family:arial; font-size:11px; border-width:1; border-style:solid; border-color:#a0a0a0;'	bgcolor='white' onmouseover='clearTimeout(CalendarObj.timeoutID2)' onmouseout='clearTimeout(CalendarObj.timeoutID2);CalendarObj.timeoutID2=setTimeout(\"CalendarObj.PopDownYear()\",100)' cellspacing=0>"	+ sHTML	+ "</table>";
			this.yearConstructed = true;
		}
	},
	this.PopDownYear = function()
	{
		clearInterval(this.intervalID1);
		clearTimeout(this.timeoutID1);
		clearInterval(this.intervalID2);
		clearTimeout(this.timeoutID2);
		this.crossYearObj.visibility= "hidden";
	},
	this.PopUpYear = function()
	{
		this.ConstructYear();
		this.crossYearObj.visibility = "visible";
		var spanYearNode = Webcrea.FindNode(this.currDivCalendar, 'id', 'spanYear');
		var leftOffset = parseInt(this.crossobj.left) + spanYearNode.offsetLeft;
		if(Webcrea.IE()) leftOffset += 6;
		this.crossYearObj.left = leftOffset + "px";
		this.crossYearObj.top = parseInt(this.crossobj.top) + 26 + "px";
		this.bShow=true;
		
	},
	this.WeekNbr = function()
	{
		var day=0;
		var dateObj = arguments[0];
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		if(this.startAt == 0) day = dateObj.getDate() + 1;
		else day = dateObj.getDate();

		var a = Math.floor((14-month) / 12);
		var y = year + 4800 - a;
		var m = month + 12 * a - 3;
		var b = Math.floor(y/4) - Math.floor(y/100) + Math.floor(y/400);
		var J = day + Math.floor((153 * m + 2) / 5) + 365 * y + b - 32045;
		var d4 = (((J + 31741 - (J % 7)) % 146097) % 36524) % 1461;
		var L = Math.floor(d4 / 1460);
		var d1 = ((d4 - L) % 365) + L;
		var week = Math.floor(d1/7) + 1;
		return week;
	},
	this.ConstructYearMonthCalendar = function()
	{
		var sHTML =	"<table	border=0 style='border-collapse:separate;font-family:verdana;font-size:11px;background: #f1f1f1;'><tr>";
		for	(var i=0; i<12; i++)
		{
			var sStyle = "text-decoration: none;display:block;width:100%;height:100%";
			var month = i+1;
			var fStyle = "";
			if((this.monthSelected==i) && (this.yearSelected==this.oyearSelected))
			{ 
				sHTML += "<td bgcolor=#FFB21E align=center style='border: solid;border-width:1px;border-color:#ECECEC;width:22px;height:22px'>";
				fStyle="#FFFFFF";
			}
			else 
			{
				sHTML += "<td bgcolor=#FFFFFF align=center style='border:solid;border-width:1px;border-color:#ECECEC;width:22px;height:22px'>";
				fStyle = "#474747";
			}
			sHTML += "<a style='"+sStyle+";text-decoration:none' href='javascript:CalendarObj.monthSelected="+i+";CalendarObj.CloseCalendar();'><font color="+fStyle+">&nbsp;" + month + "</font>&nbsp;</a>";
			sHTML += "</td>";
			if(i==5) sHTML +="</tr><tr>";
		}
		sHTML +="</table>";
		var contentNode = Webcrea.FindNode(this.currDivCalendar, 'id', 'content');
		if(contentNode) contentNode.innerHTML = sHTML;

		var spanYearNode = Webcrea.FindNode(this.currDivCalendar, 'id', 'spanYear');
		if(spanYearNode) spanYearNode.innerHTML = "&nbsp;" + this.yearSelected + "&nbsp;<IMG TITLE='Year of change' ALT='Year of change' id='changeYear' SRC='"+this.imgDir+"drop1.png' WIDTH='15' HEIGHT='14' BORDER=0>";
	},
	this.ConstructCalendar = function()
	{
		var aNumDays = Array(31,0,31,30,31,30,31,31,30,31,30,31);
		var	startDate =	new	Date(this.yearSelected, this.monthSelected, 1);
		var endDate;
		var numDaysInMonth;
		if(this.monthSelected==1)
		{
			endDate	= new Date(this.yearSelected, this.monthSelected+1, 1);
			endDate	= new Date(endDate	- (24*60*60*1000));
			numDaysInMonth = endDate.getDate();
		}
		else numDaysInMonth = aNumDays[this.monthSelected];

		var yearBeSelected = this.yearSelected;
		var monthBeSelected = this.monthSelected-1;
		if(monthBeSelected<0) 
		{
			monthBeSelected = 11;
			yearBeSelected--;
		}
		var numDaysBeMonth;
		if(monthBeSelected==1)
		{
			var endDateBe;
			endDateBe	= new Date(this.yearSelected, monthBeSelected+1, 1);
			endDateBe	= new Date(endDateBe	- (24*60*60*1000));
			numDaysBeMonth = endDateBe.getDate();
		}
		else numDaysBeMonth = aNumDays[monthBeSelected];

		var yearAfSelected = this.yearSelected;
		var monthAfSelected = this.monthSelected+1;
		if(monthAfSelected>11) 
		{
			monthAfSelected = 1;
			yearAfSelected++;
		}
		var numDaysAfMonth;
		if(monthAfSelected==1)
		{
			var endDateBe;
			endDateBe	= new Date(this.yearSelected, monthAfSelected+1, 1);
			endDateBe	= new Date(endDateBe	- (24*60*60*1000));
			numDaysAfMonth = endDateBe.getDate();
		}
		else numDaysAfMonth = aNumDays[monthAfSelected];

		var datePointer	= 0;
		dayPointer = startDate.getDay() - this.startAt;
		if(dayPointer<0) dayPointer = 6;

		var sHTML =	"<table	 border=0 style='font-family:verdana;font-size:11px;background: #f1f1f1;'><tr>";
		for	(var i=0; i<7; i++)
		{
			if(this.dayName[i] =='SUN') sHTML += "<td width='36' height='18' align='center' bgColor='#EB6666' style='color:#ffffff;font-family:arial;font-style: normal;font-size:9px;text-align:center;'>"+ this.dayName[i]+"</td>";
			else sHTML += "<td width='36' height='18' align='center' bgColor='#474747' style='color:#ffffff;font-family:arial;font-style: normal;font-size:9px;text-align:center;'>"+ this.dayName[i]+"</td>";
		}
		sHTML +="</tr><tr>";
		
		var currObj = this;
		var SetNotCurrDayHtml = function(nYear, nMonth, nDay, nSeq)
		{
			var sStyle=currObj.styleAnchor;
			sHTML += "<td align=center height=20 style='border: solid;border-width: 1px;border-color: #ECECEC;'>";
			if(nSeq % 7 == (currObj.startAt * -1)+1)					fStyle="#EB6666";
			else if	(nSeq % 7 == (7-currObj.startAt)*currObj.startAt)	fStyle="#3492EC";
			else														fStyle="#474747";

			var sHint = "";
			var sDate = nYear.toString() + Webcrea.AddZero(nMonth+1,2) + Webcrea.AddZero(nDay);
			if(Webcrea.IsHoliday(sDate, true)) 
			{
				fStyle="#EB6666";
				var hoildayObj = Webcrea.GetHoliday(sDate);
				if(hoildayObj) sHint = hoildayObj.name;
			}
			var regexp= /\"/g;
			sHint=sHint.replace(regexp,"&quot;");
			sStyle=sStyle+"display:block;width:100%;height:100%";			
			if(nSeq % 7 == (currObj.startAt * -1)+1)
			{ 
				sHTML += "<a day='day' title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:CalendarObj.yearSelected=" + nYear + ";CalendarObj.monthSelected=" + nMonth + ";CalendarObj.dateSelected=" + nDay + ";CalendarObj.CloseCalendar();'>&nbsp;<font color="+fStyle+">" + nDay + "</font>&nbsp;</a>";
			}
			else if(nSeq % 7 == 0)
			{ 
				sHTML += "<a day='day' title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:CalendarObj.yearSelected=" + nYear + ";CalendarObj.monthSelected=" + nMonth + ";CalendarObj.dateSelected=" + nDay + ";CalendarObj.CloseCalendar();'>&nbsp;<font color="+fStyle+">" + nDay + "</font>&nbsp;</a>";
			}
			else
			{ 
				sHTML += "<a day='day' title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:CalendarObj.yearSelected=" + nYear + ";CalendarObj.monthSelected=" + nMonth + ";CalendarObj.dateSelected=" + nDay + ";CalendarObj.CloseCalendar();'>&nbsp;<font color="+fStyle+">" + nDay + "&nbsp;</a>";
			}
		};
		
		for(var i=1; i<=dayPointer; i++)
		{
			if(this.currMonthOnly) sHTML += "<td >&nbsp;</td>";
			else 
			{
				var nYear = yearBeSelected;
				var nMonth = monthBeSelected;
				var nDay = numDaysBeMonth-dayPointer+i;
				SetNotCurrDayHtml(nYear, nMonth, nDay, i);
			}
		}

		for(datePointer=1; datePointer<=numDaysInMonth; datePointer++)
		{
			dayPointer++;
			var sStyle=this.styleAnchor;
			sStyle='text-decoration: none;';
			var fStyle='';
			if((datePointer==this.odateSelected) && (this.monthSelected==this.omonthSelected) && (this.yearSelected==this.oyearSelected))
			{ 
				sHTML += "<td select='select' bgcolor=#FFB21E align=center height=20 style='border: solid;border-width: 1px;border-color: #ECECEC;'>";
				fStyle="#ffffff";
			}
			else
			{
				sHTML += "<td bgcolor=#ffffff align=center height=20 style='border: solid;border-width: 1px;border-color: #ECECEC;'>";
				if(dayPointer % 7 == (this.startAt * -1)+1) 				fStyle="#EB6666";
				else if	(dayPointer % 7 == (7-this.startAt)*this.startAt)	fStyle="#3492EC";
				else														fStyle="#474747";
			}

			var sHint = "";
			var sDate = this.yearSelected.toString() + Webcrea.AddZero(this.monthSelected+1,2) + Webcrea.AddZero(datePointer);
			if(Webcrea.IsHoliday(sDate, true)) 
			{
				fStyle="#EB6666";
				var hoildayObj = Webcrea.GetHoliday(sDate);
				if(hoildayObj) sHint = hoildayObj.name;
			}
			var regexp= /\"/g;
			sHint=sHint.replace(regexp,"&quot;");
			sStyle=sStyle+"display:block;width:100%;height:100%";
			if ((datePointer==this.dateNow)&&(this.monthSelected==this.monthNow)&&(this.yearSelected==this.yearNow))
			{ 
				sHTML += "<a day='day' title=\"" + sHint + "\" style='"+sStyle+";text-decoration:none' href='javascript:CalendarObj.dateSelected="+datePointer+";CalendarObj.CloseCalendar();'><font color="+fStyle+">&nbsp;" + datePointer + "</font>&nbsp;</a>";
			}
			else if(dayPointer % 7 == (this.startAt * -1)+1)
			{ 
				sHTML += "<a day='day' title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:CalendarObj.dateSelected="+datePointer + ";CalendarObj.CloseCalendar();'>&nbsp;<font color="+fStyle+">" + datePointer + "</font>&nbsp;</a>";
			}
			else if(dayPointer % 7 == 0)
			{ 
				sHTML += "<a day='day' title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:CalendarObj.dateSelected="+datePointer + ";CalendarObj.CloseCalendar();'>&nbsp;<font color="+fStyle+">" + datePointer + "</font>&nbsp;</a>";
			}
			else
			{ 
				sHTML += "<a day='day' title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:CalendarObj.dateSelected="+datePointer + ";CalendarObj.CloseCalendar();'>&nbsp;<font color="+fStyle+">" + datePointer + "&nbsp;</a>";
			}

			sHTML += ""
			if ((dayPointer+this.startAt) % 7 == this.startAt) 
			{
				sHTML += "</tr><tr>";
			}
			else
			{
				if(!this.currMonthOnly && datePointer==numDaysInMonth)
				{
					var nSeq = dayPointer % 7;
					if(nSeq>0)
					{
						nSeq++;
						var nDay = 0;
						for(var i1=nSeq; i1<=7; i1++)
						{
							nDay++;							
							var nYear = yearAfSelected;
							var nMonth = monthAfSelected;
							SetNotCurrDayHtml(nYear, nMonth, nDay, i1);
						}
					}
				}				
			}
		}
		var contentNode = Webcrea.FindNode(this.currDivCalendar, 'id', 'content');
		if(contentNode) 
		{
			contentNode.innerHTML = sHTML;
			var selTdNode = Webcrea.FindNode(contentNode, 'select', 'select');
			if(selTdNode) this.selectObj = Webcrea.FindNode(selTdNode, 'name', 'A');
		}

		var spanMonthNode = Webcrea.FindNode(this.currDivCalendar, 'id', 'spanMonth');
		if(spanMonthNode) spanMonthNode.innerHTML = "&nbsp;" + this.monthName[this.monthSelected] + "&nbsp;<IMG TITLE='Month of change' ALT='Month of change' id='changeMonth' SRC='"+this.imgDir+"drop1.png' WIDTH='15' HEIGHT='14' BORDER=0>";
		
		var spanYearNode = Webcrea.FindNode(this.currDivCalendar, 'id', 'spanYear');
		if(spanYearNode) spanYearNode.innerHTML = "&nbsp;" + this.yearSelected + "&nbsp;<IMG TITLE='Year of change' ALT='Year of change' id='changeYear' SRC='"+this.imgDir+"drop1.png' WIDTH='15' HEIGHT='14' BORDER=0>";
	},
	this.FnPopUpCalendar = function()
	{
		var ctl = arguments[0];
		var ctl2 = arguments[1];
		var format = arguments[2];

		var	leftpos=0;
		var	toppos=0;
		
		var bDay = true;
		if(!Webcrea.IsEmpty(format) && format.length < 8) 
		{
			bDay = false;
		}
		if(this.bPageLoaded)
		{
			if(this.crossobj.visibility == "hidden") 
			{
				this.ctlToPlaceValue = ctl2;
				this.dateFormat=format;

				var formatChar = '';
				var formatMode = '';
				var arrFormatChar = [];
				var arrFormatMode = [];
				for(var i=0; i<this.dateFormat.length; i++)
				{
					if(this.dateFormat[i] == 'y' || this.dateFormat[i] == 'm' || this.dateFormat[i] == 'd' || this.dateFormat[i] == '0') 
					{
						if(!Webcrea.IsEmpty(formatChar))
						{
							if(formatMode == '0000') formatMode = 'yyyy';
							else if(formatMode == '00')
							{
								if(arrFormatMode.length == 1) formatMode = 'mm';
								else if(arrFormatMode.length == 2) formatMode = 'dd';
							}
							arrFormatChar[arrFormatChar.length] = formatChar;
							arrFormatMode[arrFormatMode.length] = formatMode;
							formatChar = '';
							formatMode = '';
						}
						formatMode += this.dateFormat[i];
						continue;
					}
					else formatChar += this.dateFormat[i];
				}
				if(!Webcrea.IsEmpty(formatMode))
				{
					arrFormatChar[arrFormatChar.length] = formatChar;
					arrFormatMode[arrFormatMode.length] = formatMode;
				}
				var nStart = 0;
				var sValue = Webcrea.FormatValue(ctl2, false);
				var sTime = '';
				for(var i=0; i<arrFormatMode.length; i++)
				{
					var nLen = arrFormatMode[i].length;
					var sSelected = sValue.substr(nStart, nLen);
					if(arrFormatMode[i].indexOf('y')>=0) this.yearSelected = parseInt(sSelected, 10);
					else if(arrFormatMode[i].indexOf('m')>=0) this.monthSelected = parseInt(sSelected, 10) - 1;
					else if(arrFormatMode[i].indexOf('d')>=0) this.dateSelected = parseInt(sSelected, 10);
					else if(this.bTimeShow) sTime += sSelected;
					nStart += nLen;
				}
				if(Webcrea.IsNull(this.yearSelected) || Webcrea.IsNull(this.monthSelected)) 
				{
					this.yearSelected = this.yearNow;
					this.monthSelected = this.monthNow;
					if(bDay) this.dateSelected = this.dateNow;
				}
				var sCheck = this.yearSelected.toString() + this.PadZero(this.monthSelected+1).toString();
				if(bDay && !Webcrea.IsNull(this.dateSelected)) sCheck += this.PadZero(this.dateSelected).toString();
				else sCheck += '01';
				if(!IsValidDate(sCheck) || isNaN(this.monthSelected) || isNaN(this.yearSelected)) 
				{
					this.monthSelected = this.monthNow;
					this.yearSelected = this.yearNow;
					if(bDay) this.dateSelected = this.dateNow;
				}
				if(isNaN(this.monthSelected)||isNaN(this.yearSelected))
				{
					this.monthSelected = this.monthNow;
					this.yearSelected = this.yearNow;
				}
				if(isNaN(this.dateSelected))
				{
					if(bDay) this.dateSelected = this.dateNow;
					else this.dateSelected = 1;
				}
				if(!Webcrea.IsEmpty(sTime))
				{
					this.timeSelected = sTime.substr(0, 2);
					this.minSelected = sTime.substr(2, 2);
					this.secSelected = sTime.substr(4, 2);
				}

				this.odateSelected=this.dateSelected;
				this.omonthSelected=this.monthSelected;
				this.oyearSelected=this.yearSelected;

				if(this.bYearMonth) this.ConstructYearMonthCalendar ();
				else this.ConstructCalendar ();
				this.crossobj.visibility="visible";
				this.bShow = true;				
				
				var aTag = ctl;
				do {
					aTag = aTag.offsetParent;
					leftpos	+= aTag.offsetLeft;
					leftpos	-= aTag.scrollLeft;
					toppos += aTag.offsetTop;
				} while(aTag.tagName!="BODY");
				var ctlLeft = ctl.offsetLeft - ctl.scrollLeft;
				var ctlTop = ctl.offsetTop - ctl.scrollTop;
				var vArr=ctl.getClientRects()[0];
				var cArr=Webcrea.FindNode(this.currDivCalendar, 'id', 'calendar');
				cArr=cArr.getClientRects()[0];
				var vLeft= window.innerWidth - leftpos- cArr.width -3;
				if(vLeft < 0){
					leftpos= vArr.right- cArr.width -3;
					this.crossobj.left = this.fixedX==-1 ? leftpos + "px" : this.fixedX;					
				}else{
					this.crossobj.left = this.fixedX==-1 ? ctlLeft + leftpos + 3 + "px" : this.fixedX;					
				}
				var vTop= window.innerHeight - toppos - vArr.height - cArr.height - 5;
				if(vTop < 0){
					toppos= ctlTop + toppos + ctl.offsetHeight- cArr.height- vArr.height;
					if(!this.bTimeShow && !this.bYearMonth) toppos=toppos + document.getElementById("tableTime").clientHeight; 
					this.crossobj.top = this.fixedY==-1 ? toppos + "px" :	this.fixedY;
				}else{
					this.crossobj.top = this.fixedY==-1 ? ctlTop + toppos + ctl.offsetHeight + 5 + "px" :	this.fixedY;
				}
			}
			else
			{
				this.HideCalendar();
				if (this.ctlNow != ctl) this.FnPopUpCalendar(ctl, ctl2, format);
			}
			this.ctlNow = ctl;
		}
	}
};