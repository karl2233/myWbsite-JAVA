package com.company.admin.model;

public class NotificationElement {
private String notificationHeader;
private String notificationBody;
private int notificationId;
public String getNotificationHeader() {
	return notificationHeader;
}
public int getNotificationId() {
	return notificationId;
}
public void setNotificationId(int notificationId) {
	this.notificationId = notificationId;
}
public void setNotificationHeader(String notificationHeader) {
	this.notificationHeader = notificationHeader;
}
public String getNotificationBody() {
	return notificationBody;
}
public void setNotificationBody(String notificationBody) {
	this.notificationBody = notificationBody;
}
}
