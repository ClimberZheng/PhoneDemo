<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function sendCode() {
		var number = window.document.getElementById("phoneNumber").value;
		//window.open()会根据项目路径加上参数形成完整的url,根据该url在新的窗口发送请求
		window.open("SendCodeServlet?phoneNumber=" + number);
		window.alert('发送验证码');
	}
</script>
<body>
	<div style="text-align: center">
		<h1>项目描述</h1>
		<h2>含有部分的手机号码格式验证，发送的验证码在SendCodeServlet中随机生成</h2>
		<h3>使用的平台：<a href="https://www.juhe.cn/">聚合数据</a></h3>
		<form action="#" method="post">
			<input type="text" id="phoneNumber" name="phoneNumber"
				placeholder="手机号码" pattern="^1[3|4|5|7|8]\d{9}$" required="required">
			<br> <br> <input type="text" name="code" placeholder="验证码"
				required="required">
			<button type="button" onclick="sendCode()">发送验证码</button>
			<br> <br>
		</form>
	</div>
</body>
</html>