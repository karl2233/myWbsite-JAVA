package com.company.registration.repository;

import java.util.List;

import com.company.registration.model.RegisterElements;
import com.company.registration.model.User;
import com.company.registration.model.UserInformation;

public interface RegisterRepository {
public List<User> getUserElementsByUserName(String username);
public List<User> getUserByEmail(String email);
public List<User> getUserByUserName(String userName);
public boolean storingHashedSaltedAccount(RegisterElements registerElement,String tokenDuration,String token ) ;
public String registerRole();
public List<UserInformation> getUserInformation(String token);
public int activateAccount(boolean activateAccount,String token);
public List<User> getUserElementsByEmail(String email);
}
