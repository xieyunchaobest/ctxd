<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>欢迎登录经营数据支撑系统</title>
<link href="../css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/public.js"></script>

<script type="text/javascript">
	$(function() {
		$(document).keyup(function(event) {
			if (event.keyCode == 13) {
				if($("#userName").val()!=''){
					$("#btnSubmit").trigger("click");
				}
				
			}
		});

		$('#btnSubmit').click(function() {
			$('.menu').toggle();

			if ($(".menu").is(":hidden")) {
				$('.page iframe').width($(window).width() - 15 + 5);
			} else {
				$('.page iframe').width($(window).width() - 15 - 168);
			}
		});

		$("#btnSubmit")
				.click(
						function() {
							var v=validate();
							if(v==false) return;
							showCover();
							$.post(
									"../tm/ctxdAction.do?method=login",
									{
										userName : $("#userName").val(),
										password : $("#password").val()
									},
									function(result) {
										//var dataObj=eval("("+result+")");//转换为json对象
										//alert(result);
										//alert(json.root[0].resultCode);
										//var json = eval("'" + result + "'");
										//alert(json.resultCode);
										if (result == 'S') {
											window.location.href = "../tm/ctxdAction.do?method=showMain";
										} else {
											cancelCoverit();
											alert('用户名或密码错误!');
											return false;
										}
										// 
									});
						});

	})
	
	function validate(){
		if($.trim($("#userName").val())==''){
			alert('请输入用户名！');
			return false;
		}
		if($.trim($("#password").val())==''){
			alert('请输入密码！');
			return false;
		}
		return true;
	}
</script>
</head>

<body onload="javascript:document.getElementById('userName').focus();">
	<div id="wrap">
		<div id="header">
			<div id="loginword"/>
		</div>
		<div id="content-wrap">
			<div class="space"></div>
			<form action="../ctxdAction.do?method=login" id="loginForm"
				method="post">
				<div class="content">
					<div class="field">
						<label>账 户：</label><input class="username" name="userName"
							id="userName" type="text" focus/>
					</div>
					<div class="field">
						<label>密 码：</label><input class="password" name="password"
							id="password" type="password" /><br />
					</div>
					<!-- <div class="field"><label>验证码：</label><input class="password" name="" type="password" /><br /></div> -->
					<div class="btn">
						<input name="" type="button" id="btnSubmit" class="login-btn"
							value="" />
					</div>
				</div>
			</form>
		</div>
		<div id="footer">
			<div style="width:300px;margin:0 auto;color:white">
    		Copyright @2015 中国联通内蒙古分公司
    		</div>
    	</div>
	</div>
</body>
</html>
