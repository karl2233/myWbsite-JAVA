package com.company.admin.model;

public class NotficationStatus {
private int notificationStatus;
private String notificationStatusReason;
private int lastInsertedNotificationId;
public int getNotificationStatus() {
	return notificationStatus;
}
public void setNotificationStatus(int notificationStatus) {
	this.notificationStatus = notificationStatus;
}
public String getNotificationStatusReason() {
	return notificationStatusReason;
}
public void setNotificationStatusReason(String notificationStatusReason) {
	this.notificationStatusReason = notificationStatusReason;
}
public int getLastInsertedNotificationId() {
	return lastInsertedNotificationId;
}
public void setLastInsertedNotificationId(int lastInsertedNotificationId) {
	this.lastInsertedNotificationId = lastInsertedNotificationId;
}
}
