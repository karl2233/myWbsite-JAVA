package com.company.registration.services;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.registration.model.User;
import com.company.registration.repository.RegisterRepository;
import com.company.registration.util.ResponseValue;

@Service("adminAutenticationService")
public class AdminAutenticationService implements AuthenticationManager  {
	@Autowired
	RegisterRepository repository;

	
	@Override
	public Authentication authenticate(Authentication authentication) throws UsernameNotFoundException   {
			User user = new User();
			List<User> users = repository.getUserElementsByEmail(authentication.getName());
			if(users.isEmpty()) {				
		    throw new UsernameNotFoundException(ResponseValue.userNotFoundWithThisEmail); 
		    }
			else {
			for (User x : users) 
			{			
				user.setListPermissions(x.getRole());
			}
			user.setPassword(users.get(0).getPassword());
			user.setSalt(users.get(0).getSalt());
			user.setUserName(users.get(0).getUsername());
			
			if(user.getRoles().get(0).equals("ROLE_ADMIN")) {
				
			}
			else {
				throw new UsernameNotFoundException(ResponseValue.userUnAuthorised);
			}
			
			String hashedPassword;
			hashedPassword = get_SHA_1_SecurePassword(authentication.getCredentials().toString(),user.getSalt());
			if(hashedPassword.equals(user.getPassword())) {
				
			}else {
				throw new UsernameNotFoundException(ResponseValue.passwordIncorrect);
				}
			}		
			
			return  authentication;
			}
	
	private  String get_SHA_1_SecurePassword(String passwordToHash, byte[] byte1)
    {
		
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(byte1);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
            return generatedPassword;
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return null;
        
    }
}