<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.cattsoft.pub.util.PagView"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.cattsoft.tm.struts.Tools"%>
<%@page import="com.cattsoft.tm.vo.DColumnDescSVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.cattsoft.tm.vo.QueryConditionSVO"%>
<%@page import="com.cattsoft.tm.vo.QueryInstanceColumnSVO"%>
<%@page import="com.cattsoft.pub.util.StringUtil" %>
<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pagination.tld" prefix="pag"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据查询</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/public.js"></script>

<style type="text/css">
body {
	background: #FFF
}

.shortselect {
	background: #fafdfe;
	height: 28px;
	width: 150px;
	line-height: 28px;
	border: 1px solid #9bc0dd;
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
}

.shottext {
	background: #fafdfe;
	height: 28px;
	width: 150px;
	line-height: 28px;
	border: 1px solid #9bc0dd;
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
}

.smalltext {
	background: #fafdfe;
	height: 15px;
	width: 25px;
	line-height: 15px;
	border: 1px solid #9bc0dd;
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
}

.btn {
	background-image: url("../images/button_bg.png");
}


.datetext {
	background: #fafdfe;
	height: 28px;
	width: 80px;
	line-height: 28px;
	border: 1px solid #9bc0dd;
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
}


</style>

<script type="text/javascript">
$(function(){
	//setMenuHeight
	<%
	List queryColList = (List) request.getAttribute("queryColumnList");
	if(queryColList.size()>10){
		out.println( "$('#datatable').width("+queryColList.size()+"*100)");
	}
	String sortBy=(String)request.getAttribute("sortBy");
	if(sortBy==null)sortBy="";
	String sortRule=(String)request.getAttribute("sortRule");
	%>
	
	
		$('#btnQuery').click(function() {
			//showCover();
			var postform = document.getElementById("queryForm");
			postform.action="../tm/ctxdAction.do?method=queryResult";
			postform.submit();
		});
		
		var menudesc=$('#menudesc', window.parent.document).val();
		$(".pageTitle").html(menudesc);
	
	
	$('#btnExport').click(function() {
			var postform = document.getElementById("queryForm");
			postform.action="../tm/ctxdAction.do?method=exportExcel";
			postform.submit();
		});
	
})

function sort(obj){
	var sortColumnName=$(obj).attr("id");
	var sortRule=$(obj).attr("sortRule");
	if(sortRule=='DESC'){
		sortRule='ASC';
	}else{
		sortRule='DESC';
	}
	$("#sortBy").val(sortColumnName);
	$("#sortRule").val(sortRule);
	var postform = document.getElementById("queryForm");
	postform.action="../tm/ctxdAction.do?method=queryResult";
	postform.submit();
}
</script>

</head>

