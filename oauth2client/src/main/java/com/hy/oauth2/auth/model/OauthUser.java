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
	private String openId;
    private String email;
    private String phone;
//    private String username;
    private String nickname;
    private List<String> privileges = new ArrayList<String>();
    public OauthUser() { }
    public OauthUser(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }
    
    public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
//    public String getUsername() {
//        return username;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
    public List<String> getPrivileges() {
        return privileges;
    }
    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}