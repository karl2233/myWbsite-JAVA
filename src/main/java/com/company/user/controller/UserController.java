package com.company.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;

import com.company.admin.model.SendNotificationElements;
import com.company.security.jwt.JwtTokenProvider;
import com.company.user.model.CheckoutReq;
import com.company.user.model.UserCheckoutListReq;
import com.company.user.model.UserNotificationListReq;
import com.company.user.repository.UserRepository;
import com.company.user.services.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController implements HandlerInterceptor {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;



	
	private static  final String HEADER = "Authorization";
	private static final String PREFIX = "Bearer ";
	private static final String SECRET = "secret";

	@PostMapping(value = "/getusernotificationlist")
	@ResponseStatus
	public ResponseEntity getusernotificationlist(@RequestHeader("Authorization") String auth, @RequestBody UserNotificationListReq userNotificationListReq)  {
	 
		return userService.getNotificationList(userNotificationListReq.getUserName(), userNotificationListReq.getIndexPosition());
	}
	
	@GetMapping(value = "/notificationChecker")//@RequestBody @Valid
//	@RequestMapping(value="/products", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity notificationChecker( @RequestParam String  userName){
		return userService.notificationChecker(userName);	
	}

	@RequestMapping(value = "/getUserCheckoutList", method = RequestMethod.POST)
	public ResponseEntity postClients(
	        @RequestHeader(value = "authorization", required=false) String auth) throws IOException {
		auth = auth.substring(7);
		String jwtToken = auth.replace(PREFIX, "");
     	Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
	    return userService.getListCheckout((String) claims.get("sub"));
	}
	
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public ResponseEntity checkout(
	        @RequestHeader(value = "authorization", required=false) String auth , @RequestBody CheckoutReq checkoutReq) {
		auth = auth.substring(7);
		String jwtToken = auth.replace(PREFIX, "");
    	Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
     	String email = userRepository.getEmailFromUserName((String) claims.get("sub"));
     	return userService.checkout(checkoutReq, email);
     	
	  //  return userService.getListCheckout((String) claims.get("sub"));
	}
	
	@RequestMapping(value = "/notification/{id}", method = RequestMethod.GET)
	public ResponseEntity checkNotification(
	  @PathVariable("id") int id , @RequestHeader(value = "authorization", required=false) String auth) {
		System.out.println(id);
		
		auth = auth.substring(7);
		String jwtToken = auth.replace(PREFIX, "");
    	Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
     	String email = userRepository.getEmailFromUserName((String) claims.get("sub"));
     	int userId = userRepository.getIdByUserName((String) claims.get("sub"));
     	
     	return userService.notificationCheck(userId, id);

        
     	//userService.checkout(checkoutElements, email
	}
}
