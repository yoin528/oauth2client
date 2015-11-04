package com.hy.oauth2.controller;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hy.oauth2.auth.handler.AccessTokenResponseHandler;
import com.hy.oauth2.auth.httpclient.HttpClientExecutor;
import com.hy.oauth2.auth.model.AccessToken;
import com.hy.oauth2.auth.model.OauthUser;
import com.hy.oauth2.auth.server.OauthService;

/**
 * @author LDZ   
 * @date 2015年11月2日 上午10:06:12 
 */
@Controller
public class MainController extends BaseController {
	@Value("#{properties['service-access-token-uri']}")
	protected String serviceTokenUri;
	@Value("#{properties['service-user-authorization-uri']}")
	protected String serviceAuthorizationUri;
	@Value("#{properties['application-host']}")
	protected String applicationHost;
	@Value("#{properties['unityUserInfoUri']}")
	protected String unityUserInfoUri;
	@Value("#{properties['mobileUserInfoUri']}")
	protected String mobileUserInfoUri;
	@Value("#{properties['appKey']}")
	protected String appKey;
	@Value("#{properties['appSecret']}")
	protected String appSecret;
	@Value("#{properties['authorizationCodeCallback']}")
	protected String authorizationCodeCallback;
	@Autowired
	private OauthService oauthService;
	
	@RequestMapping("/")
	public String main(){
//		System.out.println(serviceTokenUri);
		return "index";
	}
	/**
	 * 本地登陆，配置第三方登陆所需要的参数，如：汇业认证，腾讯认证，新浪认证
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String login(Model model){
		//配置第三方，汇业认证所需要的参数
		model.addAttribute("appKey", appKey);
		model.addAttribute("appSecret", appSecret);
		model.addAttribute("authorizationCodeCallback", authorizationCodeCallback);
		model.addAttribute("serviceAuthorizationUri", serviceAuthorizationUri);
		//可自动生成state状态码，用于回调时检验是否同一个请求，如果不传则在回调时不会返回state参数
		String uuid = UUID.randomUUID().toString();
		request.getSession().getServletContext().setAttribute("_state", uuid);
		model.addAttribute("state",uuid);
		return "login";
	}
	/**
	 * 汇业认证服务器回调接口
	 * @param code 授权code，用于换取token
	 * @param state 合法请求校验code
	 * @return
	 */
	@RequestMapping("/authorizationCodeCallback")
	public String authorizationCodeCallback(String code,String state,Model model){
		boolean validate = true;
		//存在state则校验是否相同
		if(!StringUtils.isEmpty(state)) {
			String _state = (String) request.getSession().getServletContext().getAttribute("_state");
			if(!state.equals(_state)) {
				validate = false;
			}
		}
		if(validate) {
			HttpClientExecutor executor = new HttpClientExecutor(serviceTokenUri);
			executor.addRequestParam("grantType", "authorization_code");
			executor.addRequestParam("clientId", appKey);
			executor.addRequestParam("clientSecret", appSecret);
			executor.addRequestParam("code", code);
			executor.addRequestParam("redirectUri", authorizationCodeCallback);
			AccessTokenResponseHandler responseHandler = new AccessTokenResponseHandler();
	        executor.execute(responseHandler);
	        AccessToken token = responseHandler.getAccessToken();
	        if(token.error()) {
	        	model.addAttribute("message", token.getErrorDescription());
                model.addAttribute("error", token.getError());
                return "oauth_error";
	        }
	        OauthUser authUser = oauthService.loadUnityUser(token.getAccessToken());
        	if (authUser.error()) {
                model.addAttribute("message", authUser.getErrorDescription());
                model.addAttribute("error", authUser.getError());
                return "oauth_error";
            }else {
            	request.getSession().setAttribute("user", authUser);
            }
		}
		return "redirect:/user/info";
	}
	@RequestMapping("/user/info")
	public String userInfo(Model model) {
		model.addAttribute("userDto",request.getSession().getAttribute("user"));
		return "userInfo";
	}
}