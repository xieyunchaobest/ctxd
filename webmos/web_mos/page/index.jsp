<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.cattsoft.tm.struts.Tools"%>
<%@page import="com.cattsoft.tm.vo.DColumnDescSVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<%String path=request.getContextPath(); %>
<title>经营数据支撑系统</title>
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=path%>/css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=path%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
$(function(){
	//setMenuHeight
	$('.menu').height($(window).height()-51-27-32);
	$('.sidebar').height($(window).height()-51-27-32);
	$('.page').height($(window).height()-51-27-32);
	$('.page iframe').width($(window).width()-15-180);
	$('.subMenu a[href="#"]').next('ul').hide();
	//menu on and off
	$('.btn').click(function(){
		$('.menu').toggle();
		
		if($(".menu").is(":hidden")){
			$('.page iframe').width($(window).width()-15+5);
			}else{
			$('.page iframe').width($(window).width()-15-188);
				}
		});
		
	//
	$('.subMenu a[href="#"]').click(function(){
		$(this).next('ul').toggle();
		return false;
		});
})


	function showPosition(obj,depth){
		var titleBar=$("#rightMain").contents().find(".pageTitle")
		var txt="";
		if(depth=='1'){
			txt=$(obj).html();
			titleBar.html(txt);
		}else{
			var ptxt=$(obj).parent().parent().parent().find("a").first().html();
			var subtxt=$(obj).html();
			txt=ptxt+">>"+subtxt;
		}
		$("#menudesc").val(txt);
	}
	
	 <%
	  String nodes=(String)request.getAttribute("treeJson");
		%>
	 $(document).ready(function(){
	 
	var setting = {
		callback: {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("funcmenu");
				if (treeNode.isParent) {
					zTree.expandNode(treeNode);
				} else {
				}
					var nowStr = "";
					while($(treeNode).attr("name")){
						nowStr = treeNode.name + ">>" + nowStr;
						treeNode = treeNode.getParentNode();
					}
					nowStr=nowStr.substr(0,nowStr.length-2);
					$("#menudesc").val(nowStr);
			}
		}
	};

	 var nodes=<%=nodes%>
			$.fn.zTree.init($("#funcmenu"), setting, nodes);
		});
	
</script>


</head>

<body>
<div id="wrap">
	<div id="header">
	<div style="display:none"><input type="hidden"  id="menudesc" /></div>
    <div class="nav fleft">
    	<!-- <ul> -->
        	<!-- <div class="nav-left fleft"></div> -->
            <!-- <li class="first">我的面板</li>
        	<li>设置</li>
            <li>模块</li>
            <li>内容</li>
            <li>用户</li>
            <li>扩展</li>
            <li>应用</li> -->
            <!-- <div class="nav-right fleft"></div> -->
        <!-- </ul> -->
    </div>
<div id="wrap">
<table width="100%"  border="0" cellpadding="0" cellspacing="0" background="../images/index-header-bg.gif">
      <tr>
        <td width="21%"><div class="logo fleft"> </div></td>
        <td width="61%"><div align="center"><img src="../images/index_word.png" width="301" height="35" /></div></td>
        <td width="17%">&nbsp;</td>
        <td width="1%" valign="top"><a class="logout fright" href="../tm/ctxdAction.do?method=loginOut"> </a></td>
      </tr>
    </table>
    </table><div class="subnav">
    	<div class="subnavLeft fleft"></div>
        <!-- <div class="fleft"></div> -->
        <div class="subnavRight fright"></div>
    </div>
    <div id="content">
    <div class="space"></div>
    <div class="menu fleft">
    		<ul id="funcmenu" class="ztree"></ul>
    </div>
    <div class="sidebar fleft"><div class="btn"></div></div>
    <div class="page" id="pageContent">
     <iframe width="100%" scrolling="auto" height="100%" frameborder="false" marginwidth="0px" scrolling="no" 
    	allowtransparency="true" style="border: medium none;overflow:auto;" src="../page/welcome.jsp" id="rightMain" name="right"></iframe>
    </div>
    </div><!--#content -->
    <div class="clear"></div>
    <div id="footer">
    	<div style="width:300px;margin:0 auto;color:white">
    		Copyright @2015 中国联通内蒙古分公司
    	</div>
    </div><!--#footer -->
</div><!--#wrap -->
</body>
</html>
