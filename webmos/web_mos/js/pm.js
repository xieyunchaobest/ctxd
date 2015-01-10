/***************************************************************
*
*产品管理子系统PM中用到的全部JS方法（包含页面校验）
*需要配合public.js、prototype.js使用
*此JS仅供产品管理子系统PM使用
*author:longtao
***************************************************************/


/*产品管理子系统公用方法----START*/

function MM_preloadImages() { //v3.0
  	var d=document; 
  	if(d.images){ 
  		if(!d.MM_p) d.MM_p=new Array();
    	var i,j=d.MM_p.length,a=MM_preloadImages.arguments; 
    	for(i=0; i<a.length; i++){
    		if (a[i].indexOf("#")!=0){ 
    			d.MM_p[j]=new Image; 
    			d.MM_p[j++].src=a[i];
    		}
    	}
    }
}

function MM_swapImgRestore() { //v3.0
  	var i,x,a=document.MM_sr; 
  	for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++){ 
  		x.src=x.oSrc;
  	}
}

function MM_findObj(n, d) { //v4.01
  	var p,i,x;
  	if(!d) d=document; 
  	if((p=n.indexOf("?"))>0&&parent.frames.length) {
    	d=parent.frames[n.substring(p+1)].document; 
    	n=n.substring(0,p);
    }
  	if(!(x=d[n])&&d.all) x=d.all[n]; 
  	for (i=0;!x&&i<d.forms.length;i++) 
  		x=d.forms[i][n];
  	for(i=0;!x&&d.layers&&i<d.layers.length;i++) 
  		x=MM_findObj(n,d.layers[i].document);
  	if(!x && d.getElementById) 
  		x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  	var i,j=0,x,a=MM_swapImage.arguments; 
  	document.MM_sr=new Array; 
  	for(i=0;i<(a.length-2);i+=3){
   		if ((x=MM_findObj(a[i]))!=null){
   			document.MM_sr[j++]=x; 
   			if(!x.oSrc) x.oSrc=x.src; 
   			x.src=a[i+2];
   		}
   	}   	
}


var XForm = Class.create();
XForm.prototype = {
	initialize : function(/*Form*/ form){
		this._form = form;
	},
	addParam : function(/*object*/ options){
		for(name in options){
			if(options[name] instanceof Array){
				$A(options[name]).each(function(value,index){
					this._addParam(name,value);
				}.bind(this));
			}else{
				this._addParam(name,options[name]);
			}
		}
	},
	_addParam : function( /*String*/ name, /*String*/ value){
		if(this._form[name]){
			this._form[name].value = value;
		}else{
			var paramObj = document.createElement("input");
			paramObj.type="hidden";
			paramObj.name = name;
			paramObj.value = value;
			this._form.appendChild(paramObj);
		}
	},
	submit : function(){
		this._form.submit();
	}
};

/***************************************************************
* 获取在左侧列表中选中的记录的编号:主键ID，即将要增加的记录的编号
* 参数： checkBoxName:勾选框名字
* 返回： Array : 将要增加的记录的编号组成的一个一维数组
*****************************************************************/
function getToAddArray(checkBoxName){
	var toAddArray = new Array();
	var checkIds = document.getElementsByName(checkBoxName);
	var size = checkIds.length;
	if(size > 0){
		var position = 0;
		for(var i = 0;i < size;i++){
			if(checkIds[i].checked){
				toAddArray[position] = checkIds[i].value;
				position++;
			}
		}
	}

	return toAddArray;
}

/***************************************************************
* 获取在右侧列表已存在的记录的编号:主键ID，即数据库中已有的记录的编号
* 参数： hiddenName:隐藏域名字
* 返回： Array : 数据库中已有的记录的编号组成的一个一维数组
*****************************************************************/
function getExistArray(hiddenName){
	var existArray = new Array();
	var hiddenIds = document.getElementsByName(hiddenName);
	var size = hiddenIds.length;
	if(size > 0){
		var position = 0;
		for(var i = 0;i < size;i++){
				existArray[position] = hiddenIds[i].value;
				position++;
		}
	}

	return existArray;
}

/***************************************************************
* 获取在右侧列表中选中的记录的编号:主键ID，即将要删除的记录的编号
* 参数： checkBoxName:勾选框名字
* 返回： Array : 将要删除或者更新的记录的编号组成的一个一维数组
*****************************************************************/
function getRemoveArray(checkBoxName){
	var removeArray = new Array();
	var checkIds = document.getElementsByName(checkBoxName);
	var size = checkIds.length;
	if(size > 0){
		var position = 0;
		for(var i = 0;i < size;i++){
			if(checkIds[i].checked){
				removeArray[position] = checkIds[i].value;
				position++;
			}
		}
	}

	return removeArray;
}

/***************************************************************
* 获取在右侧列表中选中的记录的编号:主键ID，即将要删除的记录的编号
* 参数： removeArray被勾选的记录；suffix隐藏域id的后缀；
*       yesValue可操作的记录的隐藏域的值
* 返回： Array : 能被删除的记录的编号组成的一个一维数组
*****************************************************************/
function getTargetRemoveArray(removeArray,suffix,yesValue){
	var targetRemoveArray = new Array();
	var size = removeArray.length;
	if(size > 0){
		var position = 0;
		for(var i = 0;i < size;i++){
			var temp = removeArray[i]+suffix;
			if(document.getElementById(temp).value == yesValue){
				targetRemoveArray[position] = removeArray[i];
				position++;
			}
		}
	}

	return targetRemoveArray;
}

/***************************************************************
* 获取无权操作的记录的编号
* 参数： removeArray被勾选的记录；suffix隐藏域id的后缀；
*       yesValue可操作的记录的隐藏域的值
* 返回： Array : 不能被删除的记录的编号组成的一个一维数组
*****************************************************************/
function getUngrantedArray(removeArray,suffix,yesValue){
	var ungrantedArray = new Array();
	var size = removeArray.length;
	if(size > 0){
		var position = 0;
		for(var i = 0;i < size;i++){
			var temp = removeArray[i]+suffix;
			if(document.getElementById(temp).value != yesValue){
				ungrantedArray[position] = removeArray[i];
				position++;
			}
		}
	}

	return ungrantedArray;
}

