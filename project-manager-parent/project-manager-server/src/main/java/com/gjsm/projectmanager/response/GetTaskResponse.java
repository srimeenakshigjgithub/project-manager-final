package com.gjsm.projectmanager.response;

import java.util.List;

import com.gjsm.projectmanager.vo.Tasks;

public class GetTaskResponse {
	
	private List<Tasks> taskList;
	private String status;
	
	public List<Tasks> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<Tasks> taskList) {
		this.taskList = taskList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
