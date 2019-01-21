package com.gjsm.projectmanager.response;

import java.util.List;

import com.gjsm.projectmanager.vo.User;

public class GetUserResponse {
	
	private List<User> userList;
	private String status;
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
