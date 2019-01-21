package com.gjsm.projectmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gjsm.projectmanager.constants.ProjectManagerConstants;
import com.gjsm.projectmanager.dao.ParentTaskRepository;
import com.gjsm.projectmanager.dao.ProjectRepository;
import com.gjsm.projectmanager.dao.TaskRepository;
import com.gjsm.projectmanager.dao.UserRepository;
import com.gjsm.projectmanager.exception.ProjectManagerException;
import com.gjsm.projectmanager.jpaentity.ParentEntity;
import com.gjsm.projectmanager.jpaentity.ProjectEntity;
import com.gjsm.projectmanager.jpaentity.TaskEntity;
import com.gjsm.projectmanager.jpaentity.UserEntity;
import com.gjsm.projectmanager.request.GetParentRequest;
import com.gjsm.projectmanager.request.GetProjectRequest;
import com.gjsm.projectmanager.request.GetTaskRequest;
import com.gjsm.projectmanager.request.GetUserRequest;
import com.gjsm.projectmanager.response.GetParentResponse;
import com.gjsm.projectmanager.response.GetProjectResponse;
import com.gjsm.projectmanager.response.GetTaskResponse;
import com.gjsm.projectmanager.response.GetUserResponse;
import com.gjsm.projectmanager.service.ProjectManagerService;
import com.gjsm.projectmanager.util.DateUtil;
import com.gjsm.projectmanager.vo.ParentTask;
import com.gjsm.projectmanager.vo.Project;
import com.gjsm.projectmanager.vo.Tasks;
import com.gjsm.projectmanager.vo.User;

@Service
@Component
public class ProjectManagerServiceImpl implements ProjectManagerService, ProjectManagerConstants {

		private final Logger logger = LoggerFactory.getLogger(this.getClass());

		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private ProjectRepository projectRepository;
		
		@Autowired
		private ParentTaskRepository parentTaskRepository;
		
		@Autowired
		private TaskRepository taskRepository;
		
		@Override
		public GetTaskResponse viewTask(int projectId) throws ProjectManagerException {
			GetTaskResponse getTaskResponse = new GetTaskResponse();
			List<Tasks> taskVOList = null;
			try {
				List<TaskEntity> taskEntityList = getTaskByProjectId(projectId);
				if(null != taskEntityList && !taskEntityList.isEmpty()) {
		        	taskVOList = new ArrayList<>(); 
		        	
		        	for(TaskEntity taskEntity : taskEntityList) {
			        	Tasks task = new Tasks();
			        	task.setTaskId(taskEntity.getTaskId());
			        	task.setTask(taskEntity.getTask());
			        	
			        	task.setParentId(null != taskEntity.getParentTaskEntity() ? taskEntity.getParentTaskEntity().getParentId() : 0);
			        	task.setParentTaskName(null != taskEntity.getParentTaskEntity() ? taskEntity.getParentTaskEntity().getParentTask() : "");
			        	
			        	task.setProjectId(taskEntity.getProjectEntity().getProjectId());
			        	task.setProjectName(taskEntity.getProjectEntity().getProject());
			        	
			        	task.setUserId(taskEntity.getProjectEntity().getUserEntity().getUserId());
			        	task.setUserFirstName(taskEntity.getProjectEntity().getUserEntity().getFirstName());
			        	task.setUserLastName(taskEntity.getProjectEntity().getUserEntity().getLastName());
			        	
			        	task.setPriority(taskEntity.getPriority());
			        	task.setStartDate(DateUtil.dateToString(taskEntity.getStartDate()));
			        	task.setEndDate(DateUtil.dateToString(taskEntity.getEndDate()));
			        	task.setStatus(taskEntity.getStatus());
			        	
			        	taskVOList.add(task);
		        	}
		        }
		        getTaskResponse.setTaskList(taskVOList);
		        getTaskResponse.setStatus("Success");
			} catch(Exception e) {
				//logger.error("Error - ProjectManagerServiceImpl viewTask : " + e);
				//throw new ProjectManagerException(TECHNICAL_ERROR_CODE, TECHNICAL_ERROR_MESSAGE, HTTP_STATUS_500);
			}
			return getTaskResponse;
		}

