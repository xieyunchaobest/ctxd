<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="gb2312"%>
<jsp:directive.page import="com.cattsoft.pub.SysConstants ,com.cattsoft.pub.util.SysConfigData;"/>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pagination.tld" prefix="pag"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>：：服务开通管理系统：：</title>
		<link href="../css/tabdisplaystyle.css" rel="stylesheet"
			type="text/css">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #fbfbfb;
}
-->
</style>
		<script type="text/javascript" language="javascript"
			src="../js/calendarTime.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/sorttable.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/public.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/relatedSelect.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/prototype.js"></script>
			<script type="text/javascript" language="javascript"
			src="../js/hideQuery.js"></script>
		<script language="javascript">
var count=0;
//所属定单
function soDetail(){
	var flag = 0;
	var count = 0;
	var url="../sp/SoDetailAction.do?method=init";
	var mote="dialogHeight: 710px; dialogWidth: 1000px; center: yes; help: no;status:no;title:no;scroll:no";
	var checkboxAry = document.all("chbSoNbrArr");
	var soSeqIds=document.all["soSeqIds"];
	if(checkboxAry != null){
		if (checkboxAry.constructor != Array) {
			if(checkboxAry.checked){
				flag++;
			    url= url+"&soVo.soNbr="+checkboxAry.value+"&soSeq="+soSeqIds.value;
			}
		}
		if(checkboxAry.length != null){
			for(var i = 0; i<checkboxAry.length; i++){
				if(checkboxAry[i].checked){
					flag=flag+1;
					count = i + count;
				}
			}
			url = url+"&soVo.soNbr="+checkboxAry[count].value+"&soSeq="+soSeqIds[count].value;
		}
	}else{
		alert("没有记录");
		return false;
	}
	if(flag==1){
		result = window.showModalDialog(url, window, mote);
	}else{
		alert("请选择一条记录");
		flag = false;
	}
	if(result==undefined||result==null)
        return false;
}
//预约申请
function soBookAdd(){
	var flag = 0;
	var count = 0;
	var url="SoBookAction.do?method=init";
	var mote="dialogHeight: 760px; dialogWidth: 970px; center: yes; help: no;status:no;title:no;scroll:no";
	var checkboxAry = document.all("chbSoNbrArr");
	if(checkboxAry != null){
		if (checkboxAry.constructor != Array) {
			if(checkboxAry.checked){
				flag++;
			    url= url+"&vo.soNbr="+checkboxAry.value;
			}
		}
		if(checkboxAry.length != null){
			for(var i = 0; i<checkboxAry.length; i++){
				if(checkboxAry[i].checked){
					flag=flag+1;
					count = i + count;
				}
			}
			url = url+"&vo.soNbr="+checkboxAry[count].value;
		}
	}else{
		alert("没有记录");
		return false;
	}
	if(flag==1){
		result = window.showModalDialog(url, window, mote);
	}else{
		alert("请选择一条记录");
		flag = false;
	}
	if(result==undefined||result==null)
        return false;
    else 
    {
        gotoPage();
        return true;
    }
}
//预约修改
function soBookMod(){
	var flag = 0;
	var count = 0;
	var url="SoBookAction.do?method=updateInit";
	var mote="dialogHeight: 760px; dialogWidth: 970px; center: yes; help: no;status:no;title:no;scroll:yes";
	var checkboxAry = document.all("chbSoNbrArr");
	var ajaxUrl="SoBookAction.do?method=queryWoIsComplate";
	var params = "vo.soNbr=";
	if(checkboxAry != null){
		if (checkboxAry.constructor != Array) {
			if(checkboxAry.checked){
				flag++;
				url= url+"&vo.soNbr="+checkboxAry.value;
				params = params + checkboxAry.value;
			}
		}
		if(checkboxAry.length != null){
			for(var i = 0; i<checkboxAry.length; i++){
				if(checkboxAry[i].checked){
					flag=flag+1;
					count = i + count;
				}
			}
			url = url+"&vo.soNbr="+checkboxAry[count].value;
			params = params + checkboxAry[count].value;
		}
	}else{
		alert("没有记录");
		return false;
	}
	if(flag==1){
		result = window.showModalDialog(url, window, mote);
	}else{
		alert("请选择一条记录");
		flag = false;
	}
	if(result==undefined||result==null)
        return false;
    else 
    {
        gotoPage();
        return true;
    }
}
function validate(){
	var flag = 0;
	var count = 0;
	var url="SoBookAction.do?method=queryBySoNbr";
	var params = "vo.soNbr=";
	var checkboxAry = document.all("chbSoNbrArr");
	if(checkboxAry != null){
		if (checkboxAry.constructor != Array) {
			if(checkboxAry.checked){
				flag++;
				params=params+checkboxAry.value;
			}
		}
		if(checkboxAry.length != null){
			for(var i = 0; i<checkboxAry.length; i++){
				if(checkboxAry[i].checked){
					flag=flag+1;
					count = i + count;
				}
			}
			params=params+checkboxAry[count].value;
		}
	}
	if(flag==1){
		var myAjax = new Ajax.Request(
				url,
				{method: 'get', parameters: params, onComplete: returnedResponse}
				);
	}else{
		document.all("bookAddBtn").disabled=true;
		document.all("bookModBtn").disabled=true;
	}
}
function chbSelect(obj){
	if(count==1){
		obj.checked=false;
		count--;
		return;
	}
	count++;
}
function returnedResponse(originalRequest){
	var resText = originalRequest.responseText;
	if(resText==1){
		document.all("bookAddBtn").disabled=false;
		document.all("bookModBtn").disabled=true;
		return true;
	}else if(resText==2){
		document.all("bookModBtn").disabled=false;
		document.all("bookAddBtn").disabled=true;
		return true;
	}else{
		return false;
	}
}
function toExcel2(){
	var temp = queryForm.chbSobnrArr2.checked;
	if(temp){
		queryForm.action = "SoAction.do?method=toExcel";
		queryForm.submit();
		return true;
	}else{
		SaveExcel('t1');
		return false;	
	}
}
function changeArea(url,value,changeSelect,requestMode){
	//synchRelated(url,value,changeSelect,requestMode);
	synRefresh('LOCAL_NET_ID', value, 'AREA_ID', changeSelect, 'Q');
	var areaId = document.all("vo.areaId").value;
	//changeWorkArea('../tm/SoBookAction.do?method=changeArea',areaId,'vo.workAreaId',requestMode);
	//synchRelated('../tm/SoBookAction.do?method=changeArea',areaId,'vo.maintAreaId',requestMode);
	synRefresh('AREA_ID', areaId, 'SERV_DEPT_ID', 'vo.maintAreaId', '');
}
function changeWorkArea(url,value,changeSelect,requestMode){
	synRefresh('AREA_ID', value, 'SERV_DEPT_ID', 'vo.maintAreaId', '');
	//synchRelated(url,value,changeSelect,requestMode);
	//var workAreaId = document.all("vo.workAreaId").value;
	//synchRelated('../tm/SoBookAction.do?method=changeWorkArea',workAreaId,'vo.maintAreaId',requestMode);
}
function chbUnable(){
	if(document.all("chbMaintAreaId").disabled){
		document.all("vo.maintAreaId").disabled=true;
	}else{
		document.all("chbMaintAreaId").checked=true;
		show(document.all("chbMaintAreaId").checked,'vo.maintAreaId');
	}
}
function showBusi(){
 	 document.all("busiAnimateF").style.display="block";
 	 document.all("busiAnimateA").style.display="block";
}
function hideBusi(){
 	 document.all("busiAnimateF").style.display="none";
 	 document.all("busiAnimateA").style.display="none";
}
function isWoComplateMod(){
	var flag = 0;
	var count = 0;
	var url="SoBookAction.do?method=queryWoIsComplate";
	var params = "vo.soNbr=";
	var checkboxAry = document.all("chbSoNbrArr");
	if(checkboxAry != null){
		if (checkboxAry.constructor != Array) {
			if(checkboxAry.checked){
				flag++;
				params=params+checkboxAry.value;
			}
		}
		if(checkboxAry.length != null){
			for(var i = 0; i<checkboxAry.length; i++){
				if(checkboxAry[i].checked){
					flag=flag+1;
					count = i + count;
				}
			}
			params=params+checkboxAry[count].value;
		}
	}
	if(flag==1){
		var myAjax = new Ajax.Request(
				url,
				{method: 'get', parameters: params, onComplete: isWoComplateModResponse}
				);
	}else{
		document.all("bookAddBtn").disabled=true;
		document.all("bookModBtn").disabled=true;
	}
}
function isWoComplateModResponse(originalRequest){
	var resText = originalRequest.responseText;
	if(resText>0){
		//alert("当前定单需要预约的环节已经回单，将不能进行预约操作");
		if(confirm("当前定单需要预约的环节已经回单，确定要进行预约操作？")){
			soBookMod();
		}
		return false;
	}else{
		soBookMod();
		return true;
	}
}
function isWoComplateAdd(){
	var flag = 0;
	var count = 0;
	var url="SoBookAction.do?method=queryWoIsComplate";
	var params = "vo.soNbr=";
	var checkboxAry = document.all("chbSoNbrArr");
	if(checkboxAry != null){
		if (checkboxAry.constructor != Array) {
			if(checkboxAry.checked){
				flag++;
				params=params+checkboxAry.value;
			}
		}
		if(checkboxAry.length != null){
			for(var i = 0; i<checkboxAry.length; i++){
				if(checkboxAry[i].checked){
					flag=flag+1;
					count = i + count;
				}
			}
			params=params+checkboxAry[count].value;
		}
	}
	if(flag==1){
		var myAjax = new Ajax.Request(
				url,
				{method: 'get', parameters: params, onComplete: isWoComplateAddResponse}
				);
	}else{
		document.all("bookAddBtn").disabled=true;
		document.all("bookModBtn").disabled=true;
	}
}
function isWoComplateAddResponse(originalRequest){
	var resText = originalRequest.responseText;
	if(resText>0){
		//alert("当前定单需要预约的环节已经回单，将不能进行预约操作");
		if(confirm("当前定单需要预约的环节已经回单，确定要进行预约操作？")){
			soBookAdd();
		}
		return false;
	}else{
		soBookAdd();
		return true;
	}
}
function overTime(isChecked,name){
	if(isChecked){
		document.all(name).checked = true;
		show(document.all("chbBookDate").checked,"fromBookDate");
		show(document.all("chbBookDate").checked,"toBookDate");
	}
}

