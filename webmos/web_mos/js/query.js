var parentnames = ''; 
var values = ''; 
var types = '';

function isValueNull(value)
{
	if (null == value || -1 != value.search("^\\s{0,}$"))
	{
		return true;
	}
	return false;
}

function isNumber(obj)
{
	return isValueNumber(obj.value);
}

function isDate(obj)
{
	return isValueDate(obj.value);
}

function isDateWithHMS(obj)
{
	return isValueDateWithHMS(obj.value);	
}

function isDateWithHMSOrEmpty(value)
{
	if (null == value || -1 != value.search("^\\s{0,}$"))	
	{
		return true;
	}
	
	return isValueDateWithHMS(value);
}

function isValueDateWithHMS(value)
{
	if (null == value)
	{
		alert("日期格式错误.应为'2007-08-01 08:02:03'样式.");
		return false;
	}

	if (value.match("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$"))
	{
		var times = value.split(" ");
		dates = times[0].split("-");
	    newDate = new Date(dates[0], dates[1] - 1, dates[2]);
		
		if (dates[0] != newDate.getFullYear() || dates[1] != (newDate.getMonth() + 1) || dates[2] != newDate.getDate())
		{
			alert(value + "日期错误.");
			return false;
		}
		
		var hours = times[1].split(":");
		if (parseFloat(hours[0]) > 23 || parseFloat(hours[0]) < 0 
			|| parseFloat(hours[1]) > 59 || parseFloat(hours[1]) < 0   
			|| parseFloat(hours[2]) > 59 || parseFloat(hours[2]) < 0)
		{
			alert(value + "日期错误.");
			return false;
		}
		
		return true;	
	}
	else
	{
		alert("日期格式错误.应为'2007-08-01 08:02:03'样式.");
		return false;
	}
}

function isValueNumber(value)
{
	if (null == value || -1 == value.search("^\\d{1,}\\.{0,1}\\d{0,}$"))
	{
		return false;		
	}
	
	return true;
}

function isValueDate(value)
{
	if (null == value)
	{
		alert("日期格式错误.应为'2007-08-12'或者'20070801'样式.");
		return false;
	}

	if (value.match("^\\d{4}-\\d{1,2}-\\d{1,2}$"))
	{
		var dates = value.split("-");
		var newDate = new Date(dates[0], dates[1] - 1, dates[2]);
		
		if (dates[0] != newDate.getFullYear()
			|| dates[1] != (newDate.getMonth() + 1)
			|| dates[2] != newDate.getDate())
		{
			alert(value + "日期错误.");
			return false;
		}
		return true;	
	}	
	else if (value.match("^\\d{8}$"))
	{
		var year = value.substr(0, 4);
		var month = value.substr(4, 2);	
		var day = value.substr(6, 2);
		var newDate = new Date(year, month - 1, day);
		
		if (year != newDate.getFullYear()
			|| month != (newDate.getMonth() + 1)
			|| day != newDate.getDate())
		{
			alert(value + "日期错误.");
			return false;
		}
		return true;	
	}
	else
	{
		alert("日期格式错误.应为'2007-08-12'或者'20070801'样式.");
		return false;
	}
}

function isNumberOrEmpty(value)
{
	if (null == value || -1 != value.search("^\\s{0,}$"))	
	{
		return true;
	}
	
	return isValueNumber(value);
}	

function isDateOrEmpty(value)
{
	if (null == value || -1 != value.search("^\\s{0,}$"))	
	{
		return true;
	}
	
	return isValueDate(value);
}

function selectChecked(obj)
{
	if(obj == null)
	{
		return false;
	}

	if(isNaN(obj.length))
	{
		if(obj.checked) return true;
	} 
	else 
	{
		var iLen = obj.length;
		for(var i = 0; i < iLen; i++)
		{
			if(obj[i].checked)
			{
				return true;
			}
		}
	}
	
	return false;
}

