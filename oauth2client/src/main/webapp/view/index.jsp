<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath }/" scope="session"/>
<html>
<body>
<h2>这是网站首页</h2>
	<ul>
		<li><a href="${base}/user/info">用户中心</a></li>
	</ul>
</body>
</html>
