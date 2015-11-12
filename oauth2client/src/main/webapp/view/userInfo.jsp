<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>第三方系统用户中心</title>
</head>
<body>
	<table>
		<tr>
			<td>昵称：</td><td>${userDto.nickname}</td>
		</tr>
		<tr>
			<td>开放ID：</td><td>${userDto.openid}</td>
		</tr>
		<tr>
			<td>邮件：</td><td>${userDto.email}</td>
		</tr>
	</table>
</body>
</html>