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

    <body  bgcolor="#e6f4fa">
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
            	<td>
            		<img src="../images/error.png" />
            	</td>
            	<td>
            		<bean:write name="errInfo" property="errMsg" />
            	</td>
            </tr>
        </table>

        <p>
            &nbsp;
        </p>
    </body>
</html>


