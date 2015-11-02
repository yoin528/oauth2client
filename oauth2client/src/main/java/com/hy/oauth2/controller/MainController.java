package com.hy.oauth2.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping("/")
	public String main(){
//		System.out.println(serviceTokenUri);
		return "index";
	}
	@RequestMapping("/login")
	public String login(){
		System.out.println(serviceTokenUri);
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