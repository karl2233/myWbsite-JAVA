package com.company.registration.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.company.registration.model.RegisterElements;
import com.company.registration.model.RegiterStatus;
import com.company.registration.model.SignInElements;
import com.company.registration.model.SignInStatus;
import com.company.registration.model.User;
import com.company.registration.model.UserInformation;
import com.company.registration.repository.RegisterRepository;
import com.company.registration.util.ResponseValue;
import com.company.security.jwt.JwtTokenProvider;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterRepository registerRepository;
	
	@Autowired
	@Qualifier("autenticationService")
	AuthenticationManager authenticationManager;
	
	@Autowired
	@Qualifier("adminAutenticationService")
	AdminAutenticationService adminAutenticationService;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Override
	public ResponseEntity registerAccount(RegisterElements registerElements)  {
	Map<Object, Object> model = new HashMap<>();	
	List<User> userByUserName =   registerRepository.getUserByUserName(registerElements.getUsername());
	
	if(userByUserName.size()!=0) {
		RegiterStatus regsiterStatus = new RegiterStatus(ResponseValue.usernameDuplicate,0);
		model.put("status",regsiterStatus);
		return new ResponseEntity<>(model,HttpStatus.OK);
	}
	List<User> userByUserEmail =   registerRepository.getUserByEmail(registerElements.getEmail());
	 
	if(userByUserEmail.size()!=0) {
		RegiterStatus regsiterStatus = new RegiterStatus(ResponseValue.emailDuplicate,0);
		model.put("status",regsiterStatus);
		return new ResponseEntity<>(model,HttpStatus.OK);
	}
	
	
		
	registerRepository.storingHashedSaltedAccount(registerElements,getTokenDuration(),getToken());
	registerRepository.registerRole();
	
	RegiterStatus regsiterStatus = new RegiterStatus(ResponseValue.registrationSuccesfull,0);
	model.put("status",regsiterStatus);
	return new ResponseEntity<>(model,HttpStatus.OK);
	}
	private String getTokenDuration() {
		Calendar date = Calendar.getInstance();
		long t= date.getTimeInMillis();
		Date afterAddingTenMins=new Date(t + (15 * 60000));
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		
	return format.format(afterAddingTenMins);
	}
	private String getToken() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		StringBuilder sb = new StringBuilder(20);
		Random random = new Random();
		for (int i = 0; i < 32; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		return output;
	}
	@Override
	public ResponseEntity activateAccount(String token) {
		Map<Object, Object> model = new HashMap<>();

		Date currentDate = new Date(); 
		Date tokenCreationDate = null;
		List<UserInformation> userInformation =registerRepository.getUserInformation(token);
		if(!userInformation.isEmpty()) {
				try {
					
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
					tokenCreationDate = format.parse(userInformation.get(0).getDurationActivate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				
				if(currentDate.compareTo(tokenCreationDate) > 0) {
					RegiterStatus regsiterStatus = new RegiterStatus(ResponseValue.accountRegistrationExpired,0);
					 model.put("status",regsiterStatus);
					  return new ResponseEntity<>(model,HttpStatus.OK);
			         
			         //delete expired account 
			         //return status of expired account
			      } else {
			    	  registerRepository.activateAccount(true, token);
			    	  RegiterStatus regsiterStatus = new RegiterStatus(ResponseValue.accountRegisteredsuccessfully,1);
			    	  model.put("status",regsiterStatus);
			  		  return new ResponseEntity<>(model,HttpStatus.OK);
			      }
		}else {
			 RegiterStatus regsiterStatus = new RegiterStatus(ResponseValue.tokenNotFound,0);
	    	  model.put("status",regsiterStatus);
	  		  return new ResponseEntity<>(model,HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity SignInAccount(SignInElements signinElements) {
		Map<Object, Object> model = new HashMap<>();
		User user = new User();
try {
	
	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinElements.getEmail(), signinElements.getPassword()));
	List<User> users = registerRepository.getUserElementsByEmail(signinElements.getEmail());
	for (User x : users) 
	{
		user.setListPermissions(x.getRole());
	}
	user.setPassword(users.get(0).getPassword());
	user.setSalt(users.get(0).getSalt());
	user.setUserName(users.get(0).getUsername());
	
	var token = "";
	
	token = tokenProvider.createToken(user.getUsername(), user.getRoles());
	SignInStatus signInStatus  = new SignInStatus(ResponseValue.signInSuccessful,1,user.getUsername(),token);
	
	model.put("status", signInStatus);
	 return new ResponseEntity(model,HttpStatus.OK);
	
}catch(AuthenticationException e) {
SignInStatus signInStatus  = new SignInStatus(ResponseValue.userNotFound,0,"","");
model.put("status", signInStatus);
return new ResponseEntity(model,HttpStatus.OK);
}

	
	}
	@Override
	public ResponseEntity adminSignInAccount(SignInElements signinElements) {
		Map<Object, Object> model = new HashMap<>();
		User user = new User();
try {
	
	adminAutenticationService.authenticate(new UsernamePasswordAuthenticationToken(signinElements.getEmail(), signinElements.getPassword()));
	List<User> users = registerRepository.getUserElementsByEmail(signinElements.getEmail());
	for (User x : users) 
	{
		user.setListPermissions(x.getRole());
	}
	user.setPassword(users.get(0).getPassword());
	user.setSalt(users.get(0).getSalt());
	user.setUserName(users.get(0).getUsername());
	
	var token = "";
	
	token = tokenProvider.createToken(user.getUsername(), user.getRoles());
	SignInStatus signInStatus  = new SignInStatus(ResponseValue.signInSuccessful,1,user.getUsername(),token);
	
	model.put("status", signInStatus);
	 return new ResponseEntity(model,HttpStatus.OK);
	
	}catch(AuthenticationException e) {
	SignInStatus signInStatus  = new SignInStatus(e.getMessage(),0,"","");
	model.put("status", signInStatus);
	return new ResponseEntity(model,HttpStatus.OK);
	}

	
	}
	
	
}