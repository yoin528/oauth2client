<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>authorization_code</title>
</head>
<body>
<a href="${contextPath}/">Home</a>

<h2>authorization_code
    <small>用 'code' 换取 'access_token'</small>
</h2>
<br/>


<div class="panel panel-default">
    <div class="panel-heading">步骤2: <code>用 'code' 换取 'access_token'</code></div>
    <div class="panel-body">
        <div ng-controller="CodeAccessTokenCtrl" class="col-md-10">

            <form action="code_access_token" method="post" class="form-horizontal">
                <input type="text" name="accessTokenUri" value="${accessTokenDto.accessTokenUri}"/>
                <input type="text" name="clientId" value="${accessTokenDto.clientId}"/>
                <input type="text" name="clientSecret" value="${accessTokenDto.clientSecret}"/>
                <input type="text" name="grantType" value="${accessTokenDto.grantType}"/>
                <input type="text" name="code" value="${accessTokenDto.code}"/>
                <input type="text" name="redirectUri" value="${accessTokenDto.redirectUri}"/>
                <button type="submit" class="btn btn-primary">获取 access_token</button>
                <span class="text-muted">后台将通过 HttpClient 去获取 access_token</span>
                <br/>
                <small class="text-muted">
                    <em class="glyphicon glyphicon-info-sign"></em> 在实际应用中, 该步骤一般由后台代码完成,前端不需要表现.
                </small>
            </form>
        </div>
    </div>
</div>
</body>
</html>