/***************************************************************
* 获取在右侧列表中选中的记录的编号:主键ID，即将要更新的记录的编号
* 参数： checkBoxName:勾选框名字
* 返回： Array : 将要删除或者更新的记录的编号组成的一个一维数组
*****************************************************************/
function getUpdateArray(checkBoxName){
	var updateArray = new Array();
	var checkIds = document.getElementsByName(checkBoxName);
	var size = checkIds.length;
	if(size > 0){
		var position = 0;
		for(var i = 0;i < size;i++){
			if(checkIds[i].checked){
				updateArray[position] = checkIds[i].value;
				position++;
			}
		}
	}

	return updateArray;
}

/***************************************************************
* 获取两个数组中的重复记录的编号:主键ID，提示用户重复记录的编号
* 参数： resArray:源数组，如在左侧列表中选中的记录
* 参数： desArray:目的数组，如在右侧列表中的记录，即数据库中已有的记录
* 返回： Array : 重复的记录的编号组成的一个一维数组
*****************************************************************/
function getRepeatArray(resArray,desArray){
	var repeatArray = new Array();
	var position = 0;
	var repeatFlag = false;

	if(desArray.length == 0){//数据库中暂没有任何记录，显然不会有重复的记录
		return desArray;
	}

	for(var i=0;i<resArray.length;i++){
		for(var j=0;j<desArray.length;j++){
			if(resArray[i] == desArray[j]){
				repeatFlag = true;				
			}
		}
		if(repeatFlag){
			//出现重复记录，保存下来
			repeatArray[position] = resArray[i];
			position++;
		}
		repeatFlag = false;
	}

	return repeatArray;
}

/***************************************************************
* 获取在源数组而不再目的数组中的记录的编号，即需要增加的记录的编号
* 参数： resArray:源数组，如在左侧列表中选中的记录
* 参数： desArray:目的数组，如在右侧列表中的记录，即数据库中已有的记录
* 返回： Array : 新增的记录的编号组成的一个一维数组
*****************************************************************/
function getInsertArray(resArray,desArray){
	var insertArray = new Array();
	var position = 0;
	var insertFlag = true;

	if(desArray.length == 0){//数据库中暂没有任何记录，显然源数组就是要增加的记录的编号
		return resArray;
	}

	for(var i=0;i<resArray.length;i++){
		for(var j=0;j<desArray.length;j++){
			if(resArray[i] == desArray[j]){
				insertFlag = false;				
			}
		}
		if(insertFlag){
			//出现新的记录，保存下来
			insertArray[position] = resArray[i];
			position++;
		}
		insertFlag = true;
	}

	return insertArray;
}

/***************************************************************
* 判断源记录编号是否在目的记录编号数组中存在
* 参数： resRecordId:源记录编号，如在左侧类表中选择的一个记录
* 参数： desArray:目的记录编号数组，如在右侧列表中的记录，即数据库中已有的记录
* 返回： true or false : 存在则返回true，否则返回false
*****************************************************************/
function getRepeatFlag(resRecordId,desArray){
	var size = desArray.length;
	if( size == 0){
		return false;
	}

	for(var i=0;i<size;i++){
		if(desArray[i] == resRecordId){
			return true;
		}
	}

	return false;
}

function getDataNoCache(url,obj){
	var options={
		method: 'get',
		asynchronous: false,
		requestHeaders:["Cache-Control","no-cache"],
		onComplete: function(request){
			var resultHTML = request.responseText;									
			$(obj).innerHTML = resultHTML;						
		}
	}	
				  
	new Ajax.Request(url+"&timestamp="+new Date().getTime(),options); 
}

function queryStart(qryTR){
	var qryTRTable = document.getElementById(qryTR);
	qryTRTable.style.display="block";
}

function queryCancle(qryTR){
	var qryTRTable = document.getElementById(qryTR);
	qryTRTable.style.display="none";
}

/***************************************************************
* 
* 展开用于查询的层，并将焦点定位到某元素上
* 参数： qryTR:用于查询的层ID；focusName:需要定位焦点的元素名称
* 返回： 
*****************************************************************/
function queryStart(qryTR,focusName){
	var qryTRTable = document.getElementById(qryTR);
	qryTRTable.style.display="block";
	var focusElement = document.getElementsByName(focusName)[0];
	focusElement.focus();
}

/***************************************************************
* 
* 校验记录是否能被操作
* 参数：url 
* 返回： "true" or "false"
*****************************************************************/
function validateOperate(url){
var resultHTML ="";
	var options={
		method: 'get',
		asynchronous: false,
		requestHeaders:["Cache-Control","no-cache"],
		onComplete: function(request){
			resultHTML = request.responseText;								
		}
	}				  
	new Ajax.Request(url,options); 
	
	return 	resultHTML;	
}

/***************************************************************
* 
* 执行全选/全不选的功能
* 参数：parentCheckBox--"全选"勾选框名称；childCheckBox--"子"勾选框名称 
* 返回： 
*****************************************************************/
function allChecked(parentCheckBox,childCheckBox){
	var parent = document.getElementsByName(parentCheckBox);
	var child = document.getElementsByName(childCheckBox);
	for(var i=0,size=child.length;i<size;i++){
		child[i].checked = parent[0].checked;
	}
}

/***************************************************************
* 
* 执行清除勾选的功能
* 参数：parentCheckBox--"全选"勾选框名称；childCheckBox--"子"勾选框名称
* 返回： 
*****************************************************************/
function allUnChecked(parentCheckBox,childCheckBox){
	var parent = document.getElementsByName(parentCheckBox);
	var child = document.getElementsByName(childCheckBox);
	parent[0].checked = false;
	for(var i=0,size=child.length;i<size;i++){
		child[i].checked = false;
	}
}

/***************************************************************
* 
* 执行获取是否有操作权限的功能
* 参数：hiddenElement--页面隐藏域元素ID；value--校验值
* 说明：如果隐藏域的值与传入的value值相等则说明具有操作权限
* 返回：true or false
*****************************************************************/
function checkRight(hiddenElement,value){
	var srcValue = document.getElementById(hiddenElement).value;
	if(srcValue == value){
		return true;
	}else{
		alert("您没有操作该记录的权限");
		return false;
	}
}

