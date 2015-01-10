
var beforeClick=null;
function chickBGColor(src){
   //alert(beforeClick);
   if(beforeClick!=null){
      beforeClick.style.backgroundColor = "#E5E8EC";
   }
   src.style.backgroundColor = "#ffff99";
   beforeClick=src;
	
}
//??????????¨®??????????¡Â????????????????????????????????¡À????????????¡À????????????????????
 var beforeClick4Check=null;
function chickBGColor(src,index){
   //alert(beforeClick);
   if(beforeClick4Check!=null){
      beforeClick4Check.style.backgroundColor = "#E5E8EC";
   }
   src.style.backgroundColor = "#ffff99";
   beforeClick4Check=src;   
   var ob=document.getElementById(index) ;
   ob.checked=true;
}
function ChangeColwhenClick(src) {
	var currRowCol = src.style.backgroundColor;
	src.style.backgroundColor = (currRowCol == "#ffff99") ? "#E5E8EC" : "#ffff99";
}

function ChangeRowColorwhenClick(src,chbObj,index) {
	var currRowCol = src.style.backgroundColor;//alert('1');
	var ob=document.all[chbObj];//alert(ob);
	if(ob==undefined){
	   return;
	}
		if(src.bgColor =="#ff8247")
		{
		if(ob.checked){
	      src.style.backgroundColor="#ffff99";
	      return;
	  }
	  else{//alert('1');
	      src.style.backgroundColor ="#ff8247";
	      return;
	   }
}
	
	if(ob.length==undefined){//alert('2');
	   if(ob.checked){
	      src.style.backgroundColor="#ffff99";
	      return;
	  }
	  else{//alert('1');
	      src.style.backgroundColor ="#E5E8EC";
	      return;
	   }
	}
	else{ 
	    if(ob[index].checked){
	       src.style.backgroundColor="#ffff99";
	       return;
	    }
	    else{ //('2');
	       src.style.backgroundColor ="#E5E8EC";
	       return;
	    }
	}
	
	//src.style.backgroundColor = (currRowCol == "#ffff99") ? "#E5E8EC" : "#ffff99";
	//if(ob.checked||ob[index].checked)
	   // src.style.backgroundColor ="#ffff99";
	///else
	   // src.style.backgroundColor ="#E5E8EC";
}
//??????¡Â??????????????¡À????????¡À????????????????????????¡ì??¡ì??¡À????????????¡À??????????????
function ChangeRadioRowColor(src,index) {

	var ob=document.all['tr'+index];
	if(ob==undefined){
	   return;
	}
	 if(src.checked){
	        ob.style.backgroundColor="#E5E8EC";
		      return;
	  }else {	
	        ob.style.backgroundColor ="#ffff99";
		      return;
	  }  
}
function ChangeTableColorwhenFullSelect(tableID,topCheckbox, checkboxAryName) {
	var checkboxAry = document.all(checkboxAryName);
	if (checkboxAry == null) {
		return;
	}
	if (topCheckbox.checked) {
		if (checkboxAry.constructor != Array) {
			checkboxAry.checked = true;
		}
		if (checkboxAry.length != null) {
			for (var len = 0; len < checkboxAry.length; len++) {
				if (checkboxAry[len].checked) {
					checkboxAry[len].checked = true;
				} else {
					checkboxAry[len].checked = true;
				}
			}
		}
	} else {
		if (checkboxAry.constructor != Array) {
			checkboxAry.checked = false;
		}
		if (checkboxAry.length != null) {
			for (var len = 0; len < checkboxAry.length; len++) {
				if (checkboxAry[len].checked) {
					checkboxAry[len].checked = false;
				}
			}
		}
	}
	ChangeTableColorWhenClick(tableID,checkboxAryName);
}
function ChangeTableColorWhenClick(tableID,checkboxAryName){
	
	var rows = document.getElementById(tableID).rows;
	var Ids = document.getElementsByName(checkboxAryName);
	for(var l=1;l<rows.length;l++){
		if(Ids[l-1].checked){
			rows[l].style.backgroundColor = "#ffff99";
		}else{
			//rows[l].style.backgroundColor = "#E5E8EC";
			rows[l].style.backgroundColor=rows[l].bgColor;
		}
	}
	return;
}
//??¨´????topCheckbox????¡¤????????????????????¨¨????????????????checkboxAryName??????¨´????chexbox??????????????????????¨°????????????????
function selectAll(topCheckbox, checkboxAryName) {
	var checkboxAry = document.all(checkboxAryName);
	if (checkboxAry == null) {
		return;
	}
	if (topCheckbox.checked) {
		if (checkboxAry.constructor != Array) {
			checkboxAry.checked = true;
		}
		if (checkboxAry.length != null) {
			for (var len = 0; len < checkboxAry.length; len++) {
				if (checkboxAry[len].checked) {
					checkboxAry[len].checked = true;
				} else {
					checkboxAry[len].checked = true;
				}
			}
		}
	} else {
		if (checkboxAry.constructor != Array) {
			checkboxAry.checked = false;
		}
		if (checkboxAry.length != null) {
			for (var len = 0; len < checkboxAry.length; len++) {
				if (checkboxAry[len].checked) {
					checkboxAry[len].checked = false;
				}
			}
		}
	}
}
		
		//??¨´????isChecked??????????????¨¨????????????????name????????????????¡¤??????????
