<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.cattsoft.pub.SysConstants"%>
<%@ page import="com.cattsoft.pub.util.SysConfigData"%>
<HTML>
<HEAD>
<TITLE>失败回单</TITLE>
<base target="_self" />	
<link href="../css/tabdisplaystyle.css" rel="stylesheet" type="text/css">
<style type="text/css">
#box{
	display: none;
	border:1px solid #96A1AB outset;
	border-radius:15px;
	background-color:#F6F6F6;
	text-align: left;
	margin-top:4px;
	margin-left:95px;
	padding-left:1px;
	padding-right:1px;
	padding-top:4px;
	line-height: 100%;
	text-indent: 0em;
	position: absolute;
}

ul{
	margin:0;
	padding:0;
	list-style:none;
}
/*.dropDownList div.dropdown select{display:none;}*/
	
	.dropDownList span{
		font-size:12px;
		display:block;
		width:156px;
		height:19px;
	    background:#FFF;
		float:left;
		line-height:19px;
		overflow:hidden;
		cursor:pointer;
	}

 	#dropDownList1{
 		position:relative;width:100%;	
 	}

	.dropDownList ul{
		margin:0;
		padding:0;
		background:#fff;
		width:226px;
		display:none;
		border-left:solid 1px #5794BF;
		border-bottom:solid 1px #5794BF;
		border-right:solid 1px #5794BF;
	}
	.dropDownList ul li{
		height:20px;
		width:100%;
		padding:2px;
		cursor:pointer;
	}
	.dropDownList ul li.over{
		background:#ccc;
	}
	.dropDownList ul.show{
		display:block;
	}
</style>

<!-- end by liangyx -->

<!-- add by liangyongxing 用div+css+js实现模拟select下拉列表的功能 -->

		<script type="text/javascript" language="javascript"
			src="../js/sorttable.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/changecol.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/calendarTime.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/public.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/jquery-1.2.6.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/prototype.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></HEAD>

