package com.company.registration.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.company.registration.model.RegisterElements;
import com.company.registration.model.SignInElements;



public interface RegisterService {
	public ResponseEntity registerAccount(RegisterElements registerElements) ;
	public ResponseEntity activateAccount(String token);
	public ResponseEntity SignInAccount(SignInElements signinElements);
	public ResponseEntity adminSignInAccount(SignInElements signinElements);
	
	}
