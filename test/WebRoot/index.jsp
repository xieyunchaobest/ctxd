<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
    <form action="http://60.31.254.52:8080/ctxd/tm/ctxdAction.do?method=login" id="ctxdForm"  target="_blank">
    	<input type="hidden" name="frompage" id="frompage" value="oa"/>
    	<input type="hidden" name="erpno" id="empNo" value="9999"/>
    	<!-- <input type="hidden" name="method" id="loginMethod" value="login" /> -->
    	
    </form>
    <a href="javascript:var aform=document.getElementById('ctxdForm'); aform.method='post';aform.submit();">数据分析系统</a>
  </body>
</html>
