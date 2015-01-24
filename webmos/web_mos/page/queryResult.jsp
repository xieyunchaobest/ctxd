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


</style>

<script type="text/javascript">
$(function(){
	//setMenuHeight
	<%
	List queryColList = (List) request.getAttribute("queryColumnList");
	if(queryColList.size()>10){
		out.println( "$('#datatable').width("+queryColList.size()+"*100)");
	}
	%>
	
	
		$('#btnQuery').click(function() {
			showCover();
			$("#queryForm").submit();
		});
	
	
})
</script>

</head>

<body>
	<form id="queryForm" action="../tm/ctxdAction.do?method=queryResult"
		method="post">
		<span style="display:none"><input type="hidden" name="instanceId" value='<%=request.getAttribute("instanceId")%>'/></span>
		<div id="contentWrap">
			<div class="pageTitle"></div>
			<div class="pageColumn">
				<div class="qryCondition">
					<table style="height:50px;margin-bottom:6px">
						<tr>
							<%
								List conditionList = (List) request.getAttribute("conditionList");
								for (int i = 0; i < conditionList.size(); i++) {
									QueryConditionSVO m = (QueryConditionSVO) conditionList.get(i);
									String columnDesc = m.getColumnDesc();
									String conditionType = m.getConditionType();
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
								} else {
										if (Tools.isDateType(dataType)) {
							%>
							<td width='100px'><input type='text' class='shottext'  onClick="WdatePicker()" readonly="true"
								value="<%=value%>" name="QRY_<%=columnName%>" />
							</td>
							<%
								} else {
							%>
							<td width='100px'><input type='text' class='shottext'
								value="<%=value%>" name="QRY_<%=columnName%>" />
							</td>
							<%
								}
									}
								}
							%>
							<td><input type="button" class="btn" style="width:100px;height:28px" id="btnQuery"
								value="查 询" />
							</td>
						</tr>
					</table>
				</div>
				<div id="datadiv" style="width:100%;overflow: auto;">
				<table id="datatable">
					<thead>
						<logic:iterate id="condition" name="queryColumnList">
							<th  style="width:100px"><bean:write name="condition"
									property="columnDesc" /></th>
						</logic:iterate>
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
										out.print("<td  class='ctd'>" + m.get(columnName)
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
				</div>
				<table style="border:0px">
					<tr style="border:0px;">
						<td style="border:0px;width:80px;text-align:left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;说明：</td>
						<td style="border:0px;text-align:left">&nbsp;</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>
