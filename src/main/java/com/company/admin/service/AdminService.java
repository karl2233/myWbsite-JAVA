package com.company.admin.service;

import org.springframework.http.ResponseEntity;

import com.company.admin.model.AddProjectReq;
import com.company.admin.model.SendNotificationElements;
import com.company.registration.model.RegisterElements;

public interface AdminService {
	public ResponseEntity sendNotification(SendNotificationElements sendNotificationElements);
	public ResponseEntity getNotificationElements(int index);
	public ResponseEntity getLastInsertedNotification();
	public ResponseEntity insertProject(AddProjectReq addProjectElement);
}
