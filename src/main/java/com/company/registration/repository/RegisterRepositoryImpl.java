package com.company.registration.repository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.company.registration.model.RegisterElements;
import com.company.registration.model.User;
import com.company.registration.model.UserInformation;
import com.company.registration.rm.CompleteUserRowMapper;
import com.company.registration.rm.LastIdInsertRowMapper;
import com.company.registration.rm.UserInformationRowMapper;
import com.company.registration.rm.UserRowMapper;
import com.company.registration.util.Querries;

@Service
@Configurable
public class RegisterRepositoryImpl implements RegisterRepository {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	byte[] salt = null ;
	
	@Override
	public List<User> getUserElementsByUserName(String username) {
	
		List<User> users =  jdbcTemplate.query(Querries.userByUsername, new Object[] {
				username
		        }, new CompleteUserRowMapper());
		return users;

	}

	@Override
	public List<User> getUserByEmail(String email) {
		return jdbcTemplate.query(Querries.selectUserByEmail, new Object[] {
				email
	        }, new UserRowMapper());
	}

	@Override
	public List<User> getUserByUserName(String userName) {
		return jdbcTemplate.query(Querries.selectUserByUserName, new Object[] {
				userName
	        }, new UserRowMapper());
	}

	@Override
	public boolean storingHashedSaltedAccount(RegisterElements registerElement,String tokenDuration,String token )  {
		String hashedPasswordWithSalt;
		try {
			salt = getSalt();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			return false;
		}
		try {
		 hashedPasswordWithSalt = get_SHA_1_SecurePassword(registerElement.getPassword(),salt);
		}catch(NoSuchAlgorithmException e) {
			return false;
		}
		
		
		
		
		 jdbcTemplate.update(Querries.insertIntoUsers,
		            new Object[] {
		            		registerElement.getUsername(),registerElement.getEmail(),hashedPasswordWithSalt,salt,false,token,tokenDuration
		            });
		
		return true;
	}

	@Override
	public String registerRole() {
		String lastInsertId =  jdbcTemplate.queryForObject(Querries.getLastInsert, new LastIdInsertRowMapper());
		jdbcTemplate.update(Querries.insertIntoUser_Permission,
	            new Object[] {
	            		lastInsertId,"3"
	            });
	
		return lastInsertId;
	}
	
	private  String get_SHA_1_SecurePassword(String passwordToHash, byte[] salt) throws NoSuchAlgorithmException
    {
        String generatedPassword = null;
     
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
            return generatedPassword;
       
       
     
    }

    private  byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

	@Override
	public List<UserInformation> getUserInformation(String token) {
		return (List<UserInformation>) jdbcTemplate.query(Querries.selectUserByToken, new Object[] {
				token
	        }, new UserInformationRowMapper());
	}

	@Override
	public int activateAccount(boolean activateAccount,String token) {
	return	jdbcTemplate.update(Querries.activateUserAccount,
	            new Object[] {
	            		activateAccount,token
	            });
	
		
	}
	
	@Override
	public List<User> getUserElementsByEmail(String email)   {
		List<User> users = null;
		users =  jdbcTemplate.query(Querries.userByEmail, new Object[] {
				email
		        }, new CompleteUserRowMapper());
		
		return users;
		
	}
	

}
