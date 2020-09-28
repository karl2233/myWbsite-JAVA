package com.company.registration.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.registration.model.RegisterElements;
import com.company.registration.model.SignInElements;
import com.company.registration.services.RegisterService;

@RestController
@CrossOrigin
@RequestMapping("/register")
public class RegistrationController {
	
	 @Autowired
	 RegisterService registerService;
	 
	
	@PostMapping(value = "/addregister")//@RequestBody @Valid
	@ResponseStatus
	public ResponseEntity Register( @RequestBody RegisterElements registerElements)  {
	return registerService.registerAccount(registerElements);
	}
	
	@GetMapping(value = "/activateRegister")//@RequestBody @Valid
//	@RequestMapping(value="/products", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity activateRegister(@RequestParam String token){
		return registerService.activateAccount(token);
	}
	
	@PostMapping(value = "/signin")//@RequestBody @Valid
	public ResponseEntity SignIn(@RequestBody SignInElements signinElements) throws Exception   {
	return	registerService.SignInAccount(signinElements);
	}
	
	@PostMapping(value = "/adminsignin")//@RequestBody @Valid
	public ResponseEntity adminSignIn(@RequestBody SignInElements signinElements) throws Exception   {
	return	registerService.adminSignInAccount(signinElements);
	}
}