<BODY>
<% String soCat = (String)request.getAttribute("soCat"); %>
<html:form styleId="failForm" action="/tm/WoHandleAction.do?method=woReturn&wonbrString" method="post" >
<table width="100%" align="left">
 <tr>
  <td>

       <fieldset>
              
      <legend>失败回单
      		<input type="hidden" value="<bean:write name='WoHandleForm' property='woNbrAryStr'/>" name="wonbrs"/>
      		<input type="hidden" value="<bean:write name="WoHandleForm" property="soNbr" />" name="returnSoNbrs"/>
      		<input type="hidden" value="<bean:write name="WoHandleForm" property="soSeq" />" name="returnSoSeqs"/>
       </legend> 
			  <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td  bgcolor="9FAAB5"> 
	        <table width="100%" cellspacing="1" >
			
                     <tr bgcolor="#E5E8EC">
                       <td width="35%" height="20" align="right">定&nbsp;单&nbsp;号&nbsp;码：</td>
                       <td width="65%" colspan="2" bgcolor="E5E8EC" title="<bean:write name="WoHandleForm" property="soNbr" />">
					   <div STYLE="width:200;overflow:hidden;text-overflow:ellipsis"> <nobr><bean:write name="WoHandleForm" property="soNbr" /></nobr></div>
					   </td>
                     </tr>
                     
                      <logic:notEmpty name="WoHandleForm"
									property="failReasonIdList" scope="request">                 				 
                     <tr bgcolor="#E5E8EC">
                       <td align="right" >失&nbsp;败&nbsp;原&nbsp;因：</td>
                       <td colspan="2" onmouseover="showReason();" onmouseout="dispareReason();">
					      	<!-- 新增加的 -->
							<div class="dropDownList">
								<div id="dropDownList1" class="dropdown" style="z-index: 100">
									<html:select  name="WoHandleForm" property="failReasonId" style="width:176;display:none">
								      	<logic:present name="WoHandleForm" property="failReasonIdList">
								      		<html:optionsCollection name="WoHandleForm" property="failReasonIdList"/>
									  	</logic:present>
									</html:select>
									<span id='spanCon'>请选择失败原因</span>
									<img src="../images/select.png" height="19px" width="19px" name="imgId" style="cursor: pointer;"/>
									<ul onclick="getMyTextValue();"></ul>
								</div>
							</div>
							<input type="hidden" value="" id="commonText"/>
							<input type="hidden" value="0" id="rank"/>
							<div id="box" style="display: none;z-index: 10">
					      	</div>
					   </td>
                     </tr>
                      </logic:notEmpty>
                      
                     <!--  
                     <tr bgcolor="#E5E8EC" id="resTr" style="display:none">
                     	<td width="35%" height="20" align="right">释放资源：</td>
                     	<td colspan="2"> 
                     		<div style="overflow:auto;height:52px;">
                     			<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="9FAAB5">
                     				<tr bgcolor="#E5E8EC">
                     					<td width="70%" colspan="2" bgcolor="E5E8EC">
                     						<span id='res'></span>
                     					</td>
                     				</tr>
                     			</table>
                     		</div>
                     	</td>
                     </tr>
                     -->
                     <tr bgcolor="#E5E8EC" id="delayFixDateTr" style="display:none">
                     		<td align="right">缓装转出日期(可空)：</td>
                     		<td> 	<html:text name="WoHandleForm"  property="delayFixCancelDate"
																	style="width:150px;"  
																	onclick="Calendar.display(Calendar.DATE)" />
                     		</td>
                     </tr>
                     <tr bgcolor="#E5E8EC" id="rltdWoTr" style="display:none">
                     		<td align="right">关联申请：</td>
                     		<td>
                     			<span id='rltdWo'></span> 
                     		</td>
                     </tr>                     
                     <logic:equal name="WoHandleForm" property="stepWoStaffConfig" value="D">
							<input type="hidden" name="retBy" value="retByCurrent" checked>当前登陆员工<br>
							<input type="hidden" name="nameConfirm" id="confirmName"/>	
					</logic:equal>
					<!-- 施工人多选（按工区+按包区） -->
										<logic:equal name="WoHandleForm" property="stepWoStaffConfig" value="S">
										<tr bgcolor="#E5E8EC">
											<td align="right" valign="top">
												施&nbsp;&nbsp;工&nbsp;&nbsp;人：
											</td>
											<td>
											<logic:notEmpty name="WoHandleForm" property="maintAreaStaffList">
												<input type="radio" name="retBy" value="retByCurrent" >当前登陆员工<br>
											</logic:notEmpty>
											<logic:empty name="WoHandleForm" property="maintAreaStaffList">
												<input type="radio" name="retBy" value="retByCurrent" checked>当前登陆员工<br>
											</logic:empty>
												<input type="radio" name="retBy" value="retByWorkAreaStaff" >按工区选
													<html:select name="WoHandleForm" property="workAreaStaffId" style='width:100'>
														<logic:present name="WoHandleForm" property="workAreaStaffList">
															<html:optionsCollection name="WoHandleForm" property="workAreaStaffList" />
														</logic:present>
													</html:select>  
													<br>
												 <logic:notEmpty name="WoHandleForm" property="maintAreaStaffList">
													<input type="radio" name="retBy" value="retByMaintAreaStaff" checked>按包区选
													<html:select name="WoHandleForm" property="maintAreaStaffId" style='width:100'>
														<logic:present name="WoHandleForm" property="maintAreaStaffList">
															<html:optionsCollection name="WoHandleForm" property="maintAreaStaffList" label="name" value="staffId" />
														</logic:present>
													</html:select>  
												</logic:notEmpty>
											</td>
										</tr>
											
										</logic:equal> 
										<!-- 施工人多选（按工区） -->
										<logic:equal name="WoHandleForm" property="stepWoStaffConfig" value="W">
										<tr bgcolor="#E5E8EC">
											<td align="right" valign="top">
												施&nbsp;工&nbsp;人&nbsp;员：
											</td>
											<td>
											<logic:notEmpty name="WoHandleForm" property="workAreaStaffList">
												<input type="radio" name="retBy" value="retByCurrent" >当前登陆员工<br>
											</logic:notEmpty>
											<logic:empty name="WoHandleForm" property="workAreaStaffList">
												<input type="radio" name="retBy" value="retByCurrent" checked>当前登陆员工<br>
											</logic:empty>
											<logic:notEmpty name="WoHandleForm" property="workAreaStaffList">
												<input type="radio" name="retBy" value="retByWorkAreaStaff" checked>按工区选
													<html:select name="WoHandleForm" property="workAreaStaffId" style='width:100'>
														<logic:present name="WoHandleForm" property="workAreaStaffList">
															<html:optionsCollection name="WoHandleForm" property="workAreaStaffList" />
														</logic:present>
													</html:select>  
											</logic:notEmpty>
											
											
											</td>
										</tr>
										
										<!-- end by liangyx -->
										
										</logic:equal>
										<!-- 施工人多选（按包区） -->
										<logic:equal name="WoHandleForm" property="stepWoStaffConfig" value="M">
										<tr bgcolor="#E5E8EC">
											<td align="right" valign="top">
												施&nbsp;&nbsp;工&nbsp;&nbsp;人：
											</td>
											<td>
											<logic:notEmpty name="WoHandleForm" property="maintAreaStaffList">
												<input type="radio" name="retBy" value="retByCurrent" >当前登陆员工<br>
											</logic:notEmpty>
											<logic:empty name="WoHandleForm" property="maintAreaStaffList">
												<input type="radio" name="retBy" value="retByCurrent" checked>当前登陆员工<br>
											</logic:empty>

												 <logic:notEmpty name="WoHandleForm" property="maintAreaStaffList">
													<input type="radio" name="retBy" value="retByMaintAreaStaff" checked>按包区选
													<html:select name="WoHandleForm" property="maintAreaStaffId" style='width:100'>
														<logic:present name="WoHandleForm" property="maintAreaStaffList">
															<html:optionsCollection name="WoHandleForm" property="maintAreaStaffList" label="name" value="staffId" />
														</logic:present>
													</html:select>  
												</logic:notEmpty>
												
										</tr>
										</logic:equal>
										
							<!--  
							<div style="overflow:auto;" id="sysConfigId">
  								<input type="hidden" value="123" id="configId"/>
									<tr bgcolor="#E5E8EC">
										<td width="35%" height="20" align="right">
											联&nbsp;系&nbsp;电&nbsp;话：
										</td>
										<td>
											<html:text name="WoHandleForm" property="processDesc" style="width:176px;"/>
										</td>
									</tr>
									<tr bgcolor="#E5E8EC" id="delayFixDateTr">
										<td width="35%" height="20" align="right">
											具备装机时间：
										</td>
										<td>
											<html:text name="WoHandleForm" value="转待装时请选择具备装机时间" property="reworkDate" style="width:176px;color:gray" 
											onclick="clearText();Calendar.display(Calendar.DATE);" 
											onfocus="compareDate()"/>
											
										</td>
									</tr>
							</div>			
							
							-->
				<!-- 障碍证实环节 -->
				<input type="hidden" value="<bean:write name='WoHandleForm' property='woSVo.stepId'/>" name="stepId"/>
				<tr bgcolor="#E5E8EC" id="idConfirmTr" style="display:none">
					<td align="right">证实内容：</td>
					<td>
						<textarea name="" cols="32" rows="2" style="overflow:auto" size="1024">							
						</textarea>
					</td>
				</tr>
			
				<logic:present name="WoHandleForm" property="soFaultExtMVO.customerSatisfactionList">
					<tr bgcolor="#E5E8EC" id="idCustSatisfaction" style="display:none">
						<td align="right">客户满意度：</td>
						<td>
							<html:select name="WoHandleForm" property="soFaultExtMVO.customerSatisfaction" style="width:100">
								<logic:present name="WoHandleForm" property="soFaultExtMVO.customerSatisfactionList">
									<html:optionsCollection name="WoHandleForm" property="soFaultExtMVO.customerSatisfactionList"/>
								</logic:present>
							</html:select>
						</td>
					</tr>
				</logic:present>
					    <tr bgcolor="#E5E8EC">
					   
                <td align="right" valign="top">备 &nbsp;&nbsp;注：
                    <label></label></td>
                <td><textarea name="surveyInput" cols="31" rows="8" 
                 style="overflow:auto" size="1024"><bean:write name="WoHandleForm" property="remarks" /></textarea></td>
              </tr>
                   
               <logic:present name="WoHandleForm" property="woNotAllowList">
										<logic:notEmpty name="WoHandleForm" property="woNotAllowList">
										<tr bgcolor="#E5E8EC">
											<td align="right" valign="top">
													无法回单：
													<label></label>
											</td>
											<td>
												<div style="overflow:auto;width:100%;height:48;" id="listDiv">
										
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
												<td bgcolor="9FAAB5">
												<table width="100%" cellspacing="1">
												<tr bgcolor="#E5E8EC">
												<td width="20%" align="center">定单号</td>
												<td width="20%" align="center">工单号</td>
												<td width="60%" align="center">无法回单原因</td>
												</tr>
												<logic:iterate id="vo" name="WoHandleForm" property="woNotAllowList">
												<tr bgcolor="#E5E8EC">
													<td width="20%" align="center">
														<bean:write name="vo" property="soNbr"/>
                                           			 </td>
													<td width="20%" align="center">
														<bean:write name="vo" property="woNbr" />
													</td>
													<td width="60%" align="center">
														<bean:write name="vo" property="remarks" />
													</td>
												</tr>		
												</logic:iterate>
												</table>
												</td>
												</tr>
												</table>
												</div>
											</td>
										</tr>
										</logic:notEmpty>
										</logic:present>
										
										<logic:present name="WoHandleForm" property="woAllowList">
											<logic:notEmpty name="WoHandleForm" property="woAllowList">
												<logic:iterate id="vo" name="WoHandleForm" property="woAllowList">
												<tr>
													<td width="20%" align="center">
														<input type="hidden" name="woAry"
															value='<bean:write name="vo" property="woNbr" />'>
													</td>
												</tr>		
												</logic:iterate>								
       										</logic:notEmpty>
										</logic:present>     
                    
            </table>
	    </td>
	  </tr>
	</table>
	</fieldset>
	
	  <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td height="2" bgcolor="#FFFFFF"></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td bgcolor="#ADB7C0">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="33" align="right" bgcolor="#C8CFD9">
              <input type="button" name="confirm" onclick="return ok_close();"  value="确 定" class="button2" id="sureBtn">
              &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
              <input name="Submit32" type="submit" class="button2" onclick='return closeWindow();' value="取 消"> &nbsp;&nbsp;&nbsp;</td>
              <td width="2%" align="right" bgcolor="#C8CFD9">&nbsp;&nbsp;&nbsp;</td>
            </tr>
          </table></td>
        </tr>
      </table>
	
	</td>
  </tr>
 </table>
