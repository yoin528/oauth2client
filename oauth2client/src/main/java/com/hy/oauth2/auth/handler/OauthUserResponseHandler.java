package com.hy.oauth2.auth.handler;

import com.hy.oauth2.auth.httpclient.MkkHttpResponse;
import com.hy.oauth2.auth.model.OauthUser;

/**
 * 15-5-18
 *
 * @author Shengzhao Li
 */
public class OauthUserResponseHandler extends AbstractResponseHandler<OauthUser> {


    private OauthUser userDto;

    public OauthUserResponseHandler() {
    }

    /*
    * Response is JSON or  XML (failed)
    *
    *  Error data:
    *  <oauth><error_description>Invalid access token: 3420d0e0-ed77-45e1-8370-2b55af0a62e8</error_description><error>invalid_token</error></oauth>
    *
    * */
    public void handleResponse(MkkHttpResponse response) {
        if (response.isResponse200()) {
            this.userDto = responseToDto(response, new OauthUser());
        } else {
            this.userDto = responseToErrorDto(response, new OauthUser());
        }
    }


    public OauthUser getUserDto() {
        return userDto;
    }


}
