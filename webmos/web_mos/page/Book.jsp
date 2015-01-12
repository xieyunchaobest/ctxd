<%@ page language="java" contentType="text/html;charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pagination.tld" prefix="pag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

	<title>预约申请/修改</title>
	<base target="_self">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../css/tabdisplaystyle.css" rel="stylesheet" type="text/css">
	<link href="../css/style.css" rel="stylesheet" type="text/css">
	<style type="text/css">
	<!--
		body {
			background-color: #fbfbfb;
		}
	-->
	</style>
	
	<script type="text/javascript" language="javascript" src="../js/relatedSelect.js"></script>
	<script type="text/javascript" language="javascript" src="../js/prototype.js"></script>
	<script type="text/javascript" language="javascript" src="../js/public.js"></script>
	<script type="text/javascript" language="javascript" src="../js/calendarTime.js"></script>
	<script type="text/javascript">
		function closeWindow(){
	        parent.window.returnValue = "yes";
			window.close();
			return false;
		}
		
		function showFailReason(obj){
			if(obj.value == "-1"){
				document.getElementById("bookFailReason").style.display="block";
			}else{
				document.getElementById("bookFailReason").style.display="none";
			}
		}
		
		function ok_close(){
			var bookTime = $("bookTime").value;
			if(bookTime == '' || bookTime == undefined || bookTime == null){
				alert("上门时间不能为空！");
				return false;
			}	
			
			var createDate = $("createDate").value;
			if(createDate == '' || createDate == undefined || createDate == null){
				alert("预约时间不能为空！");
				return false;
			}	
			
			document.succForm.action="SoBookAction.do?method=book"; 
    		document.succForm.submit();
    		return true;
		}
	</script>

</head>

<body>

<html:form styleId="succForm" action="SoBookAction.do?method=book" method="post">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
	<td align="left" valign="top">
		<table width="100%" >
			<tr align="left">
				<td>
					<fieldset>
						<legend>预约申请</legend>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td bgcolor="9FAAB5" valign="top">
									<table width="100%" cellspacing="1">
										<tr bgcolor="E5E8EC">
											<td align="right" width="29%" height="20" >内部单号：</td>
											<td width="71%">
												<html:text name="SoBookForm" property="soNbr" readonly="true" style="border:0px;background-color:#E5E8EC"/>
												<html:hidden  name="SoBookForm" property="bookStepId" />
											</td>
										</tr>
										<tr bgcolor="E5E8EC">
											<td align="right" width="29%" height="20" >上门时间：</td>
											<td width="71%">
												<html:text property="bookTime" onclick="Calendar.display(Calendar.DATE)"></html:text>
											</td>
										</tr>
										<tr bgcolor="E5E8EC">
											<td align="right" width="29%" height="20" >预约时间：</td>
											<td width="71%">
												<html:text property="createDate" onclick="Calendar.display(Calendar.DATE)"></html:text>
											</td>
										</tr>
										<tr bgcolor="E5E8EC">
											<td align="right" width="29%" height="20" >预&nbsp;&nbsp;约&nbsp;&nbsp;人：</td>
											<td width="71%">
												<html:text name="SoBookForm" property="staffName" readonly="true" style="border:0px;background-color:#E5E8EC"/>
												<html:hidden name="SoBookForm" property="vo.staffId"/>
											</td>
										</tr>
										<tr bgcolor="E5E8EC">
											<td align="right" width="29%" height="20" >是否预约成功：</td>
											<td width="71%">
												<label for="isSuccIdY">
													<!-- <input type="radio" name="isSuccess" value="0" onclick="showFailReason(this);" id="isSuccIdY"/>是 -->
													<html:radio property="bookIsOK" value="0" onclick="showFailReason(this);" styleId="isSuccIdY" bundle="checked">是</html:radio>
												</label>&nbsp;&nbsp;
												<label for="isSuccIdN">
													<!--<input type="radio" name="isSuccess" value="-1" onclick="showFailReason(this);" id="isSuccIdN"/>否-->
													<html:radio property="bookIsOK" value="-1" onclick="showFailReason(this);" styleId="isSuccIdN">否</html:radio>
												</label>
											</td>
										</tr>
										<tr bgcolor="E5E8EC" style="display: none" id="bookFailReason">
											<td align="right" width="29%" height="20" >预约失败原因：</td>
											<td width="71%">
												<html:select name="SoBookForm" property="vo.changeReason">
													<html:optionsCollection name="SoBookForm" property="changeReasonList"/>
												</html:select>
											</td>
										</tr>
										<tr bgcolor="E5E8EC">
											<td align="right" width="29%" height="20" >备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
											<td width="71%">
												<html:textarea name="SoBookForm" property="vo.remarks" rows="3" cols="50"></html:textarea>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</fieldset>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td bgcolor="#ADB7C0">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="28" align="right" bgcolor="#C8CFD9">
								<input type="button" name="confirm"
									onclick="return ok_close();" value="确 定" class="button2" id="sureBtn">
								<input name="Submit32" type="submit" class="button2"
									onclick='return closeWindow();' value="取 消">
							</td>
							<td width="2%" align="right" bgcolor="#C8CFD9">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		
	</td>
</tr>

</table>

</html:form>
</body>
</html>
