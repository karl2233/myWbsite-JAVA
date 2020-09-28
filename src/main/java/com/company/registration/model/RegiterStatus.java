package com.company.registration.model;

public class RegiterStatus {
private String statusReason;
private int statusCode;
public RegiterStatus(String statusReason, int statusCode) {
this.statusReason = statusReason;
this.statusCode = statusCode;
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

}
