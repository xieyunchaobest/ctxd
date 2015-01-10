<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.cattsoft.sm.vo.*"%>
<%@ page import="com.cattsoft.pub.util.*"%>
<%@ page import="java.util.*"%>
<!-- Development Note -->
<!--===========================================-->
<!-- 河北网通综合客户服务系统-->
<!-- 系统主操作界面main Frame页面文件 -->
<!-- Copyright @ CATTSoft All rights reserved. -->
<!-- 开始编写时间：2007-08-10 stt-->
<!----------------------------------------------->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>联通客户服务系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript"
			src="js/prototype.js"></script>
<script language="javascript">
function expand(id){
	id.style.display = id.style.display=="block"?"none":"block";
	
}

function turn(divVal,imgVal){
	var div = document.getElementsByName(divVal)[0];
	if(div.style.display == 'none'){
		div.style.display = 'block';
		document.getElementById(imgVal).src = 'images/table_right.jpg';
	}else{
		div.style.display = 'none';
		document.getElementById(imgVal).src = 'images/table_right_add.jpg';
	}
}

function init(){  
	document.getElementById("sgsDiv").style.display="none";
	document.getElementById("wfDiv").style.display="none";
    var myAjax = new Ajax.Request("tm/WoHandleAction.do?method=searchWO4Oss", {
                         method: 'get',
                         asynchronous:'true',//异步提交
                         onComplete: showSos
                         });
}

function showSos(transport){
	var xmlDoc=new ActiveXObject("Msxml2.DOMDocument");
	var res = transport.responseText;
	var count = res.split('@');
	document.getElementById("spsCount").innerText="("+count[0]+"条工单待处理)";
	res = count[1];
	if(res != null && res != ''){
		var sos = res.split("$");
		var table = document.getElementById("sps");
		for(var i = 0;i < sos.length;i++){
			var so = sos[i].split(",");
			var row = table.insertRow();
            var cell = row.insertCell();
            cell.style.width = '7%';
            cell.style.height = '20px';
            cell.style.textAlign = 'center';
            cell.style.backgroundColor = '#FFFFFF';
            cell.innerHTML = "<img src='images/icon.gif' width='6' height='6'>";
            cell = row.insertCell();
            cell.style.width = '45%';
            cell.style.backgroundColor = '#FFFFFF';
            cell.innerHTML = "<A href='#'>"+so[0]+"</A>";
            cell = row.insertCell();
            cell.style.width = '48%';
            cell.style.backgroundColor = '#FFFFFF';
            cell.innerText = so[3];
		}
		var more = table.insertRow();
		var moreCell = more.insertCell();
		moreCell.style.textAlign = 'center';
        moreCell.style.backgroundColor = '#FFFFFF';
		moreCell.innerText = "";
		moreCell = more.insertCell();
		moreCell.style.backgroundColor = '#FFFFFF';
		moreCell.innerText = "";
		moreCell = more.insertCell();
		moreCell.style.textAlign = 'right';
        moreCell.style.backgroundColor = '#FFFFFF';
		moreCell.innerHTML = "<a href='tm/WoHandleAction.do?method=initWo4Oss&pageNo=1&pagCount=10&pagSize=50&subSystemName=TM,,,'><img src='images/more.jpg' width='39' height='9'>";
	}
}

</script>
<style type="text/css">
<!--
.STYLE1 {
	color: #0000CC;
	font-weight: bold;
}
.STYLE2 {font-weight: bold}
-->
</style>
</head>

