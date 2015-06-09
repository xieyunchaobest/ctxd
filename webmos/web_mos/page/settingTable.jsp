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
<title>配置表格</title>
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
	margin-left: 20px;
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
	margin-left: 20px;
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
	margin: 0px 0px 10px 0px;
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
	width: 300px;
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
				else
					this.removeAttribute('checked');
			});
			$("option", this).each(function() {
				if (this.selected)
					this.setAttribute('selected', 'selected');
				else
					this.removeAttribute('selected');
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
		var flag = valiate();
		if (flag == false)
			return;
		var html = $("#columnFrame").contents().find("#tShowColumn").formhtml();
		$("#hiddens").html('').html(html);
		$("#queryForm").submit();
	}

	function valiate() {
		if ($("#dbTableList").val() == '' || $("#dbTableList").val() == null) {
			alert('请选择表格！');
			return false
		}

		if ($.trim($("#txttableDesc").val()) == '') {
			alert('请填写表名!');
			return false;
		}
		var vc = true;
		$("#columnFrame").contents().find("input[type='text']").each(
				function(i) {
					if ($.trim($(this).val()) == '') {
						alert('请填写注释内容!');
						vc = false;
						return false;
					}
				});
		if (vc == false)
			return false;
		return true;
	}

	function onFinish() {
<%String flag = (String) request.getAttribute("flag");
			if ("1".equals(flag)) {
				out.println("alert('配置成功！');");
			}%>
	}
	
	$(function(){
		var menudesc=$('#menudesc', window.parent.document).val();
		$(".pageTitle").html(menudesc);
	});
</script>

</head>

<body onload="onFinish();">
<div class="pageTitle"></div>
	<form id="queryForm" action="../tm/ctxdAction.do?method=settingTable"
		method="post">
		<div style="text-align:center;width:100%">
			<div style="margin:0 auto;width:1000px">
				<div id="allTables" class="divalltable">
					<span id='hiddens' style='display:none'></span> 待配置表格 <select
						name="dbTableList" size=6 id="dbTableList"
						style="height:400px;width:98%" multiple onclick="reloadPage()">
						<logic:iterate id="dbtable" name="dbTableList">
							<option title="<bean:write name='dbtable'  property='tableName' />"
								value="<bean:write name='dbtable'  property='tableName' />">
								<bean:write name='dbtable' property='tableName' />
							</option>
						</logic:iterate>
					</select>

					<logic:iterate id="dbtable" name="dbTableList">
						<input type="hidden"
							id="desc_<bean:write  name='dbtable' property='tableName'/>"
							value="<bean:write  name='dbtable' property='tableDesc'/>" />
						<input type="hidden"
							id="stat_<bean:write  name='dbtable' property='tableName'/>"
							value="<bean:write  name='dbtable' property='statisticsComments'/>" />
					</logic:iterate>


				</div>
				<div id="columndiv" class="columns"
					style="margin-left:20px;text-align:center;">
					<span style="width:65px;">表名:</span><input type="text" id="txttableDesc" class="shottext" maxlength="32"
						name="cnTableName" style="margin:10px 0px 10px 35px;width:300px" /><br>
						<div style="float:left;width:65px">统计规则:</div>
					<textarea id="txtstatRule" type="text" name="staticRule" maxlength="64"
						class="staticRule"></textarea>
					<div style="width:100px;margin:0 auto;">
						<input type="button" value="确定"
						class="sbtn"
							style="margin-top:50px;"
							onclick="submitForm()" />
					</div>

				</div>
				<div id="columndiv" class="columnscomment" style="margin-left:20px">
					<iframe width="100%" scrolling="auto" height="100%"
						frameborder="false" allowtransparency="true"
						style="border: medium none;"
						src="../tm/ctxdAction.do?method=showColumnComments"
						id="columnFrame" name="columnFrame"></iframe>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
