package com.hy.oauth2.auth.server.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.oauth2.auth.handler.AccessTokenResponseHandler;
import com.hy.oauth2.auth.handler.DefaultResponseHandler;
import com.hy.oauth2.auth.handler.OauthUserResponseHandler;
import com.hy.oauth2.auth.httpclient.HttpClientExecutor;
import com.hy.oauth2.auth.model.AccessToken;
import com.hy.oauth2.auth.model.AuthAccessToken;
import com.hy.oauth2.auth.model.AuthCallback;
import com.hy.oauth2.auth.model.OauthConfig;
import com.hy.oauth2.auth.model.OauthUser;
import com.hy.oauth2.auth.model.RefreshAccessToken;
import com.hy.oauth2.auth.server.OauthService;

/**
 * 15-5-18
 *
 * @author Shengzhao Li
 */
@Service("oauthService")
public class OauthServiceImpl implements OauthService {
    private static final Logger LOG = LoggerFactory.getLogger(OauthServiceImpl.class);
    @Autowired
    private OauthConfig oauthConfig;
    public AccessToken retrieveAccessToken(AuthAccessToken token) {
        final String fullUri = token.getAccessTokenUri();
        LOG.debug("Get access_token URL: {}", fullUri);
        return loadAccessToken(fullUri, token.getAuthCodeParams());
    }
    public AuthAccessToken createAuthAccessToken(AuthCallback callback) {
        return new AuthAccessToken()
                .setAccessTokenUri(oauthConfig.getServiceTokenUri())
                .setCode(callback.getCode());
    }
    public OauthUser loadUnityUser(String accessToken,String uri) {
        LOG.debug("Load Unity-User_Info by access_token = {}", accessToken);
        if (StringUtils.isEmpty(accessToken)) {
            return new OauthUser("无效的access_token", "'access_token'是空的!");
        } else {
        	if(uri==null||"".equals(uri)) {
        		uri = oauthConfig.getUnityUserInfoUri();
        	}
            HttpClientExecutor executor = new HttpClientExecutor(uri);
            executor.addRequestParam("access_token", accessToken);
            OauthUserResponseHandler responseHandler = new OauthUserResponseHandler();
            executor.execute(responseHandler);
            return responseHandler.getUserDto();
        }

    }
    
    public Map<String, Object> loadData(String accessToken, String uri) {
    	LOG.debug("Load Data by access_token = {}", accessToken);
        if (StringUtils.isEmpty(accessToken)) {
        	Map<String,Object> data = new HashMap<String,Object>();
        	data.put("msg", "无效的access_token!");
        	return data;
        } else {
            HttpClientExecutor executor = new HttpClientExecutor(uri);
            executor.addRequestParam("access_token", accessToken);
            DefaultResponseHandler responseHandler = new DefaultResponseHandler();
            executor.execute(responseHandler);
            return responseHandler.getData();
        }
	}
	public AccessToken retrievePasswordAccessToken(AuthAccessToken authAccessToken) {
        final String fullUri = authAccessToken.getAccessTokenUri();
        LOG.debug("Get [password] access_token URL: {}", fullUri);
        return loadAccessToken(fullUri, authAccessToken.getAccessTokenParams());
    }
    public AccessToken refreshAccessToken(RefreshAccessToken refreshAccessToken) {
        final String fullUri = refreshAccessToken.getRefreshAccessTokenUri();
        LOG.debug("Get refresh_access_token URL: {}", fullUri);
        return loadAccessToken(fullUri, refreshAccessToken.getRefreshTokenParams());
    }
    public AccessToken retrieveCredentialsAccessToken(AuthAccessToken authAccessToken) {
        final String uri = authAccessToken.getAccessTokenUri();
        LOG.debug("Get [{}] access_token URL: {}", authAccessToken.getGrantType(), uri);
        return loadAccessToken(uri, authAccessToken.getCredentialsParams());
    }
    private AccessToken loadAccessToken(String fullUri, Map<String, String> params) {
        HttpClientExecutor executor = new HttpClientExecutor(fullUri);
        for (String key : params.keySet()) {
            executor.addRequestParam(key, params.get(key));
        }
        AccessTokenResponseHandler responseHandler = new AccessTokenResponseHandler();
        executor.execute(responseHandler);
        return responseHandler.getAccessToken();
    }
}
