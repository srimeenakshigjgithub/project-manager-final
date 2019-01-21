package com.gjsm.projectmanager.response;

import java.util.List;

import com.gjsm.projectmanager.vo.Project;

public class GetProjectResponse {

	private List<Project> projectList;
	private String status;
	
	public List<Project> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
