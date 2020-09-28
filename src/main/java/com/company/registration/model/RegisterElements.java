package com.company.registration.model;
import javax.validation.constraints.NotNull;


public class RegisterElements {
//@NotNull(message="Price needs to be secified!")	
//@EmailAddressCustomAnnotation	
private String email;
//@PasswordCustomAnnotation
private String password;
private String confirmPassword;
private String username;

private RegisterElements() {
	super();
}

public RegisterElements(String email,  String password, String confirmPassword,String username) {
	super();
	this.email = email;
	this.password = password;
	this.confirmPassword = confirmPassword;
	this.username= username;
}

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getConfirmPassword() {
	return confirmPassword;
}
public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
}
