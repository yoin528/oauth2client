package com.hy.oauth2.utils;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author LDZ   
 * @date 2015年11月2日 上午9:43:58 
 */
public class PropertiesUtils {
	@Value("#{properties['service-access-token-uri']}")
    private String serviceTokenUri;
	@Value("#{properties['service-user-authorization-uri']}")
	private String serviceAuthorizationUri;
	@Value("#{properties['application-host']}")
	private String applicationHost;
	@Value("#{properties['unityUserInfoUri']}")
	private String unityUserInfoUri;
	@Value("#{properties['mobileUserInfoUri']}")
	private String mobileUserInfoUri;
	private static final PropertiesUtils instance = new PropertiesUtils();
	
	
	private PropertiesUtils() {
	}
	public static PropertiesUtils getInstance() {
		return instance;
	}
	/**
	 * 取得服务端获取令牌url
	 * @author LDZ   
	 * @date 2015年11月2日 上午9:51:59 
	 * @return
	 */
	public  String getServiceTokenUri() {
		return serviceTokenUri;
	}
	/**
	 * 取得服务端授权认证 url
	 * @author LDZ   
	 * @date 2015年11月2日 上午9:52:23 
	 * @return
	 */
	public  String getServiceAuthorizationUri() {
		return serviceAuthorizationUri;
	}
	/**
	 * 取得本地客户端服务器地址
	 * @author LDZ   
	 * @date 2015年11月2日 上午9:52:37 
	 * @return
	 */
	public  String getApplicationHost() {
		return applicationHost;
	}
	/**
	 * 用户信息接口地址
	 * @author LDZ   
	 * @date 2015年11月2日 上午9:52:52 
	 * @return
	 */
	public  String getUnityUserInfoUri() {
		return unityUserInfoUri;
	}
	/**
	 * 手机端信息接口地址
	 * @author LDZ   
	 * @date 2015年11月2日 上午9:52:56 
	 * @return
	 */
	public  String getMobileUserInfoUri() {
		return mobileUserInfoUri;
	}
	
	
}
