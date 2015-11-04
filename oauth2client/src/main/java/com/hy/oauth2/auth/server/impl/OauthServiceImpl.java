package com.hy.oauth2.auth.server.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hy.oauth2.auth.handler.AccessTokenResponseHandler;
import com.hy.oauth2.auth.handler.OauthUserResponseHandler;
import com.hy.oauth2.auth.httpclient.HttpClientExecutor;
import com.hy.oauth2.auth.model.AccessToken;
import com.hy.oauth2.auth.model.AuthAccessToken;
import com.hy.oauth2.auth.model.AuthCallback;
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
    @Value("#{properties['access-token-uri']}")
    private String accessTokenUri;
    @Value("#{properties['unityUserInfoUri']}")
    private String unityUserInfoUri;
    public AccessToken retrieveAccessToken(AuthAccessToken token) {
        final String fullUri = token.getAccessTokenUri();
        LOG.debug("Get access_token URL: {}", fullUri);
        return loadAccessToken(fullUri, token.getAuthCodeParams());
    }
    public AuthAccessToken createAuthAccessToken(AuthCallback callback) {
        return new AuthAccessToken()
                .setAccessTokenUri(accessTokenUri)
                .setCode(callback.getCode());
    }
    public OauthUser loadUnityUser(String accessToken) {
        LOG.debug("Load Unity-User_Info by access_token = {}", accessToken);
        if (StringUtils.isEmpty(accessToken)) {
            return new OauthUser("Illegal 'access_token'", "'access_token' is empty");
        } else {
            HttpClientExecutor executor = new HttpClientExecutor(unityUserInfoUri);
            executor.addRequestParam("access_token", accessToken);
            OauthUserResponseHandler responseHandler = new OauthUserResponseHandler();
            executor.execute(responseHandler);
            return responseHandler.getUserDto();
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
