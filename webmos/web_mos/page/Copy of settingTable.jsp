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
<title>简单漂亮的后台管理界面模板 - 源码之家</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>

<style type="text/css">
body {
	background: #FFF
}

.divalltable {
	border: 1px solid #9bc0dd;
	width: 150px;
	height: 450px;
	margin-top: 50px;
	margin-left: 50px;
	float: left;
}

.maindiv{
	width:95%;
	height:550px;
	border: 1px solid #9bc0dd;
	float:left;
}
.moveTo{
	width:30px;
	height:50px;
	border: 1px solid #9bc0dd;
	float:left;
	margin: 200px 10px 0 10px;
}

.settedTable{
	border: 1px solid #9bc0dd;
	width: 150px;
	height: 450px;
	margin-top: 50px;
	margin-left: 0px;
	float: left;
}


</style>

<script type="text/javascript">
	
</script>

</head>

<body>
	<form id="queryForm" action="../tm/ctxdAction.do?method=queryResult"
		method="post">
		<div class="maindiv">
			<div id="allTables" class="divalltable">
			待配置表格
			<select name="dbTableList" size=6 style="height:400px;width:98%" multiple>
				<logic:iterate id="dbtable" name="dbTableList">
					<option value="<bean:write name='dbtable'  property='tableName' />">  <bean:write name='dbtable'  property='tableName' /> </option>
				</logic:iterate>
			</select>
			
			
			</div>
			<div id="divToLeft" class="moveTo">
				<input type="button" value=" >> " style="width:30px ;height: 20px;" /><br><br/>
				<input type="button" value=" << " style="width:30px ;height: 20px;" />
			</div>
			<div id="settedTable" class="settedTable">已配置表格</div>
			<div id="columndiv" class="settedTable" style="margin-left:50px">展示列</div>
		</div>
	</form>
</body>
</html>
