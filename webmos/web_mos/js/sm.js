function smKeyPress(funcId)//support return key.
{
  if(event.keyCode == "13"){
     event.returnValue = false;
	 if(funcId==1)
     add();
	 else if(funcId==2)
		 mod();
	 else if(funcId==3)
		 srch();
	 parent.window.document.body.focus();
  }
}
function selAll(form1){//selAllChk call the function to set all checkboxes true or false.
  var array = form1.elements;
  for(var i=0; i<array.length; i++){
    var elem = array[i];
    if(elem.type=='checkbox'){
		if(form1.selAllChk.checked==true)  elem.checked=true;
		if(form1.selAllChk.checked==false)  elem.checked=false;
    }
  }
}
function about(){
var location="version.html";
window.showModalDialog(location,"newwin","dialogHeight: 200px; dialogWidth: 230px; center: yes; help:no;status:no;");
}
function checkAllChk(frm){//check if all checkboxes are checked.if not set selAllChk flase.
  var all=true;
  var elements = frm.elements;

  for(var i=0; i<elements.length; i++){
    if(elements[i].type!="checkbox")continue;
    if(!elements[i].checked&&elements[i].name!="selAllChk"){
      all=false;
	  break;
    }
  }
  if(all==true) frm.selAllChk.checked=true;
  else
	  frm.selAllChk.checked=false;
}
function cancelRep(repeat,addForm){//make repeated items unchecked.
  elements = addForm.elements;
  for(var i=0; i<repeat.length; i++){
	  for(var j=0; j<elements.length; j++){
		if(elements[j].type!="checkbox")continue;
		if(elements[j].checked&&elements[j].name!="selAllChk"){
//			alert(elements[j].value);
			if(elements[j].value==repeat[i]){
				elements[j].checked=false;
			}
		}
	  }
  }

}
function getSeledVals(elements,fld){
	var ret="";
	var num=0;
    for(var i=0; i<elements.length; i++){
		if(elements[i].name==fld){
			ret+=elements[i].value+",";
			num++;
		}
	}
	if(ret.length>0){
		if(num>1)
			ret="("+ret.substring(0,ret.lastIndexOf(","))+")";
		else
			ret=ret.substring(0,ret.lastIndexOf(","));
	}
	return ret;

}
function showUser(id){
//window.open(parent.parent.addFrame2.location="sMSysUserAction.do?method=gotoAdd&staffId="+id);
//window.showModalDialog("sMSysUserAction.do?method=findSysUsers&url=staffListByDept&sts=A&partyRoleTypeId=7&partyRoleId="+id,"newwin","dialogHeight: 470px; dialogWidth: 450px; center: yes; help:no;status:no;");
window.showModalDialog("sMSysUserAction.do?method=findSysUsersBystaffId&staffId="+id,"newwin","dialogHeight: 470px; dialogWidth: 450px; center: yes; help:no;status:no;");
}
function checkSel(form,elType,warnMsg){
  var elements = form.elements;
  var num = 0;
  var id = null;
  for(var i=0; i<elements.length; i++){
    if(elements[i].type!=elType)continue;
      if(elements[i].checked){
        id = elements[i].value;
        num++;
      }
   }
   if(num==0){
     alert(warnMsg);
     return false;
   }else
	   return true;
}
/**
 * 验证组织机构名字信息
 */
