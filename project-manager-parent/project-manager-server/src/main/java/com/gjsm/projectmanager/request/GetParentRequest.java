package com.gjsm.projectmanager.request;

import com.gjsm.projectmanager.vo.ParentTask;

public class GetParentRequest {
	
	private ParentTask parentTask;
	private String action;
	
	public ParentTask getParentTask() {
		return parentTask;
	}

	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	

}