<body bgcolor="#ffffff" onload="init();">
<table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
<tr><td height="2"></td></tr>
  
  <tr>
    <td colspan="2" align="left"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="68%"><table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td valign="top" height="100%"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="28" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="9%" rowspan="2"><img src="images/table_bg_n3.gif" width="124" height="29"></td>
                          <td width="91%" height="8"></td>
                        </tr>
                        <tr>
                          <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td background="images/table_bg_n1.gif"><div align="right"><img src="images/table_bg_n2.gif" width="22" height="21"></div></td>
                              </tr>
                          </table></td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td valign="top"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="1" bgcolor="d5d5d5"></td>
                          <td valign="top" bgcolor="f8f8f8"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                              <tr>
                                <td valign="top"><div style="overflow:auto;height:200px;"><table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                                    <tr>
                                      <td>&nbsp;</td>
                                    </tr>
                                    <tr>
										<logic:present name="bulletinBs">
											<%int i = 0;%>
												<logic:iterate id="bulletinB" name="bulletinBs">
													<%i++;%>
														<tr>
															<td>
																				<p>
																					<br>
																					标题
																					<%=i%>
																					：
																					<bean:write name="bulletinB" property="title"
																						filter="false" />
																					<br>
																					内容：
																					<bean:write name="bulletinB" property="content"
																						filter="false" />
																					<logic:notEmpty name="bulletinB" property="attachMVO">
																					<br>
																					附件：
																					<a href="<%=request.getContextPath()%>/sm/attachmentAction.do?method=download&attachmentId=<bean:write name='bulletinB' property='attachMVO.attachmentId'/>"><bean:write name="bulletinB" property="attachMVO.fileName"/></a>
																					</logic:notEmpty>
																					<br>
																					发布时间：
																					<bean:write name="bulletinB" property="releaseDate" />
																					<br>
																				</p>
																			</td>
																		</tr>
														</logic:iterate>
													</logic:present>
												</tr>
                                  	</table></div>
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                      <tr>
                                        <td>&nbsp;</td>
                                      </tr>
                                      <tr>
                                        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                              <td width="9%" rowspan="2"><img src="images/table_bg_n4.gif" width="124" height="29"></td>
                                              <td width="91%" height="8"></td>
                                            </tr>
                                            <tr>
                                              <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                  <tr>
                                                    <td background="images/table_bg_n1.gif"><div align="right"><img src="images/table_bg_n2.gif" width="22" height="21"></div></td>
                                                  </tr>
                                              </table></td>
                                            </tr>
                                        </table></td>
                                      </tr>
                                      <tr>
                                        <td><div style="overflow:auto;height:expression(document.body.clientHeight-290);">
                                        	<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
															<tr>
																<td width="76%" valign="top">
																	<table width="85%" border="0" align="center"
																		cellpadding="0" cellspacing="0">
																		<tr align="left">
																			<td height="10"></td>
																		</tr>
																		<logic:present name="bulletinSs">

																			<%int j = 0;%>
																			<logic:iterate id="bulletinS" name="bulletinSs">
																			<%j++;%>
																				<tr align="left">
																					<td height="22" valign="bottom">
																						<p>
																							<br>
																							标题
																							<%=j%>
																							：
																							<bean:write name="bulletinS" property="title"
																								filter="false" />
																							<br>
																							内容：
																							<bean:write name="bulletinS" property="content"
																								filter="false" />
																							<logic:notEmpty name="bulletinS" property="attachMVO">
																							<br>
																							附件：
																							<a href="<%=request.getContextPath()%>/sm/attachmentAction.do?method=download&attachmentId=<bean:write name='bulletinS' property='attachMVO.attachmentId'/>"><bean:write name="bulletinS" property="attachMVO.fileName"/></a>
																							</logic:notEmpty>
																							<br>
																							发布时间：
																							<bean:write name="bulletinS" property="releaseDate" />
																							<br>
																						</p>
																					</td>
																				</tr>
																				<tr>
																					<td height="1" background="images/xuxian.gif"></td>
																				</tr>
																			</logic:iterate>
																		</logic:present>



																	</table>
																</td>
															</tr>
														</table></div></td>
                                      </tr>
                                  </table></td>
                              </tr>
                          </table></td>
                          <td width="1" bgcolor="d5d5d5"></td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="6"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="2%"><img src="images/right_bottom_01.gif" width="23" height="6"></td>
                          <td width="97%" height="6" background="images/right_bottom_bg.gif"></td>
                          <td width="1%"><img src="images/right_bottom_02.gif" width="14" height="6"></td>
                        </tr>
                    </table></td>
                  </tr>
              </table></td>
            </tr>
          </table></td>
          <td width="32%" valign="top"><table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td height="28"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="9%" rowspan="2"><img src="images/table_bg_n7.gif" width="124" height="29"></td>
                    <td width="91%" height="8"></td>
                  </tr>
                  <tr>
                    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td background="images/table_bg_n1.gif"><div align="right"><img src="images/table_bg_n2.gif" width="22" height="21"></div></td>
                        </tr>
                    </table></td>
                  </tr>
              </table></td>
            </tr>
									<tr>
										<td valign="top">
											<table width="100%" height="100%" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="1" bgcolor="d5d5d5"></td>
													<td valign="top" bgcolor="f8f8f8">
														<table width="100%" border="0" cellpadding="0"
															cellspacing="0">
															<tr>
																<td valign="top">
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0" height="100%">
																		<tr id="spsDiv">
																			<td align="center" valign="top">
																				<table width="100%" cellspacing="0" cellpadding="0">
																					<tr>
																						<td height="3"></td>
																					</tr>
																				</table><div>
																				<table width="95%" cellspacing="0" cellpadding="0">
																					<tr>
																						<td bgcolor="D5D3D6">
																							<table width="100%" border="0" cellpadding="0"
																								cellspacing="1">
																								<tr>
																									<td bgcolor="#FFFFFF">
																										<table width="100%" border="0" cellpadding="0"
																											cellspacing="0">
																											<tr>
																												<td height="20"
																													background="images/table_jianbian.jpg">
																													<table width="100%" border="0"
																														cellpadding="0" cellspacing="0">
																														<tr>
																															<td width="4%">
																																&nbsp;
																															</td>
																															<td width="80%" nowrap>
																																集成定单
																																<span class="STYLE1" id="spsCount">(0条工单待处理)</span>
																															</td>
																															<td width="16%">
																																<div align="center"><a href="javascript:turn('div1','img1');">
																																	<img id="img1" src="images/table_right.jpg"
																																		width="30" height="20"></a>
																																</div>
																															</td>
																														</tr>
																													</table>
																												</td>
																											</tr>
																										</table>
																									</td>
																								</tr>
																								<tr>
																									<td bgcolor="#FFFFFF"><div id="div1" style="display:block">
																										<table width="100%" border="0" align="center"
																											cellpadding="0" cellspacing="1" id="sps">