/** 查询 **/
function query(){
	document.all("queryType").value="1";
	showBusi();
	queryForm.submit();	
}


/** 快捷查询 **/
function quickQuery(quickQueryCondition){
	document.all("queryType").value="3";
	queryForm.action = "SoBookAction.do?method=BookQuery&vo.quickQueryCondition="+quickQueryCondition;
	tip.style.display   =   'none'; 
	showBusi();
	queryForm.submit();	
}

function openQuickOptions(){
		tip.style.display   =   tip.style.display   ==   'block'   ?   'none'   :   'block'; 
		tip.style.left=175;
		tip.style.pixelTop=78;
}
	var isOut = true;
	document.onmousedown=function(){
		if(tip.style.display!="none"&&isOut){
			tip.style.display="none";
		}
}
</script>
	</head>
	<body style='overflow-y: hidden'  onLoad="hideBusi()">
		<div id="busiAnimateA"
			style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; background-color: #FFFFFF; filter: alpha(opacity =     50); display: none; z-index: 1000;">
			<table width="177" height="100%" align="center" class="busy_table"
				border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
		</div>
		<div id="busiAnimateF"
			style="position: absolute; top: 200; left: 300; width: 177; height: 32; filter: alpha(opacity =     100); display: none; z-index: 2000;">
			<table width="177" align="center" class="busy_table" border="0"
				cellspacing="0" cellpadding="0">
				<tr>
					<td width="32" height="32">
						<img src="../images/busy.gif" width="32" height="32">
					</td>
					<td>
						<img src="../images/busy_text.gif" width="145" height="50">
					</td>
				</tr>
			</table>
		</div>
		<html:form styleId="queryForm" method="post"
			action="SoBookAction.do?method=BookQuery" >
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="0" id="t2">
				<tr>
					<td colspan="2" valign="top">
						<table width="100%" align="left">
							<tr>
								<td>
									<fieldset>
										<legend>
											<a href="javascript:hideQue('qyeryDiv','listDiv','img',95,145);"><font color="blue">
											查询设置</font><img id="img" src="../images/btn_up1_blue.gif"></a>
										</legend>
										<div id="qyeryDiv" style="display:block;">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											align="left">
											<tr>
												<td>
													<table width="750" border="0" cellpadding="0"
														cellspacing="0" align="left">
														<tr>
														<html:hidden property="queryType" />
															<td width="90px" align="left" nowrap>
																<html:checkbox property="chbLocalNetId" disabled="true"
																	value="1" />
																本&nbsp;&nbsp;地&nbsp;网：
															</td>
															<td width="100px">
																<html:select property="vo.localNetId"
																	style="width:150px"
																	onchange="changeArea('../tm/SoBookAction.do?method=changeLocalNet',this.value,'vo.areaId',false);">
																	<logic:present name="SoBookForm"
																		property="localNetList">
																		<html:optionsCollection name="SoBookForm"
																			property="localNetList" />
																	</logic:present>
																</html:select>
															</td>
															<td width="90px" align="left" nowrap>
																<html:checkbox property="chbAreaId" disabled="true"
																	value="1" />
																服&nbsp;&nbsp;务&nbsp;&nbsp;区：
															<td width="100px" align="left">
																<html:select property="vo.areaId" style="width:120px"
																	onchange="changeWorkArea('../tm/SoBookAction.do?method=changeArea',this.value,'vo.maintAreaId',false);">
																	<logic:present name="SoBookForm" property="areaList">
																		<html:optionsCollection name="SoBookForm"
																			property="areaList" />
																	</logic:present>
																</html:select>
															</td>
															<td colspan="4" nowrap>
																<html:checkbox property="chbBookDate" styleId="chbBookDate"
																	onclick="show(this.checked,'fromBookDate');show(this.checked,'toBookDate');" />
																<label for="chbBookDate">预约时间：</label>
															
																<html:text property="fromBookDate" style="width:105px"
																	onclick="Calendar.display(Calendar.DATE)"
																	disabled="true" />															
																至															
																<html:text property="toBookDate" style="width:105px"
																	onclick="Calendar.display(Calendar.DATE)"
																	disabled="true" />
															</td>
															
														</tr>

														<tr>

															<td align="left" width="90px" nowrap>
																<html:checkbox property="chbSoNbr" styleId="chbSoNbr"
																	onclick="show(this.checked,'vo.extSoNbr');" />
																<label for="chbSoNbr">定单编码：</label>
															</td>
															<td width="90px" align="left">
																<html:text property="vo.extSoNbr" style="width:150px"
																	disabled="true" />
															</td>
															<td align="left" nowrap width="90px">
																<html:checkbox property="chbAccNbr" styleId="chbAccNbr"
																	onclick="show(this.checked,'vo.accNbr');" />
																<label for="chbAccNbr">业务号码：</label>
															<td width="90px" align="left">
																<html:text property="vo.accNbr" style="width:120px"
																	disabled="true" />
															</td>
															<td align="left" width="90px" nowrap>
																<html:checkbox property="chbMaintAreaId" styleId="chbMaintAreaId"
																	onclick="show(this.checked,'vo.maintAreaId');" />
																<label for="chbMaintAreaId">维护区域：</label>
															</td>
															<td width="60px" align="left">
																<html:select property="vo.maintAreaId"
																	style="width:150px" disabled="true">
																	<logic:present name="SoBookForm"
																		property="maintAreaList">
																		<html:optionsCollection name="SoBookForm"
																			property="maintAreaList" />
																	</logic:present>
																</html:select>
															</td>
															<td align="left" width="90px" nowrap>																
															</td>
															<td width="60px" align="left">															
															</td>
															</tr>
															<tr>
															<td width="120px" align="left">
																<html:checkbox property="vo.chbOverTime" styleId="vo.chbOverTime" value="1"
																	onclick="overTime(this.checked,'chbBookDate')" />															
																<label for="vo.chbOverTime">预约超期</label>
															</td>
															<td colspan="3" nowrap align="left" width="200px">
																<input name="simQueButton" type="button" class="button2"
																	value="查询" onclick="query()">															
															&nbsp;&nbsp;&nbsp;
												 	<input name="advSetButton" type="button" class="button2" style="background-image: url('../images/but_bg3.gif')"
																	value="快捷查询" onclick="openQuickOptions()">
												<%
													String showNum = SysConfigData.getSysConfigCurValue(
														SysConstants.SYS_CONFIG_QUICK_QUERY_NUMBER, null, null, null, null, null);
												 %>
												 <div id='tip' style='display:none;' class="quickQueryDiv" onmouseover="isOut=false;" onmouseout="isOut=true;"> 
												 	<table align="right" cellpadding="0" cellspacing="0" width="95px">
												 		<tr onMouseOver="changeCol(this,'#E5E8EC');" onMouseOut="changeCol(this,'#ffffff');">
												 			<td align="center" onclick="quickQuery('1');">&nbsp;最近生成的<span><%= showNum %></span>条</td>
												 		</tr>
												 	</table>
												 </div>
															</td>

														</tr>

													</table>
												</td>
											</tr>
										</table>
										</div>
									</fieldset>
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<tr>
					<td valign="top">
						<table height="100%" align="left">
							<tr>
								<td >
									<fieldset>
										<legend>
											定单查询信息
										</legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr align="left">
												<td align="right" valign="top" class="main_tab" width="98%">
													<table width="766" border="0" cellspacing="0"
														cellpadding="0" class="main_tab" bgcolor="E5E8EC">
														<tr>
															<td width="5%" align="right"></td>
															<td width="93%" align="right" height="24">
																<pag:pagination name="pageView"
																	requestUri="SoBookAction.do?method=BookQuery&turnpag=yes"></pag:pagination>
															</td>
															<td width="2%" align="right"></td>
														</tr>
													</table>
													<div id="listDiv"
														style="overflow: auto; width: 100%; height: expression(document. body . clientHeight-155>245?document.body.clientHeight-155:245)">
													<table width="2070" border="0" cellspacing="0"
															cellpadding="0" id="t" align="left">
															<tr>
																<td bgcolor="9FAAB5">
																	<table width="100%" border="0" cellpadding="0"
																		cellspacing="1" id="t1" >
																		<tr align="center" bgcolor="#BEC6CF"
																			class="fixedHeaderTr" height="23">
																			<td width="10" background="../images/3.jpg">
																			</td>
																			<td width="130" align="center"
																				background="../images/3.jpg">
																				<a href="#" onclick="sortTable('t1',2,'n')">定单号码</a>
																			</td>
																			<td width="100" background="../images/3.jpg">
																				<a href="#" onclick="sortTable('t1',3,'n')">内部单号</a>
																			</td>
																			<td width="150" background="../images/3.jpg">
																				<a href="#" onclick="sortTable('t1',4,'s')">电信业务</a>
																			</td>
																			<td width="120" background="../images/3.jpg">
																				<a href="#" onclick="sortTable('t1',5,'n')">业务号码</a>
																			</td>
																			<td width="120" background="../images/3.jpg">
																				<a href="#" onclick="sortTable('t1',6,'d')">客户名称</a>
																			</td>
																			<td width="120" background="../images/3.jpg">
																				<a href="#" onclick="sortTable('t1',7,'s')">联系方式</a>
																			</td>
																			<td width="200" background="../images/3.jpg">
																				<a href="#" onclick="sortTable('t1',8,'d')">客户地址</a>
																			</td>
																			<td width="120" background="../images/3.jpg">
																				<a href="#" onclick="sortTable('t1',9,'s')">上门时间</a>
																			</td>
																			<td width="90" background="../images/3.jpg">
																				<a href="#" onclick="sortTable('t1',10,'d')">预约人员</a>
																			</td>
																			<td width="90" background="../images/3.jpg">
																				<a href="#" onclick="sortTable('t1',11,'d')">工单状态</a>
																			</td>
																			<td width="120" background="../images/3.jpg">
																				<a href="#" onclick="sortTable('t1',12,'d')">回单时间</a>
																			</td>
																			<td width="110" background="../images/3.jpg">
																				<a href="#" onclick="sortTable('t1',13,'s')">受理营业厅</a>
																			</td>
																			<td width="90" background="../images/3.jpg">
																				<a href="#" onclick="sortTable('t1',15,'s')">受理员工</a>
																			</td>
																			<td width="120" background="../images/3.jpg">
																				<a href="#" onclick="sortTable('t1',16,'d')">受理日期</a>
																			</td>
																			<td width="90" background="../images/3.jpg">
																				<a href="#" onclick="sortTable('t1',18,'s')">变更次数</a>
																			</td>
																			<td width="290" background="../images/3.jpg">
																				<a href="#">备注</a>
																			</td>
																		</tr>
																		<logic:notEmpty name="pageView" property="viewList">
																			<logic:iterate id="vo" name="pageView"
																				property="viewList" indexId="index" offset="0">
																				<tr onMouseOver="changeCol(this,'#ffffff');"
																					onMouseOut="changeCol(this,'#E5E8EC');"
																					bgcolor="E5E8EC" align="left"
																					id="tr<bean:write name='index'/>"
																					onclick="chickBGColor(this,<bean:write name='index'/>)">
																					<td class="submit">
																						<input type="radio" name="chbSoNbrArr"
																							id='<bean:write name='index'/>'
																							value="<bean:write name="vo" property="soNbr"/>"
																							onclick="validate();chbSelect(this);" />
																						<input type="hidden" name="soSeqIds"
																							value="<bean:write name="vo" property="soSeq"/>" />
																					</td>
																					<td align="left">
																						<bean:write name="vo" property="extSoNbr" />
																					</td>
																					<td align="left">
																						<bean:write name="vo" property="soNbr" />
																					</td>
																					<td align="left">
																						<bean:write name="vo" property="busiName" />
																					</td>
																					<td align="left" title=''>
																						<bean:write name="vo" property="accNbr" />
																					</td>
																					<td align="left">
																						<bean:write name="vo" property="custName" />
																					</td>
																					<td align="left">
																						<bean:write name="vo" property="contactInfo" />
																					</td>
																					<td align="left">
																						<bean:write name="vo" property="addrInfo" />
																					</td>
																					<td align="left">
																						<bean:write name="vo" property="bookTime"
																							format="yyyy-MM-dd HH:mm:ss" />
																					</td>
																					<td align="left">
																						<bean:write name="vo" property="staffName" />
																					</td>
																					<td align="left">
																						<bean:write name="vo" property="woStsName" />
																					</td>
																					<td align="left">
																						<bean:write name="vo" property="factComplDate"
																							format="yyyy-MM-dd HH:mm:ss" />
																					</td>
																					<td align="left">
																						<bean:write name="vo" property="soWorkAreaName" />
																					</td>
																					<td align="left">
																						<bean:write name="vo" property="soStaffName" />
																					</td>
																					<td align="left">
																						<bean:write name="vo" property="applDate" />
																					</td>
																					<td align="left">
																						<bean:write name="vo" property="bookCount" />
																					</td>
																					<td align="left">
																						<bean:write name="vo" property="remarks" />
																					</td>
																				</tr>
																			</logic:iterate>
																		</logic:notEmpty>
																	</table>
																</td>
															</tr>
														</table>
													</div>

													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td height="3" bgcolor="#FFFFFF"></td>
														</tr>
													</table>
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td bgcolor="#ADB7C0">
																<table width="100%" border="0" cellspacing="0"
																	cellpadding="0">
																	<tr>
																		<td bgcolor="#C8CFD9">
																			<table width="100%" border="0" cellspacing="1"
																				cellpadding="2">
																				<tr height="25">
																					<td width="4%" align="center" nowrap>
																						&nbsp;
																					</td>
																					<td width="8%" align="center" nowrap></td>
																					<td width="89%" align="right">
																						<input name="soDetailBtn" type="button"
																							class="button2" value="所属定单"
																							onclick="return soDetail();">
																						<input name="bookAddBtn" type="button"
																							disabled="disabled" class="button2" value="预约申请"
																							onclick="isWoComplateAdd();">
																						<input name="bookModBtn" type="button"
																							disabled="disabled" class="button2" value="预约修改"
																							onclick="isWoComplateMod();">
																						<!--<input name="bookModBtn" type="button" class="button2" value="预约修改" onclick="soBookMod();">-->
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</fieldset>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
	<script type="text/javascript" language="javascript">
	//show(document.all("chbWorkAreaId").checked,'vo.workAreaId');
	//show(document.all("chbWorkAreaId").checked,'chbMaintAreaId');
	show(document.all("chbMaintAreaId").checked,'vo.maintAreaId');
	show(document.all("chbAccNbr").checked,"vo.accNbr");
	show(document.all("chbSoNbr").checked,"vo.extSoNbr");
	overTime(document.all("vo.chbOverTime").checked,"chbBookDate");
	show(document.all("chbBookDate").checked,"fromBookDate");
	show(document.all("chbBookDate").checked,"toBookDate");
</script>
</html>