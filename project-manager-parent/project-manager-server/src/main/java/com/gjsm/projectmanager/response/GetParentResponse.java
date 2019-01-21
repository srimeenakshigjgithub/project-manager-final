package com.gjsm.projectmanager.response;

import java.util.List;

import com.gjsm.projectmanager.vo.ParentTask;

public class GetParentResponse {
	
	private List<ParentTask> parentTaskList;
	private String status;
	public List<ParentTask> getParentTaskList() {
		return parentTaskList;
	}
	public void setParentTaskList(List<ParentTask> parentTaskList) {
		this.parentTaskList = parentTaskList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
