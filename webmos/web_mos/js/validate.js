function isNumber()
{
	return isValueNumber(event.srcElement.value);
}

function isDate()
{
	return isValueDate(event.srcElement.value);
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
	if (null == value || !value.match("^\\d{4}-\\d{1,2}-\\d{1,2}$"))
	{
		alert("日期格式错误.应为'2007-08-12'样式.");
		return false;
	}	
	
	var dates = value.split("-");
	var newDate = new Date(date[0], date[1], date[2]);
	
	if (date[0] != newDate.getYear() || date[1] != (newDate.getMonth() + 1) || date[2] != newDate.getDate())
	{
		alert("日期错误.");
		return false;
	}

	return true;	
}

function isNumberOrEmpty(value)
{
	if (null == value || -1 != value.search("^\\s{1,}$"))	
	{
		return true;
	}
	
	return isValueNumber(value);
}	

function isDateOrEmpty(value)
{
	if (null == value || -1 != value.search("^\\s{1,}$"))	
	{
		return true;
	}
	
	return isValueDate(value);
}

function selectChecked(obj)
{
	if(null == obj)
	{
		return false;
	}
	if(isNaN(obj.length))
	{
		if(obj.checked) 
		{
			return true;
		}
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

function onAddRowHead()
{
   var rList = new Array();	
   for(var i = 0, n = form1.select_column.options.length; i< n; i++)
   {
        if(form1.select_column.options[i].selected)
		{
            var optionName = form1.select_column.options[i].text;
            var optionValue = form1.select_column.options[i].value;
            form1.select_rowhead.add(new Option(optionName,optionValue));
			rList.push(optionValue);	
		}
   }	

   for (var i = 0, n = rList.length; i < n; i++)
   {
		for (var j = 0, m = form1.select_column.options.length; j < m; j++)
		{
			if (rList[i] == form1.select_column.options[j].value)
			{
				form1.select_column.options.remove(j);	
				break;
			}
		}
   }
}

function onDelRowHead()
{
    var rList = new Array();
    for(var i = 0, n = form1.select_rowhead.options.length; i < n; i++)
	{
        if(true == form1.select_rowhead.options[i].selected)
		{
            var optionName = form1.select_rowhead.options[i].text;
            var optionValue = form1.select_rowhead.options[i].value;
            form1.select_column.add(new Option(optionName,optionValue));
            rList.push(optionValue);
        }
    }

   for (var i = 0, n = rList.length; i < n; i++)
   {
		for (var j = 0, m = form1.select_rowhead.options.length; j < m; j++)
		{
			if (rList[i] == form1.select_rowhead.options[j].value)
			{
				form1.select_rowhead.options.remove(j);	
				break;
			}
		}
   }
}

function onAddColHead()
{
   var rList = new Array();
   for(var i = 0, n = form1.select_column.options.length; i< n; i++)
   {
        if(true == form1.select_column.options[i].selected)
		{
            var optionName = form1.select_column.options[i].text;
            var optionValue = form1.select_column.options[i].value;
            form1.select_colhead.add(new Option(optionName,optionValue));
			rList.push(optionValue);
		}
   }	

   for (var i = 0, n = rList.length; i < n; i++)
   {
		for (var j = 0, m = form1.select_column.options.length; j < m; j++)
		{
			if (rList[i] == form1.select_column.options[j].value)
			{
				form1.select_column.options.remove(j);	
				break;
			}
		}
   }
}

function onDelColHead()
{
	var rList = new Array();
    for(var i = 0, n = form1.select_colhead.options.length; i < n; i++)
	{
        if(true == form1.select_colhead.options[i].selected)
		{
            var optionName = form1.select_colhead.options[i].text;
            var optionValue = form1.select_colhead.options[i].value;
            form1.select_column.add(new Option(optionName,optionValue));
            rList.push(optionValue);
        }
    }

   for (var i = 0, n = rList.length; i < n; i++)
   {
		for (var j = 0, m = form1.select_colhead.options.length; j < m; j++)
		{
			if (rList[i] == form1.select_colhead.options[j].value)
			{
				form1.select_colhead.options.remove(j);	
				break;
			}
		}
   }
}

function onAddStatCol()
{
   var rList = new Array();
   for(var i = 0, n = form1.select_statcolumn.options.length; i< n; i++)
   {
        if(form1.select_statcolumn.options[i].selected == true)
		{
            var optionName = form1.select_statcolumn.options[i].text;
            var optionValue = form1.select_statcolumn.options[i].value;
            form1.show_column.add(new Option(optionName,optionValue));
			rList.push(optionValue);
		}
   }	

   for (var i = 0, n = rList.length; i < n; i++)
   {
		for (var j = 0, m = form1.select_statcolumn.options.length; j < m; j++)
		{
			if (rList[i] == form1.select_statcolumn.options[j].value)
			{
				form1.select_statcolumn.options.remove(j);	
				break;
			}
		}
   }

}

function onDelStatCol()
{
   var rList = new Array();			
   for(var i = 0, n = form1.show_column.options.length; i< n; i++)
   {
        if(form1.show_column.options[i].selected == true)
		{
            var optionName = form1.show_column.options[i].text;
            var optionValue = form1.show_column.options[i].value;
            form1.select_statcolumn.add(new Option(optionName,optionValue));
			rList.push(optionValue);
		}
   }	

   for (var i = 0, n = rList.length; i < n; i++)
   {
		for (var j = 0, m = form1.show_column.options.length; j < m; j++)
		{
			if (rList[i] == form1.show_column.options[j].value)
			{
				form1.show_column.options.remove(j);	
				break;
			}
		}
   }
}
function valiConWid(DateConWidName,objConWidName,dateCount){
   var sign = '0' ;
   var reSign = false;
   var count = '0';
   for (var i = 0; i < objConWidName.length; i++){
	   var mod = objConWidName[i].split("/");
	   // 以下(第270行)代码修改只影响到“流程干预”中的简单查询
	   // 如果内部单号、定单号码、业务号码、锁定状态四个条件都没有被选中，刚sign=0,不能查询；如果内部单号、定单号码、业务号码
	   // 三个条件中有一个选中或者锁定状态被选中且锁定状态的值是撤单锁定、改单锁定、缓装锁定三种锁定状态之一，sign=1,可以直接
	   // 直接查询；如果锁定状态选中，但锁定状态的值是 未锁定　，则sign=0,不能查询。
	   if (document.forms[0].all[mod[0]].checked || document.forms[0].all[mod[0]].value == "Y" || document.forms[0].all[mod[0]].value == "1" ){
		   if (document.all[mod[1]].value != ""&&document.all[mod[1]].value !="N"){// lijixu 20090702
			   sign = 1;
		   }
	   }
   }
   if (sign == '0'){
   	   if(dateCount==""||dateCount==null){
   	   		dateCount=7;
   	   }
	   for (var j = 0;j < DateConWidName.length; j++){
		    var dateMod = DateConWidName[j].split("/");
            var fromStr = document.forms[0].all(dateMod[1]).value;
			var toStr = document.forms[0].all(dateMod[2]).value;
			if(document.forms[0].all[dateMod[0]].checked || document.forms[0].all(dateMod[0]).value == "Y" ||document.forms[0].all(dateMod[0]).value == "1"){
				if ("" != fromStr && "" != toStr){
					  var sub = SubDate(fromStr,toStr);
					  if(sub < dateCount){
						count = count + 1;
					  }
				}
			}
	   }
	   if(count > '0'){
	      reSign = true;
	   }else{
	      reSign = false;
	   }
   }else{
      reSign = true;
   }
   return reSign;
}
function SubDate(fromDateStr,toDateStr){
	fromDateStr = fromDateStr.replace(/-/g,"/");
	var fromDate =Date.parse(fromDateStr);  
	toDateStr = toDateStr.replace(/-/g,"/");
	var toDate = Date.parse(toDateStr);   
	fromDate = new Date(fromDate);   
	toDate = new Date(toDate);  
	return (toDate-fromDate)/(24*60*60*1000)
}

/** 
 *为Data扩展format方法
 *把date型数据转为格式为'YYYY-MM-DD HH:MM:SS'
 *@author zhaigl
*/
Date.prototype.format = function(format){
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format))
    format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
    if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}


