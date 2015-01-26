<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.cattsoft.pub.util.PagView"%>
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
<%@ taglib uri="/WEB-INF/pagination.tld" prefix="pag"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询实例</title>
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
	
		$('#btnQuery').click(function() {
			showCover();
			$("#queryForm").submit();
		});
		
		$('#btnAddCommonQuery').click(function() {
			$("#typeFlag").val("C");
			$("#formAddInstance").submit();
		});
		
		$('#btnAddGroupQuery').click(function() {
			$("#typeFlag").val("S");
			$("#formAddInstance").submit();
		});
})

function deleteConfim(instanceId){
	if (confirm("一旦删除，将无法恢复。确认删除吗？")) {
            window.location.href="../tm/instanceSettingAction.do?method=delete&instanceId="+instanceId; 
     }
}
</script>

</head>

<body>
	<form id="queryForm" action="../tm/instanceSettingAction.do?method=getQueryInstanceList"
		method="post">
		
		<div id="contentWrap">
			<div class="pageTitle"></div>
			<div class="pageColumn">
				<div class="qryCondition">
					<table style="height:50px;margin-bottom:6px">
						<tr>
							<td style="width:30px"></td>
						 	<td style="width:250px;">
						 		实例名称：&nbsp;&nbsp;
						 		<input type="text" class="shottext" name="instanceName" value='<%=request.getAttribute("instanceName")==null?"":request.getAttribute("instanceName") %>' /> 
						 	</td>
							<td>
								<input type="button" class="btn" style="width:100px;height:28px" id="btnQuery"
								value="查 询" />
							</td>
						</tr>
					</table>
				</div>
				<div id="datadiv" style="width:100%;overflow: auto;">
				<table id="datatable">
					<thead>
							<th  style="width:100px">实例名称</th>
							<th  style="width:100px">实例类型</th>
							<th  style="width:100px">数据库表名</th>
							<th  style="width:100px">数据库表别名</th>
							<th  style="width:100px">操作</th>
					</thead>
					<tbody>
						<logic:iterate id="instance" name="instancePage" property="viewList">
							<tr>
								<td class="ctd"><bean:write name="instance" property="instanceName"/></td>
							 	<td class="ctd"><bean:write name="instance" property="instanceTypeName"/></td>
							 	<td class="ctd"><bean:write name="instance" property="tableName"/></td>
							 	<td class="ctd"><bean:write name="instance" property="tableDesc"/></td>
							 	<td class="ctd">
							 		<a href="../tm/ctxdAction.do?method=queryResult&instanceId=<bean:write name='instance' property='queryInstanceId' />" >预览</a>&nbsp;&nbsp;
							 		 <a href="javascript:void(0)" onclick="deleteConfim(<bean:write name='instance' property='queryInstanceId' />)" >删除</a>  
							 	</td>
							</tr> 
						</logic:iterate>
						<tr>
							<pag:pagination name="instancePage" requestUri=" ../tm/instanceSettingAction.do?method=getQueryInstanceList&turnpag=yes" /> 
						</tr>
					</tbody>
				</table>
				</div> 
				<div style="width:100%;height:50px;">
					<div style="float:right;width:80px;">
						<input type="button" value="添加通用查询" style="width:80px;height:28px;" id="btnAddCommonQuery"/>
					</div>
					<div style="float:right;margin-left:30px">
						<div style="width:50px;float:right;"></div>
						<input type="button" value="添加汇总查询" style="width:80px;height:28px;" id="btnAddGroupQuery"/>
					</div>
				</div>
			</div>
		</div>
	</form>
	<form action="../tm/instanceSettingAction.do" id="formAddInstance"
		method="post" style="display:none">
		<span style="display:none"><input type="hidden" value='<%=request.getAttribute("tableName")%>' name="tableName"/></span>
		<input type="hidden" id="typeFlag" name="typeFlag"/> <input type="hidden"
			name="method" value="addInstanceInit" />
	</form>
</body>
</html>
