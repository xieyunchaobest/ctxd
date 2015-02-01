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
<title>经营数据支撑系统</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
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
	
	
</script>


</head>

<body>
<div id="wrap">
	<div id="header">
	<!-- <img src="../images/index_word.png" style="width:301px:height:35px;margin:0 auto;margin-top:10px;"  /> -->
	<div style="display:none"><input type="hidden"  id="menudesc" /></div>
    <div class="logo fleft"> </div>
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
    <a class="logout fright" href="../tm/ctxdAction.do?method=loginOut"> </a>
    <div class="clear"></div>
    <div class="subnav">
    	<div class="subnavLeft fleft"></div>
        <div class="fleft"></div>
        <div class="subnavRight fright"></div>
    </div>
    </div><!--#header -->
    <div id="content">
    <div class="space"></div>
    <div class="menu fleft">
    	<ul>
    	<li class="subMenuTitle">功能菜单</li>
    		<logic:notEmpty name="treeList">
    			<logic:iterate id="tree" name="treeList">
    				<li class="subMenu"><a href="#" onclick="showPosition(this,1)"><bean:write name="tree" property="nodeTreeName" /></a>
    				<ul>
    					 <logic:notEmpty name="tree" property="userFuncNodeList">
    						<logic:iterate id="node" name="tree" property="userFuncNodeList">
    							<li><a style="padding-left:33px;"  onclick="showPosition(this,2)" href="<bean:write name='node' property='html' />" target="right"><bean:write name="node" property="funcNodeName" /></a></li>
    						</logic:iterate>
    					</logic:notEmpty>
    				</ul>
    				</li>
    			</logic:iterate>
    		</logic:notEmpty>
        </ul>
    </div>
    <div class="sidebar fleft"><div class="btn"></div></div>
    <div class="page" id="pageContent">
     <iframe width="100%" scrolling="auto" height="100%" frameborder="false" marginwidth="0px"
    	allowtransparency="true" style="border: medium none;" src="../page/welcome.jsp" id="rightMain" name="right"></iframe>
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