function selectCheckbox(obj)
{
	var value = obj.value;
	if ("0" == value)
	{
		return false;
	}
	
	return true;
}

function rowheadCheck()
{
	for(var i = 0, n =  document.getElementById("select_rowhead").children.length; i < n; i++)
	{
		var rowhead = document.getElementById("select_rowhead").children[i].itemname;

		var oRowHead = document.all.item(rowhead);
		if (null != oRowHead)
		{
			var type = new String(oRowHead.type);	
			if ("text" == type || "hidden" == type || "textarea" == type)
			{
				var value = oRowHead.value;
				if (-1 != value.search("^\\s{0,}$"))
				{
					alert(oRowHead.chinesename + "已经选做行表头,请填写相关值. ");		
					return false;
				}					
			}
			else if ("checkbox" == type)
			{
				if (!selectChecked(oRowHead))
				{
					alert(oRowHead[0].chinesename + "已经选做行表头,请选择相关值.");
					return false;
				}
			}
		}
	}

	return true;
}
function colheadCheck()
{
	for(var i = 0, n =  document.getElementById("select_colhead").children.length; i < n; i++)
	{
		var colhead = document.getElementById("select_colhead").children[i].itemname;
		var oColHead = document.all.item(colhead);
		if (null != oColHead)
		{
			var type = new String(oColHead.type);	
			if ("text" == type || "hidden" == type || "textarea" == type)
			{
				var value = oColHead.value;
				if (-1 != value.search("^\\s{0,}$"))
				{
					alert(oColHead.chinesename + "已经选做列表头,请填写相关值.");		
					return false;
				}					
			}
			else if ("checkbox" == type)
			{
				if (!selectChecked(oColHead))
				{
					alert(oColHead[0].chinesename + "已经选做列表头,请选择相关值.");
					return false;
				}
			}
		}
	}

	return true;
}

function checkListHead()
{
	for(var i = 0, n =  document.getElementById("select_column").children.length; i < n; i++)
	{
		var isNecessary =  document.getElementById("select_column").children[i].necessary;
		if (isNecessary == 'true')
		{
			alert(document.getElementById("select_column").children[i].chinesename + "为必选报表头,请选择.");
			return false;
		}
	}

	return true;
}

function checkStatcolumn()
{
	for(var i = 0, n =  document.getElementById("select_statcolumn").children.length; i < n; i++)
	{
		var isNecessary =  document.getElementById("select_statcolumn").children[i].necessary;
		if (isNecessary == 'true')
		{
			alert(document.getElementById("select_statcolumn").children[i].chinesename + "为必选统计指标,请选择.");
			return false;
		}
	}

	return true;
}

function checkSumusable()
{
	if (0 == document.getElementById("select_rowhead").children.length && (form1.show_sum_flag.checked || form1.show_total_flag.checked))
	{
		alert("行表头未选择,禁止选择小计或总计.");
		return false;
	}
	return true;
}

function getParent(itemName)
{
	var objSpan = document.all.item( 'span' + itemName);

	parentnames += objSpan.name + ';';	
	types += objSpan.htmltype + ';';		
	values += getValue(objSpan.name, objSpan.htmltype) + ';';
 
	if ('' == objSpan.parentname)
	{
		return;
	}
	else
	{
		getParent(objSpan.parentname); 				
	} 
	return;
	 
}

function isInitValue(itemName)
{
	var objSpan = document.all.item( 'span' + itemName);
	
	var parentItemName = objSpan.parentname;
	if ('' != parentItemName)
	{
		var objParent = document.all.item( 'span' + parentItemName);
		var value = getValue(objParent.name, objParent.htmltype);
		if ('' != value)
		{
			return true;
		}
	}
	else
	{
		return true;
	}

	return false;
}


function initSpanHTML(itemName)
{
	var objSpan = document.all.item( 'span' + itemName);
	var htmlType = objSpan.htmltype;
    
	if (2 == htmlType) //checkbox
	{
		objSpan.innerHTML = "";
	}
	else if (5 ==  htmlType) //select
	{
		objSpan.innerHTML = "";
	}
}

