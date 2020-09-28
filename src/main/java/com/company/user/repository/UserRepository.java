package com.company.user.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.company.user.rm.CheckNotificationRowMapper;
import com.company.user.rm.CheckoutListRowMapper;
import com.company.user.rm.EmailUserFromUserNameRowMapper;
import com.company.user.rm.LastNotificationIdRowMapper;
import com.company.user.rm.NotificationElementRowMapper;
import com.company.user.rm.UseIdRowMapper;
import com.company.user.util.Querries;
import com.company.user.model.CheckoutListElements;
import com.company.user.model.NotificationElement;

@Service
public class UserRepository implements UserRepositoryImpl  {

	@Autowired
    JdbcTemplate jdbcTemplate;

	@Override
	public List<NotificationElement> getNotificationList(int userId, int index) throws Exception  {
		return jdbcTemplate.query(Querries.getNotificationList,new Object[] {
				userId,index
        },new NotificationElementRowMapper());
	}

	@Override
	public int getUserIdByUsername(String userName) {
		return jdbcTemplate.queryForObject(Querries.getUserIdByUserName,new Object[] {
				userName
        },new UseIdRowMapper());
	}

	@Override
	public boolean checkNotification(int userId) {
		
		return jdbcTemplate.queryForObject(Querries.notificationChecker,new Object[] {
				userId
        },new CheckNotificationRowMapper());
	}

	@Override
	public int getLastNotification() throws Exception {
		return jdbcTemplate.queryForObject(Querries.lastNotificaitionElement,new LastNotificationIdRowMapper());
	}

	@Override
	public List<CheckoutListElements> getListCheckout(int userId) {
		return jdbcTemplate.query(Querries.getListOfCheckouts,new Object[] {
				userId
        },new CheckoutListRowMapper());
	}

	@Override
	public String getEmailFromUserName(String userNmae) {
		return jdbcTemplate.queryForObject(Querries.getEmailFromUserName,new Object[] {
				userNmae
        },new EmailUserFromUserNameRowMapper());
	}

	@Override
	public int paymentComplete(int projectId) {
		return  jdbcTemplate.update(Querries.projectPayed,new Object[] {
				projectId
        });
		
	}

	@Override
	public int updateNotification(int userId, int notificationId) {
		return  jdbcTemplate.update(Querries.checkNotification,new Object[] {
				notificationId , userId
        });
	}

	@Override
	public int getIdByUserName(String userName) {
		return  jdbcTemplate.queryForObject(Querries.getIdFromUserName,new Object[] {
				userName
        }, new UseIdRowMapper());
	}

	
}
