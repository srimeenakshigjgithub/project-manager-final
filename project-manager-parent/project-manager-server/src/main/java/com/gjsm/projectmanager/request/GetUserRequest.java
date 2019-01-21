package com.gjsm.projectmanager.request;

import com.gjsm.projectmanager.vo.User;

public class GetUserRequest {
	
	private User user;
	private String action;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