function getValue(itemName, htmlType)
{
	var value = '';
	if (2 == htmlType || 3 == htmlType)
	{
		var objItems = document.all.item(itemName);
		if (null == objItems)
		{
			return value;
		}

		if (objItems.length)
		{
			for (var i = 0, n = objItems.length; i < n; i++)
			{
				if (objItems[i].checked && !objItems[i].disabled)
				{
					value += objItems[i].value + ':';
				}
			}
		}
		else
		{
				if (objItems.checked && !objItems.disabled)
				{
					value += objItems.value + ':';
				}
		}
		return value;
	}
	else if (9 == htmlType)
	{
		var objItems = document.all.item(itemName);
		if (null == objItems)
		{
			return value;
		}

		if (objItems.length)
		{
			for (var i = 0, n = objItems.length; i < n; i++)
			{
				if (objItems[i].selected)
				{
					if ('' != value)
					{
						value += ':' + objItems[i].value;
					}
					else
					{
						value += objItems[i].value;
					}
				}
			}
		}
		else
		{
				if (objItems.selected)
				{
					value = objItems.value;
				}
		}
		return value;		
	}
	else
	{	
		return document.all.item(itemName).value; 					
	}
}

function setCheckBox(objsrc, itemname)
{
	var objItems = document.all.item(itemname);
	
	if (null == objItems)
	{
		return;
	}

	if (objItems.length)
	{
		for (var i = 0, n = objItems.length; i < n; i++)
		{
			objItems[i].disabled = false;
		}

		if (objsrc.checked)
		{
			for (var i = 0, n = objItems.length; i < n; i++)
			{
				objItems[i].checked = true;
				objItems[i].disabled = true;
			}
		}
	}
	else
	{
		objItems.disabled = false;
		
		if (objsrc.checked)
		{
				objItems.checked = true;
		}
	}
	
	eval("on" + itemname + "change()");
	return;
}


function sendRequest(url,innerHTMLObj)
{
	var request = false;
	if(window.HttpResponse)
	{
		request=new XMLHttpResponse();
	}
	else
	{
		if(window.ActiveXObject)
		{
			try
			{
				request=new ActiveXObject("Msxml2.XMLHTTP");
			}
			catch(e)
			{
				try
				{
					request=new ActiveXObject("Microsoft.XMLHTTP");
				}
				catch(e)
				{
				}
			}
		}
	}	
	
	if (!request)
	{
		alert("请升级IE");
		return;
	}

	try
	{
		document.all("show").innerHTML = "查询维度数据中......";
	}
	catch(e)
	{
	}
	
	request.open("POST", url, false);
	request.send();	
	document.all(innerHTMLObj).innerHTML = request.responseText;			

	try
	{
		document.all("show").innerHTML = "";
	}
	catch(e)
	{
	}
}

function setCheckBox(objsrc, itemname)
{
	var objItems = document.all.item(itemname);
	if (null == objItems)
	{
		return;
	}

	if (objItems.length)
	{
		for (var i = 0, n = objItems.length; i < n; i++)
		{
			objItems[i].disabled = false;
		}

		if (objsrc.checked)
		{
			for (var i = 0, n = objItems.length; i < n; i++)
			{
				objItems[i].checked = true;
			}
		}
	}
	else
	{
		objItems.disabled = false;
		
		if (objsrc.checked)
		{
				objItems.checked = true;
		}
	}

	return;
}


