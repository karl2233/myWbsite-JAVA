package com.company.registration.model;


import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails, Serializable {
private String userName;
private String password;
private int userId;


public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
private byte[] salt;
private String role;
private List<Permission> permissions = new ArrayList<>();
private Permission permission;

public User() {
	this.permission = new Permission();
}

public void setListPermissions(String role) {
	this.permission.setDescription(role);
	Permission per = this.permission;
	this.permissions.add(per);
}
public List<String> getRoles() {
	List<String> roles = new ArrayList<>();
	for (Permission permission : this.permissions) {
		roles.add(permission.getDescription());
	}
	return roles;
}
public byte[] getSalt() {
	return salt;
}
public void setSalt(byte[] inputStream) {
	this.salt = inputStream;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

public void setPassword(String password) {
	this.password = password;
}
public void setUserName(String userName) {
	this.userName = userName;
}
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	return permissions;
}
@Override
public String getUsername() {
	return this.userName;
}
@Override
public String getPassword() {
	return this.password;
}
@Override
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return false;
}
@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return false;
}
@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return false;
}
@Override
public boolean isEnabled() {
	// TODO Auto-generated method stub
	return false;
}

}
