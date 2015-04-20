<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=GBK" isErrorPage="true"%>
<%@ page import="com.cattsoft.pub.exception.*"%>
<%@ page import="java.io.*"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<html>

    <head>
        <base target="_self"/>
        <meta http-equiv="Content-Type" content="text/html; charset=gb2312" >
        <title>异常信息页面</title>
        <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
    </head>

    <body>
    <div id = "titleError">
        <table width="100%" height="200" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td height="50">
                </td>
            </tr>
            <tr>
                <td align="center">
                    <table border="0" cellpadding="0" cellspacing="0" width="350">
                        <tr>
                            <td>
                                <img src="<%=request.getContextPath()%>/images/error/spacer.gif" width="53" height="1" border="0"
                                    alt="">
                            </td>
                            <td>
                                <img src="<%=request.getContextPath()%>/images/error/spacer.gif" width="63" height="1" border="0"
                                    alt="">
                            </td>
                            <td>
                                <img src="<%=request.getContextPath()%>/images/error/spacer.gif" width="42" height="1" border="0"
                                    alt="">
                            </td>
                            <td>
                                <img src="<%=request.getContextPath()%>/images/error/spacer.gif" width="35" height="1" border="0"
                                    alt="">
                            </td>
                            <td>
                                <img src="<%=request.getContextPath()%>/images/error/spacer.gif" width="74" height="1" border="0"
                                    alt="">
                            </td>
                            <td>
                                <img src="<%=request.getContextPath()%>/images/error/spacer.gif" width="31" height="1" border="0"
                                    alt="">
                            </td>
                            <td>
                                <img src="<%=request.getContextPath()%>/images/error/spacer.gif" width="52" height="1" border="0"
                                    alt="">
                            </td>
                            <td>
                                <img src="<%=request.getContextPath()%>/images/error/spacer.gif" width="1" height="1" border="0"
                                    alt="">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7">
                                <img name="n001_r1_c1" src="<%=request.getContextPath()%>/images/error/001_r1_c1.gif" width="350"
                                    height="67" border="0" alt="">
                            </td>
                            <td>
                                &nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td rowspan="2" colspan="2">
                                <img name="n001_r2_c1" src="<%=request.getContextPath()%>/images/error/001_r2_c1.gif" width="116"
                                    height="92" border="0" alt="">
                            </td>
                            <td colspan="3" align="left" valign="top">
                                <table width="151" height="70" border="0" cellpadding="0"
                                    cellspacing="0">
                                    <tr>
                                        <td>
                                            出现错误：ID=
                                            <FONT color=#ff0000><bean:write name="errInfo"
                                                    property="errId" />
                                            </FONT>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                          错误信息：
                                            <FONT color=#ff0000><span id="errinfospan"><bean:write name="errInfo"
                                                    property="errMsg" /></span>
                                            </FONT>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                           解决方案:
                                            <FONT color=#ff0000>
                                                                                                                           请与维护人员联系!
                                            </FONT>
                                        </td>
                                   
                                </table>
                            </td>
                             <td rowspan="2" colspan="2">
                                <img name="n001_r2_c6" src="<%=request.getContextPath()%>/images/error/001_r2_c6.gif" width="83"
                                    height="92" border="0" alt="">
                            </td>
                            <td>
                                <img src="<%=request.getContextPath()%>/images/error/spacer.gif" width="1" height="71" border="0"
                                    alt="">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <img name="n001_r3_c3" src="<%=request.getContextPath()%>/images/error/001_r3_c3.gif" width="151"
                                    height="21" border="0" alt="">
                            </td>
                            <td>
                                <img src="<%=request.getContextPath()%>/images/error/spacer.gif" width="1" height="21" border="0"
                                    alt="">
                            </td>
                        </tr>
                        <tr>
                            <td rowspan="2">
                                <img name="n001_r4_c1" src="<%=request.getContextPath()%>/images/error/001_r4_c1.gif" width="53"
                                    height="41" border="0" alt="">
                            </td>
                            <td colspan="2">
                                <a href="javascript:showDiv('detailError');"><img name="n001_r4_c2"
                                        src="<%=request.getContextPath()%>/images/error/001_r4_c2.gif" width="105" height="32"
                                        border="0" alt="">
                                </a>
                            </td>
                            <td rowspan="2">
                                <img name="n001_r4_c4" src="<%=request.getContextPath()%>/images/error/001_r4_c4.gif" width="35"
                                    height="41" border="0" alt="">
                            </td>
                            <td colspan="2">
                                <a href="javascript:gotoBack()" target="_self"><img
                                        name="n001_r4_c5" src="<%=request.getContextPath()%>/images/error/001_r4_c5.gif"
                                        width="105" height="32" border="0" alt="">
                                </a>
                            </td>
                            <td rowspan="2">
                                <img name="n001_r4_c7" src="<%=request.getContextPath()%>/images/error/001_r4_c7.gif" width="52"
                                    height="41" border="0" alt="">
                            </td>
                            <td>
                                <img src="<%=request.getContextPath()%>/images/error/spacer.gif" width="1" height="32" border="0"
                                    alt="">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <img name="n001_r5_c2" src="<%=request.getContextPath()%>/images/error/001_r5_c2.gif" width="105"
                                    height="9" border="0" alt="">
                            </td>
                            <td colspan="2">
                                <img name="n001_r5_c5" src="<%=request.getContextPath()%>/images/error/001_r5_c5.gif" width="105"
                                    height="9" border="0" alt="">
                            </td>
                            <td>
                                <img src="<%=request.getContextPath()%>/images/error/spacer.gif" width="1" height="9" border="0"
                                    alt="">
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
    <div id="detailError" style="display:none">
        <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
            <tr align="left">
                <td>
        <h1>
            系统错误处理
        </h1>
        

        <font color="red" size="3">
        <%exception.printStackTrace(response.getWriter());%></font>
        <br>
        <a href="javascript:showDiv('titleError');"><img name="back"
                src="<%=request.getContextPath()%>/images/error/001_r4_c5.gif" width="105" height="32" border="0" alt="">
        </a>
                </td>
            </tr>
        </table>
    </div>
    </body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--
function showDiv(divId){
    if(divId=="titleError"){
    	document.getElementById("titleError").style.display="";
    	document.getElementById("detailError").style.display="none";
    }else if(divId=="detailError"){
    	document.getElementById("detailError").style.display="";
    	document.getElementById("titleError").style.display="none";
    }
}
function gotoBack(){
  //window.history.back();
  //alert(document.referrer);
  window.location.href=document.referrer;
  //window.location.reload(false);
}
//-->
</SCRIPT>
