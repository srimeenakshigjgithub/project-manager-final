package com.gjsm.projectmanager.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.gjsm.projectmanager.dao.ParentTaskRepository;
import com.gjsm.projectmanager.dao.ProjectRepository;
import com.gjsm.projectmanager.dao.TaskRepository;
import com.gjsm.projectmanager.dao.UserRepository;
import com.gjsm.projectmanager.jpaentity.ParentEntity;
import com.gjsm.projectmanager.jpaentity.ProjectEntity;
import com.gjsm.projectmanager.jpaentity.TaskEntity;
import com.gjsm.projectmanager.jpaentity.UserEntity;
import com.gjsm.projectmanager.request.GetParentRequest;
import com.gjsm.projectmanager.request.GetProjectRequest;
import com.gjsm.projectmanager.request.GetTaskRequest;
import com.gjsm.projectmanager.request.GetUserRequest;
import com.gjsm.projectmanager.service.impl.ProjectManagerServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.properties")
@ActiveProfiles("test")
public class TestProjectManagerService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String SUCCESS = "Success";
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private ProjectRepository projectRepository;
	
	@MockBean
	private ParentTaskRepository parentTaskRepository;
	
	@MockBean
	private TaskRepository taskRepository;
	
	@Autowired
    private ProjectManagerService projectManagerService;
	
	@Configuration
	static class ProjectManagerServiceTestContextConfiguration {
		@Bean
		public ProjectManagerService projectManagerService() {
			return new ProjectManagerServiceImpl();
		}
	}

	@Before
    public void setUp() throws Exception {
		given(userRepository.findAll()).willReturn(getMockUser());
		given(projectRepository.findAll()).willReturn(getMockProject());
		given(parentTaskRepository.findAll()).willReturn(getMockParentTask());
		given(taskRepository.findByProjectId(1)).willReturn(getMockTask());
		given(projectRepository.findByUserId(1)).willReturn(getMockProjEnt());
	}
	
	@Test
	public void test_getUserService() throws Exception {
		assertNotNull(projectManagerService.getUser());
	}
	
	@Test
	public void test_getProjectService() throws Exception {
		assertNotNull(projectManagerService.getProject());
	}
	
	@Test
	public void test_getParentTaskService() throws Exception {
		assertNotNull(projectManagerService.getParentTask());
	}
	
	@Test
	public void test_getTaskService() throws Exception {
		assertNotNull(projectManagerService.viewTask(1));
	}
	
	@Test
	public void test_update_addUserService() throws Exception {
		assertNotNull(projectManagerService.updateUser(getMockUser_forAdd()));
		assertEquals("Success", projectManagerService.updateUser(getMockUser_forAdd()));
	}
	
	@Test
	public void test_update_addProjectService() throws Exception {
		assertNotNull(projectManagerService.updateProject(getMockProject_forAdd()));
		assertEquals(SUCCESS, projectManagerService.updateProject(getMockProject_forAdd()));
	}
	
	@Test
	public void test_update_addParentTaskService() throws Exception {
		assertNotNull(projectManagerService.updateParentTask(getMockParentTask_forAdd()));
		assertEquals(SUCCESS, projectManagerService.updateParentTask(getMockParentTask_forAdd()));
	}
	
	@Test
	public void test_update_addTaskService() throws Exception {
		assertNotNull(projectManagerService.updateTask(getMockTask_forAdd()));
		assertEquals(SUCCESS, projectManagerService.updateTask(getMockTask_forAdd()));
	}
	
	@Test
	public void test_update_deleteUserService() throws Exception {
		assertNotNull(projectManagerService.updateUser(getMockUser_forDelete()));
		assertEquals("Success", projectManagerService.updateUser(getMockUser_forDelete()));
	}
	
	@Test
	public void test_update_deleteProjectService() throws Exception {
		assertNotNull(projectManagerService.updateProject(getMockProject_forDelete()));
		assertEquals(SUCCESS, projectManagerService.updateProject(getMockProject_forDelete()));
	}
	
	@Test
	public void test_update_deleteParentTaskService() throws Exception {
		assertNotNull(projectManagerService.updateParentTask(getMockParentTask_forDelete()));
		assertEquals(SUCCESS, projectManagerService.updateParentTask(getMockParentTask_forDelete()));
	}
	
	@Test
	public void test_update_deleteTaskService() throws Exception {
		assertNotNull(projectManagerService.updateTask(getMockTask_forDelete()));
		assertEquals(SUCCESS, projectManagerService.updateTask(getMockTask_forDelete()));
	}
	
	private List<UserEntity> getMockUser() {
		Gson gson = new Gson();
		List<UserEntity> res = new ArrayList<UserEntity>();
		try {
			UserEntity ent = gson.fromJson(new FileReader("mock/getUserRes.json"), UserEntity.class);
			res.add(ent);
        } catch (Exception e) { 
            //logger.error("Exception in TestProjectManagerService getMockUser : " + e);
        }
		return res;
	}

	private List<ProjectEntity> getMockProject() {
		Gson gson = new Gson();
		List<ProjectEntity> res = new ArrayList<ProjectEntity>();
		try {
			ProjectEntity ent = gson.fromJson(new FileReader("mock/getProjectRes.json"), ProjectEntity.class);
			res.add(ent);
        } catch (Exception e) {
        	//logger.error("Exception in TestProjectManagerService getMockProject : " + e);
        }
		return res;
	}
	
	private List<ParentEntity> getMockParentTask() {
		Gson gson = new Gson();
		List<ParentEntity> res = new ArrayList<ParentEntity>();
		try {
			ParentEntity ent = gson.fromJson(new FileReader("mock/getParentTaskRes.json"), ParentEntity.class);
			res.add(ent);
        } catch (Exception e) {
        	//logger.error("Exception in TestProjectManagerService getMockParentTask : " + e);
        }
		return res;
	}
	
	private List<TaskEntity> getMockTask() {
		Gson gson = new Gson();
		List<TaskEntity> res = new ArrayList<TaskEntity>();
		try {
			TaskEntity ent = gson.fromJson(new FileReader("mock/getTaskEntRes.json"), TaskEntity.class);
			res.add(ent);
        } catch (Exception e) {
        	//logger.error("Exception in TestProjectManagerService getMockTask : " + e);
        }
		return res;
	}
	
	private ProjectEntity getMockProjEnt() {
		Gson gson = new Gson();
		ProjectEntity res = new ProjectEntity();
		try {
			res = gson.fromJson(new FileReader("mock/projectEntRes.json"), ProjectEntity.class);
        } catch (Exception e) {
        	//logger.error("Exception in TestProjectManagerService getMockTask : " + e);
        }
		return res;
	}
	
	private GetUserRequest getMockUser_forAdd() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetUserRequest req = new GetUserRequest();
		req = gson.fromJson(new FileReader("mock/addUserReq.json"), GetUserRequest.class);
		return req;
	}
	
	private GetProjectRequest getMockProject_forAdd() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetProjectRequest req = new GetProjectRequest();
		req = gson.fromJson(new FileReader("mock/addProjectReq.json"), GetProjectRequest.class);
		return req;
	}
	
	private GetParentRequest getMockParentTask_forAdd() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetParentRequest req = new GetParentRequest();
		req = gson.fromJson(new FileReader("mock/addParentTaskReq.json"), GetParentRequest.class);
		return req;
	}
	
	private GetTaskRequest getMockTask_forAdd() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetTaskRequest req = new GetTaskRequest();
		req = gson.fromJson(new FileReader("mock/addTaskReq.json"), GetTaskRequest.class);
		return req;
	}
	
	private GetUserRequest getMockUser_forDelete() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetUserRequest req = new GetUserRequest();
		req = gson.fromJson(new FileReader("mock/deleteUserReq.json"), GetUserRequest.class);
		return req;
	}
	
	private GetProjectRequest getMockProject_forDelete() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetProjectRequest req = new GetProjectRequest();
		req = gson.fromJson(new FileReader("mock/deleteProjectReq.json"), GetProjectRequest.class);
		return req;
	}
	
	private GetParentRequest getMockParentTask_forDelete() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetParentRequest req = new GetParentRequest();
		req = gson.fromJson(new FileReader("mock/deleteParentTaskReq.json"), GetParentRequest.class);
		return req;
	}
	
	private GetTaskRequest getMockTask_forDelete() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetTaskRequest req = new GetTaskRequest();
		req = gson.fromJson(new FileReader("mock/deleteTaskReq.json"), GetTaskRequest.class);
		return req;
	}
	
}
