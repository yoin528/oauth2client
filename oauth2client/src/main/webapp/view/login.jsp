<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- saved from url=(0073)https://passport.csdn.net/account/login?from=http://my.csdn.net/my/mycsdn -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>帐号登录</title>
    <link type="text/css" rel="stylesheet" href="./static/login.css">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  <body>
    <div class="header"></div>
    <div class="main">
      <div class="container container-custom">
        <div class="row wrap-login">
          <div class="login-banner col-sm-6 col-md-7 col-lg-7 hidden-xs"></div>
          <div class="login-user col-xs-12 col-sm-6 col-md-5 col-lg-5" style="width:350px;">
            <div class="login-part">
              <h3>第三方帐号登录</h3>
              <div class="user-info">
                <div class="user-pass">
                  <form id="fm1" action="" method="post">
                    <input id="username" name="username" tabindex="1" placeholder="输入用户名/邮箱/手机号" class="user-name" type="text" value="">
                    <input id="password" name="password" tabindex="2" placeholder="输入密码" class="pass-word" type="password" value="" autocomplete="off">
					<div class="error-mess" style="display:none;">
						<span class="error-icon"></span><span id="error-message"></span>
					</div>
                    <div class="row forget-password">
                    	<span class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                        	<input type="checkbox" name="rememberMe" id="rememberMe" value="true" class="auto-login" tabindex="3">
                        	<label for="rememberMe">下次自动登录</label>
                        </span>
                        <span class="col-xs-6 col-sm-6 col-md-6 col-lg-6 forget tracking-ad" data-mod="popu_26">
                        	<a href="/account/fpwd?action=forgotpassword&amp;service=http%3A%2F%2Fmy.csdn.net%2Fmy%2Fmycsdn" tabindex="4">忘记密码</a>
                        </span>
                    </div>
                    <!-- 该参数可以理解成每个需要登录的用户都有一个流水号。只有有了webflow发放的有效的流水号，用户才可以说明是已经进入了webflow流程。否则，没有流水号的情况下，webflow会认为用户还没有进入webflow流程，从而会重新进入一次webflow流程，从而会重新出现登录界面。 -->
					<input type="hidden" name="lt" value="LT-332028-VRdLnj1T5doWHwepTSICc7Sd3x4Plc">
			 		<input type="hidden" name="execution" value="e2s1"> 
					<input type="hidden" name="_eventId" value="submit"> 
					<input class="logging" accesskey="l" value="登 录" tabindex="5" type="button"> 
                  </form>
                </div>
              </div>
              <div class="line"></div>
              <div class="third-part tracking-ad" data-mod="popu_27">
              	<span>第三方帐号登录</span>
              	<span><font color="red"></font></span>
              	<a href="${serviceAuthorizationUri}?response_type=code&client_id=${appKey}&redirect_uri=${authorizationCodeCallback}&state=${state}" class="huiye"></a>
              	<a id="baiduAuthorizationUrl" href="https://openapi.baidu.com/oauth/2.0/authorize?response_type=code&amp;client_id=cePqkUpKCBrcnQtARTNPxxQG&amp;redirect_uri=https%3A%2F%2Fpassport.csdn.net%2Faccount%2Flogin%3Foauth_provider%3DBaiduProvider" class="baidu"></a>
              	<a id="qqAuthorizationUrl" href="authorization_code" class="qq"></a>
              	<a id="githubAuthorizationUrl" href="https://github.com/login/oauth/authorize?client_id=4bceac0b4d39cf045157&amp;redirect_uri=https%3A%2F%2Fpassport.csdn.net%2Faccount%2Flogin%3Foauth_provider%3DGitHubProvider" class="github"></a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="footer"></div>
</body></html>