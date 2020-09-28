package com.company.user.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.user.model.CheckoutListElements;
import com.company.user.model.CheckoutReq;
import com.company.user.model.CheckoutStatusResponse;
import com.company.user.model.NotificationElement;
import com.company.user.repository.UserRepository;
import com.company.user.util.ResponseValue;
import com.stripe.Stripe;
import com.stripe.model.Charge;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	
	@Override
	public ResponseEntity getNotificationList(String userName, int indexPosition) 
	{
		List<NotificationElement> list = new ArrayList<>();
		Map<Object, Object> model = new HashMap<>();
		int id = userRepository.getUserIdByUsername(userName);
		
		try {
		list = userRepository.getNotificationList(id, indexPosition);
		}catch(Exception e) {
			list = null;
		}
		model.put("list", list);
	    int lastId;
		try {
			lastId = userRepository.getLastNotification();
		} catch (Exception e) {
			lastId = 0;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    model.put("lastId", lastId);
		return new ResponseEntity<>(model,HttpStatus.OK);	
	}


	@Override
	public ResponseEntity notificationChecker(String userName) {
		Map<Object, Object> model = new HashMap<>();
		int id = userRepository.getUserIdByUsername(userName);
	    boolean check =	userRepository.checkNotification(id);
	    model.put("notificationCheck", check);
		
	    return new ResponseEntity<>(model,HttpStatus.OK);
	}


	@Override
	public ResponseEntity getListCheckout(String username) {
		Map<Object, Object> model = new HashMap<>();
		
		int id = userRepository.getUserIdByUsername(username);
		
		List<CheckoutListElements> list = userRepository.getListCheckout(id);
	    model.put("list",list);
	    return new ResponseEntity<>(model,HttpStatus.OK);
	}


	@Override
	public ResponseEntity checkout(CheckoutReq checkoutElements,String email) {
		  String id = null;
		  Map<String, Object> chargeParams = new HashMap<>();
		  Map<String, Object> response = new HashMap<>();
		  CheckoutStatusResponse checkoutStatusResponse = new CheckoutStatusResponse();
		  
	        try {
	            Stripe.apiKey = "";
	            
	            chargeParams.put("amount", (int)(checkoutElements.getAmount() * 100));
	            chargeParams.put("currency", "usd");
	            chargeParams.put("description", "Charge for " + email);
	            chargeParams.put("source", checkoutElements.getToken()); // ^ obtained with Stripe.js
	            //create a charge
	            Charge charge = Charge.create(chargeParams);
	            
	            userRepository.paymentComplete(checkoutElements.getProjectId());
	  
	            
	            id = charge.getId();
	            checkoutStatusResponse.setChekcoutStatus(true);
	            checkoutStatusResponse.setCheckoutStatusReason(ResponseValue.checkoutSucces);
	            response.put("status",checkoutStatusResponse);
	            
	            return new ResponseEntity<>(response,HttpStatus.OK);
	            
	        } catch (Exception ex) {
	        	 checkoutStatusResponse.setChekcoutStatus(false);
	        	 checkoutStatusResponse.setCheckoutStatusReason(ex.getMessage());
	        	 response.put("status",checkoutStatusResponse);
	        	 
	        	 return new ResponseEntity<>(response,HttpStatus.OK);
	        }
	        
	}


	@Override
	public ResponseEntity notificationCheck(int userId, int notificationId) {
		Map<String, Object> response = new HashMap<>();
	int check = userRepository.updateNotification(userId, notificationId);
		System.out.println(check);
		response.put("status", true);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}



}