</html:form>
</BODY>
</HTML>
<script>
	<logic:notEmpty name="failMsg" scope="request">
		alert('<bean:write name="failMsg" />');
	</logic:notEmpty>
	//add by liangyx 2013/02/27 start...  增加的对时间特殊处理的需求即7天范围内 
	//计算传入的两个时间差，精确到天
	function daysBetween(DateOne,DateTwo)  
	{   
	    var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));  
	    var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);  
	    var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));  
	  
	    var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));  
	    var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);  
	    var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));  
	  
	    var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);   
	    return Math.abs(cha);  
	}  
	
	//得到当前系统时间，以2013-2-27这样的形式组合
	function getSystemTime(){
		var myDate = new Date();
	    var myYear = myDate.getFullYear();
	    var myMonth = myDate.getMonth()+1;
	    var myDay = myDate.getDate();
	  	
	    var myTimes = myYear+"-"+myMonth+"-"+myDay;
		return myTimes;
	}
/*
	function compareDate() {
		//得到用户选择的时间 
		var userTimes = document.all("reworkDate");
		userDate = userTimes.value.substring(0,10);
		//得到系统当前时间
        var currentTimes = getSystemTime();
		
		var configIdVar = document.getElementById("configId").value;//得到省份 吉林：jl 内蒙：nm add by liangyongxing 2013-5-10
		if(configIdVar=="jl"){
			//判断是否大于7天
        	var days1 = daysBetween(currentTimes,userDate); 
        	if(userDate!="" && days1>7){
        		alert("【具备装机条件时间】的条件为->\n 当前时间的7天时间范围内！");
        		userTimes.value = "";
        	}
		}
	}
	
	function clearText() {
		//清除具备时间方框中的内容
		var timeText = document.all("reworkDate");
		timeText.style.color = "black";
		if("" != timeText.value) {
			if(timeText.value=="转待装时请选择具备装机时间") {
				timeText.style.color = "black";
				timeText.value = "";
			} else {
				timeText.value = "";
				timeText.style.color = "black";
			}
		} 
	}
	function checkText() {
		var timeText = document.all("reworkDate");
		if(""==timeText.value) {
			timeText.style.color = "gray";
			timeText.value = "转待装时请选择具备装机时间";
		} else if(timeText.value=="转待装时请选择具备装机时间") {
			timeText.style.color = "gray";
		} else {
			timeText.style.color = "black";
		}
	}
	*/
	function showReason(){
		//add by liangyx 显示时候判断字符的多少，如果多就是div变大少的话就按照固定格式显示 
		var hiddenRank = document.getElementById("rank").value;//得到用户选择的第几个option的值的value 
		//得到option的对象(数组 )
		var selectOptions = document.all("failReasonId");
		//alert(selectOptions[1].text);
		document.getElementById("box").style.display="block"; 
		var tooLengthText = selectOptions[hiddenRank].text;
		if(tooLengthText.toString().length>10) {
			document.getElementById("box").style.marginLeft="2px";
		} else {
			document.getElementById("box").style.marginLeft="95px";
		}
		document.getElementById("box").innerHTML = selectOptions[hiddenRank].text;
  	}
  	function dispareReason(){
		document.getElementById("box").style.display="none"; 
  	}
	//end by liangyx ...
