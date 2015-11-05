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
                		<td><input type="text" name="authorizationUri" value="${authorizationUri}" /></td>
                	</tr>
                	<tr>
                		<td>response_type</td>
                		<td><input type="text" name="code" value="code" /></td>
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
                		<td><input type="text" name="code" value="${redirectUri}"/></td>
                	</tr>
                </table>
                
                <input type="text" style="width:500px;" name="userAuthorizationUri" value="${userAuthorizationUri}"/>
                <div ng-show="visible">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">response_type</label>

                        <div class="col-sm-10">
                            <input type="text" name="responseType" readonly="readonly"
                                   class="form-control" ng-model="responseType" value="code"/>
                            <p class="help-block">固定值 'code'</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">scope</label>
                        <div class="col-sm-10">
                            <select name="scope" ng-model="scope" class="form-control">
                                <option value="read">read</option>
                                <option value="write">write</option>
                                <option value="read,write">read,write</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">client_id</label>
                        <div class="col-sm-10">
                            <input type="text" name="clientId" required="required"
                                   class="form-control" ng-model="clientId" value="${clientId}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">redirect_uri</label>

                        <div class="col-sm-10">
                            <input type="text" name="redirectUri" readonly="readonly" class="form-control"
                                   required="required" size="50" ng-model="redirectUri"/>

                            <p class="help-block">
                                redirect_uri 是在 'AuthorizationCodeController.java' 中定义的一个 URI, 用于检查server端返回的
                                'code'与'state',并发起对 access_token 的调用</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">state</label>

                        <div class="col-sm-10">
                            <input type="text" name="state" size="50" class="form-control" required="required"
                                   ng-model="state"/>

                            <p class="help-block">一个随机值, spring-oauth-server 将原样返回,用于检测是否为跨站请求(CSRF)等</p>
                        </div>
                    </div>

                    <div class="well well-sm">
                        <span class="text-muted">最终发给 spring-oauth-server的 URL:</span>
                        <br/>

                        <div class="text-primary">
                            {{userAuthorizationUri}}?response_type={{responseType}}&scope={{scope}}&client_id={{clientId}}&redirect_uri={{redirectUri}}&state={{state}}
                        </div>
                    </div>
                </div>
                <br/>
                <br/>
                <button type="submit" class="btn btn-primary">去登录</button>
                <span class="text-muted">将重定向到 'spring-oauth-server' 的登录页面</span>
            </form>

        </div>
    </div>
</div>

</body>
</html>