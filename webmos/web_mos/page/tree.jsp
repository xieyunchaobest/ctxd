<!DOCTYPE html>
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
<link rel="stylesheet" href="<%=path%>/css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=path%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
$(function(){
	//setMenuHeight
	$('.menu').height($(window).height()-51-27-32);
	$('.sidebar').height($(window).height()-51-27-32);
	$('.page').height($(window).height()-51-27-32);
	$('.page iframe').width($(window).width()-15-168);
	$('.subMenu a[href="#"]').next('ul').hide();
	//menu on and off
	$('.btn').click(function(){
		$('.menu').toggle();
		
		if($(".menu").is(":hidden")){
			$('.page iframe').width($(window).width()-15+5);
			}else{
			$('.page iframe').width($(window).width()-15-168);
				}
		});
		
	//
	$('.subMenu a[href="#"]').click(function(){
		$(this).next('ul').toggle();
		return false;
		});
})


/* $(function() {
		$("#pageContent").load("../page/welcome.jsp", function() {
  		//alert("Load was performed.");
	});
	}) */
	
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
	
	/* $(function() {
		tree = new dhtmlXTreeObject("funcmenu", "100%", "100%", "0");
		tree.setSkin("dhx_skyblue");
		tree.setImagePath("../images/dhxtree_skyblue/");
		tree.setOnClickHandler(doOnClick);
		tree.loadXML("../tm/ctxdAction.do?method=getFuncNodeXML", function() {
			//tree.openItem("0")
		});

		function doOnClick(id) {
			var nowStr = "";
			var setlectNode = tree.getSelectedItemId().split(",");
			var temp = tree._globalIdStorageFind(setlectNode[0]);
			//	alert(temp.label);
				while (typeof (temp.parentObject) == "object") {
					nowStr = temp.label + ">>" + nowStr;
					temp = temp.parentObject;
				}
				nowStr=nowStr.substr(0,nowStr.length-2);
				$("#menudesc").val(nowStr);
			var myUrl = tree.getUserData(id, "url");
			$("#rightMain").attr(
				"src",myUrl);
		}
		;

	});
	 */
	 <%
	  String nodes=(String)request.getAttribute("treeJson");
		%>
	 $(document).ready(function(){
	 var setting = {	};
	 var nodes=<%=nodes%>
			$.fn.zTree.init($("#funcmenu"), setting, nodes);
		});
	
</script>


</head>

<body>
	
    	<ul id="funcmenu" class="ztree"></ul>
   
</body>
</html>
