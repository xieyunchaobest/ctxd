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
<title>字段注释</title>
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
	
	function ckQueryCondition(obj){
		if($(obj).attr('checked')=='checked'){
			var trn=$(obj).parent().parent().prevAll().length;
			$("input:radio[name='conditionType"+trn+"']:first").attr('checked',false);
			$("input:radio[name='conditionType"+trn+"']:last").attr('checked',false);
			//$("input:radio[name='conditionType"+trn+"']:first").removeAttr('checked');
			//$(obj).attr('checked',false);
		}else{
			var trn=$(obj).parent().parent().prevAll().length;  
			$("input:radio[name='conditionType"+trn+"']:first").attr('checked','checked');
			$(obj).attr('checked','checked');
		}
		
	}
	
	function ckConditionType(obj){
		if(typeof($(obj).attr('checked'))== "undefined" ){	
			var trn=$(obj).parent().parent().prevAll().length;  
			//$("[name='isQueryCondition'"+trn+"'']").attr("checked",'true');
			$("input:checkbox[name='isQueryCondition"+trn+"']:first").attr('checked','checked');
			//$(obj).attr('checked','checked');
		}else{
			//$(obj).attr('checked',false);
			$("input:checkbox[name='isQueryCondition"+trn+"']:first").attr('checked','false');
		}
		
	}
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
					<%
				List columnDescList=(List)request.getAttribute("columnDescList");
				if(columnDescList!=null && columnDescList.size()>0){
					out.print("<input type='hidden' name='columnCount' value='"+columnDescList.size()+"'");
				}
			 %>
						<thead>
							<th style="width:100px">
								字段名
							</th>
							<th style="width:100px">
								名称
							</th>
							<th style="width:50px">
								是否展示
							</th>
							<th style="width:50px">
								查询条件
							</th>
							<th style="width:100px">
								条件类型
							</th>
						</thead>
						<%int i=0; %>
						<logic:iterate id="column" name="columnDescList" indexId="number" >
							<% i++;%>
							<tr>
								<td class="ctd">
									<input type="hidden"  name="columnName<%=i%>" value="<bean:write name='column' property='columnName'/>" />
									<input type="hidden"  name="dataType<%=i%>" value="<bean:write name='column' property='dataType'/>" />
									<bean:write name="column" property="columnName"/>
								</td>
								<td class="ctd">
									<input type="text" class='shottext' name="columnDesc<%=i%>" value="<bean:write name='column' property='columnDesc'/>" />
								</td>
								<td  class="ctd">&nbsp;
									<input type="checkbox" name="isShow<%=i%>"  value="Y"
										<logic:equal name="column"  property="isShow" value="Y">checked</logic:equal>
									/>
								</td>
								<td  class="ctd">&nbsp;
									<input type="checkbox" name="isQueryCondition<%=i%>" value="Y" onclick="ckQueryCondition(this);"
										<logic:equal name="column"  property="isQueryCondition" value="Y">checked</logic:equal>
									/>
								</td>
								<td  class="ctd">&nbsp;
									<input type="radio" name="conditionType<%=i%>" value="S" onclick="ckConditionType(this)"
										<logic:equal name="column"  property="conditionType" value="S">checked</logic:equal>
									/>单值
									<input type="radio" name="conditionType<%=i%>" value="M" onclick="ckConditionType(this)"
										<logic:equal name="column"  property="conditionType" value="M">checked</logic:equal>
									/>多值
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
