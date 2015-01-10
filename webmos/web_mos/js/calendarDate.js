<!--
document.write("<div id=calendarLayer style='position: absolute; z-index: 9999; width: 144px; height: 170px; display: none'>");
document.write("<iframe name=calendarIframe scrolling=no frameborder=0 width=100% height=100%></iframe></div>");
function writeCalendarFrame()
{
    var strHtml =
    "	<html> " +
    "		<head> " +
    "			<meta http-equiv='Content-Type' content='text/html; charset=gb2312'/> " +
    "     <style> "+
    "			*{font-size: 12px; font-family: 宋体} "+
    "			.bg{  color: "+ CalendarWithTime.lightColor +"; cursor: default; background-color: "+ CalendarWithTime.darkColor +";} "+
    "			table#tableMain{ width: 142; height: 180;} "+
    "			table#tableWeek td{ color: "+ CalendarWithTime.lightColor +";} "+
    "			table#tableDay  td{ font-weight: bold;} "+
    "			td#yearHead, td#yearMonth, td#dayHour, td#hourMinute, td#minuteSecond {color: "+ CalendarWithTime.wordColor +"} "+
    "			.out { text-align: center; border-top: 1px solid "+ CalendarWithTime.DarkBorder +"; border-left: 1px solid "+ CalendarWithTime.DarkBorder +";"+
    "					border-right: 1px solid "+ CalendarWithTime.lightColor +"; border-bottom: 1px solid "+ CalendarWithTime.lightColor +";}"+
    "			.over{ text-align: center; border-top: 1px solid #FFFFFF; border-left: 1px solid #FFFFFF;"+
    "				border-bottom: 1px solid "+ CalendarWithTime.DarkBorder +"; border-right: 1px solid "+ CalendarWithTime.DarkBorder +"}"+
    "			input{ border: 1px solid "+ CalendarWithTime.darkColor +"; padding-top: 1px; height: 18; cursor: hand;"+
    "       color:"+ CalendarWithTime.wordColor +"; background-color: "+ CalendarWithTime.btnBgColor +"}"+
    "			</style> " +
    "		</head> " +
    "	<body onselectstart='return false' style='margin: 0px' oncontextmenu='return false'> " +
    "	<form name=calendarForm> ";

    if (CalendarWithTime.drag){
      strHtml +=
      "	<script language=javascript> "+
    	"		var drag=false, cx=0, cy=0, o = parent.CalendarWithTime.calendar; "+
      "		function document.onmousemove(){ "+
    	"			if(parent.CalendarWithTime.drag && drag){ "+
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
    	" 	onchange='parent.CalendarWithTime.thisHour=this.value; parent.hiddenSelect(this); parent.writeCalendarTime();'></select> "+
			"	<select name=tmpMinuteSelect onblur='parent.hiddenSelect(this)' style='z-index:1; position:absolute;top:173;left:51;display:none' "+
    	" 	onchange='parent.CalendarWithTime.thisMinute=this.value; parent.hiddenSelect(this); parent.writeCalendarTime();'></select> "+
			"	<select name=tmpSecondSelect onblur='parent.hiddenSelect(this)' style='z-index:1; position:absolute;top:173;left:91;display:none' "+
    	" 	onchange='parent.CalendarWithTime.thisSecond=this.value; parent.hiddenSelect(this); parent.writeCalendarTime();'></select> "+
			"	<select name=tmpYearSelect  onblur='parent.hiddenSelect(this)' style='z-index:1;position:absolute;top:3;left:18;display:none' "+
    	" 	onchange='parent.CalendarWithTime.thisYear =this.value; parent.hiddenSelect(this); parent.writeCalendarTime();'></select> "+
    	"	<select name=tmpMonthSelect onblur='parent.hiddenSelect(this)' style='z-index:1; position:absolute;top:3;left:74;display:none' "+
    	" 	onchange='parent.CalendarWithTime.thisMonth=this.value; parent.hiddenSelect(this); parent.writeCalendarTime();'></select> "+

    	"	<table id=tableMain class=bg border=0 cellspacing=2 cellpadding=0> "+
    	"		<tr> "+
      "			<td width=140 height=19 bgcolor='"+ CalendarWithTime.lightColor +"'> "+
    	"    		<table width=140 id=tableHead border=0 cellspacing=1 cellpadding=0> "+
      "					<tr align=center> "+
    	"    				<td width=15 height=19 class=bg title='向前翻 1 月&#13;快捷键：←' style='cursor: hand' onclick='parent.prevMonth()'><b>&lt;</b></td> "+
    	"    				<td width=60 id=yearHead  title='点击此处选择年份' onclick='parent.showYearSelect(parseInt(this.innerText, 10))' "+
    	"        			onmouseover='this.bgColor=parent.CalendarWithTime.darkColor; this.style.color=parent.CalendarWithTime.lightColor' "+
    	"        			onmouseout='this.bgColor=parent.CalendarWithTime.lightColor; this.style.color=parent.CalendarWithTime.wordColor'></td> "+
    	"    				<td width=50 id=yearMonth title='点击此处选择月份' onclick='parent.showMonthSelect(parseInt(this.innerText, 10))' "+
    	"        			onmouseover='this.bgColor=parent.CalendarWithTime.darkColor; this.style.color=parent.CalendarWithTime.lightColor' "+
    	"        			onmouseout='this.bgColor=parent.CalendarWithTime.lightColor; this.style.color=parent.CalendarWithTime.wordColor'></td> "+
    	"    				<td width=15 class=bg title='向后翻 1 月&#13;快捷键：→' onclick='parent.nextMonth()' style='cursor: hand'><b>&gt;</b></td> "+
      "					</tr> "+
      "				</table> "+
    	"			</td> "+
      "		</tr> "+
      "		<tr> "+
      "			<td height=20> "+
      "				<table id=tableWeek border=1 width=140 cellpadding=0 cellspacing=0 ";
    					if(CalendarWithTime.drag){
                strHtml +=
                "onmousedown='dragStart()' onmouseup='drag=false' onmouseout='drag=false' ";
              }
    					strHtml +=
              	"borderColorLight='"+ CalendarWithTime.darkColor +"' borderColorDark='"+ CalendarWithTime.lightColor +"'> "+
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
    "				<td valign=top width=140 bgcolor='"+ CalendarWithTime.lightColor +"'> "+
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
    "							<td colspan=2 class=out title='"+ CalendarWithTime.regInfo +"'> "+
    "								<input style=' background-color: "+ CalendarWithTime.btnBgColor +
    "									;cursor: hand; padding-top: 4px; width: 100%; height: 100%; border: 0' onfocus='this.blur()' "+
    " 								type=button value='清除' onclick='parent.clearCalendar()'> "+
    "							</td> "+
    "							<td colspan=2 class=out title='"+ CalendarWithTime.regInfo +"'> "+
    "								<input style=' background-color: "+ CalendarWithTime.btnBgColor +
    "									;cursor: hand; padding-top: 4px; width: 100%; height: 100%; border: 0' onfocus='this.blur()' "+
    " 								type=button value='关闭' onclick='parent.hiddenCalendarTime()'> "+
    "							</td> "+
    "						</tr> "+
    "					</table> "+
    "				</td> "+
    "			</tr> "+
	"			<tr><td height=150>&nbsp;</td></tr>"+
	"		<table> "+
    "	</form> "+
    "</body> "+
    "</html> ";

    with(CalendarWithTime.iframe)
    {
        document.writeln(strHtml);
        document.close();
        for(var i=0; i<38; i++)
        {
            CalendarWithTime.dayObj[i] = eval("calendarDay"+ i);
            CalendarWithTime.dayObj[i].onmouseover = onDayMouseOver;
            CalendarWithTime.dayObj[i].onmouseout  = onDayMouseOut;
            CalendarWithTime.dayObj[i].onclick     = returnCalendar;
        }
    }
}
function CalendarWithTime() //初始化日历的设置
{
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
    this.thisHour	 = new Date().getHours();			//定义时的变量的初始值
    this.thisMinute	 = new Date().getMinutes();			//定义时的变量的初始值
    this.thisSecond	 = new Date().getSeconds();			//定义时的变量的初始值
    this.today      = this.thisDay +"/"+ this.thisMonth +"/"+ this.thisYear;   //今天(d/m/yyyy)
    this.iframe     = window.frames("calendarIframe"); //日历的 iframe 载体
    this.calendar   = getObjectById("calendarLayer");  //日历的层
    this.dateReg    = "";           //日历格式验证的正则式
    this.yearFall   = 50;           //定义年下拉框的年差值
    this.format     = "yyyy-mm-dd"; //回传日期的格式
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

 var CalendarWithTime = new CalendarWithTime();

function calendarDate() //主调函数
{
    var e = window.event.srcElement;
    writeCalendarFrame();
    var o = CalendarWithTime.calendar.style;
    CalendarWithTime.eventSrc = e;
		if (arguments.length == 0) {
    	CalendarWithTime.objExport = e;
		} else {
    	CalendarWithTime.objExport = eval(arguments[0]);
		}

    CalendarWithTime.iframe.tableWeek.style.cursor = CalendarWithTime.drag ? "move" : "default";
		var t = e.offsetTop,  h = e.clientHeight, l = e.offsetLeft, p = e.type;
		while (e = e.offsetParent){t += e.offsetTop; l += e.offsetLeft;}
    o.display = ""; CalendarWithTime.iframe.document.body.focus();
    var cw = CalendarWithTime.calendar.clientWidth, ch = CalendarWithTime.calendar.clientHeight;
    var dw = document.body.clientWidth, dl = document.body.scrollLeft, dt = document.body.scrollTop;

    if (document.body.clientHeight + dt - t - h >= ch) o.top = (p=="image")? t + h : t + h + 6;
    else o.top  = (t - dt < ch) ? ((p=="image")? t + h : t + h + 6) : t - ch;
    if (dw + dl - l >= cw) o.left = l; else o.left = (dw >= cw) ? dw - cw + dl : dl;
    CalendarWithTime.dateReg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
    try{
        if (CalendarWithTime.objExport.value.trim() != ""){
            CalendarWithTime.dateStyle = CalendarWithTime.objExport.value.trim().match(CalendarWithTime.dateReg);
            if (CalendarWithTime.dateStyle == null)
            {
                CalendarWithTime.thisYear   = new Date().getFullYear();
                CalendarWithTime.thisMonth  = new Date().getMonth()+ 1;
                CalendarWithTime.thisDay    = new Date().getDate();
                alert("原文本框里的日期有错误！\n可能与你定义的显示时分秒有冲突！");
                writeCalendarTime(); return false;
            }
            else
            {
                CalendarWithTime.thisYear   = parseInt(CalendarWithTime.dateStyle[1], 10);
                CalendarWithTime.thisMonth  = parseInt(CalendarWithTime.dateStyle[3], 10);
                CalendarWithTime.thisDay    = parseInt(CalendarWithTime.dateStyle[4], 10);
                CalendarWithTime.thisHour    = parseInt(CalendarWithTime.dateStyle[5], 10);
                CalendarWithTime.thisMinute    = parseInt(CalendarWithTime.dateStyle[6], 10);
                CalendarWithTime.thisSecond    = parseInt(CalendarWithTime.dateStyle[7], 10);
                CalendarWithTime.inputDate  = parseInt(CalendarWithTime.thisDay, 10) +"/"+ parseInt(CalendarWithTime.thisMonth, 10) +"/"+
                parseInt(CalendarWithTime.thisYear, 10); writeCalendarTime();
            }
        }  else writeCalendarTime();
    }  catch(e){writeCalendarTime();}
}
function showMonthSelect() //月份的下拉框
{
    var m = isNaN(parseInt(CalendarWithTime.thisMonth, 10)) ? new Date().getMonth() + 1 : parseInt(CalendarWithTime.thisMonth);
    var e = CalendarWithTime.iframe.document.forms[0].tmpMonthSelect;
    for (var i=1; i<13; i++) e.options.add(new Option(i +"月", i));
    e.style.display = ""; e.value = m; e.focus(); window.status = e.style.top;
}
function showHourSelect() //小时的下拉框
{
    var m = isNaN(parseInt(CalendarWithTime.thisHour, 10)) ? new Date().getHours() : parseInt(CalendarWithTime.thisHour);
    var e = CalendarWithTime.iframe.document.forms[0].tmpHourSelect;
    for (var i=0; i<24; i++) e.options.add(new Option(i +"时", i));
    e.style.display = ""; e.value = m; e.focus(); window.status = e.style.top;
}
function showMinuteSelect() //分的下拉框
{
    var m = isNaN(parseInt(CalendarWithTime.thisMinute, 10)) ? new Date().getMinutes() : parseInt(CalendarWithTime.thisMinute);
    var e = CalendarWithTime.iframe.document.forms[0].tmpMinuteSelect;
    for (var i=0; i<=59; i++) e.options.add(new Option(i +"分", i));
    e.style.display = ""; e.value = m; e.focus(); window.status = e.style.top;
}
function showSecondSelect() //秒的下拉框
{
    var m = isNaN(parseInt(CalendarWithTime.thisSecond, 10)) ? new Date().getSeconds() : parseInt(CalendarWithTime.thisSecond);
    var e = CalendarWithTime.iframe.document.forms[0].tmpSecondSelect;
    for (var i=0; i<=59; i++) e.options.add(new Option(i +"秒", i));
    e.style.display = ""; e.value = m; e.focus(); window.status = e.style.top;
}
function showYearSelect() //年份的下拉框
{
    var n = CalendarWithTime.yearFall;
    var e = CalendarWithTime.iframe.document.forms[0].tmpYearSelect;
    var y = isNaN(parseInt(CalendarWithTime.thisYear, 10)) ? new Date().getFullYear() : parseInt(CalendarWithTime.thisYear);
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
    CalendarWithTime.thisDay = 1;
    if (CalendarWithTime.thisMonth==1)
    {
        CalendarWithTime.thisYear--;
        CalendarWithTime.thisMonth=13;
    }
    CalendarWithTime.thisMonth--; writeCalendarTime();
}
function nextMonth()  //往后翻月份
{
    CalendarWithTime.thisDay = 1;
    if (CalendarWithTime.thisMonth==12)
    {
        CalendarWithTime.thisYear++;
        CalendarWithTime.thisMonth=0;
    }
    CalendarWithTime.thisMonth++; writeCalendarTime();
}
function prevYear()
{
  CalendarWithTime.thisDay = 1;
  CalendarWithTime.thisYear--;
  writeCalendarTime();
}//往前翻 Year
function nextYear()
{
  CalendarWithTime.thisDay = 1;
  CalendarWithTime.thisYear++;
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
}//日期自动补零程序
String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
function onDayMouseOver()
{
    this.className = "over";
    this.style.backgroundColor = CalendarWithTime.darkColor;
    if(CalendarWithTime.day[this.id.substr(11)].split("/")[1] == CalendarWithTime.thisMonth)
    this.style.color = CalendarWithTime.lightColor;
}
function onDayMouseOut()
{
    this.className = "out";
    var d = CalendarWithTime.day[this.id.substr(11)];
    var a = d.split("/");
    this.style.removeAttribute('backgroundColor');
    if(a[1] == CalendarWithTime.thisMonth && d != CalendarWithTime.today)
    {
        if(CalendarWithTime.dateStyle != null && a[0] == parseInt(CalendarWithTime.dateStyle[4], 10))
        this.style.color = CalendarWithTime.lightColor;
        this.style.color = CalendarWithTime.wordColor;
    }
}
function writeCalendarTime() //对日历显示的数据的处理程序
{
    var y = CalendarWithTime.thisYear;
    var m = CalendarWithTime.thisMonth;
    var d = CalendarWithTime.thisDay;
    var h = CalendarWithTime.thisHour;
    CalendarWithTime.daysMonth[1] = (0==y%4 && (y%100!=0 || y%400==0)) ? 29 : 28;
    if (!(y<=9999 && y >= 1000 && parseInt(m, 10)>0 && parseInt(m, 10)<13 && parseInt(d, 10)>0)){
        alert("对不起，你输入了错误的日期！");
        CalendarWithTime.thisYear   = new Date().getFullYear();
        CalendarWithTime.thisMonth  = new Date().getMonth()+ 1;
        CalendarWithTime.thisDay    = new Date().getDate();
    }
    y = CalendarWithTime.thisYear;
    m = CalendarWithTime.thisMonth;
    d = CalendarWithTime.thisDay;
    h = CalendarWithTime.thisHour;
    mi = CalendarWithTime.thisMinute;
    s = CalendarWithTime.thisSecond;
    CalendarWithTime.iframe.yearHead.innerText  = y +" 年";
    CalendarWithTime.iframe.yearMonth.innerText = parseInt(m, 10) +" 月";
	// begin
	if (CalendarWithTime.timeShow)
	{
		CalendarWithTime.iframe.dayHour.innerText  = h +" 时";
		CalendarWithTime.iframe.hourMinute.innerText  = mi +" 分";
		CalendarWithTime.iframe.minuteSecond.innerText  = s +" 秒";
	}
	// end
    CalendarWithTime.daysMonth[1] = (0==y%4 && (y%100!=0 || y%400==0)) ? 29 : 28; //闰年二月为29天
    var w = new Date(y, m-1, 1).getDay();
    var prevDays = m==1  ? CalendarWithTime.daysMonth[11] : CalendarWithTime.daysMonth[m-2];
    for(var i=(w-1); i>=0; i--) //这三个 for 循环为日历赋数据源（数组 CalendarWithTime.day）格式是 d/m/yyyy
    {
        CalendarWithTime.day[i] = prevDays +"/"+ (parseInt(m, 10)-1) +"/"+ y;
        if(m==1) CalendarWithTime.day[i] = prevDays +"/"+ 12 +"/"+ (parseInt(y, 10)-1);
        prevDays--;
    }
    for(var i=1; i<=CalendarWithTime.daysMonth[m-1]; i++) CalendarWithTime.day[i+w-1] = i +"/"+ m +"/"+ y;
    for(var i=1; i<39-w-CalendarWithTime.daysMonth[m-1]+1; i++)
    {
        CalendarWithTime.day[CalendarWithTime.daysMonth[m-1]+w-1+i] = i +"/"+ (parseInt(m, 10)+1) +"/"+ y;
        if(m==12) CalendarWithTime.day[CalendarWithTime.daysMonth[m-1]+w-1+i] = i +"/"+ 1 +"/"+ (parseInt(y, 10)+1);
    }
    for(var i=0; i<38; i++)    //这个循环是根据源数组写到日历里显示
    {
        var a = CalendarWithTime.day[i].split("/");
        CalendarWithTime.dayObj[i].innerText    = a[0];
        CalendarWithTime.dayObj[i].title        = a[2] +"-"+ appendZero(a[1]) +"-"+ appendZero(a[0]);
        CalendarWithTime.dayObj[i].bgColor      = CalendarWithTime.dayBgColor;
        CalendarWithTime.dayObj[i].style.color  = CalendarWithTime.wordColor;
        if ((i<10 && parseInt(CalendarWithTime.day[i], 10)>20) || (i>27 && parseInt(CalendarWithTime.day[i], 10)<12))
            CalendarWithTime.dayObj[i].style.color = CalendarWithTime.wordDark;
        if (CalendarWithTime.inputDate==CalendarWithTime.day[i])    //设置输入框里的日期在日历上的颜色
        {CalendarWithTime.dayObj[i].bgColor = CalendarWithTime.darkColor; CalendarWithTime.dayObj[i].style.color = CalendarWithTime.lightColor;}
        if (CalendarWithTime.day[i] == CalendarWithTime.today)      //设置今天在日历上反应出来的颜色
        {CalendarWithTime.dayObj[i].bgColor = CalendarWithTime.todayColor; CalendarWithTime.dayObj[i].style.color = CalendarWithTime.lightColor;}
    }
}
function returnCalendar() //根据日期格式等返回用户选定的日期
{
    if(CalendarWithTime.objExport)
    {
        var returnValue;
        var a = (arguments.length==0) ? CalendarWithTime.day[this.id.substr(11)].split("/") : arguments[0].split("/");
        var d = CalendarWithTime.format.match(/^(\w{4})(-|\/)(\w{1,2})\2(\w{1,2})$/);
        if(d==null){alert("你设定的日期输出格式不对！\r\n\r\n请重新定义 CalendarWithTime.format ！"); return false;}
        var flag = d[3].length==2 || d[4].length==2; //判断返回的日期格式是否要补零
        returnValue = flag ? a[2] +d[2]+ appendZero(a[1]) +d[2]+ appendZero(a[0]) : a[2] +d[2]+ a[1] +d[2]+ a[0];
        CalendarWithTime.objExport.value = returnValue;
        hiddenCalendarTime();
    }
}
function clearCalendar(){
    if(CalendarWithTime.objExport){
    	CalendarWithTime.objExport.value = "";
    	hiddenCalendarTime();
    }
}

document.onclick=function()
{
     if(CalendarWithTime.eventSrc != window.event.srcElement) hiddenCalendarTime();
}

//-->
