package com.company.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.admin.model.AddProjectReq;
import com.company.admin.model.NotficationStatus;
import com.company.admin.model.NotificationElement;
import com.company.admin.model.SendNotificationElements;
import com.company.admin.model.UserById;
import com.company.admin.repository.AdminRepository;
import com.company.registration.repository.RegisterRepository;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public ResponseEntity sendNotification(SendNotificationElements sendNotificationElements) {
		Map<Object, Object> model = new HashMap<>();
		adminRepository.insertNotification(sendNotificationElements.getNotificationHeader(), sendNotificationElements.getNotificationBody());
		int id =  adminRepository.getLastInsert();
	    ArrayList<UserById> userList = (ArrayList<UserById>) adminRepository.getUsersById();
	    
	    for (UserById user: userList) {
	    	adminRepository.insertNotificationForEachUser(id,  user.getUserId());
	      }
	    model.put("status", id);
	    return new ResponseEntity<>(model,HttpStatus.OK);
	}

	@Override
	public ResponseEntity getNotificationElements(int index) {
		Map<Object, Object> model = new HashMap<>();
		ArrayList<NotificationElement> userList = (ArrayList<NotificationElement>) adminRepository.getNotificationElements(index);
		model.put("notificationElements", userList);
		return new ResponseEntity<>(model,HttpStatus.OK);
	}

	@Override
	public ResponseEntity getLastInsertedNotification() {
		NotficationStatus notifStatus = new NotficationStatus();
		Map<Object, Object> model = new HashMap<>();
		int id = 0;
		 id =  adminRepository.getLastInsert();
	if(id == 0) {
		//notifStatus.setLastInsertedNotificationId(id);
		notifStatus.setNotificationStatus(0);
		notifStatus.setNotificationStatusReason("something went wrong");
	}else {
	notifStatus.setLastInsertedNotificationId(id);
	notifStatus.setNotificationStatus(1);
	notifStatus.setNotificationStatusReason("notification Sent");}
		model.put("status", notifStatus);
		return new ResponseEntity<>(model,HttpStatus.OK);
	}

	@Override
	public ResponseEntity insertProject(AddProjectReq addProjectElement) {
		Map<Object, Object> model = new HashMap<>();
		
		int id =  adminRepository.getUserIdByUsername(addProjectElement.getUserName());
		int check = adminRepository.insertProject(id, addProjectElement.getProjectName(), addProjectElement.getProjectPrice());
		
		model.put("status", true);
		return new ResponseEntity<>(model,HttpStatus.OK);
	}

}