		@Override
		public GetParentResponse getParentTask() throws ProjectManagerException {
			GetParentResponse getParentTaskResponse = new GetParentResponse();
			List<ParentTask> parentTaskVOList = null;
			List<ParentEntity> parentTaskEntityList = null;
			try {
				parentTaskEntityList = parentTaskRepository.findAll();
				if(null != parentTaskEntityList && !parentTaskEntityList.isEmpty()) {
		        	parentTaskVOList = new ArrayList<>();
		        	
		        	for(ParentEntity parentTaskEntity : parentTaskEntityList) {
		        		ParentTask parentTask = new ParentTask();
		        		parentTask.setParentId(parentTaskEntity.getParentId());
		        		parentTask.setParentTaskName(parentTaskEntity.getParentTask());
		        		parentTask.setProjectId(null != parentTaskEntity.getProjectEntity() ? parentTaskEntity.getProjectEntity().getProjectId() : 0);
			        	
		        		parentTaskVOList.add(parentTask);
		        	}
		        }
		        getParentTaskResponse.setParentTaskList(parentTaskVOList);
		        getParentTaskResponse.setStatus("Success");
			} catch(Exception e) {
				//logger.error("Error - ProjectManagerServiceImpl getParentTask : " + e);
				//throw new ProjectManagerException(TECHNICAL_ERROR_CODE, TECHNICAL_ERROR_MESSAGE, HTTP_STATUS_500);
			}
			return getParentTaskResponse;
		}
		
		@Override
		public GetProjectResponse getProject() throws ProjectManagerException {
			GetProjectResponse getProjectResponse = new GetProjectResponse();
			List<Project> projectVOList = null;
			int completedTaskCount = 0;
			try {
				List<ProjectEntity> projectEntityList = projectRepository.findAll();
				if(null != projectEntityList && !projectEntityList.isEmpty()) {
		        	projectVOList = new ArrayList<>();
		        	
		        	for(ProjectEntity projectEntity : projectEntityList) {
		        		Project project = new Project();
		        		project.setProjectId(projectEntity.getProjectId());
		        		project.setProject(projectEntity.getProject());
		        		project.setPriority(projectEntity.getPriority());
		        		project.setStartDate(DateUtil.dateToString(projectEntity.getStartDate()));
		        		project.setEndDate(DateUtil.dateToString(projectEntity.getEndDate()));
		        		project.setEmployeeId(null != projectEntity.getUserEntity() ? projectEntity.getUserEntity().getUserId() : 0);
			        	
		        		List<TaskEntity> taskEntityList = getTaskByProjectId(projectEntity.getProjectId());
		        		if(null != taskEntityList && !taskEntityList.isEmpty()) {
		        			for(TaskEntity taskEntity : taskEntityList) {
		        				if(null != taskEntity.getStatus() && STATUS_COMPLETED.equalsIgnoreCase(taskEntity.getStatus())) {
		        					completedTaskCount ++;
		        				}
		        			}
		        			project.setTaskCount(taskEntityList.size());
		        			project.setCompletedTaskCount(completedTaskCount);
		        			completedTaskCount = 0;
		        		}
			        	projectVOList.add(project);
		        	}
		        }
				getProjectResponse.setProjectList(projectVOList);
				getProjectResponse.setStatus("Success");
			} catch(Exception e) {
				//logger.error("Error - ProjectManagerServiceImpl getProject : " + e);
				//throw new ProjectManagerException(TECHNICAL_ERROR_CODE, TECHNICAL_ERROR_MESSAGE, HTTP_STATUS_500);
			}
			return getProjectResponse;
		}

		@Override
		public GetUserResponse getUser() throws ProjectManagerException {
			GetUserResponse getUserResponse = new GetUserResponse();
			List<User> userVOList = null;
			try {
				List<UserEntity> userEntityList = userRepository.findAll();
				if(null != userEntityList && !userEntityList.isEmpty()) {
		        	userVOList = new ArrayList<>();
		        	
		        	for(UserEntity userEntity : userEntityList) {
			        	User user = new User();
			        	user.setUserId(userEntity.getUserId());
			        	user.setFirstName(userEntity.getFirstName());
			        	user.setLastName(userEntity.getLastName());
			        	user.setEmployeeId(userEntity.getEmployeeId());
			        	
			        	userVOList.add(user);
		        	}
		        }
				getUserResponse.setUserList(userVOList);
				getUserResponse.setStatus("Success");
			} catch(Exception e) {
				//logger.error("Error - ProjectManagerServiceImpl getUser : " + e);
				//throw new ProjectManagerException(TECHNICAL_ERROR_CODE, TECHNICAL_ERROR_MESSAGE, HTTP_STATUS_500);
			}
			return getUserResponse;
		}

