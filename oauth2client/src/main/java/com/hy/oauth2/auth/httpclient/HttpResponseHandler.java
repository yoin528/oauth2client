package com.hy.oauth2.auth.httpclient;

/**
 * @author Shengzhao Li
 */

public interface HttpResponseHandler {
    public void handleResponse(MkkHttpResponse response);
}