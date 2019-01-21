package com.gjsm.projectmanager.service;

import com.gjsm.projectmanager.exception.ProjectManagerException;
import com.gjsm.projectmanager.request.GetParentRequest;
import com.gjsm.projectmanager.request.GetProjectRequest;
import com.gjsm.projectmanager.request.GetTaskRequest;
import com.gjsm.projectmanager.request.GetUserRequest;
import com.gjsm.projectmanager.response.GetParentResponse;
import com.gjsm.projectmanager.response.GetProjectResponse;
import com.gjsm.projectmanager.response.GetTaskResponse;
import com.gjsm.projectmanager.response.GetUserResponse;

public interface ProjectManagerService {
	
	
	public GetTaskResponse viewTask(int projectId) throws ProjectManagerException;
	
	public GetParentResponse getParentTask() throws ProjectManagerException;
	
	public GetProjectResponse getProject() throws ProjectManagerException;

	public GetUserResponse getUser() throws ProjectManagerException;
	
	public String updateTask(GetTaskRequest request) throws ProjectManagerException;
	
	public String updateParentTask(GetParentRequest request) throws ProjectManagerException;
	
	public String updateProject(GetProjectRequest request) throws ProjectManagerException;
	
	public String updateUser(GetUserRequest request) throws ProjectManagerException;
	
}