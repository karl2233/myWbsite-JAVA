package com.company.admin.repository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.company.admin.model.NotificationElement;
import com.company.admin.model.UserById;
import com.company.admin.rm.NotificationElementRowMapper;
import com.company.admin.rm.NotificationIdRowMapper;
import com.company.admin.rm.UserIdRowMapper;
import com.company.admin.util.Querries;
import com.company.registration.model.RegisterElements;
import com.company.registration.model.User;
import com.company.registration.model.UserInformation;
import com.company.registration.rm.CompleteUserRowMapper;
import com.company.registration.rm.LastIdInsertRowMapper;
import com.company.registration.rm.UserInformationRowMapper;
import com.company.registration.rm.UserRowMapper;
import com.company.admin.rm.UseIdRowMapper;



@Service
@Configurable
public class AdminRepositoryImpl implements AdminRepository {
	
	@Autowired
    JdbcTemplate jdbcTemplate;

	@Override
	public int insertNotification(String header, String body) {
		
	return	jdbcTemplate.update(Querries.insertInNotification,
	            new Object[] {
	            		header,body
	            });
	
	
	}
	
	@Override
	public List<UserById> getUsersById() {
		return jdbcTemplate.query(Querries.getUsersById ,new UserIdRowMapper());
	}

	@Override
	public List<NotificationElement> getNotificationElements(int indexposition) {
		return jdbcTemplate.query(Querries.getListofNotification,new Object[] {
				indexposition
		        },new NotificationElementRowMapper());
		//return null;
	}

	@Override
	public int getLastInsert() {
		return jdbcTemplate.queryForObject(Querries.getLastInsert,new NotificationIdRowMapper());
	}

	@Override
	public void insertNotificationForEachUser(int notificationId,int userId) {
			jdbcTemplate.update(Querries.insertNotificationValid,
	            new Object[] {
	            		notificationId,userId,false
	            });
		
	}

	@Override
	public int insertProject(int userId, String projectName, double price) {
		return jdbcTemplate.update(Querries.insertProject,
	            new Object[] {
	            		userId,projectName,false,price
	            });
	}
	
	@Override
	public int getUserIdByUsername(String userName) {
		return jdbcTemplate.queryForObject(Querries.getUserIdByUserName,new Object[] {
				userName
        },new UseIdRowMapper());
	}


}
