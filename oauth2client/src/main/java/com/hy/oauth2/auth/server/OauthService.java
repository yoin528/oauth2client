package com.hy.oauth2.auth.server;

import com.hy.oauth2.auth.model.AccessToken;
import com.hy.oauth2.auth.model.AuthAccessToken;
import com.hy.oauth2.auth.model.AuthCallback;
import com.hy.oauth2.auth.model.OauthUser;
import com.hy.oauth2.auth.model.RefreshAccessToken;

/**
 * @author Shengzhao Li
 */
public interface OauthService {
    AccessToken retrieveAccessToken(AuthAccessToken token);
    AuthAccessToken createAuthAccessToken(AuthCallback callback);
    OauthUser loadUnityUser(String accessToken);
    AccessToken retrievePasswordAccessToken(AuthAccessToken authAccessToken);
    AccessToken refreshAccessToken(RefreshAccessToken refreshAccessToken);
    AccessToken retrieveCredentialsAccessToken(AuthAccessToken authAccessToken);
}