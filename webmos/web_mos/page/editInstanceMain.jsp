<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.cattsoft.tm.vo.QueryInstanceSVO"%>
<%@page import="com.cattsoft.tm.vo.SysDataPrivSVO"%>
<%@page import="com.cattsoft.tm.vo.FuncMenuSVO"%>
<%@page import="com.cattsoft.tm.vo.FuncNodeTreeSVO"%>
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
			var flag=validate();
			if(flag==false)return ;
			showCover();
			var html = $("#columnFrame").contents().find("#datatable").formhtml();
			$("#iframhtml").html('').html(html);
			$("#columnsForm").submit();
		});
		
		$('#tableName').change(function() {
			var tableName=$("#tableName").val();
			var typeFlag=$("#typeFlag").val();
			$("#columnFrame").attr(
				"src",
				"../tm/instanceSettingAction.do?method=columnsInit&tableName="
						+ tableName+"&typeFlag="+typeFlag);		
		});
		
	})
	
	function validate(){
		var typeFlag=$("#typeFlag").val();
		if($("tableName").val()==''){
			alert('请选择表名！');
			return false;
		}
		
		if($("#instanceName").val()==""){
			alert('请填写实例名称！');
			return false;
		}
		
		if($("#treeId").val()==''){
			alert('请选择类别！');
			return false;
		}
		
		
		if(typeFlag=="C"){
			var isShowFlag=false;
			$("#columnFrame").contents().find("[id^='chkIsShow']").each(function(i) {
				  	//alert($(this).prop('checked'));
				  	if($(this).prop('checked')==true){
				  		isShowFlag=true;
				  	} 
			});
			if(isShowFlag==false){
			 alert('请选择显示列！');
			 return false;	
			}	
		}else if(typeFlag=="S"){
			var isGroupFlag=false;
			$("#columnFrame").contents().find("[id^='chkIsGroup']").each(function(i) {
				  	//alert($(this).prop('checked'));
				  	if($(this).prop('checked')==true){
				  		isGroupFlag=true;
				  	} 
			});
			if(isGroupFlag==false){
			 alert('请选择分组项！');
			 return false;	
			}	
			
			
			var isSumFlag=false;
			$("#columnFrame").contents().find("[id^='chkIsSum']").each(function(i) {
				  	//alert($(this).prop('checked'));
				  	if($(this).prop('checked')==true){
				  		isSumFlag=true;
				  	} 
			});
			if(isSumFlag==false){
			 alert('请选择汇总项！');
			 return false;	
			}	
			
		}
		//查询条件
		var isConditionFlag=false;
			$("#columnFrame").contents().find("[id^='chkIsCondition']").each(function(i) {
				  	//alert($(this).prop('checked'));
				  	if($(this).prop('checked')==true){
				  		isConditionFlag=true;
				  	} 
			});
			if(isConditionFlag==false){
			 alert('请选择查询条件!');
			 return false;	
			}	
		
		
		var flag=true;
		$("#columnFrame").contents().find("[id^='chkIsCondition']").each(function(i) {
				var trn=$(this).parent().parent().prevAll().length;
			  	var n=parseInt(trn)-1;
			  	//alert($(this).prop('checked'));
			  	if($(this).prop('checked')==true){
			  		var conditonTypeVal=$("#columnFrame").contents().find("#sltConditionType"+n).val();
			  		if(conditonTypeVal==''){
			  			alert('请选择条件类型');
			  			flag=false;
			  			return false;
			  		}
			  	} 
		});
		if(flag==false) return false;	
		
	}
	
	$(function(){
		var menudesc=$('#menudesc', window.parent.document).val();
		$(".pageTitle").html(menudesc);
})
	
