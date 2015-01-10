<!--
document.write("<div id=calendarLayer style='position: absolute; z-index: 9999; width: 144; height: 193; display: none'>");
document.write("<iframe name=calendarIframe scrolling=no frameborder=0 width=100% height=100%></iframe></div>");
function writeCalendarFrame(){
	//added by zhaigl
	if(Calendar.timeShow){
		document.getElementById("calendarLayer").style.height="193";
	}else{
		document.getElementById("calendarLayer").style.height="171";
	}
    var strHtml =
    "	<html> " +
    "		<head> " +
    "			<meta http-equiv='Content-Type' content='text/html; charset=gb2312'/> " +
    "     <style> "+
    "			*{font-size: 12px; font-family: 宋体} "+
    "			.bg{  color: "+ Calendar.lightColor +"; cursor: default; background-color: "+ Calendar.darkColor +";} "+
    "			table#tableMain{ width: 142; height: 180;} "+
    "			table#tableWeek td{ color: "+ Calendar.lightColor +";} "+
    "			table#tableDay  td{ font-weight: bold;} "+
    "			td#yearHead, td#yearMonth, td#dayHour, td#hourMinute, td#minuteSecond {color: "+ Calendar.wordColor +"} "+
    "			.out { text-align: center; border-top: 1px solid "+ Calendar.DarkBorder +"; border-left: 1px solid "+ Calendar.DarkBorder +";"+
    "					border-right: 1px solid "+ Calendar.lightColor +"; border-bottom: 1px solid "+ Calendar.lightColor +";}"+
    "			.over{ text-align: center; border-top: 1px solid #FFFFFF; border-left: 1px solid #FFFFFF;"+
    "				border-bottom: 1px solid "+ Calendar.DarkBorder +"; border-right: 1px solid "+ Calendar.DarkBorder +"}"+
    "			input{ border: 1px solid "+ Calendar.darkColor +"; padding-top: 1px; height: 18; cursor: hand;"+
    "       color:"+ Calendar.wordColor +"; background-color: "+ Calendar.btnBgColor +"}"+
    "			</style> " +
    "		</head> " +
    "	<body onselectstart='return false' style='margin: 0px' oncontextmenu='return false'> " +
    "	<form name=calendarForm> ";

    if (Calendar.drag){
      strHtml +=
      "	<script language=javascript> "+
    	"		var drag=false, cx=0, cy=0, o = parent.Calendar.calendar; "+
      "		function document.onmousemove(){ "+
    	"			if(parent.Calendar.drag && drag){ "+
      "  			if(o.style.left=='') { "+
      "  				o.style.left=0; "+
     	"				} "+
      " 			if(o.style.top=='') { "+
      "  				o.style.top=0; "+
      "				}"+
    	"				o.style.left = parseInt(o.style.left) + window.event.clientX-cx; "+
    	"				o.style.top  = parseInt(o.style.top)  + window.event.clientY-cy; "+
      "			} "+
      "		} "+
    	"		function document.onkeydown(){ "+
      "  		switch(window.event.keyCode){ "+
      "  			case 27 : parent.hiddenCalendarTime(); break; "+
    	"				case 37 : parent.prevMonth(); break; "+
      "				case 38 : parent.prevYear(); break; "+
      "				case 39 : parent.nextMonth(); break; "+
      "				case 40 : parent.nextYear(); break; "+
    	"				case 84 : document.forms[0].today.click(); break; " +
      "			} " +
     " 			window.event.keyCode = 0; " +
     "			window.event.returnValue= false; " +
     "		} "+
    	"		function dragStart(){ " +
      "			cx=window.event.clientX; " +
      "			cy=window.event.clientY; " +
      "			drag=true; " +
      "		} " +
      "	</script> ";
    }

    strHtml +=
    	"	<select name=tmpHourSelect onblur='parent.hiddenSelect(this)' style='z-index:1; position:absolute;top:173;left:11;display:none' "+
    	" 	onchange='parent.Calendar.thisHour=this.value; parent.hiddenSelect(this); parent.writeCalendarTime();'></select> "+
			"	<select name=tmpMinuteSelect onblur='parent.hiddenSelect(this)' style='z-index:1; position:absolute;top:173;left:51;display:none' "+
    	" 	onchange='parent.Calendar.thisMinute=this.value; parent.hiddenSelect(this); parent.writeCalendarTime();'></select> "+
			"	<select name=tmpSecondSelect onblur='parent.hiddenSelect(this)' style='z-index:1; position:absolute;top:173;left:91;display:none' "+
    	" 	onchange='parent.Calendar.thisSecond=this.value; parent.hiddenSelect(this); parent.writeCalendarTime();'></select> "+
			"	<select name=tmpYearSelect  onblur='parent.hiddenSelect(this)' style='z-index:1;position:absolute;top:3;left:18;display:none' "+
    	" 	onchange='parent.Calendar.thisYear =this.value; parent.hiddenSelect(this); parent.writeCalendarTime();'></select> "+
    	"	<select name=tmpMonthSelect onblur='parent.hiddenSelect(this)' style='z-index:1; position:absolute;top:3;left:74;display:none' "+
    	" 	onchange='parent.Calendar.thisMonth=this.value; parent.hiddenSelect(this); parent.writeCalendarTime();'></select> "+

    	"	<table id=tableMain class=bg border=0 cellspacing=2 cellpadding=0> "+
    	"		<tr> "+
      "			<td width=140 height=19 bgcolor='"+ Calendar.lightColor +"'> "+
    	"    		<table width=140 id=tableHead border=0 cellspacing=1 cellpadding=0> "+
      "					<tr align=center> "+
    	"    				<td width=15 height=19 class=bg title='向前翻 1 月&#13;快捷键：←' style='cursor: hand' onclick='parent.prevMonth()'><b>&lt;</b></td> "+
    	"    				<td width=60 id=yearHead  title='点击此处选择年份' onclick='parent.showYearSelect(parseInt(this.innerText, 10))' "+
    	"        			onmouseover='this.bgColor=parent.Calendar.darkColor; this.style.color=parent.Calendar.lightColor' "+
    	"        			onmouseout='this.bgColor=parent.Calendar.lightColor; this.style.color=parent.Calendar.wordColor'></td> "+
    	"    				<td width=50 id=yearMonth title='点击此处选择月份' onclick='parent.showMonthSelect(parseInt(this.innerText, 10))' "+
    	"        			onmouseover='this.bgColor=parent.Calendar.darkColor; this.style.color=parent.Calendar.lightColor' "+
    	"        			onmouseout='this.bgColor=parent.Calendar.lightColor; this.style.color=parent.Calendar.wordColor'></td> "+
    	"    				<td width=15 class=bg title='向后翻 1 月&#13;快捷键：→' onclick='parent.nextMonth()' style='cursor: hand'><b>&gt;</b></td> "+
      "					</tr> "+
      "				</table> "+
    	"			</td> "+
      "		</tr> "+
      "		<tr> "+
      "			<td height=20> "+
      "				<table id=tableWeek border=1 width=140 cellpadding=0 cellspacing=0 ";
    					if(Calendar.drag){
                strHtml +=
                "onmousedown='dragStart()' onmouseup='drag=false' onmouseout='drag=false' ";
              }
    					strHtml +=
              	"borderColorLight='"+ Calendar.darkColor +"' borderColorDark='"+ Calendar.lightColor +"'> "+
    "    				<tr align=center> "+
    "							<td height=20>日</td>"+
    "							<td>一</td>"+
    "							<td>二</td>"+
    "							<td>三</td>"+
    "							<td>四</td>"+
    "							<td>五</td>"+
    "							<td>六</td>"+
    "						</tr> "+
    "					</table> "+
    "				</td> "+
    "			</tr> "+
    "			<tr> "+
    "				<td valign=top width=140 bgcolor='"+ Calendar.lightColor +"'> "+
    "    			<table id=tableDay height=120 width=140 border=0 cellspacing=1 cellpadding=0> ";
         			for(var x=0; x<5; x++){
              	strHtml +=
    "						<tr> ";
         				for(var y=0; y<7; y++) {
                 	strHtml +=
    "							<td class=out id='calendarDay"+ (x*7+y) +"'></td> ";
         				}
              	strHtml +=
    "						</tr> ";
    					}
         			strHtml +=
    "						<tr> ";
         				for(var x=35; x<38; x++) {
                	strHtml +=
    "							<td class=out id='calendarDay"+ x +"'></td> ";
         				}
                strHtml +=
    "							<td colspan=2 class=out title='"+ Calendar.regInfo +"'> "+
    "								<input style=' background-color: "+ Calendar.btnBgColor +
    "									;cursor: hand; padding-top: 4px; width: 100%; height: 100%; border: 0' onfocus='this.blur()' "+
    " 								type=button value='清除' onclick='parent.clearCalendar()'> "+
    "							</td> "+
    "							<td colspan=2 class=out title='"+ Calendar.regInfo +"'> "+
    "								<input style=' background-color: "+ Calendar.btnBgColor +
    "									;cursor: hand; padding-top: 4px; width: 100%; height: 100%; border: 0' onfocus='this.blur()' "+
    " 								type=button value='关闭' onclick='parent.hiddenCalendarTime()'> "+
    "							</td> "+
    "						</tr> "+
    "					</table> "+
    "				</td> "+
    "			</tr> ";

	//added by zhaigl
	if(Calendar.timeShow){
		strHtml +=" <tr style='visibility:visible'> ";
	}else{
		strHtml +=" <tr style='visibility:hidden'> ";
	}

	strHtml +=	"<td height=20 bgcolor='"+ Calendar.lightColor +"'> "+
		"    			<table width=140 border=0 cellspacing=1 cellpadding=0> "+
		"						<tr align=center> "+
		"							<td width=10>&nbsp;</td> " +
		"							<td width=40 id=dayHour  title='点击此处选择时' onclick='parent.showHourSelect(parseInt(this.innerText, 10))' "+
		"        					onmouseover='this.bgColor=parent.Calendar.darkColor; this.style.color=parent.Calendar.lightColor' "+
		"        					onmouseout='this.bgColor=parent.Calendar.lightColor; this.style.color=parent.Calendar.wordColor'> </td> "+
		"    					<td width=40 id=hourMinute  title='点击此处选择分' onclick='parent.showMinuteSelect(parseInt(this.innerText, 10))' "+
		"        					onmouseover='this.bgColor=parent.Calendar.darkColor; this.style.color=parent.Calendar.lightColor' "+
		"        					onmouseout='this.bgColor=parent.Calendar.lightColor; this.style.color=parent.Calendar.wordColor'></td> "+
		"   					<td width=40 id=minuteSecond title='点击此处选择秒' onclick='parent.showSecondSelect(parseInt(this.innerText, 10))' "+
		"        					onmouseover='this.bgColor=parent.Calendar.darkColor; this.style.color=parent.Calendar.lightColor' "+
		"        					onmouseout='this.bgColor=parent.Calendar.lightColor; this.style.color=parent.Calendar.wordColor'></td> "+
		"							<td width=10>&nbsp;</td> " +
		"    				</tr> "+
		"					</table> "+
		"				</td> "+
		"			</tr> ";

    strHtml += "<table> "+
		"	</form> "+
		"</body> "+
		"</html> ";

    with(Calendar.iframe)
    {
        document.writeln(strHtml);
        document.close();
        for(var i=0; i<38; i++)
        {
            Calendar.dayObj[i] = eval("calendarDay"+ i);
            Calendar.dayObj[i].onmouseover = onDayMouseOver;
            Calendar.dayObj[i].onmouseout  = onDayMouseOut;
            Calendar.dayObj[i].onclick     = returnCalendar;
        }
    }
}

