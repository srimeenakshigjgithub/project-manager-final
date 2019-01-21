package com.gjsm.projectmanager.vo;

public class Project {
	
private int projectId;
	
	private String project;
	
	private String startDate;
	
	private String endDate;
	
	private int priority;

	private int employeeId;
	
	private int taskCount;
	
	private int CompletedTaskCount;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}

	public int getCompletedTaskCount() {
		return CompletedTaskCount;
	}

	public void setCompletedTaskCount(int completedTaskCount) {
		CompletedTaskCount = completedTaskCount;
	}

}
