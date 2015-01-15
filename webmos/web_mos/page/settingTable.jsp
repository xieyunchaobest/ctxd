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

.maindiv {
	width: 95%;
	height: 550px;
	border: 1px solid #9bc0dd;
	float: left;
}

.moveTo {
	width: 30px;
	height: 50px;
	border: 1px solid #9bc0dd;
	float: left;
	margin: 200px 10px 0 10px;
}

.settedTable {
	border: 1px solid #9bc0dd;
	width: 150px;
	height: 450px;
	margin-top: 50px;
	margin-left: 0px;
	float: left;
}

.columns {
	border: 1px solid #9bc0dd;
	width: 400px;
	height: 140px;
	margin-top: 50px;
	margin-left: 50px;
	float: left;
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

.staticRule {
	margin: 0px 0px 10px 8px;
	width: 300px;
	height: 70px;
	background: #fafdfe;
	border: 1px solid #9bc0dd;
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
}

.columnscomment {
	border: 1px solid #9bc0dd;
	width: 400px;
	height: 450px;
	margin-top: 50px;
	margin-left: 50px;
	float: left;
}

</style>

<script type="text/javascript">

	(function($) {//解决.html()方法获取不到修改value值后的问题
		var oldHTML = $.fn.html;
		$.fn.formhtml = function() {
			if (arguments.length)
				return oldHTML.apply(this, arguments);
			$("input,textarea,button", this).each(function() {
				this.setAttribute('value', this.value);
			});
			$(":radio,:checkbox", this).each(function() {
				if (this.checked)
					this.setAttribute('checked', 'checked');
				elsethis.removeAttribute('checked');
			});
			$("option", this).each(function() {
				if (this.selected)
					this.setAttribute('selected', 'selected');
				elsethis.removeAttribute('selected');
			});
			return oldHTML.apply(this);
		};
	})(jQuery);

	function reloadPage() {
		var tableName = $.trim($("#dbTableList option:selected").text());
		var desc = $("#desc_" + tableName).val();
		var stat = $("#stat_" + tableName).val();
		$("#txttableDesc").val(desc);
		$("#txtstatRule").val(stat);
		$("#columnFrame").attr(
				"src",
				"../tm/ctxdAction.do?method=showColumnComments&tableName="
						+ tableName);
	}

	function submitForm() {
		var html = $("#columnFrame").contents().find("#tShowColumn").formhtml();
		alert(html);
	}
</script>

</head>

<body>
	<form id="queryForm" action="../tm/ctxdAction.do?method=queryResult"
		method="post">
		<div class="maindiv">
			<div id="allTables" class="divalltable">
				待配置表格 <select name="dbTableList" size=6 id="dbTableList"
					style="height:400px;width:98%" multiple onclick="reloadPage()">
					<logic:iterate id="dbtable" name="dbTableList">
						<option
							value="<bean:write name='dbtable'  property='tableId' />">
							<bean:write name='dbtable' property='tableName' />
						</option>
					</logic:iterate>
				</select>
				
				<logic:iterate id="dbtable" name="dbTableList">
					<input type="hidden" id="desc_<bean:write  name='dbtable' property='tableName'/>" value="<bean:write  name='dbtable' property='tableDesc'/>"/>
					<input type="hidden" id="stat_<bean:write  name='dbtable' property='tableName'/>" value="<bean:write  name='dbtable' property='statisticsComments'/>"/>
				</logic:iterate>


			</div>
			<div id="columndiv" class="columns" style="margin-left:50px">
				&nbsp;表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" id="txttableDesc" class="shottext" name="cnTableName" style="margin:10px 0px 10px 10px;width:300px"/><br>
				<div style="float:left">&nbsp;统计规则 ：</div></span><textarea id="txtstatRule" type="text"  name="staticRule"  class="staticRule"></textarea>
				<input type="button" value="确定" onclick="submitForm()"/>
			</div>
			<div id="columndiv" class="columnscomment" style="margin-left:50px">
				<iframe width="100%" scrolling="auto" height="100%"
					frameborder="false" allowtransparency="true"
					style="border: medium none;"
					src="../tm/ctxdAction.do?method=showColumnComments"
					id="columnFrame" name="columnFrame"></iframe>
			</div>
		</div>
	</form>
</body>
</html>