//初始化日历的设置
function XCalendar() {
    this.regInfo    = "";
    this.daysMonth  = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    this.day        = new Array(39);            //定义日历展示用的数组
    this.dayObj     = new Array(39);            //定义日期展示控件数组
    this.dateStyle  = null;                     //保存格式化后日期数组
    this.objExport  = null;                     //日历回传的显示控件
    this.eventSrc   = null;                     //日历显示的触发控件
    this.inputDate  = null;                     //转化外的输入的日期(d/m/yyyy)
    this.thisYear   = new Date().getFullYear(); //定义年的变量的初始值
    this.thisMonth  = new Date().getMonth()+ 1; //定义月的变量的初始值
    this.thisDay    = new Date().getDate();     //定义日的变量的初始值
    this.thisHour	= new Date().getHours();			//定义时的变量的初始值
    this.thisMinute	= new Date().getMinutes();			//定义时的变量的初始值
    this.thisSecond	= new Date().getSeconds();			//定义时的变量的初始值
    this.today      = this.thisDay +"/"+ this.thisMonth +"/"+ this.thisYear;   //今天(d/m/yyyy)
    this.iframe     = window.frames("calendarIframe"); //日历的 iframe 载体
    this.calendar   = getObjectById("calendarLayer");  //日历的层
    this.dateReg    = "";           //日历格式验证的正则式

    this.yearFall   = 50;           //定义年下拉框的年差值
    this.format     = "yyyy-mm-dd"; //回传日期的格式
    this.timeShow   = true;        //是否返回时间
    this.drag       = true;         //是否允许拖动
    this.darkColor  = "#96BBD8";    //控件的暗色
    this.lightColor = "#FFFFFF";    //控件的亮色
    this.btnBgColor = "#DEEFFB";    //控件的按钮背景色
    this.wordColor  = "#878888";    //控件的文字颜色
    this.wordDark   = "#DCDCDC";    //控件的暗文字颜色
    this.dayBgColor = "#F0F0F0";    //日期数字背景色
    this.todayColor = "#F8BF6E";    //今天在日历上的标示背景色
    this.DarkBorder = "#D4D0C8";    //日期显示的立体表达色
}

