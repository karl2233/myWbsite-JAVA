package com.company.user.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.company.user.model.CheckoutReq;
import com.company.user.model.NotificationElement;

public interface UserService {
public ResponseEntity getNotificationList(String userName,int indexPosition);
public ResponseEntity notificationChecker(String username);
public ResponseEntity getListCheckout(String username);
public ResponseEntity checkout(CheckoutReq checkoutElements,String email);
public ResponseEntity notificationCheck(int userId,int notificationId); 

}
