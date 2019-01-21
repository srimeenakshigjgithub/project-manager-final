package com.gjsm.projectmanager.controller;

	
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
import com.gjsm.projectmanager.service.impl.ProjectManagerServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application.properties")
@ActiveProfiles("test")
public class TestProjectManagerController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProjectManagerService projectManagerServiceImpl;
	
	@Configuration
	static class ProjectManagerServiceTestContextConfiguration {
		@Bean
		public ProjectManagerService projectManagerService() {
			return new ProjectManagerServiceImpl();
		}
	}

	@Test
	public void test_getUser_endpoint() throws Exception {
		given(projectManagerServiceImpl.getUser()).willReturn(getMockUser());
		this.mockMvc
				.perform(get("/getUser").contentType(MediaType.APPLICATION_JSON).header("Accept", "*/*"))
				.andExpect(status().isOk());
	}

	@Test
	public void test_getProject_endpoint() throws Exception {
		given(projectManagerServiceImpl.getProject()).willReturn(getMockProject());
		this.mockMvc.perform(get("/getProject").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void test_getParentTask_endpoint() throws Exception {
		given(projectManagerServiceImpl.getParentTask()).willReturn(getMockParentTask());
		this.mockMvc.perform(get("/getParentTask").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void test_getTask_endpoint() throws Exception {
		given(projectManagerServiceImpl.viewTask(1)).willReturn(getMockTask());
		this.mockMvc.perform(
				post("/viewTask").contentType(MediaType.APPLICATION_JSON).content(createTaskIdJson()))
				.andExpect(status().isOk());
	}
	@Test
	public void test_update_addUser_endpoint() throws Exception {
		given(projectManagerServiceImpl.updateUser(getMockUser_forAdd())).willReturn("Success");
		this.mockMvc.perform(post("/updateUser").contentType(MediaType.APPLICATION_JSON)
				.content(createModifyUserJson())).andExpect(status().isOk());
	}

	@Test
	public void test_update_addProject_endpoint() throws Exception {
		given(projectManagerServiceImpl.updateProject(getMockProject_forAdd())).willReturn("Success");
		this.mockMvc.perform(post("/updateProject").contentType(MediaType.APPLICATION_JSON)
				.content(createModifyProjectJson())).andExpect(status().isOk());
	}

	@Test
	public void test_update_addParentTask_endpoint() throws Exception {
		given(projectManagerServiceImpl.updateParentTask(getMockParentTask_forAdd())).willReturn("Success");
		this.mockMvc.perform(post("/updateParentTask").contentType(MediaType.APPLICATION_JSON)
				.content(createModifyParentTaskJson())).andExpect(status().isOk());
	}

	@Test
	public void test_update_addTask_endpoint() throws Exception {
		given(projectManagerServiceImpl.updateTask(getMockTask_forAdd())).willReturn("Success");
		this.mockMvc.perform(post("/updateTask").contentType(MediaType.APPLICATION_JSON)
				.content(createModifyTaskJson())).andExpect(status().isOk());
	}

	@Test
	public void test_getUserexception() throws Exception {
		given(projectManagerServiceImpl.getUser()).willThrow(new ProjectManagerException("", "", 500));
		this.mockMvc.perform(get("/getUser").contentType(MediaType.APPLICATION_PDF_VALUE)
				).andExpect(status().is5xxServerError());
	}

	@Test
	public void test_getProjectexception() throws Exception {
		given(projectManagerServiceImpl.getProject()).willThrow(new ProjectManagerException("", "", 500));
		this.mockMvc.perform(get("/getProject").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_XML)).andExpect(status().is5xxServerError());
	}

	@Test
	public void test_getParentTaskexception() throws Exception {
		given(projectManagerServiceImpl.getParentTask()).willThrow(new ProjectManagerException("", "", 500));
		this.mockMvc.perform(get("/getParentTask").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_XML)).andExpect(status().is5xxServerError());
	}

	@Test
	public void test_getTaskexception() throws Exception {
		given(projectManagerServiceImpl.viewTask(1)).willThrow(new ProjectManagerException("", "", 500));
		this.mockMvc
				.perform(post("/viewTask").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_XML).content(createTaskIdJson()))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void test_updateUserexception() throws Exception {
		given(projectManagerServiceImpl.updateUser(any(GetUserRequest.class))).willThrow(new ProjectManagerException("", "", 500));
		this.mockMvc
				.perform(post("/updateUser").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_XML).content(createModifyUserJson()))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void test_updateProjectexception() throws Exception {
		given(projectManagerServiceImpl.updateProject(any(GetProjectRequest.class))).willThrow(new ProjectManagerException("", "", 500));
		this.mockMvc
				.perform(post("/updateProject").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(createModifyProjErr()))
				.andExpect(status().is5xxServerError()); 
	}

	@Test
	public void test_updateParentTaskexception() throws Exception {
		given(projectManagerServiceImpl.updateParentTask(any(GetParentRequest.class)))
				.willThrow(new ProjectManagerException("", "", 500));
		this.mockMvc
				.perform(post("/updateParentTask").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_XML).content(createModifyParentTaskJson()))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void test_updateTaskexception() throws Exception {
		given(projectManagerServiceImpl.updateTask(any(GetTaskRequest.class))).willThrow(new ProjectManagerException("", "", 500));
		this.mockMvc
				.perform(post("/updateTask").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_XML).content(createModifyTaskJson()))
				.andExpect(status().is5xxServerError());
	}

	
	private GetUserResponse getMockUser() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetUserResponse res = new GetUserResponse();
		res = gson.fromJson(new FileReader("mock/getUserRes.json"), GetUserResponse.class);
		return res;
	}

	private GetProjectResponse getMockProject() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetProjectResponse res = new GetProjectResponse();
		res = gson.fromJson(new FileReader("mock/getProjectRes.json"), GetProjectResponse.class);
		return res;
	}

	private GetParentResponse getMockParentTask()
			throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetParentResponse res = new GetParentResponse();
		res = gson.fromJson(new FileReader("mock/getParentTaskRes.json"), GetParentResponse.class);
		return res;
	}

	private GetTaskResponse getMockTask() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetTaskResponse res = new GetTaskResponse();
		res = gson.fromJson(new FileReader("mock/getTaskRes.json"), GetTaskResponse.class);
		return res;
	}

	private GetUserRequest getMockUser_forAdd() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetUserRequest req = new GetUserRequest();
		req = gson.fromJson(new FileReader("mock/addUserReq.json"), GetUserRequest.class);
		return req;
	}

	private GetProjectRequest getMockProject_forAdd()
			throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetProjectRequest req = new GetProjectRequest();
		req = gson.fromJson(new FileReader("mock/addProjectReq.json"), GetProjectRequest.class);
		return req;
	}

	private GetParentRequest getMockParentTask_forAdd()
			throws JsonSyntaxException, JsonIOException, FileNotFoundException {
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

	
	private static String createTaskIdJson() {
		return "{\"task\" : {\"projectId\" : 1}}";
	}
	
	private String createModifyUserJson() throws Exception {
		String content = "";
		BufferedReader reader = new BufferedReader(new FileReader("mock/addUserReq.json"));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		content = stringBuilder.toString();
		return content;
	}

	
	private String createModifyProjErr() throws Exception {
		String content = "";
		BufferedReader reader = new BufferedReader(new FileReader("mock/addProjectReqErr.json"));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		content = stringBuilder.toString();
		return content;
	}
	
	private String createModifyProjectJson() throws Exception {
		String content = "";
		BufferedReader reader = new BufferedReader(new FileReader("mock/addProjectReq.json"));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		content = stringBuilder.toString();
		return content;
	}

	private String createModifyParentTaskJson() throws Exception {
		String content = "";
		BufferedReader reader = new BufferedReader(new FileReader("mock/addParentTaskReq.json"));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		content = stringBuilder.toString();
		return content;
	}

	private String createModifyTaskJson() throws Exception {
		String content = "";
		BufferedReader reader = new BufferedReader(new FileReader("mock/addTaskReq.json"));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		content = stringBuilder.toString();
		return content;
	}

}



