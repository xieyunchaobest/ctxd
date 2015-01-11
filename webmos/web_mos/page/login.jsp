<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>用户登录</title>
<link href="../css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>

<script type="text/javascript">
	$(function() {

		$('#btnSubmit').click(function() {
			$('.menu').toggle();

			if ($(".menu").is(":hidden")) {
				$('.page iframe').width($(window).width() - 15 + 5);
			} else {
				$('.page iframe').width($(window).width() - 15 - 168);
			}
		});

		$("#btnSubmit").click(function() {
			txt = $("input").val();
			$.post("../tm/ctxdAction.do?method=login", {
				userName : $("#userName").val(),
				password : $("#password").val()
			}, function(result) {
				//var dataObj=eval("("+result+")");//转换为json对象
				//alert(result);
				//alert(json.root[0].resultCode);
				//var json = eval("'" + result + "'");
				//alert(json.resultCode);
				if(result=='E'){
					alert('用户名或密码错误!');
					return false;
				}else{
					window.location.href="../tm/ctxdAction.do?method=showMain"; 
				}
				// 
			});
		});

	})
</script>
</head>

<body>
	<div id="wrap">
		<div id="header"></div>
		<div id="content-wrap">
			<div class="space"></div>
			<form action="../ctxdAction.do?method=login" id="loginForm"
				method="post">
				<div class="content">
					<div class="field">
						<label>账 户：</label><input class="username" name="userName"
							id="userName" type="text" />
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
		<div id="footer"></div>
	</div>
</body>
</html>