function show(isChecked, name) {
	if (isChecked) {
		document.all(name).disabled = false;
	} else {
		document.all(name).disabled = true;
	}
}
	
	
	//??¨²????Excel
function SaveExcel(tableName) {
	// Start Excel and get Application object.
	var oXL = new ActiveXObject("Excel.Application"); 
	// Get a new workbook.
	var oWB = oXL.Workbooks.Add();
	var oSheet = oWB.ActiveSheet;
	var table = document.all(tableName);
	var hang = table.rows.length;
	var lie = table.rows(0).cells.length; 
	
	// Add table headers going cell by cell.
	for (i = 0; i < hang; i++) {
		for (j = 0; j < lie; j++) {
			oSheet.Cells(i + 1, j + 1).NumberFormatLocal="@";
			oSheet.Cells(i + 1, j + 1).Value = table.rows(i).cells(j).innerText;
		}
	}
	oXL.Visible = true;
	oXL.UserControl = true;
}

	//??¨²????Excel
function WoSaveExcel(tableName) {
	// Start Excel and get Application object.
	var oXL = new ActiveXObject("Excel.Application"); 
	// Get a new workbook.
	var oWB = oXL.Workbooks.Add();
	var oSheet = oWB.ActiveSheet;
	var table = document.all(tableName);
	var hang = table.rows.length;
	var lie = table.rows(2).cells.length; 
	oSheet.Cells(2, 1).Value = table.rows(0).cells(0).innerText;
	oSheet.Cells(1, 2).Value = table.rows(0).cells(1).innerText;
	oSheet.Cells(1, 8).Value = table.rows(0).cells(2).innerText;
	oSheet.Cells(2, 2).Value = table.rows(1).cells(0).innerText;
	oSheet.Cells(2, 3).Value = table.rows(1).cells(1).innerText;
	oSheet.Cells(2, 4).Value = table.rows(1).cells(2).innerText;
	oSheet.Cells(2, 5).Value = table.rows(1).cells(3).innerText;
	oSheet.Cells(2, 6).Value = table.rows(1).cells(4).innerText;
	oSheet.Cells(2, 7).Value = table.rows(1).cells(5).innerText;
	oSheet.Cells(2, 8).Value = table.rows(1).cells(6).innerText;
	oSheet.Cells(2, 9).Value = table.rows(1).cells(7).innerText;
	oSheet.Cells(2, 10).Value = table.rows(1).cells(8).innerText;
	oSheet.Cells(2, 11).Value = table.rows(1).cells(9).innerText;
	oSheet.Cells(2, 12).Value = table.rows(1).cells(10).innerText;
	oSheet.Cells(2, 13).Value = table.rows(1).cells(11).innerText;
	oSheet.Cells(2, 14).Value = table.rows(1).cells(12).innerText;
	// Add table headers going cell by cell.
	for (i = 2; i < hang; i++) {
		for (j = 0; j < lie; j++) {
			oSheet.Cells(i + 1, j + 1).NumberFormatLocal="@";
			oSheet.Cells(i + 1, j + 1).Value = table.rows(i).cells(j).innerText;
		}
	}
	oXL.Visible = true;
	oXL.UserControl = true;
}
function changeCol(src, clrout) {
	if(src.bgColor =="#ff8247")
		{
		return;
		}
	if (src.style.backgroundColor == "#ffff99") {
	} else {
		src.style.backgroundColor = clrout;
	}
}
function changeCol2(src,clrout){
	if (src.style.backgroundColor == "#ffff99") {
	} else {
		src.style.backgroundColor = src.bgColor;
	}
}
function currstyle(p) {
	var oldColor = "E5E8EC";
	getTopNode(p).style.backgroundColor = p.checked ? "#ffff99" : oldColor;
}
function getTopNode(pNode) {
	if (pNode.tagName == "TR") {
		return pNode;
	} else {
		return getTopNode(pNode.parentNode);
	}
}
function openAdvanceQueryConfig() {
	name = "AdvQueryCfgAction.do?method=initAdvQuery";
	mote = "dialogHeight: 450px; dialogWidth: 500px; center: yes; help: no;status:no;title:no;scroll:no";
	return window.showModalDialog(name, window, mote);
}
/**
* add by wyun on 20070607
*/
function Trim(str) {
	return RTrim(LTrim(str));
}
function LTrim(str) {
	var whitespace = new String(" \t\n\r");
	var s = new String(str);
	if (whitespace.indexOf(s.charAt(0)) != -1) {
		var j = 0, i = s.length;
		while (j < i && whitespace.indexOf(s.charAt(j)) != -1) {
			j++;
		}
		s = s.substring(j, i);
	}
	return s;
}
function RTrim(str) {
	var whitespace = new String(" \t\n\r");
	var s = new String(str);
	if (whitespace.indexOf(s.charAt(s.length - 1)) != -1) {
		var i = s.length - 1;
		while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1) {
			i--;
		}
		s = s.substring(0, i + 1);
	}
	return s;
}
function chickChangeCol(src, chbId) {
//	alert("chickChangeCol");
	var currRowCol = src.style.backgroundColor;
	src.style.backgroundColor = (currRowCol == "#ffff99") ? "#E5E8EC" : "#ffff99";
	//if (currRowCol == "#ffff99") {
		//document.getElementById(chbId).checked = false;
	//} else {
		//document.getElementById(chbId).checked = true;
	//}
}