</script>


<!-- add by liangyx 2013/02/27 用于判断手工输入是否为空和不同省份显示的内容不同的判断-->
<script type="text/javascript">
var spanCon=document.getElementById('spanCon');
var uls=document.getElementById('dropDownList1').getElementsByTagName('ul')[0];
uls.style.position='absolute';
uls.style.top=spanCon.offsetTop+spanCon.offsetHeight+'px';
uls.style.left=spanCon.offsetLeft+'px';
uls.style.border='1px solid #e5e8ed';



var ____configArray;
function __initDropDownList(configArray){
	
	//获取Select菜单
	____configArray=configArray;
	var existArray=configArray.split("|");
	for(var i=0;i<existArray.length;i++){
		if(existArray[i].length<1){return;}
		//根据参数分别获取div，并分别添加事件
		var parentContainer=document.getElementById(existArray[i]);
		if(!parentContainer){return;}
		//获取下面的select，且获取其中的option
		var selectObj=parentContainer.getElementsByTagName("select");
		if(selectObj.length<1){return;}
		var optionArray=selectObj[0].getElementsByTagName("option");
		//获取option，并分别添加到各个li
		var optionLength=optionArray.length;
		for(var j=0;j<optionLength;j++){
			//获取ul，以便能够添加项目
			var ulObj=parentContainer.getElementsByTagName("ul");
			if(ulObj.length<1){return;}
			//获取span，以便能显示当前选择的项目
			var spanObj=parentContainer.getElementsByTagName("span");
			var imgId = parentContainer.getElementsByTagName("img");
			if(spanObj.length<1){return;}
			var liObj=document.createElement("li");
			var textNode=document.createTextNode(optionArray[j].firstChild.nodeValue)
			liObj.appendChild(textNode);
			liObj.setAttribute("currentIndex",j);
			//给每个liObj添加事件
			liObj.onclick=function(){
				selectCurrentItem(this.parentNode,this);
			}
			liObj.onmouseover=function(){this.className="over";}
			liObj.onmouseout=function(){this.className="";}
			ulObj[0].appendChild(liObj);
			spanObj[0].onclick=function(event){
				//如果当前是显示的，就隐藏，反之亦然
				showHiddenUl(this);
			}
			spanObj[0].onmouseover=function(){this.className='over';}
			spanObj[0].onmouseout=function(){this.className="";};
			
			
			imgId[0].onclick=function(event){
				//如果当前是显示的，就隐藏，反之亦然
				showHiddenUl(this);
			}
			imgId[0].onmouseover=function(){this.className='over';}
			imgId[0].onmouseout=function(){this.className="";};
			
			ulObj[0].onclick=function(){this.className="";}
		}
		parentContainer.onclick=function(event){
			if(!event){event=window.event;}
				//阻止事件冒泡
				event.cancelBubble=true;
				var eventUlObj=this.getElementsByTagName("ul")[0];
				bodyClickHiddenUl(eventUlObj);
		}
	}
}
function selectCurrentItem(ulObj,currentObj){
	var parentObj=ulObj.parentNode;
	var spanObj=parentObj.getElementsByTagName("span")[0];
	spanObj.firstChild.nodeValue=currentObj.firstChild.nodeValue;
	var selectObj=parentObj.getElementsByTagName("select")[0];
	selectObj.selectedIndex=parseInt(currentObj.getAttribute("currentIndex"));
	
	//增加显示提示框的效果
	var rankIndex = parseInt(currentObj.getAttribute("currentIndex"));//得到选择的第几个 
	//将得到的回写到隐藏域中 
	var hiddenRank = document.getElementById("rank");
	hiddenRank.value = rankIndex;
}
function showHiddenUl(currentObj){
	var parentNode=currentObj.parentNode;
	var ulObj=parentNode.getElementsByTagName("ul")[0];
	if(ulObj.className==""){
		ulObj.className="show";
	}else{
		ulObj.className="";
	}
}
//点击body区域（非"下拉菜单"）隐藏菜单
function addBodyClick(func) {
	var bodyObj=document.getElementsByTagName("body")[0];
	var oldBodyClick = bodyObj.onclick;
		if (typeof bodyObj.onclick != 'function') {
			bodyObj.onclick = func;
		} else {
			bodyObj.onclick = function() {
			oldBodyClick();
			func();
		}
	}
}
//隐藏所有的UL
function bodyClickHiddenUl(eventUlObj){
	var existArray=____configArray.split("|");
	for(var i=0;i<existArray.length;i++){
		if(existArray[i].length<1){return;}
		//寻找所有UL并且隐藏
		var parentContainer=document.getElementById(existArray[i]);
		if(!parentContainer){return;}
		var ulObj=parentContainer.getElementsByTagName("ul");
		if(eventUlObj!=ulObj[0]){
			ulObj[0].className="";
		}
	}
}
var __dropDownList="dropDownList1";
__initDropDownList(__dropDownList);
//添加这个可以确保点击body区域的时候也可以隐藏菜单
addBodyClick(bodyClickHiddenUl);
</script>
<!-- end by liangyx -->

