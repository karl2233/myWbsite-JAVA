package com.company.registration.model;

public class SignInStatus {
	private String statusReason;
	private int statusCode;
	private String userName;
	private String token;
	
	public  SignInStatus(String statusReason,int statusCode,String userName,String token) {
		this.statusReason = statusReason;
		this.statusCode = statusCode;
		this.userName = userName;
		this.token = token;
	}
	public String getStatusReason() {
		return statusReason;
	}
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