/*  return >0 ??????????????¨°????;0???????????????? ;-1??????¡§??????????¨¦;*/
function limitCheckedOne(chboxName){

	var checkboxAry = document.all(chboxName);	
	var index = 0;	
	//????checkbox
	if (checkboxAry == null) {
		alert("??????????????¡è????????????????");
		index = -1;
		return -1;
	} else if(checkboxAry.length = undefined ){
		//????????????????checkbox
		if(checkboxAry.checked){
			soNbr = nSoAry.value;
			return 0;
		}else{
			alert("????????????????????????????????");
		    return -1;
		}
	} else{
	//??????¨¤????checkbox
		var num = 0;
		for(var i = 0; i <checkboxAry.length; i++){
			if(checkboxAry[i].checked){
				num++;
				index = i;
			}
		}
		if(num == 0){
	      	alert("????????????????????????????????");
	        return -1;
	    }else if(num > 1){
	       	alert("??????????????????????????????????¨°????????");
	        return -1;
	    }
	    return index;
	}
}


/*  ??¨´????checkbox????????????????checkbox??¨°checkbox????¡Á¨¦????????????????????????????????¡Á¨¦????????¡¤??????*/
function getChbArray(chboxName){

	var checkboxAry = document.all(chboxName);	
	//alert(checkboxAry[0].value);
	//alert(checkboxAry.length );

	chbArray  = new Array();
	var index = 0;	
	//????checkbox
	if (checkboxAry == null) {
		 chbArray = null;
	} else if(checkboxAry.value != undefined ){
		//????????????????checkbox
		if(checkboxAry.checked){
			chbArray[0]=checkboxAry.value;
		}else{
		    chbArray = null;
		}
	} else{
	//??????¨¤????checkbox
		var index = 0;
		for(var i = 0; i <checkboxAry.length; i++){
			if(checkboxAry[i].checked){
				chbArray[index] = checkboxAry[i].value;
				index++;
			}
		}
		if(index == 0){
	        chbArray = null;
	    }
	    
	}
	return  chbArray;
}
//????????¡À??????????????checkbox??¨´??????????????????????¨°????????????????¡Á¨¦????????
function getHiddenArray(chboxName,hiddenAryName){
 
	var checkboxAry = document.getElementsByName(chboxName);//document.all(chboxName);	
	
	var hiddenAry = document.getElementsByName(hiddenAryName);//document.all(hiddenAryName);
	
	//alert(checkboxAry[0].value);
	//alert(checkboxAry.length );

	hiddenArray  = new Array();
	var index = 0;	
	//????checkbox
	
	if (checkboxAry == null) {
		 hiddenArray = null;
	} else if(checkboxAry.value != undefined ){
		//????????????????checkbox
		if(checkboxAry.checked){
		
			hiddenArray[0]=hiddenAry.value;
		}else{
		    hiddenArray = null;
		}
	} else{
	//??????¨¤????checkbox
	
		var index = 0;		
		for(var i = 0; i <checkboxAry.length; i++){
			if(checkboxAry[i].checked){			
				hiddenArray[index] = hiddenAry[i].value;
				index++;
			}
		}
		if(index == 0){
	        hiddenArray = null;
	    }
	    
	}
	return  hiddenArray;
}