<script language="javascript">
 function closeWindow(){
        parent.window.returnValue = "yes";
		window.close();
		return false;
	}
	
	 function showFail(){
tr.style.display="block"
	}
		 function unshowFail(){
tr.style.display="none"
	}

	//回填信息
function ok_close() {	
  	if(document.all("surveyInput") == null) {
    	return false;
  	}
  	
  	//add by liangyx 2013/02/27 start...  用于校验手工输入姓名的验证 
  	var radioName = document.getElementById("raidoName");
  	if(radioName!=null){
  		if(radioName.checked==true){
		  	var names = document.all("woStaffName");
			if(names.value==""){
				alert("选择手工输入后必须填写内容才能进行提交!");
				names.focus();
				return false;
			}
		} else {
			var names = document.all("woStaffName");
			names.value="";
		}
  	}
	//end by liangyongxing 
	
	var wonbrs = document.getElementById("wonbrs").value;
 	//批量回单和释放资源不可以同时进行
 	var resArys = getChbArray("resAry");
 	if(resArys != null && wonbrs.split(',').length > 1){
 		confirm("批量回单与自动释放资源不可以同时操作！");
 		window.close(); 		
 	}else{
	 	//若定单不存在资源配置环节则不可以自动释放资源，需要人工操作
		if(resArys != null){
			var url = "../AjaxServlet?method=ajaxIsHaveResStep";
			var params = "soNbr=" + document.getElementById("returnSoNbrs").value;;
			var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:processResponse, asynchronous:false});
	 	}
 	}
 	var remarks = Trim(document.all("surveyInput").value)  ;	
 	 
     remarks = remarks.replace(new RegExp("&","gm"),"＆") ;	
     remarks = remarks.replace(new RegExp("#","gm"),"＃") ;	
   　 
	if (remarks.length > 1024)
	{
		alert("信息过长，请精简信息内容，限制字数为1024"); 
		return false;
	}
	
	 if(undefined!=document.all("failReasonId")){
	 	var selValue=document.all("failReasonId").value;
	 	for(var i=0;i<document.all.failReasonId.options.length;i++){
			if(document.all.failReasonId.options[i].value==selValue)
				var failReason=document.all.failReasonId.options[i].text;
		}
		if (!confirm("您确定失败原因为 ：\n" + failReason ))
		{
			return false;
		}
	 }
     
	var sureBtn = document.getElementById("sureBtn");
	sureBtn.disabled = true;
	var delayFixDate = document.getElementById("delayFixCancelDate");
			var url = "WoHandleAction.do?method=woReturn&woNbrAryStr="+wonbrs+"&returnType=1&remarks="+remarks+"&soCat="+<%=soCat%>;
			if (delayFixDate!=null){
 		       url+="&delayFixDate="+delayFixDate;
 		    }
			if(undefined!=document.all("failReasonId")){
				var failReasonId = document.all("failReasonId").value;
				if(failReasonId!=null||Trim(failReasonId)!=""){
					url = url+"&failReasonId="+failReasonId;
				}
			}
			document.failForm.action=url;
	   	 	document.failForm.submit();
}