/***************************************************************
* 
* 执行用户不具备操作某记录的权限时是否继续进入下一步的功能
* 参数：elementValue--待判值；judgeValue--基准值
* 说明：如果待判值与传入的基准值相等则说明具有操作权限
* 返回：true or false
*****************************************************************/
function checkStillContinue(element,value){
	if(element == value){
		return true;
	}else{
		if(confirm("您只有查看其基本信息的权限,没有修改其基本信息的权限,确定继续吗?")){
			return true;
		}else{
			return false;
		}
	}
}

/***************************************************************
* 
* 执行+/-前的简单判断
* 参数：selectedArray:选择删除的记录构成的数组
* 参数：type:标识是+、-还是修改；0为-、1为+、其他为修改
* 说明：仅判断是否选择了记录
* 返回：true or false
*****************************************************************/
function checkSelectLength(selectedArray,type){
	if(selectedArray.length == 0){
		if(type == 0){
			alert("请选择需要删除的记录");
		}else if(type == 1){
			alert("请选择需要添加的记录");
		}else{
			alert("请选择需要设置的记录");
		}
		return false;
	}else{
		return true;
	}
}

/***************************************************************
* 
* 执行添加前的简单判断
* 参数：repeatArray:弹出提示框显示重复的记录
* 说明：弹出提示框显示重复的记录
* 返回：true or false
*****************************************************************/
function alertRepeat(repeatArray){
	if(repeatArray.length > 0){
		alert("记录："+repeatArray+"已经存在");
	}
}

/***************************************************************
* 
* 执行新记录的提交
* 参数：resArray:准备添加的记录
* 参数：desArray:已有全部的记录
* 说明：仅判断是否选择了记录
* 返回：true or false
*****************************************************************/
function commitAdd(resArray,desArray){
	var repeatArray = getRepeatArray(resArray,desArray);
	var insertArray = getInsertArray(resArray,desArray);			
	if(repeatArray.length > 0){
		alert("记录："+repeatArray+"已经存在");
	}
	if(insertArray.length>0&&confirm("您确定要添加所选的记录?")){
		return true;
	}else{
		return false;
	}
}

/***************************************************************
* 
* 执行旧记录的提交
* 参数：deleteArray:准备删除的记录
* 说明：仅判断是否选择了记录
* 返回：true or false
*****************************************************************/
function commitDel(deleteArray){
	if(deleteArray.length == 0){
		alert("请选择需要删除的记录");
		return false;
	}
	if(deleteArray.length > 0 && confirm("您确定要删除所选的记录?")){
		return true;
	}else{
		return false;
	}
}

/***************************************************************
* 
* 执行记录的删除的校验
* 参数：deleteArray:准备删除的记录
* 参数：valiUrl
* 说明：仅判断是否选择了记录,并校验是否能够删除记录
* 返回：true or false
*****************************************************************/
function commitValiDel(deleteArray,valiUrl){
	if(deleteArray.length == 0){
		alert("请选择需要删除的记录");
		return false;
	}
	if(validateOperate(valiUrl)== "false"){
		alert("记录正在使用中.....不能删除");
		return false;
	}else{
		if(confirm("您确定要删除所选的记录?")){
			return true;
		}else{
			return false;
		}
	}
}

/***************************************************************
* 
* 执行记录的名称的校验
* 参数：valiUrl
* 参数：errorString
* 说明：仅判断名称是否被使用
* 返回：true or false
*****************************************************************/
function nameUsedVali(valiUrl,errorString){
	if(isEmpty(errorString)){	
		if(validateOperate(valiUrl)== "false"){
			alert("名称重复...请重新命名");
			return false;
		}else{
			return true;
		}
	}else{
		alert(errorString);
		return false;
	}
}

/***************************************************************
* 
* 执行记录的名称的校验
* 参数：valiUrl
* 参数：errorString
* 参数：originName
* 参数：name
* 说明：仅判断名称是否被使用
* 返回：true or false
*****************************************************************/
function nameUsedVali2(valiUrl,errorString,originName,name){
	if(isEmpty(errorString)){
		if(originName == name){
			return true;
		}else{
			if(validateOperate(valiUrl)== "false"){
				alert("名称重复...请重新命名");
				return false;
			}else{
				return true;
			}
		}
	}else{
		alert(errorString);
		return false;
	}
}

/***************************************************************
* 
* 执行禁用鼠标右键
* 参数：
* 返回：
*****************************************************************/
function disableMouseRight(){
	document.oncontextmenu=new Function("event.returnValue=false;");
	document.onselectstart=new Function("event.returnValue=false;");
}

/***************************************************************
* 
* 执行隐藏父页面中的某元素
* 参数：elementId父页面元素的ID
* 返回：
*****************************************************************/
function hideFatherElement(elementId){
	var addTable = self.parent.document.getElementById(elementId);
	addTable.style.display="none";
}

/***************************************************************
* 
* 执行校验是否输入了查询名称
* 参数：查询元素的名称
* 返回：true or false
*****************************************************************/
function hasFillName(elementName){
	var queryName = document.getElementsByName(elementName)[0];
	var name = trim(queryName.value);
	if(isEmpty(name)){
		alert("请输入查询名称");
		queryName.focus();
		return false;
	}else{
		return true;
	}
}

/***************************************************************
* 
* 判断可能包含汉字的STRING的长度是否超出数据库设置
* 参数：str待校验的字符串length数据库中的长度textName文本框的名称
* 返回：错误信息字符串
*****************************************************************/
function checkCNStrLen(str,length,textName){
	var msg = "";
	if(str.length > 0){
		if(!checkCN(str,length)){
			msg = textName + "不能超过" + length + "个字符\n";
		}
	}
	
	return msg;
}

/***************************************************************
* 
* 判断String是否为空或者空白串,为空或者空白串则返回错误提示
* 参数：str待校验的字符串textName文本框的名称
* 返回：错误信息字符串
*****************************************************************/
function checkBlankStr(str,textName){
	var msg = "";
	if(isEmpty(str)){
		msg = textName + "不能为空\n";
	}
	return msg;
}

