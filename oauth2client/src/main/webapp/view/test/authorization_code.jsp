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
<a href="${base}/">Home</a>

<div class="panel panel-default">
    <div class="panel-heading">步骤1: <code>从 spring-oauth-server获取 'code'</code></div>
    <div class="panel-body">
        <div ng-controller="AuthorizationCodeCtrl" class="col-md-10" style="text-align: center;margin: 0 auto;">
            <form action="authorization_code_httpclient" method="post" class="form-horizontal">
                <table>
                	<tr>
                		<td>authorizationUri</td>
                		<td><input type="text" name="userAuthorizationUri" value="${userAuthorizationUri}" /></td>
                	</tr>
                	<tr>
                		<td>response_type</td>
                		<td><input type="text" name="responseType" value="code" /></td>
                	</tr>
                	<tr>
                		<td>scope</td>
                		<td>
							<select name="scope" ng-model="scope" class="form-control">
                                <option value="read,write">read,write</option>
                                <option value="read">read</option>
                                <option value="write">write</option>
                            </select>
						</td>
                	</tr>
                	<tr>
                		<td>client_id</td>
                		<td><input type="text" name="clientId" value="${clientId }" /></td>
                	</tr>
                	<tr>
                	<tr>
                		<td>state</td>
                		<td><input type="text" name="state" value="${state }" /></td>
                	</tr>
                	<tr>
                		<td>redirect_uri</td>
                		<td><input type="text" name="redirectUri" value="${redirect_uri}"/></td>
                	</tr>
                </table>
                <button type="submit" class="btn btn-primary">去登录</button>
                <span class="text-muted">将重定向到 'spring-oauth-server' 的登录页面</span>
            </form>

        </div>
    </div>
</div>

</body>
</html>