/*
 *比较两个时间的大小,只支持YYYY-MM-DD HH:MM:SS(目前系统通过控件获得的时间格式)
 *逻辑,先比较天数,若在同一天再比较小时,分钟
 *入参:_fromDate,_toDate
 *@author liukai
*/
function dateCompare(_fromDate,_toDate){
   var _fromLen=_fromDate.length;
   var _toLen=_toDate.length;
   var _fromDay=_fromDate.substring(0,10);
   var _toDay=_toDate.substring(0,10);
   var _filFromDay=_fromDay.replace(/-/g,'\/');
   var _filtoDay=_toDay.replace(/-/g,'\/');
  if(new Date(_filtoDay)>new Date(_filFromDay)){
      return true; 
   }else{
      if(_filtoDay==_filFromDay){
         var _fromHour=_fromDate.substring(10,_fromLen);
         var _toHour=_toDate.substring(10,_toLen);
         var _filFromHour=_fromHour.replace(/:/g,'');
         var _filToHour=_toHour.replace(/:/g,'');
         if(_filToHour>=_filFromHour){
           return true;
         }else{
            return false;
         }
      }else{
         return false;
      }
   }
};
// 检查输入的数量是否合法(至少不能为下面几种情况：１、没有输入或者在输入为空的情况下提交；
// ２、输入字母、特殊符号等其它不是数字的内容；３、输入为数字，是负数或者小数仍然不合法；
// 4、判断输入的数字超出了最大值或者最小值或者位数 等边界或者其它限制条件)
// lijx
function checkNum(num,str){
   var renValue = "1";
	num = Trim(num);
	// 判断输入是否为空
	 if(num.length<1){
	 	// alert("请输入数量！") 
	 	alert("\u8bf7\u8f93\u5165"+str+"!");
	    return  renValue = "0";
	 }
	 // 判断输入是否为数字
	 if(isNaN(num)){
	  	// alert("数量必须为数字！") 
	  	alert(str+"\u5fc5\u987b\u4e3a\u6570\u5b57!") 
	    return renValue = "0";
      }
     // 输入的补录数量是否为 正整数（补录数量不能为浮点数，也不能为非正数）
     var r = /^[0-9]*[1-9][0-9]*$/　;
     var flag= r.test(num);
	 if(flag == false){
	  	// alert("数量必须为正整数！") 
	 	alert(str+"\u5fc5\u987b\u4e3a\u6b63\u6574\u6570!")
	    return renValue = "0";
     } 
     if(num>"99999999"){
     	//alert("数量不能大于99999999！");
     	alert(str+"\u4e0d\u80fd\u5927\u4e8e 99999999!");
     	return renValue = "0";
     }
};







