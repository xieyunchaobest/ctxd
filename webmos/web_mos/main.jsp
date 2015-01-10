<%@ page contentType="text/html; charset=gb2312"%>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>欢迎使用MOS掌上运维系统</title>
	<script type="text/javascript">
	function talkToIOM(){
		var msg = document.getElementById("msg").value;
		if(msg == null || msg=="" || msg == undefined){
			alert("请输入文本！");
			return false;
		}
		testForm.action="InterAction.do?method=talkToIOM&msgToIOM=" + msg;
		testForm.submit();
	}
	</script>
  </head>
  <body>
  <form id="testForm" action="" method="post">
  <div style="height: 50px;"></div>
   <div align="center">
   		<div>
   			<input type="text" id="msg" value="<%=request.getAttribute("msgToIOM")%>"/>
   			<input type="button" value="与IOM联通" onclick="talkToIOM()"/>
   		</div>   		
   		<div style="color: blue;">
   			返回消息为：<label><%=request.getAttribute("IOMToMOS")%></label>
   		</div>
   		
   </div>
  </form>
  </body>
</html>