function checkOrgDeptName(name){
     var msgStr="";
     if(name==null||name==""){
        msgStr="机构名称不能为空 \n";
      }
      else{
      if(name.length>22){
       msgStr="机构名称应该小于22个字符 \n";
      }
     var tr=/[^a-zA-Z0-9#\u4e00-\u9fa5]/
      if(tr.test(name)){
           msgStr="机构名称只能包含汉字,数字,字符和# \n";
       }
       }
      return msgStr;
}
/**
 * 验证地址信息
 */
function checkAddr(name,str){
    var msgStr="";
    var tr=/[^a-zA-Z0-9#\u4e00-\u9fa5]/
    if(tr.test(name)){
           msgStr=str+"地址只能包含汉字,数字,字符和# \n";
       }
      return msgStr;
}
/**
 * 验证邮箱地址
 */
function isValidEmail(sText){
    var msgStr="";
   var reEmail=/^(?:\w+\.?)*\w+@(?:\w+\.?)*\w+$/;
    if(!reEmail.test(sText)){
    msgStr="邮箱地址格式不对 \n";
    }
    return msgStr;
}
/**
 * 验证员工名字信息
 */
function checkStaffName(name){
     var msgStr="";
     if(name==null||name==""){
        msgStr="姓名不能为空 \n";
      }
      else{var tr=/[^a-zA-Z\u4e00-\u9fa5]/
      if(tr.test(name))
      { msgStr="姓名只能包含汉字,字符 \n";
      }}
      return msgStr;
}
function checkNativePlace(name){
      var msgStr="";
      var tr=/[^\u4e00-\u9fa5]/
      if(tr.test(name)){
        msgStr="籍贯只能包含汉字！\n"
      }
      return msgStr;
}
function checkSchoolName(name){
    var msgStr="";
    var tr=/[^a-zA-Z0-9\u4e00-\u9fa5]/
      if(tr.test(name))
      { msgStr="毕业院校只能包含汉字,字符和数字 \n";
      }
      return msgStr;
}
function checkCertCode(code){
    var msgStr="";
    var tr=/[^a-zA-Z0-9]/;
    if(tr.test(code)){
      msgStr="证件号码只能为数字和字符 \n";
    }
    return msgStr;
}
/**
 * 验证工区名字信息
 */
function checkWorkAreaName(name){
     var msgStr="";
     if(name==null||name==""){
        msgStr="工作区名称不能为空 \n";
      }
     else{var tr=/[^a-zA-Z0-9\u4e00-\u9fa5]/
      if(tr.test(name)){
           msgStr="工作区名称只能包含汉字,数字和字符\n";
       }}
      return msgStr;
}
/***************************************************************
* 检查 邮政编码 是否有效
*******************************************/
function checkCheckPostCode(id_card){
	var msg="";

	var id_len = id_card.length;

	//==长度
	if(id_len != 6){
		msg+="邮政编码长度必须为6位 \n";
	}

	//==是否都是数字
	if(!checkTheLetter(id_card,1)) {
		msg+="邮政编码必须都为数字 \n";
	}

	return msg;
}
/***************************************************************
 * 实现对字符串是否为数字或字母或数字和字母的综合的校验
 * @param str :需要检验的字符串
 * @param flag ：检验类型标志位：1.只能是数字；2.只能是字母；3只能是数字和字母
 * @return : true : 符合条件 ； false ： 不符合条件
*****************************************************************/
function checkTheLetter(str,flag){
    if(str == "") return false;

        if (flag == 1) {
            for (var i = 0; i < str.length; i++) {
                var astr = str.charAt(i);

                if ( (astr < '0') || (astr > '9')) { //如果取出的字符不为数字，返回false
                    return false;
                }
            }
            return true;
        }
        else if (flag == 2) {
            for (var i = 0; i < str.length; i++) {
                var astr = str.charAt(i);

                if ( (astr < 'A') || (astr > 'z') || ( (astr > 'Z') && (astr < 'a'))) { //如果取出的字符不为字母，返回false
                    return false;
                }
            }
            return true;

        }
        else if (flag == 3) {
            for (var i = 0; i < str.length; i++) {
                var astr = str.charAt(i);


                if ( (astr < '0') || (astr > '9')) { //如果取出的字符即不为数字，又不为字母，返回false
                    if ( (astr < 'A') || (astr > 'z') || ( (astr > 'Z') && (astr < 'a'))) {
                        return false;
                    }
                }
            }
            return true;
        }
		else {
			return false;
		}
}

function checkNameByStr(name,str){

   var msgStr="";
     if(name==null||name==""){
        msgStr=str+"不能为空 \n";
      }
     else{var tr=/[^a-zA-Z0-9\u4e00-\u9fa5]/
      if(tr.test(name)){
           msgStr=str+"只能包含汉字,数字和字符\n";
       }}
      return msgStr;
}
function checkIdByStr(id,str){
    var msgStr="";
    if(id==null||id==""){
      msgStr=str+"不能为空！\n";
    }
     else{var tr=/[^0-9]/
      if(tr.test(id)){
           msgStr=str+"只能包含数字\n";
       }}
      return msgStr;
}
function checkName(name){
    var msgStr="";
     if(name==null||name==""){
        msgStr="不能为空 \n";
      }
     else{var tr=/[^a-zA-Z0-9\u4e00-\u9fa5]/
      if(tr.test(name)){
           msgStr="只能包含汉字,数字和字符\n";
       }}
      return msgStr;
}
function checkId(id){
    var msgStr="";
    if(id==null||id==""){
      msgStr="不能为空！\n";
    }
     else{var tr=/[^0-9]/
      if(tr.test(id)){
           msgStr="只能包含数字\n";
       }}
      return msgStr;
  }
  function findChannel(){
    var areaId=$('areaId').value
	var url = "sMWorkAreaAction.do?method=gotoChannelMain&areaId="+areaId;
	var returnValue = window.showModalDialog(url,window,"dialogHeight=450px;dialogWidth=450px;center=yes;scroll=auto;");
	var valueArr = returnValue.split("@");
	// alert(valueArr);
	// alert($('creStaffId').nodeName);
	if(returnValue!=null){
		
			$('channelId').value=valueArr[0];
			$('channelName').value = valueArr[1];
			// alert($('creStaffId').value);
		
	}
}
function chk(adds,dels){ 
   var repeat = [];

   for(var i=0; i<adds.length; i++){
    for(var j=0; j<dels.length; j++){
      if(adds[i]==dels[j]){
        repeat[repeat.length] = adds[i];
      }
    }
  }
  return repeat;
}
function nodChk(adds,repeat){
 var an=[];
 for(var i=0;i<adds.length;i++){
    var j;
    for(j=0;j<repeat.length;j++){
       if(adds[i]==repeat[j]){
       break;
       }
    }
    if(j==repeat.length){
    an[an.length]=adds[i];
    }
 }
 return an;
}
function clear(form){
  var array = form.elements;
  for(var i=0; i<array.length; i++){
    if(array[i].name=="frameSub"){
      continue;
    }
    if(array[i].type=="checkbox"){
      array[i].checked=false;
      array[i].style.backgroundColor='';
      continue;
    }
//    array[i].value="";
  }
}
 function addPty(str){
	  var addForm = frame1.document.forms[0];
	  var delForm = frame2.document.forms[0];
	  var elements = addForm.elements;
	  var adds = [];
	  var dels = [];
	  var repeat;
	  var an;
	
	  for(var i=0; i<elements.length; i++){
	    if(elements[i].type!="checkbox")continue;
	    if(elements[i].checked&&elements[i].name!="selAllChk"){
	      adds[adds.length] = elements[i].value;
	    }
	  }
	
	  if(adds.length==0){
	    alert("请选择您要设置的"+str);
	    return;
	  }
	
	  elements = delForm.elements;
	
	  for(var i=0; i<elements.length; i++){
	    if(elements[i].type!="hidden")continue;
	    //elements[i].value);
	    //if(elements[i].checked){//将来要去掉这句
	      dels[dels.length] = elements[i].value;
	    //}
	  }
	  repeat = chk(adds,dels);
	  if(repeat.length==0){
	    if(confirm("要添加这"+adds.length+"项吗？")){
	      addForm.submit();
	      clear(addForm);
	    }
	    return;
	  }
	  var s = "";
	  
	  for(var i=0; i<repeat.length; i++){
	    if(i==0){
	      s += " "+repeat[i];
	    }else{
	      s += ","+repeat[i];
	    }
	  }
	  if(repeat.length==adds.length){
	    alert("编号为"+s+" 的"+str+"已经被选择");
	  }
	  else{
	  if(confirm("编号为"+s+" 的"+str+"已经被选择,要继续添加其他几项吗？")){
	  an=nodChk(adds,repeat);
	  if(an.length>0){
	    addForm.addSysUserBars.value=an;
	    addForm.submit();
	    addForm.addSysUserBars.value="";
	      clear(addForm);
	    }  
	    
	  }}
	  cancelRep(repeat,addForm);//uncheck repeat items.   sm.js
	  checkAllChk(addForm);//check selAllChk checkbox.   sm.js
  }

function delPty(str){
  var delForm = frame2.document.forms[0];
  var elements = delForm.elements;
  var selected = [];
  for(var i=0; i<elements.length; i++){
    if(elements[i].type!="checkbox") continue;
    if(elements[i].checked&&elements[i].name!="selAllChk"){
      selected[selected.length] = elements[i].value;
    }
  }
  if(selected.length==0){
    alert("请选择您要删除的"+str);
    return;
  }
  if(confirm("您确定要移除这"+selected.length+"项吗？")){
    delForm.submit();
  }
}
function srch(){
  srchForm.frameSub.value = "Y";
  srchForm.target="listFrame";
  srchForm.submit();
  parent.srchTable.style.display="none";
}
function userAjax(url){
     var request = new Ajax.Request(
				url,
				{
					method: 'get',
					onComplete: processResponse,
					asynchronous: false
				});

}
function del(){
  var form = listFrame.privateForm;
  var array = form.elements;
  var sum = 0;
  for(var i=0; i<array.length; i++){
    var elem = array[i];
    if(elem.type=='checkbox'&& elem.checked){
      sum++
    }
  }
  if(sum==0){
    alert("您必须进行选择才能删除");
    return;
  }
  if(confirm("您确定要删除这 "+sum+" 项吗？")){
    form.submit();
  }
}