/*  ??¨´????checkbox????????????????checkbox??¨°checkbox????¡Á¨¦??????????????¨°????????????????????¡Á¨¦????????¡¤??????*/
function getChbCheckedIndexs(chboxName){

	var checkboxAry = document.all(chboxName);	
	//alert(checkboxAry[0].value);
	//alert(checkboxAry.length );

	chbArray  = new Array();
	var index = 0;	
	//????checkbox
	if (checkboxAry == null) {
		 chbArray = null;
	} else if(checkboxAry.value != undefined ){
		//????????????????checkbox
		if(checkboxAry.checked){
			chbArray[0]=0;
		}else{
		    chbArray = null;
		}
	} else{
	//??????¨¤????checkbox
		var index = 0;
		for(var i = 0; i <checkboxAry.length; i++){
			if(checkboxAry[i].checked){
				chbArray[index] = i;
				index++;
			}
		}
		if(index == 0){
	        chbArray = null;
	    }
	    
	}
	return  chbArray;
}
/**/
function rangeControl(name){
   var flag =false;
  if(name >='a' && name <='z' ){
     flag = true;
  }
  if(name >='A' && name <='Z' ){
     flag = true;
  }
  if(name >='0' && name <='9' ){
     flag = true;
  }
  return flag;
}
function popupWindow(url,height,width,object,objectKey,objectValue){
	var newUrl=url+'&objectTypeSelect='+document.all(object).value+'&objectKey='+objectKey+'&objectValue='+objectValue;
	mote = "dialogHeight: "+height+"px; dialogWidth: "+width+"px; center: yes;  help: no;status:no;title:no;scroll:no";   
	var result = window.showModalDialog(newUrl,window,mote);
	if(result==undefined)
        return false;
    else 
        return true;
}

