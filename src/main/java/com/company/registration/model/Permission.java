package com.company.registration.model;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;



public class Permission implements GrantedAuthority, Serializable {
	
	public Permission() {
		super();
	}
	private String description;
	
	public String getDescription() {
		return description;
	}

	public  void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getAuthority() {
		return description;
	}

}
