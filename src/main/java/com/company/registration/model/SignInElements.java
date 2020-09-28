package com.company.registration.model;

import javax.validation.constraints.NotNull;


public class SignInElements {
private String email;
private String password;


private SignInElements() {
	super();
}

public SignInElements(String email,  String password) {
	super();
	this.email = email;
	this.password = password;
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
}