/***************************************************************
* 
* 判断必选下拉框是否已经选择过值
* 参数：optionValue下拉框的值alertValue不能通过校验的值textName下拉框的名称
* 返回：错误信息字符串
*****************************************************************/
function checkSelectOption(optionValue,alertValue,textName){
	var msg = "";
	if(optionValue == alertValue){
		msg = "请选择" + textName + "\n";
	}
	
	return msg;
}

/***************************************************************
* 
* 检查下拉列表框中选定的选项是否默认选项
* 参数： str:选择的选项,cStr:默认选项
* 返回： true:选择的选项等于默认选项  false:选择的选项不等于默认选项
*****************************************************************/
function checkSelect(str,cStr){
	if(str == cStr){
		return true;
	}else{
		return false;
	}
}

/***************************************************************
* 
* 判断字符串是否为合法的整数输入，长度是否超限
* 参数：str待校验的字符串值length最大长度限制textName文本框的名称
* 返回：错误信息字符串
*****************************************************************/
function checkInt(str,length,textName){
	var msg = "";
	if(str != ""){
		if(!checkTheLetter(str,1)){//首先校验输入是否全为数字
			msg = textName + "只能为合法整数\n"
		}else{
			var intStr = parseInt(str,10);
			if(isNaN(intStr)){
				msg = textName + "只能为合法整数\n"
			}else{
				var intLength = parseInt(trim(length),10);
				if(intStr.length > intLength.length){
					msg = textName + "的长度不能超过" + length +"位\n"
				}
			}
		}
	}
	
	return msg;
}

/***************************************************************
* 
* 比较整型输入的两个字符串的大小，要求str1不大于str2
* 参数：str1、str2待校验的字符串值textName1、textName2文本框的名称
* 返回：错误信息字符串
*****************************************************************/
function compareInt(str1,str2,textName1,textName2){
	var msg = "";
	if(!isEmpty(str1) && !isEmpty(str2)){
		var int_str1 = parseInt(trim(str1),10);
		var int_str2 = parseInt(trim(str2),10);
		if(!isNaN(int_str1) && !isNaN(int_str2)){
			if(int_str1 > int_str2){
				msg = textName1 + "不能大于" + textName2;
			}
		}
	}
	
	return msg;
}

/***************************************************************
* 
* 判断字符串是否为合法的浮点型输入，长度是否超限
* 参数：str待校验的字符串值intLength整数位最大长度限制
*     decLength小数位最大长度限制 textName文本框的名称
* 返回：错误信息字符串
*****************************************************************/
function checkFloat(str,intLength,decLength,textName){
	var msg = "";
	var formatMsg = textName + "输入不合法\n【合法输入支持：" + intLength + "位整数+" + decLength + "位小数】\n";
	if(str != ""){
		var splitArray = str.split(".");
		var len = splitArray.length;
		if(len == 1){//输入为整数
			var tempMsg = checkInt(str,intLength,textName);
			if(tempMsg != ""){
				msg = formatMsg;
			}
		}else if(len == 2){//小数形式输入
			var intStr = splitArray[0];//整数部分
			var decStr = splitArray[1];//小数部分
			if(intStr.length == 0){//以.开始的纯小数，不允许输入
				msg = formatMsg;
			}else if(decStr.length == 0){//以.结束的纯整数，不允许输入
				msg = formatMsg;
			}else{//包含小数和整数的完整输入
				var tempMsg = checkInt(intStr,intLength,textName);
				if(tempMsg != ""){//首先校验整数部分
					msg = formatMsg;
				}else{//接着校验小数部分
					if(!checkTheLetter(decStr,1)){//校验输入是否全为数字
						msg = formatMsg;
					}else{//再校验长度限制
						if(decStr.length > decLength){
							msg = formatMsg;
						}
					}
				}
			}			
		}else{
			msg = formatMsg;
		}
	}
	
	return msg;
}

/***************************************************************
* 
* 判断两个日期的大小：生效时间不能大于失效时间
* 参数：str1,str2待校验的字符串值textName1,textName2文本框的名称
* 返回：错误信息字符串
*****************************************************************/
function compareDateStr(str1,str2,textName1,textName2){
	var msg = "";//str1为起始日期，str2为终止日期
	if(!isEmpty(str1) && !isEmpty(str2)){
		if(!dateCompare(str1,str2)){
			msg = textName1 + "不能大于" + textName2 + "\n";
		}
	}
	
	return msg;
}

/***************************************************************
* 
* 比较浮点型输入的两个字符串的大小，要求str1不大于str2
* 参数：str1、str2待校验的字符串值textName1、textName2文本框的名称
* 返回：错误信息字符串
*****************************************************************/
function compareFloat(str1,str2,textName1,textName2){
	var msg = "";
	if(!isEmpty(str1) && !isEmpty(str2)){
		var float_str1 = parseFloat(trim(str1),10);
		var float_str2 = parseFloat(trim(str2),10);
		if(!isNaN(float_str1) && !isNaN(float_str2)){
			if(float_str1 > float_str2){
				msg = textName1 + "不能大于" + textName2;
			}
		}
	}
	
	return msg;
}
/*产品管理子系统公用方法----END*/


/*产品管理子系统子模块独用页面校验方法----START*/

