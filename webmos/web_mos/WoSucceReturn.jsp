<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<HTML>
	<HEAD>
		<TITLE>正常回单</TITLE>
		<base target="_self" />	
		<link href="../css/tabdisplaystyle.css" rel="stylesheet"
			type="text/css">
		<style type="text/css">
</style>
<% String soCat = (String)request.getAttribute("soCat"); %>
		<script type="text/javascript" language="javascript"
			src="../js/sorttable.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/changecol.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/calendarTime.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/public.js"></script>
		<script type="text/javascript" language="javascript"
			src="../js/prototype.js"></script>

		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	</HEAD>
	<script>
	<logic:notEmpty name="failMsg" scope="request">
		alert('<bean:write name="failMsg" />');
	</logic:notEmpty> 
</script>
	<BODY onload="autoChangeHeight('I1')";>
		<html:form styleId="succForm" action="/tm/WoHandleAction.do?method=woReturn&returnType=0" method="post" >
		<table id="I1" name="I1" width="100%" align="left">
			<tr>
				<td>
					<fieldset>
						<legend>
							正常回单 
							<html:hidden property ="returnType"/>
							<html:hidden property="woNbrAryStr" />
							<logic:notEmpty name="WoHandleForm" property="workAreaTypeId" scope="request" >
							  <html:hidden name="WoHandleForm" property="workAreaTypeId"/>
							</logic:notEmpty> 
							<logic:notEmpty name="WoHandleForm" property="noMaintAreaWonbr" scope="request" >
							  <html:hidden name="WoHandleForm" property="noMaintAreaWonbr"/> 
							</logic:notEmpty>
							<input type="hidden" name="woDesignId" value="<bean:write name='WoHandleForm' property='woDesignId'/>"/>
							<input type="hidden" name="designRemarks" value="<bean:write name='WoHandleForm' property='designRemarks'/>"/>
						</legend> 
						<html:hidden name="WoHandleForm" property="mainWoStr" />
						<html:hidden name="WoHandleForm" property="routerMsg"/>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td bgcolor="9FAAB5">
									<table width="100%" cellspacing="1">

										<tr bgcolor="#E5E8EC">
											<td width="29%" height="20" align="right">
												定单号码：
											</td>
											<td width="71%" colspan="2" bgcolor="E5E8EC" title="<bean:write name="WoHandleForm" property="soNbr" />">
												<div STYLE="width:200;overflow:hidden;text-overflow:ellipsis">
													<nobr>
														<bean:write name="WoHandleForm" property="soNbr"/>
													</nobr>
												</div>
											</td>
										</tr>
										
										<logic:notEmpty name="WoHandleForm"
											property="overtimeIdList" scope="request">
											<tr bgcolor="#E5E8EC">
												<td align="right" >
													超时原因：
												</td>
												<td colspan="2">
													<html:select name="WoHandleForm" property="overtimeId"
														style='width:180'>
														<logic:present name="WoHandleForm"
															property="overtimeIdList">
															<html:optionsCollection name="WoHandleForm"
																property="overtimeIdList" />
														</logic:present>
													</html:select>
												</td>
											</tr>
										</logic:notEmpty>
										<logic:equal name="WoHandleForm" property="stepWoStaffConfig" value="D">
											<input type="hidden" name="retBy" value="retByCurrent" checked>当前登陆员工<br>
										</logic:equal>
										<!-- 施工人多选（按工区+按包区） -->
										<logic:equal name="WoHandleForm" property="stepWoStaffConfig" value="S">
										<tr bgcolor="#E5E8EC">
											<td align="right" valign="top">
												施&nbsp;&nbsp;工&nbsp;&nbsp;人：
											</td>
											<td>
											<logic:notEmpty name="WoHandleForm" property="maintAreaStaffList">
												<input type="radio" name="retBy" value="retByCurrent" >当前登陆员工<br>
											</logic:notEmpty>
											<logic:empty name="WoHandleForm" property="maintAreaStaffList">
												<input type="radio" name="retBy" value="retByCurrent" checked>当前登陆员工<br>
											</logic:empty>
												<input type="radio" name="retBy" value="retByWorkAreaStaff" >按工区选
													<html:select name="WoHandleForm" property="workAreaStaffId" style='width:100'>
														<logic:present name="WoHandleForm" property="workAreaStaffList">
															<html:optionsCollection name="WoHandleForm" property="workAreaStaffList" />
														</logic:present>
													</html:select>  
													<br>
												 <logic:notEmpty name="WoHandleForm" property="maintAreaStaffList">
													<input type="radio" name="retBy" value="retByMaintAreaStaff" checked>按包区选
													<html:select name="WoHandleForm" property="maintAreaStaffId" style='width:100'>
														<logic:present name="WoHandleForm" property="maintAreaStaffList">
															<html:optionsCollection name="WoHandleForm" property="maintAreaStaffList" label="name" value="staffId" />
														</logic:present>
													</html:select>  
												</logic:notEmpty>
											</td>
										</tr>
										</logic:equal>
										<!-- 施工人多选（按工区） -->
										<logic:equal name="WoHandleForm" property="stepWoStaffConfig" value="W">
										<tr bgcolor="#E5E8EC">
											<td align="right" valign="top">
												施&nbsp;&nbsp;工&nbsp;&nbsp;人：
											</td>
											<td>
											<logic:notEmpty name="WoHandleForm" property="workAreaStaffList">
												<input type="radio" name="retBy" value="retByCurrent" >当前登陆员工<br>
											</logic:notEmpty>
											<logic:empty name="WoHandleForm" property="workAreaStaffList">
												<input type="radio" name="retBy" value="retByCurrent" checked>当前登陆员工<br>
											</logic:empty>
												<input type="radio" name="retBy" value="retByWorkAreaStaff" checked>按工区选
													<html:select name="WoHandleForm" property="workAreaStaffId" style='width:100'>
														<logic:present name="WoHandleForm" property="workAreaStaffList">
															<html:optionsCollection name="WoHandleForm" property="workAreaStaffList" />
														</logic:present>
													</html:select>  
											</td>
										</tr>
										</logic:equal>
										<!-- 施工人多选（按包区） -->
										<logic:equal name="WoHandleForm" property="stepWoStaffConfig" value="M">
										<tr bgcolor="#E5E8EC">
											<td align="right" valign="top">
												施&nbsp;&nbsp;工&nbsp;&nbsp;人：
											</td>
											<td>
											<logic:notEmpty name="WoHandleForm" property="maintAreaStaffList">
												<input type="radio" name="retBy" value="retByCurrent" >当前登陆员工<br>
											</logic:notEmpty>
											<logic:empty name="WoHandleForm" property="maintAreaStaffList">
												<input type="radio" name="retBy" value="retByCurrent" checked>当前登陆员工<br>
											</logic:empty>

												 <logic:notEmpty name="WoHandleForm" property="maintAreaStaffList">
													<input type="radio" name="retBy" value="retByMaintAreaStaff" checked>按包区选
													<html:select name="WoHandleForm" property="maintAreaStaffId" style='width:100'>
														<logic:present name="WoHandleForm" property="maintAreaStaffList">
															<html:optionsCollection name="WoHandleForm" property="maintAreaStaffList" label="name" value="staffId" />
														</logic:present>
													</html:select>  
												</logic:notEmpty>
											</td>
										</tr>
										</logic:equal>
										<tr bgcolor="#E5E8EC">
											<td align="right" valign="top">
												备注：
												<label></label>
											</td>
											<td>
												<logic:notEmpty name="WoHandleForm"	property="overtimeIdList" scope="request">
													<html:textarea property="remarks"  rows="6" style="overflow:auto" cols="32"/>
												</logic:notEmpty>
												<logic:empty name="WoHandleForm"	property="overtimeIdList" scope="request">
													<html:textarea property="remarks"  rows="8" style="overflow:auto" cols="32"/>
												</logic:empty>
											</td>
										</tr>
									<logic:present name="WoHandleForm" property="woNotAllowList">
										<logic:notEmpty name="WoHandleForm" property="woNotAllowList">
										<tr bgcolor="#E5E8EC">
											<td align="right" valign="top">
													无法回单：
													<label></label>
											</td>
											<td>
												<div style="overflow:auto;width:100%;height:96;" id="listDiv">
										
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
												<td bgcolor="9FAAB5">
												<table width="100%" cellspacing="1">
												<tr bgcolor="#E5E8EC">
												<td width="20%" align="center">定单号</td>
												<td width="20%" align="center">工单号</td>
												<td width="60%" align="center">无法回单原因</td>
												</tr>
												<logic:iterate id="vo" name="WoHandleForm" property="woNotAllowList">
												<tr bgcolor="#E5E8EC">
													<td width="20%" align="left">
														<bean:write name="vo" property="soNbr"/>
                                           			 </td>
													<td width="20%" align="left">
														<bean:write name="vo" property="woNbr" />
													</td>
													<td width="60%" align="left">
														<bean:write name="vo" property="remarks" />
													</td>
												</tr>		
												</logic:iterate>
												</table>
												</td>
												</tr>
												
												</table>
												</div>
											</td>
										</tr>
										</logic:notEmpty>
										</logic:present>
										
										<logic:present name="WoHandleForm" property="woAllowList">
											<logic:notEmpty name="WoHandleForm" property="woAllowList">
												<logic:iterate id="vo" name="WoHandleForm" property="woAllowList">
												<tr>
													<td width="20%" align="center">
														<input type="hidden" name="woAry"
															value='<bean:write name="vo" property="woNbr" />'>
													</td>
												</tr>		
												</logic:iterate>								
       										</logic:notEmpty>
										</logic:present>
									</table>
								</td>
							</tr>
						</table>
					</fieldset>

					<table width="98%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="2" bgcolor="#FFFFFF"></td>
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
	</BODY>