var Calendar = new XCalendar();
Calendar.DATE = true;
Calendar.TIMESTAMP = false;
var isDisplayTime = true; 

//主调函数
XCalendar.prototype.display = function(_timeShowFlag){
	this.calendarTime(_timeShowFlag);
};
XCalendar.prototype.calendarTime = function(_timeShow) {
	Calendar.timeShow = _timeShow;
	isDisplayTime = _timeShow;
	var e = window.event.srcElement;
    writeCalendarFrame();
    var o = Calendar.calendar.style;
    Calendar.eventSrc = e;
	Calendar.objExport = e;
	if (arguments.length > 0) {
		Calendar.timeShow = arguments[0];
	} 

    Calendar.iframe.tableWeek.style.cursor = Calendar.drag ? "move" : "default";
		var t = e.offsetTop,  h = e.clientHeight, l = e.offsetLeft, p = e.type;
		while (e = e.offsetParent){t += e.offsetTop; l += e.offsetLeft;}
    o.display = ""; Calendar.iframe.document.body.focus();
    var cw = Calendar.calendar.clientWidth, ch = Calendar.calendar.clientHeight;
    var dw = document.body.clientWidth, dl = document.body.scrollLeft, dt = document.body.scrollTop;

    if (document.body.clientHeight + dt - t - h >= ch) o.top = (p=="image")? t + h : t + h + 6;
    else o.top  = (t - dt < ch) ? ((p=="image")? t + h : t + h + 6) : t - ch;
    if (dw + dl - l >= cw) o.left = l; else o.left = (dw >= cw) ? dw - cw + dl : dl;

    if  (!Calendar.timeShow) Calendar.dateReg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
    else Calendar.dateReg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
    try{
        if (Calendar.objExport.value.trim() != ""){
            Calendar.dateStyle = Calendar.objExport.value.trim().match(Calendar.dateReg);
            if (Calendar.dateStyle == null)
            {
                Calendar.thisYear   = new Date().getFullYear();
                Calendar.thisMonth  = new Date().getMonth()+ 1;
                Calendar.thisDay    = new Date().getDate();
                alert("原文本框里的日期有错误！\n可能与你定义的显示时分秒有冲突！");
                writeCalendarTime(); return false;
            }
            else
            {
                Calendar.thisYear   = parseInt(Calendar.dateStyle[1], 10);
                Calendar.thisMonth  = parseInt(Calendar.dateStyle[3], 10);
                Calendar.thisDay    = parseInt(Calendar.dateStyle[4], 10);
				//Added by zhaigl
				if(Calendar.timeShow){
					Calendar.thisHour    = parseInt(Calendar.dateStyle[5], 10);
					Calendar.thisMinute    = parseInt(Calendar.dateStyle[6], 10);
					Calendar.thisSecond    = parseInt(Calendar.dateStyle[7], 10);
				}else{
					Calendar.thisHour    = new Date().getHours();
					Calendar.thisMinute    = new Date().getMinutes();
					Calendar.thisSecond    = new Date().getSeconds();
				}
                Calendar.inputDate  = parseInt(Calendar.thisDay, 10) +"/"+ parseInt(Calendar.thisMonth, 10) +"/"+
                parseInt(Calendar.thisYear, 10); writeCalendarTime();
            }
        }  else{
			Calendar.thisHour    = new Date().getHours();
			Calendar.thisMinute    = new Date().getMinutes();
			Calendar.thisSecond    = new Date().getSeconds();
			writeCalendarTime();
		}
    }  catch(e){writeCalendarTime();}
}
function showMonthSelect() //月份的下拉框
{
    var m = isNaN(parseInt(Calendar.thisMonth, 10)) ? new Date().getMonth() + 1 : parseInt(Calendar.thisMonth);
    var e = Calendar.iframe.document.forms[0].tmpMonthSelect;
    for (var i=1; i<13; i++) e.options.add(new Option(i +"月", i));
    e.style.display = ""; e.value = m; e.focus(); window.status = e.style.top;
}
function showHourSelect() //小时的下拉框
{
    var m = isNaN(parseInt(Calendar.thisHour, 10)) ? new Date().getHours() : parseInt(Calendar.thisHour);
    var e = Calendar.iframe.document.forms[0].tmpHourSelect;
    for (var i=0; i<24; i++) e.options.add(new Option(i +"时", i));
    e.style.display = ""; e.value = m; e.focus(); window.status = e.style.top;
}
function showMinuteSelect() //分的下拉框
{
    var m = isNaN(parseInt(Calendar.thisMinute, 10)) ? new Date().getMinutes() : parseInt(Calendar.thisMinute);
    var e = Calendar.iframe.document.forms[0].tmpMinuteSelect;
    for (var i=0; i<=59; i++) e.options.add(new Option(i +"分", i));
    e.style.display = ""; e.value = m; e.focus(); window.status = e.style.top;
}
function showSecondSelect() //秒的下拉框
{
    var m = isNaN(parseInt(Calendar.thisSecond, 10)) ? new Date().getSeconds() : parseInt(Calendar.thisSecond);
    var e = Calendar.iframe.document.forms[0].tmpSecondSelect;
    for (var i=0; i<=59; i++) e.options.add(new Option(i +"秒", i));
    e.style.display = ""; e.value = m; e.focus(); window.status = e.style.top;
}
function showYearSelect() //年份的下拉框
{
    var n = Calendar.yearFall;
    var e = Calendar.iframe.document.forms[0].tmpYearSelect;
    var y = isNaN(parseInt(Calendar.thisYear, 10)) ? new Date().getFullYear() : parseInt(Calendar.thisYear);
        y = (y <= 1000)? 1000 : ((y >= 9999)? 9999 : y);
    var min = (y - n >= 1000) ? y - n : 1000;
    var max = (y + n <= 9999) ? y + n : 9999;
        min = (max == 9999) ? max-n*2 : min;
        max = (min == 1000) ? min+n*2 : max;
    for (var i=min; i<=max; i++) e.options.add(new Option(i +"年", i));
    e.style.display = ""; e.value = y; e.focus();
}
function prevMonth()  //往前翻月份
{
    Calendar.thisDay = 1;
    if (Calendar.thisMonth==1)
    {
        Calendar.thisYear--;
        Calendar.thisMonth=13;
    }
    Calendar.thisMonth--; writeCalendarTime();
}
function nextMonth()  //往后翻月份
{
    Calendar.thisDay = 1;
    if (Calendar.thisMonth==12)
    {
        Calendar.thisYear++;
        Calendar.thisMonth=0;
    }
    Calendar.thisMonth++; writeCalendarTime();
}
function prevYear()
{
  Calendar.thisDay = 1;
  Calendar.thisYear--;
  writeCalendarTime();
}//往前翻 Year
function nextYear()
{
  Calendar.thisDay = 1;
  Calendar.thisYear++;
  writeCalendarTime();
}//往后翻 Year
function hiddenSelect(e)
{
  for(var i=e.options.length; i>-1; i--)
  	e.options.remove(i);
  e.style.display="none";
}
function getObjectById(id)
{
  if(document.all)
  	return(eval("document.all."+ id));
  return(eval(id));
}
function hiddenCalendarTime()
{
  getObjectById("calendarLayer").style.display = "none";
};
function appendZero(n)
{
  return(("00"+ n).substr(("00"+ n).length-2));
}

