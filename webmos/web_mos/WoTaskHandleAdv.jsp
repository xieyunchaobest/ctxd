<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="gb2312"%>
<jsp:directive.page import="com.cattsoft.pub.SysConstants ,com.cattsoft.pub.util.SysConfigData,com.cattsoft.pub.util.PagInfo,com.cattsoft.pub.util.StringUtil;"/>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<% 
	PagInfo pagInfo	= (PagInfo)request.getAttribute("pagInfo");	
	String accNbr = (String)request.getAttribute("accNbr");
	String extSoNbr = (String)request.getAttribute("extSoNbr");
%>
<html>
	<head>
		<title>：：服务开通管理系统：：</title>
		<link href="../css/tabdisplaystyle.css" rel="stylesheet"
			type="text/css">

		<style type="text/css">
		<!--
		body {
			background-color: #fbfbfb;
		}
		-->
		</style>
		<input type="hidden" value="sc" id="configId"/>
		<script type="text/javascript" language="javascript"
			src="../js/calendarTime.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/relatedSelect.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/sorttable.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/prototype.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/public.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/optionSel.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/gb2312.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/jquery-1.2.6.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/ui.core.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/ui.autocomplete.js"></script>
	<script>
	
	function simpleQuery(){
		var accNbr = document.forms[0].accNbr.value;
		var extSoNbr = document.forms[0].extSoNbr.value;
		var pagCount = document.forms[0].pagCount.value;
		var pagSize = document.forms[0].pagSize.value;
		
		
	 	document.forms[0].action='WoHandleAction.do?method=initWo4Oss&pageNo=1&pagCount='+pagCount+'&pagSize='+pagSize+'&accNbr='+accNbr+'&extSoNbr='+extSoNbr;
		//document.forms[0].target="_blank";
		document.forms[0].submit();
	}
	 function showbusiAnimate(){
	 	 try{
		  parent.busiAnimate.style.display ='';
		  }catch(e){ 
		 try{
			  busiAnimate.style.display ='';
           }catch(e){} }}
	 function Isnumber(In_Str){
	     StrLen=In_Str.length;
		 var	Ret_Value = true;
		 for (i=0; i<StrLen; i++)        {
			FirstCha = escape(In_Str.charAt(i));
			 if ((FirstCha < '0') || (FirstCha > '9')){
				 Ret_Value = false;
					 break;
			 }
		 }
	 }
	 function filterWo(){
		var chbFilterWo = document.getElementById("filterWoFlag");
		var Ids = document.getElementsByName("woNbrAry");
		var checkDisplays = document.getElementsByName("checkDisplayAry");
		var rows = document.getElementById("t1").rows;
		if(chbFilterWo.checked){
			parent.setFilterWoChbFlag(chbFilterWo.value);
			for(var l=0;l<checkDisplays.length;l++){
				if(checkDisplays[l].value=="N"){
					Ids[l].disabled=true;
					Ids[l].checked=false;
					rows[l+1].style.backgroundColor = "#E5E8EC";
				}
			}
		}else{
			parent.setFilterWoChbFlag("");
			for(var l=0;l<checkDisplays.length;l++){
				if(checkDisplays[l].value=="N"){
					Ids[l].disabled=false;
				}
			}
		}
	}
	 
	 function goPage(no){
		 showbusiAnimate();
		 var pagCount = document.forms[0].pagCount.value;
		 var pagSize = document.forms[0].pagSize.value;
		 document.forms[0].action='WoHandleAction.do?method=initWo4Oss&pageNo='+ no +'&pagCount='+pagCount+'&pagSize='+pagSize;
		 document.forms[0].submit();
		 }
    function gotoPage(){
		 var no = document.forms[0].pageNo.value;
		 var pagCount = document.forms[0].pagCount.value;
		 var pagSize = document.forms[0].pagSize.value;
		 if (no=='') {
			 alert('请输入页数!');
			 return false;
		 }
		 if (!Isnumber(no)) {alert('输入必须为数字');return false;}
		 if (!Isnumber(pagSize)) {alert('输入必须为数字');return false;}
		 if (parseInt(no)>parseInt(pagCount)) {
			 alert('输入值必须小于总页数');
			 return false;
		 }
		 if (no<1) {
				alert('输入值必须大于0');
				return false;
		 }
		 if (pagSize<1) {
			 alert('输入值必须大于0');
			 return false;
		 }
		 if (pagSize>1001) {
			 alert('每页显示的条数太多，不能超过100条');
			 return false;
		 }
															 
		 showbusiAnimate();
		 document.forms[0].action='WoHandleAction.do?method=initWo4Oss&pageNo='+ no +'&pagCount='+pagCount+'&pagSize='+pagSize;
		 document.forms[0].submit();
		 }

    function checkSoCat(soCatArray){
  	 	var spArray = new Array();
  	 	spArray = soCatArray.split(",");
		var i=1;
		var s = spArray[0];
		while(i<spArray.length){
			if(s!=spArray[i]){
				return false;
			}
			i++;
		}
		return true;
    }
    /** 回单 **/
	function printWoReturn(yes)
	{//判断returnType为0是成功回单，为1是失败回单, 为3是故障定性
		
		var woNbrArray = getChbArray("woNbrAry");
		var soCatArray = getHiddenArray("woNbrAry","soCat");
		if(woNbrArray == null || woNbrArray.length!=1) {
				alert("请选择一条记录！");
				return false;
		}
		
		var woNbrAryStr = woNbrArray.toString();
		var soCatAryStr = soCatArray.toString();
		var sp = soCatAryStr.split(",")
		var url="WoHandleAction.do?method=woReturnInit4Oss&woNbrAryStr="+woNbrAryStr+"&retType="+yes+"&soCat="+sp[0];
		var mote="dialogHeight: 473px; dialogWidth: 500px; center: yes; help: no;status:no;title:no;scroll:no";
			
		var returnValue = window.showModalDialog(url, window, mote);
		if (returnValue=="yes")
	    {
	    	return false;
	    }else if(returnValue==undefined){
	    	return false;
	    }
		location.href="WoHandleAction.do?method=initWo4Oss&pageNo=1&pagCount=10&pagSize=50&subSystemName=TM,,,";
	}
    
	function sum(){
        var woNbrArray = getChbArray("woNbrAry");
        if(woNbrArray==null){
            document.all["sumValue"].value=0;
            document.all["chkbosAll"].checked=false;
        }else{
            document.all["sumValue"].value=woNbrArray.size();
        }
    }
    function allSum(){
        var woNbrArray = getChbArray("woNbrAry");
        if(woNbrArray==null){
            document.all["sumValue"].value=0;
            document.all["aa"].checked=false;
        }else{
            document.all['sumValue'].value=document.all['count'].value;
        }
    }
	
	</script>
	</head>
	<body>
		<form name="WoHandleForm" id="queryform" method="post" action="/web/tm/WoHandleAction.do?method=inintReturnWo">
			<input type="hidden" name="pageCodeId" value=""/>
			<input type="hidden" value="WoReturn" name="pageCode"/>
			<input type="hidden" name="advQueryVo.pageCode" value="WoReturn">
			
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="2">
				<tr align="left" valign="top">
					<td colspan="2">

				<style type="text/css">
				/*拖拉表格样式*/
				body,table,td{font-size:12px}
				*{padding:0; margin:0}
				.sortable{table-layout:fixed}
				.sortable tr{ height:20px}
				.sortheader{text-decoration:none}
				/*超出单元格隐藏内容*/
				.sortable th,.sortable td{overflow: hidden; white-space: nowrap; text-overflow: ellipsis}
				.head th{background:#76AEF0;color:#fff; filter:progid:DXImageTransform.microsoft.gradient(gradienttype=0,startColorStr=#5075B4,endColorStr=#78B1F3)}
				.list th{color:#000; font-weight:normal; border-top:1px solid #fff}
				.list td{border-top:1px solid #fff; border-left:1px solid #fff}
				/*拖动单元格*/
				.resizeDiv{width:2px;overflow:hidden; float:right; cursor:col-resize}
				.sortarrow{color:#ffc;font-weight:normal}
				/*拖拉表格样式end*/
				</style>
  
  <div id="bubble_tooltip">
	<div class="bubble_top"><span></span></div>
	<div class="bubble_middle"><div id="bubble_tooltip_content" style='margin-left:3px'></div></div>
	<div class="bubble_bottom"></div>
</div>
<div id="bubble_tooltip_up">
	<div class="bubble_bottom_to_top"><span></span></div>
	<div class="bubble_middle_tranc"><div id="bubble_tooltip_content_tranc" style='margin-left:6px'></div></div>
	<div class="bubble_top_to_bottom"></div>
</div>
   <table width="100%" height="100%" border="0" align="center"
							cellpadding="0" cellspacing="0">							
							<tr>
								<td height="30">
									<table width="500" height="25" border="0" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="70px" align="right">
												&nbsp;&nbsp;业务号码：
											</td>
											<td width="130px" align="left">
												<%
													if(!StringUtil.isBlank(accNbr)) {
														%>
														<input type="text" name="accNbr" value="<%=accNbr %>" style="width:130px">
														<%
													} else {
														%>
														<input type="text" name="accNbr" value="" style="width:130px">
														<%
													}
												%>
											</td>
											
											<td width="70px"  align="right">
												&nbsp;&nbsp;&nbsp;定单号码:
											</td>
											<td width="140px" align="left">
												<%
													if(!StringUtil.isBlank(extSoNbr)) {
														%>
														<input type="text" name="extSoNbr" value="<%=extSoNbr %>" style="width:130px">
														<%
													} else {
														%>
														<input type="text" name="extSoNbr" value="" style="width:130px">
														<%
													}
												%>
											</td>
											<td width="100px" align="right">
											<input type="button" onclick=simpleQuery() value="查　询" class=button2>
											</td>									
										</tr>
									</table>
								</td>
							</tr>
							<tr valign="top">
								<td align="left" valign="top">
									<table border=0 cellspacing=2 cellpadding=0 width="100%"
										height="100%" id=mainTable class=main_tab>
										<tr valign="top">
											<td align="center" valign="top">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0">
													<tr>
														<td width="25%" align="left">
															&nbsp;按
															<select name="culmName" onchange="parent.submitToChild('WoHandleAction.do?method=searchWOAdv&culmName='+this.value,getPageCode())" style="width:80"><option value="WO_NBR">工单号码</option></select>
															排序所有页
															
																	<input type="checkbox" name="filterWoFlag" value="1" checked="checked" onclick="filterWo();sum();">过滤从单
																	
														</td>
														<td width="1%" align="left"></td>
														<td width="72%" align="right">
[
<%if(pagInfo.getPagNo()==1) { %>
	首页/
<%}
else{
%>
<a onclick='goPage(1)' style=cursor:hand>首页</a>
<%} %>
<%if(pagInfo.getPagNo()==1) { %>
	前一页
<%}
else{
%>
<a onclick='goPage(<%=pagInfo.getPagNo()-1%>)' style=cursor:hand>/前一页</a>
<%} %>
]&nbsp
[
<%if(pagInfo.getPagNo()==pagInfo.getPagCount()) {%>
	后一页
<%}else {%>
<a onclick='goPage("<%=pagInfo.getPagNo()+1%>")' style=cursor:hand>后一页</a>
<%} %>	

<%if(pagInfo.getPagNo()==pagInfo.getPagCount()) {%>
	/尾页]
<%}else {%>
<a onclick='goPage("<%=pagInfo.getPagCount()%>")' style=cursor:hand>/尾页]</a>
<%} %>	

<input type='hidden' name='begin' value='<%=(pagInfo.getPagNo()-1)*pagInfo.getPagSize()+1%>'>
<input type='hidden' name='end' value='<%=pagInfo.getPagSize()*pagInfo.getPagNo()%>'>
<input type='hidden' name='count' value='<%=pagInfo.getCount()%>'>
<input type='hidden' name='pagCount' value='<%=pagInfo.getPagCount()%>'>
显示第
<%if(pagInfo.getCount()==0) {%>
0
<%} else{%>
<%=(pagInfo.getPagNo()-1)*pagInfo.getPagSize()+1%>
<%} %>
到
<%if(pagInfo.getPagSize()*pagInfo.getPagNo()>pagInfo.getCount()) {%>
<%=pagInfo.getCount() %>
<%}else{ %>
<%=pagInfo.getPagSize()*pagInfo.getPagNo()%>
<%} %>

条&nbsp共<%=pagInfo.getCount()%>项&nbsp每页显示<input type='text' name='pagSize' size='2' onkeydown='if(event.keyCode==13) {event.returnValue = false;document.forms[0].goButton.onclick();}' value='<%=pagInfo.getPagSize() %>' >项&nbsp共<%=pagInfo.getPagCount() %>页&nbsp第<input type='text' name='pageNo' size='2' onkeydown='if(event.keyCode==13) {event.returnValue = false;document.forms[0].goButton.onclick();}' value='<%=pagInfo.getPagNo() %>' >页&nbsp<input type='button' onclick='gotoPage()' style='cursor:hand' name='goButton' value='GO!'>
														</td>
													</tr>
												</table>
												<div
													style="overflow: auto; width: 100%; height: expression(document.body.clientHeight-100);">
													<table width="2850" border="0"
														cellspacing="0" cellpadding="0" align="left">
														<tr>
															<td bgcolor="9FAAB5">
														<table width="100%" border="1" cellpadding="0"
																	cellspacing="0" class="sortable" id="t1">
																	<!-- 生成标题行 -->
																	<tr align="center" class="fixedHeaderTr"
																		bgcolor="#BEC6CF">
																		<td width="30">
																			<div align="center">
																				<label>
																					<input type="checkbox" name="aa" value="checkbox"
																						onclick="ChangeTableColorwhenFullSelect('t1',this,'woNbrAry');sum();">
																				</label>
																			</div>
																		</td>
																		<td width="30" align="center"
																			background="../images/3.jpg">
																			<a href="#" onclick="sortTable('t1',2,'s')">优先</a>
																		</td>
																		<td width="30" align="center">
																			<a href="#" onclick="sortTable('t1',3,'s')">超期</a>
																		</td>
																			<td align="center"
																				width='100'>
																				<a href="#"
																					onclick="sortTable('t1',4,'n')">
																					工单号码 </a>
																			</td>
																		
																			<td align="center"
																				width='70'>
																				<a href="#"
																					onclick="sortTable('t1',5,'n')">
																					内部单号 </a>
																			</td>
																		
																			<td align="center"
																				width='150'>
																				<a href="#"
																					onclick="sortTable('t1',6,'n')">
																					定单号码 </a>
																			</td>
																		
																			<td align="center"
																				width='250'>
																				<a href="#"
																					onclick="sortTable('t1',7,'s')">
																					业务号码/新旧号码/共线号码/物理号码 </a>
																			</td>
																		
																			<td align="center"
																				width='150'>
																				<a href="#"
																					onclick="sortTable('t1',8,'s')">
																					业务名称 </a>
																			</td>
																		
																			<td align="center"
																				width='70'>
																				<a href="#"
																					onclick="sortTable('t1',9,'s')">
																					装拆标志 </a>
																			</td>
																		
																			<td align="center"
																				width='120'>
																				<a href="#"
																					onclick="sortTable('t1',10,'s')">
																					施工人员 </a>
																			</td>
																		
																			<td align="center"
																				width='120'>
																				<a href="#"
																					onclick="sortTable('t1',11,'s')">
																					派单时间 </a>
																			</td>
																		
																			<td align="center"
																				width='120'>
																				<a href="#"
																					onclick="sortTable('t1',12,'s')">
																					预警时间 </a>
																			</td>
																		
																			<td align="center"
																				width='70'>
																				<a href="#"
																					onclick="sortTable('t1',13,'s')">
																					运行状态 </a>
																			</td>
																		
																			<td align="center"
																				width='120'>
																				<a href="#"
																					onclick="sortTable('t1',14,'s')">
																					运行状态时间 </a>
																			</td>
																		
																			<td align="center"
																				width='200'>
																				<a href="#"
																					onclick="sortTable('t1',15,'s')">
																					用户地址 </a>
																			</td>
																		
																			<td align="center"
																				width='120'>
																				<a href="#"
																					onclick="sortTable('t1',16,'s')">
																					受理时间 </a>
																			</td>
																		
																			<td align="center"
																				width='500'>
																				<a href="#"
																					onclick="sortTable('t1',17,'s')">
																					工单备注 </a>
																			</td>
																		
																			<td align="center"
																				width='120'>
																				<a href="#"
																					onclick="sortTable('t1',18,'s')">
																					受理营业厅 </a>
																			</td>
																		
																			<td align="center"
																				width='70'>
																				<a href="#"
																					onclick="sortTable('t1',19,'s')">
																					受理员工 </a>
																			</td>
																		
																			<td align="center"
																				width='100'>
																				<a href="#"
																					onclick="sortTable('t1',20,'s')">
																					本地网 </a>
																			</td>
																		
																			<td align="center"
																				width='100'>
																				<a href="#"
																					onclick="sortTable('t1',21,'s')">
																					服务区 </a>
																			</td>
																		
																			<td align="center"
																				width='100'>
																				<a href="#"
																					onclick="sortTable('t1',22,'s')">
																					回单人员 </a>
																			</td>
																		
																			<td align="center"
																				width='70'>
																				<a href="#"
																					onclick="sortTable('t1',23,'s')">
																					工单类型 </a>
																			</td>
																		
																	</tr>
																	<logic:notEmpty name="lists" scope="request">
																	<logic:iterate id="vo" name="lists" indexId="index" scope="request">
																		<tr onMouseOver="changeCol(this,'#ffffff');"
																			onMouseOut="changeCol2(this,'#E5E8EC');"
																		
																			bgcolor="#ECD3EB">
																			
																			<td align="center">
																				<label>
																					<input type="checkbox" name="woNbrAry"
																						onclick="sum()"
																						value='<bean:write name="vo" property="woNbr"/>'
																						id="index">
																				</label>
																				<input id="index" type="hidden" name="soCat" value='<bean:write name="vo" property="soCat"/>'>
																			</td>
																			<!-- 优先 -->
																			<td align="center">
																					<img src='../images/jibie03.gif' width='5'
																						height='13'>
																			</td>
																			<!-- 超期 -->
																			<td align='center'>
																					<img src='../images/time02.gif' width='11'
																						height='13'>
																			</td>
																								<td align="left">
																								<!--  title='854494'>-->
																								<div
																									STYLE='width:100;overflow:hidden;text-overflow:ellipsis'>
																											<bean:write name="vo" property="woNbr"/>
																								</div>
																							<td align="left">
																								<!--  title='362175'>-->
																								<div
																									STYLE='width:70;overflow:hidden;text-overflow:ellipsis'>
																									<bean:write name="vo" property="soNbr"/>
																								</div>
																							</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="extSoNbr"/>'>
																								<div
																									STYLE='width:150; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="extSoNbr"/>
																									</nobr>
																								</div>
																							</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="accNbr"/>'>
																								<div
																									STYLE='width:250; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="accNbr"/>
																									</nobr>
																								</div>
																							</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="businessName"/>'>
																								<div
																									STYLE='width:150; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="businessName"/>
																									</nobr>
																								</div>
																							</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="actTypeName"/>'>
																								<div
																									STYLE='width:70; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="actTypeName"/>
																									</nobr>
																								</div>
																							</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="woStaffName"/>'>
																								<div
																									STYLE='width:70; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="woStaffName"/>
																									</nobr>
																								</div>
																							</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="asgnDate"/>'>
																								<div
																									STYLE='width:120; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="asgnDate"/>
																									</nobr>
																								</div>
																							</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="preAlarmDate"/>'>
																								<div
																									STYLE='width:120; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="preAlarmDate"/>
																									</nobr>
																								</div>
																							</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="runStsName"/>'>
																								<div
																									STYLE='width:70; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="runStsName"/>
																									</nobr>
																								</div>
																							</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="runStsDate"/>'>
																								<div
																									STYLE='width:120; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="runStsDate"/>
																									</nobr>
																								</div>
																							</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="situated"/>'>
																								<div
																									STYLE='width:200; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="situated"/>
																									</nobr>
																								</div>
																							</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="applDate"/>'>
																								<div
																									STYLE='width:120; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="applDate"/>
																									</nobr>
																								</div>
																							</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="woRemarks"/>'>
																								<div
																									STYLE='width:120; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="woRemarks"/>
																									</nobr>
																								</div>
																							</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="soWorkAreaName"/>'>
																								<div
																									STYLE='width:120; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="soWorkAreaName"/>
																									</nobr>
																								</div>
																							</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="soStaffName"/>'>
																								<div
																									STYLE='width:70; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="soStaffName"/>
																									</nobr>
																								</div>
																							</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="localNetId"/>'>
																								<div
																									STYLE='width:100; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="localNetId"/>
																									</nobr>
																								</div>
																							<td align="left"
																								 title='<bean:write  name="vo" property="areaId"/>'>
																								<div
																									STYLE='width:100; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="areaId"/>
																									</nobr>
																								</div>
																						<td align="left">
																						</td>
																							<td align="left"
																								 title='<bean:write name="vo" property="woTypeName"/>'>
																								<div
																									STYLE='width:70; overflow: hidden; text-overflow: ellipsis'>
																									<nobr>
																										<bean:write name="vo" property="woTypeName"/>
																									</nobr>
																								</div>
																				</tr>
																				</logic:iterate>
																	</logic:notEmpty>
																		</table>
																		</td>
															</tr>
														</table>
													</div>
													<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td bgcolor="#ADB7C0">
															<table width="100%" border="1" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td bgcolor="#C8CFD9">
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr height="30">
																				<td width="4%" align="center" nowrap>
																					<input type="checkbox" name="chkbosAll"
																						value="checkbox"
																						onclick="ChangeTableColorwhenFullSelect('t1',this,'woNbrAry');sum();">
																					所有页
																					当前选中工单数：
																					<input type="text" name="sumValue" size="1"
																						value="0" readonly>
																				</td>
																																							
																				<td width="89%" align="right">
																					
																					<div id="btnDiv">
																									<!-- 如果enableRet4Auto为空，则正常回单按钮显示 -->
																										<input name="successReturnBtn" type="button"
																												onclick="printWoReturn('0')" class="button2"
																												value="正常回单">
																							    <!--对成功回单定制，失败回单定制按钮进行判断-->
																								<!-- selFlag=Y表示必须显示 -->
																									<!-- 如果enableRet4Auto为空，则失败回单按钮显示 -->
																										<input name="failReturnBtn" type="button"
																												onclick="printWoReturn('1')" class="button2"
																												value="失败回单">
																							    <!--对成功回单定制，失败回单定制按钮进行判断-->
																						<!--img src="../images/wo_toolbar/zhankaigongjul_n.gif" onclick="openToolbar()" onmouseover="chgIcon(this,'zhankaigongjul1_n.gif')" onmouseout="chgIcon(this,'zhankaigongjul_n.gif')" alt="展开工具栏"  style="cursor: hand;" align="middle"-->
																					</div>
																					<div id="toolBarDiv" align="left" style="position:fixed; width:550px; right:0px; bottom:19px;  z-index:1000; display: none;border: 1px solid #336699;background-color:#EEEEE2;">
																						<img src="../images/wo_toolbar/yincanggongjul_n.gif" onclick="hideToolbar()" onmouseover="chgIcon(this,'yincanggongjul1_n.gif')" onmouseout="chgIcon(this,'yincanggongjul_n.gif')" alt="隐藏工具栏" style="cursor: hand;" align="middle">
																						
																							<!-- imagePath只是img文件的名字且不包括后缀.gif,onmouseover中的文件名是前者的名字+1.gif -->
																							<img src="../images/wo_toolbar/zhengchanghuidan.gif" onclick="successReturn()" onmouseover="chgIcon(this,'/zhengchanghuidan1.gif')" onmouseout="chgIcon(this,'/zhengchanghuidan.gif')" alt="正常回单" style="cursor: hand;" align="middle">
																						
																							<!-- imagePath只是img文件的名字且不包括后缀.gif,onmouseover中的文件名是前者的名字+1.gif -->
																							<img src="../images/wo_toolbar/shibaihuidan.gif" onclick="failReturn()" onmouseover="chgIcon(this,'/shibaihuidan1.gif')" onmouseout="chgIcon(this,'/shibaihuidan.gif')" alt="失败回单" style="cursor: hand;" align="middle">
																						
																							<!-- imagePath只是img文件的名字且不包括后缀.gif,onmouseover中的文件名是前者的名字+1.gif -->
																							<img src="../images/wo_toolbar.gif" onclick="woAttachManage()" onmouseover="chgIcon(this,'1.gif')" onmouseout="chgIcon(this,'.gif')" alt="附件管理" style="cursor: hand;" align="middle">
																						
																							<!-- imagePath只是img文件的名字且不包括后缀.gif,onmouseover中的文件名是前者的名字+1.gif -->
																							<img src="../images/wo_toolbar/zhuanpai.gif" onclick="sendTo()" onmouseover="chgIcon(this,'/zhuanpai1.gif')" onmouseout="chgIcon(this,'/zhuanpai.gif')" alt="转派" style="cursor: hand;" align="middle">
																						
																							<!-- imagePath只是img文件的名字且不包括后缀.gif,onmouseover中的文件名是前者的名字+1.gif -->
																							<img src="../images/wo_toolbar.gif" onclick="woUpdateRemark()" onmouseover="chgIcon(this,'1.gif')" onmouseout="chgIcon(this,'.gif')" alt="修改备注" style="cursor: hand;" align="middle">
																						
																							<!-- imagePath只是img文件的名字且不包括后缀.gif,onmouseover中的文件名是前者的名字+1.gif -->
																							<img src="../images/wo_toolbar/daochue_n.gif" onclick="toExcel()" onmouseover="chgIcon(this,'/daochue_n1.gif')" onmouseout="chgIcon(this,'/daochue_n.gif')" alt="导出Excel" style="cursor: hand;" align="middle">
																					</div>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<!--  
																<tr>
																	<td bgcolor="ffffff" height="4" align="left"
																		valign="top">
																	</td>
																</tr>
																<tr>
																	<td bgcolor="ffffff" height="13" align="left"
																		valign="top">
																	</td>
																</tr>
																-->
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
	</form>
  </body>
</html>