function getDate(value)
{
	if (null == value)
	{
		alert("日期格式错误.应为'2007-08-12'或者'20070801'样式.");
		return null;
	}

	if (value.match("^\\d{4}-\\d{1,2}-\\d{1,2}$"))
	{
		var dates = value.split("-");
		var newDate = new Date(dates[0], dates[1] - 1, dates[2]);
		
		if (dates[0] != newDate.getFullYear()
			|| dates[1] != (newDate.getMonth() + 1)
			|| dates[2] != newDate.getDate())
		{
			alert(value + "日期错误.");
			return null;
		}
		return newDate;	
	}	
	else if (value.match("^\\d{8}$"))
	{
		var year = value.substr(0, 4);
		var month = value.substr(4, 2);	
		var day = value.substr(6, 2);
		var newDate = new Date(year, month - 1, day);
		
		if (year != newDate.getFullYear()
			|| month != (newDate.getMonth() + 1)
			|| day != newDate.getDate())
		{
			alert(value + "日期错误.");
			return null;
		}
		return newDate;	
	}
	else if (value.match("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$"))
	{
		var times = value.split(" ");
		dates = times[0].split("-");
	    var newDate = new Date(dates[0], dates[1] - 1, dates[2]);
		
		if (dates[0] != newDate.getFullYear() || dates[1] != (newDate.getMonth() + 1) || dates[2] != newDate.getDate())
		{
			return false;
		}
		
		var hours = times[1].split(":");
		if (parseFloat(hours[0]) > 23 || parseFloat(hours[0]) < 0 
			|| parseFloat(hours[1]) > 59 || parseFloat(hours[1]) < 0   
			|| parseFloat(hours[2]) > 59 || parseFloat(hours[2]) < 0)
		{
			return null;
		}
		
		return new Date(dates[0], dates[1] - 1, dates[2], hours[0], hours[1], hours[2]);
	}
	else
	{
		alert("日期格式错误.应为'2007-08-12'或者'20070801'或者'2007-08-12 05:09:04'样式.");
		return null;
	}
}
function pageSetup()
{

	try
	{
		var RegWsh = new ActiveXObject("WScript.Shell");
		var hkey_root="HKEY_CURRENT_USER\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
		RegWsh.RegWrite(hkey_root+"header","&b打印时间:&d &T");
		RegWsh.RegWrite(hkey_root+"footer","&b第&p页&b共&P页");
	}
	catch(e)
	{
		alert("请将报表系统加入到受可信任的网点.允许自动设置页码.");
	}

	document.getElementById("printspan").innerHTML = "<object  id='WebBrowser'  classid='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2'  height='0'  width='0' viewastext></object>"; 
	
	//top.frames[1].document.all.WebBrowser.ExecWB(7,1);
}
function search(selectName, contentName, srcName)
{ 
	var sel = document.all.item(selectName);
	var condtion = document.all.item(contentName).value;
	var src = document.all.item(srcName);
	var selectedArray = new Array();
	var sSize = 0;	
	
	while (sel.options.length > 0)
	{
		if (sel.options[0].selected)
		{
			selectedArray[sSize++] = sel.options[0];  
		}
		sel.options.remove(0);
	}		
	
	if("" == condtion.replace(/^\s+|\s+$/, ""))
	{
		for (var i = 0, n = src.options.length; i < n; i++)
		{
			var op = document.createElement('option');
			op.value = src.options[i].value;
			op.text = src.options[i].text;
			sel.options.add(op);	
			for (var j = 0, m = selectedArray.length; j < m; j++)
			{
				var sOp = selectedArray[j];
				if (op.value == sOp.value)
				{
					sel.options[i].selected = true;		
					break;
				}
			}
		}
	} 
	else
	{
		for (var i = 0, n = selectedArray.length; i < n; i++)
		{
			sel.options.add(selectedArray[i]);
		}
		
		for (var i = 0, n = src.options.length; i < n; i++)
		{
			var op = src.options[i];
			var opText = op.text;
			if (-1 != opText.indexOf(condtion))
			{
				var f = false;
				for (var j = 0, m = selectedArray.length; j < m; j++)
				{
					var sOp = selectedArray[j];
					if (sOp.value == op.value)
					{
						f = true;
						break;
					}
				}
				if (!f)
				{
					var opt = document.createElement('option');
					opt.value = op.value;
					opt.text = opText;
					sel.options.add(opt);				
				}
			}	
		}
	}
		
	return;
}

