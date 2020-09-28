package com.company.registration.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.registration.model.User;
import com.company.registration.repository.RegisterRepository;

@Service
public class UserServices implements UserDetailsService {
	
	@Autowired
	RegisterRepository repository;
	
	public UserServices(RegisterRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User();
		List<User> users ;
	    users = repository.getUserElementsByUserName(username);
	    
		for (User x : users) 
		{
			user.setListPermissions(x.getRole());
		}
		user.setPassword(users.get(0).getPassword());
		user.setSalt(users.get(0).getSalt());
		user.setUserName(users.get(0).getUsername());
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
	}
		
}