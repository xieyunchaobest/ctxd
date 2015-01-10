<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="gb2312"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<html>

    <head>
        <base target="_self"/>
        <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
        <title>出错信息页面</title>
        <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
        
        <script>
        
          function gotoBack(){
            //window.history.back();
            //alert(document.referrer);
            //window.location.href=document.referrer;
            window.location.replace(document.referrer);
          }
      
       </script>
       
    </head>

    <body>
    <body>
        <p>
            &nbsp;
        </p>
        <table>
            <tr>
                <td height="20">
                </td>
            </tr>
        </table>
        <p align="center">
        <table width="350" border="0" cellpadding="0" cellspacing="0" class="table_gray">
            <tr>
                <td width="44" height="45" align="center" valign="top">
                    <img src="<%=request.getContextPath()%>/images/errorapp/top_001.gif" width="44" height="45">
                </td>
                <td align="right" valign="top" background="<%=request.getContextPath()%>/images/errorapp/top_004.gif">
                    <img src="<%=request.getContextPath()%>/images/errorapp/top_005.gif" width="80" height="42">
                </td>
                <td width="44" height="45" align="left" valign="top">
                    <img src="<%=request.getContextPath()%>/images/errorapp/top_002.gif" width="44" height="45">
                </td>
            </tr>
            <tr>
                <td width="60" valign="bottom">
                    <img src="<%=request.getContextPath()%>/images/errorapp/mi_001_t_9.gif" width="44" height="157">
                </td>
                <td width="100%" align="left" valign="top">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="11">
                                <img src="<%=request.getContextPath()%>/images/errorapp/mi_001_t_1.gif" width="11" height="20">
                            </td>
                            <td background="<%=request.getContextPath()%>/images/errorapp/mi_001_t_3.gif">
                                &nbsp;
                            </td>
                            <td width="11">
                                <img src="<%=request.getContextPath()%>/images/errorapp/mi_001_t_2.gif" width="11" height="20">
                            </td>
                        </tr>
                        <tr>
                            <td align="left" valign="top"
                                background="<%=request.getContextPath()%>/images/errorapp/mi_001_t_5.gif">
                                <img src="<%=request.getContextPath()%>/images/errorapp/mi_001_t_5.gif" width="11" height="1">
                            </td>
                            <td align="center" valign="top" bgcolor="#FFFFFF">
                                <table width="100%" border="0" align="center" cellpadding="0"
                                    cellspacing="5">
                                    <tr>
                                        <td>
                                            <font class="datefont">提示信息ID：</FONT>
                                            <br>
                                            ID=
                                            <FONT color=#000000><bean:write name="errInfo"
                                                    property="errId" />
                                            </FONT>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <font class="datefont">提示信息：</font>
                                            <br>
                                            <FONT color=#000000 ><span id="errinfospan"><bean:write name="errInfo"
                                                    property="errMsg" /></span>
                                            </FONT>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            &nbsp;
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            &nbsp;
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td background="<%=request.getContextPath()%>/images/errorapp/mi_001_t_4.gif">
                                <img src="<%=request.getContextPath()%>/images/errorapp/mi_001_t_4.gif" width="11" height="1">
                            </td>
                        </tr>
                        <tr>
                            <td align="left" valign="bottom">
                                <img src="<%=request.getContextPath()%>/images/errorapp/mi_001_t_7.gif" width="11" height="18">
                            </td>
                            <td height="18" background="<%=request.getContextPath()%>/images/errorapp/mi_001_t_8.gif">
                                <img src="<%=request.getContextPath()%>/images/errorapp/mi_001_t_8.gif" width="2" height="18">
                            </td>
                            <td>
                                <img src="<%=request.getContextPath()%>/images/errorapp/mi_001_t_6.gif" width="11" height="18">
                            </td>
                        </tr>
                    </table>
                </td>
                <td valign="bottom">
                    <img src="<%=request.getContextPath()%>/images/errorapp/mi_001_t_9.gif" width="44" height="157">
                </td>
            </tr>
            <tr bgcolor="#CFD3DD">
                <td height="30" colspan="3">
                    <table width="50%" border="0" align="center" cellpadding="0" cellspacing="5">
                        <tr>
                            <td align="center">
                                <a href="#" onclick="gotoBack()" target="_self"><img
                                        src="<%=request.getContextPath()%>/images/errorapp/anniu222.gif" width="96" height="28"
                                        border="0">
                                </a>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

        <p>
            &nbsp;
        </p>
    </body>
</html>


