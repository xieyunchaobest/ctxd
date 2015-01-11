<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.cattsoft.tm.struts.Tools"%>
<%@page import="com.cattsoft.tm.vo.DColumnDescSVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>简单漂亮的后台管理界面模板 - 源码之家</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
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

.btn {
	background-image: url("../images/button_bg.png");
}
</style>
</head>

<body>
	<form id="queryForm" action="../tm/ctxdAction.do?method=queryResult"
		method="post">
		<span style="display:none"><input type="hidden" value='<%=request.getAttribute("tableId")%>' name="tableId"/></span>
		<div id="contentWrap">
			<div class="pageTitle"></div>
			<div class="pageColumn">
				<div class="qryCondition">
					<table style="height:50px;margin-bottom:6px">
						<tr>
							<%
								List conditionList = (List) request.getAttribute("conditionList");
								for (int i = 0; i < conditionList.size(); i++) {
									Map m = (HashMap) conditionList.get(i);
									String columnDesc = (String) m.get("columnDesc");
									String conditionType = (String) m.get("conditionType");
									String columnName = (String) m.get("columnName");
									String dataType = (String) m.get("dataType");
									List data = (List) m.get("data");
									String value = (String) m.get("value");
									out.print("<td width='100px'>" + columnDesc + "</td>");
									if ("M".equals(conditionType)) {
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
							<td><input type="submit" class="btn" style="width:100px;height:28px"
								value="查 询" />
							</td>
						</tr>
					</table>
				</div>
				<table>
					<thead>
						<logic:iterate id="condition" name="queryColumnList">
							<th width="100px"><bean:write name="condition"
									property="columnDesc" /></th>
						</logic:iterate>
					</thead>
					<tbody>
						<%
							List resList = (List) request.getAttribute("resList");
							if(resList!=null &&resList.size()>0){
							for (int i = 0; i < resList.size(); i++) {
						%>
						<tr>
							<%
								Map m = (HashMap) resList.get(i);
									List queryColumnList = (List) request
											.getAttribute("queryColumnList");
									for (int j = 0; j < queryColumnList.size(); j++) {
										DColumnDescSVO column = (DColumnDescSVO) queryColumnList
												.get(j);
										String columnName = column.getColumnName();
										out.print("<td class='ctd'>" + m.get(columnName)
												+ "</td>");
									}
							%>
						</tr>
						<%}
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</form>
</body>
</html>
