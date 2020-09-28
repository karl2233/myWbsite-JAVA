package com.company.admin.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.admin.model.AddProjectReq;
import com.company.admin.model.SendNotificationElements;
import com.company.admin.service.AdminService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	 
	
	@PostMapping(value = "/SendNotification")//@RequestBody @Valid
	@ResponseStatus
	public ResponseEntity Register( @RequestBody SendNotificationElements sendNotificationElements)  {
		return adminService.sendNotification(sendNotificationElements);
	}
	
	@GetMapping(value = "/notificationList")//@RequestBody @Valid
//	@RequestMapping(value="/products", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity Item( @RequestParam(value = "notificationId") int notificationId,@RequestHeader("Authorization") String auth) {
		return adminService.getNotificationElements(notificationId);
	}
	
	
	@PostMapping(value = "/addProject")//@RequestBody @Valid
	@ResponseStatus
	public ResponseEntity AddProject( @RequestBody AddProjectReq addProjectReq)  {
		return adminService.insertProject(addProjectReq);
	}
	
}