</script>

	<%
		List tableList=(List)request.getAttribute("tableList");
		List instanceTypeList=(List)request.getAttribute("instanceTypeList");
		List dataPrivList=(List)request.getAttribute("dataPrivList");
		String typeFlag=(String)request.getAttribute("typeFlag");
		QueryInstanceSVO instance=(QueryInstanceSVO)request.getAttribute("instance");
		QueryInstanceSVO inst=(QueryInstanceSVO)request.getAttribute("inst");
		String treeId=instance.getTreeId();
		String instanceId=instance.getQueryInstanceId();
		//String tableDesc=inst.getTableDesc();
	 %>
</head>

<body>
	<form id="columnsForm" action="../tm/instanceSettingAction.do?method=addInstance"
		method="post">
		<span style="display:none">
			<% out.println("<input type='hidden' name='typeFlag' id='typeFlag' value='"+typeFlag+"' />");  %>
		</span>
		<div id="contentWrap">
			<div class="pageTitle"></div>
			<div class="pageColumn">
				<div class="qryCondition">
					<table style="height:50px;margin-bottom:6px">
						<tr>
							<td width="50px"></td>
							<td width='200px'>
								<label>表名：</label>
								<label>
									<bean:write name="inst" property="tableDesc"/>
								</label>
							</td>
							 <td style="float:left;line-height:50px">
							 	<label style="margin-left:50px">实例名称：</label>
							 	<label><input type="text" name="instanceName" class="shottext" value="<bean:write name='instance' property='instanceName' />"/></label>
							 </td>
							 <td style="float:left;line-height:50px">
								<label for="treeId" style="margin-left:50px">类别：</label>
								 <select class='shortselect' id="treeId" name="treeId">
								 	<option value="">请选择</option>
								 	<%
										if(instanceTypeList!=null){
											for(int i=0;i<instanceTypeList.size();i++){
												FuncMenuSVO tree=(FuncMenuSVO)instanceTypeList.get(i);
												String atreeId=tree.getFuncMenuId();
												if(atreeId.equals(treeId)){
													out.println("<option selected  value='" +atreeId+"'>"+tree.getFuncMenuName()+"</option>");
												}else{
													out.println("<option value='" +atreeId+"'>"+tree.getFuncMenuName()+"</option>");
												}
												
											}
										}								 	
								 	 %>
								 </select>
							</td>
							<td style="float:left;line-height:50px">
								<label for="dataPriv" style="margin-left:50px">数据权限：</label>
								 <select class='shortselect' id="dataPriv" name="dataPriv">
								 	<option value="">请选择</option>
								 	<%
								 		String dataPriv=instance.getDataPriv();
										if(dataPrivList!=null){
											for(int i=0;i<dataPrivList.size();i++){
												SysDataPrivSVO priv=(SysDataPrivSVO)dataPrivList.get(i);
												if(priv.getSysDataPrivId().equals(dataPriv)){
													out.println("<option selected value='" +priv.getSysDataPrivId()+"' >"+priv.getSysDataPrivName()+"</option>");
												}else{
													out.println("<option value='" +priv.getSysDataPrivId()+"' >"+priv.getSysDataPrivName()+"</option>");
												}
												
											}
										}								 	
								 	 %>
								 </select>
							</td>
							<td>
								<input type="button" class="sbtn" id="btnSave" value="保  存" />
							</td>
						</tr>
					</table>
				</div>
				<div id="datadiv" style="width:100%;height:400px;overflow: auto;">
				<iframe width="100%" scrolling="auto" height="100%"
					frameborder="false" allowtransparency="true"
					style="border: medium none;"
					src="../tm/instanceSettingAction.do?method=editInstanceColumnsInit&instanceId=<%=instanceId%>&typeFlag=<%=typeFlag%>"
					id="columnFrame" name="columnFrame"></iframe>
				</div>
				<span id="iframhtml" style="display:none">
				</span>
				<span style="display:none">
					<input type="hidden" name='tableName'  value='<bean:write name="instance" property="tableName"/>' />
					<input type="hidden" name='instanceId'  value='<bean:write name="instance" property="queryInstanceId"/>' />
				</span>
			</div>
		</div>
	</form>
</body>
</html>