function trim()
{
  return this.replace(/(^\s*)|(\s*$)/g,"");
}
function onDayMouseOver()
{
    this.className = "over";
    this.style.backgroundColor = Calendar.darkColor;
    if(Calendar.day[this.id.substr(11)].split("/")[1] == Calendar.thisMonth)
    this.style.color = Calendar.lightColor;
}
function onDayMouseOut()
{
    this.className = "out";
    var d = Calendar.day[this.id.substr(11)];
    var a = d.split("/");
    this.style.removeAttribute('backgroundColor');
    if(a[1] == Calendar.thisMonth && d != Calendar.today)
    {
        if(Calendar.dateStyle != null && a[0] == parseInt(Calendar.dateStyle[4], 10))
        this.style.color = Calendar.lightColor;
        this.style.color = Calendar.wordColor;
    }
}
function writeCalendarTime() //对日历显示的数据的处理程序
{
    var y = Calendar.thisYear;
    var m = Calendar.thisMonth;
    var d = Calendar.thisDay;
    var h = Calendar.thisHour;
    Calendar.daysMonth[1] = (0==y%4 && (y%100!=0 || y%400==0)) ? 29 : 28;
    if (!(y<=9999 && y >= 1000 && parseInt(m, 10)>0 && parseInt(m, 10)<13 && parseInt(d, 10)>0)){
        alert("对不起，你输入了错误的日期！");
        Calendar.thisYear   = new Date().getFullYear();
        Calendar.thisMonth  = new Date().getMonth()+ 1;
        Calendar.thisDay    = new Date().getDate();
    }
    y = Calendar.thisYear;
    m = Calendar.thisMonth;
    d = Calendar.thisDay;
    h = Calendar.thisHour;
    mi = Calendar.thisMinute;
    s = Calendar.thisSecond;
    Calendar.iframe.yearHead.innerText  = y +" 年";
	Calendar.iframe.yearMonth.innerText = parseInt(m, 10) +" 月";
	Calendar.iframe.dayHour.innerText  = h +" 时";
	Calendar.iframe.hourMinute.innerText  = mi +" 分";
	Calendar.iframe.minuteSecond.innerText  = s +" 秒";
    Calendar.daysMonth[1] = (0==y%4 && (y%100!=0 || y%400==0)) ? 29 : 28; //闰年二月为29天
    var w = new Date(y, m-1, 1).getDay();
    var prevDays = m==1  ? Calendar.daysMonth[11] : Calendar.daysMonth[m-2];
    for(var i=(w-1); i>=0; i--) //这三个 for 循环为日历赋数据源（数组 Calendar.day）格式是 d/m/yyyy
    {
        Calendar.day[i] = prevDays +"/"+ (parseInt(m, 10)-1) +"/"+ y;
        if(m==1) Calendar.day[i] = prevDays +"/"+ 12 +"/"+ (parseInt(y, 10)-1);
        prevDays--;
    }
    for(var i=1; i<=Calendar.daysMonth[m-1]; i++) Calendar.day[i+w-1] = i +"/"+ m +"/"+ y;
    for(var i=1; i<39-w-Calendar.daysMonth[m-1]+1; i++)
    {
        Calendar.day[Calendar.daysMonth[m-1]+w-1+i] = i +"/"+ (parseInt(m, 10)+1) +"/"+ y;
        if(m==12) Calendar.day[Calendar.daysMonth[m-1]+w-1+i] = i +"/"+ 1 +"/"+ (parseInt(y, 10)+1);
    }
    for(var i=0; i<38; i++)    //这个循环是根据源数组写到日历里显示
    {
        var a = Calendar.day[i].split("/");
        Calendar.dayObj[i].innerText    = a[0];
        Calendar.dayObj[i].title        = a[2] +"-"+ appendZero(a[1]) +"-"+ appendZero(a[0]);
        Calendar.dayObj[i].bgColor      = Calendar.dayBgColor;
        Calendar.dayObj[i].style.color  = Calendar.wordColor;
        if ((i<10 && parseInt(Calendar.day[i], 10)>20) || (i>27 && parseInt(Calendar.day[i], 10)<12))
            Calendar.dayObj[i].style.color = Calendar.wordDark;
        if (Calendar.inputDate==Calendar.day[i])    //设置输入框里的日期在日历上的颜色
        {Calendar.dayObj[i].bgColor = Calendar.darkColor; Calendar.dayObj[i].style.color = Calendar.lightColor;}
        if (Calendar.day[i] == Calendar.today)      //设置今天在日历上反应出来的颜色
        {Calendar.dayObj[i].bgColor = Calendar.todayColor; Calendar.dayObj[i].style.color = Calendar.lightColor;}
    }
}
function returnCalendar() //根据日期格式等返回用户选定的日期
{
    if(Calendar.objExport)
    {
        var returnValue;
        var a = (arguments.length==0) ? Calendar.day[this.id.substr(11)].split("/") : arguments[0].split("/");
        var d = Calendar.format.match(/^(\w{4})(-|\/)(\w{1,2})\2(\w{1,2})$/);
        if(d==null){alert("你设定的日期输出格式不对！\r\n\r\n请重新定义 Calendar.format ！"); return false;}
        var flag = d[3].length==2 || d[4].length==2; //判断返回的日期格式是否要补零
        returnValue = flag ? a[2] +d[2]+ appendZero(a[1]) +d[2]+ appendZero(a[0]) : a[2] +d[2]+ a[1] +d[2]+ a[0];
        if(Calendar.timeShow)
        {
            var h = Calendar.thisHour, m = Calendar.thisMinute, s = Calendar.thisSecond;
            returnValue += flag ? " "+ appendZero(h) +":"+ appendZero(m) +":"+ appendZero(s) : " "+  h  +":"+ m +":"+ s;
        }
        if(isDisplayTime){
        	Calendar.objExport.value = returnValue;
        }else{
        	Calendar.objExport.value = returnValue.substring(0,10);
        }
        hiddenCalendarTime();
    }
}
function clearCalendar(){
    if(Calendar.objExport){
    	Calendar.objExport.value = "";
    	hiddenCalendarTime();
    }
}

function  onclick()
{
     if(Calendar.eventSrc != window.event.srcElement) hiddenCalendarTime();
}

//-->