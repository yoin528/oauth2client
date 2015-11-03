package com.hy.oauth2.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("/")
	public String main(){
//		System.out.println(serviceTokenUri);
		return "index";
	}
	@RequestMapping("/login")
	public String login(Model model){
		model.addAttribute("appKey", appKey);
		model.addAttribute("appSecret", appSecret);
		model.addAttribute("authorizationCodeCallback", authorizationCodeCallback);
		model.addAttribute("serviceAuthorizationUri", serviceAuthorizationUri);
		return "login";
	}
	@RequestMapping("/authorizationCodeCallback")
	public String authorizationCodeCallback(){
		Map params = request.getParameterMap();
		Set keys = params.keySet();
		for(Object key : keys) {
			System.out.println(key+","+params.get(key).toString());
		}
		return "login";
	}
	
	
	
	@RequestMapping("/user/info")
	public String userInfo() {
		return "userInfo";
	}
}