<%--																											<tr>--%>
<%--																												<td width="7%" height="20" align="center"--%>
<%--																													bgcolor="#FFFFFF">--%>
<%--																													<img src="images/icon.gif" width="6"--%>
<%--																														height="6">--%>
<%--																												</td>--%>
<%--																												<td width="45%" bgcolor="#FFFFFF">--%>
<%--																													<A href="#">191101080516S4100535</A>--%>
<%--																													<a href="table.htm" target="mainFrame"></a>--%>
<%--																												</td>--%>
<%--																												<td width="48%" bgcolor="#FFFFFF">--%>
<%--																													无线市话欠费拆机--%>
<%--																												</td>--%>
<%--																											</tr>--%>
<%--																											<tr>--%>
<%--																												<td height="20" align="center"--%>
<%--																													bgcolor="#FFFFFF">--%>
<%--																													<img src="images/icon.gif" width="6"--%>
<%--																														height="6">--%>
<%--																												</td>--%>
<%--																												<td bgcolor="#FFFFFF">--%>
<%--																													<A href="#">191101080516S2410531</A>--%>
<%--																													<a href="table.htm" target="mainFrame"></a>--%>
<%--																												</td>--%>
<%--																												<td bgcolor="#FFFFFF">--%>
<%--																													ADSL新装--%>
<%--																												</td>--%>
<%--																											</tr>--%>
<%--																											<tr>--%>
<%--																												<td height="20" align="center"--%>
<%--																													bgcolor="#FFFFFF">--%>
<%--																													<img src="images/icon.gif" width="6"--%>
<%--																														height="6">--%>
<%--																												</td>--%>
<%--																												<td bgcolor="#FFFFFF">--%>
<%--																													<A href="#">191101080516S2410532</A>--%>
<%--																													<a href="table.htm" target="mainFrame"></a>--%>
<%--																												</td>--%>
<%--																												<td bgcolor="#FFFFFF">--%>
<%--																													ADSL新装--%>
<%--																												</td>--%>
<%--																											</tr>--%>
<%--																											<tr>--%>
<%--																												<td align="center" bgcolor="#FFFFFF">--%>
<%--																													&nbsp;--%>
<%--																												</td>--%>
<%--																												<td bgcolor="#FFFFFF">--%>
<%--																													&nbsp;--%>
<%--																												</td>--%>
<%--																												<td align="right" bgcolor="#FFFFFF">--%>
<%--																													<a href="#"><img src="images/more.jpg"--%>
<%--																															width="39" height="9">--%>
<%--																													</a>--%>
<%--																												</td>--%>
<%--																											</tr>--%>
																										</table></div>
																									</td>
																								</tr>
																							</table>
																						</td>
																					</tr>
																				</table></div>
																			</td>
																		</tr>
																		<tr id="sgsDiv">
																			<td align="center" valign="top">
																				<div><table width="95%" cellspacing="0" cellpadding="0">
																					<tr>
																						<td bgcolor="D5D3D6">
																							<table width="100%" border="0" cellpadding="0"
																								cellspacing="1">
																								<tr>
																									<td bgcolor="#FFFFFF">
																										<table width="100%" border="0" cellpadding="0"
																											cellspacing="0">
																											<tr>
																												<td height="20"
																													background="images/table_jianbian.jpg">
																													<table width="100%" border="0"
																														cellpadding="0" cellspacing="0">
																														<tr>
																															<td width="4%">
																																&nbsp;
																															</td>
																															<td width="80%">
																																服务保障<span class="STYLE1">(12条工单等待处理)</span>
																															</td>
																															<td width="16%">
																																<div align="center"><a href="javascript:turn('div2','img2');">
																																	<img id="img2" src="images/table_right.jpg"
																																		width="30" height="20"></a>
																																</div>
																															</td>
																														</tr>
																													</table>
																												</td>
																											</tr>
																										</table>
																									</td>
																								</tr>
																								<tr>
																									<td bgcolor="#FFFFFF"><div id="div2" style="display:block">
																										<table width="100%" border="0" align="center"
																											cellpadding="0" cellspacing="1">
																											<tr>
																												<td width="7%" height="20" align="center"
																													bgcolor="#FFFFFF">
																													<img src="images/icon.gif" width="6"
																														height="6">
																												</td>
																												<td width="45%" bgcolor="#FFFFFF">
																													<A href="#">191231080516S4107815</A>
																													<a href="table.htm" target="mainFrame"></a>
																												</td>
																												<td width="48%" bgcolor="#FFFFFF">
																													交换设备故障
																												</td>
																											</tr>
																											<tr>
																												<td height="20" align="center"
																													bgcolor="#FFFFFF">
																													<img src="images/icon.gif" width="6"
																														height="6">
																												</td>
																												<td bgcolor="#FFFFFF">
																													<A href="#">191121080516S4642935</A>
																													<a href="table.htm" target="mainFrame"></a>
																												</td>
																												<td bgcolor="#FFFFFF">
																													河西水域南岸停电
																												</td>
																											</tr>
																											<tr>
																												<td align="center" bgcolor="#FFFFFF">
																													&nbsp;
																												</td>
																												<td bgcolor="#FFFFFF">
																													&nbsp;
																												</td>
																												<td align="right" bgcolor="#FFFFFF">
																													<a href="#"><img src="images/more.jpg"
																															width="39" height="9">
																													</a>
																												</td>
																											</tr>
																										</table></div>
																									</td>
																								</tr>
																							</table>
																						</td>
																					</tr>
																				</table></div>
																			</td>
																		</tr>
																		<tr id="wfDiv">
																			<td align="center" valign="top">
																				<div><table width="95%" cellspacing="0" cellpadding="0">
																					<tr>
																						<td bgcolor="D5D3D6">
																							<table width="100%" border="0" cellpadding="0"
																								cellspacing="1">
																								<tr>
																									<td bgcolor="#FFFFFF">
																										<table width="100%" border="0" cellpadding="0"
																											cellspacing="0">
																											<tr>
																												<td height="20"
																													background="images/table_jianbian.jpg">
																													<table width="100%" border="0"
																														cellpadding="0" cellspacing="0">
																														<tr>
																															<td width="4%">
																																&nbsp;
																															</td>
																															<td width="80%">
																																施工调度<span class="STYLE1">(25条工单等待处理)</span>
																															</td>
																															<td width="16%">
																																<div align="center"><a href="javascript:turn('div3','img3');">
																																	<img id="img3" src="images/table_right.jpg"
																																		width="30" height="20"></a>
																																</div>
																															</td>
																														</tr>
																													</table>
																												</td>
																											</tr>
																										</table>
																									</td>
																								</tr>
																								<tr>
																									<td bgcolor="#FFFFFF"><div id="div3" style="display:block">
																										<table width="100%" border="0" align="center"
																											cellpadding="0" cellspacing="1">
																											<tr>
																												<td width="7%" height="20" align="center"
																													bgcolor="#FFFFFF">
																													<img src="images/icon.gif" width="6"
																														height="6">
																												</td>
																												<td width="45%" bgcolor="#FFFFFF">
																													<A href="#">191123080516S6589535</A>
																													<a href="table.htm" target="mainFrame"></a>
																												</td>
																												<td width="48%" bgcolor="#FFFFFF">
																													固定电话新装
																												</td>
																											</tr>
																											<tr>
																												<td height="20" align="center"
																													bgcolor="#FFFFFF">
																													<img src="images/icon.gif" width="6"
																														height="6">
																												</td>
																												<td bgcolor="#FFFFFF">
																													<A href="#">123611080516S7642535</A>
																													<a href="table.htm" target="mainFrame"></a>
																												</td>
																												<td bgcolor="#FFFFFF">
																													ADSL拆机
																												</td>
																											</tr>
																											<tr>
																												<td align="center" bgcolor="#FFFFFF">
																													&nbsp;
																												</td>
																												<td bgcolor="#FFFFFF">
																													&nbsp;
																												</td>
																												<td align="right" bgcolor="#FFFFFF">
																													<a href="#"><img src="images/more.jpg"
																															width="39" height="9">
																													</a>
																												</td>
																											</tr>
																										</table></div>
																									</td>
																								</tr>
																							</table>
																						</td>
																					</tr>
																				</table></div>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
													<td width="1" bgcolor="d5d5d5"></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
              <td height="6"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="2%"><img src="images/right_bottom_01.gif" width="23" height="6"></td>
                    <td width="97%" height="6" background="images/right_bottom_bg.gif"></td>
                    <td width="1%"><img src="images/right_bottom_02.gif" width="14" height="6"></td>
                  </tr>
              </table></td>
            </tr>
          </table></td>
        </tr>
      </table></td>
  </tr>
</table>
</body>
</html>