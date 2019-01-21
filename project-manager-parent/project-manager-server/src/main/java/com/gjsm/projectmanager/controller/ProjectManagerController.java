package com.gjsm.projectmanager.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gjsm.projectmanager.constants.ProjectManagerConstants;
import com.gjsm.projectmanager.exception.ProjectManagerException;
import com.gjsm.projectmanager.request.GetParentRequest;
import com.gjsm.projectmanager.request.GetProjectRequest;
import com.gjsm.projectmanager.request.GetTaskRequest;
import com.gjsm.projectmanager.request.GetUserRequest;
import com.gjsm.projectmanager.response.GetParentResponse;
import com.gjsm.projectmanager.response.GetProjectResponse;
import com.gjsm.projectmanager.response.GetTaskResponse;
import com.gjsm.projectmanager.response.GetUserResponse;
import com.gjsm.projectmanager.service.ProjectManagerService;

@RestController
public class ProjectManagerController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProjectManagerService projectManagerServiceImpl;
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/viewTask", method = RequestMethod.POST, headers = "Accept=*/*", consumes = {
			"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetTaskResponse viewTask(@RequestBody @Valid GetTaskRequest request) throws ProjectManagerException {
		GetTaskResponse getTaskResponse = new GetTaskResponse();
		try {
			getTaskResponse = projectManagerServiceImpl.viewTask(request.getTask().getProjectId());
		} catch(ProjectManagerException e) {
			logger.error("Error - ProjectManagerController viewTask : "+ e);
			throw new ProjectManagerException(e.getErrorCode(), e.getErrorMessage(), 500);
		}
		return getTaskResponse;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/updateTask", method = RequestMethod.POST, headers = "Accept=*/*", consumes = {
			"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetTaskResponse updateTask(@RequestBody @Valid GetTaskRequest request) throws ProjectManagerException {
		GetTaskResponse getTaskResponse = new GetTaskResponse();
		String status = ProjectManagerConstants.EMPTY;
		try {
			status = projectManagerServiceImpl.updateTask(request);
			getTaskResponse.setStatus(status);
		} catch(ProjectManagerException e) {
			logger.error("Error - ProjectManagerController updateTask : "+ e);
			throw new ProjectManagerException(e.getErrorCode(), e.getErrorMessage(), 500);
		}
		return getTaskResponse;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/getParentTask", method = RequestMethod.GET, headers = "Accept=*/*", produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetParentResponse getParentTask() throws ProjectManagerException {
		GetParentResponse getParentTaskResponse = new GetParentResponse();
		try {
			getParentTaskResponse = projectManagerServiceImpl.getParentTask();
		} catch(ProjectManagerException e) {
			logger.error("Error - ProjectManagerController getParentTask : "+ e);
			throw new ProjectManagerException(e.getErrorCode(), e.getErrorMessage(), 500);
		}
		return getParentTaskResponse;
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/updateParentTask", method = RequestMethod.POST, headers = "Accept=*/*", consumes = {
			"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetParentResponse updateParentTask(@RequestBody @Valid GetParentRequest request) throws ProjectManagerException {
		GetParentResponse getParentTaskResponse = new GetParentResponse();
		String status = ProjectManagerConstants.EMPTY;
		try {
			status = projectManagerServiceImpl.updateParentTask(request);
			getParentTaskResponse.setStatus(status);
		} catch(ProjectManagerException e) {
			logger.error("Error - ProjectManagerController updateParentTask : "+ e);
			throw new ProjectManagerException(e.getErrorCode(), e.getErrorMessage(), 500);
		}
		return getParentTaskResponse;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/getProject", method = RequestMethod.GET, headers = "Accept=*/*", produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetProjectResponse getProject() throws ProjectManagerException {
		GetProjectResponse getProjectResponse = new GetProjectResponse();
		try {
			getProjectResponse = projectManagerServiceImpl.getProject();
		} catch(ProjectManagerException e) {
			logger.error("Error - ProjectManagerController getProject : "+ e);
			throw new ProjectManagerException(e.getErrorCode(), e.getErrorMessage(), 500);
		}
		return getProjectResponse;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/updateProject", method = RequestMethod.POST, headers = "Accept=*/*", consumes = {
			"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetProjectResponse updateProject(@RequestBody @Valid GetProjectRequest request) throws ProjectManagerException {
		GetProjectResponse getProjectResponse = new GetProjectResponse();
		String status = ProjectManagerConstants.EMPTY;
		try {
			status = projectManagerServiceImpl.updateProject(request);
			getProjectResponse.setStatus(status);
		} catch(ProjectManagerException e) {
			logger.error("Error - ProjectManagerController updateProject : "+ e);
			throw new ProjectManagerException(e.getErrorCode(), e.getErrorMessage(), 500);
		}
		return getProjectResponse;
	}
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/getUser", method = RequestMethod.GET, headers = "Accept=*/*", produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetUserResponse getUser() throws ProjectManagerException {
		GetUserResponse getUserResponse = new GetUserResponse();
		try {
			getUserResponse = projectManagerServiceImpl.getUser();
		} catch(ProjectManagerException e) {
			logger.error("Error - ProjectManagerController getUser : "+ e);
			throw new ProjectManagerException(e.getErrorCode(), e.getErrorMessage(), 500);
		}
		return getUserResponse;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST, headers = "Accept=*/*", consumes = {
			"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetUserResponse updateUser(@RequestBody @Valid GetUserRequest request) throws ProjectManagerException {
		GetUserResponse getUserResponse = new GetUserResponse();
		String status = ProjectManagerConstants.EMPTY;
		try {
			status = projectManagerServiceImpl.updateUser(request);
			getUserResponse.setStatus(status);
		} catch(ProjectManagerException e) {
			logger.error("Error - ProjectManagerController updateUser : "+ e);
			throw new ProjectManagerException(e.getErrorCode(), e.getErrorMessage(), 500);
		}
		return getUserResponse;
	}
	
}