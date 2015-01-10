<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.cattsoft.sm.vo.*"%>
<%@ page import="com.cattsoft.pub.util.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>osstop</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
		<style type="text/css">
		    .otherSys{
		        color:#000000;
		        width:172px;
		        height:36px;
		        border:0px solid #7D8B99;
		        padding-top:14px;
		        padding-left:55px;
		        font-size:15px;
		        font-family: "微软雅黑,宋体";
		        margin-top:19px;
		        margin-left:-14px;
		        margin-right:-6px;
		        cursor: hand;
		        font-weight:bolder;
				float:center;
				background-image:url('images/top_sys_bg1.png');
		    }
			
		</style>
		<%
			java.text.SimpleDateFormat sDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
			String sysUserName =(String)request.getSession().getAttribute("sysUserName");
		%>
	</head>
	<body>
	   <form id="topForm" target="_blank" method="post">
		<table width="100%" height="55" border="0" cellpadding="0"
			cellspacing="0" background="images/topFrame_bg.gif" id="table1">
			<tr align="left" valign="top" id="tr1">		
				<td width="210" height="55" align="left" valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" >
						<tr>
							<td>&nbsp;</td>
							<td><img src="images/pcss/head_logon.gif" width="100" height="56"></td>
							<td><img src="images/pcss/head_bg_1n.gif" width="161" height="56"></td>
						</tr>
					</table>
				</td>
				
				<td width="700" align="left" valign="top" >&nbsp;</td>
				<td width="270" align="right">
				    <img src="images/union_logo_14.png" width="270" height="55px" >
				</td>
			</tr>
		</table> 
		<div id ="table2Div" >
		<table width="100%" height="25" border="0" cellpadding="0"
			cellspacing="0" background="images/body_menu_bg5.gif" id ="table2" style="margin-top: -2px;" align="right">
			<tr align="left">
				<td width="50%" align="left">
					<span style="font-weight:bold;font-size:13px;color:white;">
					&nbsp;&nbsp;&nbsp;日期时间：[<%=sDateFormat.format(new Date(System.currentTimeMillis()))%>] &nbsp; | &nbsp;
					员工：[<%=sysUserName%>]</span>
				</td>
				<td width="50%" align="right">
					<span style="margin-top:3px;vertical-align:middle;"><img src="images/person.gif" width="16" height="14"></span>
					<span><a href="login.do?method=logout&entryMode=ossMode" target="_parent">
						<strong style="color: #ffffff">[退出] </strong>
					</a></span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
		</div>
		</form>
	</body>
</html>
<script language="javascript">
if(parent.parent.whole.rows=="25,*,20")
	{  table1.style.display="none";
	}else{ 
	   table1.style.display="";
	}	
</script>