/***************************************************************
* 
* 目录管理模块：通信服务目录基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiCommServSpecTree(){
	var errorString = "";
	//通信服务树节点名称（不能为空，长度不能超60,name)
	var name = document.getElementById("name");
	var blankCheckMsg = checkBlankStr(name.value,"目录名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(name.value,60,"目录名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}
	
	return errorString;
}

/***************************************************************
* 
* 目录管理模块：销售资源目录基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiResourceSpecTree(){
	var errorString = "";
	//销售资源树节点名称（不能为空，长度不能超60,name)
	var name = document.getElementById("name");
	var blankCheckMsg = checkBlankStr(name.value,"目录名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(name.value,60,"目录名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}
	
	return errorString;
}

/***************************************************************
* 
* 目录管理模块：产品目录基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiProdTree(){
	var errorString = "";
	//产品树节点名称（不能为空，长度不能超60,name)--%>
	var name = document.getElementById("name");
	var blankCheckMsg = checkBlankStr(name.value,"目录名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(name.value,60,"目录名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}	
	return errorString;
}

/***************************************************************
* 
* 基本信息管理模块：属性基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiCharacteristic(){
	var errorString = "";			
	//属性名称（不能为空，长度不能超60,name)--%>			
	var name = document.getElementById("name");
	var blankCheckMsg = checkBlankStr(name.value,"属性名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(name.value,60,"属性名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}
	//数据类型标识(不能为请选择,dataType)--%>
	var dataType = document.getElementById("dataType");	
	errorString = errorString + checkSelectOption(dataType.value,"X","数据类型");
	//属性值类型(不能为请选择,valueType)--%>
	var valueType = document.getElementById("valueType");
	errorString = errorString + checkSelectOption(valueType.value,"X","属性值类型");				
	if(valueType.value == "D"){
		//属性值类型为离散选择型时，数据类型只能为字符串型或者数字型--%>
		if(dataType.value == "VA"||dataType.value == "N"){
			//正确的设置--%>
		}else{
			//非法的设置--%>
			errorString = errorString + "属性值类型为离散选择型时，数据类型只能为字符串型或者数字型 \n";
		}
	}
	//起始值(varchar2(10))--%>
	var fromValue = document.getElementById("fromValue");
	errorString = errorString + checkCNStrLen(fromValue.value,10,"起始值");
	var toValue = document.getElementById("toValue");
	errorString = errorString + checkCNStrLen(toValue.value,10,"终止值");
	//默认值(vachar2(10))--%>
	var defaultValue = document.getElementById("defaultValue");
	errorString = errorString + checkCNStrLen(defaultValue.value,10,"默认值");
	//标准编码(长度不能超过45，standardCode)--%>
	var standardCode = document.getElementById("standardCode");				
	errorString = errorString + checkCNStrLen(standardCode.value,10,"标准编码");
	//属性值最大长度限制(能为空,只能为数字，数字位数最大为6,valueLenLimit)--%>
	var valueLenLimit = document.getElementById("valueLenLimit");
	errorString = errorString + checkInt(valueLenLimit.value,6,"最大长度");
	//属性值最小长度限制(能为空,只能为数字，数字位数最大为6,valueLenMinLimit)--%>
	var valueLenMinLimit = document.getElementById("valueLenMinLimit");
	errorString = errorString + checkInt(valueLenMinLimit.value,6,"最小长度");
	//最大长度不能小于最小长度--%>
	compareInt(valueLenMinLimit.value,valueLenLimit.value,"最小长度","最大长度");	
	//属性值个数限制(能为空,只能为数字，数字位数最大为6,valueNumLimit)--%>
	var valueNumLimit = document.getElementById("valueNumLimit");
	errorString = errorString + checkInt(valueNumLimit.value,6,"个数限制");
	
	return errorString;	
}

/***************************************************************
* 
* 基本信息管理模块：属性值基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiEnumValue(){
	var errorString = "";			
	//属性值（不能为空，长度不能超45,enumValueCode)	--%>		
	var enumValueCode = document.getElementById("enumValueCode");			
	var blankCheckMsg = checkBlankStr(enumValueCode.value,"属性值");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(enumValueCode.value,45,"属性值");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}		
	//属性值名称(不能为空 varchar2(45))--%>			
	var enumValueName = document.getElementById("enumValueName");
	var blankCheckMsg2 = checkBlankStr(enumValueName.value,"属性值名称");
	if(blankCheckMsg2 == ""){
		var lengthCheckMsg = checkCNStrLen(enumValueName.value,45,"属性值名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg2;
	}
	
	return errorString;	
}

/***************************************************************
* 
* 通信服务管理模块：通信服务基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiCommServSpec(){
	var errorString = "";
	//通信服务名称（不能为空，长度不能超60,name)
	var name = document.getElementById("name");
	var blankCheckMsg = checkBlankStr(name.value,"服务名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(name.value,60,"服务名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}
	//通信服务种类(不能为请选择,commServSpecCatId)
	var commServSpecCatId = document.getElementById("commServSpecCatId");
	errorString = errorString + checkSelectOption(commServSpecCatId.value,"X","服务种类");
	//主体标识(不能为请选择,mainFlag)-->
	var mainFlag = document.getElementById("mainFlag");	
	errorString = errorString + checkSelectOption(mainFlag.value,"X","主体标识");		
	//重复标识(不能为请选择,repFlag)--
	var repFlag = document.getElementById("repFlag");
	errorString = errorString + checkSelectOption(repFlag.value,"X","重复标识");	
	//标准编码(长度不能超过30，standardCode)-->
	var standardCode = document.getElementById("standardCode");
	errorString = errorString + checkCNStrLen(standardCode.value,30,"标准编码");	
					
	return errorString;
}

/***************************************************************
* 
* 协议管理模块：协议规格基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiContractTemplate(){
	var errorString ="";
	//协议名称（不能为空，长度不能超60,name)
	var name = document.getElementById("name");
	var blankCheckMsg = checkBlankStr(name.value,"协议名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(name.value,60,"协议名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}
	//协议类型(不能为请选择,contractType)--%>
	var contractType = document.getElementById("contractType");	
	errorString = errorString + checkSelectOption(contractType.value,"X","协议类型");	
	//相关文件(长度不能超60,relFile)
	var relFile = document.getElementById("relFile");
	errorString = errorString + checkCNStrLen(relFile.value,60,"相关文件路径");
	//协议编码(长度不能超60,contractTempCode)
	var contractTempCode = document.getElementById("contractTempCode");	
	errorString = errorString + checkCNStrLen(contractTempCode.value,60,"相关文件路径");
	//模板描述(长度不能超255,templateDesc);
	var templateDesc = document.getElementById("templateDesc");	
	errorString = errorString + checkCNStrLen(templateDesc.value,255,"相关文件路径");
				
	return errorString;	
}

/***************************************************************
* 
* 协议管理模块：协议约束基本信息维护，页面校验
* 参数：
* 返回：
*****************************************************************/
function valiContractTemplateRestrict(){
	var errorString = "";
	//服务动作(不能为请选择,mainFlag)-->
	var servActId = document.getElementById("servActId");
	errorString = errorString + checkSelectOption(servActId.value,"X","服务动作");
	//约束时长（只能为数字，数字位数最大6位,resCycle)
	var resCycle = document.getElementById("resCycle");
	errorString = errorString + checkInt(resCycle.value,6,"约束时长");
	//约束方法（不能为空,resMethod)
	var resMethod = document.getElementById("resMethod");
	errorString = errorString + checkCNStrLen(resMethod.value,100,"约束方法");
	//约束描述(能为空,长度不能超255,resDesc);
	var resDesc = document.getElementById("resDesc");
	errorString = errorString + checkCNStrLen(resDesc.value,255,"约束描述");
				
	return errorString;	
}

