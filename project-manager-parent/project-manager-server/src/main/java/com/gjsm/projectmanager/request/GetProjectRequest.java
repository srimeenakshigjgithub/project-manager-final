package com.gjsm.projectmanager.request;

import com.gjsm.projectmanager.vo.Project;

public class GetProjectRequest {
	
	private Project project;
	private String action;
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	

}