function initOrgDeptTree(url,height,width,type,objectId,objectValue){
	var ownerType = document.all(type).value;
	var newUrl=url+'&ownerType='+ownerType+'&objectId='+objectId+'&objectValue='+objectValue;
	mote = "dialogHeight: "+height+"px; dialogWidth: "+width+"px; center: yes;  help: no;status:no;title:no;scroll:no";   
	var result = window.showModalDialog(newUrl,window,mote);
	if(result==undefined){
        return false;
   	}else {
        return true;
  	}
}
/*
  added by lijiancheng 2008-12-23
*/
String.prototype.getBytes = function(){   
     var cArr = this.match(/[^\x00-\xff]/ig);   
     return this.length + (cArr == null ? 0 : cArr.length);   
}    
function outOfRange(value,length){
    if(value.getBytes()>length)return true;
    else return false;
}
function nullAble(str){
    if(str==''||str==null||str==undefined)return false;
    else return true;
}

function isNum(str){
   var re=/^[-]?\d*\.?\d*$/;
   if(str.match(re))return true;
   else return false;
}

function setTitle(td,src){
	var obj = document.getElementById(src);
	var value = obj.value;
	td.title = value;
}

/**
*select checkbox by click text
* add by yanxin 2009-6-22
*/
function selChbByTxt(chbName,targetName,targetName2){
	if(document.all(chbName).disabled){
		return false;
	}
	document.all(chbName).checked=!document.all(chbName).checked;
	var isChecked = document.all(chbName).checked;
	if(targetName == null || targetName ==undefined){
		return;
	}
	if (isChecked) {
		document.all(targetName).disabled = false;
		if(targetName2!=undefined || targetName2!=null){
			document.all(targetName2).disabled = false;
		}
		
	} else {
		document.all(targetName).disabled = true;
		if(targetName2!=undefined || targetName2!=null){
			document.all(targetName2).disabled = true;
		}
		
	}
	return true;
}

function autoChangeHeight(tableId){

	//var width = document.all[tableId].offsetWidth;     
	var height = document.all[tableId].offsetHeight;
		
	//width = eval(width + 50); 
	height = eval(height + 50);
	
	window.window.dialogHeight=(height+'px'); 

}

function resetDialogHeight(){
  if(window.dialogArguments == null){    
  	return; 
  }  
  var ua = navigator.userAgent;  
  var height = document.body.offsetHeight;  
  if(ua.lastIndexOf("MSIE 6.0") != -1){
  		var height = document.body.offsetHeight;      
  		window.dialogHeight=(height+70)+"px"; 
  }
}

function autoAdjustDialogHeight(){
	if (window.dialogArguments == null) {
		return;
	}
	var ua = navigator.userAgent;
	var height = document.body.offsetHeight;
	if (ua.lastIndexOf("MSIE 6.0") != -1) {
		if (ua.lastIndexOf("Windows NT 6.1") != -1) {
			var height = document.body.offsetHeight;
			window.dialogHeight = (height) + "px";
		}else if (ua.lastIndexOf("Windows NT 5.1") != -1) {
			var height = document.body.offsetHeight;
			window.dialogHeight = (height + 66) + "px";
		}else{
			var height = document.body.offsetHeight;
			window.dialogHeight = (height + 49) + "px";
		}
	}
}

//trim space
String.prototype.Trim = function() { 
	var m = this.match(/^\s*(\S+(\s+\S+)*)\s*$/); 
	return (m == null) ? "" : m[1]; 
}

//check start with 13,15,18 mobile
String.prototype.isMobile = function() { 
	return (/^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(this.Trim())); 
}

//check 5 or 7 byte tel
String.prototype.isTel = function(){
	return (/^(\d{5}|\d{7})?$/.test(this.Trim()));
}
