package com.hy.oauth2.auth.model;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON data:
 * {"archived":false,"email":"unity@wdcy.cc","guid":"55b713df1c6f423e842ad68668523c49","phone":"","privileges":["UNITY"],"username":"unity"}
 *
 * @author Shengzhao Li
 */
public class OauthUser extends AbstractOauth {
	private static final long serialVersionUID = 1L;
	private String openid;
    private String email;
//    private String phone;
//    private String username;
    private String nickname;
    private List<String> authorities = new ArrayList<String>();
    public OauthUser() { }
    public OauthUser(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }
    
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
//    public String getPhone() {
//        return phone;
//    }
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//    public String getUsername() {
//        return username;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
    
	public String getNickname() {
		return nickname;
	}
	public List<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}