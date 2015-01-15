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
<title>×Ö¶Î×¢ÊÍ</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>

<style type="text/css">
body {
	background: #FFF
}
.shottext {
	background: #fafdfe;
	height: 20px;
	width: 150px;
	line-height: 20px;
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
	$(function() {
	})
</script>

</head>

<body>
	<form id="queryForm" action="../tm/ctxdAction.do?method=queryResult"
		method="post">
		<span style="display:none"><input type="hidden"
			value='<%=request.getAttribute("tableId")%>' name="tableId" />
		</span>
		<div id="contentWrap">
			<div class="pageColumn">
				<div class="qryCondition">
					<table style="height:50px;margin-bottom:6px" id="tShowColumn">
						<thead>
							<th style="width:100px">
								×Ö¶ÎÃû
							</th>
							<th style="width:100px">
								Ãû³Æ
							</th>
						</thead>
						<logic:iterate id="column" name="columnDescList" >
							<tr>
								<td class="ctd">
									<bean:write name="column" property="columnName"/>
								</td>
								<td class="ctd">
									<input type="text" class='shottext' name="columnDesc" value="<bean:write name='column' property='columnDesc'/>" />
								</td>
							</tr>
						</logic:iterate>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