function processResponse(originalRequest) {
	//alert("originalRequest.responseText = " + originalRequest.responseText)
	if ("false"!=originalRequest.responseText) {
		if(!confirm(originalRequest.responseText+"资源是后补的，需在后补环节人工释放！是否继续回单？"))
 		window.close();
	}
}

function getSourceList(failReasonId, checkboxName, checkboxName1) {
	//add by liangyx 2013/03/01
	var names = document.all("failReasonId");
	var reasonText = document.getElementById("commonText");
	for(var i=0;i<names.length;i++){
		if(failReasonId==names.options[i].value) {
			reasonText.value = names.options[i].text;
		}
	}
	
	//add by Baixd 11/06/09
	var vStepId = document.all.failForm("stepId").value;
	if(vStepId != null && vStepId != "" && "SP3058" == vStepId){
		//证实内容暂时不做显示
		//document.getElementById("idConfirmTr").style.display="block";
		document.getElementById("idCustSatisfaction").style.display="block";
	}
	
	if(failReasonId == ''){
		//初始页面使用
		if(undefined!=document.all("failReasonId")){
			failReasonId = document.getElementById('failReasonId').value;
		}else{
			return;
		}
	}
	if(failReasonId != '20003001' && failReasonId != '20003002' && failReasonId != '20003003'){
		var rltdWoTr = document.getElementById('rltdWoTr');
		rltdWoTr.style.display="none"; 
	}
	if(failReasonId == '20003003' ){//缓装
		var delayFixDateTr = document.getElementById('delayFixDateTr');
		delayFixDateTr.style.display="block"; 
	} 
	
	//alert("failReasonId = " + failReasonId);
	var wonbrs = document.getElementById("wonbrs").value;
	var returnSoNbrs = document.getElementById("returnSoNbrs").value;
	var soNbrs = returnSoNbrs.split(',');
	var woNbr=wonbrs.split(',');
	var url = "WoHandleAction.do?method=getResourceList";
	var params = "failReasonId=" + failReasonId  + "&checkboxName=" + checkboxName + "&soNbr=" + soNbrs[0]+"&woNbr="+woNbr[0];
	var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:showResponse, asynchronous:true});
	if (failReasonId == '20003001' || failReasonId == '20003002'||failReasonId == '20003003' ) {
		// alert("hi" + failReasonId);
		var url1 = "WoHandleAction.do?method=getRelatedWoList";
		params = "failReasonId=" + failReasonId  + "&checkboxName=" + checkboxName1 + "&woNbrAryStr="+wonbrs;
		var myAjax1 = new Ajax.Request(url1, {method:"get", parameters:params, onComplete:showResponse1, asynchronous:true});
	}
	
}
	