</HTML>
<script language="javascript">
 function closeWindow(){
        parent.window.returnValue = "yes";
		window.close();
		return false;
	}
	
	 function showFail(){
		tr.style.display="block"
	}
	function unshowFail(){
		tr.style.display="none"
	}

	//回填信息
	function ok_close() {   
	if (document.all("noMaintAreaWonbr")!=null && document.all("noMaintAreaWonbr").value!=null && document.all("noMaintAreaWonbr").value.length>0){
	  if(!confirm(document.all("noMaintAreaWonbr").value+"维护区为空，是否继续回单？")){
	     return false;
	  }	  
	}

  	if(document.all("remarks") == null) {
    	return false;
  	}
  	
 	var remarks = Trim(document.all("remarks").value);
	
	if (remarks.length > 1024)
	{
		alert("信息过长，请精简信息内容，限制字数为1024"); 
		return false;
	}
	
    var returnType = document.getElementById("returnType").value;
	//if (!confirm("您确定超时原因为 ：\n" + failReason ))
	//{
	//	return false;
	//}
	
	var sureBtn = document.getElementById("sureBtn");
	sureBtn.disabled = true;
	
    document.succForm.action="WoHandleAction.do?method=woReturn&returnType="+0+"&soCat="+<%=soCat%>+"&remarks="+remarks; 
    document.succForm.submit();
    
    //parent.window.returnValue = "no";

   // window.dialogArguments.document.forms[0].remarks.value=remarks;
    //window.dialogArguments.document.forms[0].overtimeId.value=document.all("failReasonId").value;
	
	 //window.close();
	
	// return false;
	}
	


</script>
