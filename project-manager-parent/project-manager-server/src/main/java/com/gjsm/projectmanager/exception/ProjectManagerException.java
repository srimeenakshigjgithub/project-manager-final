package com.gjsm.projectmanager.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectManagerException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	private int status;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public ProjectManagerException(){
		
	}
	
	public ProjectManagerException( String errorCode, String errorMessage, int status){
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.status = status;
	}
	
	public ProjectManagerException changeToRestError() {
		ProjectManagerException restError = new ProjectManagerException();
		restError.setErrorCode(this.errorCode);
		restError.setErrorMessage(this.errorMessage);
		return restError;
		
	}
}
