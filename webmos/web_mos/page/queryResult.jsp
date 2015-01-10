<%@page import="com.cattsoft.tm.vo.DColumnDescSVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <title>查询结果</title>
	<script type="text/javascript">
	
	function talkToIOM(){
		
	}
	</script>
  </head>
  <body>
  <form id="queryForm" action="../tm/ctxdAction.do?method=queryResult" method="post">
  <span style="display:none"><input type="hidden" value='<%=request.getAttribute("tableId")%>' name="tableId"/></span>
  	<div id="queryConditionDiv">
  		<%--<logic:iterate id="condition" name="conditionList">
  			<label>
  				<bean:write name="condition" property="columnDesc"  /> 
  			</label>
  			<lalbel>
  			
  				<input type="text" name="<bean:write name='condition' property='column_name' />" />
  			
  			<logic:equal name="condition" property="conditionType" value="M">
  				<html:select name="condition" property='<bean:write name="columnName" />' >
  					<html:optionsCollection name="condition" property="data" label="column_name"/>
  				</html:select>
  			</logic:equal>
  			</lalbel>
  		</logic:iterate>
  		--%>
  		<table>
  			<tr>
		  		<%
		  			List conditionList=(List)request.getAttribute("conditionList");
		  		    for(int i=0;i<conditionList.size();i++){
		  		    	Map m=(HashMap)conditionList.get(i);
		  		    	String columnDesc=(String)m.get("columnDesc");
		  		    	String conditionType=(String)m.get("conditionType");
		  		    	String columnName=(String)m.get("columnName");
		  		    	String dataType=(String)m.get("dataType");
		  		    	List data=(List)m.get("data");
		  		    	String value=(String)m.get("value");
		  		    	out.print("<td width='100px'>"+columnDesc+"</td>");
		  		    	if("M".equals(conditionType)){
		  		    		out.print("<td width='100px'>"); %>
		  		    		<select name="QRY_<%=columnName %>">
		  		    		<% 
		  		    			out.print("<option value=\'\'>请选择</option> ");
		  		    			for(int j=0;j<data.size();j++){
		  		    				String v=(String)data.get(j);%>
		  		    				<option value="<%=v%>" <%if(value.equals(v)){out.print("selected=selected");}%>   ><%=v%></option>
		  		    		<%
		  		    			}
		  		    		%>
		  		    		</select>
		  		    		</td>
		  		    		<%
		  		    	}else{
		  		    		if("DATE".equals(dataType)){%>
		  		    			<td width='100px'><input type='text' value="<%=value%>" name="QRY_<%=columnName%>" /></td>
		  		    		<%}else{
		  		    			out.println();
		  		    		}
		  		    	}
		  		    }
		  		%>
		  		<td><input type="submit" value="查询"/></td>
  			</tr>
  		</table>
  		
  		
  	</div>
  	<div id="queryColumnsDiv">
  		<table>
  			<tr>
		  		<logic:iterate id="condition" name="queryColumnList">
		  			<td width="100px">
		  				<bean:write name="condition" property="columnDesc" />
		  			</td>
		  		</logic:iterate>
	  		</tr>
  		</table>
  	</div>
  	<div id="showResultDiv">
  		<table>
  		<%
  		List resList=(List)request.getAttribute("resList");
			for(int i=0;i<resList.size();i++){
				%>
				<tr>
				<%
				Map m=(HashMap)resList.get(i);
				List queryColumnList=(List)request.getAttribute("queryColumnList");
				for(int j=0;j<queryColumnList.size();j++){
					DColumnDescSVO column=(DColumnDescSVO)queryColumnList.get(j);
	  				String columnName=column.getColumnName();
	  				out.print("<td width='100px'>"+m.get(columnName)+"</td>");
				}%>
			</tr>
			<%}%>
  		</table>
  	
  	</div>
  	
  
  </form>
  </body>
</html>