function showResponse(originalRequest){
	var resHTML = originalRequest.responseText;
	//alert("resHTML="+resHTML);
	if(resHTML != ""){
		var resTr = document.getElementById('resTr');
		resTr.style.display="block"; 
	}else{
		var resTr = document.getElementById('resTr');
		resTr.style.display="none"; 
	}
	var cb = document.getElementById('res');
	cb.innerHTML=resHTML;
}

function showResponse1(originalRequest){
	var resHTML = originalRequest.responseText;
	//alert("resHTML="+resHTML);
	if(resHTML != ""){
		var rltdWoTr = document.getElementById('rltdWoTr');
		rltdWoTr.style.display="block"; 
	}else{
		var rltdWoTr = document.getElementById('rltdWoTr');
		rltdWoTr.style.display="none"; 
	}
	var cb = document.getElementById('rltdWo');
	cb.innerHTML=resHTML;
}

<%
	String mode = SysConfigData.getSysConfigCurValue(
		SysConstants.SYS_CONFIG_FLEX_WEBSTART, null, null, null, null,null);
	if(mode == null || "".equals(mode.trim())){
		mode = "FLEX";
	}
%>
var procShowMode = "<%=mode %>";
function failDoFlagResponse(originalRequest){
	var failDoFlag = originalRequest.responseText;
	var returnValue;
	if("M"==failDoFlag){//异常处理标志为：异常人工调度
		var soNbr = document.getElementById("returnSoNbrs").value;
		var soSeq = document.getElementById("returnSoSeqs").value;
		var url = "../wm/flexAction.do?method=init&type=failForward&soNbr="+soNbr+"&soSeq="+soSeq;
        if(procShowMode == "WEBSTART"){
        	url = "../wm/AppletAction.do?method=init&type=failForward&soNbr="+soNbr+"&soSeq="+soSeq;
        }
		returnValue = window.showModalDialog(url, window,"dialogHeight: 568px; dialogWidth: 850px; center: yes; help: no;status:no;title:no;scroll:no");
		if(returnValue == undefined){
			alert("已选择的失败原因需要人工调度，请选择异常回退到的活动！");
			return;
		}
	} 
	var wonbrs = document.getElementById("wonbrs").value;
	var remarks = Trim(document.all("surveyInput").value)  ;	
    remarks = remarks.replace(new RegExp("&","gm"),"＆") ;	
    remarks = remarks.replace(new RegExp("#","gm"),"＃") ;	
	var url = "WoHandleAction.do?method=woReturn&woNbrAryStr="+wonbrs+"&returnType=1&remarks="+remarks;
	if(undefined!=document.all("failReasonId")){
		var failReasonId = document.all("failReasonId").value;
	}else{
		return;
	}
	var failReasonId = document.all("failReasonId").value;
	if(failReasonId!=null||Trim(failReasonId)!=""){
		url = url+"&failReasonId="+failReasonId;
	}
	if(returnValue != undefined && failReasonId!=null && Trim(returnValue) != ''){
		url = url+"&procNodeId="+returnValue;
	}
	document.failForm.action=url;
    document.failForm.submit();
}
</script>   