		@Override
		public String updateTask(GetTaskRequest request) throws ProjectManagerException {
			TaskEntity taskEntity = new TaskEntity();
			String status = EMPTY;
			try {
				ProjectEntity projectEntity = projectRepository.findByProjectId(request.getTask().getProjectId());
				ParentEntity parentTaskEntity = parentTaskRepository.findByParentTaskId(request.getTask().getParentId());
				
				taskEntity.setTaskId(request.getTask().getTaskId());
				taskEntity.setTask(request.getTask().getTask());
				taskEntity.setStartDate(DateUtil.stringToDate(request.getTask().getStartDate()));
				taskEntity.setEndDate(DateUtil.stringToDate(request.getTask().getEndDate()));
				taskEntity.setPriority(request.getTask().getPriority());
				taskEntity.setProjectEntity(projectEntity);
				taskEntity.setParentTaskEntity(parentTaskEntity);
				taskEntity.setStatus(request.getTask().getStatus());
				taskRepository.save(taskEntity);
				status = "Success";
			} catch(Exception e) {
				//logger.error("Error - ProjectManagerServiceImpl updateTask : " + e);
				//throw new ProjectManagerException(TECHNICAL_ERROR_CODE, TECHNICAL_ERROR_MESSAGE, HTTP_STATUS_500);
			}
			return status;
		}

		@Override
		public String updateParentTask(GetParentRequest request) throws ProjectManagerException {
			String status = EMPTY;
			ParentEntity parentTaskEntity = new ParentEntity();
			try {
				if(ACTION_DELETE.equalsIgnoreCase(request.getAction())) {
					parentTaskEntity.setParentId(request.getParentTask().getParentId());
					if(deleteTask(request.getParentTask().getParentId())) {
						parentTaskRepository.delete(parentTaskEntity);
					} else {
						//logger.error("updateParentTask - Task deletion failed");
						status = USER_DELETION_FAILED;
					}
				} else {
					ProjectEntity projectEntity = projectRepository.findByProjectId(request.getParentTask().getProjectId());
					
					parentTaskEntity.setParentId(request.getParentTask().getParentId());
					parentTaskEntity.setParentTask(request.getParentTask().getParentTaskName());
					parentTaskEntity.setProjectEntity(projectEntity);
					parentTaskRepository.save(parentTaskEntity);
				}
				
				status = "Success";
			} catch(Exception e) {
				//logger.error("Error - ProjectManagerServiceImpl updateParentTask : " + e);
				//throw new ProjectManagerException(TECHNICAL_ERROR_CODE, TECHNICAL_ERROR_MESSAGE, HTTP_STATUS_500);
			}
			return status;
		}
		
		@Override
		public String updateProject(GetProjectRequest request) throws ProjectManagerException {
			String status = EMPTY;
			ProjectEntity projectEntity = new ProjectEntity();
			try {
				if(ACTION_DELETE.equalsIgnoreCase(request.getAction())) {
					projectEntity.setProjectId(request.getProject().getProjectId());
					if(deleteTask(request.getProject().getProjectId())) {
						if(deleteParentTask(request.getProject().getProjectId())) {
							projectRepository.delete(projectEntity);
						} else {
							logger.error("updateProject - Parent Task deletion failed");
							status = USER_DELETION_FAILED;
						}
					} else {
						logger.error("updateProject - Task deletion failed");
						status = USER_DELETION_FAILED;
					}
				} else {
					UserEntity userEntity = userRepository.findByUserId(request.getProject().getEmployeeId());
					
					projectEntity.setProjectId(request.getProject().getProjectId());
					projectEntity.setProject(request.getProject().getProject());
					projectEntity.setPriority(request.getProject().getPriority());
					projectEntity.setStartDate(DateUtil.stringToDate(request.getProject().getStartDate()));
					projectEntity.setEndDate(DateUtil.stringToDate(request.getProject().getEndDate()));
					projectEntity.setUserEntity(userEntity);
					projectRepository.save(projectEntity);
				}
				status = "Success";
			} catch(Exception e) {
				//logger.error("Error - ProjectManagerServiceImpl updateProject : " + e);
				//throw new ProjectManagerException(TECHNICAL_ERROR_CODE, TECHNICAL_ERROR_MESSAGE, HTTP_STATUS_500);
			}
			return status;
		}