/***************************************************************
* 
* 协议管理模块：协议约束惩罚基本信息维护，页面校验
* 参数：
* 返回：
*****************************************************************/
function valiContractTemplatePunish(){
	var errorString ="";
	//单位违约金(只能为数字，数字位数最大6位,unitPunishFine)
	var unitPunishFine = document.getElementById("unitPunishFine");
	errorString = errorString + checkInt(unitPunishFine.value,6,"单位违约金");
	//数量(只能为数字，数字位数最大6位,count)
	var count = document.getElementById("count");
	errorString = errorString + checkInt(count.value,6,"数量");
	//惩罚描述(长度不能超255,punishDescription)			    
	var punishDesc = document.getElementById("punishDesc");
	errorString = errorString + checkCNStrLen(punishDesc.value,255,"惩罚描述");
				
	return errorString;	
}

/***************************************************************
* 
* 群组规格管理模块：群组规格基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiGroupSpec(){
	var errorString = "";
	//群组规格名字（不能为空，长度不能超60,name)
	var name = document.getElementById("name");
	var blankCheckMsg = checkBlankStr(name.value,"群组名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(name.value,60,"群组名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}
	//--涉及服务开通(不能为请选择,pfFlag)--%>
	var pfFlag = document.getElementById("pfFlag");	
	errorString = errorString + checkSelectOption(pfFlag.value,"X","服务开通标识");
	//--群组编码生成标志(不能为请选择,groupNbrFlag)--%>
	var groupNbrFlag = document.getElementById("groupNbrFlag");	
	errorString = errorString + checkSelectOption(groupNbrFlag.value,"X","编码生成标识");
	//--管理归属(不能为请选择,attchFlag)--%>
	var attchFlag = document.getElementById("attchFlag");
	errorString = errorString + checkSelectOption(attchFlag.value,"X","管理归属");
	//--群组是否动态生成(不能为请选择,autoFlag)--%>
	var autoFlag = document.getElementById("autoFlag");
	errorString = errorString + checkSelectOption(autoFlag.value,"X","动态生成标识");	
	//--群组类型(不能为请选择,mainFlag)--%>
	var groupTypeId = document.getElementById("groupTypeId");
	errorString = errorString + checkSelectOption(groupTypeId.value,"X","群组类型标识");
	//--关系类型(不能为请选择,relType)--%>
	var relType = document.getElementById("relType");
	errorString = errorString + checkSelectOption(relType.value,"X","关系类型标识");
	//--最小数量(不能为空,minNumber Number(6))--%>
	var minNumber = document.getElementById("minNumber");
	var blankCheckMsg2 = checkBlankStr(minNumber.value,"最小数量");
	if(blankCheckMsg2 == ""){
		errorString = errorString + checkInt(minNumber.value,6,"最小数量");
	}else{
		errorString = errorString + blankCheckMsg2;		
	}
	
	return errorString;
}
/***************************************************************
* 
* 价格计划管理模块：定价参数基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiPriceParam(){
	var errorString = "";
	//--参数名称（不能为空，长度不能超32,name)--%>
	var name = document.getElementById("name");
	var blankCheckMsg = checkBlankStr(name.value,"参数名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(name.value,32,"参数名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}
	//--取值类型(不能为请选择,inputFlag)--%>				
	var inputFlag = document.getElementById("inputFlag");	
	errorString = errorString + checkSelectOption(inputFlag.value,"X","取值类型");
	//--数据类型(不能为请选择,inputType)--%>
	var inputType = document.getElementById("inputType");
	errorString = errorString + checkSelectOption(inputType.value,"X","数据类型");
	//--必填类型(不能为请选择,inputFlag)--%>				
	var nullFlag = document.getElementById("nullFlag");	
	errorString = errorString + checkSelectOption(nullFlag.value,"X","必填标识");	
	//--默认值(长度不能超20,最多允许输入2位小数,defaultValue)--%>
	var defaultValue = document.getElementById("defaultValue");
	var defaultMsg = checkFloat(defaultValue.value,18,2,"默认值");
	errorString = errorString + defaultMsg;
	//--开始值(长度不能超20,最多允许输入2位小数,paramValueFrom)--%>
	var paramValueFrom = document.getElementById("paramValueFrom");
	var fromMsg = checkFloat(paramValueFrom.value,18,2,"开始值");
	errorString = errorString + fromMsg;
	//--终止值(长度不能超20,最多允许输入2位小数,paramValueTo)--%>
	var paramValueTo = document.getElementById("paramValueTo");
	var toMsg = checkFloat(paramValueTo.value,18,2,"终止值");	
	errorString = errorString + toMsg;
	//--终止值、起始值、默认值之间的校验--%>
	if(isEmpty(toMsg) && isEmpty(fromMsg)){		
		errorString = errorString + compareFloat(paramValueFrom.value,paramValueTo.value,"起始值","终止值");
	}
	if(isEmpty(toMsg) && isEmpty(defaultMsg)){
		errorString = errorString + compareFloat(defaultValue.value,paramValueTo.value,"默认值","终止值");
	}
	if(isEmpty(fromMsg) && isEmpty(defaultMsg)){
		errorString = errorString + compareFloat(paramValueFrom.value,defaultValue.value,"起始值","默认值");
	}

	return errorString;
}

/***************************************************************
* 
* 价格计划管理模块：定价参数值基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiPriceParamValue(){
	var errorString = "";			
	//--参数值（不能为空,长度不能超60,priceParamValue)--%>
	var priceParamValue = document.getElementById("priceParamValue");
	var blankCheckMsg = checkBlankStr(priceParamValue.value,"参数名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(priceParamValue.value,60,"参数名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}
	
	return errorString;	
}
/***************************************************************
* 
* 价格计划管理模块：定价策略基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiPriceItem(){
	var errorString = "";
	//--定价策略名称(不能为空，长度不能超60,,name)--%>
	var name = document.getElementById("name");
	var blankCheckMsg = checkBlankStr(name.value,"策略名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(name.value,60,"策略名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}
	//--生效时间(不能为空,effDate)--%>
	var effDate = document.getElementById("effDate");
	errorString = errorString + checkBlankStr(effDate.value,"生效时间");
	//--失效时间(能为空,expDateString)--%>
	var expDateString = document.getElementById("expDateString");
	//--生效时间不能大于失效时间--%>
	errorString = errorString + compareDateStr(effDate.value,expDateString.value,"生效时间","失效时间");
	//--金额(只能为数字，number(8,2),rate)--%>
	var rate = document.getElementById("rate");
	errorString = errorString + checkFloat(rate.value,6,2,"金额");
	//--数量(只能为数字，数字位数最大6位,pricingUnitCount)--%>
	var pricingUnitCount = document.getElementById("pricingUnitCount");
	errorString = errorString + checkInt(pricingUnitCount.value,6,"数量");
	//--定价策略描述(长度不能超255,remarks)--%>
	var remarks = document.getElementById("remarks");
	errorString = errorString + checkCNStrLen(remarks.value,255,"策略描述");
	
	return errorString;
}

/***************************************************************
* 
* 价格计划管理模块：价格计划基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiPricePlan(){
	var errorString = "";			
	//--名称(不能为空，长度不能超60,name)--%>
	var name = document.getElementById("name");
	var blankCheckMsg = checkBlankStr(name.value,"价格名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(name.value,60,"价格名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}
	//--类型(不能为请选择,type)--%>
	var type = document.getElementById("type");
	errorString = errorString + checkSelectOption(type.value,"X","价格类型");		
	//--层级(不能为请选择,priceLevel)--%>
	var priceLevel = document.getElementById("priceLevel");				
	errorString = errorString + checkSelectOption(priceLevel.value,"X","价格层级");		
	//--优惠类型(不能为请选择,disctType)--%>
	var disctType = document.getElementById("disctType");				
	errorString = errorString + checkSelectOption(disctType.value,"X","优惠类型");		
	//--子优惠类型(不能为请选择,subDisctType)--%>
	var subDisctType = document.getElementById("subDisctType");				
	errorString = errorString + checkSelectOption(subDisctType.value,"X","子优惠类型");		
	//--排他性(不能为请选择,disctType)--%>
	var rejectFlag = document.getElementById("rejectFlag");				
	errorString = errorString + checkSelectOption(rejectFlag.value,"X","排他性");		
	//--生效时间(不能为空,effDate)--%>
	var effDate = document.getElementById("effDate");
	errorString = errorString + checkBlankStr(effDate.value,"生效时间");
	//--失效时间(能为空,expDateString)--%>
	var expDateString = document.getElementById("expDateString");
	//--生效时间不能大于失效时间--%>
	errorString = errorString + compareDateStr(effDate.value,expDateString.value,"生效时间","失效时间");	
	//--备注(长度不能超255,remarks)--%>
	var remarks = document.getElementById("remarks");	
	errorString = errorString + checkCNStrLen(remarks.value,255,"备注");
	
	return errorString;
}

/***************************************************************
* 
* 价格计划管理模块：价格计划策略构成基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiPricePlanList(){
	var errorString = "";
	var priority = document.getElementById("priority");
	var blankCheckMsg = checkBlankStr(priority.value,"优先级");
	if(blankCheckMsg == ""){
		errorString = errorString + checkInt(priority.value,6,"优先级");
	}else{
		errorString = errorString + blankCheckMsg;
	}
	
	return errorString;
}
/***************************************************************
* 
* 价格计划管理模块：价格计划套餐基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiPricePlanBrand(){
	var errorString = "";
	//--套餐名称(不能为空，长度不能超60,,name)--%>
	var name = document.getElementById("name");
	var blankCheckMsg = checkBlankStr(name.value,"套餐名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(name.value,60,"套餐名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}
	//--多选标志(不能为请选择,multiFlag)--%>
	var multiFlag = document.getElementById("multiFlag");
	errorString = errorString + checkSelectOption(multiFlag.value,"X","多选标志");		
	//--套餐描述(长度不能超255,remarks);--%>
	var remarks = document.getElementById("remarks");
	errorString = errorString + checkCNStrLen(remarks.value,255,"备注");
	
	return errorString;
}

/***************************************************************
* 
* 产品开发管理模块：产品基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiProduct(){
	var errorString = "";
	//--产品名称(不能为空，长度不能超60,name)--%>
	var name = document.getElementById("name");
	var blankCheckMsg = checkBlankStr(name.value,"产品名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(name.value,60,"产品名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}					
	//--主体标识（不能为请选择,mainFlag）--%>
	var mainFlag = document.getElementById("mainFlag");				
	errorString = errorString + checkSelectOption(mainFlag.value,"X","主体标识");		
	//--捆绑标识（不能为请选择,bundleFlag）--%>
	var bundleFlag = document.getElementById("bundleFlag");				
	errorString = errorString + checkSelectOption(bundleFlag.value,"X","捆绑标识");		
	//--生效时间(不能为空,effDateString)--%>
	var effDateString = document.getElementById("effDateString");
	errorString = errorString + checkBlankStr(effDateString.value,"生效时间");
	//--失效时间(能为空,expDateString)--%>
	var expDateString = document.getElementById("expDateString");
	//--生效时间不能大于失效时间--%>
	errorString = errorString + compareDateStr(effDateString.value,expDateString.value,"生效时间","失效时间");	
	//--标准编码(能为空,长度不能超过60，standardCode)--%>
	var standardCode = document.getElementById("standardCode");
	errorString = errorString + checkCNStrLen(standardCode.value,60,"标准编码");
	//--必选数量下限(只能为数字，数字位数最大为6,amountLow)--%>
	var amountLow = document.getElementById("amountLow");
	var lowMsg = checkInt(amountLow.value,6,"数量下限");
	errorString = errorString + lowMsg;
	//--必选数量上限(只能为数字，数字位数最大为6,amountHigh)--%>
	var amountHigh = document.getElementById("amountHigh");
	var highMsg = checkInt(amountHigh.value,6,"数量上限");
	errorString = errorString + highMsg;
	if(isEmpty(lowMsg) && isEmpty(highMsg)){
		errorString = errorString + compareInt(amountLow.value,amountHigh.value,"数量下限","数量上限");
	}
			
	return errorString;		
}

/***************************************************************
* 
* 产品开发管理模块：产品构成基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiProdItem(){
	var errorString = "";					
	//可选标志(不能为请选择,selFlag)
	var selFlag = document.getElementById("selFlag");				
	errorString = errorString + checkSelectOption(selFlag.value,"X","可选标志");		
	//同步竣工(不能为请选择,synCompFlag)
	var synCompFlag = document.getElementById("synCompFlag");							
	errorString = errorString + checkSelectOption(synCompFlag.value,"X","同步竣工标志");		
	//必选数量下限(只能为数字，数字位数最大为6,amountLow)	
	var amountLow = document.getElementById("amountLow");
	var lowMsg = checkInt(amountLow.value,6,"数量下限");
	errorString = errorString + lowMsg;
	//--必选数量上限(只能为数字，数字位数最大为6,amountHigh)--%>
	var amountHigh = document.getElementById("amountHigh");
	var highMsg = checkInt(amountHigh.value,6,"数量上限");
	errorString = errorString + highMsg;
	if(isEmpty(lowMsg) && isEmpty(highMsg)){
		errorString = errorString + compareInt(amountLow.value,amountHigh.value,"数量下限","数量上限");
	}
				
	return errorString;	
}

/***************************************************************
* 
* 产品开发管理模块：产品价格计划基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiProdPricePlan(){
	var errorString = "";
	//--必选标识（不能为请选择,mainFlag）--%>
	var mandFlag = document.getElementById("mandFlag");	
	errorString = errorString + checkSelectOption(mandFlag.value,"X","必选标识");		
	//--生效时间(不能为空,effDate)--%>
	var effDate = document.getElementById("effDate");
	errorString = errorString + checkBlankStr(effDate.value,"生效时间");	
		
	return errorString;
}

/***************************************************************
* 
* 产品规格管理模块：产品规格基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiProdSpec(){
	var errorString = "";	
	//--规格名称（不能为空，长度不能超60,name)--%>			
	var name = document.getElementById("name");
	var blankCheckMsg = checkBlankStr(name.value,"规格名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(name.value,60,"规格名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}
	//--组合标识(不能为请选择,composeFlag)--%>
	var composeFlag = document.getElementById("composeFlag");				
	errorString = errorString + checkSelectOption(composeFlag.value,"X","组合标识");		
	//--主体标识(不能为请选择,mainFlag)--%>
	var mainFlag = document.getElementById("mainFlag");				
	errorString = errorString + checkSelectOption(mainFlag.value,"X","主体标识");		
	//--规格成本(只能为数字，数字位数最大8位,cost)--%>
	var cost = document.getElementById("cost");
	errorString = errorString + checkInt(cost.value,8,"规格成本");
	//--标准编码(长度不能超过30，standardCode)--%>
	var standardCode = document.getElementById("standardCode");				
	errorString = errorString + checkCNStrLen(standardCode.value,30,"标准编码");
	
	return errorString;	
}

/***************************************************************
* 
* 销售资源管理模块：销售资源基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiResourceSpec(){
	var errorString = "";
	//--销售资源名称（不能为空，长度不能超60,name)--%>
	var name = document.getElementById("name");
	var blankCheckMsg = checkBlankStr(name.value,"资源名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(name.value,60,"资源名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}
	//--销售资源类别(不能为请选择,resourceSpecTypeId)--%>
	var resourceSpecTypeId = document.getElementById("resourceSpecTypeId");				
	errorString = errorString + checkSelectOption(resourceSpecTypeId.value,"X","资源类别");		
		
	return errorString;
}

/***************************************************************
* 
* 服务动作管理模块：服务动作基本信息维护，页面校验
* 参数：无
* 返回：错误信息字符串
*****************************************************************/
function valiServAct(){
	var errorString = "";			
	//--服务动作名称(不能为空，长度不能超60,name)--%>
	var name = document.getElementById("name");
	var blankCheckMsg = checkBlankStr(name.value,"动作名称");
	if(blankCheckMsg == ""){
		var lengthCheckMsg = checkCNStrLen(name.value,60,"动作名称");
		errorString = errorString + lengthCheckMsg;
	}else{
		errorString = errorString + blankCheckMsg;
	}
	//--服务类型(不能为请选择N,type)--%>
	var type = document.getElementById("type");	
	errorString = errorString + checkSelectOption(type.value,"X","服务类型");			
	//--动作类型(不能为请选择N,actType)--%>
	var actType = document.getElementById("actType");				
	errorString = errorString + checkSelectOption(actType.value,"X","动作类型");			
	//--欠费办理(不能为请选择X,debtFlag)--%>
	var debtFlag = document.getElementById("debtFlag");				
	errorString = errorString + checkSelectOption(debtFlag.value,"X","欠费办理标识");			
	//--批量办理(不能为请选择X,batchFlag)--%>
	var batchFlag = document.getElementById("batchFlag");				
	errorString = errorString + checkSelectOption(batchFlag.value,"X","批量办理标识");			
	//--组合标识(不能为请选择X,composeFlag)--%>
	var composeFlag = document.getElementById("composeFlag");				
	errorString = errorString + checkSelectOption(composeFlag.value,"X","组合标识");			
	//--标准编码(长度不能超过30，standardCode)--%>
	var standardCode = document.getElementById("standardCode");
	errorString = errorString + checkCNStrLen(standardCode.value,30,"标准编码");
	
	return errorString;
}
function isEmpty(name){
	 if (name == ""){
	   return true;
	 }else{
	    return false;
	 }
}
function checkCN(value,length){
  if(value.length > length){
       return false;
  }else{
       return true;
  }
}
/*产品管理子系统子模块独用页面校验方法----END*/