<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户中心</title>
</head>
<body>
	<dl class="dl-horizontal">
	    <dt>username</dt>
	    <dd><code>${userDto.username}</code></dd>
	    <dt>id</dt>
	    <dd><code>${userDto.id}</code></dd>
	    <dt>password</dt>
	    <dd><code>${userDto.password}</code></dd>
	    <dt>role_id</dt>
	    <dd><code>${userDto.role_id}</code></dd>
	</dl>
</body>
</html>