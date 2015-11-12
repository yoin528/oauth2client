package com.hy.oauth2.controller;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hy.oauth2.auth.model.AccessToken;
import com.hy.oauth2.auth.model.AuthAccessToken;
import com.hy.oauth2.auth.model.AuthCallback;
import com.hy.oauth2.auth.model.AuthorizationCode;
import com.hy.oauth2.auth.model.OauthConfig;
import com.hy.oauth2.auth.model.OauthUser;
import com.hy.oauth2.auth.server.OauthService;

/**
 * @author LDZ    
 * @date 2015年11月2日 上午10:06:12 
 */
@Controller
public class MainController extends BaseController {
	@Autowired
	private OauthConfig oauthConfig;
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
		model.addAttribute("appKey", oauthConfig.getAppKey());
		model.addAttribute("appSecret", oauthConfig.getAppSecret());
		model.addAttribute("authorizationCodeCallback", oauthConfig.getAuthorizationCodeCallback());
		model.addAttribute("serviceAuthorizationUri", oauthConfig.getServiceAuthorizationUri());
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
				model.addAttribute("message", "非法状态码: " + state);
	            model.addAttribute("error", "无效的 state");
	            return "oauth_error";
			}
		}
		if(validate) {
			AuthAccessToken accessToken = new AuthAccessToken();
            accessToken.setAccessTokenUri(oauthConfig.getServiceTokenUri());
            accessToken.setClientId(oauthConfig.getAppKey());
            accessToken.setClientSecret(oauthConfig.getAppSecret());
            accessToken.setRedirectUri(oauthConfig.getAuthorizationCodeCallback());
            accessToken.setCode(code);
            //使用code取得token
            final AccessToken token = oauthService.retrieveAccessToken(accessToken);
	        if(token.error()) {
	        	model.addAttribute("message", token.getErrorDescription());
                model.addAttribute("error", token.getError());
                model.addAttribute("desc", token.getOriginalText());
                return "oauth_error";
	        }
	        //使用token换取用户信息
//	        Map<String,Object> data = oauthService.loadData(token.getAccessToken(), oauthConfig.getUserInfoUri());
//	        if(data.get("error")==null) {
//	        	Set<String> keys = data.keySet();
//	        	for(String key:keys) {
//	        		System.out.println(key+","+data.get(key));
//	        	}
//	        }else {
//	        	model.addAttribute("message", data.get("error"));
//                model.addAttribute("error", data.get("desc"));
//                model.addAttribute("desc", data.get("content"));
//                return "oauth_error";
//	        }
	        OauthUser authUser = oauthService.loadUnityUser(token.getAccessToken(),oauthConfig.getUserInfoUri());
        	if (authUser.error()) {
                model.addAttribute("message", authUser.getErrorDescription());
                model.addAttribute("error", authUser.getError());
                model.addAttribute("desc", token.getOriginalText());
                return "oauth_error";
            }else {
            	request.getSession().setAttribute("user", authUser);
            	request.getSession().setAttribute("_token", token);
            	model.addAttribute("user", authUser);
            }
	        
		}
		return "redirect:/";
	}
	@RequestMapping("/user/info")
	public String userInfo(Model model) {
		model.addAttribute("userDto", member);
		return "userInfo";
	}
	
	
	//--------------测试---------------
	
	//使用authorization_code，跳转到authorization_code页面准备提交
	@RequestMapping("/authorization_code")
	public String authorization_code(Model model) {
		//服务器认证地址
		model.addAttribute("userAuthorizationUri", oauthConfig.getServiceAuthorizationUri());
        //本地回调地址
        model.addAttribute("redirect_uri", "http://www.p2p.com/client/authorization_code_callback");
        //客户端id
        model.addAttribute("clientId", oauthConfig.getAppKey());
        //状态码
        model.addAttribute("state", UUID.randomUUID().toString());
		return "test/authorization_code";
	}
	
	@RequestMapping(value = "authorization_code_httpclient")
    public String submitAuthorizationCode(AuthorizationCode codeDto, HttpServletRequest request) throws Exception {
		request.getSession().getServletContext().setAttribute("_state", codeDto.getState());
        final String fullUri = codeDto.getFullUri();
        return "redirect:" + fullUri;
    }
	
	@RequestMapping(value = "authorization_code_callback")
    public String authorizationCodeCallback(AuthCallback callbackDto, Model model) throws Exception {
		boolean validate = true;
		//存在state则校验是否相同
		if(!StringUtils.isEmpty(callbackDto.getState())) {
			String _state = (String) request.getSession().getServletContext().getAttribute("_state");
			if(!callbackDto.getState().equals(_state)) {
				validate = false;
			}
		}
        if (callbackDto.error()) {
            //Server response error
            model.addAttribute("message", callbackDto.getError_description());
            model.addAttribute("error", callbackDto.getError());
            return "oauth_error";
        } else if (validate) {
            //Go to retrieve access_token form
            AuthAccessToken accessTokenDto = oauthService.createAuthAccessToken(callbackDto);
            accessTokenDto.setAccessTokenUri(oauthConfig.getServiceTokenUri());
            accessTokenDto.setClientId(oauthConfig.getAppKey());
            accessTokenDto.setClientSecret(oauthConfig.getAppSecret());
            accessTokenDto.setRedirectUri("http://www.p2p.com/client/authorization_code_callback");
            model.addAttribute("accessTokenDto", accessTokenDto);
			return "test/code_access_token";
        } else {
            //illegal state
            model.addAttribute("message", "Illegal \"state\": " + callbackDto.getState());
            model.addAttribute("error", "Invalid state");
            return "auth_error";
        }

    }
	
	@RequestMapping(value = "code_access_token", method = RequestMethod.POST)
    public String codeAccessToken(AuthAccessToken tokenDto, Model model) throws Exception {
        AccessToken accessTokenDto = oauthService.retrieveAccessToken(tokenDto);
        if (accessTokenDto.error()) {
            model.addAttribute("message", accessTokenDto.getErrorDescription());
            model.addAttribute("error", accessTokenDto.getError());
            return "oauth_error";
        } else {
            model.addAttribute("accessTokenDto", accessTokenDto);
            model.addAttribute("unityUserInfoUri", oauthConfig.getUnityUserInfoUri());
            return "test/access_token_result";
        }
    }
	@RequestMapping("unity_user_info")
	public String unityUserInfo(String access_token, Model model) {
		OauthUser userDto = oauthService.loadUnityUser(access_token,oauthConfig.getUserInfoUri());
		System.out.println(userDto);
//        if (userDto.error()) {
//            //error
//            model.addAttribute("message", userDto.getErrorDescription());
//            model.addAttribute("error", userDto.getError());
//            return "redirect:oauth_error";
//        } else {
//            model.addAttribute("userDto", userDto);
//        }

            return "test/unity_user_info";
    }
	
}