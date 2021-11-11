package com.deloitte.nextgen.framework.service.security.models;

public class AuthenticationObj {
	
	private String userName;
	private String password;
	private String responseStatus;
	
	


	public AuthenticationObj() {
		
	}


	public AuthenticationObj(String userName, String password, String responseStatus) {
		super();
		this.userName = userName;
		this.password = password;
		this.responseStatus = responseStatus;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	
	

}
