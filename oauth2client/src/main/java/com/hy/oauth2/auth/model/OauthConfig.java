package com.hy.oauth2.auth.model;

import org.springframework.beans.factory.annotation.Value;

public class OauthConfig {
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
	@Value("#{properties['appKey']}")
	private String appKey;
	@Value("#{properties['appSecret']}")
	private String appSecret;
	@Value("#{properties['authorizationCodeCallback']}")
	private String authorizationCodeCallback;
	@Value("#{properties['userInfoUri']}")
	private String userInfoUri;
	public String getServiceTokenUri() {
		return serviceTokenUri;
	}
	public String getServiceAuthorizationUri() {
		return serviceAuthorizationUri;
	}
	public String getApplicationHost() {
		return applicationHost;
	}
	public String getUnityUserInfoUri() {
		return unityUserInfoUri;
	}
	public String getMobileUserInfoUri() {
		return mobileUserInfoUri;
	}
	public String getAppKey() {
		return appKey;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public String getAuthorizationCodeCallback() {
		return authorizationCodeCallback;
	}
	public String getUserInfoUri() {
		return userInfoUri;
	}
}