function selectAll(selectName)
{
	var sel = document.all.item(selectName);
	for (var i = 0, n = sel.options.length; i < n; i++)
	{
		sel.options[i].selected = true;	
	}
}

function invertedChoose(selectName)
{
	var sel = document.all.item(selectName);
	for (var i = 0, n = sel.options.length; i < n; i++)
	{
		if (true == sel.options[i].selected)
		{
			sel.options[i].selected = false;
		}
		else
		{
			sel.options[i].selected = true;
		}
			
	}
	
}

function cancelAll(selectName)
{
	var sel = document.all.item(selectName);
	for (var i = 0, n = sel.options.length; i < n; i++)
	{
		sel.options[i].selected = false;	
	}
}
function checkHistory(obj)
{
	if (true == obj.checked && !confirm("查询历史表数据,系统响应速度将比查询当前表系统响应时间长.\n是否确认本次查询包含历史表数据?"))
	{
		obj.checked = false;	
	}

}
function replaceString(repStr,rgExp,replaceText){
    var str = repStr.replace(rgExp,replaceText);
    if(str.indexOf(rgExp)!= -1){
    	str = replaceString(str,rgExp,replaceText);
    }
    return str;
}



function openwin(selectStr,TagID){
	
	var mote="dialogHeight: 350px; dialogWidth: 350px; center: yes; help: no;status:no;title:no;scroll:no";
	var result = window.showModalDialog ("show_list.jsp",mxh,mote) ;
		
		if(result==undefined) 
		     return false;
		else
			document.getElementById(TagID+'Text').value=result;
		     return true;
}
function openwin(selectStr,TagID){
	var selectStr = replaceString(selectStr,"|","'");
    var startIndex = selectStr.indexOf("onchange");
    var endIndex = selectStr.indexOf("change()");
    var replaceStr = selectStr.substring(startIndex,endIndex+9);
    selectStr = selectStr.replace(replaceStr,'');
    var serchStr= "<input type='text' size='25' class='input' onkeyup=\"search('"+TagID+"','"+TagID+"_cnt','"+TagID+"_src')\"   name='"+TagID+"_cnt'/>"+
						    	"<input name='sa' type='button' value='全 选' class='btn4' onclick=\"selectAll('"+TagID+"')\"/>"+
							    "<input name='csi' type='button' value='反 选' class='btn3' onclick=\"invertedChoose('"+TagID+"')\"/>"+
							    "<input name='csa' type='button' value='取 消' class='btn3' onclick=\"cancelAll('"+TagID+"')\"/>";
	//    var selectStr = document.getElementById(TagID).outerHTML;
		var  showText = '';
	    var   mxh  =   new   Array(selectStr,TagID,serchStr);
		var mote="dialogHeight: 240px; dialogWidth: 310px; center: yes; help: no;status:no;title:no;scroll:no";
		var result = window.showModalDialog ("show_list.jsp",mxh,mote) ;
		if(result==undefined) 
		     return false;
		else{
				 var parentSelect = document.getElementById(TagID);
				 for(var i=0 ;i<result.length;i++){
					 	for(var j=0;j<parentSelect.options.length;j++){
					 		if(result[i] == parentSelect.options[j].value){
					 					parentSelect.options[j].selected=true;
										showText = document.getElementById(TagID).options[j].text+" , "+showText;
								}
					 	}			
					}
					 document.getElementById(TagID).onchange();
					 document.getElementById(TagID+'Text').value=showText.substring(0,showText.length-3);
					 return true;
		}
	}
function cancel(TagID){
		var parentSelect = document.getElementById(TagID);
	 	for(var i=0;i<parentSelect.options.length;i++){
			parentSelect.options[i].selected=false;
	 	}
	 	document.getElementById(TagID+'Text').value='';
	}