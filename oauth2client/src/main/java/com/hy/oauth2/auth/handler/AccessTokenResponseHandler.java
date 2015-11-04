package com.hy.oauth2.auth.handler;

import com.hy.oauth2.auth.httpclient.MkkHttpResponse;
import com.hy.oauth2.auth.model.AccessToken;

/**
 * 15-5-18
 *
 * @author Shengzhao Li
 */
public class AccessTokenResponseHandler extends AbstractResponseHandler<AccessToken> {


    private AccessToken accessToken;

    public AccessTokenResponseHandler() {
    }

    public void handleResponse(MkkHttpResponse response) {
        if (response.isResponse200()) {
            this.accessToken = responseToDto(response, new AccessToken());
        } else {
            this.accessToken = responseToErrorDto(response, new AccessToken());
        }
    }


    public AccessToken getAccessToken() {
        return accessToken;
    }
}
