package com.company.user.model;

public class NotificationElement {
private boolean notificationCheck;
private String notificationBody;
private String notificationHeader;
private int notificationId;
public boolean isNotificationCheck() {
	return notificationCheck;
}
public void setNotificationCheck(boolean notificationCheck) {
	this.notificationCheck = notificationCheck;
}
public String getNotificationBody() {
	return notificationBody;
}
public void setNotificationBody(String notificationBody) {
	this.notificationBody = notificationBody;
}
public String getNotificationHeader() {
	return notificationHeader;
}
public void setNotificationHeader(String notificationHeader) {
	this.notificationHeader = notificationHeader;
}
public int getNotificationId() {
	return notificationId;
}
public void setNotificationId(int notificationId) {
	this.notificationId = notificationId;
}

}
