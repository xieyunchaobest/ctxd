<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title></title>
<link href="../css/tabdisplaystyle.css" rel="stylesheet" type="text/css">

<style type="text/css"></style>
<script>
	<logic:notEmpty name="failMsg" scope="request">
		alert('<bean:write name="failMsg" />');
	</logic:notEmpty>
	
function closeWin(){
	window.returnValue='success';
     //alert('ok');
     window.close();
}
     
</script>
</head>

<body onload="closeWin()">
	<logic:notEmpty name="failMsg" scope="request">
		<bean:write name="failMsg" />;
	</logic:notEmpty>
</body>
</html>