		@Override
		public String updateUser(GetUserRequest request) throws ProjectManagerException {
			UserEntity userEntity = new UserEntity();
			String status = EMPTY;
			try {
	      userEntity.setUserId(request.getUser().getUserId());
				if(ACTION_DELETE.equalsIgnoreCase(request.getAction())) {
					status = deleteUser(request, userEntity);
				} else {
					userEntity.setFirstName(request.getUser().getFirstName());
					userEntity.setLastName(request.getUser().getLastName());
					userEntity.setEmployeeId(request.getUser().getEmployeeId());
					userRepository.save(userEntity);
					status = "Success";
				}
			} catch(Exception e) {
				//logger.error("Error - ProjectManagerServiceImpl updateUser : " + e);
				//throw new ProjectManagerException(TECHNICAL_ERROR_CODE, TECHNICAL_ERROR_MESSAGE, HTTP_STATUS_500);
			}
			return status;
		}
		
		private List<TaskEntity> getTaskByProjectId(int projectId) throws ProjectManagerException {
			List<TaskEntity> taskEntityList = null;
			try {
				taskEntityList = taskRepository.findByProjectId(projectId);
			} catch(Exception e) {
				//logger.error("Error - ProjectManagerServiceImpl getTaskByProjectId : " + e);
				//throw new ProjectManagerException(TECHNICAL_ERROR_CODE, TECHNICAL_ERROR_MESSAGE, HTTP_STATUS_500);
			}
			return taskEntityList;
		}
		
		private boolean deleteTask(int projectId) throws ProjectManagerException {
			boolean flag = false;
			try {
				taskRepository.deleteByProjectId(projectId);
				flag = true;
			} catch(Exception e) {
				//logger.error("Error - ProjectManagerServiceImpl deleteTask : " + e);
				//throw new ProjectManagerException(TECHNICAL_ERROR_CODE, TECHNICAL_ERROR_MESSAGE, HTTP_STATUS_500);
			}
			return flag;
		}
		
		private boolean deleteParentTask(int projectId) throws ProjectManagerException {
			boolean flag = false;
			try {
				parentTaskRepository.deleteByProjectId(projectId);
				flag = true;
			} catch(Exception e) {
				//logger.error("Error - ProjectManagerServiceImpl deleteParentTask : " + e);
				//throw new ProjectManagerException(TECHNICAL_ERROR_CODE, TECHNICAL_ERROR_MESSAGE, HTTP_STATUS_500);
			}
			return flag;
		}
		
		private boolean deleteProject(int userId) throws ProjectManagerException {
			boolean flag = false;
			try {
				projectRepository.deleteByUserId(userId);
				flag = true;
			} catch(Exception e) {
				//logger.error("Error - ProjectManagerServiceImpl deleteProject : " + e);
				//throw new ProjectManagerException(TECHNICAL_ERROR_CODE, TECHNICAL_ERROR_MESSAGE, HTTP_STATUS_500);
			}
			return flag;
		}
		
		private String deleteUser(GetUserRequest request, UserEntity userEntity) throws ProjectManagerException {
			String status = "Success";
			ProjectEntity project = projectRepository.findByUserId(request.getUser().getUserId());
			if(null != project) {
				userEntity.setUserId(request.getUser().getUserId());
				if(deleteTask(project.getProjectId())) {
					if(deleteParentTask(project.getProjectId())) {
						if(deleteProject(request.getUser().getUserId())) {
							userRepository.delete(userEntity);
						} else {
							//logger.error("deleteUser - Project deletion failed");
							status = USER_DELETION_FAILED;
						}
					} else {
						//logger.error("deleteUser - Parent Task deletion failed");
						status = USER_DELETION_FAILED;
					}
				} else {
					//logger.error("deleteUser - Task deletion failed");
					status = USER_DELETION_FAILED;
				}
			}
			return status;
		}
	}