<body>
	<form id="queryForm" action="../tm/ctxdAction.do?method=exportExcel"
		method="post">
		<span style="display:none">
			<input type="hidden" name="instanceId" value='<%=request.getAttribute("instanceId")%>'/>
			<input type="hidden" id="sortBy" name="sortBy" value='<%=sortBy%>' />
			<input type="hidden" id="sortRule" name="sortRule" value='<%=sortRule%>' />
		</span>
		<div id="contentWrap">
			<div class="pageTitle"></div>
			<div class="pageColumn">
				<div class="qryCondition">
					<table style="height:50px;margin-bottom:6px;border:1px solid #c5dbe2">
						<tr class="ctrb" >
							<%
								List conditionList = (List) request.getAttribute("conditionList");
								for (int i = 0; i < conditionList.size(); i++) {
									QueryConditionSVO m = (QueryConditionSVO) conditionList.get(i);
									String columnDesc = m.getColumnDesc();
									String conditionType = m.getConditionType();
									String v1="";
									String v2="";
									if("S".equals(conditionType)){
										String vals=m.getValue();
										if(!StringUtil.isBlank(vals)){
											String vs[]=vals.split(",");
											if(vs.length==1){
												v1=vs[0];
											}else if(vs.length==2){
												v1=vs[0];
												v2=vs[1];
											}
										}
									}
									String columnName = m.getColumnName();
									String dataType = m.getDataType();
									List data = (List) m.getData();
									String value = (String) m.getValue();
									out.print("<td width='100px'>" + columnDesc + "</td>");
									if ("B".equals(conditionType)) {
										out.print("<td width='100px'>");
							%>
							<select class="shortselect" name="QRY_<%=columnName%>">
								<%
									out.print("<option value=\'\'>请选择</option> ");
											for (int j = 0; j < data.size(); j++) {
												String v = (String) data.get(j);
								%>
								<option value="<%=v%>"
									<%if (value.equals(v)) {
							out.print("selected=selected");
						}%>><%=v%></option>
								<%
									}
								%>
							</select>
							</td>
							<%
								} else if("E".equals(conditionType) || "C".equals(conditionType)){
										if (Tools.isDateType(dataType)) {
							%>
							<td width='100px'><input type='text' class='datetext'  onClick="WdatePicker()" readonly="true"
								value="<%=value%>" name="QRY_<%=columnName%>" />
							</td>
							<%
								} else {
							%>
							<td width='100px'>
								<input type='text' class='shottext' value="<%=value%>" name="QRY_<%=columnName%>" />
							</td>
							<%
								}
									}else if("S".equals(conditionType)){
										if (Tools.isDateType(dataType)) {
										%>
										<td width='200px'>
											<input type='text' class='datetext'  onClick="WdatePicker()" readonly="true"
											value="<%=v1%>" name="QRY_START_<%=columnName%>" /> —
											<input type='text' class='datetext'  onClick="WdatePicker()" readonly="true"
											value="<%=v2%>" name="QRY_END_<%=columnName%>" />
											
										</td>
										<%
											} else {
										%>
										<td width='200px'>
											<input type='text' class='shottext'
											value="<%=v1%>" name="QRY_START_<%=columnName%>" /> —
											<input type='text' class='shottext'
											value="<%=v2%>" name="QRY_END_<%=columnName%>" />
										</td>
										<%
										}
									
									}
								}
							%>
							<td>
							<input type="button" class="sbtn"   id="btnQuery"
								value="查 询" />
							<input type="button" class="sbtn"   id="btnExport"
								value="导 出" />
							</td>
							</td>
							
						</tr>
					</table>
		<div style="overflow-X:scroll;height:420px;"><div id="datadiv" style="width:100%;">
				<table id="datatable">
					<thead>
						<%
							if(queryColList!=null){
								for(int i=0;i<queryColList.size();i++){
									QueryInstanceColumnSVO c=(QueryInstanceColumnSVO)queryColList.get(i);
									String colDesc=c.getColumnDesc();
									String isSort=c.getIsSort();
									String colName=c.getColumnName();
									out.println("<th style='width:100px' id='"+colName+"' onclick='sort(this);' sortRule='"+sortRule+"' />");
									out.print(c.getColumnDesc());
									if("Y".equals(isSort)){
										if(sortBy.equals(colName)){//如果根据此列排序
											if("DESC".equals(sortRule)){//如果是降序
												out.println("<img src='../images/up_now.png' />");
											}else{
												out.println("<img src='../images/down_now.png' />");
											}
										
										}else{
											out.println("<img src='../images/down.jpg' id='"+colName+"' sortRule='DESC'/>");
										}
										
									}
									out.println("</th>");
								}
							}
						 %>
						<%-- <logic:iterate id="condition" name="queryColumnList">
							<th style="width:100px" id="<bean:write name='condition' property='columnName' />">
								<bean:write name="condition" property="columnDesc" />
								<logic:equal name="condition" property="isSort" value="Y">
									<img src="../images/down.jpg" />
								</logic:equal>
							</th>
						</logic:iterate> --%>
					</thead>
					<tbody>
						<%
							PagView pv=(PagView)request.getAttribute("resList");
							List resList = pv.getViewList();
							if(resList!=null &&resList.size()>0){
							for (int i = 0; i < resList.size(); i++) {
						%>
						<tr>
							<%
								Map m = (HashMap) resList.get(i);
									List queryColumnList = (List) request
											.getAttribute("queryColumnList");
									for (int j = 0; j < queryColumnList.size(); j++) {
										QueryInstanceColumnSVO column = (QueryInstanceColumnSVO) queryColumnList
												.get(j);
										String columnName = column.getColumnName();
										String width=column.getWidth();
										String bgColor=column.getBgColor();
										out.print("<td  class='ctd' style='width:"+width+"px;background-color:"+bgColor+";' >" + m.get(columnName)
												+ "</td>");
									}
							%>
						</tr>
						<%}
							}
						%>
						<tr>
							<pag:pagination name="resList" requestUri=" ../tm/ctxdAction.do?method=queryResult&turnpag=yes" /> 
						</tr>
					</tbody>
				</table>
				</div></div>
				</div>
				<table style="border:0px">
					<tr style="border:0px;">
						<td style="border:0px;width:80px;text-align:left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;说明：</td>
						<td style="border:0px;text-align:left">&nbsp;<bean:write name="tableVO" property="tableDesc" />  </td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>
