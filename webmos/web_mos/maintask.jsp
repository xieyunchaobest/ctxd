<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.cattsoft.pub.util.SysConfigData"%>
<%@ page import="com.cattsoft.pub.SysConstants"%>
<HTML>
<HEAD>
<TITLE>:::公众客户服务支撑系统:::</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/tabdisplaystyle.css" rel="stylesheet" type="text/css">
<style type="text/css">
body{
	margin:0px;
	padding:0px;
	width:100%;
	height:100%;
	background:#ffffff;
	text-align:center;
	background-image: url(images/news_bj1.gif);
}
#login_bg{
    margin-top:100px; padding:0px;
	width:595px; height:359px;
	background:#ffffff url(images/login_bg.jpg) no-repeat center center;}
.sysName{
	
	color:#C40005;    
	font-size:28px;
	font-weight:bold;
	letter-spacing:0px;
	font-family:"黑体","宋体",Arial, Helvetica, sans-serif;
}
.companyName{color:#555555; font-size:22px; font-weight:bold; font-family:"黑体","宋体",Arial, Helvetica, sans-serif;}
.list{color:#C40005; font-size:30px; font-weight:bold; margin:5px;font-family:Arial, Helvetica, sans-serif;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></HEAD>
<%
		request.setAttribute("hasCheckCode","Y");
	if(true){
	%>
	
	<BODY BGCOLOR=#FFFFFF onkeypress="keyPress()"
		onload="document.all('userName').focus();">
<html:form action="/login.do?method=login" method="post"
					onsubmit="return subForm()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="middle"><table width="607" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td valign="top"><table width="607" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><img src="images/2009020301.jpg" width="607" height="43"></td>
            </tr>
            <tr>
              <td height="30" valign="top" bgcolor="#FFFFFF"><table width="607" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="1" bgcolor="#FFFFFF"></td>
                </tr>
                <tr>
                  <td height="1" bgcolor="#E63337"></td>
                </tr>
              </table></td>
            </tr>
            <tr>
              <td bgcolor="#FFFFFF"><div align="center">
                <table width="607" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><div align="right"><img src="images/pcss/2009020302.jpg"></div></td>
                    <td width="110">&nbsp;</td>
                  </tr>
                </table>
              </div></td>
            </tr>
            <tr>
              <logic:equal name="hasCheckCode" value="<%=SysConstants.SYS_CONFIG_CHECK_CODE_YES%>">
              <td height="213" valign="top" background="images/pcss/2009020304.jpg"><table width="607" height="213" border="0" cellpadding="0" cellspacing="0">
              </logic:equal>
              <logic:equal name="hasCheckCode" value="<%=SysConstants.SYS_CONFIG_CHECK_CODE_NO%>">
              <td height="213" valign="top" background="images/pcss/2009020304.jpg"><table width="607" height="213" border="0" cellpadding="0" cellspacing="0">
              </logic:equal>
                  <tr>
                    <td width="233">&nbsp;</td>
                    <td width="250" valign="top"><table width="100%" border="0" cellspacing="4" cellpadding="2">
                          <tr> 
                            <td height="30" colspan="3"><label></label> <label></label></td>
                          </tr>
                            <td width="33%" height="17" align="right"><label> 
                              <span style="font-size:12px">用户名：</span></label></td>
                            <td width="67%" colspan="2" align="left"><label><html:text property="userName" styleClass="input" size="18"
							/> </label></td>
                          </tr>
                          <tr> 
                            <td height="26" align="right" class="style1"><span style="font-size:12px">密　码：</span></td>
                            <td colspan="2"><label><html:password property="password" styleClass="input" size="18"
							/> </label></td>
                          </tr>
                          <tr> 
                            <td align="right" class="style1">&nbsp;</td>
                            <td colspan="2"><a href="javascript:subForm()"><img src="images/denglunew.gif"></a> 
                              <a href="javascript:reset()"><img src="images/quxiaonew.gif"></a></td>
                          </tr>
                        </table></td>
                  </tr>
              </table></td>
            </tr>
            <tr>
              <td><img src="images/2009020305.jpg" width="607" height="45"></td>
            </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</html:form>
</BODY>	
	<%
	}else{
	%>
<BODY BGCOLOR=#FFFFFF onkeypress="keyPress()"
		onload="document.all('userName').focus();">
<html:form action="/login.do?method=login" method="post"
					onsubmit="return subForm()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="middle"><table width="607" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td valign="top"><table width="607" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><img src="images/2009020301.jpg" width="607" height="43"></td>
            </tr>
            <tr>
              <td height="30" valign="top" bgcolor="#FFFFFF"><table width="607" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="1" bgcolor="#FFFFFF"></td>
                </tr>
                <tr>
                  <td height="1" bgcolor="#E63337"></td>
                </tr>
              </table></td>
            </tr>
            <tr>
              <td bgcolor="#FFFFFF"><div align="center">
                <table width="607" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="402"><div align="right"><img src="images/2009020302.jpg" width="286" height="63"></div></td>
                    <td width="43"><img src="images/login_version_V6.0.jpg" width="43" height="63"></td>
                    <td width="154">&nbsp;</td>
                  </tr>
                </table>
              </div></td>
            </tr>
            <tr>
              <logic:equal name="hasCheckCode" value="<%=SysConstants.SYS_CONFIG_CHECK_CODE_YES%>">
              <td height="239" valign="top" background="images/2009020304.jpg"><table width="607" height="239" border="0" cellpadding="0" cellspacing="0">
              </logic:equal>
              <logic:equal name="hasCheckCode" value="<%=SysConstants.SYS_CONFIG_CHECK_CODE_NO%>">
              <td height="213" valign="top" background="images/2009020304.jpg"><table width="607" height="213" border="0" cellpadding="0" cellspacing="0">
              </logic:equal>
                  <tr>
                    <td width="233">&nbsp;</td>
                    <td width="374" valign="top"><table width="100%" border="0" cellspacing="4" cellpadding="2">
                          <tr> 
                            <td height="20" colspan="3"><label></label> <label></label></td>
                          </tr>
                          <tr> 
                            <td height="7" colspan="3"><img src="images/2009020303.jpg" width="72" height="30"></td>
                          </tr>
                          <tr> 
                            <td width="16%" height="17" align="right"><label> 
                              <span class="style1">用户名：</span></label></td>
                            <td width="84%" colspan="2" align="left"><label><html:text property="userName" styleClass="input" size="18"
							/> </label></td>
                          </tr>
                          <tr> 
                            <td height="26" align="right" class="style1"><span class="style5">密　码：</span></td>
                            <td colspan="2"><label><html:password property="password" styleClass="input" size="18"
							/></label></td>
                          </tr>
                          <tr> 
                            <td align="right" class="style1">&nbsp;</td>
                            <td colspan="2"><a href="javascript:subForm()"><img src="images/denglunew.gif"></a> 
                              <a href="javascript:reset()"><img src="images/quxiaonew.gif"></a></td>
                          </tr>
                        </table></td>
                  </tr>
              </table></td>
            </tr>
            <tr>
              <td><img src="images/2009020305.jpg" width="607" height="45"></td>
            </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</html:form>
</BODY>	
	<%
	}
%>

</HTML>
<SCRIPT LANGUAGE="JavaScript">

function subForm(){	
   var userValue=document.loginFormBean.userName.value;
   var pwdValue=document.loginFormBean.password.value;
   if(userValue==''&&pwdValue==''){
      alert("用户名和密码不能为空");
      document.loginFormBean.userName.focus();
      return;
    }
     if(userValue==''&&pwdValue!=''){
      alert("用户不能为空");
      document.loginFormBean.userName.focus();
      return;
   }
    if(userValue!=''&&pwdValue==''){
      alert("密码不能为空");
      document.loginFormBean.password.focus();
      return;
   }
   if(document.loginFormBean.checkCode){
   	   var chkValue=document.loginFormBean.checkCode.value;
	   if(chkValue==''){
	      alert("验证码不能为空");
	      document.loginFormBean.checkCode.focus();
	      return;
	   }   
   }
	document.loginFormBean.submit();
}
function openindex(){
window.opener=null;window.close();
ht=screen.height-80;
wd=screen.width;
var username2 = document.loginFormBean.userName.value;
var password2 = document.loginFormBean.password.value;

popup = window.open('login.do?method=login&username='+username2+'&password='+password2,'','height='+ht+',width='+wd+',left=0,top=0,toolbar=no,titlebar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes');
}
function reset(){
	document.loginFormBean.userName.value="";
	document.loginFormBean.password.value="";
}
function keyPress()
{
  if(event.keyCode == "13"){
    if(document.loginFormBean.userName.value==""){
     document.loginFormBean.userName.focus();
    return;
      }
   if(document.loginFormBean.password.value==""){
     document.loginFormBean.password.focus();
     return;
   }
     event.returnValue = false;
	 subForm();

  }
}
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//&quot;</SCRIPT>
<logic:messagesPresent name="loginCheckCode">
	<SCRIPT LANGUAGE="JavaScript">
		alert("验证码输入不正确！");
	</SCRIPT>
</logic:messagesPresent>
<logic:messagesPresent name="loginok">
	<SCRIPT LANGUAGE="JavaScript">
		alert("错误的用户名或密码！");
	</SCRIPT>
</logic:messagesPresent>

<logic:messagesPresent name="expired">
	<SCRIPT LANGUAGE="JavaScript">
		alert("密码过期，点击'确定'按钮会弹出修改密码的对话框");
	</SCRIPT>
</logic:messagesPresent>

<logic:messagesPresent name="loginMode">
	<SCRIPT LANGUAGE="JavaScript">
		alert("只能在一个地方登陆！");
	</SCRIPT>
</logic:messagesPresent>

<logic:messagesPresent name="LoginStsCheck">
	<SCRIPT LANGUAGE="JavaScript">
		alert("拥有该用户的员工已离职！");
	</SCRIPT>
</logic:messagesPresent>

<logic:messagesPresent name="firstLogin">
	<SCRIPT LANGUAGE="JavaScript">
	  <% String crmModiPwdJspUrl = SysConfigData.getSysConfigCurValue(
				SysConstants.SYS_CONFIG_CRM_MOD_PWD_JSP_URL, null, null, null,
				null, null);
	     String userName = (String)session.getAttribute("userName");
	     crmModiPwdJspUrl = crmModiPwdJspUrl + userName;
	  %>
  	var crmModiPwdJspUrl = '<%=crmModiPwdJspUrl%>';
  
	window.showModalDialog(crmModiPwdJspUrl,"newwin","dialogHeight: 195px; dialogWidth: 360px; center: yes; help:no;status:no;");
	</SCRIPT>
</logic:messagesPresent>