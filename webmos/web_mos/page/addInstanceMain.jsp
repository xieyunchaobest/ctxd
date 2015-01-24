<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.cattsoft.tm.vo.DTableDescSVO"%>
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
				else this.removeAttribute('checked');
			});
			$("option", this).each(function() {
				if (this.selected)
					this.setAttribute('selected', 'selected');
				else this.removeAttribute('selected');
			});
			return oldHTML.apply(this);
		};
	})(jQuery);
	
	
	$(function() {

		$('#btnSave').click(function() {
			showCover();
			var html = $("#columnFrame").contents().find("#datatable").formhtml();
			$("#iframhtml").html('').html(html);
			$("#columnsForm").submit();
		});
		
		$('#tableSelect').change(function() {
			var tableName=$("#tableSelect").val();
			$("#columnFrame").attr(
				"src",
				"../tm/instanceSettingAction.do?method=columnsInit&tableName="
						+ tableName);		
		});
		
	})
	
</script>

	<%
		List tableList=(List)request.getAttribute("tableList");
		
	 %>
</head>

<body>
	<form id="columnsForm" action="../tm/ctxdAction.do?method=addInstance"
		method="post">
		<span style="display:none"></span>
		<div id="contentWrap">
			<div class="pageTitle"></div>
			<div class="pageColumn">
				<div class="qryCondition">
					<table style="height:50px;margin-bottom:6px">
						<tr>
							<td width="50px"></td>
							<td width='200px'>
								<label for="tableSelect">表名：</label>
								 <select class='shortselect' id="tableSelect" name="tableSelect">
								 	<option value="">请选择</option>
								 	<%
										if(tableList!=null){
											for(int i=0;i<tableList.size();i++){
												DTableDescSVO table=(DTableDescSVO)tableList.get(i);
												out.println("<option value='" +table.getTableName()+"'>"+table.getTableDesc()+"</option>");
											}
										}								 	
								 	 %>
								 </select>
							</td>
							 <td style="float:left;line-height:50px">
							 	<label for="instanceName"  style="margin-left:50px">实例名称：</label>
							 	<input type="text"  class="shottext"  name="instanceName" id="instanceName" size=80/>
							 </td>
							<td>
								<input type="button" class="btn" style="width:100px;height:28px" id="btnSave" value="保  存" />
							</td>
						</tr>
					</table>
				</div>
				<div id="datadiv" style="width:100%;height:400px;overflow: auto;">
				<iframe width="100%" scrolling="auto" height="100%"
					frameborder="false" allowtransparency="true"
					style="border: medium none;"
					src="../tm/instanceSettingAction.do?method=columnsInit"
					id="columnFrame" name="columnFrame"></iframe>
				</div>
				<span id="iframhtml"></span>
			</div>
		</div>
	</form>
</body>
</html>
